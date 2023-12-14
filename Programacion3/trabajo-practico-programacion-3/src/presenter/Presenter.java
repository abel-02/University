package presenter;

import model.LightsOut;

public class Presenter {
	private LightsOut game;
	
	public Presenter() {
		this.game = new LightsOut();
	}
	
	public void iniciarJuego(int numeroNivel) {
		switch(numeroNivel) {
			case 0 -> game.crearTablero(3);
			case 1 -> game.crearTablero(4);
			case 2 -> game.crearTablero(5);
			case 3 -> game.crearTablero(7);
		}
	}
	
	public int largoDelTablero() {
		return game.getTablero().length;
	}
	
	public boolean estaEncendida(int fila, int col) {
		return game.estaEncendido(fila, col);
	}
	
	public void cambiarEstado(int fila, int col) {
		game.cambiarEstado(fila, col);
	}
	
	public int consultarRecordHistorico() {
		return game.consultarRecordHistorico();
	}
	
	public void registrarRecord(int nuevoRecord) {
		game.registrarRecord(nuevoRecord);
	}
	
	public void estadoDelTablero() {
		System.out.println(game.dameTablero()+"\n");
	}
	
	public boolean verificaEstadoDelTablero() {
		return game.estadoDelTablero();
	}
}
