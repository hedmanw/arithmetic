// Generated from /home/wilhelm/develop/arithmetic/arithmetic-core/src/main/java/se/wilhelmhedman/arithmetic/antlr/Arithmetic.g4 by ANTLR 4.5.3

    package se.wilhelmhedman.arithmetic.antlr;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ArithmeticLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		LPAREN=1, RPAREN=2, PLUS=3, MINUS=4, TIMES=5, DIV=6, POINT=7, POW=8, SIN=9, 
		ASIN=10, COS=11, ACOS=12, TAN=13, ATAN=14, LN=15, LOG=16, PI=17, E=18, 
		ANS=19, DIGIT=20, WS=21;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"LPAREN", "RPAREN", "PLUS", "MINUS", "TIMES", "DIV", "POINT", "POW", "SIN", 
		"ASIN", "COS", "ACOS", "TAN", "ATAN", "LN", "LOG", "PI", "E", "ANS", "DIGIT", 
		"WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "')'", "'+'", "'-'", "'*'", "'/'", "'.'", "'^'", "'sin'", 
		"'asin'", "'cos'", "'acos'", "'tan'", "'atan'", "'ln'", "'log'", "'pi'", 
		"'e'", "'ans'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "LPAREN", "RPAREN", "PLUS", "MINUS", "TIMES", "DIV", "POINT", "POW", 
		"SIN", "ASIN", "COS", "ACOS", "TAN", "ATAN", "LN", "LOG", "PI", "E", "ANS", 
		"DIGIT", "WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public ArithmeticLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Arithmetic.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\27n\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3"+
		"\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13"+
		"\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\17\3\17"+
		"\3\17\3\17\3\17\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\23"+
		"\3\23\3\24\3\24\3\24\3\24\3\25\3\25\3\26\3\26\3\26\3\26\2\2\27\3\3\5\4"+
		"\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22"+
		"#\23%\24\'\25)\26+\27\3\2\3\5\2\13\f\17\17\"\"m\2\3\3\2\2\2\2\5\3\2\2"+
		"\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2"+
		"\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3"+
		"\2\2\2\2)\3\2\2\2\2+\3\2\2\2\3-\3\2\2\2\5/\3\2\2\2\7\61\3\2\2\2\t\63\3"+
		"\2\2\2\13\65\3\2\2\2\r\67\3\2\2\2\179\3\2\2\2\21;\3\2\2\2\23=\3\2\2\2"+
		"\25A\3\2\2\2\27F\3\2\2\2\31J\3\2\2\2\33O\3\2\2\2\35S\3\2\2\2\37X\3\2\2"+
		"\2![\3\2\2\2#_\3\2\2\2%b\3\2\2\2\'d\3\2\2\2)h\3\2\2\2+j\3\2\2\2-.\7*\2"+
		"\2.\4\3\2\2\2/\60\7+\2\2\60\6\3\2\2\2\61\62\7-\2\2\62\b\3\2\2\2\63\64"+
		"\7/\2\2\64\n\3\2\2\2\65\66\7,\2\2\66\f\3\2\2\2\678\7\61\2\28\16\3\2\2"+
		"\29:\7\60\2\2:\20\3\2\2\2;<\7`\2\2<\22\3\2\2\2=>\7u\2\2>?\7k\2\2?@\7p"+
		"\2\2@\24\3\2\2\2AB\7c\2\2BC\7u\2\2CD\7k\2\2DE\7p\2\2E\26\3\2\2\2FG\7e"+
		"\2\2GH\7q\2\2HI\7u\2\2I\30\3\2\2\2JK\7c\2\2KL\7e\2\2LM\7q\2\2MN\7u\2\2"+
		"N\32\3\2\2\2OP\7v\2\2PQ\7c\2\2QR\7p\2\2R\34\3\2\2\2ST\7c\2\2TU\7v\2\2"+
		"UV\7c\2\2VW\7p\2\2W\36\3\2\2\2XY\7n\2\2YZ\7p\2\2Z \3\2\2\2[\\\7n\2\2\\"+
		"]\7q\2\2]^\7i\2\2^\"\3\2\2\2_`\7r\2\2`a\7k\2\2a$\3\2\2\2bc\7g\2\2c&\3"+
		"\2\2\2de\7c\2\2ef\7p\2\2fg\7u\2\2g(\3\2\2\2hi\4\62;\2i*\3\2\2\2jk\t\2"+
		"\2\2kl\3\2\2\2lm\b\26\2\2m,\3\2\2\2\3\2\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}