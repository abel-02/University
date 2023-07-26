
public class Tupla<T1, T2> {
	T1 a;
	T2 b;
	public Tupla(T1 a, T2 b) {
		this.a = a;
		this.b = b;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((a == null) ? 0 : a.hashCode());
		result = prime * result + ((b == null) ? 0 : b.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tupla other = (Tupla) obj;
		if (a == null) {
			if (other.a != null)
				return false;
		} else if (!a.equals(other.a))
			return false;
		if (b == null) {
			if (other.b != null)
				return false;
		} else if (!b.equals(other.b))
			return false;
		return true;
	}
	public T1 getA() {
		return a;
	}
	public void setA(T1 a) {
		this.a = a;
	}
	public T2 getB() {
		return b;
	}
	public void setB(T2 b) {
		this.b = b;
	}
	@Override
	public String toString() {
		return "[ " + a + " , " + b + " ]";
	}
	
	
}
