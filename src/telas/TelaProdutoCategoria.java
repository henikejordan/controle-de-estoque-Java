/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import util.ConectaBanco;

/**
 *
 * @author Henike
 */
public class TelaProdutoCategoria extends javax.swing.JFrame {

    ConectaBanco conex = ConectaBanco.getInstance();

    /**
     * Creates new form TelaProdutoData
     */
    public TelaProdutoCategoria() {
        initComponents();
        MaskFormatter form;
        try {
            form = new MaskFormatter("##/##/####");
            jFormattedTextFieldData.setFormatterFactory(new DefaultFormatterFactory(form));

            // pega data do sistema
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date hoje = new java.util.Date();
            jFormattedTextFieldData.setText(df.format(hoje));
        } catch (ParseException ex) {
            Logger.getLogger(TelaPesquisaVenda.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.preencherComboCat();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButtonOK = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jFormattedTextFieldData = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        jComboBoxCat = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Produto por data");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Produto por categoria");

        jLabel2.setText("Data:");

        jButtonOK.setText("Confirmar");
        jButtonOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOKActionPerformed(evt);
            }
        });

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        jLabel3.setText("Categoria:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jButtonOK)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonCancelar)
                .addGap(23, 23, 23))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 29, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jFormattedTextFieldData)
                            .addComponent(jComboBoxCat, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(31, 31, 31))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextFieldData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBoxCat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCancelar)
                    .addComponent(jButtonOK))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(297, 254));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOKActionPerformed
        conex.conexao();
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            format.setLenient(false);
            Date data = new Date(format.parse(jFormattedTextFieldData.getText()).getTime());
            conex.executaSQL("select vpr.codp,"
                    + " vpr.cod_barras,"
                    + " vpr.nome,"
                    + " vpr.preco_compra,"
                    + " vpr.preco_venda,"
                    + " vpr.estoque_minimo,"
                    + " vpr.codf,"
                    + " vpr.nome_fornecedor,"
                    + " vpr.codcat,"
                    + " vpr.nome_categoria,"
                    + " ed.coded,"
                    + " ed.quantidade,"
                    + " vpr.data"
                    + " from (select vpd.codp,"
                    + " vpd.cod_barras,"
                    + " vpd.nome,"
                    + " vpd.preco_compra,"
                    + " vpd.preco_venda,"
                    + " vpd.estoque_minimo,"
                    + " vpd.codf,"
                    + " vpd.nome_fornecedor,"
                    + " vpd.codcat,"
                    + " vpd.nome_categoria,"
                    + " max(vpd.data) as data"
                    + " from view_produtos_detalhes vpd"
                    + " join estoque_dia ed on ed.data= vpd.data"
                    + " where vpd.data <= '" + data + "' and vpd.nome_categoria = '" + jComboBoxCat.getSelectedItem() + "'"
                    + " group by vpd.codp, vpd.cod_barras, vpd.nome,"
                    + " vpd.preco_compra, vpd.preco_venda,"
                    + " vpd.estoque_minimo, vpd.codf, vpd.nome_fornecedor,"
                    + " vpd.codcat, vpd.nome_categoria)vpr"
                    + " join estoque_dia ed on ed.codp = vpr.codp and ed.data=vpr.data order by nome");
            JRResultSetDataSource relatResul = new JRResultSetDataSource(conex.rs);// Passa um resultSet preenchido para o relatório!
            Map parametros = new HashMap();
            parametros.put("Parameter1", jFormattedTextFieldData.getText());
            parametros.put("Parameter2", jComboBoxCat.getSelectedItem());
            JasperPrint jpPrint = JasperFillManager.fillReport("iReport/relatorioBrancoProdutoCategoria.jasper", parametros, relatResul);// Indica o caminho para pegar o modelo do relatório
            JasperViewer jv = new JasperViewer(jpPrint, false);// Cria instância para impressão (setar false para não fechar toda a aplicação!!!)
            jv.setVisible(true);// Relatório para visualização
            jv.toFront(); // Seta o formulário na frente da aplicação
            this.dispose();
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao chamar o relatório!\nERRO:" + ex);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Data inválida!");
        }
        conex.desconecta();
    }//GEN-LAST:event_jButtonOKActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void preencherComboCat() {
        conex.conexao();
        conex.executaSQL("select * from categoria order by nome");
        Object obj = jComboBoxCat.getSelectedItem();
        jComboBoxCat.removeAllItems();
        jComboBoxCat.addItem("");
        try {
            conex.rs.first();
            do {
                jComboBoxCat.addItem(conex.rs.getString("nome"));
            } while (conex.rs.next());
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(this, "Erro ao preencher combobox!\n"+ex.getMessage());
        }
        jComboBoxCat.setSelectedItem(obj);
        conex.desconecta();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaProdutoCategoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new TelaProdutoCategoria().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonOK;
    private javax.swing.JComboBox jComboBoxCat;
    private javax.swing.JFormattedTextField jFormattedTextFieldData;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
