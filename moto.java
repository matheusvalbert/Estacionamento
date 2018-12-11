import java.io.Serializable;

public class moto extends veiculo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public moto (String placa, int dia, int horaEnt, int minutoEnt, int segundoEnt, float valor, int posicao) {
		
		super(placa, dia, horaEnt, minutoEnt, segundoEnt);
		this.valor = valor;
		this.posicao = posicao;
	}
}
