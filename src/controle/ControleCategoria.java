/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import entidade.CCategoria;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import util.ConectaBanco;

/**
 *
 * @author Henike
 */
public class ControleCategoria {

    public ConectaBanco conex = ConectaBanco.getInstance();

    public boolean salvar(CCategoria cat) {
        boolean flag = false;
        conex.conexao();
        try {
            PreparedStatement pst = conex.conn.prepareStatement("insert into categoria(nome, descricao) values(?,?)");
            pst.setString(1, cat.getNome());
            pst.setString(2, cat.getDescricao());
            pst.execute();

            flag = true;
            JOptionPane.showMessageDialog(null, "Categoria cadastrada com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir a categoria!\n" + ex.getMessage().replaceAll("ERROR:", ""));
        }
        conex.desconecta();
        return flag;
    }

    public boolean alterar(CCategoria cat) {
        boolean flag = false;
        conex.conexao();
        try {
            PreparedStatement pst = conex.conn.prepareStatement("update categoria set nome=?, descricao=? where codcat=?");
            pst.setString(1, cat.getNome());
            pst.setString(2, cat.getDescricao());
            pst.setInt(3, cat.getCodcat());
            pst.execute();

            flag = true;
            JOptionPane.showMessageDialog(null, "Categoria alterada com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar a categoria!\n" + ex.getMessage().replaceAll("ERROR:", ""));
        }
        conex.desconecta();
        return flag;
    }

    public void excluir(CCategoria cat) {
        conex.conexao();
        try {
            PreparedStatement pst = conex.conn.prepareStatement("delete from categoria where codcat=?");
            pst.setInt(1, cat.getCodcat());
            pst.execute();

            JOptionPane.showMessageDialog(null, "Categoria excluída com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir a categoria!\nEsta categoria está vinculada a um registro.");
        }
        conex.desconecta();
    }
}
