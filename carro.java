import java.io.Serializable;

public class carro extends veiculo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public carro (String placa, int dia, int horaEnt, int minutoEnt, int segundoEnt, float valor, int posicao) {
		
		super(placa, dia, horaEnt, minutoEnt, segundoEnt);
		this.valor = valor;
		this.posicao = posicao;
	}
}
