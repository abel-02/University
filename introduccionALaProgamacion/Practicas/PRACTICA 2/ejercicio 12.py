nota=float(input("ingrese su nota"))

if nota>=1 and nota<=10:
    if nota>=4:
        if nota>=7:
            print("Felicitaciones, promocionaste")
        else:
            print("Enhorabuena, deberas rendir el examen final")
    else:
        print("Lo sentimos, reprobaste")
else:
    print("Por favor, ingrese una nota vālida")