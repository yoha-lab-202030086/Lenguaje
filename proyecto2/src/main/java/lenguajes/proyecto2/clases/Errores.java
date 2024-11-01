package lenguajes.proyecto2.clases;

public class Errores {
    private String lexema;
    private TokenType tipo;
    private int linea;
    private int col;

    public Errores(String lexema, TokenType tipo, int linea, int col) {
        this.lexema = lexema;
        this.tipo = tipo;
        this.linea = linea;
        this.col = col;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public TokenType getTipo() {
        return tipo;
    }

    public void setTipo(TokenType tipo) {
        this.tipo = tipo;
    }

    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
