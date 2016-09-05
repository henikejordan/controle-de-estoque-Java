/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import entidade.CProduto;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import util.ConectaBanco;

/**
 *
 * @author Henike
 */
public class ControleProduto {

    public ConectaBanco conex = ConectaBanco.getInstance();
    private int codp, codf, codcat, coded = 0;
    private boolean ver = false;

    public boolean inserir(CProduto produto) {
        boolean flag = false;
        // Pra não dar erro do produto estar com os campos corretos, mas a quantidade inválida!!!!!
        // Caso contrário ele iria inserir o produto, com mas o estoque_dia não!!!!!
        if (produto.getQuantidade() < 0) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir o produto!\nQuantidade inválida!");
            return flag;
        }

        //Se não possui fornecedor
        if (produto.getFornecedor() == null) {
            JOptionPane.showMessageDialog(null, "O fornecedor deve ser preenchido!");
            return flag;
        }

        //Se não possui categoria
        if (produto.getCategoria() == null) {
            JOptionPane.showMessageDialog(null, "A categoria deve ser preenchida!");
            return flag;
        }

        //Se não possui fornecedor
        if (produto.getFornecedor().equals("")) {
            JOptionPane.showMessageDialog(null, "O fornecedor deve ser preenchido!");
            return flag;
        }

        //Se não possui categoria
        if (produto.getCategoria().equals("")) {
            JOptionPane.showMessageDialog(null, "A categoria deve ser preenchida!");
            return flag;
        }

        this.buscaCodigo(produto.getFornecedor(), produto.getCategoria());
        conex.conexao();
        try {
            PreparedStatement pst = conex.conn.prepareStatement("insert into produto(cod_barras, nome, preco_compra, preco_venda, estoque_minimo, codcat, codf) values (?,?,?,?,?,?,?)");
            pst.setString(1, produto.getCod_barras());
            pst.setString(2, produto.getNome());
            BigDecimal precocompra = new BigDecimal(produto.getPreco_compra()).setScale(2, BigDecimal.ROUND_HALF_UP);
            pst.setBigDecimal(3, precocompra);
            BigDecimal precovenda = new BigDecimal(produto.getPreco_venda()).setScale(2, BigDecimal.ROUND_HALF_UP);
            pst.setBigDecimal(4, precovenda);
            pst.setInt(5, produto.getEstoque_minimo());
            pst.setInt(6, codcat);
            pst.setInt(7, codf);
            pst.execute();

            conex.executaSQL("select max(codp) from produto");
            conex.rs.first();
            codp = conex.rs.getInt("max");// pega a chave do último produto inserido
            ver = true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir o produto!\n" + ex.getMessage().replaceAll("ERROR:", ""));
        }
        // Se o produto está com os campos corretos!!!!!
        if (ver) {
            try {
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                Date data = new Date(format.parse(produto.getData()).getTime());

                PreparedStatement pst = conex.conn.prepareStatement("insert into estoque_dia(quantidade, data, codp) values (?,?,?)");
                pst.setInt(1, produto.getQuantidade());
                pst.setDate(2, data);
                pst.setInt(3, codp);
                pst.execute();

                flag = true;
                JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao inserir o produto!\n" + ex.getMessage().replaceAll("ERROR:", ""));
            } catch (ParseException ex) {
                Logger.getLogger(ControleProduto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        ver = false;
        conex.desconecta();
        return flag;
    }

    public boolean alterar(CProduto produto) {
        boolean flag = false;
        // Pra não dar erro do produto estar com os campos corretos, mas a quantidade inválida!!!!!
        // Caso contrário ele iria inserir o produto, com mas o estoque_dia não!!!!!
        if (produto.getQuantidade() < 0) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar o produto!\nQuantidade inválida!");
            return flag;
        }

        //Se não possui categoria
        if (produto.getCategoria() == null) {
            JOptionPane.showMessageDialog(null, "A categoria deve ser preenchida!");
            return flag;
        }

        //Se não possui fornecedor
        if (produto.getFornecedor() == null) {
            JOptionPane.showMessageDialog(null, "O fornecedor deve ser preenchido!");
            return flag;
        }

        //Se não possui categoria
        if (produto.getCategoria().equals("")) {
            JOptionPane.showMessageDialog(null, "A categoria deve ser preenchida!");
            return flag;
        }

        //Se não possui fornecedor
        if (produto.getFornecedor().equals("")) {
            JOptionPane.showMessageDialog(null, "O fornecedor deve ser preenchido!");
            return flag;
        }

        this.buscaCodigo(produto.getFornecedor(), produto.getCategoria());
        Date data = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            data = new Date(format.parse(produto.getData()).getTime());
        } catch (ParseException ex) {
            Logger.getLogger(ControleProduto.class.getName()).log(Level.SEVERE, null, ex);
        }

        conex.conexao();
        try {
            PreparedStatement pst = conex.conn.prepareStatement("update produto set cod_barras=?, nome=?, preco_compra=?, preco_venda=?, "
                    + "estoque_minimo=?, codcat=?, codf=? where codp=?");
            pst.setString(1, produto.getCod_barras());
            pst.setString(2, produto.getNome());
            BigDecimal precocompra = new BigDecimal(produto.getPreco_compra()).setScale(2, BigDecimal.ROUND_HALF_UP);
            pst.setBigDecimal(3, precocompra);
            BigDecimal precovenda = new BigDecimal(produto.getPreco_venda()).setScale(2, BigDecimal.ROUND_HALF_UP);
            pst.setBigDecimal(4, precovenda);
            pst.setInt(5, produto.getEstoque_minimo());
            pst.setInt(6, codcat);
            pst.setInt(7, codf);
            pst.setInt(8, produto.getCodp());
            pst.execute();

            codp = produto.getCodp();
            ver = true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar produto!\n" + ex.getMessage().replaceAll("ERROR:", ""));
        }
        // Se o produto está com os campos corretos!!!!!
        if (ver) {
            try {
                conex.executaSQL("select * from estoque_dia where codp='" + codp + "' and data='" + data + "'");
                conex.rs.first();
                coded = conex.rs.getInt("coded");

                // Significa que o produto está sendo alterado na mesma data da última alteração 
                PreparedStatement pst = conex.conn.prepareStatement("update estoque_dia set quantidade=?, data=? where coded=?");
                pst.setInt(1, produto.getQuantidade());
                pst.setDate(2, data);
                pst.setInt(3, coded);
                pst.execute();

                flag = true;
                JOptionPane.showMessageDialog(null, "Produto alterado com sucesso!");
            } catch (SQLException ex) {
                try {
                    PreparedStatement pst = conex.conn.prepareStatement("insert into estoque_dia(quantidade, data, codp) values (?,?,?)");
                    pst.setInt(1, produto.getQuantidade());
                    pst.setDate(2, data);
                    pst.setInt(3, codp);
                    pst.execute();

                    flag = true;
                    JOptionPane.showMessageDialog(null, "Produto alterado com sucesso!\n");
                } catch (SQLException ex1) {

                }
            }
        }
        ver = false;
        conex.desconecta();
        return flag;
    }

    public void excluir(CProduto produto) {
        conex.conexao();
        try {
            PreparedStatement pst = conex.conn.prepareStatement("delete from estoque_dia where codp=?");
            pst.setInt(1, produto.getCodp());
            pst.execute();

            pst = conex.conn.prepareStatement("delete from produto where codp=?");
            pst.setInt(1, produto.getCodp());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Produto excluído com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir o produto!\nEste produto está vinculado a um registro.");

        }
        conex.desconecta();
    }

    private void buscaCodigo(String nomeForn, String nomeCat) {
        conex.conexao();
        try {
            conex.executaSQL("select * from fornecedor where nome='" + nomeForn + "'");
            conex.rs.first();
            codf = conex.rs.getInt("codf");

            conex.executaSQL("select * from categoria where nome='" + nomeCat + "'");
            conex.rs.first();
            codcat = conex.rs.getInt("codcat");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar código!\n" + ex.getMessage().replaceAll("ERROR:", ""));
        }
        conex.desconecta();
    }
}
