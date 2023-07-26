def cargoLista(a):
    f=open("fichero.txt","w")
    f.write(str(len(a))+"\n")
    for i in range(len(a)):
        f.write(str(a[i])+"\n")
    f.close()


li=[100,200,300,400,500,600]
lista=cargoLista(li)