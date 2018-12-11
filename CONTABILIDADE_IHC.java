import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class CONTABILIDADE_IHC {
	
	JLabel periodo, quantidade, lucro, ate;
	JButton ok;
	private static final String[] dias = {"30", "29", "28", "27", "26", "25", "24", "23", "22", "21", "20", "19", "18", "17", "16", "15", "14", "13", "12", "11", "10", "9", "8", "7", "6", "5", "4", "3", "2", "1"};
	
	public CONTABILIDADE_IHC () {
		
		BancoDeDados bancoDeDados = BancoDeDados.getInstance();
		
		JFrame frame = new JFrame("Contabilidade");
		
		periodo = new JLabel("Selecione o periodo:");
		periodo.setBounds(25,20,200,30);
		periodo.setFont(new Font("Dialog", Font.PLAIN, 15));
		
		JComboBox<String> selecionarDiaIni = new JComboBox<>(dias);
		selecionarDiaIni.setSelectedIndex(29);
		selecionarDiaIni.setBounds(175,20,70,30);
		
		ate = new JLabel("ate");
		ate.setBounds(250,20,200,30);
		ate.setFont(new Font("Dialog", Font.PLAIN, 15));
		
		JComboBox<String> selecionarDiaFim = new JComboBox<>(dias);
		selecionarDiaFim.setSelectedIndex(29);
		selecionarDiaFim.setBounds(275,20,70,30);
		
		quantidade = new JLabel("Quantidade de veiculos: " + bancoDeDados.totalDeVeiculos(Integer.parseInt((String)selecionarDiaIni.getSelectedItem()), Integer.parseInt((String)selecionarDiaFim.getSelectedItem())));
		quantidade.setBounds(25,60,200,30);
		quantidade.setFont(new Font("Dialog", Font.PLAIN, 15));
		
		lucro = new JLabel("O lucro foi de: " + bancoDeDados.totalPagar(Integer.parseInt((String)selecionarDiaIni.getSelectedItem()), Integer.parseInt((String)selecionarDiaFim.getSelectedItem())));
		lucro.setBounds(25,100,200,30);
		lucro.setFont(new Font("Dialog", Font.PLAIN, 15));
		
		ok = new JButton("OK");
		ok.setBounds(310,125,70,35);
		
		frame.add(periodo);
		frame.add(quantidade);
		frame.add(lucro);
		frame.add(ate);
		
		frame.add(selecionarDiaIni);
		frame.add(selecionarDiaFim);
		
		frame.add(ok);
		
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setSize(400, 200);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		
		selecionarDiaIni.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(Integer.parseInt((String)selecionarDiaIni.getSelectedItem()) < Integer.parseInt((String)selecionarDiaFim.getSelectedItem())) {
					
					quantidade.setText("Quantidade de veiculos: " + bancoDeDados.totalDeVeiculos(Integer.parseInt((String)selecionarDiaIni.getSelectedItem()), Integer.parseInt((String)selecionarDiaFim.getSelectedItem())));
					lucro.setText(("O lucro foi de: " + bancoDeDados.totalPagar(Integer.parseInt((String)selecionarDiaIni.getSelectedItem()), Integer.parseInt((String)selecionarDiaFim.getSelectedItem()))));
				}
				
				else {
					
					quantidade.setText("Quantidade de veiculos: " + bancoDeDados.totalDeVeiculos(Integer.parseInt((String)selecionarDiaFim.getSelectedItem()), Integer.parseInt((String)selecionarDiaIni.getSelectedItem())));
					lucro.setText(("O lucro foi de: " + bancoDeDados.totalPagar(Integer.parseInt((String)selecionarDiaFim.getSelectedItem()), Integer.parseInt((String)selecionarDiaIni.getSelectedItem()))));
				}
			}
		});
		
		selecionarDiaFim.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(Integer.parseInt((String)selecionarDiaIni.getSelectedItem()) < Integer.parseInt((String)selecionarDiaFim.getSelectedItem())) {
					
					quantidade.setText("Quantidade de veiculos: " + bancoDeDados.totalDeVeiculos(Integer.parseInt((String)selecionarDiaIni.getSelectedItem()), Integer.parseInt((String)selecionarDiaFim.getSelectedItem())));
					lucro.setText(("O lucro foi de: " + bancoDeDados.totalPagar(Integer.parseInt((String)selecionarDiaIni.getSelectedItem()), Integer.parseInt((String)selecionarDiaFim.getSelectedItem()))));
				}
				
				else {
					
					quantidade.setText("Quantidade de veiculos: " + bancoDeDados.totalDeVeiculos(Integer.parseInt((String)selecionarDiaFim.getSelectedItem()), Integer.parseInt((String)selecionarDiaIni.getSelectedItem())));
					lucro.setText(("O lucro foi de: " + bancoDeDados.totalPagar(Integer.parseInt((String)selecionarDiaFim.getSelectedItem()), Integer.parseInt((String)selecionarDiaIni.getSelectedItem()))));
				}
			}
		});
		
		ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
			}
		});
	}
}
