.data
	texto: .word 0
	num: .word 0

.text
	inputPorTeclado:
		.fnstart
			push {lr}
				mov r7,#3
				mov r0,#0
				mov r2,#4
				ldr r1,=texto
				swi 0
			pop {lr}
			bx lr
		.fnend

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
.global main

main:
	bl inputPorTeclado

	mov r6,#0
	ldr r0,=texto
	ldr r3,=num
	mov r1,#0
	ldr r6,[r0]
ciclo:
	cmp r1, #3
	beq fin
	bl convertirNum
	add r1,#1
	bl ciclo

fin:
	ldr r5,=num
	ldr r5,[r5]
	mov r7,#1
	swi 0
