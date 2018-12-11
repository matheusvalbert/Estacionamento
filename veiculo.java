import java.io.Serializable;

public abstract class veiculo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String placa;
	private int diaEnt, diaSai;
	private int horaEnt, minutoEnt, segundoEnt, horaSai, minutoSai,segundoSai;
	protected float valor;
	protected int posicao;
	
	public veiculo (String placa, int diaEnt, int horaEnt, int minutoEnt, int segundoEnt) {
		
		this.placa = placa;
		this.diaEnt = diaEnt;
		this.horaEnt = horaEnt;
		this.minutoEnt = minutoEnt;
		this.segundoEnt = segundoEnt;
	}
	
	public String getPlaca() {
		
		return placa;
	}
	
	public int getPosicao() {
		
		return posicao;
	}

	public void setDiaSai(int diaSai) {
		
		this.diaSai = diaSai;
	}
	
	public void setHoraSai(int horaSai) {
		
		this.horaSai = horaSai;
	}

	public void setMinutoSai(int minutoSai) {
		
		this.minutoSai = minutoSai;
	}

	public void setSegundoSai(int segundoSai) {
		
		this.segundoSai = segundoSai;
	}
	
	public float saida () {
		
		int dia, hora, minuto, segundo;
		float total = 0;
		
		dia = diaSai - diaEnt;
		hora = horaSai - horaEnt;
		minuto = minutoSai - minutoEnt;
		segundo = segundoSai - segundoEnt;
		
		total += dia * (valor*24);
		total += hora * valor;
		total += minuto * (valor/60);
		total += segundo * (valor/3600);
		
		
		return total;
	}
}