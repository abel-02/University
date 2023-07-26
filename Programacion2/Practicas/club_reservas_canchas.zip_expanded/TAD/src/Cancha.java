import java.util.HashMap;

public class Cancha {
	private String codCancha;
	private String tipo;
	private String estadoGeneral;
	private double precio;
	private HashMap<String,HashMap<Integer,Equipo>> cronograma;
	
	Cancha ( String codCancha, String estado, String tipo, double precio){
		if (codCancha==null)
			throw new RuntimeException();
		if (estado==null)
			throw new RuntimeException();
		if (tipo==null)
			throw new RuntimeException();
		if (precio <= 0)
			throw new RuntimeException();
		
		
	}
	
	boolean reservar(String dia, int hora, Equipo equipo) {
		if (equipo==null)
			throw new RuntimeException();
		if (!equipo.esDeTipo(tipo))
			throw new RuntimeException();
		
		HashMap<Integer,Equipo> reservasDelDia;
		// Si tiene una entrada para ese dia, la uso
		if (cronograma.containsKey(dia))
			reservasDelDia = cronograma.get(dia);
		else {
			// Si no tiene una entrada para ese dia, creo una entrada nueva 
			// y la meto en el diccionario.
			reservasDelDia = new HashMap<>();
			cronograma.put(dia,reservasDelDia);
		}
		
		// verificacion de disponibilidad
		if (reservasDelDia.containsKey(hora))
			return false;
		// reserva
		reservasDelDia.put(hora,equipo);
		return true;
	}
	
	Equipo consultarReserva(String dia, int hora) {
		
	}
	List<Integer> consultarDisponibilidad(String dia) {
		
	}
	
	int consultarTotalHorasReservadas() {
		
	}
	
	void limpiarReservas() {
		// cronograma = new HashMap<>(); // OPCION 1
		cronograma.clear(); // OPCION 2
	}
	
	public String toString() {
		return "devolver información util para entender el estado del objeto";
	}
}
