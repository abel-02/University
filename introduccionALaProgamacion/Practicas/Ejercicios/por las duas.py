def record(nombres, puntos):
    lista = []
    archivo= open("record.txt","r")
    lineas = archivo.readlines()

    for i in lineas:
        i = i.strip("\n")
        lista.append(i)

    for j in range(0,20,2):
        nom = lista[j]
        nombres.append(nom)

    for k in range(1,20,2):
        k = int(k)
        pun = lista[k]
        puntos.append(pun)


def reemplazaRec(users, points, newPuntaje, newUser):
    for i in range(0,len(points)):
        if newPuntaje > int(points[i]):
            masAlto = i
    points[masAlto] = newPuntaje
    users[masAlto] = newUser


def guardoRec(listaNombres, listaPuntos):
    lista = []
    for i in range(0,10):
        n = listaNombres[i]
        p = listaPuntos[i]
        lista.append(n)
        lista.append(p)

    archivo = open("record", "w")


newPoint = 100
newUser = "redox"
usuario = []
puntaje = []
record(usuario, puntaje)

print(usuario)
print(puntaje)
