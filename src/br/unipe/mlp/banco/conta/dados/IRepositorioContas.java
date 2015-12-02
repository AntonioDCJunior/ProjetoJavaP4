package br.unipe.mlp.banco.conta.dados;

import br.unipe.mlp.banco.conta.modelo.Conta;

public interface IRepositorioContas {
	void inserir(Conta conta);
	Conta procurar(String numero);
	void remover(String numero);
	void  atualizar(Conta conta);
	boolean existe(String numero);
	String listaContas();
}
