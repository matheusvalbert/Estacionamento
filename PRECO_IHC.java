import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class PRECO_IHC {

	private JLabel moto, caminhonete, carro;
	private JTextField motoPreco, caminhonetePreco, carroPreco;
	private JButton ok, cancelar;
	
	public PRECO_IHC() {
		
		BancoDeDados bancoDeDados = BancoDeDados.getInstance();
		
		JFrame frame = new JFrame("Insercao de precos");
		
		moto = new JLabel("Preco moto por hora:");
		moto.setBounds(25,20,200,30);
		moto.setFont(new Font("Dialog", Font.PLAIN, 15));
		
		motoPreco = new JTextField();
		motoPreco.setBounds(180,20,50,30);
		
		caminhonete = new JLabel("Preco Caminhonete por hora:");
		caminhonete.setBounds(25,60,250,30);
		caminhonete.setFont(new Font("Dialog", Font.PLAIN, 15));
		
		caminhonetePreco = new JTextField();
		caminhonetePreco.setBounds(235,60,50,30);
		
		carro = new JLabel("Preco carro por hora:");
		carro.setBounds(25,100,250,30);
		carro.setFont(new Font("Dialog", Font.PLAIN, 15));
		
		carroPreco = new JTextField();
		carroPreco.setBounds(180,100,50,30);
		
		ok = new JButton("OK");
		ok.setBounds(300,140,70,35);
		
		cancelar = new JButton("CANCELAR");
		cancelar.setBounds(200,140,90,35);
		
		frame.add(moto);
		frame.add(motoPreco);
		frame.add(caminhonete);
		frame.add(caminhonetePreco);
		frame.add(carro);
		frame.add(carroPreco);
		frame.add(ok);
		frame.add(cancelar);
		
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setSize(400, 210);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		
		ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				
				try {
					
				bancoDeDados.setPreco(Float.parseFloat(motoPreco.getText()), Float.parseFloat(caminhonetePreco.getText()), Float.parseFloat(carroPreco.getText()));
				JOptionPane.showMessageDialog(null, "Preco atualizado com sucesso", "Precos", JOptionPane.PLAIN_MESSAGE);
				}
				
				catch (NumberFormatException NumberFormatException) {
			
					JOptionPane.showMessageDialog(null, "Entrada Invalida, tente novamente", "Precos", JOptionPane.PLAIN_MESSAGE);
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
