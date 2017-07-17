grammar Arithmetic;
// java -jar antlr4-4.5.3.jar /home/wilhelm/develop/arithmetic/arithmetic-core/src/main/java/se/wilhelmhedman/arithmetic/antlr/Arithmetic.g4
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
factor:
      atom (POW atom)* #powerFactor
    | (SIN | COS | TAN | LN | LOG) atom #functionFactor
    ;
atom:
      number #singleAtom
    | LPAREN expression RPAREN #parenthesizedExpression
    | (PI | E | ANS) #constantAtom
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
SIN: 'sin';
COS: 'cos';
TAN: 'tan';
LN: 'ln';
LOG: 'log';
PI: 'pi';
E: 'e';
ANS: 'ans';
DIGIT: ('0' .. '9');
WS: [ \r\n\t] -> channel (HIDDEN);
