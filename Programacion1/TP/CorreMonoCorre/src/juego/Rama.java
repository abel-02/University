package juego;

import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Observer;

import entorno.Entorno;
import entorno.Herramientas;

@SuppressWarnings("deprecation")
public class Rama {
	double x;
	double y;
	int al;
	int an;
	int superficie;
	int bottom;
	double principio;
	double fin;
	double escala;
	int random;
	int velocidad;
	Serpiente serpiente;
	ArrayList<Fruta> frutas;
	Observer observer;
	java.awt.Image rama;
	
	public Rama(double x, double y) {
		rama = Herramientas.cargarImagen("rama.png");
		this.x = x;
		this.y = y;
		this.escala = 0.6;
		this.an = (int) (rama.getWidth((ImageObserver) observer)*escala);
		this.al = (int) (rama.getHeight((ImageObserver) observer)*escala);
		this.superficie = (int) (y - (al/2));
		this.bottom = (int) (y + (al/2));
		this.principio = (x - an/3);
		this.fin = (x + an/3);
		this.velocidad = 4;
		
		
		//Posibilidad de que tenga una serpiente
		int random = (int) (Math.random()*10);
		
		if(random < 5) {
			serpiente = new Serpiente(this.x, this.y - 20);
		}
		if(random > 4) {
			frutas = new ArrayList<Fruta>();
			if(random > 7) {
				frutas.add(new Fruta(principio, y-20));
				frutas.add(new Fruta(x, y-20));
				frutas.add(new Fruta(fin, y-20));
			}else {
				frutas.add(new Fruta(principio+20, y-20));
				frutas.add(new Fruta(fin-20, y-20));
			}
			
		}
		
	}
	
	public void dibujarse(Entorno entorno) {
		entorno.dibujarImagen(rama, x, y, 0, escala);
	}
	
	public void moverse() {
		this.x -= this.velocidad;
	}
	
	
}
