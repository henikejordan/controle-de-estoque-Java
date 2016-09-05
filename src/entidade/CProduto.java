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
public class CProduto {

    private int codp;
    private String cod_barras;
    private String nome;
    private int quantidade;
    private int estoque_minimo;
    private float preco_compra;
    private float preco_venda;
    private String validade;
    private String data;
    private String fornecedor;
    private String categoria;

    /**
     * @return the codp
     */
    public int getCodp() {
        return codp;
    }

    /**
     * @param codp the codp to set
     */
    public void setCodp(int codp) {
        this.codp = codp;
    }

    /**
     * @return the cod_barras
     */
    public String getCod_barras() {
        return cod_barras;
    }

    /**
     * @param cod_barras the cod_barras to set
     */
    public void setCod_barras(String cod_barras) {
        this.cod_barras = cod_barras;
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
     * @return the quantidade
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * @param quantidade the quantidade to set
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * @return the estoque_minimo
     */
    public int getEstoque_minimo() {
        return estoque_minimo;
    }

    /**
     * @param estoque_minimo the estoque_minimo to set
     */
    public void setEstoque_minimo(int estoque_minimo) {
        this.estoque_minimo = estoque_minimo;
    }

    /**
     * @return the preco_compra
     */
    public float getPreco_compra() {
        return preco_compra;
    }

    /**
     * @param preco_compra the preco_compra to set
     */
    public void setPreco_compra(float preco_compra) {
        this.preco_compra = preco_compra;
    }

    /**
     * @return the preco_venda
     */
    public float getPreco_venda() {
        return preco_venda;
    }

    /**
     * @param preco_venda the preco_venda to set
     */
    public void setPreco_venda(float preco_venda) {
        this.preco_venda = preco_venda;
    }

    /**
     * @return the validade
     */
    public String getValidade() {
        return validade;
    }

    /**
     * @param validade the validade to set
     */
    public void setValidade(String validade) {
        this.validade = validade;
    }

    /**
     * @return the data
     */
    public String getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * @return the fornecedor
     */
    public String getFornecedor() {
        return fornecedor;
    }

    /**
     * @param fornecedor the fornecedor to set
     */
    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    /**
     * @return the categoria
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

}
