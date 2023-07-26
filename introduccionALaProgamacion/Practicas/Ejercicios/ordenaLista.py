lista=[5,4,3,2,1,3.5]
lista2=["cinco","cuatro","tres","dos","uno","tresYcinco"]

def ordenaLista(listaP, listaN):

    for recorrido in range(1, len(listaP)):
      for posicion in range(len(listaP) - recorrido):

        if lista[posicion] > listaP[posicion + 1]:
            temp = listaP[posicion]
            temp1 = listaN[posicion]
            listaP[posicion] = listaP[posicion + 1]
            listaN[posicion] = listaN[posicion + 1]
            listaP[posicion + 1] = temp
            listaN[posicion + 1] = temp1

ordenaLista(lista,lista2)


print(lista)
print(lista2)
