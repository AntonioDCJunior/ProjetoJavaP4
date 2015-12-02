package modelo.exception;

public class ValorInvalidoException extends Exception {
	
	public ValorInvalidoException(String mensagem){
		super(mensagem);
	}

	public String toString(){
		return"O valor que você forneceu é inválido para a operação.Operação é válida para valores acima de R$0.00";
	}
}
