def ordenaLista(listaP, listaN):

    for recorrido in range(1, len(listaP)):
      for posicion in range(len(listaP) - recorrido):

        if listaP[posicion] > listaP[posicion + 1]:
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
    archivo= open("record.txt","r")
    lineas = archivo.readlines()

    for i in lineas:
        i = i.strip("\n")
        lista.append(i)

    for j in range(9,0,-2):
        pun = int(lista[j])
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





newPoint = 65
newUser = "blue"
usuario = []
puntaje = []

record(usuario, puntaje)

reemplazaRec(usuario, puntaje, newPoint, newUser)

guardoRec(usuario, puntaje)

print(usuario)
print(puntaje)




