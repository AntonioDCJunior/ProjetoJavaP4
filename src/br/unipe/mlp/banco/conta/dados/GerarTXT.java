package br.unipe.mlp.banco.conta.dados;
import br.unipe.mlp.banco.conta.modelo.Conta;
import br.unipe.mlp.banco.conta.modelo.ContaTabelaModelo;
import br.unipe.mlp.banco.conta.modelo.ContaCorrente;
import br.unipe.mlp.banco.conta.fachada.Banco;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.table.TableModel;



public class GerarTXT {
	 private Banco banco;


    public GerarTXT(ArrayList<Conta>contasLista){
    	banco = new Banco();
    	File file = new File("C:/Users/A.Jr/Desktop/Contas.txt");
    	    
	try{
		
		FileWriter fw = new FileWriter(file,true);
		PrintWriter pw = new PrintWriter(fw);
	    
		pw.print(banco.listar());		
		
		fw.close();
		pw.close();
		
	}catch(Exception e){
		System.out.println("Erro ao criar arquivo!");
	}
	
 }

}