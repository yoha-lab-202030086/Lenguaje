package root.proyecto1.css;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JButton;
import root.proyecto1.vistas.Errores;
import root.proyecto1.vistas.Token;

public class AnalizadorCss {

    private final String html = "CSS";
    private String lineaCode;
    private int position;
    private ArrayList<Errores> errores;

    private Set<String> etiquetaTipo;
    private Set<String> reglas;
    private Set<String> otros;

    public AnalizadorCss() {
        this.otros = new HashSet<>();
        this.errores = new ArrayList<>();
        this.etiquetaTipo = new HashSet<>();
        this.reglas = new HashSet<>();

        inicializarCss();
    }

    public void inicializarCss() {
        etiquetaTipo.add("body");
        etiquetaTipo.add("header");
        etiquetaTipo.add("main");
        etiquetaTipo.add("nav");
        etiquetaTipo.add("aside");
        etiquetaTipo.add("div");
        etiquetaTipo.add("ul");
        etiquetaTipo.add("ol");
        etiquetaTipo.add("li");
        etiquetaTipo.add("a");
        etiquetaTipo.add("h1");
        etiquetaTipo.add("h2");
        etiquetaTipo.add("h3");
        etiquetaTipo.add("h4");
        etiquetaTipo.add("h5");
        etiquetaTipo.add("h6");
        etiquetaTipo.add("p");
        etiquetaTipo.add("span");
        etiquetaTipo.add("label");
        etiquetaTipo.add("textarea");
        etiquetaTipo.add("button");
        etiquetaTipo.add("section");
        etiquetaTipo.add("article");
        etiquetaTipo.add("footer");

        // Inicializar reglas CSS
        reglas.add("color");
        reglas.add("background-color");
        reglas.add("background");
        reglas.add("font-size");
        reglas.add("font-weight");
        reglas.add("font-family");
        reglas.add("font-align");
        reglas.add("width");
        reglas.add("height");
        reglas.add("min-width");
        reglas.add("min-height");
        reglas.add("max-width");
        reglas.add("max-height");
        reglas.add("display");
        reglas.add("inline");
        reglas.add("block");
        reglas.add("inline-block");
        reglas.add("flex");
        reglas.add("grid");
        reglas.add("none");
        reglas.add("margin");
        reglas.add("border");
        reglas.add("padding");
        reglas.add("content");
        reglas.add("border-color");
        reglas.add("border-style");
        reglas.add("border-width");
        reglas.add("border-top");
        reglas.add("border-bottom");
        reglas.add("border-left");
        reglas.add("border-right");
        reglas.add("box-sizing");
        reglas.add("border-box");
        reglas.add("position");
        reglas.add("static");
        reglas.add("relative");
        reglas.add("absolute");
        reglas.add("sticky");
        reglas.add("fixed");
        reglas.add("top");
        reglas.add("bottom");
        reglas.add("left");
        reglas.add("right");
        reglas.add("z-index");
        reglas.add("justify-content");
        reglas.add("align-items");
        reglas.add("border-radius");
        reglas.add("auto");
        reglas.add("float");
        reglas.add("list-style");
        reglas.add("text-align");
        reglas.add("box-shadow");
        reglas.add("sans-serif");
        reglas.add("center");

        //OTROS
        otros.add("px");
        otros.add("%");
        otros.add("rem");
        otros.add("em");
        otros.add("vw");
        otros.add("vh");
        otros.add(":hover");
        otros.add(":active");
        otros.add(":not()");
        otros.add(":nth-child()");
        otros.add("odd");
        otros.add("even");
        otros.add("::before");
        otros.add("::after");
        otros.add(":");
        otros.add(";");
        otros.add(",");

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

    public ArrayList<Token> analizar(String lineaTexto, int numeroLinea) {
        lineaCode = lineaTexto;
        position = 0;

        ArrayList<Token> tokensLinea = new ArrayList<>();

        while (position < lineaCode.length()) {
            skipWhitespace();
            System.out.println("ANALISIS CSS caracter actualL " + caracterActual());
            char a = caracterActual();
            if (caracterActual() == '*') {
                tokensLinea.add(new Token("*", "*", html, "UNIVERSAL", numeroLinea, position));
                position++;
            } else if (isLetter(caracterActual())) {
                Token e = eTipo(numeroLinea);
                if(e!=null){
                tokensLinea.add(e);
                    
                }

            } else if (caracterActual() == '{') {
                tokensLinea.add(new Token("{", "{", html, "OTROS", numeroLinea, position));
                position++;

            } else if (caracterActual() == '}') {
                tokensLinea.add(new Token("}", "}", html, "OTROS", numeroLinea, position));
                position++;

            } else if (caracterActual() == '(') {
                tokensLinea.add(new Token("(", "(", html, "OTROS", numeroLinea, position));
                position++;

            } else if (caracterActual() == ')') {
                tokensLinea.add(new Token(")", ")", html, "OTROS", numeroLinea, position));
                position++;

            } else if (caracterActual() == '.') {
                Token t = selectorClase(numeroLinea);
                if (t != null) {

                    tokensLinea.add(t);
                }
            } else if (caracterActual() == '#') {
                Token t = selectorClase(numeroLinea);
                if (t != null) {

                    tokensLinea.add(t);
                }
            } else if (caracterActual() == '>') {
                tokensLinea.add(new Token(">", ">", html, "COMBINADOR", numeroLinea, position));
                position++;

            } else if (caracterActual() == '+') {
                tokensLinea.add(new Token("+", "+", html, "COMBINADOR", numeroLinea, position));
                position++;

            } else if (caracterActual() == '~') {
                tokensLinea.add(new Token("~", "~", html, "COMBINADOR", numeroLinea, position));
                position++;
            } else if (caracterActual() == ':') {
                Token e = otros(numeroLinea);
                if(e!=null){
                tokensLinea.add(e);
                    
                }
            } else if (caracterActual() == '%') {
                tokensLinea.add(new Token("%", "%", html, "OTROS", numeroLinea, position));
                position++;
            } else if (caracterActual() == ';') {
                tokensLinea.add(new Token(";", ";", html, "OTROS", numeroLinea, position));
                position++;
            } else if (caracterActual() == ',') {
                tokensLinea.add(new Token(",", ",", html, "OTROS", numeroLinea, position));
                position++;
            } else if (caracterActual() == '\'') {
                tokensLinea.add(lexString(numeroLinea));
            } else if (isDigit(caracterActual())) {
                Token t = lexnumber(numeroLinea);
                if (t != null) {
                    tokensLinea.add(t);
                }
            } else if (caracterActual() == '/') {
                tokensLinea.add(isComenter(numeroLinea));
            }
        }

        return tokensLinea;
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
        return new Token(result.toString(), "//.*", html, "COMENTARIO", numLinea, position);
    }

    public Token lexnumber(int numeroLinea) {
        StringBuilder resultado = new StringBuilder();
        resultado.append(caracterActual());
        position++;
        while (position < lineaCode.length() && (isDigit(caracterActual()))) {

            resultado.append(caracterActual());
            position++;
        }

        String salida = resultado.toString();

        if (esCadenaDeEnteros(salida)) {
            return new Token(salida, "[0-9]+", html, "ENTERO", numeroLinea, position);

        } else {
            errores.add(new Errores(resultado.toString(), "NO_SE", "NO_SE", numeroLinea, position));
            return null;
        }

    }

    public boolean esCadenaDeEnteros(String str) {
        try {
            Integer.parseInt(str); // Intenta convertir el string a un entero
            return true; // Si no lanza excepción, es un entero
        } catch (NumberFormatException e) {
            return false; // Si lanza excepción, no es una cadena de enteros
        }
    }

    private Token lexString(int numeroLinea) {
        StringBuilder result = new StringBuilder();

        result.append(caracterActual());
        position++;

        while (position < lineaCode.length() && caracterActual() != '\'') {
            result.append(caracterActual());
            position++;
        }

        result.append(caracterActual());

        position++;

        return new Token(result.toString(), "\"(.*?)\"", html, "CADENA", numeroLinea, position);
    }

    public Token selectorClase(int numLinea) {
        StringBuilder resultado = new StringBuilder();
        resultado.append(caracterActual());
        position++;

        if (!(caracterActual() >= 97) & !(caracterActual() <= 122)) {
            return null;
        }

        resultado.append(caracterActual());
        position++;
        while (position < lineaCode.length() & (isLetter(caracterActual()) || isDigit(caracterActual()))) {
            resultado.append(caracterActual());
            position++;

        }

        if (caracterActual() == '-') {
            resultado.append(caracterActual());
            position++;
        }
        if (!(caracterActual() >= 97) & !(caracterActual() <= 122)) {
            return null;
        }

        resultado.append(caracterActual());
        position++;

        while (position < lineaCode.length() & (isLetter(caracterActual()) || isDigit(caracterActual()))) {
            resultado.append(caracterActual());
            position++;

        }

        String value = resultado.toString();
        if (value.contains("-") && value.contains(".")) {

            return new Token(value, value, html, "D_CLASE", numLinea, position);

        } else if (value.contains("-") && value.contains("#")) {
            return new Token(value, value, html, "D_ID", numLinea, position);

        } else {
            errores.add(new Errores(value, "NO_SE", "NO_SE", numLinea, position));
            return null;
        }

    }

    public ArrayList<Errores> getErrores() {
        return errores;
    }

    public Token otros(int numLinea) {
        StringBuilder resultado = new StringBuilder();
        resultado.append(caracterActual());
        position++;
        char a = caracterActual();
        while (position < lineaCode.length() & (caracterActual() != 10 & caracterActual() != ' ')) {

            resultado.append(caracterActual());
            position++;

        }

        String value = resultado.toString();
        if (otros.contains(value)) {
            return new Token(value, value, html, "OTROS", numLinea, position);

        } else {
            errores.add(new Errores(value, "NO_SE", "NO_SE", numLinea, position));
            return null;
        }

    }

    public Token eTipo(int numLinea) {
        StringBuilder resultado = new StringBuilder();
        resultado.append(caracterActual());
        position++;
        while (position < lineaCode.length() & (isLetter(caracterActual()) || caracterActual() == '-')) {
            resultado.append(caracterActual());
            position++;

        }

        String value = resultado.toString();
        if (etiquetaTipo.contains(value)) {
            return new Token(value, value, html, "E_TIPO", numLinea, position);

        } else if (reglas.contains(value)) {

            return new Token(value, value, html, "REGLA", numLinea, position);

        } else if (otros.contains(value)) {

            return new Token(value, value, html, "OTROS", numLinea, position);

        } else {
            errores.add(new Errores(value, "NO_SE", "NO_SE", numLinea, position));
            return null;
        }

    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

}
