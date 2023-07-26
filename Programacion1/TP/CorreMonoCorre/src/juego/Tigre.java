package juego;

import java.awt.Color;
import java.awt.image.ImageObserver;
import java.util.Observer;


import entorno.Entorno;
import entorno.Herramientas;

@SuppressWarnings({ "deprecation", "unused" })
public class Tigre {
		double x;
		double y;
		int al;
		int an;
		double escala;
		java.awt.Image tigre;
		Observer observer;
		int velocidad;
		
		Tigre(){
			tigre = Herramientas.cargarImagen("tigre.gif");
			escala = 0.5;
			al = (int) (tigre.getHeight((ImageObserver) observer)*escala);
			an = (int) (tigre.getWidth((ImageObserver) observer)*escala)-25;
			x = 900;
			y = 420;
			this.velocidad = 6;
		}
		
		public void dibujarse(Entorno e) {
//			e.dibujarRectangulo(x, y, an, al, 0, Color.blue); //hitbox
			e.dibujarImagen(tigre, x+15, y, 0, escala);
		}
		
		public void moverse() {
			x -= this.velocidad;
		}
		
}

