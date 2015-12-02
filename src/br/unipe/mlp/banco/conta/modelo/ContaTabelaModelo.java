package br.unipe.mlp.banco.conta.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

//import br.unipe.cc.banco.model.Conta;

public class ContaTabelaModelo extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
    private static final int COL_NOME = 0;
    private static final int COL_NUMERO = 1;
    private static final int COL_SALDO = 2;
    
    private List<Conta> valores;
    
    public ContaTabelaModelo(List<Conta> valores) {
		this.valores = valores;
	}
    
    public int getColumnCount() {		
		return 3;
	}
    
	public String getColumnName(int column) { 	        
	   
	    if (column == COL_NOME) return "Nome";
	    if (column == COL_NUMERO) return "Numero de Conta";
	    if (column == COL_SALDO) return "Saldo";	   
	    
	    return "";   
	}
	
	public Object getValueAt(int row, int column) {  
        
		Conta mod = valores.get(row);  
        if (column == COL_NOME) return mod.getNome();
        else if (column == COL_NUMERO) return mod.getNumero();
        else if (column == COL_SALDO) return mod.getSaldo();
        return "";  
    }
	public TableCellRenderer CellRendererPane(){
		DefaultTableCellRenderer def = new DefaultTableCellRenderer();
		def.setHorizontalAlignment(0);
		return def;
	}
	
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case COL_NOME:
			valores.get(rowIndex).setNome((String) aValue);
			break;
		case COL_NUMERO:
			valores.get(rowIndex).setNumero((String) aValue);
			break;
		case COL_SALDO:
			valores.get(rowIndex).setSaldo((Double) aValue);
			break;
		}	
		
	} //Metodo se permitir que se altere as celulas
	 
	 public Class<?> getColumnClass(int columnIndex) {   
        return String.class;  
    }
	 
	 public boolean isCellEditable(int rowIndex, int columnIndex) {    
        return false;  
    }
	 
	 public Conta get(int row) {  
        return valores.get(row);  
    }

	public int getRowCount() {
	
		return valores.size();
	}
    
	public void removeRow(int row){
		valores.remove(row);
	}
	

}
