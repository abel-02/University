package e1;

public class MemoriaRom extends ComponentePC{
	private int capacidad;

	public MemoriaRom(String m, int v, int c) {
		super(m, v);
		this.capacidad = c;
	}

	
	
	@Override
	public String toString() {
		return "MemoriaRom [capacidad=" + capacidad + ", getMarca()=" + getMarca() + ", getVelocidad()="
				+ getVelocidad() + ", consultarEstado()=" + consultarEstado() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	public int devuelveCapacidad() {
		return this.capacidad;
	}
}
