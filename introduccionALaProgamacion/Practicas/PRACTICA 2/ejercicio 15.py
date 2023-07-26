a=int(input("Ingrese un numero entero"))
b=int(input("Ingrese un segundo numero entero"))
c=int(input("Ingrese un tercer numero entero"))
print("inicial", a, b, c)

if a>c and a>b:
    if b>c:
        aux=a
        a=c
        c=aux
        print(a,b,c)

    else:
        aux=c
        c=a
        a=b
        b=aux
        print(a,b,c)

if b>a and b>c:
     if a>c:
        aux=a
        a=c
        c=b
        b=aux
        print(a,b,c)

     else:
        aux=b
        b=c
        c=aux
        print(a,b,c)

if c>b and c>a:
        if a>b:
           aux=a
           a=b
           b=aux
           print(a,b,c)