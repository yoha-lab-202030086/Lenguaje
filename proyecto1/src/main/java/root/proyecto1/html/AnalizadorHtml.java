package root.proyecto1.html;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.lang.model.SourceVersion;
import javax.swing.JButton;
import root.proyecto1.vistas.Errores;
import root.proyecto1.vistas.Estado;
import root.proyecto1.vistas.Token;

public class AnalizadorHtml {

    private final String html = "HTML";
    private String lineaCode;
    private int position;
    public static Map<String, String> traduccionesEtiquetas;
    private Map<String, String> titulos;
    private Set<String> palabrasReservadas;
    private ArrayList<Token> tokensHtml;
    private ArrayList<Errores> errores;

    public ArrayList<Errores> getErrores() {
        return errores;
    }

    public AnalizadorHtml() {
        this.traduccionesEtiquetas = new HashMap<>();
        this.palabrasReservadas = new HashSet<>();
        this.errores = new ArrayList<>();
        this.titulos = new HashMap<>();
        inicializarTraducciones();
        inicializarPalabrasReservadas();
    }

    private void inicializarTraducciones() {
        // Etiquetas de estructura
        traduccionesEtiquetas.put("principal", "main");
        traduccionesEtiquetas.put("encabezado", "header");
        traduccionesEtiquetas.put("navegacion", "nav");
        traduccionesEtiquetas.put("apartado", "aside");
        traduccionesEtiquetas.put("piepagina", "footer");

        // Etiquetas de listas
        traduccionesEtiquetas.put("listaordenada", "ul");
        traduccionesEtiquetas.put("listadesordenada", "ol");
        traduccionesEtiquetas.put("itemlista", "li");

        // Etiquetas de contenido
        traduccionesEtiquetas.put("anclaje", "a");
        traduccionesEtiquetas.put("contenedor", "div");
        traduccionesEtiquetas.put("seccion", "section");
        traduccionesEtiquetas.put("articulo", "article");
        traduccionesEtiquetas.put("parrafo", "p");
        traduccionesEtiquetas.put("span", "span");

        // Etiquetas de formulario
        traduccionesEtiquetas.put("formulario", "form");
        traduccionesEtiquetas.put("entrada", "input");
        traduccionesEtiquetas.put("etiqueta", "label");
        traduccionesEtiquetas.put("area", "textarea");
        traduccionesEtiquetas.put("boton", "button");
        titulos.put("titulo1", "h1");
        titulos.put("titulo2", "h2");
        titulos.put("titulo3", "h3");
        titulos.put("titulo4", "h4");
        titulos.put("titulo5", "h5");
        titulos.put("titulo6", "h6");
    }

    private void inicializarPalabrasReservadas() {
        palabrasReservadas.addAll(Arrays.asList(
                "class", "href", "onClick", "id", "style", "type",
                "placeholder", "required", "name"
        ));
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

    public ArrayList<Token> analizar(String linea, int numeroLinea) {
        lineaCode = linea;
        position = 1;
        ArrayList<Token> tokensLinea = new ArrayList<>();

        while (position < linea.length()) {
            skipWhitespace();
            System.out.println("ANALISIS HTML");

            if (caracterActual() == '<') {
                tokensLinea.add(new Token("<", "<", html, "OP_TAG", numeroLinea, position));
                position++;
                tokensLinea.addAll(tagInterno(numeroLinea));
            } else if (isLetter(caracterActual()) || isDigit(caracterActual())) {
                Token e=textoInterno(numeroLinea);
                tokensLinea.add(e);
            }else if(caracterActual()=='/'){
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

    public Token textoInterno(int numLinea) {
        StringBuilder resultado = new StringBuilder();
        resultado.append(caracterActual());
        position++;
        while (caracterActual()!='<') {
                        skipWhitespace();

            System.out.println("INTERNO: "+caracterActual());
            char c=caracterActual();
            if(caracterActual()!=10){
                            resultado.append(caracterActual());

            }
            position++;

        }
        String value = resultado.toString();
        return new Token(value, value, html, "TEXTO", numLinea, position);

    }

    public ArrayList<Token> tagInterno(int numeroLinea) {
        ArrayList<Token> token = new ArrayList<>();
        while (caracterActual() != '>') {
           
            skipWhitespace();
            char e=caracterActual();
            if (isLetter(caracterActual())) {
                Token t = (Token) tag(numeroLinea);
                if (t != null) {

                    token.add(t);
                }

            } else if (caracterActual() == '"') {
                token.add(lexString(numeroLinea));
            } else if (caracterActual() == '=') {

                token.add(new Token("=", "=", html, "IGUAL", numeroLinea, position));
                position++;
            } else if (caracterActual() == '/') {

                token.add(new Token("/", "/", html, "CIERRE", numeroLinea, position));
                position++;
            } else {
            errores.add(new Errores(caracterActual()+"","NO_SE","NO_SE",numeroLinea,position));
            return null;
        }

        }

        if (caracterActual() == '>') {
            token.add(new Token(">", ">", html, "OP_TAG", numeroLinea, position));
            position++;
        }

        return token;

    }

    private Token lexString(int numeroLinea) {
        StringBuilder result = new StringBuilder();

        result.append(caracterActual());
        position++;

        while (position < lineaCode.length() && caracterActual() != '"') {
            result.append(caracterActual());
            position++;
        }

        result.append(caracterActual());

        position++;

        return new Token(result.toString(), "\"(.*?)\"", html, "OP_TAG", numeroLinea, position);
    }

    public Token tag(int numLinea) {
        StringBuilder resultado = new StringBuilder();
        resultado.append(caracterActual());
        position++;
        while (position < lineaCode.length() & (isLetter(caracterActual()) || isDigit(caracterActual()))) {
            resultado.append(caracterActual());
            position++;

        }

        String value = resultado.toString();
        if (traduccionesEtiquetas.containsKey(value)) {

            return new Token(value, value, html, "TAG", numLinea, position);

        } else if (titulos.containsKey(value)) {
            return new Token(value, value, html, "TAG", numLinea, position);

        } else if (palabrasReservadas.contains(value)) {

            return new Token(value, value, html, "K_W", numLinea, position);

        } else {
            errores.add(new Errores(value,"NO_SE","NO_SE",numLinea,position));
            return null;
        }
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

}
