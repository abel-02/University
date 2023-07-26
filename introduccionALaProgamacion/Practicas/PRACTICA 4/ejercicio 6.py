def exclamar(unaCadena):
     return ("!"+unaCadena+"!")

def gritar(unaCadena):
    return ("!!!"+unaCadena+"!!!")     #o bien   return (("!"*3)+unaCadena+("!"+3))





#programa principal
palabra=input("")
print(exclamar(palabra))


# o bienprint(exclamar(exclamar(exclamar(palabra))))     ESTO NO ES VALIDO PORQUE VA A AGARRAR LA CADENA Y LE VA A AGREGAR UN ! EN SUS EXTREMOS !

