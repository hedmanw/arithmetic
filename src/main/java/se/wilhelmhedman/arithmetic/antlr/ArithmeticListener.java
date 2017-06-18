// Generated from /home/wilhelm/develop/arithmetic/src/main/java/se/wilhelmhedman/arithmetic/antlr/Arithmetic.g4 by ANTLR 4.5.3

    package se.wilhelmhedman.arithmetic.antlr;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ArithmeticParser}.
 */
public interface ArithmeticListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ArithmeticParser#root}.
	 * @param ctx the parse tree
	 */
	void enterRoot(ArithmeticParser.RootContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArithmeticParser#root}.
	 * @param ctx the parse tree
	 */
	void exitRoot(ArithmeticParser.RootContext ctx);
	/**
	 * Enter a parse tree produced by the {@code twoTerm}
	 * labeled alternative in {@link ArithmeticParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterTwoTerm(ArithmeticParser.TwoTermContext ctx);
	/**
	 * Exit a parse tree produced by the {@code twoTerm}
	 * labeled alternative in {@link ArithmeticParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitTwoTerm(ArithmeticParser.TwoTermContext ctx);
	/**
	 * Enter a parse tree produced by the {@code singleTerm}
	 * labeled alternative in {@link ArithmeticParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterSingleTerm(ArithmeticParser.SingleTermContext ctx);
	/**
	 * Exit a parse tree produced by the {@code singleTerm}
	 * labeled alternative in {@link ArithmeticParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitSingleTerm(ArithmeticParser.SingleTermContext ctx);
	/**
	 * Enter a parse tree produced by the {@code twoNumber}
	 * labeled alternative in {@link ArithmeticParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTwoNumber(ArithmeticParser.TwoNumberContext ctx);
	/**
	 * Exit a parse tree produced by the {@code twoNumber}
	 * labeled alternative in {@link ArithmeticParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTwoNumber(ArithmeticParser.TwoNumberContext ctx);
	/**
	 * Enter a parse tree produced by the {@code singleNumber}
	 * labeled alternative in {@link ArithmeticParser#term}.
	 * @param ctx the parse tree
	 */
	void enterSingleNumber(ArithmeticParser.SingleNumberContext ctx);
	/**
	 * Exit a parse tree produced by the {@code singleNumber}
	 * labeled alternative in {@link ArithmeticParser#term}.
	 * @param ctx the parse tree
	 */
	void exitSingleNumber(ArithmeticParser.SingleNumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArithmeticParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterFactor(ArithmeticParser.FactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArithmeticParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitFactor(ArithmeticParser.FactorContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArithmeticParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(ArithmeticParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArithmeticParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(ArithmeticParser.NumberContext ctx);
}