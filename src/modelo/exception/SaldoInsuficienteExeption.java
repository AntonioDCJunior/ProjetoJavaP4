package modelo.exception;

public class SaldoInsuficienteExeption extends Exception {

	public SaldoInsuficienteExeption(String mensagem){
		super(mensagem);
	}
	
	public String toString(){
		return "Saldo insuficiente para a realiza��o do d�bito !";
	}
}
