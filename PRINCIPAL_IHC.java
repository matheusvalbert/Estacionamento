import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PRINCIPAL_IHC {
	
	private JButton informacaoPreco, entradaVeiculo, saidaVeiculo, contabilidade;
	private JPanel entradaDados, vagaCarroTerreo[][], terreo, vagaCarroPrimeiroPiso[][], primeiroPiso, nomePiso;
	private JLabel texto;
	private int countMoto = 1, countCaminhonete = 1, countCarro = 1, i, j;
	
	public PRINCIPAL_IHC () {
		
		BancoDeDados bancoDeDados = BancoDeDados.getInstance();
		
		JFrame frame = new JFrame("Estacionamento");
		
		//entrada de dados
		entradaDados = new JPanel(new GridLayout(1, 4, 50, 50));
		entradaDados.setBounds(120,720,1400,150);
		informacaoPreco = new JButton("Insercao de preco por hora");
		entradaDados.add(informacaoPreco);
		entradaVeiculo = new JButton("Entrada de Veiculos");
		entradaDados.add(entradaVeiculo);
		saidaVeiculo = new JButton("Saida de Veiculos");
		entradaDados.add(saidaVeiculo);
		contabilidade = new JButton("Contabilidade");
		entradaDados.add(contabilidade);
		//****************************
		
		//jpanel do andar terreo
		terreo = new JPanel(null);
		terreo.setBounds(240,140,500,500);
		//**************************
		
		//vaga jpanel vermelho / verde no tereo
		vagaCarroTerreo = new JPanel[10][10];
		
		for(i = 0; i < 10; i++) {
		
			for(j = 0; j < 10; j++) {
				
				vagaCarroTerreo[i][j] = new JPanel(null);
				vagaCarroTerreo[i][j].setBounds(50*j,50*i,40,40);
				
				if(i < 2) {
					
					texto = new JLabel("M" + countMoto);
					if(bancoDeDados.posicaoMoto[countMoto -1] == false)
						vagaCarroTerreo[i][j].setBackground(Color.green);
					else
						vagaCarroTerreo[i][j].setBackground(Color.red);
					
					countMoto++;
				}
				
				else if(i < 4) {
					
					texto = new JLabel("C" + countCaminhonete);
					if(bancoDeDados.posicaoCaminhonete[countCaminhonete -1] == false)
						vagaCarroTerreo[i][j].setBackground(Color.green);
					else
						vagaCarroTerreo[i][j].setBackground(Color.red);
					
					countCaminhonete++;
				}
				
				else {
					
					texto = new JLabel("c" + countCarro);
					if(bancoDeDados.posicaoCarro[countCarro -1] == false)
						vagaCarroTerreo[i][j].setBackground(Color.green);
					else
						vagaCarroTerreo[i][j].setBackground(Color.red);
					
					countCarro++;
				}
				
				texto.setBounds(8,0,40,40);
				vagaCarroTerreo[i][j].add(texto);
				terreo.add(vagaCarroTerreo[i][j]);
			}
		}
		//****************************
		
		//jpanel do primeiro piso
		primeiroPiso = new JPanel(null);
		primeiroPiso.setBounds(880,140,500,500);
		//***************************
		
		//vaga jpanel vermelho / verde no primeiro piso
		vagaCarroPrimeiroPiso = new JPanel[10][10];
		
		for(i = 0; i < 10; i++) {
			
			for(j = 0; j < 10; j++) {
				
				vagaCarroPrimeiroPiso[i][j] = new JPanel(null);
				vagaCarroPrimeiroPiso[i][j].setBounds(50*j,50*i,40,40);
				texto = new JLabel("c" + countCarro);
				if(bancoDeDados.posicaoCarro[countCarro -1] == false)
					vagaCarroPrimeiroPiso[i][j].setBackground(Color.green);
				else
					vagaCarroPrimeiroPiso[i][j].setBackground(Color.red);
				countCarro++;
				texto.setBounds(5,0,40,40);
				vagaCarroPrimeiroPiso[i][j].add(texto);
				primeiroPiso.add(vagaCarroPrimeiroPiso[i][j]);
			}
		}
		//*********************************************
		
		//jlabel print nome do piso
		nomePiso = new JPanel(null);
		nomePiso.setBounds(435,35,800,500);
		//************************
		
		//printar o terreo
		texto = new JLabel("Terreo");
		texto.setBounds(0, 0, 100, 100);
		texto.setFont(new Font("Dialog", Font.PLAIN, 30));
		nomePiso.add(texto);
		//***********************
		
		// printar primeiro piso
		texto = new JLabel("Primeiro piso");
		texto.setBounds(590, 0, 200, 100);
		texto.setFont(new Font("Dialog", Font.PLAIN, 30));
		nomePiso.add(texto);
		//*************************
		
		frame.add(terreo);
		frame.add(primeiroPiso);
		frame.add(nomePiso);
		frame.add(entradaDados);
		frame.add(terreo);
		frame.add(primeiroPiso);
		frame.add(nomePiso);
		frame.add(entradaDados);
		
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1650, 950);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		
		informacaoPreco.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				new PRECO_IHC();
			}
		});
		
		entradaVeiculo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				new ENTRADA_IHC(vagaCarroTerreo, vagaCarroPrimeiroPiso);
					
			}
		});
		
		saidaVeiculo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				new SAIDA_IHC(vagaCarroTerreo, vagaCarroPrimeiroPiso);
			}
		});
		
		contabilidade.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				new CONTABILIDADE_IHC();
			}
		});
	}
}
