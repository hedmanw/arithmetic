package se.wilhelmhedman.arithmetic;

import org.junit.Test;
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

        er = new ExpressionRunner("1000000");
        assertEquals("1E+6", er.evaluate());

        er = new ExpressionRunner("250000*5");
        assertEquals("1.25E+6", er.evaluate());

        er = new ExpressionRunner("0.0001*0.5");
        assertEquals("0.00005", er.evaluate());
    }
}