package tp;

public class Electricidad extends Servicio{
	private int horasTrabajadas;
	private double costoHora;
	
	public Electricidad(int codServicio, String tipoServicio, Especialista especialista, Cliente cliente, String domicilio,
			double costoHora, int horasTrabajadas) {
		super(codServicio, tipoServicio, especialista, cliente, domicilio);
		if(!Validador.esPositivo(costoHora) || !Validador.esPositivo(horasTrabajadas))
			throw new RuntimeException("El costo o las horas trabajadas deben ser mayores a 0");
		this.costoHora = costoHora;
		this.horasTrabajadas = horasTrabajadas;
	}
	
	@Override
	public double calcularImporte(double costoMateriales) {
		return (this.horasTrabajadas * this.costoHora) + costoMateriales;
	}

	@Override
	public boolean mismaEspecializacion(String especializacion) {
		return this.tipoServicio.equals(especializacion);
	}
}
