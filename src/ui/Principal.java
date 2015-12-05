package ui;
import java.sql.SQLException;

import br.unipe.mlp.banco.conta.fachada.Banco;
import br.unipe.mlp.banco.conta.modelo.Conta;
import br.unipe.mlp.banco.conta.modelo.ContaCorrente;


public class Principal {

	public static void main(String[] args) throws SQLException {
		Banco banco = new Banco();
		
		
		Conta c1 = new ContaCorrente("Jo","100-7",300.0);
	
		banco.cadastraConta(c1);
		System.out.println(banco.listar());
	}	

}
