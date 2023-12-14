.data
	var1: .word 10
	var2: .word 20
	resul: .word 0
.text

.global main

main:
	mov r1,#3
	mov r2,#5
	mov r0,#0

	cmp r1,r2
	bgt suma

	com r1,r2
	beq esIgual

suma:
	add r0, r1,r2
	bal fin

esIguaL:
	mov r0,r2
	bal fin

fin:
	mov r7,#1
	swi 0
