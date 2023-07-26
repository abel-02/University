package juego;

import java.awt.image.ImageObserver;
import java.util.Random;


import entorno.Entorno;
import entorno.Herramientas;

public class Arbol {
	double x;
	double y;
	Rama rama;
	double escala;
	int al;
	int an;
	double abajo;
	java.awt.Image arbol;
	ImageObserver observer;
	int velocidad;
	
	
	
	
	Arbol(double x, Suelo piso){
		
		this.x = x;
		this.y = 320;
		arbol=Herramientas.cargarImagen("arbol.png");
		
		double rangeMin = 0.45;
	    double rangeMax = 0.8;
	    Random r = new Random();
	    escala = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
	    al = (int) (arbol.getHeight(observer)*escala);
	    an = (int) (arbol.getWidth(observer)*escala);
	    abajo = y + al/2 - 50;
	    velocidad = 4;
	    
	    if(abajo < piso.superficie) {
			y += piso.superficie-abajo;
		}
		
		//Generacion random de ramas
		if(an % 2 == 0) {
			rama = new Rama(this.x, this.y);
		}
	}
	
	public void dibujarse(Entorno entorno) {
		entorno.dibujarImagen(arbol, x, y, 0, escala);
	}
	
	public void moverse() {
		this.x -= this.velocidad;
	}
}
