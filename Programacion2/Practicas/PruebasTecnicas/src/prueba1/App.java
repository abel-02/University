package prueba1;

public class App {
	
	// String común, pero se está creando constantemente un string
	private static String cadenaReversa(String a) {
		String aux = "";
		for(int i = 0; i < a.length(); i++) {
			aux = a.charAt(i) + aux;
		}
		return aux;
	}
	
	// String común, pero se está creando constantemente un string
		private static StringBuilder cadenaReversa2(String a) {
			StringBuilder nueva = new StringBuilder();
			StringBuilder aux =  new StringBuilder();
			for(int i = a.length(); i > 0; i--) {
				aux.append(a.charAt(i));
			}
			return aux;
		}
	
	
	
	
	
	
	public static void main(String[] args) {
		
		String a = "holaMundo";
		StringBuilder nuevo = new StringBuilder();
		String aux = cadenaReversa(a);
		nuevo = cadenaReversa2(nuevo);
		System.out.println(aux);
	}
}
