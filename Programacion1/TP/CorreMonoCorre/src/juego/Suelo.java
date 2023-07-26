package juego;

import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.Observer;

import entorno.Entorno;
import entorno.Herramientas;

@SuppressWarnings("deprecation")
public class Suelo {
	int an;
	int al;
	double x;
	double y;
	double superficie;
	double escala;
	Image pisoimg;
	Observer observer;
	
	Suelo(){	
		this.pisoimg = Herramientas.cargarImagen("piso.png");
//		this.an = 800;
//		this.al = 150;
		this.escala = 1.2;
		this.an = (int) (pisoimg.getWidth((ImageObserver)observer)*escala);
		this.al = (int) (pisoimg.getHeight((ImageObserver)observer)*escala);
		this.x = 400;
		this.y = 600;
		this.superficie = y - (this.al/2);
		
	}

	public void dibujarse(Entorno entorno){
		entorno.dibujarImagen(pisoimg, x, y, 0, escala);
	}
}
