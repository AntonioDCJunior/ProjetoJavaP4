package br.unipe.mlp.banco.conta.fachada;

import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;

import br.unipe.mlp.banco.conta.modelo.Conta;
import br.unipe.mlp.banco.conta.modelo.ServicosConta;

public class Banco {
	
	private ServicosConta servicos;
	
	public Banco(){
		try {
			this.servicos = new ServicosConta();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void cadastraConta(Conta conta){
		servicos.cadastra(conta);
	}
	public void remover(String numero){
		servicos.remover(numero);
	}
	public void alterar(Conta conta){
		servicos.atualizar(conta);
	}
	public Conta buscarNome(String nome){
		return	servicos.procurarNome(nome);
	}
	public Conta buscarNumero(String numero){
		return servicos.procurar(numero);
	}
	public String listar(){
		return servicos.ListaContas();
	}
	public ArrayList<Conta> listTable(){
		return (ArrayList<Conta>) servicos.getListConta();
	}
}
