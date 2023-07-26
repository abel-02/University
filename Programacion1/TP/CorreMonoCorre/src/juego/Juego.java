package juego;


import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import javax.sound.sampled.Clip;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	Mono mono;
	Suelo piso;
	Saco saco;
	Basura basura;
	ArrayList<Arbol> arboles;
	ArrayList<Piedra> piedras;
	ArrayList<Tigre> tigres;
	ArrayList<Basura> basuras;
	ArrayList<Corazon> corazones;
	int tiempo_sal;
	int tiempo_disparo;
	int pos_inicial;
	int separacion;
	int cantPiedras;
	int puntaje;
	int puntos_rama;
	int cont;
	int record;
	int vidas;
	int obtenerVida;
	Arbol a;
	java.awt.Image fondo;
	Image gameOver;
	Clip musica;
	
//	Clip musica;
	// Variables y métodos propios de cada grupo
	// ...

	Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Selva Mono Capuchino - Grupo 6 - v1", 800, 600);
		// Inicializar lo que haga falta para el juego
		// ...
		fondo = Herramientas.cargarImagen("fondo2.gif");
		gameOver = Herramientas.cargarImagen("gameOver.png");
		tiempo_disparo = 0;
		tiempo_sal = 0;
		saco = new Saco();
		mono = new Mono();
		piso = new Suelo();
		basura = new Basura();
		a = new Arbol(1200, piso); 
		arboles = new ArrayList<Arbol>();
		tigres = new ArrayList<Tigre>();
		basuras = new ArrayList<Basura>();
		corazones = new ArrayList<Corazon>();
		corazones.add(new Corazon(0));
		corazones.add(new Corazon(20));
		corazones.add(new Corazon(40));
	    pos_inicial = 800;
	    separacion = 200;
	    cantPiedras = 0;
	    vidas = 3;
	    puntaje = 0;
	    obtenerVida = 0;
	    cont = 0;
	    record = 0;
	    

		// Inicia el juego!
		this.entorno.iniciar();
		
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y por lo
	 * tanto es el método más importante de esta clase. Aquí se debe actualizar el
	 * estado interno del juego para simular el paso del tiempo (ver el enunciado
	 * del TP para mayor detalle).
	 */
	public void tick() {
		entorno.dibujarImagen(fondo, 400, 240, 0, 1);
		entorno.cambiarFont("", 20, Color.white);
		entorno.escribirTexto("Puntaje:"+puntaje, 680, 70);
		entorno.escribirTexto("Piedras:"+cantPiedras, 680, 50);
		
		//Crear corazones
		if(obtenerVida >= 10 && vidas <= 3) {
			for(Corazon c : corazones) {
				if(c.lastimado == true) {
					c.lastimado = false;
					vidas += 1;
					obtenerVida = 0;
					break;
				}
			}
		}
		
		//dibujar corazones
//		if(corazones != null) {
			for(Corazon c : corazones) {
				c.dibujarse(entorno);
//			}
		}
		
		//Crear arboles
		if(arboles != null && arboles.size() <= 1000) {
			arboles.add(new Arbol(pos_inicial += (separacion+Math.random()*200), piso));
		}
		
		// Salto
		if(mono != null && entorno.estaPresionada(entorno.TECLA_ARRIBA) && tiempo_sal < 15) {
			tiempo_sal += 1;
			mono.saltar();
		}
		// Caer 
		if(mono != null && mono.y < 435) {
			mono.caer(entorno);
		}else {
			tiempo_sal = 0;
			cont = 0;
		}

		//Lanzar piedra
		if(mono != null && entorno.sePresiono(entorno.TECLA_ESPACIO) && cantPiedras > 0) {
			mono.lanzar();
			cantPiedras -= 1;
		}
	
		//Dibujar piso
		piso.dibujarse(entorno);
		
		if(arboles != null) {
			//Recorro los arboles
			for(Arbol ar : arboles) {
				if(ar != null) {
					//Dibujar arboles
					ar.dibujarse(entorno);
					ar.moverse();
					//dibujar ramas
					if(ar.rama != null) {
						ar.rama.dibujarse(entorno);
						ar.rama.moverse();
						//dibujar frutas
						if(ar.rama.frutas != null) {
							for(Fruta f : ar.rama.frutas) {
								if(f!=null) {
									f.dibujarse(entorno);
									f.moverse();
								}
							}
						}
						//dibujar serpientes
						if(ar.rama.serpiente != null) {
							ar.rama.serpiente.dibujarse(entorno);
							ar.rama.serpiente.moverse();
						}
					}
					if(mono != null) {
						//Que el mono pueda subirse a una rama y sume puntos por ello
						if(ar.rama != null && mono.y < ar.rama.superficie && intersecan(mono.x, ar.rama.x, mono.y, ar.rama.y, mono.al, ar.rama.al, mono.an, ar.rama.an)) {
							mono.y = ar.rama.y - mono.al/2 - ar.rama.al/2;
							tiempo_sal = 0;
							cont += 1;
							if(cont == 1 || !ar.equals(a)) {
								puntaje += 5;
								obtenerVida += 5;
								a = ar;
							}
						}
						//Que el mono no atraviese la rama desde abajo
						if(ar.rama != null && ar.rama != null && entorno.estaPresionada(entorno.TECLA_ARRIBA) && mono.cabeza > ar.rama.bottom && intersecan(mono.x, ar.rama.x, mono.y, ar.rama.y, mono.al, ar.rama.al, mono.an, ar.rama.an)) {
							mono.y = ar.rama.y + mono.al/2 + ar.rama.al/2;
						}
						//Frutas
						if(ar.rama != null && ar.rama.frutas != null) {
							for(Fruta f : ar.rama.frutas) {
								//Agarrar frutas
								if(f!=null && intersecan(mono.x, f.x, mono.y, f.y, mono.al, f.al, mono.an, f.an)) {
									ar.rama.frutas.set(ar.rama.frutas.indexOf(f), null);
									puntaje += 10;
									obtenerVida += 10;
								}
								//Eliminar frutas fuera de la pantalla
								if(f!=null && f.x < 0-f.an/2) {
									ar.rama.frutas.set(ar.rama.frutas.indexOf(f), null);
								}
							}
						}
						//Matar serpientes
						if(mono.piedras != null && ar.rama != null && ar.rama.serpiente != null) {
							for(int i = 0 ; i<mono.piedras.size() ; i++) {
								if(ar.rama.serpiente != null && intersecan(ar.rama.serpiente.x, mono.piedras.get(i).x, ar.rama.serpiente.y, mono.piedras.get(i).y, ar.rama.serpiente.al, mono.piedras.get(i).al, ar.rama.serpiente.an, mono.piedras.get(i).an)) {
									ar.rama.serpiente = null;
									mono.piedras.remove(i);
								}
							}
						}
						//Que la serpiente mate al mono
						if(ar.rama != null && ar.rama.serpiente != null && intersecan(mono.x, ar.rama.serpiente.x, mono.y, ar.rama.serpiente.y, mono.al, ar.rama.serpiente.al, mono.an, ar.rama.serpiente.an)) {
							mono = null;
							puntaje -= 50;
							obtenerVida = 0;
							for(int i = corazones.size()-1 ; i>-1 ; i--) {
								if(corazones.get(i).lastimado == false) {
									corazones.get(i).lastimado = true;
									vidas -= 1;
									break;
								}
							}
						}
					}
					//Eliminar arboles fuera de pantalla
					if(ar.x < 0 - ar.an/2) {
						arboles.set(arboles.indexOf(ar), null);
					}
				}
			}
		}
		//Dibujar basura
		if(basuras != null && (int) (Math.random()*400) == 50 && basuras.size() != 200) {
			basuras.add(new Basura());
		}
		if(basuras != null) {
			for(Basura b : basuras) {
				if(b != null) {
					b.moverse();
					b.dibujarse(entorno);
					
					if(mono != null) {
						if(intersecan(mono.x, b.x, mono.y, b.y, mono.al, b.al, mono.an, b.an)){
							basuras.set(basuras.indexOf(b), null);
							puntaje -= 5;
							obtenerVida -= 5;
						}
					}
					if(b != null && b.x < 0 - b.an/2) {
						basuras.set(basuras.indexOf(b), null);
					}
				}
			}
		}
	
		//Crear tigres
		if(tigres != null && (int) (Math.random()*250) == 100 && tigres.size() != 1000) {
				tigres.add(new Tigre());
		}
		
		if(tigres != null) {
			for(Tigre t : tigres) {
				if(t != null) {
					//Dibujar tigre y moverlo
					t.dibujarse(entorno);
					t.moverse();
					
					for(Tigre t_ : tigres) {
						//Separar los tigres para que no se superpongan
						if(t_ != null && !t_.equals(t) && intersecan(t.x, t_.x, t.y, t_.y, t.al, t_.al, t.an, t_.an)) {
							t_.x += 20;
						}
					}
					//Que el mono mate al tigre
					if(mono != null) {
						if(mono.piedras != null) {
							for(int i = 0; i<mono.piedras.size(); i++) {
								if(tigres.contains(t) && mono.piedras.get(i) != null && intersecan(mono.piedras.get(i).x, t.x, mono.piedras.get(i).y, t.y, mono.piedras.get(i).al, t.al, mono.piedras.get(i).an, t.an)) {
									mono.piedras.remove(i);
									tigres.set(tigres.indexOf(t), null);
								}
							}
						}
						//Que el tigre mate al mono
						if(intersecan(mono.x, t.x, mono.y, t.y, mono.al, t.al, mono.an, t.an)) {
							mono = null;
							puntaje -= 50;
							obtenerVida = 0;
							for(int i = corazones.size()-1 ; i>-1 ; i--) {
								if(corazones.get(i).lastimado == false) {
									vidas -= 1;
									corazones.get(i).lastimado = true;
									break;
								}
							}
							
						}
					}
					//eliminar tigre cuando sale de pantalla
					if(t.x < 0 - t.an/2 ) {
						tigres.set(tigres.indexOf(t), null);
					}
				}
			}
		}
		
		//Dibujar saco
		if(saco != null) {
			saco.dibujarse(entorno);
			saco.moverse();
			if(saco.x < 0 - saco.an/2) {
				saco = null;
			}
		}
		//Pickear saco
		if(saco != null && mono != null && intersecan(mono.x, saco.x, mono.y, saco.y, mono.al, saco.al, mono.an, saco.an)) {
			saco = null;
			cantPiedras += 10;
		}
		//Generar saco
		if(saco == null && cantPiedras <= 3  && (int) (Math.random()*450) == 50) {
			saco = new Saco();
		}
		//dibujar piedras
		if(mono != null && mono.piedras != null) {
			for(Piedra p : mono.piedras) {
				if(p!= null) {
					p.dibujarse(entorno);
					p.moverse();
				}
			}
		}
		
		//Dibujar mono
		if(mono != null) {
			mono.dibujarse(entorno);
		}
		//Respawn
		if(mono == null && entorno.estaPresionada(entorno.TECLA_ESPACIO) && vidas > 0) {
			mono = new Mono();
		}

		
		//Eliminar piedras que salen del mapa
		if(mono != null) {
			for(int i = 0 ; i < mono.piedras.size() ; i++) {
				if(mono.piedras.get(i).x > 800) {
					mono.piedras.remove(i);
					}
				}
		}
		
		if(mono == null && puntaje > record) {
			record = puntaje;
		}
		
		if(arboles != null && tigres != null && basuras != null && arboles.get(arboles.size()-1) == null || vidas == 0){
			mono = null;
			arboles = null;
			tigres = null;
			piedras = null;
			basuras = null;
			entorno.cambiarFont("", 50, Color.white);
			entorno.escribirTexto("Puntaje: "+puntaje, 250, 400);
			entorno.dibujarImagen(gameOver, 400, 200, 0, 1.5);
		}
		
			
	}
	//Interseccion de Bloques
	public boolean intersecan(double x1, double x2, double y1, double y2, int al1, int al2, int an1, int an2) {
		boolean intersecY = false;
		double distanciaY = Math.max(y1, y2) - Math.min(y1, y2);
		double sumaAlt = (al1/2) + (al2/2);
		if(distanciaY - sumaAlt < 0) {
			intersecY = true;
		}
		
		boolean intersecX = false;
		double distanciaX = Math.max(x1, x2) - Math.min(x1, x2);
		double sumaAnch = (an1/2) + (an2/2);
		if(distanciaX - sumaAnch < 0) {
			intersecX = true;
		}
		
		if(intersecX && intersecY) {
			return true;
		}else {
			return false;
		}
		
	}
	
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
	
	
}
