grammar Arithmetic;
// java -jar antlr4-4.5.3.jar /home/wilhelm/develop/arithmetic/src/main/java/se/wilhelmhedman/arithmetic/antlr/Arithmetic.g4
@header {
    package se.wilhelmhedman.arithmetic.antlr;
}

root: expression;

expression:
      expression (PLUS | MINUS) term #twoExpression
    | term #singleExpression
    ;
term:
      term (TIMES | DIV) factor #twoTerm
    | factor #singleTerm
    ;
factor: atom (POW atom)*;
atom:
      number #singleAtom
    | LPAREN expression RPAREN #parenthesizedExpression
    ;
number: MINUS? DIGIT+ (POINT DIGIT*)?;

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