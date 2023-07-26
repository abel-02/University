from principal import *
from configuracion import *
import random
import math
import pyphen

def lectura(archivo, salida, salida2, salida3, puntos):
    long = archivo.readlines()
    for elem in long:
        if "ñ" not in elem:
            elem = elem.strip("\n")
            elem.lower()
            if len(elem) <= 6:
                salida.append(elem)
            if len (elem) > 6 and len(elem) <= 10:
                salida2.append(elem)
            if len(elem) > 10:
                salida3.append(elem)

def nuevaPalabra(lista, lista2, lista3, puntos):
    if puntos < 50:
        palabraRandom = random.choice(lista)
    if puntos >= 50 and puntos <= 100:
        palabraRandom = random.choice(lista2)
    if puntos > 100:
        palabraRandom = random.choice(lista3)
    return palabraRandom

def silabasTOpalabra(silaba):
    palabra_nueva = ""
    for char in silaba:
        if char == "-" and char == " ":
            char = ""
            palabra_nueva = palabra_nueva + char
        palabra_nueva = palabra_nueva + char
    return palabra_nueva

def silabear(palabra):
    palabraTexto = str(palabra)
    a = pyphen.Pyphen(lang="es")
    palabraSilaba = a.inserted(palabraTexto)
    return palabraSilaba

def esCorrecta(palabraEnSilabasEnPantalla, palabra):
    palabra_nueva = ""
    for char in palabra:
        if char == " ":
            char = "-"
        palabra_nueva = palabra_nueva + char
    if palabraEnSilabasEnPantalla == palabra_nueva:
        return True

def puntaje(palabra):
    if len(palabra) <= 6:
        return 3
    elif len(palabra) > 6 and len(palabra) <=10:
        return 5
    else:
        return 10