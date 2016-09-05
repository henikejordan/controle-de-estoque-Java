/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import controle.ControleVenda;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Henike
 */
public class ModeloTabelaItensVenda extends AbstractTableModel {

    private ArrayList linhas = null;
    private String[] colunas = null;

    public ModeloTabelaItensVenda(ArrayList lin, String[] col) {
        this.setLinhas(lin);
        this.setColunas(col);
    }

    public ArrayList getLinhas() {
        return linhas;
    }

    private void setLinhas(ArrayList linhas) {
        this.linhas = linhas;
    }

    public String[] getColunas() {
        return colunas;
    }

    private void setColunas(String[] colunas) {
        this.colunas = colunas;
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public int getRowCount() {
        return linhas.size();
    }

    @Override
    public String getColumnName(int numCol) {
        return colunas[numCol];
    }

    @Override
    public Object getValueAt(int numLin, int numCol) {
        Object[] linha = (Object[]) getLinhas().get(numLin);
        return linha[numCol];
    }

    @Override
    public void setValueAt(Object obj, int rowIndex, int columnIndex) {

        //Object categoria = linhas.get(rowIndex);
        //categoria.setName(String.valueOf(obj));
        ControleVenda controle = new ControleVenda();
        try {
            int quantidade = Integer.parseInt(String.valueOf(obj));
            String produto = String.valueOf(this.getValueAt(rowIndex, columnIndex - 1));
            if (quantidade > 0) {
                controle.alterarItem(quantidade, produto);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao alterar o produto!\nQuantidade inválida.");
            }

        } catch (NumberFormatException ex) {

        }

        this.fireTableDataChanged();
        this.fireTableCellUpdated(rowIndex, columnIndex);

    }

    @Override
    public boolean isCellEditable(int row, int column) { // custom isCellEditable function
        return column == 1;
    }

    public void setCellEditable(int row, int col, boolean value) {
        //this.editable_cells[row][col] = value; // set cell true/false
        this.fireTableCellUpdated(row, col);
    }

}
