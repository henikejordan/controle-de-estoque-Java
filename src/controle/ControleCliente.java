/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import entidade.CCliente;
import entidade.CEndereco;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import util.ConectaBanco;

/**
 *
 * @author Henike
 */
public class ControleCliente {

    public ConectaBanco conex = ConectaBanco.getInstance();

    public boolean salvar(CCliente cliente, CEndereco endereco) {
        boolean flag = false;
        int codcd, codc, codt, codcel;

        //Se não possui algum telefone
        if (cliente.getTelefone().equals("") || cliente.getCelular().equals("")) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir o cliente!\nOs telefones devem ser preenchidos.");
            return flag;
        }

        //Se os telefones forem iguais
        if (cliente.getTelefone().equals(cliente.getCelular())) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir o cliente!\nOs telefones devem ser diferentes.");
            return flag;
        }

        //Se não possui cidade
        if (endereco.getCidade() == null) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir o cliente!\nA cidade deve ser preenchida.");
            return flag;
        }

        //Se não possui estado
        if (endereco.getEstado() == null) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir o cliente!\nO estado deve ser preenchido.");
            return flag;
        }

        //Se não possui cidade
        if (endereco.getCidade().equals("")) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir o cliente!\nA cidade deve ser preenchida.");
            return flag;
        }

        //Se não possui estado
        if (endereco.getEstado().equals("")) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir o cliente!\nO estado deve ser preenchido.");
            return flag;
        }

        conex.conexao();
        try {
            // DADOS DA CIDADE //////////////////////////////////////////////////////////
            conex.executaSQL("select * from cidade where nome='" + endereco.getCidade() + "' and uf='" + endereco.getEstado() + "'");
            conex.rs.first();
            codcd = conex.rs.getInt("codcd");
            ////////////////////////////////////////////////////////////////////////////////

            // DADOS DO CLIENTE /////////////////////////////////////////////////////////
            PreparedStatement pst = conex.conn.prepareStatement("insert into cliente(nome, cpf, rg, orgao_expedidor, email, "
                    + "rua, numero, bairro, cep, complemento, codcd) values(?,?,?,?,?,?,?,?,?,?,?)");
            pst.setString(1, cliente.getNome());
            pst.setString(2, cliente.getCpf());
            pst.setString(3, cliente.getRg());
            pst.setString(4, cliente.getOrgao_expedidor());
            pst.setString(5, cliente.getEmail());
            pst.setString(6, endereco.getRua());
            pst.setString(7, endereco.getNumero());
            pst.setString(8, endereco.getBairro());
            pst.setString(9, endereco.getCep());
            pst.setString(10, endereco.getComplemento());
            pst.setInt(11, codcd);
            pst.execute();

            conex.executaSQL("select * from cliente where nome='" + cliente.getNome() + "'");
            conex.rs.first();
            codc = conex.rs.getInt("codc");
            /////////////////////////////////////////////////////////////////////////////////

            // DADOS DO TELEFONE ////////////////////////////////////////////////////////////
            try {
                pst = conex.conn.prepareStatement("insert into telefone(numero_tel) values(?)");
                pst.setString(1, cliente.getTelefone());
                //pst.setString(2, "Residencial");
                pst.execute();
            } catch (SQLException ex) {
            }

            try {
                pst = conex.conn.prepareStatement("insert into telefone(numero_tel) values(?)");
                pst.setString(1, cliente.getCelular());
                //pst.setString(2, "Comercial");
                pst.execute();
            } catch (SQLException ex) {
            }

            conex.executaSQL("select * from telefone where numero_tel='" + cliente.getTelefone() + "'");
            conex.rs.first();
            codt = conex.rs.getInt("codt");

            pst = conex.conn.prepareStatement("insert into itens_cli_tel(codc, codt)values(?,?)");
            pst.setInt(1, codc);
            pst.setInt(2, codt);
            pst.execute();

            conex.executaSQL("select * from telefone where numero_tel='" + cliente.getCelular() + "'");
            conex.rs.first();
            codcel = conex.rs.getInt("codt");

            pst = conex.conn.prepareStatement("insert into itens_cli_tel(codc, codt)values(?,?)");
            pst.setInt(1, codc);
            pst.setInt(2, codcel);
            pst.execute();
            //////////////////////////////////////////////////////////////////////////////////
            flag = true;
            JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na inserção do cliente!\n" + ex.getMessage().replaceAll("ERROR:", ""));
        }
        conex.desconecta();
        return flag;
    }

    public boolean alterar(CCliente cliente, CEndereco endereco) {
        boolean flag = false;
        int codcd, codt, codcel;

        //Se não possui algum telefone
        if (cliente.getTelefone().equals("") || cliente.getCelular().equals("")) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar o cliente!\nOs telefones devem ser preenchidos.");
            return flag;
        }

        //Se os telefones forem iguais
        if (cliente.getTelefone().equals(cliente.getCelular())) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar o cliente!\nOs telefones devem ser diferentes.");
            return flag;
        }

        //Se não possui cidade
        if (endereco.getCidade() == null) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar o cliente!\nA cidade deve ser preenchida.");
            return flag;
        }

        //Se não possui estado
        if (endereco.getEstado() == null) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar o cliente!\nO estado deve ser preenchido.");
            return flag;
        }

        //Se não possui cidade
        if (endereco.getCidade().equals("")) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar o cliente!\nA cidade deve ser preenchida.");
            return flag;
        }

        //Se não possui estado
        if (endereco.getEstado().equals("")) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar o cliente!\nO estado deve ser preenchido.");
            return flag;
        }

        conex.conexao();
        try {
            // DADOS DA CIDADE //////////////////////////////////////////////////////////
            conex.executaSQL("select * from cidade where nome='" + endereco.getCidade() + "' and uf='" + endereco.getEstado() + "'");
            conex.rs.first();
            codcd = conex.rs.getInt("codcd");
            ////////////////////////////////////////////////////////////////////////////////

            // DADOS DO CLIENTE /////////////////////////////////////////////////////////
            PreparedStatement pst = conex.conn.prepareStatement("update cliente set nome=?, cpf=?, rg=?, orgao_expedidor=?, email=?, "
                    + "rua=?, numero=?, bairro=?, cep=?, complemento=?, codcd=? where codc=?");
            pst.setString(1, cliente.getNome());
            pst.setString(2, cliente.getCpf());
            pst.setString(3, cliente.getRg());
            pst.setString(4, cliente.getOrgao_expedidor());
            pst.setString(5, cliente.getEmail());
            pst.setString(6, endereco.getRua());
            pst.setString(7, endereco.getNumero());
            pst.setString(8, endereco.getBairro());
            pst.setString(9, endereco.getCep());
            pst.setString(10, endereco.getComplemento());
            pst.setInt(11, codcd);
            pst.setInt(12, cliente.getCodc());
            pst.execute();
            /////////////////////////////////////////////////////////////////////////////////

            // DADOS DO TELEFONE ////////////////////////////////////////////////////////////
            try {
                pst = conex.conn.prepareStatement("insert into telefone(numero_tel) values(?)");
                pst.setString(1, cliente.getTelefone());
                //pst.setString(2, "Residencial");
                pst.execute();
            } catch (SQLException ex) {
            }

            try {
                pst = conex.conn.prepareStatement("insert into telefone(numero_tel) values(?)");
                pst.setString(1, cliente.getCelular());
                //pst.setString(2, "Comercial");
                pst.execute();
            } catch (SQLException ex) {
            }

            // problema aki, pois têm uma trigger que não deixa apagar itens_cli_tel quando o cliente
            // está em alguma venda!!!!!
            pst = conex.conn.prepareStatement("delete from itens_cli_tel where codc=?");
            pst.setInt(1, cliente.getCodc());
            pst.execute();

            conex.executaSQL("select * from telefone where numero_tel='" + cliente.getTelefone() + "'");
            conex.rs.first();
            codt = conex.rs.getInt("codt");

            pst = conex.conn.prepareStatement("insert into itens_cli_tel(codc, codt)values(?,?)");
            pst.setInt(1, cliente.getCodc());
            pst.setInt(2, codt);
            pst.execute();

            conex.executaSQL("select * from telefone where numero_tel='" + cliente.getCelular() + "'");
            conex.rs.first();
            codcel = conex.rs.getInt("codt");

            pst = conex.conn.prepareStatement("insert into itens_cli_tel(codc, codt)values(?,?)");
            pst.setInt(1, cliente.getCodc());
            pst.setInt(2, codcel);
            pst.execute();
            //////////////////////////////////////////////////////////////////////////////////
            flag = true;
            JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na alteração do cliente!\n" + ex.getMessage().replaceAll("ERROR:", ""));
        }
        conex.desconecta();
        return flag;
    }

    public void excluir(CCliente cliente, CEndereco endereco) {
        int codt = 0, codcel = 0;
        PreparedStatement pst;
        conex.conexao();
        try {
            conex.executaSQL("select * from view_cliente_detalhes where codc='" + cliente.getCodc() + "'");
            conex.rs.first();
            codt = conex.rs.getInt("codt");
            codcel = conex.rs.getInt("codcel");

            // exclui dados na tabela itens_tel_forn
            pst = conex.conn.prepareStatement("delete from itens_cli_tel where codc=?");
            pst.setInt(1, cliente.getCodc());
            pst.execute();

            //exclui dados na tabela cliente
            pst = conex.conn.prepareStatement("delete from cliente where codc=?");
            pst.setInt(1, cliente.getCodc());
            pst.execute();

            JOptionPane.showMessageDialog(null, "Dados removidos com sucesso!");
        } catch (SQLException ex) {
            try {
                pst = conex.conn.prepareStatement("insert into itens_cli_tel(codc, codt)values(?,?)");
                pst.setInt(1, cliente.getCodc());
                pst.setInt(2, codt);
                pst.execute();

                pst = conex.conn.prepareStatement("insert into itens_cli_tel(codc, codt)values(?,?)");
                pst.setInt(1, cliente.getCodc());
                pst.setInt(2, codcel);
                pst.execute();

                JOptionPane.showMessageDialog(null, "Erro na exclusão do cliente!!\nEste cliente está vinculado a um registro.");
            } catch (SQLException ex1) {
                //Logger.getLogger(ControleCliente.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }

        try {
            //se os telefones não pertencerem a outros clientes, então estes serão excluídos
            pst = conex.conn.prepareStatement("delete from telefone where numero_tel=? and codf is null");
            pst.setString(1, cliente.getTelefone());
            pst.execute();
        } catch (SQLException ex) {
        }

        try {
            //se os telefones não pertencerem a outros clientes, então estes serão excluídos
            pst = conex.conn.prepareStatement("delete from telefone where numero_tel=? and codf is null");
            pst.setString(1, cliente.getCelular());
            pst.execute();
        } catch (SQLException ex) {
        }

        conex.desconecta();
    }

}
