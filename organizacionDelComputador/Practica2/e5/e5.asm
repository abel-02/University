.data
	texto: .asciz "Hola que tal"

.text
	longitud:
		.fnstart
			push{lr}
				ldr r3,[r1,r0]
				cmp r3,#0
				beq bx lr
				add r0,#1
				bl longitud
			pop{lr}
			bx lr
		.fnend

.global main

main:

	ldr r1,=texto   // puntero
	mov r0,#0	// i
	mov r3,#0	// dato

	bl longitud

end:
	mov r7#1
	swi 0
