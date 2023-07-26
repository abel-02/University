package practique0;

import acumuladores.Acumuladores;

public class Ejercicios {

	// Ejercicio 1
	boolean mayor10(int[] lista) {
		boolean ret = true;
		for(int i = 0; i < lista.length; i++) {
			ret&= greaterThan10(lista[i]);
		}
		return ret;
	}

	private boolean greaterThan10(int num) {
		return num > 10;
	}
	
	// Ejercicio 2
	
	public static boolean pertenecenTodos(int[] elems, int[] arreglo) {
		if(estaVacio(elems) || estaVacio(arreglo))
			return false;
		
		boolean ret = true;
		for(int e: elems) {
			ret &= contieneElemento(e, arreglo);
		}
		return ret;
	}

	private static boolean estaVacio(int[] arreglo) {
		return arreglo == null;
	}

	private static boolean contieneElemento(int elemento, int[] arreglo) {
		boolean ret = false;
		for(int e: arreglo) {
			ret |= (e==elemento);
		}
		return ret;
	}
	
	
	// Ejercicio 3
	
	
}
