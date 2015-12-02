package br.unipe.mlp.banco.conta.dados;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.unipe.mlp.banco.conta.modelo.Conta;
import br.unipe.mlp.banco.conta.modelo.ContaCorrente;

public class RepositoryContasHashSet implements IRepositorioContas {
	
	private Set<Conta> Contas;
	private SqlServer sqlBanco;
	private PropriedadesSqlServer configSql;
	private static String tabela = "Contas";
	
	public RepositoryContasHashSet() throws IOException {
	this.Contas = new HashSet<Conta>();
	configSql = new PropriedadesSqlServer();
	sqlBanco = new SqlServer(configSql.getUrl(), configSql.getDatabase(),
				   configSql.getUsuario(), configSql.getSenha(),configSql.getPorta());
	getDadosBancoDB();
	}
	
	public void inserir(Conta conta){		
		if(!Contas.contains(conta)){
			sqlBanco.conectar();
			Contas.add(conta);
			inserirContaSql(tabela, conta.getNome(), conta.getNumero(), conta.getSaldo());
			sqlBanco.desconectar();
		}
		
	}
	public Conta  procurar(String numero){
		for(Conta conta : Contas){
			if(conta.getNumero().equals(numero)){
				return conta;
			}
		}
		return null;
	}
	public void  remover(String numero){
		Conta conta = procurar(numero);
		if(conta!=null){			
			sqlBanco.conectar();
			Contas.remove(conta);  
			removerContaSql(tabela, numero);
			sqlBanco.desconectar();
		}
	}
	public void  atualizar(Conta conta){
		for(Conta cont : Contas){
			if(cont.getNumero().equals(conta.getNumero())){
				cont = conta;
				sqlBanco.conectar();				
				updateContaSql(tabela, conta.getNome(), conta.getNumero(), conta.getSaldo());
				sqlBanco.desconectar();
			}
		}		
	}
	public boolean existe(String numero){
		for(Conta conta : Contas){
			if(conta.getNumero().equals(numero)){
				return true;
			}
		}
		return false;
	}
	public Conta procurarNome(String nome){
		for(Conta conta : Contas){
			if(conta.getNome().equals(nome)){
				return conta;
			}
		}
		return null;
	}
	public String listaContas(){
		String strLista = "";
		for(Conta conta : Contas){
			strLista += conta;
		}
		return strLista;
	}
	public List<Conta> getListContas(){
		ArrayList<Conta> contasTabela = new ArrayList<Conta>();
		for(Conta conta : Contas){
			contasTabela.add(conta);
		}
		Collections.sort(contasTabela);
			return contasTabela;
	}
	public void getDadosBancoDB(){
		sqlBanco.conectar();
		ResultSet dados = sqlBanco.executar("SELECT * FROM Contas");
		
		
		try {
			while(dados.next()){
				ContaCorrente conta = new ContaCorrente();
				conta.setNome(dados.getString(2)); 
				conta.setNumero(dados.getString(3));
				conta.setSaldo(dados.getDouble(4));
				Contas.add(conta);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		sqlBanco.desconectar();
	}
	
	private int inserirContaSql(String tabela,String nome,String numero,double saldo){
		return sqlBanco.inserir("INSERT INTO Contas (Nome,Numero,Saldo) VALUES('"+nome+"','"+numero+"','"+saldo+"')");
	}
	private int removerContaSql(String tabela,String numero){
		return sqlBanco.inserir("DELETE FROM Contas WHERE Numero='"+numero+"'");
	}
	private int updateContaSql(String tabela,String nome,String numero,double saldo){
		return sqlBanco.inserir("UPDATE "+tabela+" SET Nome = '"+nome+"', Saldo = '"+saldo+"' WHERE Numero = '"+numero+"'");
	}
}
