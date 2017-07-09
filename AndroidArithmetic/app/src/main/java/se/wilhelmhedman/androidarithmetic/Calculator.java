package se.wilhelmhedman.androidarithmetic;

import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import se.wilhelmhedman.androidarithmetic.calc.ArithmeticFacade;
import se.wilhelmhedman.androidarithmetic.calc.IArithmeticQuery;
import se.wilhelmhedman.androidarithmetic.widget.CalculatorInputTextView;

public class Calculator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        final CalculatorInputTextView typingTextView = (CalculatorInputTextView) findViewById(R.id.textViewTyping);
        final TextView resultTextView = (TextView) findViewById(R.id.textViewAnswers);

        final int[] literalButtons = new int[] {
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

        };

        final View.OnClickListener literalButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button self = (Button) view;
                typingTextView.addText(self.getText());
            }
        };

        for (int i: literalButtons) {
            findViewById(i).setOnClickListener(literalButtonListener);
        }

        findViewById(R.id.buttonAc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence text = typingTextView.getText();
                if (text.length() == 0) {
                    resultTextView.setText("");
                }
                typingTextView.clear();
            }
        });

        findViewById(R.id.buttonDel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                typingTextView.delete();
            }
        });

        findViewById(R.id.buttonExe).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence text = typingTextView.getText();
                if (text.length() > 0) {
                    IArithmeticQuery response = ArithmeticFacade.execute(text.toString());
                    resultTextView.setText(response.toString());
                    if (!response.hasErrors()) {
                        typingTextView.clear();
                    }
                }
            }
        });

        resultTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startHistory = new Intent(getBaseContext(), History.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeScaleUpAnimation(view, 0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
                startActivity(startHistory, options.toBundle());
            }
        });
    }
}
