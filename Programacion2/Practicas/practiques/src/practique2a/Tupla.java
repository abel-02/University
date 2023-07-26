package practique2a;

public class Tupla<T1,T2> {
	private T1 x;
	private T2 y;
	
	public Tupla(T1 x, T2 y) {
		this.x = x;
		this.y = y;
	}
	
	public T1 obtenerX() {
		return this.x;
	}
	
	public T2 obtenerY() {
		return this.y;
	}
	
	public void establecerX(T1 x) {
		this.x = x;
	}
	
	public void establecerY(T2 y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "Tupla [x=" + x + ", y=" + y + "]";
	}

	public Object obtenerValor1() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
