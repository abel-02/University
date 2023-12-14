package model;

public class LightsOut {
	private int recordTurnosNFacil;
	private int recordTurnosNNormal;
	private int recordTurnosNDificil;
	private int recordTurnosNImposible;
	private boolean finalizado;
	private boolean[][] tablero;
	
	public LightsOut() {
		this.recordTurnosNFacil = 0;
		this.recordTurnosNNormal = 0;
		this.recordTurnosNDificil = 0;
		this.recordTurnosNImposible = 0;
	}
	
	public void crearTablero(int filaColumna) {
		tablero = new boolean[filaColumna][filaColumna];
		inicializarJuego(); // asigna los botones encendidos y apagados de forma random
		validarTablero();
		finalizado = false;
		
	}
	
	private void validarTablero() { // Si todo el tablero tiene celdas false crea otro tablero
		while(todoTableroEnFalse()==false) {
			inicializarJuego();
		}
	}
	
	private boolean todoTableroEnFalse() {
		boolean todosEnFalse = false;
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero.length; j++) {
				todosEnFalse |= tablero[i][j];
			}
		}
		return todosEnFalse;
	}
	
	private void inicializarJuego() {
		for (int fila = 0; fila < tablero.length; fila++) { // false: apagado, true: prendido
			for (int columna = 0; columna < tablero[0].length; columna++) {
				tablero[fila][columna] = ((int) Math.round(Math.random()) == 1) ? true : false;
			}
		}
	}
	
	public boolean estaEncendido(int fila, int columna) {
		return tablero[fila][columna];
	}
	
	public boolean cambiarEstado(int fila, int columna) {
		tablero[fila][columna] = (tablero[fila][columna]) ? false : true;
		return tablero[fila][columna];
	}
	
	public boolean isFinalizado() {
		return finalizado;
	}
	
	public String dameTablero() {
		StringBuilder tabla = new StringBuilder();
		tabla.append("[ ");
		for (int fila = 0; fila < tablero.length; fila++) {
			for (int columna = 0; columna < tablero[0].length; columna++) {
				tabla.append(tablero[fila][columna]).append(", ");
			}
			tabla.setLength(tabla.length()-2);
			tabla.append("\n");
		}
		tabla.setLength(tabla.length()-1);
		tabla.append(" ]");
		return tabla.toString();
	}
	
	public boolean[][] getTablero(){
		return tablero;
	}
	
	public boolean estadoDelTablero() {
		boolean encendido = false;
		for(int fila = 0; fila < this.tablero.length; fila++) {
			for(int columna = 0; columna < this.tablero.length; columna++) {
				encendido |= estaEncendido(fila,columna); 
			}
		}
		return finalizado = (!encendido) ? true : false;
	}
	
	public void registrarRecord(int nuevoRecord) {
		switch(tablero.length) {
			case 3 -> recordTurnosNFacil = nuevoRecord;
			case 4 -> recordTurnosNNormal = nuevoRecord;
			case 5 -> recordTurnosNDificil = nuevoRecord;
			case 7-> recordTurnosNImposible = nuevoRecord;
		}
	}
	
	public int consultarRecordHistorico() {
		switch(tablero.length) {
			case 3: return recordTurnosNFacil;
			case 4: return recordTurnosNNormal;
			case 5: return recordTurnosNDificil;
			case 7: return recordTurnosNImposible;
			}
		return 0;
	}
	
}	
