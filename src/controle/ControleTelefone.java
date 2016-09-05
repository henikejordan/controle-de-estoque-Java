/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import entidade.CTelefone;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import util.ConectaBanco;

/**
 *
 * @author Henike
 */
public class ControleTelefone {

    public ConectaBanco conex = ConectaBanco.getInstance();

    public boolean salvar(CTelefone telefone) {
        boolean flag = false;
        conex.conexao();
        try {
            PreparedStatement pst = conex.conn.prepareStatement("insert into telefone(numero_tel, tipo) values(?,?)");
            pst.setString(1, telefone.getNumero_tel());
            pst.setString(2, telefone.getTipo());
            pst.execute();

            flag = true;
            JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir!\n" + ex.getMessage().replaceAll("ERROR:", ""));
        }
        conex.desconecta();
        return flag;
    }

    public boolean alterar(CTelefone telefone) {
        boolean flag = false;
        conex.conexao();
        try {
            PreparedStatement pst = conex.conn.prepareStatement("update telefone set numero_tel=?, tipo=? where codt=?");
            pst.setString(1, telefone.getNumero_tel());
            pst.setString(2, telefone.getTipo());
            pst.setInt(3, telefone.getCodt());
            pst.execute();

            flag = true;
            JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar!\n" + ex.getMessage().replaceAll("ERROR:", ""));
        }
        conex.desconecta();
        return flag;
    }

    public void excluir(CTelefone telefone) {
        conex.conexao();
        try {
            PreparedStatement pst = conex.conn.prepareStatement("delete from telefone where codt=?");
            pst.setInt(1, telefone.getCodt());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dados excluidos com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir o telefone!\nEste telefone está vinculado a um registro.");
        }
        conex.desconecta();
    }

}
