package br.unipe.mlp.banco.conta.modelo;

import java.io.IOException;
import java.util.List;

import br.unipe.mlp.banco.conta.dados.IRepositorioContas;
import br.unipe.mlp.banco.conta.dados.RepositoryContasHashSet;

public class ServicosConta {

	private IRepositorioContas contas;
	
	public ServicosConta() throws IOException {
		this.contas = new RepositoryContasHashSet();
	}
	
	public void cadastra(Conta conta){				
			contas.inserir(conta);		
	}
	
	public boolean atualizar(Conta conta){
		if(contas.existe(conta.getNumero())){
			contas.atualizar(conta);
			return true;
		}
		return false;
	}
	public Conta procurar(String numero){
		return contas.procurar(numero);
	}
	public Conta procurarNome(String nome){
		return ((RepositoryContasHashSet) contas).procurarNome(nome);
	}
	public boolean remover(String numero){
		Conta conta = contas.procurar(numero);
		if(conta != null){
			contas.remover(numero);
			return true;
		}				
		return false;
	}
	public boolean existe(String numero){
		return contas.existe(numero);
	}
	public String ListaContas(){
		return contas.listaContas();
	}


	public List<Conta> getListConta() {		
		return (List<Conta>) ((RepositoryContasHashSet) contas).getListContas();
	}
}
