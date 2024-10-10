package root.proyecto1.vistas;

import java.awt.List;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import root.proyecto1.css.AnalizadorCss;
import root.proyecto1.html.AnalizadorHtml;
import root.proyecto1.js.AnalizadorJs;

public class MultiplesAnalizadores {

    ArrayList<Token> tokensHtml;
    ArrayList<Token> tokensCss;
    ArrayList<Token> tokensJs;
public static ArrayList<Errores> reporteErrores;

    public static ArrayList<Token> reporteTokens;
    private Estado estadoActual;
    private AnalizadorHtml analizadorHTML;
    private AnalizadorCss analizadorCSS;
    private AnalizadorJs analizadorJS;
    private int columna;
    private int linea;

    public MultiplesAnalizadores() {
        //  this.estadoActual = Estado.HTML; // Estado inicial por defecto
        this.tokensHtml = new ArrayList<>();
        this.tokensCss = new ArrayList<>();
        this.tokensJs = new ArrayList<>();
        this.reporteTokens = new ArrayList<>();
        this.reporteErrores=new ArrayList<>();

        this.analizadorHTML = new AnalizadorHtml();
        this.analizadorCSS = new AnalizadorCss();
        this.analizadorJS = new AnalizadorJs();
        this.linea = 1;
        this.columna = 1;

    }

    public void analizarCodigo(String codigoFuente) {
        String[] lineas = codigoFuente.split("\n");
        for (String lineaTexto : lineas) {
            this.columna = 1;
            if (lineaTexto.trim().startsWith(">>")) {
                reporteTokens.add(new Token(lineaTexto, lineaTexto, "", "ESTADO", linea, lineaTexto.length()));
                cambiarEstado(lineaTexto.trim());
            } else {
                if (estadoActual == null) {
                    System.out.println("NO HA INICIALIZADO ESTADO");
                    continue;
                }
                analizarLinea(lineaTexto, linea++);
            }
        }
        
        reporteErrores.addAll(analizadorHTML.getErrores());
        reporteErrores.addAll(analizadorCSS.getErrores());
        reporteErrores.addAll(analizadorJS.getErrores());

        generarHtml();

    }

    private void cambiarEstado(String tokenEstado) {
        linea++;
        switch (tokenEstado.toLowerCase()) {
            case ">>[html]":
                estadoActual = Estado.HTML;
                break;
            case ">>[css]":
                estadoActual = Estado.CSS;
                break;
            case ">>[js]":
                estadoActual = Estado.JS;
                break;
            default:
                // Manejar error de token de estado desconocido
                break;
        }

    }

    private void analizarLinea(String linea, int lineaActual) {

        switch (estadoActual) {
            case HTML:

                tokensHtml.addAll(analizadorHTML.analizar(linea, lineaActual));
                reporteTokens.addAll(analizadorHTML.analizar(linea, lineaActual));

                break;
            case CSS:
                tokensCss.addAll(analizadorCSS.analizar(linea, lineaActual));
                reporteTokens.addAll(analizadorCSS.analizar(linea, lineaActual));
                break;
            case JS:
                tokensJs.addAll(analizadorJS.analizar(linea, lineaActual));
                reporteTokens.addAll(analizadorJS.analizar(linea, lineaActual));
                break;

            default:
                System.out.println("Analisis no incorporado...");
        }
    }

    private void generarHtml() {
        StringBuilder html = new StringBuilder();
        StringBuilder css = new StringBuilder();
        StringBuilder js = new StringBuilder();

        for (Token token : tokensHtml) {
            // Reemplazar 'contenedor' por 'div'
            if (analizadorHTML.traduccionesEtiquetas.containsKey(token.getToken())) {
                html.append(analizadorHTML.traduccionesEtiquetas.get(token.getToken()));
            } else {
                if (token.getToken().equals(">")) {
                    html.append(token.getToken());
                    html.append("\n");
                } else {
                    html.append(token.getToken());
                }

            }
            html.append(" "); // Agregar un espacio entre tokens
        }

        for (Token token : tokensCss) {
            if (token.getToken().equals(";") || token.getToken().equals("{")) {
                css.append(token.getToken());
                css.append("\n");

            } else {
                css.append(token.getToken());
            }
            css.append(" "); // Agregar un espacio entre tokens
        }

        for (Token token : tokensJs) {
            if (token.getToken().equals(";") || token.getToken().equals("{")) {
                js.append(token.getToken());
                js.append("\n");

            } else {
                js.append(token.getToken());
            }

            js.append(" "); // Agregar un espacio entre tokens
        }

        String htmlStructure = "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "    <title>Mi Documento HTML</title>\n"
                + "    <style>\n" // Agregar los tokens CSS dentro de <style>
                + css.toString() + "\n"
                + "    </style>\n"
                + "</head>\n"
                + "<body>\n"
                + html.toString() + "\n" // Aquí se escriben los tokens generados para el body
                + "    <script>\n" // Agregar los tokens JS dentro de <script>
                + js.toString() + "\n"
                + "    </script>\n"
                + "</body>\n"
                + "</html>";

        try (FileWriter fileWriter = new FileWriter("pagina.html")) {
            fileWriter.write(htmlStructure);
            System.out.println("Archivo HTML creado con éxito.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("html: \n" + html.toString());
    }
}
