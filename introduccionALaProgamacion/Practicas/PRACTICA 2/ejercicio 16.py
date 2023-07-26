anio=int(input("Ingrese año"))

if anio%4==0:
    if (anio%100==0 and anio%400!=0):
       print(anio,"no es bisiesto")
    else:
        print(anio, "es bisiesto")
else:
    print(anio, "No es bisiesto")



