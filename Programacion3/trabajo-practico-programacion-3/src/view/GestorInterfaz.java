package view;

import java.awt.EventQueue;

import presenter.Presenter;

public class GestorInterfaz {
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Se crea el presenter que usamos para todo el juego.
					Presenter presenter = new Presenter();
					PantallaInicio pInicio = new PantallaInicio(presenter);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void irAMenu(PantallaJuego juego, Presenter presenter) {
		juego.getLightsOut().setVisible(false);
		PantallaInicio pInicio = new PantallaInicio(presenter);
	}

	public void abrirJuego(String nombreUsuario, Presenter presenter) {
		PantallaJuego juego = new PantallaJuego(nombreUsuario, this, presenter);
		juego.getLightsOut().setVisible(true);
	}

}