/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

// Padrão de projeto Singleton
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Henike
 */
public final class ConectaBanco {

    // Objeto a ser retornado por getInstance
    private static final ConectaBanco CONECTA = new ConectaBanco();

    public Statement stm; //responsável por preparar e realizar pesquisas no BD
    public ResultSet rs; //responsável por armazenar o resultado de uma pesquisa passada para o statement
    private final String driver = "org.postgresql.Driver"; //responsável por identificar o serviço de BD
    private final String caminho = "jdbc:postgresql://localhost:5432/"; //responsável por setar o local do BD
    private final String usuario = "postgres";
    private final String senha = "12345";
    public Connection conn; //responsável por realizar a conexão com o BD

    // Construtor privado que impede a instanciação pelos clientes
    private ConectaBanco() {
        System.err.println("Singleton object created");
    }

    // Retorna o objeto ConectaBancoSingleton estático
    public static ConectaBanco getInstance() {
        return CONECTA;
    }

    public void conexao() { //método responsável por realizar a conexão com o BD  
        try {//tentativa inicial
            System.setProperty("jdbc.Drivers", driver); //seta a propriedade do driver de conexão
            conn = DriverManager.getConnection(caminho, usuario, senha); //realiza a conexão com o BD
            //JOptionPane.showMessageDialog(null, "Conectado com sucesso!");
        } catch (SQLException ex) {//exceção
            JOptionPane.showMessageDialog(null, "Erro de conexão!\n Erro:" + ex.getMessage());
        }
    }

    public void executaSQL(String sql) {
        try {
            stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stm.executeQuery(sql);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro no executaSQL!\n Erro:" + ex.getMessage());
        }
    }

    public void desconecta() {// método para fechar a conexão com o BD
        try {
            conn.close();// fecha a conexão
            //JOptionPane.showMessageDialog(null, "Desconectado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão!\n Erro:" + ex.getMessage());
        }
    }
}
