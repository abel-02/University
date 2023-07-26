n = int(input("Ingresa un numero: "))
divisores=0

for i in range(100):
     if i%n==0:
        divisores=divisores+1
        print(divisores, end=" ")