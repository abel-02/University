def interseccion(lista1,lista2):
    listaNueva=[]
    for i in lista1:
        for j in lista2:
            if (i==j):   #esto no lo sabia
               listaNueva.append(i)
    return listaNueva




#Programa principal


conjuntoA=[2,3,4,5,6,7]
conjuntoB=[8,9,4,11,3]
#interseccion=[3,4]

resultado=interseccion(conjuntoA, conjuntoB)
print(resultado)






