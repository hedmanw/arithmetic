package se.wilhelmhedman.androidarithmetic;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import se.wilhelmhedman.androidarithmetic.calc.ArithmeticFacade;
import se.wilhelmhedman.androidarithmetic.calc.ErrorResponse;
import se.wilhelmhedman.androidarithmetic.calc.IArithmeticQuery;
import se.wilhelmhedman.androidarithmetic.listeners.InsertingOperatorButtonListener;
import se.wilhelmhedman.androidarithmetic.listeners.SpecialButtonListener;
import se.wilhelmhedman.androidarithmetic.widget.CalculatorHistoryView;
import se.wilhelmhedman.androidarithmetic.widget.CalculatorInputTextView;
import se.wilhelmhedman.androidarithmetic.widget.EvaluationErrorNotificationFacade;
import se.wilhelmhedman.androidarithmetic.listeners.LiteralButtonListener;
import se.wilhelmhedman.androidarithmetic.widget.SyntaxErrorTextView;

import static se.wilhelmhedman.androidarithmetic.listeners.LiteralButtonListener.LITERAL_BUTTONS;
import static se.wilhelmhedman.androidarithmetic.listeners.InsertingOperatorButtonListener.INSERTING_OPERATOR_BUTTONS;
import static se.wilhelmhedman.androidarithmetic.listeners.SpecialButtonListener.SPECIAL_BUTTON_ACTIONS;

public class Calculator extends AppCompatActivity {

    private CalculatorInputTextView typingTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        typingTextView = (CalculatorInputTextView) findViewById(R.id.textViewTyping);
        final SyntaxErrorTextView errorContainer = (SyntaxErrorTextView) findViewById(R.id.containerError);
        final EvaluationErrorNotificationFacade errorNotificationFacade = new EvaluationErrorNotificationFacade(typingTextView, errorContainer);
        final CalculatorHistoryView calculatorHistoryView = (CalculatorHistoryView) findViewById(android.R.id.list);

        final View.OnClickListener literalButtonListener = new LiteralButtonListener(typingTextView, errorNotificationFacade);
        for (int i: LITERAL_BUTTONS) {
            findViewById(i).setOnClickListener(literalButtonListener);
        }

        final View.OnClickListener insertingOperatorButtonListener = new InsertingOperatorButtonListener(typingTextView, errorNotificationFacade);
        for (int i : INSERTING_OPERATOR_BUTTONS) {
            findViewById(i).setOnClickListener(insertingOperatorButtonListener);
        }

        final View.OnClickListener specialButtonListener = new SpecialButtonListener(typingTextView, errorNotificationFacade);
        for (int i: SPECIAL_BUTTON_ACTIONS.keySet()) {
            findViewById(i).setOnClickListener(specialButtonListener);
        }

        findViewById(R.id.buttonAc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence text = typingTextView.getText();
                if (text.length() == 0) {
                    calculatorHistoryView.removeAllViews();
                }
                typingTextView.clear();
                errorNotificationFacade.clearEvaluationError();
            }
        });

        findViewById(R.id.buttonDel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                typingTextView.delete();
                errorNotificationFacade.clearEvaluationError();
            }
        });

        findViewById(R.id.buttonExe).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence text = typingTextView.getText();
                if (text.length() > 0) {
                    IArithmeticQuery response = ArithmeticFacade.execute(text.toString());
                    if (!response.hasErrors()) {
                        typingTextView.clear();
                        calculatorHistoryView.addHistoryEntry(response.toString());
                        errorNotificationFacade.clearEvaluationError();
                    }
                    else {
                        ErrorResponse errorResponse = (ErrorResponse) response;
                        errorNotificationFacade.setEvaluationError(errorResponse);
                    }
                }
            }
        });

        calculatorHistoryView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startHistory = new Intent(getBaseContext(), History.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeScaleUpAnimation(view, 0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
                ActivityCompat.startActivityForResult(Calculator.this, startHistory, 0, options.toBundle());
            }
        });

//        ArithmeticFacade.execute("1+2+3");
//        ArithmeticFacade.execute("5*6*7");
    }

    private void redo(String value) {
        typingTextView.setText(value);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 0:
                if (resultCode == History.RESULT_REDO) {
                    String returnValue = data.getStringExtra("content");
                    redo(returnValue);
                }
                break;
        }
    }
}
