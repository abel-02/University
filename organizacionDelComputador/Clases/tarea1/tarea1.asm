.data
	texto: .word 0
	num: .word 0

.text
	// convertirNum(r0 array, r1 posicion, r3 guardar)
	convertirNum:
		.fnstart
			push {lr}
				mov r2,#0
				ldrb r2,[r0,r1]
				add r2, r2, #0x-30
				strb r2,[r3,r1]
			pop {lr}
			bx lr
		.fnend
	
	convertirATexto:
		.fnstart
			push{lr}
				mov r2,#0
				ldrb r2,[r0,r1]
				add r2, r2, #0x30
				strb r2,[r3,r1]
			pop{lr}
			bx lr
		.fnend

.global main

main:
	ldr r0,=texto
	ldr r3,=num
	mov r1,#0

ciclo:
	cmp r1, #3
	beq fin
	bl convertirNum
	add r1,#1
	bl ciclo

fin:
	ldr r5,=num
	ldr r5,[r5]	@Prueba para el debug
	mov r7,#1
	swi 0
