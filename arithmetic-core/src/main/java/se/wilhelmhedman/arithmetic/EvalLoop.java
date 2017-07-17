package se.wilhelmhedman.arithmetic;

import se.wilhelmhedman.arithmetic.evaluation.EvaluationException;
import se.wilhelmhedman.arithmetic.evaluation.NumericException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EvalLoop {
    private static void loop() {
        BufferedReader br;

        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                System.out.print("> ");
                String input = br.readLine();

                if ("q".equals(input)) {
                    System.out.println("Exit!");
                    System.exit(0);
                }

                ExpressionRunner expressionRunner = new ExpressionRunner(input);
                String result;
                try {
                    result = expressionRunner.evaluate();
                } catch (EvaluationException e) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(' ');
                    for (int i = 0; i < e.getOffendingCharIndex(); i++) {
                        sb.append(' ');
                    }
                    sb.append('^');
                    System.out.println(sb.toString());
                    result = e.getMessage();
                } catch (NumericException e) {
                    result = e.getMessage();
                }
                System.out.println("< " + result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        loop();
    }
}
