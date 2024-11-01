
package lenguajes.proyecto2.vistas;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.StringReader;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import lenguajes.proyecto2.analizador.Lexer;
import static lenguajes.proyecto2.analizador.Lexer.tokens;
import lenguajes.proyecto2.clases.Token;
import lenguajes.proyecto2.clases.TokenType;
import static lenguajes.proyecto2.clases.TokenType.COMENTARIO;

public class Principal extends javax.swing.JFrame {

    /**
     * Creates new form Principal
     */
     private StyledDocument doc;
    public Principal() {
        initComponents();
        doc = codigo.getStyledDocument();
        setupStyles();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        codigo = new javax.swing.JTextPane();
        jButton1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setViewportView(codigo);

        jButton1.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        jButton1.setText("Analizar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jMenu1.setText("Archivo");

        jMenuItem1.setText("Cargar");
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Reportes");

        jMenuItem2.setText("Tokens");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuItem3.setText("Sintactico");
        jMenu2.add(jMenuItem3);

        jMenuItem4.setText("Errores");
        jMenu2.add(jMenuItem4);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(283, 283, 283)
                .addComponent(jButton1)
                .addContainerGap(472, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed

        RTokenks token= new RTokenks();
        token.setVisible(true);
        token.setLocationRelativeTo(null);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       analyzeAndHighlight();
    }//GEN-LAST:event_jButton1ActionPerformed

     private void setupStyles() {
        StyleContext context = StyleContext.getDefaultStyleContext();

        Style createStyle = codigo.addStyle("CreateStyle", null);
        StyleConstants.setForeground(createStyle, Color.ORANGE);

        Style tipoDatoStyle = codigo.addStyle("TipoDatoStyle", null);
        StyleConstants.setForeground(tipoDatoStyle, new Color(128, 0, 128)); // Morado

        Style numeroStyle = codigo.addStyle("NumeroStyle", null);
        StyleConstants.setForeground(numeroStyle, Color.BLUE);

        Style fechaStyle = codigo.addStyle("FechaStyle", null);
        StyleConstants.setForeground(fechaStyle, Color.YELLOW);

        Style cadenaStyle = codigo.addStyle("CadenaStyle", null);
        StyleConstants.setForeground(cadenaStyle, Color.GREEN);

        Style idStyle = codigo.addStyle("IdStyle", null);
        StyleConstants.setForeground(idStyle, Color.MAGENTA);

        Style signosStyle = codigo.addStyle("SignosStyle", null);
        StyleConstants.setForeground(signosStyle, Color.BLACK);

        Style comentarioStyle = codigo.addStyle("ComentarioStyle", null);
        StyleConstants.setForeground(comentarioStyle, Color.GRAY);
        
        Style errorStyle = codigo.addStyle("errorStyle", null);
        StyleConstants.setForeground(errorStyle, Color.RED);
    }

      private Style getStyleForTokenType(TokenType type) {
        switch (type) {
            case CREATE:
            case LOGICOS:
                return codigo.getStyle("CreateStyle");
            case TIPO_DATO:
                return codigo.getStyle("TipoDatoStyle");
            case ENTERO:
            case DECIMAL:
            case BOOLEANO:
            case FUNCIONES:
                return codigo.getStyle("NumeroStyle");
            case FECHA:
                return codigo.getStyle("FechaStyle");
            case CADENA:
                return codigo.getStyle("CadenaStyle");
            case ID:
                return codigo.getStyle("IdStyle");
            case SIGNOS:
            case ARITMETICOS:
            case RELACIONALES:
                return codigo.getStyle("SignosStyle");
            case COMENTARIO:
                return codigo.getStyle("ComentarioStyle");
            case ERROR:
                return codigo.getStyle("errorStyle");
            default:
                return null;
        }
    }


  // Analizar y colorear el texto en JTextPane sin eliminar el documento prematuramente
private void analyzeAndHighlight() {
    String text = codigo.getText(); // Extrae el texto completo del JTextPane
    Lexer lexer = new Lexer(new BufferedReader(new StringReader(text)));
    tokens.clear(); // Limpia la lista de tokens anterior para evitar duplicados

    try {
        // Generamos los tokens y los agregamos a la lista tokens
        Token token;
        while ((token = lexer.yylex()) != null) {
            tokens.add(token);
        }

        // Borramos el documento una vez que ya tenemos los tokens generados
        doc.remove(0, doc.getLength());

        // Posición actual en el texto
        int currentPosition = 0;

        // Insertamos cada token con su estilo correspondiente, respetando saltos de línea y espacios
        for (Token t : tokens) {
            // Posición inicial y final del token en el texto
            int tokenStart = text.indexOf(t.getLexema(), currentPosition);

            if (tokenStart == -1) {
                continue; // En caso de que no encuentre el token en el texto
            }

            // Agrega texto sin estilo (espacios, saltos de línea) hasta el token
            if (currentPosition < tokenStart) {
                String whitespace = text.substring(currentPosition, tokenStart);
                doc.insertString(doc.getLength(), whitespace, null);
            }

            // Inserta el token con el estilo adecuado
            Style style = getStyleForTokenType(t.getTipo());
            doc.insertString(doc.getLength(), t.getLexema(), style);

            // Actualizamos la posición actual al final del token
            currentPosition = tokenStart + t.getLexema().length();
        }

        // Añade cualquier texto remanente después del último token
        if (currentPosition < text.length()) {
            String remainingText = text.substring(currentPosition);
            doc.insertString(doc.getLength(), remainingText, null);
        }
        
    } catch (Exception e) {
        e.printStackTrace();
    }
}



      
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextPane codigo;
    private javax.swing.JButton jButton1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}