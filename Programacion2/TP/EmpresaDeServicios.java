package tp;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class EmpresaDeServicios implements Empresa {
	private HashMap<Integer, Cliente> clientes;
	private HashMap<Integer, Especialista> especialistas;								
	private HashMap<Integer, Servicio> servicios;										
	private int codServicio;
	private HashMap<String, Double> facturacion;
	
	public EmpresaDeServicios() {
		this.clientes = new HashMap<>();
		this.especialistas = new HashMap<>();
		this.servicios = new HashMap<>();
		this.codServicio = 0;
		this.facturacion = new HashMap<>();
		this.facturacion.put("Electricidad", 0.0);
		this.facturacion.put("Pintura", 0.0);
		this.facturacion.put("PinturaEnAltura", 0.0);
		this.facturacion.put("GasistaInstalacion", 0.0);
		this.facturacion.put("GasistaRevision", 0.0);
	}
	
	@Override
	public void registrarCliente(int dni, String nombre, String telefono) {
		if(clienteRegistrado(dni))
			throw new RuntimeException("El cliente ya está registrado");
		else {
			Cliente cliente = new Cliente(dni,nombre,telefono);
			this.clientes.put(dni, cliente);
		}	
	}
	
	@Override
	public void registrarEspecialista(int nroEspecialista, String nombre, String telefono, String especialidad) {
		if(especialistaRegistrado(nroEspecialista))
			throw new RuntimeException("El especialista ya está registrado");
		if(!tipoServicioExistente(especialidad))
			throw new RuntimeException("La empresa no ofrece ese tipo de servicio");
		else {
			Especialista especialista = new Especialista(nroEspecialista, nombre, especialidad, telefono);
			this.especialistas.put(nroEspecialista, especialista);
		}
	}

	@Override
	public int solicitarServicioElectricidad(int dni, int nroEspecialista, String direccion, double precioPorHora,
			int horasTrabajadas) {
		// validacion all in one
		servicioValido(dni, nroEspecialista, "Electricidad");
		this.codServicio++;
		Servicio servicio = new Electricidad(this.codServicio, "Electricidad", especialistas.get(nroEspecialista), clientes.get(dni), direccion,
				precioPorHora, horasTrabajadas);
		agregarServicio(servicio);
		return this.codServicio;
	}

	@Override
	public int solicitarServicioPintura(int dni, int nroEspecialista, String direccion, int metrosCuadrados,
			double precioPorMetroCuadrado) {
		// validacion all in one
		servicioValido(dni, nroEspecialista, "Pintura");
		this.codServicio++;
		Servicio servicio = new Pintura(this.codServicio, "Pintura", especialistas.get(nroEspecialista), clientes.get(dni), direccion,
				metrosCuadrados, precioPorMetroCuadrado);
		agregarServicio(servicio);
		return this.codServicio;
	}
	
	@Override
	public int solicitarServicioPintura(int dni, int nroEspecialista, String direccion, int metrosCuadrados,
			double precioPorMetroCuadrado, int piso, double seguro, double alqAndamios) {
		// validacion all in one
		servicioValido(dni, nroEspecialista, "PinturaEnAltura");
		if(piso <= 0 || seguro <= 0 || alqAndamios <= 0)
			throw new RuntimeException("Los datos no son válidos");
		this.codServicio++;
		Servicio servicio = new PinturaAltura(this.codServicio, "PinturaEnAltura", especialistas.get(nroEspecialista), clientes.get(dni), direccion,
				metrosCuadrados, precioPorMetroCuadrado, piso, seguro, alqAndamios);
		agregarServicio(servicio);
		return this.codServicio;
	}
	
	@Override
	public int solicitarServicioGasistaInstalacion(int dni, int nroEspecialista, String direccion, int cantArtefactos,
			double precioPorArtefacto) {
		servicioValido(dni, nroEspecialista, "GasistaInstalacion");
		
		this.codServicio++;
		Servicio servicio = new GasistaInstalacion(this.codServicio, "GasistaInstalacion", especialistas.get(nroEspecialista), clientes.get(dni), direccion,
				cantArtefactos, precioPorArtefacto);
		agregarServicio(servicio);
		
		return this.codServicio;
	}
	
	@Override
	public int solicitarServicioGasistaRevision(int dni, int nroEspecialista, String direccion, int cantArtefactos,
			double precioPorArtefacto) {
		// validation all in one
		servicioValido(dni, nroEspecialista, "GasistaRevision");
		this.codServicio++;
		Servicio servicio = new GasistaRevision(this.codServicio, "GasistaRevision", especialistas.get(nroEspecialista), clientes.get(dni), direccion,
				cantArtefactos, precioPorArtefacto);
		agregarServicio(servicio);
		return this.codServicio;
	}
	
	
	//Tener mas de un if para cada excepcion
	@Override
	public double finalizarServicio(int codServicio, double costoMateriales) {
		if(!servicioExistente(codServicio) || !servicios.get(codServicio).consultarServicioActivo())
			throw new RuntimeException("El servicio no está activo o no existe");
		
		buscarServicio(codServicio).finalizarServicio();
		
		return servicioConMateriales(codServicio, costoMateriales);
	}


	@Override
	public Map<String, Integer> cantidadDeServiciosRealizadosPorTipo() {
		Map<String, Integer> aux = new HashMap<>();
		Iterator<String> iterador = this.facturacion.keySet().iterator();
		
		while(iterador.hasNext()) {
			String tipo = iterador.next();
			int cant = cantidadServicios(tipo);
			aux.put(tipo, cant);
		}
		
		return aux;
	}
	
	@Override
	public double facturacionTotalPorTipo(String tipoServicio) {
		if(!tipoServicioExistente(tipoServicio))
			throw new RuntimeException("El tipo de servicio es invalido");
		
		return (double) this.facturacion.get(tipoServicio);
	}

	@Override
	public double facturacionTotal() {
		double cont = 0;
		
		for(Double servicio: this.facturacion.values()) {
			cont += servicio;
		}
		
		return cont;
	}
	
	@Override
	public void cambiarResponsable(int codServicio, int nroEspecialista) {
		Servicio sAux = buscarServicio(codServicio);
		Especialista eAux = especialistas.get(nroEspecialista);
		
		if(!servicioExistente(codServicio) || !servicios.get(codServicio).consultarServicioActivo()) 
			throw new RuntimeException("El servicio no está registrado o activo");
		if(!especialistaRegistrado(nroEspecialista) || !sAux.mismaEspecializacion(eAux.devuelveEspecializacion()))
			throw new RuntimeException("El especialista no está registrado o no se especializa en ese tipo");
		
		buscarServicio(codServicio).actualizarEspecialista(this.especialistas.get(nroEspecialista));
	}

	@Override
	public String listadoServiciosAtendidosPorEspecialista(int nroEspecialista) {
		if(!especialistaRegistrado(nroEspecialista))
			throw new RuntimeException("El especialista no está registrado");
		
		StringBuilder listado = new StringBuilder();
		
		for(Servicio servicios: this.servicios.values()) {
			if(servicios.consultarCodigoEspecialista() == (nroEspecialista))
				listado.append(servicios.toString());
		}
		return listado.toString();
	}
	
	@Override
	public String toString() {
	    StringBuilder datos = new StringBuilder();
	    
	    datos.append("Empresa: El Tano Construcciones \n\n");
	    
	    datos.append("\nTipos de servicio disponibles:\n");
	    for (Map.Entry<String, Double> entry : facturacion.entrySet()) {
	        String tipoServicio = entry.getKey();
	        datos.append(tipoServicio).append("\n");
	    }
	    
	    datos.append("\nClientes atendidos:\n");
	    for (Cliente cliente : clientes.values()) {
	        datos.append(cliente.toString()).append("\n");
	    }
	    
	    datos.append("\nProfesionales registrados:\n");
	    for (Especialista especialista : especialistas.values()) {
	        datos.append(especialista.toString()).append("\n");
	    }
	    
	    datos.append("\nServicios solicitados:\n");
	    for (Servicio servicio : servicios.values()) {
	        datos.append(servicio.toString()).append("\n");
	    }
	    
	    return datos.toString();
	}	

	// Recibe una especialidad y devuelve true si la empresa ofrece ese servicio.
	private boolean tipoServicioExistente(String especialidad) {
		return this.facturacion.containsKey(especialidad);
	}

	// Recibe un servicio y calcula su importe, actualiza facturacion y devuelve el importe.
	private double servicioConMateriales(int nroServicio, double costoMateriales) {
	    String tipo = buscarServicio(nroServicio).consultarTipoServicio();
	    double aux = buscarServicio(nroServicio).calcularImporte(costoMateriales);    
	    Double valorActual = this.facturacion.get(tipo);
	    // Si no se registro la facturacion el valor es null
	    if (valorActual != null) 
	    	// Reescribo el valor del diccionario (Sumo la facturacion del servicio al acumulador
	        this.facturacion.put(tipo, valorActual + aux);
	    else 
	        this.facturacion.put(tipo, aux);
	    return aux;
	}

	// Devuelve la cantidad de servicios de un tipo de servicio.
	private int cantidadServicios(String tipo) {
		int cont = 0;
		for(Servicio servicio: this.servicios.values()) {
			if(!servicio.consultarServicioActivo()) 
				if(servicio.consultarTipoServicio().equals(tipo)) 
					cont++;
		}
		return cont;
	}
	
	// Recibe un codigo de servicio, busca el servicio y lo devuelve.
	private Servicio buscarServicio(int codServicio) {
		return this.servicios.get(codServicio);
	}
	
	// Recibe codigo de servicio y verifica si existe o no.
	private boolean servicioExistente(int codServicio) {
		return this.servicios.containsKey(codServicio);
	}
	
	// Valida los datos y genera una excepción en caso de un dato inválido.
	private void servicioValido(int dni, int nroEspecialista, String tipo) {
		if(!clienteRegistrado(dni))
			throw new RuntimeException("El cliente no está registrado");
		if(!especialistaRegistrado(nroEspecialista))
			throw new RuntimeException("El especialista no está registrado");
		if(!especialistas.get(nroEspecialista).seEspecializa(tipo))
			throw new RuntimeException("El especialista no está especializado en este tipo de servicio");
	}
	
	//	Agrega el servicio a la estructura de datos.
	private void agregarServicio(Servicio servicio) {
		this.servicios.put(servicio.consultarCodServicio(), servicio);
	}
	
	// Verifica que el especialista esté registrado en la estructura de datos.	
	private boolean especialistaRegistrado(int nroEspecialista) {
		return this.especialistas.containsKey(nroEspecialista);
	}
	
	// Verifica que el cliente esté registrado en la estructura de datos.
	private boolean clienteRegistrado(int dni) {
		return this.clientes.containsKey(dni);
	}

}


