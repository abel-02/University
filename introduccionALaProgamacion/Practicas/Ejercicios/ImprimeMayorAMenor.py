a=int(input("Ingrese un numero"))
b=int(input("Ingrese un numero"))
c=int(input("Ingrese un numero"))

if (a>b) and (a>c) and (b>c):
    print(a,b,c)
else:
    if (a>c) and (a>b) and (c>b):
        print(a,c,b)
    else:
        if (b>c) and (a>c):
            print(b,a,c)
        else:
            if (b>c) and (a<c):
                print(b,c,a)
            else:
                if (a>b):
                    print(c,a,b)
                else:
                    print(c,b,a)
