
package root.proyecto1.vistas;


public class Errores {
    
    private String lenguajeEncontrado;
    private String lenguajeSugerido;
    private String lex;
    private int linea;
    private int columna;

    public Errores(String lexema,String lenguajeEncontrado, String lenguajeSugerido, int linea, int columna) {
        this.lenguajeEncontrado = lenguajeEncontrado;
        this.lenguajeSugerido = lenguajeSugerido;
        this.linea = linea;
        this.columna = columna;
        this.lex=lexema;
    }

    public String getLenguajeEncontrado() {
        return lenguajeEncontrado;
    }

    public void setLenguajeEncontrado(String lenguajeEncontrado) {
        this.lenguajeEncontrado = lenguajeEncontrado;
    }

    public String getLenguajeSugerido() {
        return lenguajeSugerido;
    }

    public void setLenguajeSugerido(String lenguajeSugerido) {
        this.lenguajeSugerido = lenguajeSugerido;
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

    public String getLex() {
        return lex;
    }
    

    public void setColumna(int columna) {
        this.columna = columna;
    }

    @Override
    public String toString() {
        return "Errores{" + "lenguajeEncontrado=" + lenguajeEncontrado + ", lenguajeSugerido=" + lenguajeSugerido + ", lex=" + lex + ", linea=" + linea + ", columna=" + columna + '}';
    }


    
    
    
}
