def divisores(n):
    listaDiv=[]
    for i in range(1,n+1):
        if n%i==0:
            listaDiv.append(i)
    return listaDiv
