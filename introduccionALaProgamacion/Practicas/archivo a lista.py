def leoLista():
    lista=[]
    f=open("fichero.txt","r")
    long=int(f.readline())
    for i in range(long):
        lista.append(int(f.readline()))
    f.close()
    return (lista)

l=leoLista()
print(l)