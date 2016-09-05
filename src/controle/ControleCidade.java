/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import entidade.CCidade;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import util.ConectaBanco;

/**
 *
 * @author Henike
 */
public class ControleCidade {

    public ConectaBanco conex = ConectaBanco.getInstance();

    public boolean salvar(CCidade cid) {
        boolean flag = false;
        conex.conexao();
        try {
            PreparedStatement pst = conex.conn.prepareStatement("insert into cidade(nome, uf) values(?,?)");
            pst.setString(1, cid.getNome());
            pst.setString(2, cid.getUf());
            pst.execute();

            flag = true;
            JOptionPane.showMessageDialog(null, "Cidade cadastrada com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir a cidade!\n" + ex.getMessage().replaceAll("ERROR:", ""));
        }
        conex.desconecta();
        return flag;
    }

    public boolean alterar(CCidade cid) {
        boolean flag = false;
        conex.conexao();
        try {
            PreparedStatement pst = conex.conn.prepareStatement("update cidade set nome=?, uf=? where codcd=?");
            pst.setString(1, cid.getNome());
            pst.setString(2, cid.getUf());
            pst.setInt(3, cid.getCodcd());
            pst.execute();

            flag = true;
            JOptionPane.showMessageDialog(null, "Cidade alterada com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar a cidade!\n" + ex.getMessage().replaceAll("ERROR:", ""));
        }
        conex.desconecta();
        return flag;
    }

    public void excluir(CCidade cid) {
        conex.conexao();
        try {
            PreparedStatement pst = conex.conn.prepareStatement("delete from cidade where codcd=?");
            pst.setInt(1, cid.getCodcd());
            pst.execute();

            JOptionPane.showMessageDialog(null, "Cidade excluída com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir a cidade!\nEsta cidade está vinculada a um registro.");
        }
        conex.desconecta();
    }

}
