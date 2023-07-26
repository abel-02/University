import math




n=int(input("Ingrese un numero"))
signo=1
suma=0
for i in range(1,n+1):
    if i%2==0:
      signo=-1
    else:
        signo=1
    sucesion=(signo/i)
    suma=suma+sucesion
print(suma)      #Valor aproximado logartimico de 2, mientras mas terminos tenga mas se acerca
print(math.log(2))               #Este es el valor de logaritmico de 2









#El ejercicio de abajo me identifica el termino en el cual mi valor aproximado coincida en los
#primeros 3 decimales con el valor real de log2



import math
log2=math.log(2)
print(log2)

suma=0
i=1
while(abs(log2-suma)>=0.0001):
    termino=1/i
    if i%2==0:
        suma=suma-termino
    else:
        suma=suma+termino
    i=i+1
print("El termino",i,"nos brinda la aproximacion",suma)