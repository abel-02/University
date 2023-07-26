#ciudadano argentino, vota?

edad=int(input("ingrese su edad"))

if edad>=18 and edad<=70:
    distancia=float(input("A cuantos kilometrás de distancia se encuentra del centro de votacion"))
    if distancia<500 and distancia>=0:
        print("Felicidades, usted puede votar")
    else:
        print("Lo sentimos, usted no puede votar")
else:
    print("Lo sentimos, usted no puede votar")

