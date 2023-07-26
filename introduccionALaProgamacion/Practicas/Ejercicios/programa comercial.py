
print("Introduzca ¨1¨ para elejir el procesador i5, ¨2¨ para  el procesador i7 o ¨3¨ para el i9 ")
proce=int(input("Digite la opción del procesador que desea adquirir"))

while proce!=1 and proce!=2 and proce!=3:
        proce=int(input("Ingrese una opción válida"))

if proce==1:
       costo=600
else:
     if proce==2:
        costo=700
     else:
        costo=1000

ram=int(input("Digite ¨1¨ para seleccionar 8gb de ram, ¨2¨ para 16gb y ¨3¨ para 32gb"))

while ram!=1 and ram!=2 and ram!=3:
    ram=int(input("Ingrese una opción válida"))

if ram==1:
     costo=costo+200
else:
    if ram==2:
        costo=costo+300
    else:
        costo=costo+400

disco=int(input("¿Desea expandir la capacidad del disco? Seleccione ¨1¨ para si. ¨0¨ para no"))

while disco!=1 and disco!=0:
    disco=int(input("Ingrese una opción válida"))

if disco==1:
    costo=costo+300


print("\n\nEl importe de su compra es de", costo,"$")

