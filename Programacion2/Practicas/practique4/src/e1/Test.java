package e1;

public class Test {
	public static void main(String[] args) {
		MemoriaRom memoria = new MemoriaRom("Western Digital", 5004, 1000);
		Procesador procesador = new Procesador("AMD", 3600, "Ryzen 3200g");
		
		
		PC miPc = new PC('D',"Shensy 56g", procesador, memoria, 16);
		miPc.mostrarValores();
	}
}
