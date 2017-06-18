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
            put("10", "10.0");
            put("-10", "-10.0");
            put("2+-10", "2.0+[-10.0]");
            put("-10.1234", "-10.1234");
            put("1+2+3", "1.0+[2.0]+[3.0]");
            put("1+2-3", "1.0+[2.0]-[3.0]");
            put("1-2-3", "1.0-[2.0]-[3.0]");
            put("1-2+3", "1.0-[2.0]+[3.0]");
            put("1+2*3", "1.0+[2.0*[3.0]]");
            put("1*2+3", "1.0*[2.0]+[3.0]");
            put("1*2*3", "1.0*[2.0]*[3.0]");
            put("10^2", "10.0^[2.0]");
            put("2*10^2", "2.0*[10.0^[2.0]]");
            put("10^2*2", "10.0^[2.0]*[2.0]");
            put("(2*2)+2", "(2.0*[2.0])+[2.0]");
            put("2*(2+2)", "2.0*[(2.0+[2.0])]");
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
        assertEquals("6.0", er.evaluate());

        er = new ExpressionRunner("1.0+2+3");
        assertEquals("6.0", er.evaluate());

        er = new ExpressionRunner("1-2+3");
        assertEquals("2.0", er.evaluate());
    }
}