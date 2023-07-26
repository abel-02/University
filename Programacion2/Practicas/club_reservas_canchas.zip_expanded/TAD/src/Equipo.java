import java.util.ArrayList;
import java.util.List;

public class Equipo {
	private String nombre;
	private int cantidad;
	private String tipo;
	private List<Tupla<String,Integer>> reservas;
	
	Equipo(String nombre, int cantidad, String tipo){
		if (nombre == null)
			throw new RuntimeException();
		if (tipo == null)
			throw new RuntimeException();
		if (cantidad<1)
			throw new RuntimeException();
		
		
		reservas = new ArrayList<>();
	}
	
	
	List<String> consultarReservas() {
		
	}
	
	boolean tieneReserva(String dia, int hora) {
		
	}
	
	void agregarReserva(String dia, int hora) {
		reservas.add(
				new Tupla<>(dia, hora)
			);
	}
	
	boolean esDeTipo(String tipo){
		return this.tipo.equals(tipo);
	}
	
	void limpiarReservas() {
		// reservas = new ArrayList<>(); // OPCION 1
		reservas.clear(); // OPCION 2
	}
	
	public String toString() {
		return "devolver información util para entender el estado del objeto";
	}
}
