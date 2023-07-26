palabra=input("Ingresar una palabra")
letra=input("Ingresar un letra")
cadenaVacia="" #No se puede separar en las cadenas, tenemos que usar esto

for char in palabra:
    if char==letra:
        cadenaVacia=cadenaVacia+"*"  #No puedo romper la palabra y poner el asterisco, hay que trabajarlo asi (Dijo el profe)
    else:
        cadenaVacia=cadenaVacia+char

print(cadenaVacia)