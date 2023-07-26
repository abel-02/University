def primo(n):
    cont=0
    for i in range(1,n+1):
        if n%i==0:
            cont=cont+1
    if cont==2:
        return True
    else:
        return False


def primosN(n):
    primos=[1]  #le agreguè el 1 manualmente, porque sino el programa no funciona
    for i in range(n+1):
        if primo(i):   #es igual a ==True
            primos.append(i)
    return primos



n=int(input(""))
primoss=primosN(n)
print(primoss)