package ui;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.table.TableModel;

import com.itextpdf.text.DocumentException;

import br.unipe.mlp.banco.conta.dados.GerarPDF;
import br.unipe.mlp.banco.conta.dados.GerarTXT;
import br.unipe.mlp.banco.conta.fachada.Banco;
import br.unipe.mlp.banco.conta.modelo.ComparatorContaCrescenteSaldo;
import br.unipe.mlp.banco.conta.modelo.ComparatorContaDecrescenteSaldo;
import br.unipe.mlp.banco.conta.modelo.Conta;
import br.unipe.mlp.banco.conta.modelo.ContaTabelaModelo;

public class TelaMenu {
	
	 private Image img;
     private JFrame	tela;
	 private JMenuBar menuBar = null;
	 private JMenu 	sistemaMenu = null;
	 private JMenu	ajudaMenu = null;
	 private JMenuItem cadastroSubMenu = null;
	 private JMenuItem alteraSubMenu = null;
	 private JMenuItem removeSubMenu = null;
	 private JMenuItem sobreMenu = null;
	 private static JTable tabela;
	 private static ArrayList<Conta> contasBusca = null;
	 private static ArrayList<Conta> contasLista = null;
	 private static TableModel tableModel = null;
	 private GerarPDF pdf;
	 private GerarTXT txt;
	 private Banco banco;
	 private JScrollPane barraRolagem;
	 private static SpringLayout springLayout = null;
	 @SuppressWarnings("rawtypes")
	 private static JComboBox comboBox = null;
	 private JPanel painelTabela = null;
	 private String selectCombo = "";
	 private JTextField textBusca = null;
	 private JCheckBox checkNome = null;
	 private JCheckBox checkNumero = null;
	 private JCheckBox checkSaldo = null;
	 private JCheckBox checkCresc = null;
	 private JCheckBox checkDescresc = null;
	 private JButton btnComfirmar = null;
	 private JButton btnGerarPDF = null;
	 private JButton btnGerarTXT = null;
	 private TelaCadastro telaCadastro = null;
	 private TelaRemover telaRemover = null;
	 protected TelaSobre telaSobre;
	 
	 public static void main(String[] args) {
			
			TelaMenu tela = new TelaMenu();
			springLayout = new SpringLayout();
			tela.mostrarTela();
		}
	 
	 
	public TelaMenu(){		
		tela = new JFrame();		
		banco = new Banco();
		
	}
	
	private void mostrarTela(){		
		
		tela.setTitle("Banco Universal");			
		tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tela.add(new JLabel(new ImageIcon("resources/imagem/BancoUniversal.png")));
		tela.setIconImage(Toolkit.getDefaultToolkit().getImage("resources/imagem/LogoBancoJavaIcone.jpg"));	
		tela.setSize(800, 600);
		tela.setResizable(false);			
		tela.setLocationRelativeTo(null);
		tela.setLayout(null);			
		tela.getContentPane().setLayout(springLayout);
		
		montarMenu();
		tela.setVisible(true);
	}
	
	
	private void montarMenu(){
		menuBar = new JMenuBar();
		ajudaMenu = new JMenu("Ajuda");
		sistemaMenu = new JMenu("Sistema");
	
		menuBar.add(sistemaMenu);
		menuBar.add(ajudaMenu);
		
		montarSubMenu();
		tela.setJMenuBar(menuBar);
		montarTabela();
		montarEstrutura();
	}

	private void montarSubMenu(){
		cadastroSubMenu = new JMenuItem("Cadastrar");
		alteraSubMenu = new JMenuItem("Alterar");
		removeSubMenu = new JMenuItem("Remover");
		
		sistemaMenu.add(cadastroSubMenu);
		sistemaMenu.add(alteraSubMenu);
		sistemaMenu.add(removeSubMenu);
		
		itemSubMenu();
	}
	

	private void itemSubMenu(){		
		sobreMenu = new JMenuItem("Sobre o Banco Universal");
		ajudaMenu.add(sobreMenu);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void montarEstrutura(){		
			
		comboBox = new JComboBox(new String[]{"Selecione","Listar","Buscar"});
		checkCresc = new JCheckBox("Crescente");
		checkDescresc = new JCheckBox("Decrescente");
		
		
		btnComfirmar = new JButton("Confirmar");
		btnGerarPDF = new JButton("Gerar .pdf");
		btnGerarTXT = new JButton("Gerar .txt");
		textBusca = new JTextField();	
		contasLista = banco.listTable();
		contasBusca = new ArrayList<Conta>();
		
		springLayout.putConstraint(SpringLayout.NORTH, comboBox, 140, SpringLayout.NORTH, tela.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, comboBox, 39, SpringLayout.WEST, tela.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, comboBox, 155, SpringLayout.WEST, tela.getContentPane());
		tela.getContentPane().add(comboBox);
		
		montarOpListar();
		
		cadastroSubMenu.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				telaCadastro = new TelaCadastro(tela);
				
			}
		});
		
		removeSubMenu.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				 telaRemover = new TelaRemover(tela);
				
			}
		});
		
		sobreMenu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				telaSobre = new TelaSobre(tela);
			}
		});
	
		comboBox.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				if(selectCombo.equals("Listar")){
					desSelectCheck();
					desabiliCheck();
					checkNome.setEnabled(true);
					checkNumero.setEnabled(false);
					checkSaldo.setEnabled(true);
					checkCresc.setEnabled(true);
					checkDescresc.setEnabled(true);
					tela.revalidate();
					
				} else if (selectCombo.equals("Buscar")){
					desSelectCheck();
					tableModel = new ContaTabelaModelo(new ArrayList<Conta>());
					tabela.setModel(tableModel);
					checkNome.setEnabled(true);
					checkNumero.setEnabled(true);
					checkSaldo.setEnabled(false);
					checkCresc.setEnabled(false);
					checkDescresc.setEnabled(false);
					btnGerarTXT.setEnabled(false);
                    btnGerarPDF.setEnabled(false);
					btnComfirmar.setEnabled(true);
					textBusca.setEnabled(true);					
					tela.revalidate();
				} else {
					desSelectCheck();
					desabiliCheck();
				}
			}
		});
		comboBox.addItemListener( new ItemListener() {
			
			public void itemStateChanged(ItemEvent item) {
				
				if(item.getStateChange() == ItemEvent.SELECTED){		
					selectCombo = (String)comboBox.getSelectedItem();				
				
				}				
			}
		});
		ActionListener evenBoxChk = new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if(selectCombo.equals("Buscar")){
					if(checkNome.isSelected()){
						checkNumero.setEnabled(false);
					}else if(checkNumero.isSelected()){					
						checkNome.setEnabled(false);
					} else {
						checkNome.setEnabled(true);
						checkNumero.setEnabled(true);
					}
				
				} else if(selectCombo.equals("Listar")) {
					if(checkCresc.isSelected()&&checkNome.isSelected()) {
						checkDescresc.setEnabled(false);
						checkSaldo.setEnabled(false);
						Collections.reverse(contasLista);
						btnGerarTXT.setEnabled(true);
						btnGerarPDF.setEnabled(true);
						btnComfirmar.setEnabled(true);
					} else if(checkDescresc.isSelected()&&checkNome.isSelected()) {
						checkCresc.setEnabled(false);
						checkSaldo.setEnabled(false);
						Collections.sort(contasLista);
						btnGerarTXT.setEnabled(true);
						btnGerarPDF.setEnabled(true);
						btnComfirmar.setEnabled(true);
					} else if(checkCresc.isSelected()&&checkSaldo.isSelected()) {
						checkDescresc.setEnabled(false);
						checkNome.setEnabled(false);
						Collections.sort(contasLista,new ComparatorContaCrescenteSaldo());
						btnGerarTXT.setEnabled(true);
						btnGerarPDF.setEnabled(true);
						btnComfirmar.setEnabled(true);
					} else if(checkDescresc.isSelected()&&checkSaldo.isSelected()) {
						checkCresc.setEnabled(false);
						checkNome.setEnabled(false);
						Collections.sort(contasLista,new ComparatorContaDecrescenteSaldo());
						btnGerarTXT.setEnabled(true);
						btnGerarPDF.setEnabled(true);
						btnComfirmar.setEnabled(true);
					} else if(!checkCresc.isSelected()||!checkSaldo.isSelected()||!checkDescresc.isSelected()||!checkNome.isSelected()) {
						checkCresc.setEnabled(true);
						checkDescresc.setEnabled(true);
						checkNome.setEnabled(true);
						checkSaldo.setEnabled(true);
						btnComfirmar.setEnabled(false);
					}
					if(checkNome.isSelected()){
						checkSaldo.setEnabled(false);
					}else if(checkSaldo.isSelected()){
						checkNome.setEnabled(false);
					}
					if(checkCresc.isSelected()){
						checkDescresc.setEnabled(false);
					}else if(checkDescresc.isSelected()){
						checkCresc.setEnabled(false);
					}
				}				
				
			}
		};
		
		btnComfirmar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(selectCombo.equals("Listar")){					
					tableModel = new ContaTabelaModelo(contasLista);
					
				} else if(selectCombo.equals("Buscar")){
					Conta conta1 =  banco.buscarNome((String)textBusca.getText()) ;
					Conta conta2 =  banco.buscarNumero((String)textBusca.getText());
					
					if(conta1!=null||conta2!=null){	
						if(!contasBusca.isEmpty()){
							contasBusca.clear();
						}
						if(conta1!=null){
							contasBusca.add(conta1);
						} else {
							contasBusca.add(conta2);
						}
						tableModel = new ContaTabelaModelo(contasBusca);
					} else {
						JOptionPane.showMessageDialog(tela,"Conta não Encontrada");
						tableModel = new ContaTabelaModelo(new ArrayList<Conta>());
					}
				}
					tabela.setModel(tableModel);
					tela.revalidate();
			
			}
		});
		
		btnGerarPDF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				try {
					pdf = new GerarPDF(null);
				} catch (DocumentException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}			
		});
		
		btnGerarTXT.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				txt = new GerarTXT(null);
				
			}
		});
	
		checkCresc.addActionListener(evenBoxChk);
		checkDescresc.addActionListener(evenBoxChk);
		checkNome.addActionListener(evenBoxChk);
		checkNumero.addActionListener(evenBoxChk);
		checkSaldo.addActionListener(evenBoxChk);
	}
	private void desabiliCheck(){
		checkNome.setEnabled(false);
		checkNumero.setEnabled(false);
		checkSaldo.setEnabled(false);
		btnGerarTXT.setEnabled(false);
		btnGerarPDF.setEnabled(false);
		btnComfirmar.setEnabled(false);
		checkCresc.setEnabled(false);
		checkDescresc.setEnabled(false);
		textBusca.setEnabled(false);
		
	}
	private void desSelectCheck(){
		checkNome.setSelected(false);
		checkNumero.setSelected(false);
		checkSaldo.setSelected(false);
		btnGerarTXT.setEnabled(false);
		btnGerarPDF.setEnabled(false);
		btnComfirmar.setSelected(false);
		checkCresc.setSelected(false);
		checkDescresc.setSelected(false);
	
	}
	private void montarOpListar(){
		
		checkNome = new JCheckBox("Nome");
		springLayout.putConstraint(SpringLayout.WEST, checkNome, -4, SpringLayout.WEST, painelTabela);
		springLayout.putConstraint(SpringLayout.SOUTH, checkNome, -27, SpringLayout.NORTH, painelTabela);
		tela.getContentPane().add(checkNome);
		
		checkNumero = new JCheckBox("Numero Conta");
		springLayout.putConstraint(SpringLayout.WEST, checkNumero, 21, SpringLayout.EAST, checkNome);
		springLayout.putConstraint(SpringLayout.SOUTH, checkNumero, 0, SpringLayout.SOUTH, checkNome);
		tela.getContentPane().add(checkNumero);
		
		checkSaldo= new JCheckBox("Saldo");
		springLayout.putConstraint(SpringLayout.NORTH, checkSaldo, 0, SpringLayout.NORTH, checkNome);
		springLayout.putConstraint(SpringLayout.WEST, checkSaldo, 16, SpringLayout.EAST, checkNumero);
		tela.getContentPane().add(checkSaldo);
		
		textBusca = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textBusca, 2, SpringLayout.NORTH, comboBox);
		springLayout.putConstraint(SpringLayout.WEST, textBusca, 0, SpringLayout.WEST, painelTabela);
		springLayout.putConstraint(SpringLayout.EAST, textBusca, 68, SpringLayout.EAST, checkNumero);
		tela.getContentPane().add(textBusca);
		textBusca.setColumns(10);
		
		checkCresc = new JCheckBox("Crescente");
		springLayout.putConstraint(SpringLayout.NORTH, checkCresc, 27, SpringLayout.SOUTH, comboBox);
		springLayout.putConstraint(SpringLayout.WEST, checkCresc, 0, SpringLayout.WEST, comboBox);
		tela.getContentPane().add(checkCresc);
		
		checkDescresc = new JCheckBox("Decrescente");
		springLayout.putConstraint(SpringLayout.NORTH, checkDescresc, 6, SpringLayout.SOUTH, checkCresc);
		springLayout.putConstraint(SpringLayout.WEST, checkDescresc, 0, SpringLayout.WEST, comboBox);
		tela.getContentPane().add(checkDescresc);
		
		btnGerarTXT = new JButton("GerarTXT");
		springLayout.putConstraint(SpringLayout.NORTH, btnGerarTXT,70, SpringLayout.WEST, comboBox);
		springLayout.putConstraint(SpringLayout.EAST, btnGerarTXT, 0, SpringLayout.EAST, painelTabela);
		tela.getContentPane().add(btnGerarTXT);
		desabiliCheck();
		
		btnGerarPDF = new JButton("GerarPDF");
		springLayout.putConstraint(SpringLayout.NORTH, btnGerarPDF,40, SpringLayout.WEST, comboBox);
		springLayout.putConstraint(SpringLayout.EAST, btnGerarPDF, 0, SpringLayout.EAST, painelTabela);
		tela.getContentPane().add(btnGerarPDF);
		desabiliCheck();		
		
		btnComfirmar = new JButton("Confirmar");
		springLayout.putConstraint(SpringLayout.NORTH, btnComfirmar, 0, SpringLayout.NORTH, comboBox);
		springLayout.putConstraint(SpringLayout.EAST, btnComfirmar, 0, SpringLayout.EAST, painelTabela);
		tela.getContentPane().add(btnComfirmar);
		desabiliCheck();	
		
	}	
	
	private void montarTabela(){
		painelTabela = new JPanel();		
		contasLista = new ArrayList<Conta>();		
		
		springLayout.putConstraint(SpringLayout.NORTH, painelTabela, -380, SpringLayout.SOUTH, tela.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, painelTabela, 170, SpringLayout.WEST, tela.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, painelTabela,-10, SpringLayout.SOUTH, tela.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, painelTabela, -170, SpringLayout.EAST, tela.getContentPane());				
		
		tabela = new JTable(new ContaTabelaModelo(contasLista));		
		barraRolagem = new JScrollPane(tabela);
		barraRolagem.setViewportView(tabela);
		painelTabela.add(barraRolagem);
		tabela.setDefaultRenderer(Object.class, new ContaTabelaModelo(contasLista).CellRendererPane());
		tela.add(painelTabela);		
		
	}
	
	
}
