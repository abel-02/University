package practique2a;

import java.util.ArrayList;
import java.util.List;

public class Conjunto2<T> {
	
	private List<T> conjunto;
	
	public Conjunto2(){
		this.conjunto = new ArrayList<T>();
	}
	
	public Integer tamaño() {
		return this.conjunto.size();
	}
	
	
	// Agrega un elemento al conjunto, en caso de que ya esté se lanza una excepción
	public void agregar(T n) {
		if(this.conjunto.contains(n))
			throw new RuntimeException("No se puede guardar un objeto que ya esté en el conjunto");
		else
			this.conjunto.add(n);
	}
	
	
	// Obtiene el elemento del conjunto del indice correspondiente
	public T iesimo(Integer indice) {
		if(indice > this.conjunto.size() || indice < 0)
			throw new RuntimeException("El indice no es correcto");
		return this.conjunto.get(indice);
	}
	
	
	// Reemplaza el conjunto por su union con c
	public void union(Conjunto2<T> c) {
		Conjunto2 aux = this.union2(c);
		this.conjunto = aux.conjunto;
	}
	
	// Devuelve la unión del conjunto con c, dejando afuera los elementos repetidos 
		public Conjunto2<T> union2(Conjunto2<T> c) {
			Conjunto2 aux = this.interseccion2(c);
			aux.conjunto.addAll(this.conjunto);
			for(T elem : c.conjunto) {
				if(!aux.contiene(elem))
					aux.agregar(elem);
			}
			return aux;
		}

	

	// Reemplaza el conjunto por su intersección con c
	public void interseccion(Conjunto2<T> c) {
		Conjunto2 aux = this.interseccion2(c);		
		this.conjunto = aux.conjunto;
	}
	
	// Devuelve la interseccion del conjunto con c, dejando afuera los elementos repetidos 
	public Conjunto2<T> interseccion2(Conjunto2<T> c) {
		Conjunto2 aux = new Conjunto2();
		for(T elem : this.conjunto) {
			if(c.contiene(elem))
				aux.agregar(elem);
		}
		return aux;
	}
	
	
	
	@Override
	public String toString() {
		return "Conjunto [conjunto=" + conjunto + "]";
	}
	
	// Obtiene el objeto directamente del conjunto, segun el indice correspondiente
	private T obtener(int i) {
		return this.conjunto.get(i);
	}

	// Si el conjunto contiene el objeto
		boolean contiene(T obj) {
			boolean ret = false;
			for(T elem: this.conjunto) {
				ret |= (elem.equals(obj));
			}
			return ret;
		}
	
}
