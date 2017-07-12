package se.wilhelmhedman.arithmetic.antlr;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class ArithmeticErrorListener implements ANTLRErrorListener {
    private List<SyntaxError> syntaxErrors;

    public ArithmeticErrorListener() {
        syntaxErrors = new ArrayList<>();
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        syntaxErrors.add(new SyntaxError(charPositionInLine, msg));
    }

    @Override
    public void reportAmbiguity(Parser parser, DFA dfa, int i, int i1, boolean b, BitSet bitSet, ATNConfigSet atnConfigSet) {
        System.out.println("ambiguity");

    }

    @Override
    public void reportAttemptingFullContext(Parser parser, DFA dfa, int i, int i1, BitSet bitSet, ATNConfigSet atnConfigSet) {
        System.out.println("context");

    }

    @Override
    public void reportContextSensitivity(Parser parser, DFA dfa, int i, int i1, int i2, ATNConfigSet atnConfigSet) {
        System.out.println("sensitivity");

    }

    public List<SyntaxError> getSyntaxErrors() {
        return syntaxErrors;
    }

    public class SyntaxError {
        private final int charPositionInLine;
        private final String message;

        public SyntaxError(int charPositionInLine, String message) {
            this.charPositionInLine = charPositionInLine;
            this.message = message;
        }

        public int getCharPositionInLine() {
            return charPositionInLine;
        }

        public String getMessage() {
            return message;
        }
    }
}
