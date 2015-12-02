package modelo.exception;

import javax.swing.JOptionPane;

public class ContaNaoEncontradaException extends Exception {
	
	public ContaNaoEncontradaException (String mensagem){
		super(JOptionPane.showInputDialog(mensagem));
	}
	

}
