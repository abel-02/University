dinero=int(input("Ingrese la cantidad de dinero a extraer"))
ext1000=dinero%1000
billet1000=dinero//1000

ext500=ext1000%500
billet500=ext1000//500

ext200=ext500%200
billet200=ext500//200

ext100=ext200%100
billet100=ext200//100

ext50=ext100%50
billet50=ext100//50

ext20=ext50%20
billet20=ext50//20

ext10=ext20%10
billet10=ext20//10

print("Para retirar", dinero, "se le entregará: ", billet1000,"billetes de 1000     ",billet500,
"billetes de 500     ", billet200, "billetes de 200     ", billet100,"billetes de 100     ", billet50,
"billetes de 50     " ,billet20,"billetes de 20        ", billet10,"billetes de 10")