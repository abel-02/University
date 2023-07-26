#a, todas la potencias menores a 2

num=int(input("Ingrese un numero"))

for i in range(num):
    if 2**i<num:
        pot=2**i
        print(pot, end=" ")





#b, las primeras n potencias de 2 (como los gb de almacenamiento)


n=int(input("Ingrese num"))

for i in range(n+1):
    pot=2**1
    print(pot, end=" ")



#c, las primeras n potencias de n


n=int(input("Ingrese un numero"))

for i in range(1, n+1):
    pot=n**i
    print(pot, end=" ")