print ("Este programa calcula los segundos en dias horas minutos y segundos BIEN")
s=int(input("Ingrese la cantidad de segundos: "))

d=s//(60*60*24)
s=s%(60*60*24)
h=s//(60*60)
s=s%(60*60)
m=s//60
s=s%(60)
print ("La cantidad de dias es: ",d)
print ("La cantidad de horas es: ",h)
print ("La cantidad de minutos es: ",m)
print ("La cantidad de segundos es: ",s)