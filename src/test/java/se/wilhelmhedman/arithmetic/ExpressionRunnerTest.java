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
            put("1+2+3", "1.0+[2.0+[3.0]]");
            put("1+2-3", "1.0+[2.0-[3.0]]");
            put("1-2-3", "1.0-[2.0-[3.0]]");
            put("1-2+3", "1.0-[2.0+[3.0]]");
            put("1+2*3", "1.0+[2.0*[3.0]]");
            put("1*2+3", "1.0*[2.0]+[3.0]");
        }};

        for (String key: input.keySet()) {
            ExpressionRunner er = new ExpressionRunner(key);
            Expression ex = er.getRoot();
            assertEquals(input.get(key), ex.toString());
        }
    }

}