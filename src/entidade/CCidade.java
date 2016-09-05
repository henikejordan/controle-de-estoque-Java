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
public class CCidade {

    private int codcd;
    private String uf;
    private String nome;

    /**
     * @return the codcd
     */
    public int getCodcd() {
        return codcd;
    }

    /**
     * @param codcd the codcd to set
     */
    public void setCodcd(int codcd) {
        this.codcd = codcd;
    }

    /**
     * @return the uf
     */
    public String getUf() {
        return uf;
    }

    /**
     * @param uf the uf to set
     */
    public void setUf(String uf) {
        this.uf = uf;
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

}
