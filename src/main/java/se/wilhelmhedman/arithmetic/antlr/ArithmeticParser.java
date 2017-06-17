// Generated from /home/wilhelm/develop/arithmetic/src/main/java/se/wilhelmhedman/arithmetic/antlr/Arithmetic.g4 by ANTLR 4.5.3

    package se.wilhelmhedman.arithmetic.antlr;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ArithmeticParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		LPAREN=1, RPAREN=2, PLUS=3, MINUS=4, TIMES=5, DIV=6, POINT=7, POW=8, DIGIT=9, 
		WS=10;
	public static final int
		RULE_root = 0, RULE_expression = 1, RULE_term = 2, RULE_number = 3;
	public static final String[] ruleNames = {
		"root", "expression", "term", "number"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "')'", "'+'", "'-'", "'*'", "'/'", "'.'", "'^'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "LPAREN", "RPAREN", "PLUS", "MINUS", "TIMES", "DIV", "POINT", "POW", 
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

	@Override
	public String getGrammarFileName() { return "Arithmetic.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ArithmeticParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class RootContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public RootContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_root; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArithmeticListener ) ((ArithmeticListener)listener).enterRoot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArithmeticListener ) ((ArithmeticListener)listener).exitRoot(this);
		}
	}

	public final RootContext root() throws RecognitionException {
		RootContext _localctx = new RootContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_root);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(8);
			expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class SingleTermContext extends ExpressionContext {
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public SingleTermContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArithmeticListener ) ((ArithmeticListener)listener).enterSingleTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArithmeticListener ) ((ArithmeticListener)listener).exitSingleTerm(this);
		}
	}
	public static class TwoTermContext extends ExpressionContext {
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode PLUS() { return getToken(ArithmeticParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(ArithmeticParser.MINUS, 0); }
		public TwoTermContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArithmeticListener ) ((ArithmeticListener)listener).enterTwoTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArithmeticListener ) ((ArithmeticListener)listener).exitTwoTerm(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_expression);
		int _la;
		try {
			setState(15);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				_localctx = new TwoTermContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(10);
				term();
				setState(11);
				_la = _input.LA(1);
				if ( !(_la==PLUS || _la==MINUS) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(12);
				expression();
				}
				break;
			case 2:
				_localctx = new SingleTermContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(14);
				term();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermContext extends ParserRuleContext {
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
	 
		public TermContext() { }
		public void copyFrom(TermContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class SingleNumberContext extends TermContext {
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public SingleNumberContext(TermContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArithmeticListener ) ((ArithmeticListener)listener).enterSingleNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArithmeticListener ) ((ArithmeticListener)listener).exitSingleNumber(this);
		}
	}
	public static class TwoNumberContext extends TermContext {
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public TerminalNode TIMES() { return getToken(ArithmeticParser.TIMES, 0); }
		public TerminalNode DIV() { return getToken(ArithmeticParser.DIV, 0); }
		public TwoNumberContext(TermContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArithmeticListener ) ((ArithmeticListener)listener).enterTwoNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArithmeticListener ) ((ArithmeticListener)listener).exitTwoNumber(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_term);
		int _la;
		try {
			setState(22);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				_localctx = new TwoNumberContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(17);
				number();
				setState(18);
				_la = _input.LA(1);
				if ( !(_la==TIMES || _la==DIV) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(19);
				term();
				}
				break;
			case 2:
				_localctx = new SingleNumberContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(21);
				number();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NumberContext extends ParserRuleContext {
		public TerminalNode DIGIT() { return getToken(ArithmeticParser.DIGIT, 0); }
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArithmeticListener ) ((ArithmeticListener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArithmeticListener ) ((ArithmeticListener)listener).exitNumber(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_number);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(24);
			match(DIGIT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\f\35\4\2\t\2\4\3"+
		"\t\3\4\4\t\4\4\5\t\5\3\2\3\2\3\3\3\3\3\3\3\3\3\3\5\3\22\n\3\3\4\3\4\3"+
		"\4\3\4\3\4\5\4\31\n\4\3\5\3\5\3\5\2\2\6\2\4\6\b\2\4\3\2\5\6\3\2\7\b\32"+
		"\2\n\3\2\2\2\4\21\3\2\2\2\6\30\3\2\2\2\b\32\3\2\2\2\n\13\5\4\3\2\13\3"+
		"\3\2\2\2\f\r\5\6\4\2\r\16\t\2\2\2\16\17\5\4\3\2\17\22\3\2\2\2\20\22\5"+
		"\6\4\2\21\f\3\2\2\2\21\20\3\2\2\2\22\5\3\2\2\2\23\24\5\b\5\2\24\25\t\3"+
		"\2\2\25\26\5\6\4\2\26\31\3\2\2\2\27\31\5\b\5\2\30\23\3\2\2\2\30\27\3\2"+
		"\2\2\31\7\3\2\2\2\32\33\7\13\2\2\33\t\3\2\2\2\4\21\30";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}