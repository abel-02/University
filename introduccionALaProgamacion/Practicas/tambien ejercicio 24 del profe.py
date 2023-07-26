def cobertura(cliente):  #hacer de cuenta que ya esta hecho y usarlo para lo que se usa


def usados(cliente): #hacer de cuenta que ya esta hecho y usarlo para lo que se usa


def radioDeCobertura(cliente, localidad):   #hacer de cuenta que ya esta hecho y usarlo para lo que se usa


def pagara(cliente, localidad):
    costo=0
    if cobertura(cliente)=="oro" and not radioDeCobertura(cliente, localidad):            #si es categoria oro y esta fuera de la cobertura(paga mas por estar fuera de cobertura).
      costo=30
    else:
        if cobertura(cliente)=="plata" and usados(cliente)>5 and radioDeCobertura(cliente, localidad)==True:
            costo=50             #plata, mas de 5 servicios y dentro del rango de cobertura
        else:
            if not radioDeCobertura(cliente, localidad) and cobertura(cliente)=="plata":
                costo=80                #sin grandes servicios y fuera de cobertura
            else:
                if cobertura(cliente)=="plata"         #dentro de cobertura y sin grandes servicios (no se si ya esta bien porque cambie el codigo) fijate abel del futuro
                costo=50
    return costo