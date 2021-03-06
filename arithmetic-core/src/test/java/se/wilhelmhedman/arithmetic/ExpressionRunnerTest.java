package se.wilhelmhedman.arithmetic;

import org.junit.Test;
import se.wilhelmhedman.arithmetic.evaluation.EvaluationContext;
import se.wilhelmhedman.arithmetic.evaluation.EvaluationException;
import se.wilhelmhedman.arithmetic.evaluation.NumericException;
import se.wilhelmhedman.arithmetic.tree.Expression;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class ExpressionRunnerTest {
    @Test
    public void getRoot() throws Exception {
        Map<String, String> input = new HashMap<String, String>() {{
            put("10", "10");
            put("-10", "-10");
            put("2+-10", "2+[-10]");
            put("-10.1234", "-10.1234");
            put("1+2+3", "1+[2]+[3]");
            put("1+2-3", "1+[2]-[3]");
            put("1-2-3", "1-[2]-[3]");
            put("1-2+3", "1-[2]+[3]");
            put("1+2*3", "1+[2*[3]]");
            put("1*2+3", "1*[2]+[3]");
            put("1*2*3", "1*[2]*[3]");
            put("10^2", "10^[2]");
            put("2*10^2", "2*[10^[2]]");
            put("10^2*2", "10^[2]*[2]");
            put("(2*2)+2", "(2*[2])+[2]");
            put("2*(2+2)", "2*[(2+[2])]");
            put("sin(90)", "sin[(90)]");
            put("sin(45*2)", "sin[(45*[2])]");
            put("pi/pi", "pi/[pi]");
        }};

        for (String key: input.keySet()) {
            ExpressionRunner er = new ExpressionRunner(key);
            Expression ex = er.getRoot();
            assertEquals("Failure for " + key, input.get(key), ex.toString());
        }
    }

    @Test
    public void evaluate() throws Exception {
        ExpressionRunner er = new ExpressionRunner("1+2+3");
        assertEquals("6", er.evaluate());

        er = new ExpressionRunner("1+2+3");
        assertEquals("6", er.evaluate());

        er = new ExpressionRunner("1-2+3");
        assertEquals("2", er.evaluate());

        er = new ExpressionRunner("0.5+0.5");
        assertEquals("1", er.evaluate());

        er = new ExpressionRunner("10");
        assertEquals("10", er.evaluate());

        er = new ExpressionRunner("999999");
        assertEquals("999999", er.evaluate());

        er = new ExpressionRunner("100^0.5");
        assertEquals("10", er.evaluate());

        er = new ExpressionRunner("5^2");
        assertEquals("25", er.evaluate());

        er = new ExpressionRunner("10^3");
        assertEquals("1000", er.evaluate());

        er = new ExpressionRunner("10^-2");
        assertEquals("0.01", er.evaluate());

        er = new ExpressionRunner("1000000");
        assertEquals("1E+6", er.evaluate());

        er = new ExpressionRunner("250000*5");
        assertEquals("1.25E+6", er.evaluate());

        er = new ExpressionRunner("250000*250");
        assertEquals("6.25E+7", er.evaluate());

        er = new ExpressionRunner("0.0001*0.5");
        assertEquals("0.00005", er.evaluate());

        er = new ExpressionRunner("1/3");
        assertEquals("0.33333", er.evaluate());

        er = new ExpressionRunner("sin(90)");
        assertEquals("1", er.evaluate());

        er = new ExpressionRunner("sin(45+45)");
        assertEquals("1", er.evaluate());

        er = new ExpressionRunner("cos(90)");
        assertEquals("0", er.evaluate());

        er = new ExpressionRunner("cos(0)");
        assertEquals("1", er.evaluate());

        er = new ExpressionRunner("ln(2.718281828459045)");
        assertEquals("1", er.evaluate());

        er = new ExpressionRunner("log(10^10)");
        assertEquals("10", er.evaluate());

        er = new ExpressionRunner("ln(e^2)");
        assertEquals("2", er.evaluate());

        er = new ExpressionRunner("2*ans", "5");
        assertEquals("10", er.evaluate());
    }

    @Test
    public void identities() throws Exception {
        Map<String, String> input = new HashMap<String, String>() {{
            put("(2+2)-2", "2");
            put("2+(2-2)", "2");
            put("(500*500)/500", "500");
            put("1000*(1000/1000)", "1000");
            put("(4^2)^(1/2)", "4");
            put("(16^3)^(1/3)", "16");
            put("ln(e)", "1");
            put("log(10^10)", "10");
            put("asin(sin(90))", "90");
            put("acos(cos(90))", "90");
            put("atan(tan(45))", "45");
        }};

        runExpected(input);
    }

    private void runExpected(Map<String, String> input) throws EvaluationException, NumericException {
        for (String key: input.keySet()) {
            ExpressionRunner er = new ExpressionRunner(key);
            String actual = er.evaluate();
            assertEquals("Failure for " + key, input.get(key), actual);
        }
    }

    @Test
    public void trigonometric() throws Exception {
        ExpressionRunner er;

        EvaluationContext degreesContext = new EvaluationContext.EvaluationContextBuilder().setUseDegrees(true).build();
        EvaluationContext.setActiveContext(degreesContext);

        er = new ExpressionRunner("sin(270)");
        assertEquals("-1", er.evaluate());

        er = new ExpressionRunner("sin(180)");
        assertEquals("0", er.evaluate());

        er = new ExpressionRunner("sin(90)");
        assertEquals("1", er.evaluate());

        er = new ExpressionRunner("sin(0)");
        assertEquals("0", er.evaluate());

        er = new ExpressionRunner("asin(1)");
        assertEquals("90", er.evaluate());

        er = new ExpressionRunner("asin(0)");
        assertEquals("0", er.evaluate());

        er = new ExpressionRunner("cos(270)");
        assertEquals("0", er.evaluate());

        er = new ExpressionRunner("cos(180)");
        assertEquals("-1", er.evaluate());

        er = new ExpressionRunner("cos(90)");
        assertEquals("0", er.evaluate());

        er = new ExpressionRunner("cos(0)");
        assertEquals("1", er.evaluate());

        er = new ExpressionRunner("tan(45)");
        assertEquals("1", er.evaluate());

        EvaluationContext radiansContext = new EvaluationContext.EvaluationContextBuilder().setUseDegrees(false).build();
        EvaluationContext.setActiveContext(radiansContext);

        er = new ExpressionRunner("sin(3*pi/2)");
        assertEquals("-1", er.evaluate());

        er = new ExpressionRunner("sin(pi)");
        assertEquals("0", er.evaluate());

        er = new ExpressionRunner("sin(pi/2)");
        assertEquals("1", er.evaluate());

        er = new ExpressionRunner("sin(0)");
        assertEquals("0", er.evaluate());

        er = new ExpressionRunner("asin(1)");
        assertEquals("1.5708", er.evaluate());

        er = new ExpressionRunner("asin(0)");
        assertEquals("0", er.evaluate());

        er = new ExpressionRunner("cos(3*pi/2)");
        assertEquals("0", er.evaluate());

        er = new ExpressionRunner("cos(pi)");
        assertEquals("-1", er.evaluate());

        er = new ExpressionRunner("cos(pi/2)");
        assertEquals("0", er.evaluate());

        er = new ExpressionRunner("cos(0)");
        assertEquals("1", er.evaluate());
    }

    @Test
    public void syntaxErrors() throws Exception {
        assertSyntaxErrorsThrown(new ExpressionRunner("50+"), 3);
        assertSyntaxErrorsThrown(new ExpressionRunner("+"), 0);
        assertSyntaxErrorsThrown(new ExpressionRunner("(-"), 2);
        assertSyntaxErrorsThrown(new ExpressionRunner("-"), 1);
        assertSyntaxErrorsThrown(new ExpressionRunner("-("), 1);
        assertSyntaxErrorsThrown(new ExpressionRunner("-^2"), 1);
        assertSyntaxErrorsThrown(new ExpressionRunner("sin"), 3);
    }


    @Test
    public void mathErrors() throws Exception {
        assertMathErrors("10/0");
        assertMathErrors("tan(90)");
        assertMathErrors("tan(270)");

    }

    private void assertSyntaxErrorsThrown(ExpressionRunner er, int expected) {
        try {
            er.evaluate();
            fail("Failure for " + er.getInput());
        } catch (EvaluationException e) {
            assertEquals(expected, e.getOffendingCharIndex());
        } catch (NumericException e) {
            fail("Threw NumericException.");
        }
    }

    private void assertMathErrors(String s) {
        try {
            ExpressionRunner er = new ExpressionRunner(s);
            er.evaluate();
            fail("Failure for " + er.getInput() + ", no exception was thrown.");
        } catch (EvaluationException e) {
            fail("Wrong exception! EvaluationException was thrown!");
            e.printStackTrace();
        } catch (NumericException e) {
            assertEquals(e.getClass(), NumericException.class);
        }
    }
}