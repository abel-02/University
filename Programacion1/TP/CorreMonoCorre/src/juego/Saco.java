package juego;

import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.Observer;

import entorno.Entorno;
import entorno.Herramientas;

@SuppressWarnings("deprecation")
public class Saco {
	double x;
	double y;
	int al;
	int an;
	double escala;
	Image sacoimg;
	Observer observer;
	int velocidad;
	
	Saco(){
		sacoimg = Herramientas.cargarImagen("saco.gif");
		x = 800;
		y = 435;
		escala = 1.5;
		al = (int) (sacoimg.getHeight((ImageObserver)observer)*escala);
		an = (int) (sacoimg.getWidth((ImageObserver)observer)*escala);
		this.velocidad = 4;
	}
	
	public void dibujarse(Entorno e) {
		e.dibujarImagen(sacoimg, x, y, 0, escala);
	}
	
	public void moverse() {
		x -= this.velocidad;
	}
}
