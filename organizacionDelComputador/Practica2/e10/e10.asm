.data
	texto: .asciz "HOLA QUE TAL"

.text


.global main


main:
	ldr r0,=texto	//puntero
	mov r1,#0	//dato
	mov r2,#0	// i

cicloTexto:
	ldrb r1,[r0,r2]
	cmp r1,#0
	beq fin
	cmp r1,#0x20
	beq caracterEspacio
	add r1,#0x20
	strb r1,[r0,r2]
	add r2,#1
	bl cicloTexto

caracterEspacio:
	add r2,#1
	bl cicloTexto


fin:
	mov r7,#1
	swi 0
