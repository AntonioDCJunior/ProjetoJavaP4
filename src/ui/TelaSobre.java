package ui;

import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class TelaSobre {
	
	JDialog telaCadDialog;
	
	
	SpringLayout sl = new SpringLayout();
	JPanel t_Cad = new JPanel();


  public TelaSobre(JFrame pai){
	telaCadDialog = new JDialog(pai,"Sobre",true);
	
	mostrarTela();
	
  }

  private void mostrarTela() {
	
	sobre();  
	montarTela();
	
   }
  private void sobre(){
	  
	  //onde tem "insira o texto aqui vc vai escreve o sobre"
	  JLabel texto = new JLabel("Desenvolvedores:");	  		
	  JLabel texto1 = new JLabel("Antonio Júnior");
	  JLabel texto2 = new JLabel("Flávio Henrique");
	  JLabel texto3 = new JLabel("Iago Lima");
	  JLabel texto4 = new JLabel("Matheus Leão");
	  JLabel texto5 = new JLabel("Curso: Ciência da Comutação - 2015.2");
	  JLabel texto6 = new JLabel("Cadeira: Metodologia e Linguagem de Programação Avançada");
	  
	  sl.putConstraint(SpringLayout.NORTH, texto, 20, SpringLayout.NORTH, t_Cad);
	  sl.putConstraint(SpringLayout.WEST, texto, 10, SpringLayout.WEST, t_Cad);
	  
	  sl.putConstraint(SpringLayout.NORTH, texto1, 45, SpringLayout.NORTH, t_Cad);
	  sl.putConstraint(SpringLayout.WEST, texto1, 10, SpringLayout.WEST, t_Cad);
	  
	  sl.putConstraint(SpringLayout.NORTH, texto2, 65, SpringLayout.NORTH, t_Cad);
	  sl.putConstraint(SpringLayout.WEST, texto2, 10, SpringLayout.WEST, t_Cad);
	  
	  sl.putConstraint(SpringLayout.NORTH, texto3, 85, SpringLayout.NORTH, t_Cad);
	  sl.putConstraint(SpringLayout.WEST, texto3, 10, SpringLayout.WEST, t_Cad);
	  
	  sl.putConstraint(SpringLayout.NORTH, texto4, 105, SpringLayout.NORTH, t_Cad);
	  sl.putConstraint(SpringLayout.WEST, texto4, 10, SpringLayout.WEST, t_Cad);
	  
	  sl.putConstraint(SpringLayout.NORTH, texto5, 145, SpringLayout.NORTH, t_Cad);
	  sl.putConstraint(SpringLayout.WEST, texto5, 10, SpringLayout.WEST, t_Cad);
	  
	  sl.putConstraint(SpringLayout.NORTH, texto6, 165, SpringLayout.NORTH, t_Cad);
	  sl.putConstraint(SpringLayout.WEST, texto6, 10, SpringLayout.WEST, t_Cad);
	  
	  
	  t_Cad.add(texto);
	  t_Cad.add(texto1);
	  t_Cad.add(texto2);
	  t_Cad.add(texto3);
	  t_Cad.add(texto4);
	  t_Cad.add(texto5);
	  t_Cad.add(texto6);
	 
  }
  
  private void montarTela() {
		
		telaCadDialog.setSize(400, 230);
		telaCadDialog.setLayout(new FlowLayout());
		t_Cad.setLayout(sl);
		telaCadDialog.setContentPane(t_Cad);
		t_Cad.setSize(500, 500);
		telaCadDialog.setResizable(false);
		telaCadDialog.setLocationRelativeTo(null);
		telaCadDialog.setVisible(true);	
	}
}