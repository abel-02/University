.data
	n1: .word 9
	n2: .word 6

.text
	mayorN1:
		.fnstart
			push {lr}
				MOV r0, r1
			pop {lr}
			bx lr
		.fnend

        mayorN2:
                .fnstart
                        push {lr}
                                MOV r0, r2
                        pop {lr}
               		bx lr
		 .fnend

.global main

main:

	ldr r1,=n1
	ldr r1,[r1]
	ldr r2,=n2
	ldr r2,[r2]

	cmp r1,r2
	bge N1
	bl mayorN2
	bl end

N1:
	bl mayorN1
	bl end

end:
	mov r7,#1
	swi 0
