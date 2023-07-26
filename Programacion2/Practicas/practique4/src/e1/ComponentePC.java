package e1;

public class ComponentePC {
	private String marca;
	private int velocidad;
	private boolean encendido;
	
	
	public ComponentePC(String m, int v) {
		this.marca = m;	
		this.velocidad = v;
		this.encendido = false;
	}


	public String getMarca() {
		return marca;
	}

	public int getVelocidad() {
		return velocidad;
	}

	public boolean consultarEstado() {
		return encendido;
	}

	public void encenderComponente() {
		this.encendido = true;
	}
	
	public void apagarComponente() {
		this.encendido = false;
	}
	
	public String mostrarValoresComponente() {
		return "";
	}
	
	
	
}
