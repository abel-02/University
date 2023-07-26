package e1;

public class Procesador extends ComponentePC{
	private String modelo;
	
	public Procesador(String m, int v, String modelo) {
		super(m,v);
		this.modelo = modelo;
	}

	@Override
	public String toString() {
		return "Procesador [modelo=" + modelo + ", getMarca()=" + getMarca() + ", getVelocidad()=" + getVelocidad()
				+ ", consultarEstado()=" + consultarEstado() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	@Override
	public String mostrarComponente() {
		return 
	}
}
