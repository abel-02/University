def divisoresPositivos(a):
    cont=0
    for i in range(1,a+1):
        if a%i==0:
            cont=cont+1
    return cont


def esPrimo(a):
    cant=divisoresPositivos(a)
    if cant==2:
        return True
    else:
        return False

def factorPri(n):
    i=2
    while(i<=n):
       if(esPrimo(i) and n%i==0):

             n=n/i
             print(i)

       else:
             i=i+1




#main



