def pagara(cliente, localidad):
    costo=0
    if cobertura(cliente)=="oro":
        if not radioDeCobertura(cliente, localidad):
            costo=costo+30
    else:
        if radioCobertura(cliente, localidad):
            if usados(cliente)>5:
                costo=costo+50
            else:
                costo=costo+30
                if usados(cliente)>5:
                    costo=costo+50
    return costo