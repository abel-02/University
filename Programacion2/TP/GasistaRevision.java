package tp;

public class GasistaRevision extends Gasista {
	private double costoRevision;
	
	public GasistaRevision(int codServicio, String tipoServicio, Especialista especialista, Cliente cliente,
			String domicilio, int cantArtefactos, double costoServicio) {
		super(codServicio, tipoServicio, especialista, cliente, domicilio, cantArtefactos);
		if(!Validador.esPositivo(costoRevision))
			throw new RuntimeException("El costo por servicio de revisión no debe ser negativo");
		this.costoRevision = costoServicio;
	}

	@Override
	public double calcularImporte(double costoMateriales) {
		double descuento = 0;
//		if(this.cantArtefactos > 5)
		descuento = (this.cantArtefactos > 5)? this.costoRevision * 0.10 : this.costoRevision * 0.15;
		return ((this.costoRevision * this.cantArtefactos) - descuento) + costoMateriales;
	}

	@Override
	public boolean mismaEspecializacion(String especializacion) {
		return this.tipoServicio.equals(especializacion);
	}
}
