package juego;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Observer;

import entorno.Entorno;
import entorno.Herramientas;

@SuppressWarnings({ "deprecation", "unused" })
public class Mono {
	 double y;
	 double x;
	 int an;
	 int al;
	 int pies;
	 int cabeza;
	 double escala;
	 Observer observer;
	 ArrayList<Piedra> piedras;
	 java.awt.Image mono_corriendo;
	
	public Mono() {
		mono_corriendo = Herramientas.cargarImagen("mono.gif");
		y = 420;
		x = 85;
		escala = 0.3;
		
		an = (int) (mono_corriendo.getWidth((ImageObserver) observer)*escala) - 50;
		al = (int) (mono_corriendo.getHeight((ImageObserver) observer)*escala) - 15;
		pies = (int) (y + (al/2));
		cabeza = (int) (y - (al/2));
		piedras = new ArrayList<Piedra>();
	}

	public void dibujarse(Entorno entorno){
//		entorno.dibujarRectangulo(x, y, an, al, 0, Color.red); //hitbox
		entorno.dibujarImagen(mono_corriendo, x-17, y, 0, escala);
		
	}

	public void saltar() {
		y -= 20;
	}
	
	public void caer(Entorno entorno) {
		this.y += 6;
		if(entorno.estaPresionada(entorno.TECLA_ABAJO)) {
			this.y += 10;
		}
		
	}
	
	public void lanzar() {
		piedras.add(new Piedra(this.x, this.y));
	}
	
}


