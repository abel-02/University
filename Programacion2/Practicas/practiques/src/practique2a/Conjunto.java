package practique2a;

import java.util.ArrayList;

public class Conjunto<T> {
	
		private ArrayList<T> elems;

		private int indice;
		
		Conjunto () {
			elems = new ArrayList<>();
			indice = 0;
		}
		void agregar(T elem) {
			if (! pertenece(elem))
				elems.add(elem);
		}
		void quitar(T elem) {
			elems.remove(elem);
//			Iterator<T> it = elems.iterator();
//			while (it.hasNext())
//				if (it.next().equals(elem))
//					it.remove();
		}
		
		boolean pertenece(T elem) {
			return elems.contains(elem);
		}
		
		int tamanio () {
			return elems.size();
		}
		boolean estaVacio() {
			return tamanio()==0; 
		}
		
		T dameUno() {
			if (estaVacio())
				throw new RuntimeException("El conjunto esta vacio");
			
			if (indice>= tamanio())
				indice=0;
			
			return elems.get(indice++);
		}
		
		public String toString() {
			// long init = System.nanoTime();
			// String s= "{";
			StringBuilder sb = new StringBuilder();
			sb.append("{");
			for (T e : elems) {
				// s = s + " " + e.toString() + " ;";
				sb.append(" ");
				sb.append(e);
				sb.append(" ;");
				// Tambien se pueden excribir en una linea.
				// sb.append(" ").append( e ).append(" ;");
			}
			
			// s = s.substring(0,s.length()-1) + "}";
			sb.setCharAt(sb.length()-1, '}');
			// System.out.println(System.nanoTime() - init);
			return sb.toString();
		}
		

		//////////////////  TAREA ///////////////////
		// USAR SOLO LOS METODOS DEFINIDOS ARRIBA. //
		/////////////////////////////////////////////
		
		public void union(Conjunto<T> c){
			// union1: Destructiva
			for (int i = 0; i < c.tamanio(); i++) // Solo para repetir y recorrer todo c.
				agregar(c.dameUno());
		}
		
		public Conjunto<T> union2(Conjunto<T> c){
			// union2: No debe tener Aliasing!
			Conjunto<T> nuevoConjunto = new Conjunto<>();
			
			nuevoConjunto.union(this);
			nuevoConjunto.union(c);
			
			return nuevoConjunto;
		}
		
		public void interseccion(Conjunto<T> c){
			// interseccion 1: Destructiva
			// Conjuntos: this y c
			// modifico lo que tengo en this             
			// this { 1 , 2 , 4 , 6 }
			// c    { 1 , 3 , 6 , 9 }
			// res  { 1 , 6 }
			
			// OPCION 1
			Conjunto<T> elemsNoInterseccion = new Conjunto<>();
			for (int i = 0; i < tamanio(); i++) { // Solo para repetir y recorrer todo c.
				T elem = dameUno();
				if ( ! c.pertenece(elem))
					elemsNoInterseccion.agregar(elem);
			}
			for (int i = 0; i < elemsNoInterseccion.tamanio(); i++)
				quitar(elemsNoInterseccion.dameUno());
			
			// OPCION 2
			Conjunto<T> interseccion = this.interseccion2(c);
			vaciarConjunto(this);
			this.union(interseccion);
		} 
		private void vaciarConjunto(Conjunto<T> c) {
			// Si este metodo se puede optimizar,
			// la opcion 2 sería la más conveniente.
			for (int i = 0; i < c.tamanio(); i++)
				this.quitar(c.dameUno());
		}
		
		public Conjunto<T> interseccion2(Conjunto<T> c){
			Conjunto<T> nuevoConjunto = new Conjunto<>();
			
			for (int i = 0; i < c.tamanio(); i++) { // Solo para repetir y recorrer todo c.
				T elem = c.dameUno();
				if (pertenece(elem))
					nuevoConjunto.agregar(elem);
			}
			
			return nuevoConjunto;
			
		}
}

