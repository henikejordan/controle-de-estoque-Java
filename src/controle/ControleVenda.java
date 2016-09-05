/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import entidade.CVenda;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import util.ConectaBanco;

/**
 *
 * @author Henike
 */
public class ControleVenda {

    public ConectaBanco conex = ConectaBanco.getInstance();
    private int codc, codu, codfp;

    public void inserirItem(CVenda venda) {
        conex.conexao();
        try {
            PreparedStatement pst = conex.conn.prepareStatement("insert into itens_venda_produto(codv, codp, qtde_produto, preco_venda) values (?,?,?,?)");
            pst.setInt(1, venda.getCodv());
            pst.setInt(2, this.achaCodProduto(venda.getNome_produto()));
            pst.setInt(3, venda.getQtde_item());
            pst.setFloat(4, venda.getPreco_venda());
            pst.execute();

            //JOptionPane.showMessageDialog(null, "Produto adicionado com sucesso!");
        } catch (SQLException ex) {
            if (ex.getMessage().length() >= 0 && ex.getMessage().length() <= 70) {
                JOptionPane.showMessageDialog(null, "Erro ao adicionar o produto!\nQuantidade inválida.");
            } else if (ex.getMessage().length() > 70 && ex.getMessage().length() < 150) {
                JOptionPane.showMessageDialog(null, "Erro ao adicionar o produto!\nProduto já adicionado na venda.");
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao adicionar o produto!\nQuantidade indisponível.");
            }
        }
        conex.desconecta();
    }

    public void alterarItem(int quantidade, String produto) {
        conex.conexao();
        try {
            PreparedStatement pst = conex.conn.prepareStatement("update itens_venda_produto set qtde_produto=? where codp=? and codv=(select max(codv) from venda)");
            pst.setInt(1, quantidade);
            pst.setInt(2, this.achaCodProduto(produto));
            pst.execute();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar o produto!\nQuantidade indisponível.");
        }
        conex.desconecta();
    }

    public void excluirItem(CVenda venda) {
        conex.conexao();
        try {
            PreparedStatement pst = conex.conn.prepareStatement("delete from itens_venda_produto where codv=? and codp=?");
            pst.setInt(1, venda.getCodv());
            pst.setInt(2, this.achaCodProduto(venda.getNome_produto()));
            pst.execute();

            //JOptionPane.showMessageDialog(null, "Produto removido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao remover o produto!\n" + ex.getMessage().replaceAll("ERROR:", ""));
        }
        conex.desconecta();
    }

    private int achaCodProduto(String nome) {
        int codp = 0;
        conex.conexao();
        conex.executaSQL("select * from produto where nome='" + nome + "'");
        try {
            conex.rs.first();
            codp = conex.rs.getInt("codp");
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Erro ao achar o produto!\n");
        }
        conex.desconecta();
        return codp;
    }

    public boolean fechaVenda(CVenda venda) {
        boolean ver = false;
        PreparedStatement pst;
        if (!this.achaCliente(venda.getNome_cliente())) {
            return ver;
        }
        if (!this.achaLogin(venda.getLogin())) {
            return ver;
        }
        if (!this.achaFormaPagamento(venda.getForma_pagamento())) {
            return ver;
        }
        if ("À prazo".equals(venda.getForma_pagamento())
                && (venda.getNum_parcelas() > 100 || venda.getNum_parcelas() < 1)) {
            JOptionPane.showMessageDialog(null, "Erro ao finalizar a venda!\nNúmero de parcelas não permitida.");
            return ver;
        }
        if ("À vista".equals(venda.getForma_pagamento()) && venda.getTotal_compra() == 0) {
            JOptionPane.showMessageDialog(null, "Erro ao finalizar a venda!\nA compra deve conter produtos.");
            return ver;
        }
        if (venda.getTotal() == 0) {
            JOptionPane.showMessageDialog(null, "Erro ao finalizar a venda!\nO TOTAL da compra deve ser positivo.");
            return ver;
        }
        conex.conexao();
        try {
            pst = conex.conn.prepareStatement("update venda set data=?, hora=?, total_compra=?, descontos=?, "
                    + "total=?, total_recebido=?, codc=?, codu=?, codfp=? where codv=?");

            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            format.setLenient(false);
            java.sql.Date dataVenda = new java.sql.Date(format.parse(venda.getData()).getTime());
            pst.setDate(1, dataVenda);

            Date date = new Date();
            StringBuilder data = new StringBuilder();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            Time time = new Time(sdf.parse(data.toString() + sdf.format(date)).getTime());
            pst.setTime(2, time);
            BigDecimal totalcompra = new BigDecimal(venda.getTotal_compra()).setScale(2, BigDecimal.ROUND_HALF_UP);
            pst.setBigDecimal(3, totalcompra);
            BigDecimal descontos = new BigDecimal(venda.getDescontos()).setScale(2, BigDecimal.ROUND_HALF_UP);
            pst.setBigDecimal(4, descontos);
            BigDecimal total = new BigDecimal(venda.getTotal()).setScale(2, BigDecimal.ROUND_HALF_UP);
            pst.setBigDecimal(5, total);
            BigDecimal totalrecebido = new BigDecimal(venda.getTotal_recebido()).setScale(2, BigDecimal.ROUND_HALF_UP);
            pst.setBigDecimal(6, totalrecebido);
            pst.setInt(7, codc);
            pst.setInt(8, codu);
            pst.setInt(9, codfp);
            pst.setInt(10, venda.getCodv());
            pst.execute();

            ver = true;
            //JOptionPane.showMessageDialog(null, "Venda finalizada!");

            if (venda.getForma_pagamento().equals("À prazo")) {
                String dp, dia, partes[] = venda.getData().split(Pattern.quote("/"));
                int mes = Integer.parseInt(partes[1]) + 1, i = 1;

                if (venda.getDia_venc() < 10) {
                    dia = "" + venda.getDia_venc();
                } else {
                    dia = "0" + venda.getDia_venc();
                }

                // PRIMEIRA PARCELA!!!!!
                if (mes == 13) {
                    int ano = Integer.parseInt(partes[2]) + 1;
                    partes[2] = "" + ano;
                    mes = 1;
                }
                if (mes >= 10) {
                    dp = mes + "/" + dia + "/" + partes[2];
                } else {
                    dp = "0" + mes + "/" + dia + "/" + partes[2];
                }

                // pega data do sistema
                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                Date hoje = new Date(dp);
                java.sql.Date dataParc = new java.sql.Date(df.parse(df.format(hoje)).getTime());

                pst = conex.conn.prepareStatement("insert into parcela_venda(codv, data_vencimento, qtde_parcelas, valor, num_parcela) values(?,?,?,?,?)");
                pst.setInt(1, venda.getCodv());
                pst.setDate(2, dataParc);
                pst.setInt(3, venda.getNum_parcelas());
                pst.setFloat(4, venda.getValor_parcela() - venda.getDif_primeira_parcela());
                pst.setInt(5, i);
                pst.execute();
                mes++;
                i++;
                ///////////////////////

                while (i <= venda.getNum_parcelas()) {
                    if (mes == 13) {
                        int ano = Integer.parseInt(partes[2]) + 1;
                        partes[2] = "" + ano;
                        mes = 1;
                    }
                    if (mes >= 10) {
                        dp = mes + "/" + dia + "/" + partes[2];
                    } else {
                        dp = "0" + mes + "/" + dia + "/" + partes[2];
                    }

                    // pega data do sistema
                    df = new SimpleDateFormat("dd/MM/yyyy");
                    hoje = new Date(dp);
                    dataParc = new java.sql.Date(df.parse(df.format(hoje)).getTime());

                    pst = conex.conn.prepareStatement("insert into parcela_venda(codv, data_vencimento, qtde_parcelas, valor, num_parcela) values(?,?,?,?,?)");
                    pst.setInt(1, venda.getCodv());
                    pst.setDate(2, dataParc);
                    pst.setInt(3, venda.getNum_parcelas());
                    pst.setFloat(4, venda.getValor_parcela());
                    pst.setInt(5, i);
                    pst.execute();
                    mes++;
                    i++;
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao finalizar a venda!!\n" + ex.getMessage().replaceAll("ERROR:", ""));
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao finalizar a venda!!\nData da venda inválida");
        }
        conex.desconecta();
        return ver;
    }

    private boolean achaCliente(String nome) {
        boolean val = false;
        conex.conexao();
        try {
            conex.executaSQL("select * from cliente where nome='" + nome + "'");
            conex.rs.first();
            codc = conex.rs.getInt("codc");
            val = true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao achar o cliente!\nPor favor digite o nome de cliente corretamente.");
        }
        conex.desconecta();
        return val;
    }

    private boolean achaLogin(String login) {
        boolean val = false;
        conex.conexao();
        try {
            conex.executaSQL("select * from usuario where login='" + login + "'");
            conex.rs.first();
            codu = conex.rs.getInt("codu");
            val = true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao achar o login!\n" + ex.getMessage().replaceAll("ERROR:", ""));
        }
        conex.desconecta();
        return val;
    }

    private boolean achaFormaPagamento(String forma) {
        boolean val = false;
        conex.conexao();
        try {
            conex.executaSQL("select * from forma_pagamento where tipo='" + forma + "'");
            conex.rs.first();
            codfp = conex.rs.getInt("codfp");
            val = true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao achar a forma de pagamento!\n" + ex.getMessage().replaceAll("ERROR:", ""));
        }
        conex.desconecta();
        return val;
    }

    public void cancelaVenda() {
        conex.conexao();
        PreparedStatement pst;
        conex.executaSQL("select * from venda where total=0");
        try {
            conex.rs.first();
            do {
                pst = conex.conn.prepareStatement("delete from itens_venda_produto where codv=?");
                pst.setInt(1, conex.rs.getInt("codv"));
                pst.execute();
            } while (conex.rs.next());

        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Erro ao cancelar a venda!\nERRO: "+ex);
        }

        try {
            pst = conex.conn.prepareStatement("delete from venda where total=?");
            pst.setInt(1, 0);
            pst.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cancelar a venda!\n" + ex.getMessage().replaceAll("ERROR:", ""));
        }
        conex.desconecta();
    }
}
