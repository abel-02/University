package practica6;

public class E1 {
	
	// Suma de elementos
	public static int suma(int[] a) {
		int i = 0;
		return suma(a, i);
	}

	private static int suma(int[] a, int i) {
		if(a.length <= i) 
			return 0;		
		return a[i] + suma(a, i+1);
	}

	
	public static int minimo(int[] a) {
		int i = 0;
		return minimo(a, i);
	}
	
	
	
	private static int minimo(int[] a, int i) {
		if(a.length <= i)
			return a[0];
//		if()
		return 0;
	}

	public static void main(String[] args) {
		int[] a = {1,2,3,4,5};
		System.out.println(suma(a));
	}
}
