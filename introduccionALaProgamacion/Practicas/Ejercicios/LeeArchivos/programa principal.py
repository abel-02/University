from funciones import*

participantes=[]
n=""
while n!="0":
    n=input("Por favor, ingrese el nombre del participante. (Ingrese 0 para finalizar")
    if n!="0":
      participantes.append(n)


exporto=cargoLista(participantes)
importo=leoLista()
print(importo)
