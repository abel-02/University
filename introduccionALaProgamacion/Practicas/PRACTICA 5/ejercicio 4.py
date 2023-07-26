def laMascorta(lista1,lista2):
    if (len(lista1)) > (len(lista2)):
        return lista2
    else:
        return lista1



lista1=[1,2,3,4,5]
lista2=[1,2,3,6,9,10]
print(laMascorta(lista2,lista1))