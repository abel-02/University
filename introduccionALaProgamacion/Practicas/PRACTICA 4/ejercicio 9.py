def numMayor(a,b):
    if a>b:
        return a
    else:
        return b

def mayorNum(a,b,c):
    if a>b and a>c:
        return a
    else:
        if b>c:
            return b
        else:
            return c



x=int(input("ingrese un numero"))
z=int(input("Ingrese otro numero"))
y=int(input("Ingrese 3er numero"))

mayor=mayorNum(x,z,y)
print(mayor)