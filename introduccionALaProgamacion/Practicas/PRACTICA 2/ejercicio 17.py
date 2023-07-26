print("Este programa calcula ax + b  = 0")

a = float(input("Ingrese el valor de a"))
b = float(input("Ingrese el valor de b"))

if(a==0 and b==0):
    print("todos los reales")
else:
    if(not a):
        print("no tiene solucion")
    else:
        print("x vale:",(-b)/a)