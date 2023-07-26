def leoLista():
    lista=[]
    f=open("participantes.txt","r")
    long=int(f.readline())
    for i in range(long):
        lista.append(str(f.readline()))
    f.close()
    return (lista)

def cargoLista(a):
    f=open("participantes.txt","w")
    f.write(str(len(a))+"\n")
    for i in range(len(a)):
        f.write(str(a[i])+"\n")
    f.close()