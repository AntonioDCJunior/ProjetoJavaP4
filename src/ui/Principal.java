package ui;
import java.sql.SQLException;

import br.unipe.mlp.banco.conta.fachada.Banco;
import br.unipe.mlp.banco.conta.modelo.Conta;
import br.unipe.mlp.banco.conta.modelo.ContaCorrente;


public class Principal {

	public static void main(String[] args) throws SQLException {
		Banco banco = new Banco();
		
		
		Conta c1 = new ContaCorrente("Jo","100-7",300.0);
	//	banco.remover("158-7");
		banco.cadastraConta(c1);
	//	banco.alterar(c1);
		
		//sql.inserir("INSERT INTO Contas (Nome,Numero,Saldo) VALUES('"+c1.getNome()+"','"+c1.getNumero()+"','"+c1.getSaldo()+"')");
		//sql.inserir("DELETE FROM Contas");
		
		//System.out.println(sql.inserir("INSERT INTO Contas(Nome,Numero,Saldo) VALUES ('luiz','666-x',200.0)"));
//		ResultSet resul = sql.executar("SELECT nome,numero,saldo FROM Contas");
//		while(resul.next()){
//			System.out.println(resul.getString("Nome"));
//		}
		System.out.println(banco.listar());
	}	

}
