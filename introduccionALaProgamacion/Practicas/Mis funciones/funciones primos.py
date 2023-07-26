def esDivisor(m,n):
    return m%n==0


def cantDivi(n):
    cont=0
    for i in range(1, n+1):
        if (esDivisor(n,i)):      #Podemos poner (n%i==0)==True
            cont=cont+1
    return cont


def esPrimo(x):
    cant=cantDivi(x)
    if (cant==2):
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



