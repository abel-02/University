package tp;

public class Pintura extends Servicio{
	private int superficie;
	private double costoPorMetro;
	
	public Pintura(int codServicio, String tipoServicio, Especialista especialista, Cliente cliente, String domicilio, 
			int superficie, double costoPorMetro) {
		super(codServicio, tipoServicio, especialista, cliente, domicilio);
		if(!Validador.esPositivo(superficie) || !Validador.esPositivo(costoPorMetro))
			throw new RuntimeException("El costo o la superficie no pueden ser negativo");
		this.superficie = superficie;
		this.costoPorMetro = costoPorMetro;
	}

	@Override
	public double calcularImporte(double costoMateriales) {
		return (this.superficie * this.costoPorMetro) + costoMateriales;
	}

	@Override
	public boolean mismaEspecializacion(String especializacion) {
		return this.tipoServicio.equals(especializacion);
	}
		
}
