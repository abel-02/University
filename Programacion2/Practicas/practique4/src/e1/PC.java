package e1;

public class PC {
	
	// Atributos
	
	private char tipo;
	private String modelo;
	private Procesador procesador;
	private MemoriaRom discoDuro;
	private int memoria;
	private boolean encendido;
	
	// Operaciones
	
	public PC(char c, String model, Procesador p, MemoriaRom r, int m) {
		this.tipo = c;
		this.modelo = model;
		this.procesador = p;
		this.discoDuro = r;
		this.memoria = m;
		this.encendido = false;
	}
	
	public void encenderPC() {
		this.encendido = true;
		this.discoDuro.encenderComponente();
		this.procesador.encenderComponente();
	}
	
	public void apagarPC() {
		this.encendido = false;
		this.discoDuro.apagarComponente();
		this.procesador.apagarComponente();
	}
	
	private String devuelveTipo() {
		String tipo = "Tipo invalido";
		switch (this.tipo){
			case 'D':
				tipo = "escritorio";
			case 'A':
				tipo = "all-in-one";
			case 'L':
				tipo = "laptop";
		}
		return tipo;
	}
	
	public void mostrarValores() {
		System.out.println(modelo + " -- Procesador: " + procesador.getMarca() + " -- Disco: " + 
				discoDuro.devuelveCapacidad() + "gb -- Ram: " + memoria + "gb");
	}
	
	public void consultarEstadoPC() {
		if(this.encendido)
			System.out.println("El pc está encendido");
		else
			System.out.println("El pc está apagado");
	}
	
	consultarComponentePC()
}
