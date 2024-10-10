
package root.proyecto1.vistas;


public class Token {
    private String token;
    private String regex;
    private String lenguaje;
    private String tipo;
    private int linea;
    private int columna;

    public Token(String token, String regex, String lenguaje, String tipo, int linea, int columna) {
        this.token = token;
        this.regex = regex;
        this.lenguaje = lenguaje;
        this.tipo = tipo;
        this.linea = linea;
        this.columna = columna;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public String getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    @Override
    public String toString() {
        return "Token{" + "token=" + token + ", regex=" + regex + ", lenguaje=" + lenguaje + ", tipo=" + tipo + ", linea=" + linea + ", columna=" + columna + '}';
    }
    
    
}
