package juego;

import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.Observer;

import entorno.Entorno;
import entorno.Herramientas;

@SuppressWarnings("deprecation")
public class Corazon {
	double x;
	double y;
	double escala;
	int al;
	int an;
	boolean lastimado;
	Image corazonImg;
	Image corazonLasImg;
	Observer observer;
	Corazon(int separacion){
		corazonImg = Herramientas.cargarImagen("corazon.png");
		corazonLasImg = Herramientas.cargarImagen("corazonGris.png");
		x = 25 + separacion;
		y = 25;
		escala = 0.04;
		lastimado = false;
		
		al = (int) (corazonImg.getHeight((ImageObserver)observer)*escala);
		an = (int) (corazonImg.getWidth((ImageObserver)observer)*escala);
	}
	
	public void dibujarse(Entorno entorno) {
		if(!lastimado) {
			entorno.dibujarImagen(corazonImg, x, y, 0, escala);
		}else {
			entorno.dibujarImagen(corazonLasImg, x, y, 0, escala);
		}
	}
}
