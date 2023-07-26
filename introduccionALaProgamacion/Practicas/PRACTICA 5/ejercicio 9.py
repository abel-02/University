def maximo(lista):
    mayor=lista[0]   #mayor = 2
    for elem in lista:
        if elem > mayor:  #elem=5
            mayor=elem
    return mayor