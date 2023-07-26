#Una empresa telefónica desea un programa para calcular el importe de sus clientes. Sabiendo que el costo por comunicación es de $1 y
#por cada segundo se cobra $0,01 hacer dicho programa. Se deben ingresar la cantidad de llamadas realizadas y el tiempo total de
#comunicación, el programa debe devolver el precio a cobrar.

cantLlamadas=int(input("Ingrese la cantidad de llamadas: "))
tiempo=int(input("Indique cuantos segundos hablo: "))
costoSegundo=0.01

precio=cantLlamadas+tiempo*costoSegundo

print("Usted recibira:$", precio)