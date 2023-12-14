.data
	n1: .byte 8
	n2: .byte 2
	resultado: .byte 0
.text
	dividirN1porN2:
		.fnstart
			cmp r1,r2
			ble finDivision
			sub r1,r2
			add r3,#1
			bl dividirN1porN2
		.fnend

	finDivision:
		.fnstart
			strb r3,[r0]
			bl end
		.fnend
.global main


main:
	ldr r1,=n1
	ldr r2,=n2
	ldr r0,=resultado

	ldrb r1,[r1]
	ldrb r2,[r2]
	mov r3,#0

	cmp r1,r2
	bge N1Mayor
	bl incumpleCondiciones

N1Mayor:
	cmp r2,#0
	beq incumpleCondiciones
	push {lr}
	bl dividirN1porN2

incumpleCondiciones:
	strb r3,[r0]
	bl end

end:
	mov r7,#1
	swi 0
