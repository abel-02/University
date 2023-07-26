package juego;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.Observer;

import entorno.Entorno;
import entorno.Herramientas;

@SuppressWarnings({ "deprecation", "unused" })
public class Basura {
	double x;
	double y;
	int al;
	int an;
	double escala;
	Image basuraimg;
	Observer observer;
	int velocidad;
	
	Basura(){
		basuraimg = Herramientas.cargarImagen("basura.png");
		x = 850;
		y = 440;
		escala = 0.19;
		al = (int) (basuraimg.getHeight((ImageObserver) observer)*escala);
		an = (int) (basuraimg.getWidth((ImageObserver) observer)*escala);
		this.velocidad = 4;
	}
	
	public void dibujarse(Entorno e) {
//		e.dibujarRectangulo(x, y, an, al, 0, Color.CYAN); //hitbox
		e.dibujarImagen(basuraimg, x, y, 0, escala);
	}
	
	public void moverse() {
		x -= this.velocidad;
	}
}
