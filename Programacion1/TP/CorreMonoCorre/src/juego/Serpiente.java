package juego;

import java.awt.Color;
import java.awt.image.ImageObserver;
import java.util.Observer;


import entorno.Entorno;
import entorno.Herramientas;

@SuppressWarnings({ "deprecation", "unused" })
public class Serpiente {
	double x;
	double y;
	int al;
	int an;
	double escala;
	java.awt.Image serpiente;
	Observer observer;
	int velocidad;
	
	Serpiente(double x, double y){
		serpiente = Herramientas.cargarImagen("serpiente.gif");
		this.x = x;
		this.y = y-10;
		this.escala = 3;
		this.al = (int) (serpiente.getHeight((ImageObserver) observer)*escala);
		this.an = (int) (serpiente.getWidth((ImageObserver) observer)*escala);
		this.velocidad = 4;
	}
	
	public void dibujarse(Entorno entorno) {
//		entorno.dibujarRectangulo(x, y, an, al, 0, Color.green); //hitbox
		entorno.dibujarImagen(serpiente, x, y, 0, escala);
	}
	
	public void moverse() {
		this.x -= this.velocidad;
	}
}
