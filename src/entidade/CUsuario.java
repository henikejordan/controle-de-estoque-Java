/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

/**
 *
 * @author Henike
 */
public class CUsuario {

    private int codu;
    private String nome;
    private String login;
    private String senha;
    private int codpv;

    /**
     * @return the codu
     */
    public int getCodu() {
        return codu;
    }

    /**
     * @param codu the codu to set
     */
    public void setCodu(int codu) {
        this.codu = codu;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * @return the codpv
     */
    public int getCodpv() {
        return codpv;
    }

    /**
     * @param codpv the codpv to set
     */
    public void setCodpv(int codpv) {
        this.codpv = codpv;
    }

}
