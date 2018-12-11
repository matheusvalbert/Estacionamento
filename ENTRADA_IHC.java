import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ENTRADA_IHC {
	
	private JButton ok, cancelar;
	private JLabel diaP, horaP, placaP, categoriaP, doisPontos1, doisPontos2;
	private JTextField placa;
	private static final String[] categorias = {"Moto", "Caminhonete", "Carro",""};
	private static final String[] dias = {"30", "29", "28", "27", "26", "25", "24", "23", "22", "21", "20", "19", "18", "17", "16", "15", "14", "13", "12", "11", "10", "9", "8", "7", "6", "5", "4", "3", "2", "1"};
	private static final String[] hora = {"23", "22", "21", "20", "19", "18", "17", "16", "15", "14", "13", "12", "11", "10", "9", "8", "7", "6", "5", "4", "3", "2", "1", "0"};
	private static final String[] minSeg = {"59", "58", "57", "56", "55", "54", "53", "52", "51", "50", "49", "48", "47", "46", "45", "44", "43", "42", "41", "40", "39", "38", "37", "36", "35", "34", "33", "32", "31", "30", "29", "28", "27", "26", "25", "24", "23", "22", "21", "20", "19", "18", "17", "16", "15", "14", "13", "12", "11", "10", "9", "8", "7", "6", "5", "4", "3", "2", "1", "0"};
	
	public ENTRADA_IHC (JPanel[][] vagaCarroTerreo, JPanel[][] vagaCarroPrimeiroPiso) {
		
		BancoDeDados bancoDeDados = BancoDeDados.getInstance();
		
		JFrame frame = new JFrame("Entrada de Veiculos");
		
		diaP = new JLabel("Selecione a data:");
		diaP.setBounds(25,20,200,30);
		diaP.setFont(new Font("Dialog", Font.PLAIN, 15));
		
		JComboBox<String> selecionarDia = new JComboBox<>(dias);
		selecionarDia.setSelectedIndex(29);
		selecionarDia.setBounds(140,20,70,30);
		
		SimpleDateFormat dcn = new SimpleDateFormat("yyyy-MM-dd");
	    String date = dcn.format(jDateChooser1.getDate() );
	    jLabel1.setText(date.toString());
		
		horaP = new JLabel("Selecione a hora:");
		horaP.setBounds(25,60,200,30);
		horaP.setFont(new Font("Dialog", Font.PLAIN, 15));
		
		JComboBox<String> selecionarHora = new JComboBox<>(hora);
		selecionarHora.setSelectedIndex(23);
		selecionarHora.setBounds(150,60,70,30);
		
		doisPontos1 = new JLabel(":");
		doisPontos1.setBounds(225,60,70,30);
		doisPontos1.setFont(new Font("Dialog", Font.PLAIN, 15));
		
		JComboBox<String> selecionarMinuto = new JComboBox<>(minSeg);
		selecionarMinuto.setSelectedIndex(59);
		selecionarMinuto.setBounds(235,60,70,30);
		
		doisPontos2 = new JLabel(":");
		doisPontos2.setBounds(310,60,70,30);
		doisPontos2.setFont(new Font("Dialog", Font.PLAIN, 15));
		
		JComboBox<String> selecionarSegundo = new JComboBox<>(minSeg);
		selecionarSegundo.setSelectedIndex(59);
		selecionarSegundo.setBounds(320,60,70,30);
		
		placaP = new JLabel("Digite a placa:");
		placaP.setBounds(25,100,200,30);
		placaP.setFont(new Font("Dialog", Font.PLAIN, 15));
		frame.add(placaP);
		
		placa = new JTextField();
		placa.setBounds(130,100,80,30);
		
		categoriaP = new JLabel("Selecione a categoria:");
		categoriaP.setBounds(25,140,200,30);
		categoriaP.setFont(new Font("Dialog", Font.PLAIN, 15));
		
		JComboBox<String> selecionarCategoria = new JComboBox<>(categorias);
		selecionarCategoria.setSelectedIndex(3);
		selecionarCategoria.setBounds(180,142,140,30);
		
		ok = new JButton("OK");
		ok.setBounds(300,190,70,35);
		
		cancelar = new JButton("CANCELAR");
		cancelar.setBounds(200,190,90,35);
		
		frame.add(diaP);
		frame.add(selecionarDia);
		frame.add(horaP);
		frame.add(selecionarHora);
		frame.add(doisPontos1);
		frame.add(selecionarMinuto);
		frame.add(doisPontos2);
		frame.add(selecionarSegundo);
		frame.add(placa);
		frame.add(categoriaP);
		frame.add(selecionarCategoria);
		frame.add(ok);
		frame.add(cancelar);
		
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setSize(400, 270);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		
		ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
				
					boolean entrada = bancoDeDados.entradaVeiculo(Integer.parseInt((String) selecionarDia.getSelectedItem()), Integer.parseInt((String) selecionarHora.getSelectedItem()), Integer.parseInt((String)selecionarMinuto.getSelectedItem()), Integer.parseInt((String)selecionarSegundo.getSelectedItem()), placa.getText(), (String) selecionarCategoria.getSelectedItem());
					
					if (entrada == true) {
	
						if("Moto".equals(selecionarCategoria.getSelectedItem())) {
	
							int vagaMoto = bancoDeDados.posicao;
							vagaCarroTerreo[vagaMoto / 10][vagaMoto % 10].setBackground(Color.red);
							JOptionPane.showMessageDialog(null, "Entrada de veiculo realizada com sucesso\nPosicao: M" + (bancoDeDados.posicao + 1), "Entrada de veiculo", JOptionPane.PLAIN_MESSAGE);
						}
						
						else if("Caminhonete".equals(selecionarCategoria.getSelectedItem())) {
							
							int vagaCaminhonete = bancoDeDados.posicao;
							vagaCarroTerreo[(vagaCaminhonete / 10) + 2][vagaCaminhonete % 10].setBackground(Color.red);
							JOptionPane.showMessageDialog(null, "Entrada de veiculo realizada com sucesso\nPosicao: C" + (bancoDeDados.posicao + 1), "Entrada de veiculo", JOptionPane.PLAIN_MESSAGE);
						}
						
						else if("Carro".equals(selecionarCategoria.getSelectedItem())) {
							
							int vagaCarro = bancoDeDados.posicao;
							if(vagaCarro < 60) {
								
								vagaCarroTerreo[(vagaCarro / 10) + 4][vagaCarro % 10].setBackground(Color.red);
							}
							else {
								
								vagaCarro -= 60;
								vagaCarroPrimeiroPiso[vagaCarro / 10][vagaCarro % 10].setBackground(Color.red);
							}
							
							JOptionPane.showMessageDialog(null, "Entrada de veiculo realizada com sucesso\nPosicao: c" + (bancoDeDados.posicao + 1), "Entrada de veiculo", JOptionPane.PLAIN_MESSAGE);
						}
						
						frame.dispose();
					}
					
					else
						JOptionPane.showMessageDialog(null, "Erro estacionamento cheio, ou categoria nao selecionada", "Entrada de veiculo", JOptionPane.PLAIN_MESSAGE);
				}
				catch (NumberFormatException NumberFormatException) {
					
					JOptionPane.showMessageDialog(null, "Insercao Incorreta", "Entrada de veiculo", JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		
		cancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
			}
		});
	}
}
