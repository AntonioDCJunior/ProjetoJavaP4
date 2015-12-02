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


public class TelaRemover {

	JTextField txtnumero = new JTextField(6);
	SpringLayout sl = new SpringLayout();
	JPanel t_Cad = new JPanel();
	JDialog telaRemDialog;
	Banco banco = new Banco();
	
	public TelaRemover(JFrame pai){
		telaRemDialog = new JDialog(pai,"Remover Conta",true);
		mostrarTela();				
	}

	private void mostrarTela(){		
		telaDeRemover();
		botaoConcluido();
		montarTela();
	}
	
	
		private void telaDeRemover() {
		
			JLabel remover = new JLabel("REMOVER Nº DE CONTA: ");
		
			sl.putConstraint(SpringLayout.NORTH, remover, 80, SpringLayout.NORTH, t_Cad);
			sl.putConstraint(SpringLayout.WEST, remover, 50, SpringLayout.WEST, t_Cad);
			sl.putConstraint(SpringLayout.NORTH, txtnumero,20, SpringLayout.NORTH, remover);
			sl.putConstraint(SpringLayout.WEST, txtnumero, -105, SpringLayout.EAST, remover);
			t_Cad.add(remover);
			t_Cad.add(txtnumero);
	}
		
		private void botaoConcluido() {
			
			JButton botao = new JButton("Concluido");t_Cad.add(botao);
			sl.putConstraint(SpringLayout.SOUTH, botao, -20, SpringLayout.SOUTH, t_Cad);
			sl.putConstraint(SpringLayout.WEST, botao, 75, SpringLayout.WEST, t_Cad);
			t_Cad.add(botao);
		
			botao.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				//Conta c = new ContaCorrente("",txtnumero.getText(),0);
				banco.remover(txtnumero.getText());
				JOptionPane.showMessageDialog(null, "Nº de Conta Removido");
				System.exit(0);
			}
			
		});
		}

		private void montarTela() {
			
			telaRemDialog.setSize(250, 250);
			telaRemDialog.setLayout(new FlowLayout());
			t_Cad.setLayout(sl);
			telaRemDialog.setContentPane(t_Cad);
			t_Cad.setSize(250, 250);
			telaRemDialog.setResizable(false);
			telaRemDialog.setLocationRelativeTo(null);
			telaRemDialog.setVisible(true);	
		}
		
		
	
}
