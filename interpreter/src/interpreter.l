// fuente 'jflex' para interprete

package org.unp.plp.interprete;

%%

%class Lexer
%byaccj

%{

  private Parser yyparser;

  /** constructor del analizador sintáctico
  *** acepta como parámetro el lector de caracteres a usar
  **/

  public Lexer(java.io.Reader r, Parser p )
  {
    this(r);
    yyparser = p  ;
  }

  /** devuelve el numero de linea donde está el último caracter leido
  **/

  public int lineaActual()
  {
     return yyline;
  }

%}

%%
"world" {return Parser.WORLD;}
"x" {return Parser.X;}

"gold" {return Parser.GOLD;}

"pit" {return Parser.PIT;}

"wumpus" {return Parser.WUMPUS;}

"hero" {return Parser.HERO;}

"[" {return Parser.CORCHETEA;}

"]" {return Parser.CORCHETEB;}

";" {return Parser.SEMI;}

"rem" {return Parser.REM;}


"put" {return Parser.PUT;}
[0-9]+
   {  yyparser.yylval = new Integer(yytext());
      return Parser.CONSTANT;
   }

\r|\n|\r\n
  { // lleva la cuenta de lineas
    yyline++ ;
    return Parser.NL;
  }

[ \t]+ {  /** accionwumpus [3,2] vacia: se ignoran los espacios y tabuladores **/
       }

.      {  /** token desconocido: se produce un mensaje de error **/
          yyparser.yyerror("El lexema '"+yytext()+"' no corresponden a ningún token conocido");
       }
