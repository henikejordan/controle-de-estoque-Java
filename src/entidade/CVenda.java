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
public class CVenda {

    private int codv;
    private String data;
    private String hora;
    private float total_compra;
    private float descontos;
    private float total;
    private float total_recebido;
    private int qtde_item;
    private float preco_venda; // preco do produto vendido!!!!!
    private String nome_cliente;
    private String nome_produto;
    private String login;
    private String forma_pagamento;
    private int num_parcelas;
    private float juro_parcela;
    private float valor_parcela;
    private float dif_primeira_parcela;
    private int dia_venc;

    /**
     * @return the codv
     */
    public int getCodv() {
        return codv;
    }

    /**
     * @param codv the codv to set
     */
    public void setCodv(int codv) {
        this.codv = codv;
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
     * @return the hora
     */
    public String getHora() {
        return hora;
    }

    /**
     * @param hora the hora to set
     */
    public void setHora(String hora) {
        this.hora = hora;
    }

    /**
     * @return the total_compra
     */
    public float getTotal_compra() {
        return total_compra;
    }

    /**
     * @param total_compra the total_compra to set
     */
    public void setTotal_compra(float total_compra) {
        this.total_compra = total_compra;
    }

    /**
     * @return the descontos
     */
    public float getDescontos() {
        return descontos;
    }

    /**
     * @param descontos the descontos to set
     */
    public void setDescontos(float descontos) {
        this.descontos = descontos;
    }

    /**
     * @return the total
     */
    public float getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(float total) {
        this.total = total;
    }

    /**
     * @return the total_recebido
     */
    public float getTotal_recebido() {
        return total_recebido;
    }

    /**
     * @param total_recebido the total_recebido to set
     */
    public void setTotal_recebido(float total_recebido) {
        this.total_recebido = total_recebido;
    }

    /**
     * @return the qtde_item
     */
    public int getQtde_item() {
        return qtde_item;
    }

    /**
     * @param qtde_item the qtde_item to set
     */
    public void setQtde_item(int qtde_item) {
        this.qtde_item = qtde_item;
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
     * @return the nome_cliente
     */
    public String getNome_cliente() {
        return nome_cliente;
    }

    /**
     * @param nome_cliente the nome_cliente to set
     */
    public void setNome_cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
    }

    /**
     * @return the nome_produto
     */
    public String getNome_produto() {
        return nome_produto;
    }

    /**
     * @param nome_produto the nome_produto to set
     */
    public void setNome_produto(String nome_produto) {
        this.nome_produto = nome_produto;
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
     * @return the forma_pagamento
     */
    public String getForma_pagamento() {
        return forma_pagamento;
    }

    /**
     * @param forma_pagamento the forma_pagamento to set
     */
    public void setForma_pagamento(String forma_pagamento) {
        this.forma_pagamento = forma_pagamento;
    }

    /**
     * @return the num_parcelas
     */
    public int getNum_parcelas() {
        return num_parcelas;
    }

    /**
     * @param num_parcelas the num_parcelas to set
     */
    public void setNum_parcelas(int num_parcelas) {
        this.num_parcelas = num_parcelas;
    }

    /**
     * @return the juro_parcela
     */
    public float getJuro_parcela() {
        return juro_parcela;
    }

    /**
     * @param juro_parcela the juro_parcela to set
     */
    public void setJuro_parcela(float juro_parcela) {
        this.juro_parcela = juro_parcela;
    }

    /**
     * @return the valor_parcela
     */
    public float getValor_parcela() {
        return valor_parcela;
    }

    /**
     * @param valor_parcela the valor_parcela to set
     */
    public void setValor_parcela(float valor_parcela) {
        this.valor_parcela = valor_parcela;
    }

    /**
     * @return the dif_primeira_parcela
     */
    public float getDif_primeira_parcela() {
        return dif_primeira_parcela;
    }

    /**
     * @param dif_primeira_parcela the dif_primeira_parcela to set
     */
    public void setDif_primeira_parcela(float dif_primeira_parcela) {
        this.dif_primeira_parcela = dif_primeira_parcela;
    }

    /**
     * @return the dia_venc
     */
    public int getDia_venc() {
        return dia_venc;
    }

    /**
     * @param dia_venc the dia_venc to set
     */
    public void setDia_venc(int dia_venc) {
        this.dia_venc = dia_venc;
    }

}
