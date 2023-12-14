.data
        vector: .byte 1,2,3,4
.text

.global main


main:
        mov r0,#0        // i
        mov r2,#0        // acum
        ldr r1,=vector   // puntero
        mov r3,#0       // guardo dato
ciclo:
        cmp r0,#4      //largo del vector  4 datos (4 x4) ya que es un .word
        beq end
        ldrb r3,[r1,r0]  //Levanto dato
        add r2,r3       // Sumo el dato
        add r0,#1       // i++
        bl ciclo
end:
        mov r7,#1
        swi 0




