package tp;

public class Cliente implements Validador{
	private  int dni;
	private String nombre;
	private String telefono;
	
	public Cliente(int dni, String nombre, String telefono) {
		if(!Validador.validarNombre(nombre) || !Validador.validarTelefono(telefono))
			throw new RuntimeException("El nombre o el telefono ingresado no son correctos");
		if(!Validador.esPositivo(dni))
			throw new RuntimeException("El DNI de un cliente no puede ser negativo");
		this.dni = dni;
		this.nombre = nombre;
		this.telefono = telefono;
	}
	
	public int devuelveDNI() {
		return this.dni;
	}
	
	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cliente [")
                .append(" nombre : ").append(nombre)
                .append(" | telefono : ").append(telefono)
                .append(" ]");
        return sb.toString();
    }
}
