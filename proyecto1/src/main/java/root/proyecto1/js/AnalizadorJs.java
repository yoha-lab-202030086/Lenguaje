
package root.proyecto1.js;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JButton;
import root.proyecto1.vistas.Errores;
import root.proyecto1.vistas.Token;

public class AnalizadorJs {

    private final String html = "JAVASCRIPT";

    private Set<String> reservadas;
    private Set<String> booleanas;
    private Set<String> otros;
    private String lineaCode;
    private int position;
    private ArrayList<Errores> errores;

    public AnalizadorJs() {

        this.reservadas = new HashSet<>();
        this.booleanas = new HashSet<>();
        this.errores = new ArrayList<>();
        reservadasInit();
    }

    private void reservadasInit() {

        //PALABRAS RESERVADAS
        reservadas.add("function");
        reservadas.add("const");
        reservadas.add("let");
        reservadas.add("document");
        reservadas.add("event");
        reservadas.add("alert");
        reservadas.add("for");
        reservadas.add("while");
        reservadas.add("if");
        reservadas.add("else");
        reservadas.add("return");
        reservadas.add("console");
        reservadas.add("log");
        reservadas.add("querySelectorAll");
        reservadas.add("null");
        reservadas.add("break");
        reservadas.add("case");
        reservadas.add("catch");
        reservadas.add("class");
        reservadas.add("continue");
        reservadas.add("debugger");
        reservadas.add("default");
        reservadas.add("delete");
        reservadas.add("do");
        reservadas.add("export");
        reservadas.add("extends");
        reservadas.add("finally");
        reservadas.add("forEach");
        reservadas.add("import");
        reservadas.add("in");
        reservadas.add("instanceof");
        reservadas.add("new");
        reservadas.add("super");
        reservadas.add("switch");
        reservadas.add("this");
        reservadas.add("throw");
        reservadas.add("try");
        reservadas.add("typeof");
        reservadas.add("var");
        reservadas.add("void");
        reservadas.add("with");
        reservadas.add("yield");
        reservadas.add("true");
        reservadas.add("false");
        reservadas.add("undefined");
        reservadas.add("Infinity");
        reservadas.add("NaN");
        reservadas.add("async");
        reservadas.add("await");
        reservadas.add("enum");
        reservadas.add("implements");
        reservadas.add("interface");
        reservadas.add("package");
        reservadas.add("private");
        reservadas.add("protected");
        reservadas.add("public");

        //BOLEANAS
        this.booleanas.add("true");
        this.booleanas.add("false");
    }

    public ArrayList<Token> analizar(String lineaTexto, int lineaActual) {

        lineaCode = lineaTexto;
        position = 1;

        ArrayList<Token> tokensLinea = new ArrayList<>();

        while (position < lineaCode.length()) {
            skipWhitespace();
            System.out.println("ANALISIS JAVASCRIPT caracter actualL " + caracterActual());
            char a = caracterActual();

            if (isLetter(caracterActual())) {
                tokensLinea.add(lexIdentifier(lineaActual));

            } else if (isDigit(caracterActual())) {
                Token t=lexnumber(lineaActual);
                if(t!=null){
                    
                tokensLinea.add(t);
                }
            } else if (caracterActual() == '\'' || caracterActual() == '"' || caracterActual() == '`') {
                tokensLinea.add(lexString(lineaActual));
            } else if (caracterActual() == '+') {
                tokensLinea.add(sumas(lineaActual));

            } else if (caracterActual() == '-') {
                tokensLinea.add(restas(lineaActual));
            } else if (caracterActual() == '*') {
                tokensLinea.add(new Token(caracterActual() + "", caracterActual() + "", html, "MULTIPLICACION", lineaActual, position));

            } else if (caracterActual() == '=') {
                tokensLinea.add(iguals(lineaActual));
            } else if (caracterActual() == '<') {
                tokensLinea.add(iguals(lineaActual));
            } else if (caracterActual() == '>') {
                tokensLinea.add(iguals(lineaActual));
            } else if (caracterActual() == '!') {
                tokensLinea.add(iguals(lineaActual));
            } else if (caracterActual() == '|') {
                tokensLinea.add(orss(lineaActual));
            } else if (caracterActual() == '&') {
                tokensLinea.add(andss(lineaActual));
            } else if (caracterActual() == '(') {
                tokensLinea.add(new Token(caracterActual() + "", caracterActual() + "", html, "PARENTESIS", lineaActual, position));
                position++;
            } else if (caracterActual() == ')') {
                tokensLinea.add(new Token(caracterActual() + "", caracterActual() + "", html, "PARENTESIS", lineaActual, position));
                position++;
            } else if (caracterActual() == '[') {
                tokensLinea.add(new Token(caracterActual() + "", caracterActual() + "", html, "CORCHETE", lineaActual, position));
                position++;
            } else if (caracterActual() == ']') {
                tokensLinea.add(new Token(caracterActual() + "", caracterActual() + "", html, "CORCHETE", lineaActual, position));
                position++;
            } else if (caracterActual() == '{') {
                tokensLinea.add(new Token(caracterActual() + "", caracterActual() + "", html, "LLAVE", lineaActual, position));
                position++;
            } else if (caracterActual() == '}') {
                tokensLinea.add(new Token(caracterActual() + "", caracterActual() + "", html, "LLAVE", lineaActual, position));
                position++;
            } else if (caracterActual() == '.') {
                tokensLinea.add(new Token(caracterActual() + "", caracterActual() + "", html, "PUNTO", lineaActual, position));
                position++;
            } else if (caracterActual() == ';') {
                tokensLinea.add(new Token(caracterActual() + "", caracterActual() + "", html, "P_COMA", lineaActual, position));
                position++;
            } else if (caracterActual() == ',') {
                tokensLinea.add(new Token(caracterActual() + "", caracterActual() + "", html, "COMA", lineaActual, position));
                position++;
            } else if (caracterActual() == ':') {
                tokensLinea.add(new Token(caracterActual() + "", caracterActual() + "", html, "D_PUNTOS", lineaActual, position));
                position++;
            } else if (caracterActual() == '/') {
                tokensLinea.add(isComenter(position));
            }
        }

        return tokensLinea;

    }

    public Token orss(int numeroLinea) {
        StringBuilder resultado = new StringBuilder();
        resultado.append(caracterActual());
        position++;
        while (position < lineaCode.length() && (caracterActual() == '+')) {
            resultado.append(caracterActual());
            position++;
        }

        String value = resultado.toString();

        if (value.equalsIgnoreCase("||")) {

            return new Token(value, value, html, "OR", numeroLinea, position);

        } else {
            return new Token(value, value, html, "OR", numeroLinea, position);

        }
    }

    private Token isComenter(int numLinea) {
        StringBuilder result = new StringBuilder();

        result.append(caracterActual());
        position++;

        while (position < lineaCode.length() && caracterActual() != 10) {
            result.append(caracterActual());
            position++;
        }
        result.append(caracterActual());
        position++;

        if (result.toString().length() == 1 && result.toString().equals("/")) {
            return new Token(result.toString(), "/", html, "DIVISION", numLinea, position);

        } else {
            return new Token(result.toString(), "//.*", html, "COMENTARIO", numLinea, position);

        }
    }

    public Token andss(int numeroLinea) {
        StringBuilder resultado = new StringBuilder();
        resultado.append(caracterActual());
        position++;
        while (position < lineaCode.length() && (caracterActual() == '+')) {
            resultado.append(caracterActual());
            position++;
        }

        String value = resultado.toString();

        if (value.equalsIgnoreCase("&&")) {

            return new Token(value, value, html, "AND", numeroLinea, position);

        } else {
            return new Token(value, value, html, "AND", numeroLinea, position);

        }
    }

    public Token iguals(int numeroLinea) {
        StringBuilder resultado = new StringBuilder();
        resultado.append(caracterActual());
        position++;
        while (position < lineaCode.length() && (caracterActual() == '=')) {
            resultado.append(caracterActual());
            position++;
        }

        String value = resultado.toString();

        if (value.equalsIgnoreCase("==")) {

            return new Token(value, value, html, "IGUAL", numeroLinea, position);

        } else if (value.equalsIgnoreCase(">=")) {
            return new Token(value, value, html, "MAYOR_IGUAL", numeroLinea, position);

        } else if (value.equalsIgnoreCase("<=")) {
            return new Token(value, value, html, "MENOR_IGUAL", numeroLinea, position);

        } else if (value.equalsIgnoreCase("!=")) {
            return new Token(value, value, html, "DIFERENTE", numeroLinea, position);

        } else if (value.equalsIgnoreCase(">")) {
            return new Token(value, value, html, "MAYOR", numeroLinea, position);

        } else if (value.equalsIgnoreCase("!")) {
            return new Token(value, value, html, "NOT", numeroLinea, position);

        } else if (value.equalsIgnoreCase("=")) {
            return new Token(value, value, html, "ASIGNACION", numeroLinea, position);

        } else {
            return new Token(value, value, html, "MENOR", numeroLinea, position);

        }
    }

    public Token sumas(int numeroLinea) {
        StringBuilder resultado = new StringBuilder();
        resultado.append(caracterActual());
        position++;
        while (position < lineaCode.length() && (caracterActual() == '+')) {
            resultado.append(caracterActual());
            position++;
        }

        String value = resultado.toString();

        if (value.equalsIgnoreCase("++")) {

            return new Token(value, value, html, "INCREMENTO", numeroLinea, position);

        } else {
            return new Token(value, value, html, "SUMA", numeroLinea, position);

        }
    }

    public Token restas(int numeroLinea) {
        StringBuilder resultado = new StringBuilder();
        resultado.append(caracterActual());
        position++;
        while (position < lineaCode.length() && (caracterActual() == '-')) {
            resultado.append(caracterActual());
            position++;
        }

        String value = resultado.toString();

        if (value.equalsIgnoreCase("--")) {

            return new Token(value, value, html, "DECREMENTO", numeroLinea, position);

        } else {
            return new Token(value, value, html, "RESTA", numeroLinea, position);

        }
    }

    private Token lexString(int numeroLinea) {
        StringBuilder result = new StringBuilder();

        result.append(caracterActual());
        position++;

        while (position < lineaCode.length() && (caracterActual() != '"' && caracterActual() != '\'' && caracterActual() != '`')) {
            result.append(caracterActual());
            position++;
        }

        result.append(caracterActual());

        position++;

        return new Token(result.toString(), "CADENA", html, "CADENA", numeroLinea, position);

    }

    public Token lexnumber(int numeroLinea) {
        StringBuilder resultado = new StringBuilder();
        resultado.append(caracterActual());
        position++;
        while (position < lineaCode.length() && (isDigit(caracterActual()))) {

            resultado.append(caracterActual());
            position++;
        }
        if (position < lineaCode.length() && caracterActual() == '.') {

            resultado.append(caracterActual());
            position++;

            if (position < lineaCode.length() && isDigit(caracterActual())) {
                while (position < lineaCode.length() && isDigit(caracterActual())) {
                    resultado.append(caracterActual());
                    position++;

                }

                return new Token(resultado.toString(), "[0-9]+ . [0-9]+", html, "DECIMAL", numeroLinea, position);

            } else {
                errores.add(new Errores(resultado.toString(), "NO_SE", "NO_SE", numeroLinea, position));
                return null;
            }
        } else {
            return new Token(resultado.toString(), "[0-9]+", html, "ERROR", numeroLinea, position);

        }

    }

    public Token lexIdentifier(int numeroLinea) {
        StringBuilder resultado = new StringBuilder();
        resultado.append(caracterActual());
        position++;
        while (position < lineaCode.length() && (isLetter(caracterActual()) || isDigit(caracterActual()) || caracterActual() == '_')) {
            resultado.append(caracterActual());
            position++;
        }

        String value = resultado.toString();

        if (reservadas.contains(value)) {

            return new Token(value, value, html, "RESERVADAS", numeroLinea, position);

        } else if (booleanas.contains(value)) {
            return new Token(value, value, html, "BOOLEAN", numeroLinea, position);

        } else {
            return new Token(value, "[a-zA-Z]([a-zA-Z] | [0-9] | [ _ ])*", html, "ID", numeroLinea, position);

        }
    }

    private char caracterActual() {
        if (position >= 0 && position < lineaCode.length()) {
            return lineaCode.charAt(position);
            // Procesar el carácter
        } else {
            return ' ';
        }
    }

    private void skipWhitespace() {
        while (position < lineaCode.length() && Character.isWhitespace(caracterActual())) {
            position++;
        }
    }

    private boolean isLetter(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    public ArrayList<Errores> getErrores() {
        return errores;
    }

}
