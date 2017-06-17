grammar Arithmetic;

@header {
    package se.wilhelmhedman.arithmetic.antlr;
}

root: expression;

expression:
      term (PLUS | MINUS) expression #twoTerm
    | term #singleTerm
    ;
term:
      number (TIMES | DIV) term #twoNumber
    | number #singleNumber
    ;
//term: factor ((TIMES | DIV) factor)*;
//factor: atom (POW atom)*;
//atom: number | LPAREN expression RPAREN;
number: DIGIT;

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
