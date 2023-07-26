package practique2a;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {

/* Test Ejercicio 2 */
//		ArrayList<Tupla<Integer,Integer>> coordenadas = new ArrayList<Tupla<Integer,Integer>>();
//		rellenarTupla(coordenadas, 10);
//		
//		for(int i = 0; i < coordenadas.size(); i++) {
//			coordenadas.get(i).establecerX(i);
//			coordenadas.get(i).establecerY(i+1);
//		}
//		
//		for(int i = 0; i < coordenadas.size(); i++) {
//			System.out.println(coordenadas.get(i).toString());
//		}
		
/* Test ejercicio 3 */		
		Conjunto2 ejemplo = new Conjunto2();
		Conjunto2 ejemplo2 = new Conjunto2();
		ejemplo.agregar(1);
		ejemplo.agregar(2);
		ejemplo2.agregar(1);
		ejemplo2.agregar(3);
		ejemplo.union(ejemplo2);
		System.out.println(ejemplo);

	
	}
	
	
	
	
	public static  void rellenarTupla(ArrayList a, int tamano) {
		 int i = 0;
		    while(i < tamano) {
		       Tupla<Integer,Integer> nuevaTupla = new Tupla<>(0,0);
		       a.add(nuevaTupla);
		       i++;
		    }
	}
	
	

}
