n=int(input("Ingrese un numero"))
sucesion1=0
for i in range(1,n+1):
    sucesion=2*i
    sucesion1=sucesion1+sucesion
    print(sucesion1, end=" ")


#b


n=int(input("Ingrese un numero"))
sucesion1=0
for i in range(1,n+1):
    sucesion=i**2
    sucesion1=sucesion1+sucesion
    print(sucesion1, end=" ")


#c

n=int(input("Ingrese un numero"))
sucesion1=0
for i in range(1,n+1):
    sucesion=(i**3)-(i**2)
    sucesion1=sucesion1+sucesion
    print(sucesion1, end=" ")


#d

n=int(input("Ingrese un numero"))
sucesion1=0
for i in range(1,n+1):
    sucesion=1/(i**2)
    sucesion1=sucesion1+sucesion
    print(sucesion1, end=" ")

