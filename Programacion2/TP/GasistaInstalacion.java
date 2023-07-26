package tp;

public class GasistaInstalacion extends Gasista{
	private double costoInstalacion;
	
	public GasistaInstalacion(int codServicio, String tipoServicio, Especialista especialista,Cliente cliente,
			String domicilio, int cantArtefactos, double costoServicio) {
		super(codServicio, tipoServicio, especialista, cliente, domicilio, cantArtefactos);
		if(!Validador.esPositivo(costoInstalacion))
			throw new RuntimeException("El costo por servicio de instalación no debe ser negativo");
		this.costoInstalacion = costoServicio;
	}

	@Override
	public double calcularImporte(double costoMateriales) {
		return (this.cantArtefactos * this.costoInstalacion) + costoMateriales;
	}

	@Override
	public boolean mismaEspecializacion(String especializacion) {
		return this.tipoServicio.equals(especializacion);
	}

}
