package tp;

public abstract class  Servicio implements Validador {
	protected int codServicio;
	protected Cliente cliente;
	protected String tipoServicio;
	protected Especialista especialista;
	protected String domicilio;
	protected boolean servicioActivo;
	
	// No usar interfaz para meetodos, porque no cambia la implementacion. Clase abstracta, metodos estaticos.
	public Servicio(int codServicio, String tipoServicio, Especialista especialista, Cliente cliente, String domicilio) {
		if(!Validador.esPositivo(codServicio) || !Validador.validarDomicilio(domicilio))
			throw new RuntimeException("El código de servicio o el domicilio no son válidos");
		this.codServicio = codServicio;
		this.tipoServicio = tipoServicio;
		this.especialista = especialista;
		this.cliente = cliente;
		this.domicilio = domicilio;
		this.servicioActivo = true;
	}
	
	public int consultarCodigoEspecialista() {
		return this.especialista.devuelveNumEspecialista();
	}
	
	public String consultarTipoServicio() {
		return this.tipoServicio;
	}
	
	public int consultarCodServicio() {
		return this.codServicio;
	}
	
	public boolean consultarServicioActivo() {
		return this.servicioActivo;
	}
	
	public void actualizarEspecialista(Especialista especialista) {
		this.especialista = especialista;
	}
	

	
	public void finalizarServicio() {
		this.servicioActivo = false;
	}
	
	@Override
	public String toString() {
		 StringBuilder sb = new StringBuilder();
		    sb.append(" + [ ")
		      .append(codServicio)
		      .append(" - ")
		      .append(tipoServicio)
		      .append(" ] ")
		      .append(domicilio)
		      .append("\n");
		    return sb.toString();
	}
	
	public abstract double calcularImporte(double costoMateriales);

	public abstract boolean mismaEspecializacion(String especializacion);
			
	
	
}
