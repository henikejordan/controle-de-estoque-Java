/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import entidade.CEndereco;
import entidade.CFornecedor;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import util.ConectaBanco;

/**
 *
 * @author Henike
 */
public class ControleFornecedor {

    public ConectaBanco conex = ConectaBanco.getInstance();

    public boolean salvar(CFornecedor fornecedor, CEndereco endereco) {
        boolean flag = false;
        int codcd, codf, codf_tel = 0, codf_tel2 = 0;

        //Se não possui algum telefone
        if (fornecedor.getTelefone().equals("") || fornecedor.getCelular().equals("")) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir o fornecedor!\nOs telefones devem ser preenchidos.");
            return flag;
        }

        //Se os telefones forem iguais
        if (fornecedor.getTelefone().equals(fornecedor.getCelular())) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir o fornecedor!\nOs telefones devem ser diferentes.");
            return flag;
        }

        //Se não possui cidade
        if (endereco.getCidade() == null) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir o fornecedor!\nA cidade deve ser preenchida.");
            return flag;
        }

        //Se não possui estado
        if (endereco.getEstado() == null) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir o fornecedor!\nO estado deve ser preenchido.");
            return flag;
        }

        //Se não possui cidade
        if (endereco.getCidade().equals("")) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir o fornecedor!\nA cidade deve ser preenchida.");
            return flag;
        }

        //Se não possui estado
        if (endereco.getEstado().equals("")) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir o fornecedor!\nO estado deve ser preenchido.");
            return flag;
        }

        conex.conexao();

        //verifica se o telefone já existe em outro fornecedor
        try {
            conex.executaSQL("select * from telefone where numero_tel='" + fornecedor.getTelefone() + "'");
            conex.rs.first();
            codf_tel = conex.rs.getInt("codf");

            conex.executaSQL("select * from telefone where numero_tel='" + fornecedor.getCelular() + "'");
            conex.rs.first();
            codf_tel2 = conex.rs.getInt("codf");
        } catch (SQLException ex) {
        }
        //////////////////////////////////////////////////////
        try {
            if (codf_tel == 0 && codf_tel2 == 0) {
                // DADOS DA CIDADE //////////////////////////////////////////////////////////
                conex.executaSQL("select * from cidade where nome='" + endereco.getCidade() + "' and uf='" + endereco.getEstado() + "'");
                conex.rs.first();
                codcd = conex.rs.getInt("codcd");
                ////////////////////////////////////////////////////////////////////////////////

                // DADOS DO FORNECEDOR /////////////////////////////////////////////////////////
                PreparedStatement pst = conex.conn.prepareStatement("insert into fornecedor(nome, cnpj, inscricao_estadual, email, "
                        + "website, rua, numero, bairro, cep, complemento, codcd) values(?,?,?,?,?,?,?,?,?,?,?)");
                pst.setString(1, fornecedor.getNome());
                pst.setString(2, fornecedor.getCnpj());
                pst.setString(3, fornecedor.getInscricao_estadual());
                pst.setString(4, fornecedor.getEmail());
                pst.setString(5, fornecedor.getWebsite());
                pst.setString(6, endereco.getRua());
                pst.setString(7, endereco.getNumero());
                pst.setString(8, endereco.getBairro());
                pst.setString(9, endereco.getCep());
                pst.setString(10, endereco.getComplemento());
                pst.setInt(11, codcd);
                pst.execute();

                conex.executaSQL("select * from fornecedor where nome='" + fornecedor.getNome() + "'");
                conex.rs.first();
                codf = conex.rs.getInt("codf");
                /////////////////////////////////////////////////////////////////////////////////

                // DADOS DO TELEFONE ////////////////////////////////////////////////////////////
                try {
                    pst = conex.conn.prepareStatement("insert into telefone(numero_tel, codf) values(?,?)");
                    pst.setString(1, fornecedor.getTelefone());
                    //pst.setString(2, "Residencial");
                    pst.setInt(2, codf);
                    pst.execute();
                } catch (SQLException ex) { //se houve exceção o telefone já existe e não pertence a outro fornecedor
                    pst = conex.conn.prepareStatement("update telefone set codf=? where numero_tel='" + fornecedor.getTelefone() + "'");
                    //conex.rs.first();
                    pst.setInt(1, codf);
                    pst.execute();
                }

                try {
                    pst = conex.conn.prepareStatement("insert into telefone(numero_tel, codf) values(?,?)");
                    pst.setString(1, fornecedor.getCelular());
                    //pst.setString(2, "Comercial");
                    pst.setInt(2, codf);
                    pst.execute();
                } catch (SQLException ex) { //se houve exceção o telefone já existe e não pertence a outro fornecedor
                    pst = conex.conn.prepareStatement("update telefone set codf=?  where numero_tel='" + fornecedor.getCelular() + "'");
                    //conex.rs.first();
                    pst.setInt(1, codf);
                    pst.execute();
                }
                //////////////////////////////////////////////////////////////////////////////////
                flag = true;
                JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao inserir o fornecedor!\nNúmero de telefone já pertence a outro fornecedor");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir o fornecedor!\n" + ex.getMessage().replaceAll("ERROR:", ""));
        }
        conex.desconecta();
        return flag;
    }

    public boolean alterar(CFornecedor fornecedor, CEndereco endereco) {
        boolean flag = false;
        int codcd, codf_tel = 0, codf_tel2 = 0;

        //Se não possui algum telefone
        if (fornecedor.getTelefone().equals("") || fornecedor.getCelular().equals("")) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar o fornecedor!\nOs telefones devem ser preenchidos.");
            return flag;
        }

        //Se os telefones forem iguais
        if (fornecedor.getTelefone().equals(fornecedor.getCelular())) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar o fornecedor!\nOs telefones devem ser diferentes.");
            return flag;
        }

        //Se não possui cidade
        if (endereco.getCidade() == null) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar o fornecedor!\nA cidade deve ser preenchida.");
            return flag;
        }

        //Se não possui estado
        if (endereco.getEstado() == null) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar o fornecedor!\nO estado deve ser preenchido.");
            return flag;
        }

        //Se não possui cidade
        if (endereco.getCidade().equals("")) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar o fornecedor!\nA cidade deve ser preenchida.");
            return flag;
        }

        //Se não possui estado
        if (endereco.getEstado().equals("")) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar o fornecedor!\nO estado deve ser preenchido.");
            return flag;
        }

        conex.conexao();

        //verifica se o telefone já existe em outro fornecedor
        try {
            conex.executaSQL("select * from telefone where numero_tel='" + fornecedor.getTelefone() + "'");
            conex.rs.first();
            codf_tel = conex.rs.getInt("codf");

            conex.executaSQL("select * from telefone where numero_tel='" + fornecedor.getCelular() + "'");
            conex.rs.first();
            codf_tel2 = conex.rs.getInt("codf");

        } catch (SQLException ex) {
        }
        //////////////////////////////////////////////////////
        try {
            if ((codf_tel == 0 || codf_tel == fornecedor.getCodf()) && (codf_tel2 == 0 || codf_tel2 == fornecedor.getCodf())) {
                // DADOS DA CIDADE //////////////////////////////////////////////////////////
                conex.executaSQL("select * from cidade where nome='" + endereco.getCidade() + "' and uf='" + endereco.getEstado() + "'");
                conex.rs.first();
                codcd = conex.rs.getInt("codcd");
                ////////////////////////////////////////////////////////////////////////////////

                // DADOS DO FORNECEDOR /////////////////////////////////////////////////////////
                PreparedStatement pst = conex.conn.prepareStatement("update fornecedor set nome=?, cnpj=?, inscricao_estadual=?, email=?, website=?, "
                        + "rua=?, numero=?, bairro=?, cep=?, complemento=?, codcd=? where codf=?");
                pst.setString(1, fornecedor.getNome());
                pst.setString(2, fornecedor.getCnpj());
                pst.setString(3, fornecedor.getInscricao_estadual());
                pst.setString(4, fornecedor.getEmail());
                pst.setString(5, fornecedor.getWebsite());
                pst.setString(6, endereco.getRua());
                pst.setString(7, endereco.getNumero());
                pst.setString(8, endereco.getBairro());
                pst.setString(9, endereco.getCep());
                pst.setString(10, endereco.getComplemento());
                pst.setInt(11, codcd);
                pst.setInt(12, fornecedor.getCodf());
                pst.execute();
                /////////////////////////////////////////////////////////////////////////////////

                // DADOS DO TELEFONE ////////////////////////////////////////////////////////////
                pst = conex.conn.prepareStatement("update telefone set codf=null where codf='" + fornecedor.getCodf() + "'");
                //conex.rs.first();
                //pst.setInt(1, fornecedor.getCodf());
                pst.execute();

                try {
                    pst = conex.conn.prepareStatement("insert into telefone(numero_tel, codf) values(?,?)");
                    pst.setString(1, fornecedor.getTelefone());
                    //pst.setString(2, "Residencial");
                    pst.setInt(2, fornecedor.getCodf());
                    pst.execute();
                } catch (SQLException ex) { //se houve exceção o telefone já existe e não pertence a outro fornecedor
                    pst = conex.conn.prepareStatement("update telefone set codf=? where numero_tel='" + fornecedor.getTelefone() + "'");
                    //conex.rs.first();
                    pst.setInt(1, fornecedor.getCodf());
                    pst.execute();
                }

                try {
                    pst = conex.conn.prepareStatement("insert into telefone(numero_tel, codf) values(?,?)");
                    pst.setString(1, fornecedor.getCelular());
                    //pst.setString(2, "Comercial");
                    pst.setInt(2, fornecedor.getCodf());
                    pst.execute();
                } catch (SQLException ex) { //se houve exceção o telefone já existe e não pertence a outro fornecedor
                    pst = conex.conn.prepareStatement("update telefone set codf=?  where numero_tel='" + fornecedor.getCelular() + "'");
                    //conex.rs.first();
                    pst.setInt(1, fornecedor.getCodf());
                    pst.execute();
                }
                //////////////////////////////////////////////////////////////////////////////////
                flag = true;
                JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao alterar o fornecedor!\nNúmero de telefone já pertence a outro fornecedor");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar o fornecedor!\n" + ex.getMessage().replaceAll("ERROR:", ""));
        }
        conex.desconecta();
        return flag;
    }

    public void excluir(CFornecedor fornecedor, CEndereco endereco) {
        String tel = null, cel = null;
        PreparedStatement pst;
        conex.conexao();
        try {
            conex.executaSQL("select * from view_fornecedor_detalhes where codf='" + fornecedor.getCodf() + "'");
            conex.rs.first();
            tel = conex.rs.getString("numero_tel");
            cel = conex.rs.getString("numero_cel");

            // deixa o telefone sem o código do fornecedor
            pst = conex.conn.prepareStatement("update telefone set codf=null where codf='" + fornecedor.getCodf() + "'");
            //conex.rs.first();
            //pst.setInt(1, fornecedor.getCodf());
            pst.execute();

            //exclui dados na tabela fornecedor
            pst = conex.conn.prepareStatement("delete from fornecedor where codf=?");
            pst.setInt(1, fornecedor.getCodf());
            pst.execute();

            JOptionPane.showMessageDialog(null, "Dados removidos com sucesso!");
        } catch (SQLException ex) {
            try {
                // desfaz as alterações
                pst = conex.conn.prepareStatement("update telefone set codf=? where numero_tel=?");
                pst.setInt(1, fornecedor.getCodf());
                pst.setString(2, tel);
                pst.execute();

                pst = conex.conn.prepareStatement("update telefone set codf=? where numero_tel=?");
                pst.setInt(1, fornecedor.getCodf());
                pst.setString(2, cel);
                pst.execute();
                JOptionPane.showMessageDialog(null, "Erro ao excluir o fornecedor!\nEste fornecedor está vinculado a um registro.");
            } catch (SQLException ex1) {
                //Logger.getLogger(ControleFornecedor.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }

        try {
            //se os telefones não pertencerem a outros fornecedores, então estes serão excluídos
            pst = conex.conn.prepareStatement("delete from telefone where numero_tel=? and codf is null");
            pst.setString(1, fornecedor.getTelefone());
            pst.execute();
        } catch (SQLException ex) {
        }

        try {
            //se os telefones não pertencerem a outros fornecedores, então estes serão excluídos
            pst = conex.conn.prepareStatement("delete from telefone where numero_tel=? and codf is null");
            pst.setString(1, fornecedor.getCelular());
            pst.execute();
        } catch (SQLException ex) {
        }

        conex.desconecta();
    }

}
