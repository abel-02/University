package tp;

public interface Validador {
	
	// Valida los datos "nombre" y "telefono".
		public static boolean validarNombre(String nombre) {
			return (!nombre.isEmpty()) && (nombre != null) && (nombre.length() < 50);
		}
		
		// Valida el telefono
		public static boolean validarTelefono(String telefono) {
			return (telefono.matches("\\d+")) && (telefono != null) && (!telefono.equals(""));
		}
		
		// Valida si un número es positivo.
		public static boolean esPositivo(Integer num) {
			return (num > 0);
		}

		public static boolean esPositivo(double num) {
			return num >= 0;
		}

		public static boolean validarDomicilio(String domicilio) {
			return (!domicilio.isEmpty());
		}

		
		
		
}