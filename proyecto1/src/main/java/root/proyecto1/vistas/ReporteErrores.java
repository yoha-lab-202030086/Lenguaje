
package root.proyecto1.vistas;

import javax.swing.table.DefaultTableModel;


public class ReporteErrores extends javax.swing.JFrame {

  
    public ReporteErrores() {
        initComponents();
        tabla();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        erroress = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Liberation Sans", 1, 36)); // NOI18N
        jLabel1.setText("Errores");

        erroress.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(erroress);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(372, 372, 372)
                .addComponent(jLabel1)
                .addContainerGap(421, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable erroress;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    public void tabla(){
                        DefaultTableModel modif=(DefaultTableModel)erroress.getModel();
        modif.setRowCount(0);
        modif.setColumnIdentifiers(new Object[]{"TOKEN", "LENGUAJE ENCONTRADO","LENGUAJE SUGERIDO", "FILA","COLUMNA"});
        for (Errores token : MultiplesAnalizadores.reporteErrores) {
            modif.addRow(new Object[]{token.getLex(),token.getLenguajeEncontrado(),token.getLenguajeSugerido(),token.getLinea(),token.getColumna()});
        }
        erroress.setModel(modif);
    }
}
