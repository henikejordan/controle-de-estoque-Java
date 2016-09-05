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
public class CPrivilegios {

    private int codpv;
    private boolean logins;
    private boolean categorias;
    private boolean clientes;
    private boolean fornecedores;
    private boolean produtos;
    private boolean pesq_vendas;
    private boolean efetuar_vendas;
    private boolean relatorios;

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

    /**
     * @return the logins
     */
    public boolean isLogins() {
        return logins;
    }

    /**
     * @param logins the logins to set
     */
    public void setLogins(boolean logins) {
        this.logins = logins;
    }

    /**
     * @return the categorias
     */
    public boolean isCategorias() {
        return categorias;
    }

    /**
     * @param categorias the categorias to set
     */
    public void setCategorias(boolean categorias) {
        this.categorias = categorias;
    }

    /**
     * @return the clientes
     */
    public boolean isClientes() {
        return clientes;
    }

    /**
     * @param clientes the clientes to set
     */
    public void setClientes(boolean clientes) {
        this.clientes = clientes;
    }

    /**
     * @return the fornecedores
     */
    public boolean isFornecedores() {
        return fornecedores;
    }

    /**
     * @param fornecedores the fornecedores to set
     */
    public void setFornecedores(boolean fornecedores) {
        this.fornecedores = fornecedores;
    }

    /**
     * @return the produtos
     */
    public boolean isProdutos() {
        return produtos;
    }

    /**
     * @param produtos the produtos to set
     */
    public void setProdutos(boolean produtos) {
        this.produtos = produtos;
    }

    /**
     * @return the pesq_vendas
     */
    public boolean isPesq_vendas() {
        return pesq_vendas;
    }

    /**
     * @param pesq_vendas the pesq_vendas to set
     */
    public void setPesq_vendas(boolean pesq_vendas) {
        this.pesq_vendas = pesq_vendas;
    }

    /**
     * @return the efetuar_vendas
     */
    public boolean isEfetuar_vendas() {
        return efetuar_vendas;
    }

    /**
     * @param efetuar_vendas the efetuar_vendas to set
     */
    public void setEfetuar_vendas(boolean efetuar_vendas) {
        this.efetuar_vendas = efetuar_vendas;
    }

    /**
     * @return the relatorios
     */
    public boolean isRelatorios() {
        return relatorios;
    }

    /**
     * @param relatorios the relatorios to set
     */
    public void setRelatorios(boolean relatorios) {
        this.relatorios = relatorios;
    }

}
