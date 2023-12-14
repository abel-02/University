.data
	input: .byte 0x7d
	print: .byte

.text
	leerPorConsola:
		.fnstart
          		mov r7,#3
                        mov r0,#0
                        mov r2,#5
                        ldr r1,=input
                        swi 0
                        bx lr
		.fnend

	imprimirPorPantalla:
		.fnstart
			mov r7,#4
			mov r0,#1
			mov r2,#4
			ldr r1,=print
			swi 0
			bx lr
		.fnend

.global main

main:
//	bl leerPorConsola
	ldr r0,=input	//Numero
	ldrb r0,[r0]
	mov r1,#0	//Centena
	mov r2,#0	//Decena
	mov r3,#0	//Unidad


calcularCentena:
	cmp r0,#100
	blt calcularDecena
	sub r0,#100
	add r1,#1
	bl calcularCentena

calcularDecena:
	cmp r0,#10
	blt calcularUnidad
	sub r0,#10
	add r2,#1
	bl calcularDecena

calcularUnidad:
	cmp r0,#0
	ble guardarDatos
	sub r0,#1
	add r3,#1
	bl calcularUnidad

guardarDatos:
	ldr r4,=print
	add r1,#0x30
	add r2,#0x30
	add r3,#0x30
//	ldr r6,[r4]
	strb r1,[r4]
//	ldr r6,[r4]
	strb r2,[r4, #1]
//	ldr r6,[r4]
	strb r3,[r4, #2]
	ldr r6,[r4]
	bl imprimirPorPantalla
	bl fin

fin:
	mov r7,#1
	swi 0

