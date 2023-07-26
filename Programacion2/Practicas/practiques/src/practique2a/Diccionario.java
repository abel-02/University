package practique2a;


	public class Diccionario <C,V> {
		
		private List<Tupla<C,V>> entradas;
		private Conjunto<C> claves;
		// IREP:
		//   * todas las claves deben estar como clave en la lista de entradas.
		//   * todas las claves en la lista de entradas deben estar en el conjunto de claves.
		//   * claves.tamanio = entradas.size
		//   * las claves en entradas no pueden estar repetidas.
		
		Diccionario() {
			entradas = new LinkedList<>();
			claves = new Conjunto<>();
		}
		
		V agregar(C clave, V valor) {
			// Agrega una entrada al diccionario 
			// si la clave no existe
			// Si existe la clave se le define el valor 
			// y se devuelve el valor anterior.
			
			if (pertenece(clave)) {
				return actualizarEntradaExistente(clave,valor);
			} 
			
			entradas.add(new Tupla<>(clave,valor));
			claves.agregar(clave);
		
			return null;
		}
		
		V obtener(C clave) {
			// Devuelve el valor asociado a la clave.
			if (! pertenece(clave))
				return null;
			
			return buscarEntrada(clave).obtenerValor2();
//			Tupla<C,V> e = buscarEntrada(clave);
//			return e.obtenerValor2();
			
//			V aux=null;
//			for (Tupla<C,V> entrada : entradas)
//				if (entrada.obtenerValor1().equals(clave))
//					aux = entrada.obtenerValor2();
//			return aux;
		}
			
		
		boolean pertenece(C clave) {
			return claves.pertenece(clave);
			// devuelve verdadero si la clase esta definida.
//			for (Tupla<C,V> entrada : entradas)
//				if (entrada.obtenerValor1().equals(clave))
//					return true;
//			return false;
		}
		int tamanio() {
			return claves.tamanio();
			// return entradas.size();
		}
		boolean estaVacia() {
			return tamanio()==0;
		}
		
		boolean eliminar(C clave) {
			// quita la entrada asociada a la clave.
			// devuelve verdadero si la clave estaba definida
			if (! pertenece(clave))
				return false;
			
//			Esta tambien es una solucion valida. y tiene muchas ventajas.
//			Tupla<C,V> entrada = buscarEntrada(clave);
//			entradas.remove(entrada);
			
			Iterator<Tupla<C,V>> it = entradas.iterator();
			while (it.hasNext()) {
				Tupla<C,V> e = it.next();
				if (e.obtenerValor1().equals(clave))
					it.remove();
			}
			claves.quitar(clave);
			return true;
		}
		
		private Tupla<C,V> buscarEntrada(C clave) {
			for (Tupla<C,V> entrada : entradas)
				if (entrada.obtenerValor1().equals(clave))
					return entrada;
			return null;
		}

		private V actualizarEntradaExistente(C clave, V valor) {
			Tupla<C,V> entrada = buscarEntrada(clave);
			V aux = entrada.obtenerValor2();
			entrada.establecerValor2(valor);
			return aux;
		}

	}
	
	                

