package ui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import br.unipe.mlp.banco.conta.fachada.Banco;
import br.unipe.mlp.banco.conta.modelo.Conta;
import br.unipe.mlp.banco.conta.modelo.ContaCorrente;


public class TelaCadastro {

	JDialog telaCadDialog;
	JTextField txtnome = new JTextField(6);
	JTextField txtnome2 = new JTextField(15);
	JTextField txtnome3 = new JTextField(15);
	SpringLayout sl = new SpringLayout();
	JPanel t_Cad = new JPanel();
	Banco banco = new Banco();
	
	
	public TelaCadastro(JFrame pai){
		telaCadDialog = new JDialog(pai,"Cadastro Conta",true);		
		mostrarTela();
		
		
	}
	
	private void mostrarTela() {
		
		cadastroNome();
		cadastroConta();
		cadastroSaldo();
		botaoConcluido();
		montarTela();
		
	}

	
	private void cadastroNome() {
		
		JLabel nome = new JLabel("NOME COMPLETO: ");
		sl.putConstraint(SpringLayout.NORTH, nome, 40, SpringLayout.NORTH, t_Cad);
		sl.putConstraint(SpringLayout.WEST, nome, 10, SpringLayout.WEST, t_Cad);
		sl.putConstraint(SpringLayout.NORTH, txtnome2,-4, SpringLayout.NORTH, nome);
		sl.putConstraint(SpringLayout.WEST, txtnome2, 13, SpringLayout.EAST, nome);
		t_Cad.add(nome);
		t_Cad.add(txtnome2);
		
	}
	
	private void cadastroConta() {
		
		JLabel cpf = new JLabel("CONTA:");
		sl.putConstraint(SpringLayout.NORTH, cpf, 75, SpringLayout.NORTH, t_Cad);
		sl.putConstraint(SpringLayout.WEST, cpf, 36, SpringLayout.WEST, t_Cad);
		t_Cad.add(cpf);
		sl.putConstraint(SpringLayout.NORTH, txtnome,-2, SpringLayout.NORTH, cpf);
		sl.putConstraint(SpringLayout.WEST, txtnome, 52, SpringLayout.EAST, cpf);
		t_Cad.add(txtnome);
		
	}		
	

	
	private void cadastroSaldo() {
		
		JLabel tipo = new JLabel("SALDO: ");
		sl.putConstraint(SpringLayout.NORTH, tipo, 110, SpringLayout.NORTH, t_Cad);
		sl.putConstraint(SpringLayout.WEST, tipo, 37, SpringLayout.WEST, t_Cad);
		t_Cad.add(tipo);
		sl.putConstraint(SpringLayout.NORTH, txtnome3, 1, SpringLayout.NORTH, tipo);
		sl.putConstraint(SpringLayout.WEST, txtnome3, 47, SpringLayout.EAST, tipo);
		t_Cad.add(txtnome3);
				
	}
	
	private void botaoConcluido() {
		
	
		JButton botao = new JButton("Concluido");t_Cad.add(botao);		
		sl.putConstraint(SpringLayout.SOUTH, botao, -20, SpringLayout.SOUTH, t_Cad);
		sl.putConstraint(SpringLayout.WEST, botao, 120, SpringLayout.WEST, t_Cad);
		t_Cad.add(botao);
	
		botao.addActionListener(new ActionListener() {
	
		public void actionPerformed(ActionEvent e) {
			Conta c = new ContaCorrente(txtnome2.getText(),txtnome.getText(),Integer.parseInt(txtnome3.getText()));
			banco.cadastraConta(c);
			System.out.println(banco.listar());
			JOptionPane.showMessageDialog(null, "Cadastro Concluido");
						
			System.exit(0);
		}
		
	});
	}
	
	
	
	private void montarTela() {
	
		telaCadDialog.setSize(350, 300);
		telaCadDialog.setLayout(new FlowLayout());
		t_Cad.setLayout(sl);
		telaCadDialog.setContentPane(t_Cad);
		t_Cad.setSize(500, 500);
		telaCadDialog.setResizable(false);
		telaCadDialog.setLocationRelativeTo(null);
		telaCadDialog.setVisible(true);	
	}
	
	
}
