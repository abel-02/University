package tp;

public abstract class Gasista extends Servicio {
	protected int cantArtefactos;
	
	public Gasista(int codServicio, String tipoServicio, Especialista especialista, Cliente cliente, String domicilio,
			int cantArtefactos) {
		super(codServicio, tipoServicio, especialista, cliente, domicilio);
		if(!Validador.esPositivo(cantArtefactos))
			throw new RuntimeException("La cantidad de artefactos debe ser como mínimo 1");
		this.cantArtefactos = cantArtefactos;
	}
	
}
