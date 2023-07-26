#Trabajo en una empresa y necesito dos personas para que me ayuden a hacer mi programa, uno hara una funcion que sume teniendo dos parametros y otro que hace una funcion que reste con dos parametros, no me importa como lo hicieron, porque
# son gente que estudio y solo me importa que haga eso.

#funcion hecha por lauty
def suma(n1,n2):
    s=n1+n2
    return s

#funcion hecha por kevin
def resta(r1,r2):
    return r1-r2

#programa principal que estoy desarrollando
a=int(input(""))
b=int(input(""))
su=suma(a,b)
print(su)



#print(su(resta(a,b))





def invertirCadena(ca):
    cadena=input("ingresar cadena")
    cadenanew=""
    for char in cadena:
       #quita vocales, aplica un filtro
       if char!="a" and char!="e" and char!="i" and char!="o" and char!="u" and char!="A" and char!="E" and char!="I" and char!="O" and char!="U":
        cadenanew=char+cadenanew


    return cadenanew

