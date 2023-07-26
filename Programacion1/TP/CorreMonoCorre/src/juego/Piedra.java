package juego;

import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.Observer;

import entorno.Entorno;
import entorno.Herramientas;

@SuppressWarnings("deprecation")
public class Piedra {
	double x;
	double y;
	int al;
	int an;
	double escala;
	Image piedraimg;
	Observer observer;
	int velocidad;
	
	public Piedra(double x, double y) {
		piedraimg = Herramientas.cargarImagen("roca.png");
		this.x = x;
		this.y = y;
		escala = 0.5;
		al = (int) (piedraimg.getHeight((ImageObserver)observer)*escala);
		an = (int) (piedraimg.getWidth((ImageObserver)observer)*escala);
		this.velocidad = 10;
	}
	
	public void dibujarse(Entorno entorno) {
		entorno.dibujarImagen(piedraimg, x, y, 0, escala);;
	}
	
	public void moverse() {
		this.x += this.velocidad;
	}
}
