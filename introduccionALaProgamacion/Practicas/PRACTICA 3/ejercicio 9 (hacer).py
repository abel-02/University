n=int(input("Ingrese un numero"))
divisores=0

for i in range(1,n+1):
    if n%i==0:
        print(i, end=" ")
