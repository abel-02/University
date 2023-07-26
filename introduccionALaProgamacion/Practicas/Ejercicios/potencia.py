def potencia(a,b):
    suma=1
    for i in range(b):
        suma=suma*a
    return suma

n=int(input("ingrese la base"))
m=int(input("ingrese la potencia"))

print(n,"elevado a",m,"es",potencia(n,m))
