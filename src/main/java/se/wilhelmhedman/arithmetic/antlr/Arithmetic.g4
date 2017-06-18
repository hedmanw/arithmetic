grammar Arithmetic;
// java -jar antlr4-4.5.3.jar /home/wilhelm/develop/arithmetic/src/main/java/se/wilhelmhedman/arithmetic/antlr/Arithmetic.g4
@header {
    package se.wilhelmhedman.arithmetic.antlr;
}

root: expression;

expression:
      term (PLUS | MINUS) expression #twoTerm
    | term #singleTerm
    ;
term:
      factor (TIMES | DIV) term #twoNumber
    | factor #singleNumber
    ;
factor: atom (POW atom)*;
atom:
      number #singleAtom
    | LPAREN expression RPAREN #parenthesizedExpression
    ;
number: DIGIT*;

LPAREN: '(';
RPAREN: ')';
PLUS: '+';
MINUS: '-';
TIMES: '*';
DIV: '/';
POINT: '.';
POW: '^';
DIGIT: ('0' .. '9');
WS: [ \r\n\t] -> channel (HIDDEN);
