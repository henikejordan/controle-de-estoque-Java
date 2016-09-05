/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import entidade.CUsuario;
import entidade.CPrivilegios;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import util.ConectaBanco;

/**
 *
 * @author Henike
 */
public class ControleUsuario {

    public ConectaBanco conex = ConectaBanco.getInstance();

    public boolean salvar(CUsuario usu, CPrivilegios pri) {
        boolean flag = false;
        int codpv = -1;
        conex.conexao();
        try {
            PreparedStatement pst = conex.conn.prepareStatement("insert into privilegios(logins, categorias, clientes, fornecedores, produtos, pesq_vendas, efetuar_vendas, relatorios) values(?,?,?,?,?,?,?,?)");
            pst.setBoolean(1, pri.isLogins());
            pst.setBoolean(2, pri.isCategorias());
            pst.setBoolean(3, pri.isClientes());
            pst.setBoolean(4, pri.isFornecedores());
            pst.setBoolean(5, pri.isProdutos());
            pst.setBoolean(6, pri.isPesq_vendas());
            pst.setBoolean(7, pri.isEfetuar_vendas());
            pst.setBoolean(8, pri.isRelatorios());
            pst.execute();

            conex.executaSQL("select max(codpv) from privilegios");
            conex.rs.first();
            codpv = conex.rs.getInt("max");// pega a chave do último produto inserido

            pst = conex.conn.prepareStatement("insert into usuario(nome, login, senha, codpv) values(?,?,?,?)");
            pst.setString(1, usu.getNome());
            pst.setString(2, usu.getLogin());
            pst.setString(3, usu.getSenha());
            pst.setInt(4, codpv);
            pst.execute();

            flag = true;
            JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
        } catch (SQLException ex) {
            try {
                PreparedStatement pst = conex.conn.prepareStatement("delete from privilegios where codpv=?");
                pst.setInt(1, codpv);
                pst.execute();
            } catch (SQLException ex1) {
                //JOptionPane.showMessageDialog(null, "Erro ao excluir o privilégio!\n"+ex1.getMessage());
            }
            JOptionPane.showMessageDialog(null, "Erro ao inserir o usuário!\n" + ex.getMessage().replaceAll("ERROR:", ""));
        }
        conex.desconecta();
        return flag;
    }

    public boolean alterar(CUsuario usu, CPrivilegios pri) {
        boolean flag = false;
        conex.conexao();
        try {
            PreparedStatement pst = conex.conn.prepareStatement("update usuario set nome=?, login=?, senha=?, codpv=? where codu=?");
            pst.setString(1, usu.getNome());
            pst.setString(2, usu.getLogin());
            pst.setString(3, usu.getSenha());
            pst.setInt(4, pri.getCodpv());
            pst.setInt(5, usu.getCodu());
            pst.execute();

            pst = conex.conn.prepareStatement("update privilegios set logins=?, categorias=?, clientes=?, fornecedores=?, produtos=?, pesq_vendas=?, efetuar_vendas=?, relatorios=? where codpv=?");
            pst.setBoolean(1, pri.isLogins());
            pst.setBoolean(2, pri.isCategorias());
            pst.setBoolean(3, pri.isClientes());
            pst.setBoolean(4, pri.isFornecedores());
            pst.setBoolean(5, pri.isProdutos());
            pst.setBoolean(6, pri.isPesq_vendas());
            pst.setBoolean(7, pri.isEfetuar_vendas());
            pst.setBoolean(8, pri.isRelatorios());
            pst.setInt(9, pri.getCodpv());
            pst.execute();

            flag = true;
            JOptionPane.showMessageDialog(null, "Usuário alterado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar o usuário!\n" + ex.getMessage().replaceAll("ERROR:", ""));
        }
        conex.desconecta();
        return flag;
    }

    public void excluir(CUsuario usu) {
        conex.conexao();
        try {
            PreparedStatement pst = conex.conn.prepareStatement("delete from usuario where codu=?");
            pst.setInt(1, usu.getCodu());
            pst.execute();

            pst = conex.conn.prepareStatement("delete from privilegios where codpv=?");
            pst.setInt(1, usu.getCodpv());
            pst.execute();

            JOptionPane.showMessageDialog(null, "Usuário excluído com sucesso!");
        } catch (SQLException ex) {
            if (ex.getMessage().length() > 70) {
                JOptionPane.showMessageDialog(null, "Erro ao excluir o usuário!\nEste usuário está vinculado a um registro.");
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao excluir o usuário!\n" + ex.getMessage().replaceAll("ERROR:", ""));
            }
        }
        conex.desconecta();
    }

}
