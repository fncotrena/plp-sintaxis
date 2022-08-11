// fuente byaccj para una calculadora sencilla


%{
  import java.io.*;
  import java.util.List;
  import java.util.ArrayList;
%}


// lista de tokens por orden de prioridad

%token NL         // nueva línea
%token CONSTANT   // constante
%token WORLD   // constante
%token GOLD  
%token PIT  
%token WUMPUS  
%token HERO
%token PRINT
%token IN
%token CORCHETEA 
%token CORCHETEB
%token SEMI
%token REM

%%
//WORld statt, action_statement ;print_statement

program
  : word_statement action_statement statement_list 
  |
SEMI

word_statement
  : WORLD CONSTANT X CONSTANT SEMI NL {world.create(int)$2,(int)$4)}
SEMI

action_statement
  : ACTION $2 IN CORCHETEA CONSTANTS SEMI CONSTANTS CORCHETEB {wold.put((String)$2,(int)$5,int()))}
SEMI

print_statement
:PRINT WORLD {world.print}

statement_list
  : statement                // Unica sentencia
  | statement statement_list // Sentencia,y lista
SEMI

statement
:action_statement 
|print_statement
;
action 
:PUT{System.out.println("pone")}
|REM{System.out.println("remover")}

%%

  /** referencia al analizador léxico
  **/
  private Lexer lexer ;

  private WumpusWorld world;

  /** constructor: crea el Interpreteranalizador léxico (lexer)
  **/
  public Parser(Reader r)
  {
     lexer = new Lexer(r, this);
     world = new WumpusWorld();
  }

  /** esta función se invoca por el analizador cuando necesita el
  *** siguiente token del analizador léxico
  **/
  private int yylex ()
  {
    int yyl_return = -1;

    try
    {
       yylval = new Object();
       yyl_return = lexer.yylex();
    }
    catch (IOException e)
    {
       System.err.println("error de E/S:"+e);
    }

    return yyl_return;
  }

  /** invocada cuando se produce un error
  **/
  public void yyerror (String descripcion, int yystate, int token)
  {
     System.err.println ("Error en línea "+Integer.toString(lexer.lineaActual())+" : "+descripcion);
     System.err.println ("Token leído : "+yyname[token]);
  }

  public void yyerror (String descripcion)
  {
     System.err.println ("Error en línea "+Integer.toString(lexer.lineaActual())+" : "+descripcion);
     //System.err.println ("Token leido : "+yyname[token]);
  }
