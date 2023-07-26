package acumuladores;

public class Acumuladores {

/*	Ejercicio 1      */
	
		public boolean todosMultiplosEnAlgunaFila(int[][] mat, int num) {
			if(!esPositivo(num))
				return false;
						
			boolean tMultiplos = false;
			for(int i = 0; i < mat.length; i++) {
				tMultiplos |= todosMultiplos(mat[i],num);
			}return tMultiplos;
		}
		
	
		private boolean todosMultiplos(int[] a, int n) {
			boolean multiplos = true;
			for(int i = 0; i < a.length; i++) {
				multiplos &= esMultiplo(a[i],n);
			}return multiplos;
		}
	
		private boolean esMultiplo(int i, int n) {
			return i%n==0;
		}
		
		private boolean esPositivo(int num) {
			if (num > 0)
				return true;
			return false;
		}
		
	
	
	
	
/*	Ejercicio 2      */
	
		public  boolean hayInterseccionPorFila(int[][] mat1, int[][]mat2) { 
			if(distintasFilas(mat1,mat2)) 
				return false;
			if(matrizVacia(mat1) || matrizVacia(mat2)) 
				return false;
						
			boolean hayInterseccion = true;
			for(int f = 0; f < mat1.length; f++){
				hayInterseccion &= interseccionEnFila(mat1[f],mat2[f]);
			}return hayInterseccion;
		}
	
		
		// Busca si un elemento del primer arreglo esta en el segundo
		private  boolean interseccionEnFila(int[] arreglo, int[] arreglo2) {
			boolean hayInterseccion = false;
			for(int i = 0; i < arreglo.length; i++){
				hayInterseccion |= estaEnArreglo(arreglo[i],arreglo2);
			}return hayInterseccion;
		}
		
		// Si un numero esta en un arreglo
		private  boolean estaEnArreglo(int num, int[] mat) {
			boolean esta = false;
			for(int i = 0; i < mat.length; i++) {
				esta |= (num==mat[i]);
			}return esta;
		}	
	
		private boolean distintasFilas(int[][] mat1, int[][] mat2) { 
			return mat1.length != mat2.length; 
		}

	
	

/*	Ejercicio 3      */
	
		public boolean algunaFilaSumaMasQueLaColumna(int[][] mat, int nColum) { //blue
			if(matrizVacia(mat))
				return false;
			if(columnaInvalida(nColum,mat)) 
				return false;
						
			boolean filaMasQueColumna = false;
			for(int f = 0; f < mat.length; f++) {
				filaMasQueColumna |= (totalFila(mat[f]) > totalColum(mat,nColum));
			}return filaMasQueColumna; 
		}
	
	
		private boolean columnaInvalida(int nColum, int[][] mat) {
			return (nColum >= mat[0].length || nColum < 0);
		}
	
		private int totalFila(int[] fila) {	//red
			int acum = 0;
			for(int i = 0; i < fila.length; i++) {
				acum += fila[i];
			}return acum;
		}
		
		private int totalColum(int[][] mat, int colum) {
			int acum = 0;
			for(int f = 0; f < mat.length; f++) {
				acum += mat[f][colum];
			}return acum;
		}
	
	
	
	
/*	Ejercicio 4      */
	
	
		// mueve columna
		public boolean hayInterseccionPorColumna(int[][] mat1, int[][]mat2) {			
			if(matrizVacia(mat1) || matrizVacia(mat2)) 
				return false;					
			if(difColum(mat1,mat2)) 
				return false;
					
			boolean hayPorColum = true;
			for(int c = 0; c < mat1[0].length; c++) {
				hayPorColum &= interseccionEnColu(mat1,mat2,c);
			}return hayPorColum;
		}
		
		
		// mueve fila y acumula
		private boolean interseccionEnColu(int[][] mat1, int[][] mat2, int c) {
			boolean interColum = false;
			for(int f = 0; f < mat1.length; f++) {
				interColum |= estaEnColum(mat1[f][c],mat2,c);
			}return interColum;
		}
		
		// devuelve true si hay interseccion
		private boolean estaEnColum(int num, int[][] mat2, int c) {
			boolean inter = false;
			for(int f = 0; f < mat2.length; f++) {
				inter |= interseccion(num, mat2[f][c]);
			}return inter;
		}
		
		private boolean difColum(int[][] mat1, int[][] mat2) {
			return mat1[0].length != mat2[0].length;
		}

		private boolean matrizVacia(int[][] mat) {
			return mat == null || mat.length == 0 || mat[0].length == 0;
		}
	
		private boolean interseccion(int i, int j) { 
			return i==j; 
		}
	
}
