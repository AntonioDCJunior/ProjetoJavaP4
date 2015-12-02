package modelo.exception;

import javax.swing.JOptionPane;

public class ContaJaCadastradaException extends Exception {
	
	public ContaJaCadastradaException(String mensagem){
		super(JOptionPane.showInputDialog(mensagem));
	}
	

}
