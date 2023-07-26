a=int(input("Ingrese un numero entero"))
b=int(input("Ingrese otro numero entero"))

if a<b:
    aux=a
    a=b
    b=aux
    if a>b:
        print(a,b)
#   else:
#       print(b,a)


#El programa no muestra si a es mayor que b porque no se pide en la consigna