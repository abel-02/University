def swap (lista,i,j):
    aux=lista(i)
    lista[i]=lista[j]
    lista[j]=aux

li=[25,45,8,14]
lo=len(li)
i1=int(input("Ingrese un indice a intercambiar"))
i2=int(input("Ingrese otro indice para intercambiarlo por el primero"))
if i1>=0 and i2>=0 and i1<lo and i2<lo:  #VERIFICACION DE DATOS, EL LO PUDO HABER SIDO LEN PERO PARA OPTMIIZARLO LO GUUARDAMOS EN UNA VARIABLE
    print(li)
    swap(li,i1,i2)
    print(li)

else:
    print("Indices incorrectos")

