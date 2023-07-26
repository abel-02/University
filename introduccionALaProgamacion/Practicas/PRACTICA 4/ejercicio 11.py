def divisoresPropios(n):
    cont=0
    for i in range(1, n):
        if n%i==0:
            cont=cont+i
    return cont


def numPerfecto(n):
    if divisoresPropios(n)==n:
        return True
    else:
        return False

def numAbundante(n):
    if divisoresPropios(n)>n:
        return True
    else:
        return False


n=int(input("Ingrese un numero"))

divi=numAbundante(n)
print(divi)