letra=input("ingrese letra")
cadena=input("Ingrse cadena")
ncadena=""
for char in cadena:
    if char==letra:
        char="*"
    ncadena=ncadena+char
print(ncadena)