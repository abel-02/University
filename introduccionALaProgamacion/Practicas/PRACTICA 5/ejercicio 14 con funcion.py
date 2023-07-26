def esta(lista, n):
    for elem in lista:
        if n==elem:
            return True
    return False

def interseccion(lista1,lista2):
    listaNueva=[]
    i=0
    while(i<len(lista1)):
        if (esta(lista2, lista1[i])):
            listaNueva.append(lista1[i])
        i=i+1
    return listaNueva




#Programa principal


conjuntoA=[2,3,4,5,6,7]
conjuntoB=[8,9,4,11,3]
#interseccion=[3,4]

resultado=interseccion(conjuntoA, conjuntoB)
print(resultado)






