import pygame
import pyphen
from funcionesVACIAS import *
from pygame.locals import *
from configuracion import *
from principal import *

def dameLetraApretada(key):
    if key == K_a:
        return("a")
    elif key == K_b:
        return("b")
    elif key == K_c:
        return("c")
    elif key == K_d:
        return("d")
    elif key == K_e:
        return("e")
    elif key == K_f:
        return("f")
    elif key == K_g:
        return("g")
    elif key == K_h:
        return("h")
    elif key == K_i:
        return("i")
    elif key == K_j:
        return("j")
    elif key == K_k:
        return("k")
    elif key == K_l:
        return("l")
    elif key == K_m:
        return("m")
    elif key == K_n:
        return("n")
    elif key == K_o:
        return("o")
    elif key == K_p:
        return("p")
    elif key == K_q:
        return("q")
    elif key == K_r:
        return("r")
    elif key == K_s:
        return("s")
    elif key == K_t:
        return("t")
    elif key == K_u:
        return("u")
    elif key == K_v:
        return("v")
    elif key == K_w:
        return("w")
    elif key == K_x:
        return("x")
    elif key == K_y:
        return("y")
    elif key == K_z:
        return("z")
    elif key == K_KP_MINUS:
        return("-")
    elif key == K_SPACE:
       return(" ")
    else:
        return("")

def dibujar(screen, palabraUsuario, palabraActual, puntos, segundos):
    fin = pygame.mixer.Sound("audios/gameover.wav")
    defaultFont= pygame.font.Font( pygame.font.get_default_font(), TAMANNO_LETRA)
    defaultFontGrande= pygame.font.Font( pygame.font.get_default_font(), TAMANNO_LETRA_GRANDE)

    #Linea Horizontal
    pygame.draw.line(screen, (0,0,0), (0, ALTO-70) , (ANCHO, ALTO-70), 5)

    #muestra lo que escribe el jugador
    screen.blit(defaultFont.render("HACE MAGIA:",1,(200,200,200)), (30,570))
    screen.blit(defaultFont.render(palabraUsuario, 1, COLOR_TEXTO), (190, 570))
    #muestra el puntaje
    screen.blit(defaultFont.render("Puntos: " + str(puntos), 1, COLOR_TEXTO), (680, 10))

    #Sube/baja el nivel
    if (puntos<=50):
        ren1=defaultFont.render("Nivel 1",1,COLOR_TEXTO)
        NIVEL = 1
    elif (puntos>50 and puntos <100):
        ren1=defaultFont.render("Nivel 2",1,COLOR_TEXTO)
        NIVEL = 2
    elif (puntos >=100):
        ren1=defaultFont.render("Nivel 3",1,COLOR_TEXTO)
        NIVEL = 3
    screen.blit(ren1,(400,10))

    #muestra los segundos y puede cambiar de color con el tiempo
    if(segundos<15):
        ren = defaultFont.render("Tiempo: " + str(int(segundos)), 1, COLOR_TIEMPO_FINAL)
    else:
        ren = defaultFont.render("Tiempo: " + str(int(segundos)), 1, COLOR_TEXTO)
    screen.blit(ren, (10, 10))

    #Reproduce sonido de fin del juego (¡gameover!)
    if (segundos < 1):
        fin.play()

    #muestra la palabra
    if puntos <50:
        screen.blit(defaultFontGrande.render(palabraActual, 1, COLOR_LETRAS), (ANCHO//2-len(palabraActual)*TAMANNO_LETRA_GRANDE//4,ALTO-400))
    elif puntos >= 50 and puntos <100:
        screen.blit(defaultFontGrande.render(palabraActual, 1, AMARILLO), (ANCHO//2-len(palabraActual)*TAMANNO_LETRA_GRANDE//4,ALTO-400))
    else:
        screen.blit(defaultFontGrande.render(palabraActual, 1, ROJO), (ANCHO//2-len(palabraActual)*TAMANNO_LETRA_GRANDE//4,ALTO-400))

def menuYReglas(bandera,bandera2,bandera3, screen):
    while bandera == True:
        background_inicio = pygame.image.load("imagenes/rickandmorty.jpg")  #Se carga el fondo
        defaultFont= pygame.font.Font( pygame.font.get_default_font(), 25)
        texto=defaultFont.render("EL JUEGO DEL MAGO GOMA",True,COLOR_CONF)
        texto2=defaultFont.render("PRESIONE ESPACIO PARA VER LAS REGLAS",True,COLOR_CONF)
        texto3=defaultFont.render("PRESIONE P PARA VER LOS PUNTAJES",True,COLOR_CONF)
        screen.blit(background_inicio,[0,0])
        screen.blit(texto,(220,20))
        screen.blit(texto2,(140,130))
        screen.blit(texto3,(210,160))
        pygame.display.update()
        for e in pygame.event.get():
            if e.type == QUIT:
                pygame.quit()
                return()
            if e.type == KEYDOWN:
                if dameLetraApretada(e.key) == " ":  #Si se presiona espacio se ven las reglas
                    bandera = False
            if e.type == KEYDOWN:
                if dameLetraApretada(e.key) == "p":  #Si se presiona la p se inicia el juego
                    bandera = False
                    bandera2 = False   #Cuando las dos bandera están en falso, se terminan los dos ciclos y arranca el juego
    while bandera == False and bandera2 == True:
        reglas = pygame.image.load("imagenes/reglas.jpg")
        screen.blit(reglas,[0,0])
        defaultFont= pygame.font.Font( pygame.font.get_default_font(), TAMANNO_LETRA)
        texto=defaultFont.render("REGLAS DEL JUEGO",True,COLOR_CONF)
        regla1=defaultFont.render("1. El juego consiste en separar las palabras en sílabas",True,COLOR_CONF)
        regla2=defaultFont.render("2. Se suman 5 puntos por cada acierto y se resta 1 por cada error",True,COLOR_CONF)
        regla3=defaultFont.render("3. Niveles:",True,COLOR_CONF)
        regla3_1=defaultFont.render("   Nivel 1: 50 puntos o menos",True,COLOR_CONF)
        regla3_2=defaultFont.render("   Nivel 2: más de 50 puntos y menos de 100",True,COLOR_CONF)
        regla3_3=defaultFont.render("   Nivel 3: 100 puntos o más",True,COLOR_CONF)
        regla4=defaultFont.render("4. Duración del juego 120 segundos",True,COLOR_CONF)
        opcion=defaultFont.render("PULSA P PARA JUGAR",True,COLOR_CONF)
        copy_right=defaultFont.render("Comisión 5 ©",True,COLOR_CONF)
        screen.blit(texto,(270,50))
        screen.blit(regla1,(110,80))
        screen.blit(regla2,(110,110))
        screen.blit(regla3,(110,140))
        screen.blit(regla3_1,(110,170))
        screen.blit(regla3_2,(110,200))
        screen.blit(regla3_3,(110,230))
        screen.blit(regla4,(110,260))
        screen.blit(opcion,(280,420))
        screen.blit(copy_right,(10,580))
        pygame.display.update()
        for e in pygame.event.get():
            if e.type == QUIT:
                pygame.quit()
                return()
            if e.type == KEYDOWN:
                if dameLetraApretada(e.key) == "p":
                    bandera2 = False
    while bandera == False and bandera2 == False and bandera3 == True:
            lista_users = []
            lista_scores = []
            record(lista_users,lista_scores)
            score_fondo = pygame.image.load("imagenes/score.jpg")
            screen.blit(score_fondo,[0,0])
            defaultFont= pygame.font.Font( pygame.font.get_default_font(), TAMANNO_LETRA)
            score=defaultFont.render("RECORD DE SCORES MÁGICOS",True,COLOR_CONF)
            play=defaultFont.render("PULSA P PARA JUGAR",True,COLOR_CONF)
            #variables de puntaje
            scores=defaultFont.render(lista_scores[4],True,COLOR_CONF)
            users=defaultFont.render(lista_users[4],True,COLOR_CONF)
            scores2=defaultFont.render(lista_scores[3],True,COLOR_CONF)
            users2=defaultFont.render(lista_users[2],True,COLOR_CONF)
            scores3=defaultFont.render(lista_scores[2],True,COLOR_CONF)
            users3=defaultFont.render(lista_users[2],True,COLOR_CONF)
            scores4=defaultFont.render(lista_scores[1],True,COLOR_CONF)
            users4=defaultFont.render(lista_users[1],True,COLOR_CONF)
            scores5=defaultFont.render(lista_scores[0],True,COLOR_CONF)
            users5=defaultFont.render(lista_users[0],True,COLOR_CONF)
            #Ubicación del puntaje en pantalla
            screen.blit(scores,(270,190))
            screen.blit(users,(270,170))
            screen.blit(scores2,(270,240))
            screen.blit(users2,(270,220))
            screen.blit(scores3,(270,290))
            screen.blit(users3,(270,270))
            screen.blit(scores4,(270,340))
            screen.blit(users4,(270,320))
            screen.blit(scores5,(270,390))
            screen.blit(users5,(270,370))
            screen.blit(score,(270,50))
            screen.blit(play,(270,90))
            pygame.display.update()
            for e in pygame.event.get():
                if e.type == QUIT:
                    pygame.quit()
                    return()
                if e.type == KEYDOWN:
                    if dameLetraApretada(e.key) == "p":
                        bandera3 = False

def ordenaLista(listaP, listaN):

    for recorrido in range(1, len(listaP)):
      for posicion in range(len(listaP) - recorrido):

        if int(listaP[posicion]) > int(listaP[posicion + 1]):
            temp = listaP[posicion]
            temp1 = listaN[posicion]
            listaP[posicion] = listaP[posicion + 1]
            listaN[posicion] = listaN[posicion + 1]
            listaP[posicion + 1] = temp
            listaN[posicion + 1] = temp1

def darVuelta(lista1, lista2):
    listaP = []
    listaN = []
    for i in range(5, 0, -1):
        listaP.append(lista1[i])
        listaN.append(lista2[i])
    lista1 = listaP
    lista2 = listaN


def record(nombres, puntos):
    lista = []
    archivo= open("txt/record.txt","r")
    lineas = archivo.readlines()

    for i in lineas:
        i = i.strip("\n")
        lista.append(i)

    for j in range(9,0,-2):
        pun = lista[j]
        puntos.append(pun)

    for k in range(8,(0-1),-2):
        k = int(k)
        nom = lista[k]
        nombres.append(nom)

def reemplazaRec(listaN, listaP, newPuntaje, newUser):
    listaN.append(newUser)
    listaP.append(newPuntaje)
    ordenaLista(listaP, listaN)
    listaP.reverse()
    listaN.reverse()

def guardoRec(listaNombres, listaPuntos):
    lista = []
    for i in range(0,5):
        n = listaNombres[i]
        p = str(listaPuntos[i])
        lista.append(n)
        lista.append(p)
    f = open("record.txt", "w")
    for k in range(0,len(lista)):
        carac = str(lista[k])
        f.write(carac + "\n")

    f.close()


