package practique2a;

public class Nat {
	int num;
	
	public Nat(Integer n){
		if(n<0)
			new RuntimeException("Objeto no válido");
		else
			this.num = n;
	}
	
	
	
	public int getNum() {
		return num;
	}



	public void setNum(int num) {
		this.num = num;
	}



	public void sumar(Nat n) {
		this.num += n.num;
	}



	@Override
	public String toString() {
		return "Nat [num=" + num + "]";
	}
	
	
}
