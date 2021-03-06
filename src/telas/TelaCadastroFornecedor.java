/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import controle.ControleFornecedor;
import entidade.CEndereco;
import entidade.CFornecedor;
import entidade.ModeloTabela;
import static java.awt.event.KeyEvent.*;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import util.ConectaBanco;

/**
 *
 * @author Henike
 */
public class TelaCadastroFornecedor extends javax.swing.JFrame {

    ConectaBanco conex = ConectaBanco.getInstance();
    CFornecedor fornecedor = new CFornecedor();
    CEndereco endereco = new CEndereco();
    ControleFornecedor control = new ControleFornecedor();
    int flag = 0;

    /**
     * Creates new form TelaCadastroFornecedor
     */
    public TelaCadastroFornecedor() {
        initComponents();
        getRootPane().setDefaultButton(jButtonNovo);

        this.preencherTabela("select * from view_fornecedor_detalhes order by nome limit 100");
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
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        jButtonCancelar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButtonNovo = new javax.swing.JButton();
        jTextFieldPesqForn = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButtonAlterar = new javax.swing.JButton();
        jButtonAjuda = new javax.swing.JButton();
        jButtonPesquisar = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastrar fornecedor");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tabelaMouseEntered(evt);
            }
        });
        tabela.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabelaKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(tabela);

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Cadastrar fornecedor");

        jButtonNovo.setText("Novo");
        jButtonNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNovoActionPerformed(evt);
            }
        });

        jLabel2.setText("Pesquisar fornecedor:");

        jButtonAlterar.setText("Alterar");
        jButtonAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterarActionPerformed(evt);
            }
        });

        jButtonAjuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/telas/help.gif"))); // NOI18N
        jButtonAjuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAjudaActionPerformed(evt);
            }
        });

        jButtonPesquisar.setText("Pesquisar");
        jButtonPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPesquisarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(204, 204, 204)
                        .addComponent(jButtonAjuda))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jButtonNovo)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButtonAlterar)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 490, Short.MAX_VALUE)
                                .addComponent(jButtonCancelar))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextFieldPesqForn, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonPesquisar)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(36, 36, 36))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jButtonAjuda))
                .addGap(23, 23, 23)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldPesqForn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonPesquisar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonNovo)
                    .addComponent(jButtonCancelar)
                    .addComponent(jButtonAlterar))
                .addGap(29, 29, 29))
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNovoActionPerformed
        new TelaNovoFornecedor().setVisible(true);
    }//GEN-LAST:event_jButtonNovoActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void tabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMouseClicked
        if (evt.getClickCount() == 2 && !evt.isConsumed()) {
            evt.consume();
            String nome = "" + tabela.getValueAt(tabela.getSelectedRow(), 0);
            new TelaAlterarFornecedor(nome).setVisible(true);
        }
    }//GEN-LAST:event_tabelaMouseClicked

    private void jButtonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarActionPerformed
        try {
            String nome = "" + tabela.getValueAt(tabela.getSelectedRow(), 0);
            if (nome != null) {
                new TelaAlterarFornecedor(nome).setVisible(true);
            }
        } catch (ArrayIndexOutOfBoundsException ex) {

        }
    }//GEN-LAST:event_jButtonAlterarActionPerformed

    private void jButtonAjudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAjudaActionPerformed
        new TelaAjudaCadFornecedor().setVisible(true);
    }//GEN-LAST:event_jButtonAjudaActionPerformed

    private void tabelaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMouseEntered
        if (jTextFieldPesqForn.getText().equals("")) {
            this.preencherTabela("select * from view_fornecedor_detalhes order by nome limit 100");
        } else {
            this.preencherTabela("select * from view_fornecedor_detalhes where nome ~* '" + jTextFieldPesqForn.getText() + "' order by nome");
        }
    }//GEN-LAST:event_tabelaMouseEntered

    private void tabelaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabelaKeyPressed
        if (evt.getKeyCode() == VK_ENTER) {
            evt.consume();
            String nome = "" + tabela.getValueAt(tabela.getSelectedRow(), 0);
            new TelaAlterarFornecedor(nome).setVisible(true);
        } else if (evt.getKeyCode() == VK_ESCAPE) {
            jTextFieldPesqForn.requestFocus();
        }
    }//GEN-LAST:event_tabelaKeyPressed

    private void jButtonPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPesquisarActionPerformed
        if (jTextFieldPesqForn.getText().equals("")) {
            this.preencherTabela("select * from view_fornecedor_detalhes order by nome limit 100");
        } else {
            this.preencherTabela("select * from view_fornecedor_detalhes where nome ~* '" + jTextFieldPesqForn.getText() + "' order by nome");
        }
    }//GEN-LAST:event_jButtonPesquisarActionPerformed

    private void preencherTabela(String sql) {
        ArrayList dados = new ArrayList();
        String[] colunas = new String[]{"Nome", "CEP", "CNPJ", "Telefone"};
        int i = 0;
        conex.conexao();
        conex.executaSQL(sql);
        try {
            conex.rs.first();
            do {
                i++;
                dados.add(new Object[]{conex.rs.getString("nome"), conex.rs.getString("cep"),
                    conex.rs.getString("cnpj"), conex.rs.getString("numero_tel")});
            } while (conex.rs.next());
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(this, "Erro ao preencher Arraylist!\n"+ex.getMessage());
        }

        ModeloTabela modelotabela = new ModeloTabela(dados, colunas);
        tabela.setModel(modelotabela);
        if (i > 20) {
            tabela.getColumnModel().getColumn(0).setPreferredWidth(286);
            tabela.getColumnModel().getColumn(1).setPreferredWidth(96);
            tabela.getColumnModel().getColumn(2).setPreferredWidth(156);
            tabela.getColumnModel().getColumn(3).setPreferredWidth(133);
        } else {
            tabela.getColumnModel().getColumn(0).setPreferredWidth(290);
            tabela.getColumnModel().getColumn(1).setPreferredWidth(100);
            tabela.getColumnModel().getColumn(2).setPreferredWidth(160);
            tabela.getColumnModel().getColumn(3).setPreferredWidth(137);
        }
        tabela.getColumnModel().getColumn(0).setResizable(false);
        tabela.getColumnModel().getColumn(1).setResizable(false);
        tabela.getColumnModel().getColumn(2).setResizable(false);
        tabela.getColumnModel().getColumn(3).setResizable(false);
        tabela.getTableHeader().setReorderingAllowed(false);
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

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
            java.util.logging.Logger.getLogger(TelaCadastroCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new TelaCadastroFornecedor().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAjuda;
    private javax.swing.JButton jButtonAlterar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonNovo;
    private javax.swing.JButton jButtonPesquisar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFieldPesqForn;
    private javax.swing.JTable tabela;
    // End of variables declaration//GEN-END:variables
}
