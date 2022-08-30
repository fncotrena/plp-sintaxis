//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";



package org.unp.plp.interprete;



//#line 2 "./src/interpreter.y"
  import java.io.*;
  import java.util.Collection;
  import java.util.List;
//#line 21 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//## **user defined:Object
String   yytext;//user variable to return contextual strings
Object yyval; //used to return semantic vals from action routines
Object yylval;//the 'lval' (result) I got from yylex()
Object valstk[] = new Object[YYSTACKSIZE];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
final void val_init()
{
  yyval=new Object();
  yylval=new Object();
  valptr=-1;
}
final void val_push(Object val)
{
  try {
    valptr++;
    valstk[valptr]=val;
  }
  catch (ArrayIndexOutOfBoundsException e) {
    int oldsize = valstk.length;
    int newsize = oldsize*2;
    Object[] newstack = new Object[newsize];
    System.arraycopy(valstk,0,newstack,0,oldsize);
    valstk = newstack;
    valstk[valptr]=val;
  }
}
final Object val_pop()
{
  return valstk[valptr--];
}
final void val_drop(int cnt)
{
  valptr -= cnt;
}
final Object val_peek(int relative)
{
  return valstk[valptr-relative];
}
final Object dup_yyval(Object val)
{
  return val;
}
//#### end semantic value section ####
public final static short NL=257;
public final static short CONSTANT=258;
public final static short WORLD=259;
public final static short PRINT=260;
public final static short WUMPUS=261;
public final static short HERO=262;
public final static short GOLD=263;
public final static short PIT=264;
public final static short PUT=265;
public final static short REM=266;
public final static short IN=267;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    0,    1,    2,    2,    3,    3,    4,    4,    4,
    7,    8,    8,    8,    9,    9,   10,   10,   10,   10,
   10,   11,   11,   11,   13,   13,   13,   14,   14,   12,
   12,   12,    5,    6,    6,    6,
};
final static short yylen[] = {                            2,
    0,    2,    6,    0,    4,    1,    1,    4,    4,    4,
    5,    7,    7,    7,    1,    3,    4,    4,    4,    3,
    3,    1,    3,    3,    3,    3,    1,    3,    1,    1,
    1,    1,    2,    1,    1,    1,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    0,    0,    2,    0,    6,    7,
    0,   33,   36,   34,   35,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    9,   10,    0,    8,    5,    3,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   11,    0,    0,    0,   30,   31,   32,    0,    0,    0,
   22,    0,    0,   12,    0,    0,    0,    0,    0,    0,
   13,   14,   16,    0,    0,    0,    0,    0,   27,    0,
    0,    0,    0,    0,    0,    0,    0,   29,   25,   26,
    0,   28,
};
final static short yydgoto[] = {                          2,
    3,    7,    8,    9,   10,   17,   25,   26,   48,   49,
   50,   51,   70,   79,
};
final static short yysindex[] = {                      -243,
 -235,    0, -238,  -96, -228, -184,    0,  -15,    0,    0,
 -217,    0,    0,    0,    0, -207, -205, -193,   -9,  -20,
  -10, -238, -189,  -61,    0,    0, -176,    0,    0,    0,
   39,   40,   41,  -59,  -57, -172,   -6,   30,   31,   32,
    0,  -98,  -98,  -98,    0,    0,    0,    1,   49,   12,
    0,    2,    3,    0,  -98,   36,  -60,  -58,  -98,  -98,
    0,    0,    0,  -98,  -98,   13,  -98,   13,    0,   -4,
   -4,   13,   13,   13,  -40,  -40,  -98,    0,    0,    0,
    8,    0,
};
final static short yyrindex[] = {                        98,
    0,    0,   99,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   99,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    7,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  -39,    0,  -34,    0,  -32,
  -26,  -30,  -24,  -23,    0,    0,    0,    0,    0,    0,
    0,    0,
};
final static short yygindex[] = {                         0,
    0,   79,    0,    0,    0,    0,   81,    0,  -18,    0,
  -25,   16,   43,   28,
};
final static int YYTABLESIZE=218;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         77,
   65,   32,   67,   38,   20,   40,   46,   47,   23,   21,
   23,   23,   23,   17,   24,    1,   24,   24,   24,   18,
   19,    5,    4,   11,   52,   53,    6,   23,   23,   23,
   12,   66,   68,   24,   24,   24,   63,   75,   72,   73,
   19,   74,   76,   18,   46,   47,   46,   47,   82,   23,
   59,   81,   60,   20,   59,   59,   60,   60,   21,   20,
   23,   21,   17,   22,   46,   47,   24,   30,   18,   19,
   24,   58,   56,   57,   69,   69,   13,   14,   15,   16,
   27,   33,   34,   35,   36,   37,   41,   42,   43,   44,
   78,   78,   55,   54,   61,   62,   64,    1,    4,   15,
   29,   28,   71,   80,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   45,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   31,   45,   37,   45,
   39,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   45,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
   61,   63,   61,   63,   44,   63,  105,  106,   41,   44,
   43,   44,   45,   44,   41,  259,   43,   44,   45,   44,
   44,  260,  258,  120,   43,   44,  265,   60,   61,   62,
  259,   57,   58,   60,   61,   62,   55,   42,   64,   65,
  258,   67,   47,   59,  105,  106,  105,  106,   41,   59,
   43,   77,   45,   93,   43,   43,   45,   45,   93,  267,
   93,  267,   93,  257,  105,  106,   93,  257,   93,   93,
   91,   60,   61,   62,   59,   60,  261,  262,  263,  264,
   91,  258,   44,   44,   44,  258,   93,   58,   58,   58,
   75,   76,   44,   93,   93,   93,   61,    0,    0,   93,
   22,   21,   60,   76,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  258,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  258,  258,  258,  258,
  258,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  258,
};
}
final static short YYFINAL=2;
final static short YYMAXTOKEN=267;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,"'('","')'","'*'","'+'","','",
"'-'",null,"'/'",null,null,null,null,null,null,null,null,null,null,"':'","';'",
"'<'","'='","'>'","'?'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
"'['",null,"']'",null,null,null,null,null,null,null,null,null,null,null,"'i'",
"'j'",null,null,null,null,null,null,null,null,null,null,null,null,null,"'x'",
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,"NL","CONSTANT","WORLD","PRINT",
"WUMPUS","HERO","GOLD","PIT","PUT","REM","IN",
};
final static String yyrule[] = {
"$accept : program",
"program :",
"program : world_statement statement_list",
"world_statement : WORLD CONSTANT 'x' CONSTANT ';' NL",
"statement_list :",
"statement_list : statement ';' NL statement_list",
"statement : action_statement",
"statement : print_statement",
"action_statement : PUT object IN una_celda",
"action_statement : PUT PIT IN una_celda",
"action_statement : PUT PIT IN muchas_celda",
"una_celda : '[' CONSTANT ',' CONSTANT ']'",
"muchas_celda : '[' CONSTANT ',' '?' ':' cond_list ']'",
"muchas_celda : '[' '?' ',' CONSTANT ':' cond_list ']'",
"muchas_celda : '[' '?' ',' '?' ':' cond_list ']'",
"cond_list : cond",
"cond_list : cond ',' cond_list",
"cond : expr '=' '=' expr",
"cond : expr '>' '=' expr",
"cond : expr '<' '=' expr",
"cond : expr '>' expr",
"cond : expr '<' expr",
"expr : op",
"expr : expr '+' term",
"expr : expr '-' term",
"term : term '*' factor",
"term : term '/' factor",
"term : op",
"factor : '(' expr ')'",
"factor : op",
"op : CONSTANT",
"op : 'i'",
"op : 'j'",
"print_statement : PRINT WORLD",
"object : HERO",
"object : GOLD",
"object : WUMPUS",
};

//#line 110 "./src/interpreter.y"

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
//#line 323 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 3:
//#line 34 "./src/interpreter.y"
{world.create((int)val_peek(4), (int)val_peek(2));}
break;
case 8:
//#line 48 "./src/interpreter.y"
{ world.putObject((String)val_peek(2), (Celda)val_peek(0)); }
break;
case 9:
//#line 49 "./src/interpreter.y"
{ world.putPit((Celda)val_peek(0)); }
break;
case 10:
//#line 50 "./src/interpreter.y"
{ world.putPits((Collection<Celda>)val_peek(0)); }
break;
case 11:
//#line 55 "./src/interpreter.y"
{ yyval = new Celda((int)val_peek(3),(int)val_peek(1)); }
break;
case 12:
//#line 59 "./src/interpreter.y"
{yyval = val_peek(1);}
break;
case 13:
//#line 60 "./src/interpreter.y"
{yyval = val_peek(1);}
break;
case 14:
//#line 61 "./src/interpreter.y"
{yyval = val_peek(1);}
break;
case 16:
//#line 66 "./src/interpreter.y"
{yyval = world.condicion((List<Celda>)val_peek(2),(List<Celda>)val_peek(0),(a,b) -> true);}
break;
case 17:
//#line 70 "./src/interpreter.y"
{yyval = world.condicion(((Matriz)val_peek(3)).celdas(),((Matriz)val_peek(0)).celdas(),(a,b) -> a == b);}
break;
case 18:
//#line 71 "./src/interpreter.y"
{yyval = world.condicion(((Matriz)val_peek(3)).celdas(),((Matriz)val_peek(0)).celdas(),(a,b) -> a >= b);}
break;
case 19:
//#line 72 "./src/interpreter.y"
{yyval = world.condicion(((Matriz)val_peek(3)).celdas(),((Matriz)val_peek(0)).celdas(),(a,b) -> a <= b);}
break;
case 20:
//#line 73 "./src/interpreter.y"
{yyval = world.condicion(((Matriz)val_peek(2)).celdas(),((Matriz)val_peek(0)).celdas(),(a,b) -> a > b);}
break;
case 21:
//#line 74 "./src/interpreter.y"
{yyval = world.condicion(((Matriz)val_peek(2)).celdas(),((Matriz)val_peek(0)).celdas(),(a,b) -> a < b);}
break;
case 23:
//#line 79 "./src/interpreter.y"
{ yyval = Matriz.operar((Matriz)val_peek(2), (Matriz)val_peek(0), (a,b) -> a+b); }
break;
case 24:
//#line 80 "./src/interpreter.y"
{ yyval = Matriz.operar((Matriz)val_peek(2), (Matriz)val_peek(0), (a,b) -> a-b); }
break;
case 25:
//#line 83 "./src/interpreter.y"
{ yyval = Matriz.operar((Matriz)val_peek(2), (Matriz)val_peek(0), (a,b) -> a*b); }
break;
case 26:
//#line 84 "./src/interpreter.y"
{ yyval = Matriz.operar((Matriz)val_peek(2), (Matriz)val_peek(0), (a,b) -> a/b); }
break;
case 30:
//#line 96 "./src/interpreter.y"
{ yyval = Matriz.constante((int)val_peek(0)); }
break;
case 31:
//#line 97 "./src/interpreter.y"
{ yyval = Matriz.i(); }
break;
case 32:
//#line 98 "./src/interpreter.y"
{ yyval = Matriz.j(); }
break;
case 33:
//#line 103 "./src/interpreter.y"
{ world.print(); }
break;
//#line 560 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
