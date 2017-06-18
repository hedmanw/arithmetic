package se.wilhelmhedman.arithmetic;

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
                double result = expressionRunner.evaluate();
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
