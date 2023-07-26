#! /usr/bin/env python
import os, random, sys, math
import pygame
import pyphen
from pygame.locals import *
from configuracion import *
from extras import *
from funcionesVACIAS import *

#Funcion principal
def main():
        #Centrar la ventana y despues inicializar pygame
        os.environ["SDL_VIDEO_CENTERED"] = "1"
        pygame.init()
        pygame.mixer.init()

        #Preparar la ventana
        pygame.display.set_caption("El juego del Mago Goma...")
        screen = pygame.display.set_mode((ANCHO, ALTO))

        #Carga icono
        icono = pygame.image.load("imagenes/mago.png")
        pygame.display.set_icon(icono)

        #Cargar imagen de fondo
        background = pygame.image.load("imagenes/magofondo.jpg")
        reglas = pygame.image.load("imagenes/reglas.jpg")
        background_inicio = pygame.image.load("imagenes/rickandmorty.jpg")

        #Carga de sonidos
        acierto = pygame.mixer.Sound("audios/acierto.wav")
        fallo = pygame.mixer.Sound("audios/fallo.wav")
        inicio = pygame.mixer.Sound("audios/juego.mp3")
        fin = pygame.mixer.Sound("audios/gameover.wav")

        #Banderas para la función menuYReglas
        bandera = True
        bandera2 = True
        bandera3 = True

        #Función que muestra el menú y las reglas
        menuYReglas(bandera,bandera2,bandera3, screen)

        #tiempo total del juego
        gameClock = pygame.time.Clock()
        tiempo = pygame.time.get_ticks()/1000
        totaltime = 0
        segundos = TIEMPO_MAX
        fps = FPS_inicial

        #Variables y listas
        puntos = 0
        palabra = ""
        lemarioEnSilabas=[]
        lista_nivel1 = []
        lista_nivel2 = []
        lista_nivel3 = []
        listaNombres = []
        listaPuntos = []
        user = "Username"

        archivo= open("txt/lemario.txt","r")

        #lectura del diccionario
        lectura(archivo, lista_nivel1, lista_nivel2, lista_nivel3, puntos)

        #elige una al azar
        palabraEnPantalla=nuevaPalabra(lista_nivel1, lista_nivel2, lista_nivel3, puntos)

        palabraEnPantallaAnterior=""
        dibujar(screen, palabra, palabraEnPantalla, puntos, segundos)

        inicio.play() #Reproduce sonido de inicio

        #carga los marcadores de puntaje
        record(listaNombres, listaPuntos)

        print(listaNombres)
        print(listaPuntos)

        while segundos > fps/1000:
        # 1 frame cada 1/fps segundos
            gameClock.tick(fps)
            totaltime += gameClock.get_time()

            if True:
            	fps = 3

            #Buscar la tecla apretada del modulo de eventos de pygame
            for e in pygame.event.get():

                #QUIT es apretar la X en la ventana
                if e.type == QUIT:
                    pygame.quit()
                    return()

                #Ver si fue apretada alguna tecla
                if e.type == KEYDOWN:
                    letra = dameLetraApretada(e.key)
                    palabra += letra #es la palabra que escribe el usuario
                    if e.key == K_BACKSPACE:
                        palabra = palabra[0:len(palabra)-1]
                    if e.key == K_RETURN:
                        #pasa la palabra a silabas
                        palabraEnPantallaEnSilabas=silabear(palabraEnPantalla)
                        if esCorrecta(palabraEnPantallaEnSilabas, palabra):
                            puntos += puntaje(palabraEnPantalla) #Se llama a la función puntaje para ver cuantos puntos devuelve la palabra
                            acierto.play() #Reproduce sonido de acierto
                        else:
                            puntos -= 1
                            fallo.play() #Reproduce sonido cuando fallás

                        #elige una al azar
                        palabraEnPantalla=nuevaPalabra(lista_nivel1, lista_nivel2, lista_nivel3, puntos)
                        palabra = ""

            segundos = TIEMPO_MAX - pygame.time.get_ticks()/1000 + tiempo

            #Limpiar pantalla anterior
            screen.blit(background,[0,0])

            #Dibujar de nuevo todo
            dibujar(screen, palabra, palabraEnPantalla, puntos,segundos)

            pygame.display.flip()
            #Guarda nuevo record (si lo hubiese)
        reemplazaRec(listaNombres, listaPuntos, puntos, user)
            #guarda en el txt
        guardoRec(listaNombres, listaPuntos)



        while 1:
            #Esperar el QUIT del usuario
            for e in pygame.event.get():
                if e.type == QUIT:
                    pygame.quit()
                    return

        archivo.close()
#Programa Principal ejecuta Main
if __name__ == "__main__":
    main()
