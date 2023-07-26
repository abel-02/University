print ("Este programa ordena tres numeros de mayor a menor")
a=int(input("Ingrese el primero"))
b=int(input("Ingrese el primero"))
c=int(input("Ingrese el primero"))

#El mayor es A
if(a>=b and a>=c):
    print(a,end=" ")
    if(b>=c):
        print(b,c)
    else:
        print (c,b)
else:
    #El mayor es B
    if(b>=a and b>=c):
        print(b,end=" ")
        if(a>=c):
            print(a,c)
        else:
           print(c,a)
    else:
        #El mayor es C
        #if(c>a and c>b):
            print(c,end=" ")
            if(a>=b):
                print(a,b)
            else:
                print(b,a)
