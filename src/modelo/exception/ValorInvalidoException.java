package modelo.exception;

public class ValorInvalidoException extends Exception {
	
	public ValorInvalidoException(String mensagem){
		super(mensagem);
	}

	public String toString(){
		return"O valor que voc� forneceu � inv�lido para a opera��o.Opera��o � v�lida para valores acima de R$0.00";
	}
}
