.data
.text
.global main

main:
	mov r0,#1
	mov r1,#0
while:
	cmp r0,#100
	beq end
	add r1,r0
	add r0,#1
	bl while

end:
	mov r7,#1
	swi 0
