package inicio.flexycup;
import java.util.ArrayList;
import lenguajes.proyecto2.clases.*;


%% //separador de area

%{
    public static ArrayList<Errores> erroresLexicos=new ArrayList<>();
  public static ArrayList<Token> tokens =new ArrayList<>();
%}

/* opciones y declaraciones de jflex */
%public
%class Lexer
%line
%type Token
%column
%{
    public ArrayList<Token> getTokens() {
        return tokens;
    }
%}
LineTerminator = \r|\n|\r\n

WhiteSpace = {LineTerminator} | [ \t\f]

/* integer literals */
entero = [0-9]
DECIMAL         = [0-9]+(\.[0-9]+)?
CADENA_SIMPLE   = \'[^\']*\'
IDENTIFICADOR   = [a-zA-Z][a-zA-Z0-9_]*
COMENTARIO      = --[^\n]*
FECHA           = \'[0-9]{4}-[0-9]{2}-[0-9]{2}\'


%% // separador de areas

/* reglas lexicas */
<YYINITIAL>{

	"CREATE" { tokens.add(new Token(yytext(),TokenType.CREATE,yyline, yycolumn)); }
    "DATABASE" { tokens.add(new Token(yytext(),TokenType.CREATE,yyline, yycolumn)); }
    "TABLE" { tokens.add(new Token(yytext(),TokenType.CREATE,yyline, yycolumn)); }
      "KEY"     {tokens.add(new Token(yytext(),TokenType.CREATE,yyline, yycolumn));}
      "NULL" {tokens.add(new Token(yytext(),TokenType.CREATE,yyline, yycolumn));}
      "PRIMARY" {tokens.add(new Token(yytext(),TokenType.CREATE,yyline, yycolumn));}
	"UNIQUE"	{tokens.add(new Token(yytext(),TokenType.CREATE,yyline, yycolumn));}

	"FOREIGN"		{tokens.add(new Token(yytext(),TokenType.CREATE,yyline, yycolumn));}
     "REFERENCES"     {tokens.add(new Token(yytext(),TokenType.CREATE,yyline, yycolumn));}
      "ALTER"     {tokens.add(new Token(yytext(),TokenType.CREATE,yyline, yycolumn));}
      "ADD"     {tokens.add(new Token(yytext(),TokenType.CREATE,yyline, yycolumn));}
      "COLUMN"     {tokens.add(new Token(yytext(),TokenType.CREATE,yyline, yycolumn));}
      "TYPE"     {tokens.add(new Token(yytext(),TokenType.CREATE,yyline, yycolumn));}
      "DROP"     {tokens.add(new Token(yytext(),TokenType.CREATE,yyline, yycolumn));}
      "CONSTRAINT"     {tokens.add(new Token(yytext(),TokenType.CREATE,yyline, yycolumn));}
      "IF"     {tokens.add(new Token(yytext(),TokenType.CREATE,yyline, yycolumn));}
      "EXIST"     {tokens.add(new Token(yytext(),TokenType.CREATE,yyline, yycolumn));}
      "CASCADE"     {tokens.add(new Token(yytext(),TokenType.CREATE,yyline, yycolumn));}
      "ON"     {tokens.add(new Token(yytext(),TokenType.CREATE,yyline, yycolumn));}
      "DELETE"     {tokens.add(new Token(yytext(),TokenType.CREATE,yyline, yycolumn));}
      "SET"     {tokens.add(new Token(yytext(),TokenType.CREATE,yyline, yycolumn));}
      "UPDATE"     {tokens.add(new Token(yytext(),TokenType.CREATE,yyline, yycolumn));}
      "INSERT"     {tokens.add(new Token(yytext(),TokenType.CREATE,yyline, yycolumn));}
      "INTO"     {tokens.add(new Token(yytext(),TokenType.CREATE,yyline, yycolumn));}
      "VALUES"     {tokens.add(new Token(yytext(),TokenType.CREATE,yyline, yycolumn));}
      "SELECT"     {tokens.add(new Token(yytext(),TokenType.CREATE,yyline, yycolumn));}
      "FROM"     {tokens.add(new Token(yytext(),TokenType.CREATE,yyline, yycolumn));}
      "WHERE"     {tokens.add(new Token(yytext(),TokenType.CREATE,yyline, yycolumn));}
      "AS"     {tokens.add(new Token(yytext(),TokenType.CREATE,yyline, yycolumn));}
      "GROUP"     {tokens.add(new Token(yytext(),TokenType.CREATE,yyline, yycolumn));}
      "ORDER"     {tokens.add(new Token(yytext(),TokenType.CREATE,yyline, yycolumn));}
      "BY"     {tokens.add(new Token(yytext(),TokenType.CREATE,yyline, yycolumn));}
      "ASC"     {tokens.add(new Token(yytext(),TokenType.CREATE,yyline, yycolumn));}
      "DESC"     {tokens.add(new Token(yytext(),TokenType.CREATE,yyline, yycolumn));}
      "LIMIT"     {tokens.add(new Token(yytext(),TokenType.CREATE,yyline, yycolumn));}
      "JOIN"     {tokens.add(new Token(yytext(),TokenType.CREATE,yyline, yycolumn));}
      "INTEGER,"     {tokens.add(new Token(yytext(),TokenType.TIPO_DATO,yyline, yycolumn));}
      "BIGINT"     {tokens.add(new Token(yytext(),TokenType.TIPO_DATO,yyline, yycolumn));}
      "VARCHAR"     {tokens.add(new Token(yytext(),TokenType.TIPO_DATO,yyline, yycolumn));}
      "DECIMAL"     {tokens.add(new Token(yytext(),TokenType.TIPO_DATO,yyline, yycolumn));}
      "DATE"     {tokens.add(new Token(yytext(),TokenType.TIPO_DATO,yyline, yycolumn));}
      "TEXT"     {tokens.add(new Token(yytext(),TokenType.TIPO_DATO,yyline, yycolumn));}
      "BOOLEAN"     {tokens.add(new Token(yytext(),TokenType.TIPO_DATO,yyline, yycolumn));}
      "SERIAL"     {tokens.add(new Token(yytext(),TokenType.TIPO_DATO,yyline, yycolumn));}
      "TRUE"     {tokens.add(new Token(yytext(),TokenType.BOOLEANO,yyline, yycolumn));}
      "FALSE"     {tokens.add(new Token(yytext(),TokenType.BOOLEANO,yyline, yycolumn));}
      "SUM"     {tokens.add(new Token(yytext(),TokenType.FUNCIONES,yyline, yycolumn));}
      "AVG"     {tokens.add(new Token(yytext(),TokenType.FUNCIONES,yyline, yycolumn));}
      "COUNT"     {tokens.add(new Token(yytext(),TokenType.FUNCIONES,yyline, yycolumn));}
      "MAX"     {tokens.add(new Token(yytext(),TokenType.FUNCIONES,yyline, yycolumn));}
      "MIN"     {tokens.add(new Token(yytext(),TokenType.FUNCIONES,yyline, yycolumn));}
      "AND"     {tokens.add(new Token(yytext(),TokenType.LOGICOS,yyline, yycolumn));}
      "OR"     {tokens.add(new Token(yytext(),TokenType.LOGICOS,yyline, yycolumn));}
      "NOT"     {tokens.add(new Token(yytext(),TokenType.LOGICOS,yyline, yycolumn));}

      {FECHA}   {tokens.add(new Token(yytext(),TokenType.FECHA,yyline, yycolumn));}
     {CADENA_SIMPLE} {tokens.add(new Token(yytext(),TokenType.CADENA,yyline, yycolumn));}

	"="		{tokens.add(new Token(yytext(),TokenType.SIGNOS,yyline, yycolumn));}

    ","		{tokens.add(new Token(yytext(),TokenType.SIGNOS,yyline, yycolumn));}

    ";"     {tokens.add(new Token(yytext(),TokenType.SIGNOS,yyline, yycolumn));}

    "."     {tokens.add(new Token(yytext(),TokenType.SIGNOS,yyline, yycolumn));}
    "<="     {tokens.add(new Token(yytext(),TokenType.RELACIONALES,yyline, yycolumn));}
    ">="     {tokens.add(new Token(yytext(),TokenType.RELACIONALES,yyline, yycolumn));}
    ">"     {tokens.add(new Token(yytext(),TokenType.RELACIONALES,yyline, yycolumn));}
    "<"     {tokens.add(new Token(yytext(),TokenType.RELACIONALES,yyline, yycolumn));}
    "+"     {tokens.add(new Token(yytext(),TokenType.ARITMETICOS,yyline, yycolumn));}
    "-"     {tokens.add(new Token(yytext(),TokenType.ARITMETICOS,yyline, yycolumn));}
    "*"     {tokens.add(new Token(yytext(),TokenType.ARITMETICOS,yyline, yycolumn));}
    "/"     {tokens.add(new Token(yytext(),TokenType.ARITMETICOS,yyline, yycolumn));}

    "("     {tokens.add(new Token(yytext(),TokenType.SIGNOS,yyline, yycolumn));}
    ")"     {tokens.add(new Token(yytext(),TokenType.SIGNOS,yyline, yycolumn));}
    {entero}+ {tokens.add(new Token(yytext(),TokenType.ENTERO,yyline, yycolumn));}

      {DECIMAL} {tokens.add(new Token(yytext(),TokenType.DECIMAL,yyline, yycolumn));}

      {IDENTIFICADOR} {tokens.add(new Token(yytext(),TokenType.ID,yyline, yycolumn));}
	{WhiteSpace} 	{/* ignoramos */}

    [^] {System.out.println("Error lexico: ");
      Errores e=new Errores("Token no es reconocido: "+yytext(),TokenType.ERROR,yyline,yycolumn);
      erroresLexicos.add(e);
      }


      }