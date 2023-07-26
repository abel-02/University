kwi=200
cinicial=480
cexcedente=25.5
consumo=int(input("Ingrese la cantidad de kw que consumiō"))

if consumo>kwi:
    impuesto=78
    e= consumo-kwi
    ce= e * cexcedente
    pfinal= cinicial + ce + impuesto
    print("El importe de su factura es de ", pfinal, "$")

else:
    pfinal1=cinicial
    print("El importe de su factura es de ", pfinal1, "$")