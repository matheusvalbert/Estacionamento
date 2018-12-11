import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class BancoDeDados implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public float precoMoto, precoCaminhonete, precoCarro;
	private ArrayList<veiculo> veiculos = new ArrayList<veiculo>();
	public int countMoto = 0, countCaminhonete = 0, countCarro = 0;
	public boolean posicaoMoto[] = new boolean[20], posicaoCaminhonete[] = new boolean[20], posicaoCarro[] = new boolean[160], flagMoto = false, flagCaminhonete = false, flagCarro = false;
	private int i, count = 0;
	public int posicao, posicaoRemove, totalVeiculos[] = new int[30];
	public float totalPagar[] = new float[30], valorPagar;

	private static BancoDeDados instance = null;

	private BancoDeDados() {}
	
	public static BancoDeDados getInstance(){
		
		if(instance == null) {
			
			try {
				
				ObjectInput in = new ObjectInputStream(new FileInputStream("save.ser"));
				instance = (BancoDeDados) in.readObject();
				in.close();
	        
			} catch (IOException | ClassNotFoundException e) {
				
				instance = new BancoDeDados();
			}
		}
	      
		return instance;
	}
	
	public void setPreco(float precoMoto, float precoCaminhonete, float precoCarro) {
		
		this.precoMoto = precoMoto;
		this.precoCaminhonete = precoCaminhonete;
		this.precoCarro = precoCarro;
		
		serializacao();
	}
	
	public boolean entradaVeiculo(int dia, int hora, int minuto, int segundo, String placa, String categoria) {
		
		if("Moto".equals(categoria) && countMoto < 20) {
			
			if(flagMoto == false) {
				
				for(i = 0; i < 20; i++) {
					
					posicaoMoto[i] = false;
				}
				
				flagMoto = true;
			}
			
			count = 0;
			posicao = 0;
			
			while(posicaoMoto[count] != false) {
				
				count++;
				posicao = count;
			}
			
			countMoto++;
			veiculo moto = new moto(placa, dia, hora, minuto, segundo, precoMoto, posicao);
			posicaoMoto[posicao] = true;
			veiculos.add(moto);
			serializacao();
			return true;
		}
		
		else if("Caminhonete".equals(categoria) && countCaminhonete < 20) {
			
			if(flagCaminhonete == false) {
				
				for(i = 0; i < 20; i++) {
					
					posicaoCaminhonete[i] = false;
				}
				
				flagCaminhonete = true;
			}
			
			count = 0;
			posicao = 0;
			
			while(posicaoCaminhonete[count] != false) {
				
				count++;
				posicao = count;
			}
			
			countCaminhonete++;
			veiculo caminhonete = new caminhonete(placa, dia, hora, minuto, segundo, precoCaminhonete, posicao);
			posicaoCaminhonete[posicao] = true;
			veiculos.add(caminhonete);
			serializacao();
			return true;
		}
		
		else if("Carro".equals(categoria) && countCarro < 160) {
			
			if(flagCarro == false) {
				
				for(i = 0; i < 160; i++) {
					
					posicaoCarro[i] = false;
				}
				
				flagCarro = true;
			}
			
			count = 0;
			posicao = 0;
			
			while(posicaoCarro[count] != false) {
				
				count++;
				posicao = count;
			}
			
			countCarro++;
			veiculo carro = new carro(placa, dia, hora, minuto, segundo, precoCarro, posicao);
			posicaoCarro[posicao] = true;
			veiculos.add(carro);
			serializacao();
			return true;
		}
		
		else {
			
			return false;
		}
	}
	
	public int saidaVeiculo(int dia, int hora, int minuto, int segundo, String placa) {
		
		for(veiculo pos : veiculos) {
			
			if(placa.equals(pos.getPlaca())) {
				
				if(pos instanceof moto) {
					
					countMoto--;
					posicaoRemove = pos.getPosicao();
					posicaoMoto[posicaoRemove] = false;
					pos.setDiaSai(dia);
					pos.setHoraSai(hora);
					pos.setMinutoSai(minuto);
					pos.setSegundoSai(segundo);
					valorPagar = pos.saida();
					totalVeiculos[dia - 1]++;
					totalPagar[dia - 1] += valorPagar;
					veiculos.remove(pos);
					serializacao();
					return 0;
				}
				
				else if(pos instanceof caminhonete) {
					
					countCaminhonete--;
					posicaoRemove = pos.getPosicao();
					posicaoCaminhonete[posicaoRemove] = false;
					pos.setDiaSai(dia);
					pos.setHoraSai(hora);
					pos.setMinutoSai(minuto);
					pos.setSegundoSai(segundo);
					valorPagar = pos.saida();
					totalVeiculos[dia - 1]++;
					totalPagar[dia - 1] += valorPagar;
					veiculos.remove(pos);
					serializacao();
					return 1;
				}
				else if(pos instanceof carro) {
					
					countCarro--;
					posicaoRemove = pos.getPosicao();
					posicaoCarro[posicaoRemove] = false;
					pos.setDiaSai(dia);
					pos.setHoraSai(hora);
					pos.setMinutoSai(minuto);
					pos.setSegundoSai(segundo);
					valorPagar = pos.saida();
					totalVeiculos[dia - 1]++;
					totalPagar[dia - 1] += valorPagar;
					veiculos.remove(pos);
					serializacao();
					return 2;
				}
			}
		}
		
		return -1;
	}
	
	public int totalDeVeiculos(int diaIni, int diaFim) {
		
		int total = 0;
		
		for(i = diaIni; i <= diaFim; i++) {
			
			total += totalVeiculos[i - 1];
		}
		
		return total;
	}
	
	public float totalPagar(int diaIni, int diaFim) {
		
		float total = 0;
		
		for(i = diaIni; i <= diaFim; i++) {
			
			total += totalPagar[i - 1];
		}
		
		return total;
	}
	
	public void serializacao() {
		
		try {
		
			ObjectOutput out = null;
			
			out = new ObjectOutputStream(new FileOutputStream("save.ser"));
			
			out.writeObject(instance);
			
			out.close();
		
		} catch (IOException e) {
			
			System.out.println("Erro");
			e.printStackTrace();
		}
	}
}
