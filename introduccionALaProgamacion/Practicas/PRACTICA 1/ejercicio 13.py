interes=6
capital=int(input("Ingrese su capital a invertir"))
meses=int(input("¿Cuántos meses desea depositarlo?"))
capitalParcial=((capital*interes)/100)*meses
capitalFinal=capitalParcial+capital
print("Su capital final será:",capitalFinal)