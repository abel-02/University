def cantDivi(n):
    cont=0
    for i in range(1, n+1):
        if (esDivisor(n,i)):      #Podemos poner (n%i==0)==True
            cont=cont+1
    return cont


def esPrimo(x):
    cant=cantDivi(x)
    if (cant==2):
        return True
    else:
        return False



def numPoderoso(n):
    cont=0
    for i in range(n+1):
        if n%i==0 and (esPrimo(n))==True:
            cont=cont+1
            if n%(i**2)==0:
                contp=contp+1
    if cont==contp:
        return True
    else:
        return False



a=int(input("Ingrese un numero"))
num=numPoderoso(a)
print(num)






