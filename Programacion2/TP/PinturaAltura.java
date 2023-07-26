package tp;

public class PinturaAltura extends Pintura {
	private int piso;
	private double costoSeguro;
	private double costoAlquilerAndamios;
	
	public PinturaAltura(int codServicio, String tipoServicio, Especialista especialista, Cliente cliente, String domicilio,
			int superficie, double costoPorMetro, int piso, double costoSeguro, double alqAndamios) {
		super(codServicio, tipoServicio, especialista, cliente, domicilio, superficie, costoPorMetro);
		if(!Validador.esPositivo(piso))
			throw new RuntimeException("El piso debe ser mayor que 1");
		if(!Validador.esPositivo(costoSeguro) || !Validador.esPositivo(alqAndamios))
			throw new RuntimeException("El costo de seguro o alquiler no deben ser negativos");			
		this.piso = piso;
		this.costoSeguro = costoSeguro;
		this.costoAlquilerAndamios = alqAndamios;	
	}
	
	@Override
	public double calcularImporte(double costoMateriales) {
		double costoAlq = (this.piso > 5)? this.costoAlquilerAndamios * 1.20 : this.costoAlquilerAndamios;
		return super.calcularImporte(costoMateriales) + this.costoSeguro + costoAlq;
	}
}

