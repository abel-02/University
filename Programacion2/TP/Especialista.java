package tp;

public class Especialista implements Validador{
	private int numEspecialista;
	private String especializacion;
	private String nombre;
	private String telefono;

	public Especialista(int numEspecialista, String nombre, String especializacion, String telefono) {
		if(!Validador.esPositivo(numEspecialista))
			throw new RuntimeException("El número de especialista no puede ser menor que 0");
		if(!Validador.validarNombre(nombre) || !Validador.validarTelefono(telefono))
			throw new RuntimeException("El nombre o el telefono ingresado no son correctos");
		this.numEspecialista = numEspecialista;
		this.especializacion = especializacion;
		this.nombre = nombre;
		this.telefono = telefono;
	}
	
	public int devuelveNumEspecialista() {
		return this.numEspecialista;
	}
	
	public String devuelveEspecializacion() {
		return this.especializacion;
	}
	
	// IREP, cada servicio ese punto cliente debe pertenecer a clientes
	// IREPm cada es[ec
	// Objetos, Arboles, binario de busqueda.
	// cada tipo debe ser igual a la suma de tood eso.
	
	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Especialista [")
                .append(" nombre : ").append(nombre)
                .append(" | telefono : ").append(telefono)
                .append(" | tipo de especialización : ").append(especializacion)
                .append(" ]");
        return sb.toString();
    }
	
	// Verifica que el especialista se especialice en el tipo pasado por parámetro.
	public boolean seEspecializa(String especializacion) {
		return this.especializacion.equals(especializacion);		    
	}
}
