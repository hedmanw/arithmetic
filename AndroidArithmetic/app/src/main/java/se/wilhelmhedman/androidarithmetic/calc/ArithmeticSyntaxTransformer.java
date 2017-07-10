package se.wilhelmhedman.androidarithmetic.calc;

public class ArithmeticSyntaxTransformer {
    private static final Rule[] rules = new Rule[] {
            new Rule("×", "*"),
            new Rule("÷", "/"),
            new Rule("+", "+"),
            new Rule("−", "-"),
            new Rule("²", "^2")
    };

    public ArithmeticSyntaxTransformer() {
        // TODO: Caching
    }

    public String transformToArithmeticDSL(String input) {
        String temp = input;

        for (Rule rule : rules) {
            temp = rule.sourceToTargetTransformation(temp);
        }

        return temp;
    }

    public String transformFromArithmeticDSL(String input) {
        String temp = input;

        for (Rule rule : rules) {
            temp = rule.targetToSourceTransformation(temp);
        }

        return temp;
    }

    private static class Rule {
        private String sourceString;
        private String targetString;

        public Rule(String sourceString, String targetString) {
            this.sourceString = sourceString;
            this.targetString = targetString;
        }

        public String sourceToTargetTransformation(String string) {
            return string.replace(sourceString, targetString);
        }

        public String targetToSourceTransformation(String string) {
            return string.replace(targetString, sourceString);
        }
    }
}
