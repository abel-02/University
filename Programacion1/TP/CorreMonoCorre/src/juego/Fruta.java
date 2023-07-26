package juego;

import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.Observer;

import entorno.Entorno;
import entorno.Herramientas;

@SuppressWarnings("deprecation")
public class Fruta {
	double x;
	double y;
	int al;
	int an;
	double escala;
	Image frutaimg;
	Observer observer;
	int velocidad;
	
	public Fruta(double x, double y) {
		frutaimg = Herramientas.cargarImagen("banana.gif");
		this.x = x;
		this.y = y - 15;
		escala = 2;
		this.al = (int) (frutaimg.getHeight((ImageObserver)observer)*escala);
		this.an = (int) (frutaimg.getWidth((ImageObserver)observer)*escala);
		this.velocidad = 4;
	}
	
	public void moverse() {
		this.x -= this.velocidad;
	}
	public void dibujarse(Entorno e) {
		e.dibujarImagen(frutaimg, x, y, 0, escala);
	}
}
