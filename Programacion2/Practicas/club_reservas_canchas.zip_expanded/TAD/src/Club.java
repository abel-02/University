import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Club {
	private String nombre;
	private HashMap<String, Cancha> canchas;
	private HashMap<String, Equipo> equipos;
	
	private final String[] diasAbierto = {"lunes","miercoles","jueves","viernes","sabado","domingo"};
	private final int horarioInicio = 10;
	private final int horarioCierre = 23;
	
	public Club(String nombre) {
		this.nombre = nombre;
		this.canchas = new HashMap<>();
		this.equipos = new HashMap<>();
		// Crear las canchas iniciales
		registrarCancha("F0001", "nueva", "futbol5", 120);
		registrarCancha("F0002", "nueva", "futbol5", 120);
		registrarCancha("F0003", "nueva", "futbol5", 120);
		registrarCancha("B0001", "nueva", "basquet", 170);
		registrarCancha("B0002", "nueva", "basquet", 170);
	}
	
	public void registrarCancha(String codCancha,String estadoGeneral,String tipo, double precio) {
		validarTipo(tipo);
		validarCodCanchaNoRepetida(codCancha);
		
		Cancha nuevaCancha = new Cancha(codCancha,estadoGeneral, tipo, precio);
		canchas.put(codCancha,nuevaCancha);
	}
	
	private void validarCodCanchaNoRepetida(String codCancha) {
		if (codCancha==null)
			throw new RuntimeException();
		
		if (canchas.containsKey(codCancha))
			throw new RuntimeException("El codigo de cancha ya esta registrado.");
	}

	private void validarTipo(String tipo) {
		if (! tipo.equals("futbol5") || ! tipo.equals("basquet"))
			throw new RuntimeException("Tipo invalido");
	}

	public void registrarEquipo(String nombre, int cantidad, String tipo) {
		validarNombreEquipo(nombre);
		validarTipo(tipo);
		
		Equipo equipoNuevo = new Equipo(nombre,cantidad,tipo);
		equipos.put(nombre,equipoNuevo);
		
	}
	private void validarNombreEquipo(String nombre) {
		if (nombre==null)
			throw new RuntimeException();
		if (nombre.length()<1)
			throw new RuntimeException();
		
		if (equipos.containsKey(nombre))
			throw new RuntimeException("Equipo ya registrado");
		
	}

	public boolean reservarCancha(String nombreEquipo, String codCancha,String dia, int hora) {
		// validar dias y horas
		validarDia(dia);
		validarHora(hora);
		
		// Busco la cancha y el equipo.
		Cancha cancha = buscarCancha(codCancha);
		Equipo equipo = buscarEquipo(nombreEquipo);
		
		// Intento Reservar.
		// COMO EQUIPO TIENE SUS RESERVAS, HAY QUE HACER UN PASO MAS ANTES DEL RETURN
		if (cancha.reservar(dia, hora, equipo)) {
			equipo.agregarReserva(dia, hora);
			return true;
		}
		
		return false;
	}
	
	private void validarHora(int hora) {
		if (hora<horarioInicio || hora > horarioCierre)
			throw new RuntimeException();
	}

	private void validarDia(String dia) {
		boolean esValido=false;
		for (String s: diasAbierto)
			esValido |= s.equals(dia);
		
		if (!esValido)
			throw new RuntimeException();
	}

	private Equipo buscarEquipo(String nombreEquipo) {
		if (!equipos.containsKey(nombreEquipo))
			throw new RuntimeException("El equipo no esta en el sistema: " + nombreEquipo);
		return equipos.get(nombreEquipo);
	}

	private Cancha buscarCancha(String codCancha) {
		if (!canchas.containsKey(codCancha))
			throw new RuntimeException("La cancha no esta en el sistema: " + codCancha);
		
		return canchas.get(codCancha);
	}

	public String consultarReserva(String codCancha,String dia, int hora) {
		
	}
	public List<Integer> consultarDisponibilidad(String dia, String codCancha) {
		
	}
	public Map<String,Integer> consultarUsoEstadistico() {
		// diccionario que agrupa tipo de cancha con la cantidad de horas totales que se reservaron.
	}
	public void limpiarReservas() {
		for (Equipo e : equipos.values())
			e.limpiarReservas();
		
		for (Cancha c: canchas.values())
			c.limpiarReservas();
	}
	
	
	public String toString() {
		return "devolver información util para entender el estado del objeto";
	}
}
