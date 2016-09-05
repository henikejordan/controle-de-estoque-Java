/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import controle.AtualizaHora;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import util.ConectaBanco;

/**
 *
 * @author Henike
 */
public class TelaInicial extends javax.swing.JFrame implements PropertyChangeListener {

    private TelaBrowser browser;
    ConectaBanco conex = ConectaBanco.getInstance();
    private String user;

    /**
     * Creates new form TelaInicial
     *
     * @param user
     */
    public TelaInicial(String user) {
        this.user = user;
        initComponents();

        //pegar resolução da tela
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();

        //setar a posição e tamanho da label
        jLabelFundo.setBounds(0, 0, d.width - 12, d.height - 112);

        File diretorio = new File("img\\image.jpg");
        //se existir a imagem, então carregue-a
        if (diretorio.exists()) {
            ImageIcon imagem = new ImageIcon("img\\image.jpg");
            Image imag = imagem.getImage().getScaledInstance(jLabelFundo.getWidth(), jLabelFundo.getHeight(), Image.SCALE_SMOOTH);
            jLabelFundo.setIcon(new ImageIcon(imag));
        }

        browser = new TelaBrowser();
        browser.setTitle("Abrir");
        browser.setVisible(false);
        browser.getJFileChooser().addPropertyChangeListener(this);

        // Comando para maximizar a tela
        this.setExtendedState(this.getExtendedState() | TelaInicial.MAXIMIZED_BOTH);
        conex.conexao();
        jLabelUser.setText(user);
        AtualizaHora.start(jLabelHorario);

        conex.executaSQL("select * from usuario where login='" + jLabelUser.getText() + "'");
        try {
            conex.rs.first();
            int codpv = conex.rs.getInt("codpv");

            conex.executaSQL("select * from privilegios where codpv='" + codpv + "'");
            conex.rs.first();
            jMenuCadItemUsu.setEnabled(conex.rs.getBoolean("logins"));
            jMenuCadItemCli.setEnabled(conex.rs.getBoolean("clientes"));
            jMenuCadItemForn.setEnabled(conex.rs.getBoolean("fornecedores"));
            jMenuCadItemCat.setEnabled(conex.rs.getBoolean("categorias"));
            jMenuCadItemProd.setEnabled(conex.rs.getBoolean("produtos"));
            jMenuItemPesqVenda.setEnabled(conex.rs.getBoolean("pesq_vendas"));
            jMenuItemCadVenda.setEnabled(conex.rs.getBoolean("efetuar_vendas"));
            jMenuItemFatVendas.setEnabled(conex.rs.getBoolean("pesq_vendas"));
            jMenuItemVendasParc.setEnabled(conex.rs.getBoolean("efetuar_vendas"));
            jMenuRelatorios.setEnabled(conex.rs.getBoolean("relatorios"));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao setar os privilégios!\n" + ex);
        }
        jMenuCadastros.setMnemonic(KeyEvent.VK_C);
        jMenuVenda.setMnemonic(KeyEvent.VK_V);
        jMenuRelatorios.setMnemonic(KeyEvent.VK_R);
        jMenuConfig.setMnemonic(KeyEvent.VK_O);
        jMenuSair.setMnemonic(KeyEvent.VK_S);
    }

    private TelaInicial() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jDialog2 = new javax.swing.JDialog();
        jFrame1 = new javax.swing.JFrame();
        jLabelUser = new javax.swing.JLabel();
        jLabelUsuario = new javax.swing.JLabel();
        jLabelHorario = new javax.swing.JLabel();
        jLabelFundo = new javax.swing.JLabel();
        jMenuBar = new javax.swing.JMenuBar();
        jMenuCadastros = new javax.swing.JMenu();
        jMenuCadItemProd = new javax.swing.JMenuItem();
        jMenuCadItemCat = new javax.swing.JMenuItem();
        jMenuCadItemCli = new javax.swing.JMenuItem();
        jMenuCadItemForn = new javax.swing.JMenuItem();
        jMenuCadItemUsu = new javax.swing.JMenuItem();
        jMenuVenda = new javax.swing.JMenu();
        jMenuItemCadVenda = new javax.swing.JMenuItem();
        jMenuItemFatVendas = new javax.swing.JMenuItem();
        jMenuItemPesqVenda = new javax.swing.JMenuItem();
        jMenuItemVendasParc = new javax.swing.JMenuItem();
        jMenuRelatorios = new javax.swing.JMenu();
        jMenuConfig = new javax.swing.JMenu();
        jMenuItemAltImg = new javax.swing.JMenuItem();
        jMenuSair = new javax.swing.JMenu();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Controle Estoque da Acquatic e Lazer");
        setLocation(new java.awt.Point(500, 200));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabelUser.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabelUser.setText("jLabel1");

        jLabelUsuario.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabelUsuario.setText("Usuário:");

        jLabelHorario.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabelHorario.setText("jLabel2");

        jLabelFundo.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jMenuBar.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N

        jMenuCadastros.setMnemonic('C');
        jMenuCadastros.setText("Cadastro");
        jMenuCadastros.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N

        jMenuCadItemProd.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jMenuCadItemProd.setText("Produto");
        jMenuCadItemProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuCadItemProdActionPerformed(evt);
            }
        });
        jMenuCadastros.add(jMenuCadItemProd);

        jMenuCadItemCat.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jMenuCadItemCat.setText("Categoria");
        jMenuCadItemCat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuCadItemCatActionPerformed(evt);
            }
        });
        jMenuCadastros.add(jMenuCadItemCat);

        jMenuCadItemCli.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jMenuCadItemCli.setText("Cliente");
        jMenuCadItemCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuCadItemCliActionPerformed(evt);
            }
        });
        jMenuCadastros.add(jMenuCadItemCli);

        jMenuCadItemForn.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jMenuCadItemForn.setText("Fornecedor");
        jMenuCadItemForn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuCadItemFornActionPerformed(evt);
            }
        });
        jMenuCadastros.add(jMenuCadItemForn);

        jMenuCadItemUsu.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jMenuCadItemUsu.setText("Usuário");
        jMenuCadItemUsu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuCadItemUsuActionPerformed(evt);
            }
        });
        jMenuCadastros.add(jMenuCadItemUsu);

        jMenuBar.add(jMenuCadastros);

        jMenuVenda.setText("Venda");
        jMenuVenda.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N

        jMenuItemCadVenda.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jMenuItemCadVenda.setText("Registrar Venda");
        jMenuItemCadVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCadVendaActionPerformed(evt);
            }
        });
        jMenuVenda.add(jMenuItemCadVenda);

        jMenuItemFatVendas.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jMenuItemFatVendas.setText("Faturamentos");
        jMenuItemFatVendas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemFatVendasActionPerformed(evt);
            }
        });
        jMenuVenda.add(jMenuItemFatVendas);

        jMenuItemPesqVenda.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jMenuItemPesqVenda.setText("Detalhes de Vendas");
        jMenuItemPesqVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPesqVendaActionPerformed(evt);
            }
        });
        jMenuVenda.add(jMenuItemPesqVenda);

        jMenuItemVendasParc.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jMenuItemVendasParc.setText("Vendas Parceladas");
        jMenuItemVendasParc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemVendasParcActionPerformed(evt);
            }
        });
        jMenuVenda.add(jMenuItemVendasParc);

        jMenuBar.add(jMenuVenda);

        jMenuRelatorios.setText("Relatórios");
        jMenuRelatorios.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jMenuRelatorios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuRelatoriosMouseClicked(evt);
            }
        });
        jMenuBar.add(jMenuRelatorios);

        jMenuConfig.setText("Configurar");
        jMenuConfig.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N

        jMenuItemAltImg.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jMenuItemAltImg.setText("Alterar imagem de fundo");
        jMenuItemAltImg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAltImgActionPerformed(evt);
            }
        });
        jMenuConfig.add(jMenuItemAltImg);

        jMenuBar.add(jMenuConfig);

        jMenuSair.setText("Sair");
        jMenuSair.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jMenuSair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuSairMouseClicked(evt);
            }
        });
        jMenuBar.add(jMenuSair);

        setJMenuBar(jMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelFundo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelUser, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 240, Short.MAX_VALUE)
                        .addComponent(jLabelHorario, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelFundo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelUser)
                    .addComponent(jLabelUsuario)
                    .addComponent(jLabelHorario))
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(611, 447));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemPesqVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPesqVendaActionPerformed
        new TelaPesquisaVenda().setVisible(true);
    }//GEN-LAST:event_jMenuItemPesqVendaActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        conex.desconecta();
    }//GEN-LAST:event_formWindowClosing

    private void jMenuCadItemProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuCadItemProdActionPerformed
        new TelaCadastroProduto().setVisible(true);
    }//GEN-LAST:event_jMenuCadItemProdActionPerformed

    private void jMenuCadItemCatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuCadItemCatActionPerformed
        new TelaCadastroCategoria().setVisible(true);
    }//GEN-LAST:event_jMenuCadItemCatActionPerformed

    private void jMenuCadItemFornActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuCadItemFornActionPerformed
        new TelaCadastroFornecedor().setVisible(true);
    }//GEN-LAST:event_jMenuCadItemFornActionPerformed

    private void jMenuCadItemCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuCadItemCliActionPerformed
        new TelaCadastroCliente().setVisible(true);
    }//GEN-LAST:event_jMenuCadItemCliActionPerformed

    private void jMenuCadItemUsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuCadItemUsuActionPerformed
        conex.conexao();
        try {
            conex.executaSQL("select * from usuario where login='" + jLabelUser.getText() + "'");
            conex.rs.first();
            new TelaCadastroUsuario().setVisible(true);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao encontrar login!");
        }
        conex.desconecta();
    }//GEN-LAST:event_jMenuCadItemUsuActionPerformed

    private void jMenuItemCadVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCadVendaActionPerformed
        new TelaCadastroVenda(user).setVisible(true);
    }//GEN-LAST:event_jMenuItemCadVendaActionPerformed

    private void jMenuItemFatVendasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemFatVendasActionPerformed
        new TelaConferenciaCaixa().setVisible(true);
    }//GEN-LAST:event_jMenuItemFatVendasActionPerformed

    private void jMenuItemAltImgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAltImgActionPerformed
        browser.setVisible(true);
    }//GEN-LAST:event_jMenuItemAltImgActionPerformed

    private void jMenuItemVendasParcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemVendasParcActionPerformed
        new TelaParcelamentoVenda().setVisible(true);
    }//GEN-LAST:event_jMenuItemVendasParcActionPerformed

    private void jMenuSairMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuSairMouseClicked
        if (JOptionPane.showConfirmDialog(this, "Deseja realmente sair?", "AVISO!", JOptionPane.YES_NO_OPTION) == 0) {
            conex.desconecta();
            System.exit(0);
        }
    }//GEN-LAST:event_jMenuSairMouseClicked

    private void jMenuRelatoriosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuRelatoriosMouseClicked
        new TelaRelatorios().setVisible(true);
    }//GEN-LAST:event_jMenuRelatoriosMouseClicked

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
            java.util.logging.Logger.getLogger(TelaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new TelaInicial().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabelFundo;
    private javax.swing.JLabel jLabelHorario;
    private javax.swing.JLabel jLabelUser;
    private javax.swing.JLabel jLabelUsuario;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JMenuItem jMenuCadItemCat;
    private javax.swing.JMenuItem jMenuCadItemCli;
    private javax.swing.JMenuItem jMenuCadItemForn;
    private javax.swing.JMenuItem jMenuCadItemProd;
    private javax.swing.JMenuItem jMenuCadItemUsu;
    private javax.swing.JMenu jMenuCadastros;
    private javax.swing.JMenu jMenuConfig;
    private javax.swing.JMenuItem jMenuItemAltImg;
    private javax.swing.JMenuItem jMenuItemCadVenda;
    private javax.swing.JMenuItem jMenuItemFatVendas;
    private javax.swing.JMenuItem jMenuItemPesqVenda;
    private javax.swing.JMenuItem jMenuItemVendasParc;
    private javax.swing.JMenu jMenuRelatorios;
    private javax.swing.JMenu jMenuSair;
    private javax.swing.JMenu jMenuVenda;
    // End of variables declaration//GEN-END:variables

    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        /*
        if (pce.getPropertyName().equals(JFileChooser.SELECTED_FILE_CHANGED_PROPERTY)) {
            File f = (File) pce.getNewValue(); 
            if (f != null && f.isFile()) {
                String dir = f.getAbsolutePath();
                System.out.println(dir);
                this.carregaImagem(dir);
            }
        }*/
    }
}
