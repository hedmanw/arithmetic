package se.wilhelmhedman.androidarithmetic;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import java.util.HashMap;
import java.util.Map;

import se.wilhelmhedman.androidarithmetic.calc.ArithmeticFacade;
import se.wilhelmhedman.androidarithmetic.calc.ErrorResponse;
import se.wilhelmhedman.androidarithmetic.calc.IArithmeticQuery;
import se.wilhelmhedman.androidarithmetic.widget.CalculatorHistoryView;
import se.wilhelmhedman.androidarithmetic.widget.CalculatorInputTextView;
import se.wilhelmhedman.androidarithmetic.widget.EvaluationErrorNotificationFacade;
import se.wilhelmhedman.androidarithmetic.widget.SyntaxErrorTextView;

public class Calculator extends AppCompatActivity {
    private static final int[] LITERAL_BUTTONS = new int[] {
            R.id.button0,
            R.id.button1,
            R.id.button2,
            R.id.button3,
            R.id.button4,
            R.id.button5,
            R.id.button6,
            R.id.button7,
            R.id.button8,
            R.id.button9,
            R.id.buttonAdd,
            R.id.buttonSub,
            R.id.buttonMul,
            R.id.buttonDiv,
            R.id.buttonPow,
            R.id.buttonDecimal,
            R.id.buttonRParen,
            R.id.buttonLParen,
            R.id.buttonSquare,
            R.id.buttonAns,
    };
    private static final Map<Integer, String> SPECIAL_BUTTON_ACTIONS = new HashMap<Integer, String>() {{
        put(R.id.buttonSin, "sin(");
        put(R.id.buttonCos, "cos(");
        put(R.id.buttonTan, "tan(");
        put(R.id.buttonLog, "log(");
        put(R.id.buttonLn, "ln(");
    }};

    private CalculatorInputTextView typingTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        typingTextView = (CalculatorInputTextView) findViewById(R.id.textViewTyping);
        final SyntaxErrorTextView errorContainer = (SyntaxErrorTextView) findViewById(R.id.containerError);
        final EvaluationErrorNotificationFacade errorNotificationFacade = new EvaluationErrorNotificationFacade(typingTextView, errorContainer);
        final CalculatorHistoryView calculatorHistoryView = (CalculatorHistoryView) findViewById(android.R.id.list);

        final View.OnClickListener literalButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button self = (Button) view;
                typingTextView.addText(self.getText());
                errorNotificationFacade.clearEvaluationError();
            }
        };

        for (int i: LITERAL_BUTTONS) {
            findViewById(i).setOnClickListener(literalButtonListener);
        }

        final View.OnClickListener specialButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                typingTextView.addText(SPECIAL_BUTTON_ACTIONS.get(view.getId()));
                errorNotificationFacade.clearEvaluationError();
            }
        };

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
