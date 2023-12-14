/*********************************************************************************
*                                  TP Final - ARM
*                            Organización del Computador
*                               2do cuatrimestre 2023
*
* -------------------------------------------------------------------------------
*                           Ensamblador Y Compilador:
* Ensamblador (GNU Binutils for Raspbian) 2.31.1 de GNU
* © 2018 Free Software Foundation, Inc.
* Este programa es software libre; se puede redistribuir bajo los términos de
* la Licencia Pública General de GNU versión 3 o posterior.
* Este programa no tiene ninguna garantía.
* Este ensamblador se configuró para un objetivo «arm-linux-gnueabihf».
* gcc (Raspbian 8.3.0-6+rpi1) 8.3.0
* Copyright (C) 2018 Free Software Foundation, Inc.
* This is free software; see the source for copying conditions.  There is NO
* warranty; not even for MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
* ------------------------------------------------------------------------------
* Se pide realizar un programa que reciba como input un mensaje normal y
* que devuelva el mismo mensaje cifrado
*
* - Desplazamiento del alfabeto es circular. Si se llega al final del alfabeto,
*  se comienza desde el principio
* - El desplazamiento varia en base a un vector de al menos 5 posiciones
*
*********************************************************************************/

.data
msg_salida:	.ascii "Mensaje procesado:           \n"
conteo:  	.ascii "Caracteres procesados:       \n"
msg_error:	.ascii "Error en el bit de paridad, ingreso incorrecto \n"
msg_pal_clave:	.ascii "Palabra clave para decodificar: \n"
msg_vector:	.ascii "El vector de desplazamiento usado: \n"
cadena:      	.ascii "                                                           \n"
codificado:  	.ascii "                                                           \n"
cant_caracteres: .ascii "  \n"
desplazamiento: .byte 0, 0, 0, 0, 0
desp_interno:	.byte 3, 7, 4, 1, 8
cadena_des:	.ascii "     \n"
opcion: 	.ascii " "
opcion_deco:	.ascii " "
total_car:	.word 0
clave:		.ascii "     \n"
clave_des:	.ascii "     \n"
linea:		.ascii "------------------------------------------------------- \n"
inicio:		.ascii "#=========================================================#\n"
		.ascii "#                                                         #\n"
		.ascii "#           MÁQUINA DE CODIFICACIÓN DE MENSAJES           #\n"
		.ascii "#            Cifrado Cesar mejorado, método OC            #\n"
		.ascii "#                                                         #\n"
		.ascii "#=========================================================#\n"
		.ascii "        \n"
		.ascii "Ingrese el mensaje con alguno de los siguientes formatos de entrada:\n"
		.ascii "(mensaje a codificar o decodificar;[0-9];[0-9];[0-9];[0-9];[0-9];c/d)\n"
		.ascii "(mensaje a decodificar;clave;d)\n"
		.ascii "------------------------------------------------------- \n"

.text
/* funciones ---------------------- */
fn_print_linea:
	/* imprime una linea de separacion */
	.fnstart
		push {r7, lr}
		mov r7, #4
		ldr r1, =linea
		mov r0, #1
		mov r2, #57
		swi 0
		pop {r7, lr}
		bx lr
	.fnend

fn_print:
	/* Imprime por partalla
	 * Requiere dos parametro s:
	 * r1 -> puntero a la cadena
	 * r2 -> cantidad de caracteres a mostrar
	*/
	.fnstart
		push {r7, lr}
		mov r7, #4 // codigo de operacion - salida por pantalla
		mov r0, #1 // salida de cadena
		swi 0	   // llamada a la interrupcion - SO
		pop {r7, lr}
		bx lr
	.fnend

fn_input:
        /* Ingreso por teclado
         * Requiere dos parametros:
         * r1 -> puntero a la cadena donde se guarda
         * r2 -> cantidad de caracteres a ingresar
         */
	.fnstart
		push {r7, lr}
		mov r7, #3 // codigo de operacion - ingreso por teclado
	 	mov r0, #0 // ingreso de cadena
		swi 0      // llamada a la interrupcion - SO
		pop {r7, lr}
		bx lr
	.fnend

fn_codificar_letra:
	/* Codifica una letra (a-z) de acuerdo al desplazamiento dado
	 * Requiere dos parametros:
	 * r1 -> caracter a codificar
	 * r2 -> desplazamiento
	 * return:
	 * r0 -> el caracter codificado
	 */
	.fnstart
		push {r4, lr}
		mov r0, #0x0   // contendra el caracter codificado
		add r4, r1, r2 // suma del caracter mas el desplazamiento
		cmp r4, #0x7a  // compara el resultado con el 0x7a - ultima letra
		bgt rueda      // si es mayor salta a rueda
		mov r0, r4     // si es menor o igual guarda el caracter codificado en r4
		bal fnfin
	rueda:
		sub r1, r4, #0x7a // resta el valor hexa de la ultima letra y lo guarda en un auxiliar
		add r0, r1, #0x60 // suma el desplazamiento auxiliar al comienzo del abecedario
	fnfin:
		pop {r4, lr}
		bx lr
	.fnend

fn_decodificar_letra:
	/* Decodifica una letra (a-z) de acuerdo a un desplazamiento dado
	 * Requiere 2 parametros
	 * r1 -> caracter a decodificar
	 * r2 -> desplazamiento
	 * return:
	 * r0 -> caracter decodificado
	 */
	.fnstart
		push {r4, lr}
		mov r0, #0x0
		sub r4, r1, r2
		cmp r4, #0x61
		blt rueda_dec
		mov r0, r4
		bal fnfin_dec
	rueda_dec:
		add r1, r4, #0x7a
		sub r0, r1, #0x60
	fnfin_dec:
		pop {r4, lr}
		bx lr
	.fnend

fn_codificar_texto:
	/* codifica una cadena de texto
	 * pequiere dos parametros:
	 * r0 -> puntero a la cadena a codificar
	 * r1 -> puntero a la cadena donde guardar el texto codificado
	 * r2 -> desplazamiento
	 * r3 -> opcion ('c' -> codifica / 'd' -> decodifica)
	 */
	.fnstart
		push {r4, r5, r6, r7, r8, lr}
		mov r5, r0
		mov r6, #0 // contador del vector de desplazamientos
		mov r7, r2 // copia del puntero del vector
		mov r8, r2 // reinicio del puntero del vector
		mov r4, r1 // puntero a texto codificado
		ldrb r1, [r5], #1   // primer caracter
		//ldrb r2, [r7], #1   // primer desplazamiento
		while:
			cmp r1, #0x3b 	// compara con ';' caracter para dar fin a la cadena
			beq endwhile
			cmp r1, #0x20 	// compara con espacio, si es igual avanzo un carater y se ignora
			beq ignore
			cmp r1, #0x61 	// compara con cualquier caracter antes de la 'a' en la tabla ascii
			blt ignore
			cmp r1, #0x7a 	// compara con cualquier caracter despues de la 'z' en la tabla ascii
			bgt ignore
			/* si es un caracter valido llama a la funcion codificar o decodificar */
			cmp r6, #5
			beq reiniciar
			bal continue
			reiniciar:
				mov r6, #0
				mov r7, r8
				bal continue
			continue:
				add r6, #1
				ldrb r2, [r7], #1
				cmp r3, #0x63 //'c'
				beq codif
				cmp r3, #0x64 //'d'
				beq decod
			codif:
				bl fn_codificar_letra
				bal save_car
			decod:
				bl fn_decodificar_letra
			save_car:
				strb r0, [r4], #1
				bal next
			ignore:
				strb r1, [r4], #1 // agrego el caracter ignorado a la cadena
				bal next
			next:
				ldrb r1, [r5], #1
				bal while
		endwhile:
		cmp r3, #0x63
		bne borrar_bit
		bl fn_agregar_bit_paridad
		bal end_cod_texto

		borrar_bit:
		bl fn_borrar_bit_paridad

		end_cod_texto:
		pop {r4, r5, r6, r7, r8, lr}
		bx lr
	.fnend

fn_cantidad_caracteres:
	/* tranformo en cadena el numero de la cantidad de caracteres procesados
	 * Parametros:
	 * r0 -> numero con la cantidad de caracteres
	 * r1 -> puntero donde se va a guardad la cantidad de caracteres en string
	 */
	.fnstart
		push {r4, r5, r6, r7, r8, lr}
		mov r3, #10 // base
		mov r5, #0 // contador de digitos
		sdiv r6, r0, r3
		mas_digitos:
			cmp r0, #0
			beq end_digitos
			mul r4, r6, r3
			sub r7, r0, r4
			add r7, #0x30
			push {r7}
			mov r0, r6
			sdiv r6, r0, r3
			add r5, #1
			bal mas_digitos
		end_digitos:

		pasar_nro_a_caracter:
			cmp r5, #0
			beq end_pasar
			pop {r7}
			strb r7, [r1], #1
			sub r5, #1
			bal pasar_nro_a_caracter
		end_pasar:
		pop {r4, r5, r6, r7, r8, lr}
		bx lr
	.fnend

fn_procesar_cadena_ingresada:
	/* Procesa la cadena ingresada por teclado
	 * separando el mensaje a codificar
	 * el vector de desplazamientos
	 * y la opcion ('c' -> codificar / 'd' -> decodificar)
	 * requiere los parametros:
	 * r0 -> puntero a la cadena ingresada por teclado
	 * r1 -> puntero a total_car
	 * r2 -> puntero al vector de desplazamientos
	 * r3 -> puntero a cadena opcion
	 */
	.fnstart
		push {r4, r5, r6, r7, r8, r9, r10, lr}
		mov r7, r0 // copia del puntero a la cadena
		ldrb r5, [r7], #1 // caracter a procesar
		mov r6, #0 // contador de caracteres
		ldr r8, =opcion_deco // opcion decodificacion
		ldr r10, =clave // puntero a la clave
		recorre_mensaje:
			cmp r5, #0x3b
			beq end_recorre_mensaje
			add r6, #1
			ldrb r5, [r7], #1
			bal recorre_mensaje
		end_recorre_mensaje:
		str r6, [r1]
		ldrb r5, [r7], #1
		cmp r5, #0x61
		blt carga_vector

		carga_clave:
			cmp r5, #0x3b
			beq end_carga_clave
			strb r5, [r10], #1
			ldrb r5, [r7], #1
			bal carga_clave
		end_carga_clave:
		mov r9, #0x63 //'c' (clave)
		strb r9, [r8] // 'c' en opcion decodificar
		ldrb r5, [r7], #1
		bal save_opcion

		carga_vector:
			cmp r5, #0x3b
			beq ignore_ptocoma
			cmp r5, #0x63 //'c'
			beq save_opcion
			cmp r5, #0x64 //'d'
			beq save_opcion
			sub r5, #0x30
			strb r5, [r2], #1
			ldrb r5, [r7], #1
			bal carga_vector
		save_opcion:
			strb r5, [r3]
			bal end_carga_vector
		ignore_ptocoma:
			ldrb r5, [r7], #1
			bal carga_vector
		end_carga_vector:
		pop {r4, r5, r6, r7, r8, r9, r10, lr}
		bx lr
	.fnend

fn_generar_clave:
	/* Genera una clave alfebetica o palabra clave para poder
	 * decodificar un mensaje cifrado sin conocer el vector
 	 * de desplazamientos con los primeros 5 caracteres de
	 * la cadena ingresada. Utiliza un vector interno de desplazamientos
	 * Requiere parametros
	 * r0 -> puntero a la cadena ingresada
	 * r1 -> puntero a la cadena clave
	 * r2 -> puntero a la desplazamiento interno [3, 7, 4, 1, 8]
	 */
	.fnstart
		push {r4, r5, r6, lr}
		mov r4, r0
		mov r5, r1
		mov r3, r2 // copia dl puntero del desplazamiento interno
		mov r6, #0
		ldrb r1, [r4], #1
		ldrb r2, [r3], #1
		generar_clave:
			cmp r6, #5
			beq end_generar_clave
			cmp r1, #0x20 // si es un espacio lo ignora
			beq ignore_space
			bl fn_codificar_letra
			bal agrega_codigo
			ignore_space:
				ldrb r1, [r4], #1
				bal generar_clave
			agrega_codigo:
				strb r0, [r5], #1
				add r6, #1
				ldrb r1, [r4], #1
				ldrb r2, [r3], #1
				bal generar_clave
		end_generar_clave:
		pop {r4, r5, r6, lr}
		bx lr
	.fnend

fn_descifrar_clave:
	/* Descifra la clave alfabetica y lo guarda en clave decifrada
	 * Requiere parametros
	 * r0 -> puntero a la clave
	 * r1 -> puntero a la clave decifrada
	 * r2 -> puntero al desplazamiento interno
	 */
	.fnstart
		push {r4, r5, r6, lr}
		mov r5, #0 // contador
		mov r3, r0 // copia de puntero clave
		mov r4, r1 // copia de puntero clave descifrada
		mov r6, r2 // copia de puntero desplzamiento interno
		descifrar_clave:
			cmp r5, #5
			beq end_descifrar_clave
			ldrb r1, [r3], #1
			ldrb r2, [r6], #1
			bl fn_decodificar_letra
			strb r0, [r4], #1
			add r5, #1
			bal descifrar_clave
		end_descifrar_clave:
		pop {r4, r5, r6, lr}
		bx lr
	.fnend

fn_guardar_desplazamiento:
	/* Guarda el desplazamiento en una cadena para mostrar por pantalla
	 * Requiere parametros:
  	 * r0 -> puntero a cadena
	 * r1 -> puntero a clave_des
	 * r2 -> puntero a cadena_des
	 * r3 -> puntero al vector de desplazamiento
	 */
	.fnstart
		push {r4, r5, r6, r7, r8, lr}
		mov r6, #0
		mov r5, #0 // contador
		ldrb r7, [r0], #1
		ldrb r4, [r1], #1
		recorre_clave_des:
			cmp r5, #5
			beq end_recorre_clave_des
			cmp r7, #0x20 // espacio
			beq ignora_espacio
			cmp r7, r4
			bge guarda_nro
			add r7, #26
		guarda_nro:
			sub r6, r7, r4
			strb r6, [r3], #1 // guardo el nro en el vector de desplazamientos
			add r6, r6, #0x30
			strb r6, [r2], #1 // guardo en cadena_des
			add r5, #1
			ldrb r7, [r0], #1
			ldrb r4, [r1], #1
			bal recorre_clave_des
		ignora_espacio:
			ldrb r7, [r0], #1
			bal recorre_clave_des
		end_recorre_clave_des:
		pop {r4, r5, r6, r7, r8, lr}
		bx lr
	.fnend

fn_guardar_des_en_cadena:
	/* Guarda el vector de desplazamiento en una cadena
	 * para poder mostrarlo por pantalla
	 * Requiere dos parametros
	 * r0 -> puntero al vector de desplazamiento
 	 * r1 -> puntero a la cadena_des
	 */
	.fnstart
		push {r4, lr}
		mov r3, #0
		ldrb r2, [r0], #1
		leer_vec:
			cmp r3, #5
			beq end_leer_vec
			add r2, #0x30
			strb r2, [r1], #1
			add r3, #1
			ldrb r2, [r0], #1
			bal leer_vec
		end_leer_vec:
		pop {r4, lr}
	.fnend

fn_agregar_bit_paridad:
	/* Agrega bit de paridad / paridad par
	 * 0 -> si la cadena tiene cantidad par de caracteres
	 * 1 -> si la cadena tiene cantidad impar de caracteres
	 */
	.fnstart
		push {r4, lr}
		ldr r1, =total_car
		ldr r1, [r1]
		mov r2, r1
		ldr r0, =codificado
		add r0, r1
		ver_paridad:
			cmp r2, #0
			beq es_par
			cmp r2, #1
			beq es_impar
			sub r2, #2
			bal ver_paridad
		es_par:
			mov r3, #0x30
			strb r3, [r0]
			bal end_bit_paridad
		es_impar:
			mov r3, #0x31
			strb r3, [r0]
			bal end_bit_paridad
		end_bit_paridad:
		pop {r4, lr}
		bx lr
	.fnend

fn_verificar_bit_paridad:
	/* Verifica que el bit de paridad del mensaje a decodificar sea correcto
	 * Recibe 2 parametros
	 * r0 -> puntero a la cadena para decodificar
	 * r1 -> total de caracteres de la cadena
	 * Verifica el bit de paridad con el siguiente criterio
	 * 0 -> el mensaje debe tener cantidad par de caracteres
	 * 1 -> el mensaje debe tener cantidad impar de caracteres
	 * return en r0:
	 * 0 -> si es correcto
	 * 1 -> si es incorrecto
	 */
	.fnstart
		push {r4, lr}
		ldr r1, [r1]
		sub r1, #1
		add r0, r1
		ldrb r0, [r0] // guarda el bit de paridad en r0
		sub r0, #0x30
		ver_par:
			cmp r1, #0
			beq par
			cmp r1, #1
			beq impar
			sub r1, #2
			bal ver_par
		par:
			cmp r0, #0
			beq correcto
			bal incorrecto
		impar:
			cmp r0, #1
			beq correcto
			bal incorrecto
		correcto:
			mov r0, #0
			bal end_ver_par
		incorrecto:
			mov r0, #1
			bal end_ver_par
		end_ver_par:
		pop {r4, lr}
		bx lr
	.fnend

fn_borrar_bit_paridad:
	/* Borra el ultimo caracter de la cadena procesada si esta dodificando
	 * para que no muestre el bit de paridad en la salida por pantalla
	 */
	.fnstart
		push {r4, lr}
		mov r0, #0x20 // espacio
		ldr r1, =total_car
		ldr r1, [r1]
		ldr r2, =codificado
		add r2, r1
		strb r0, [r2]
		pop {r4, lr}
		bx lr
	.fnend

/* fin de las funciones --------------- */

.global main

main:
	.fnstart
		push {lr}
		/* Salida por pantalla de la presentacion */
		mov r2, #600
		ldr r1, =inicio
		bl fn_print

		/* Lectura por teclado de la cadena */
		mov r2, #60 // cantidad de caracteres
		ldr r1, =cadena // donde se guarda
		bl fn_input

		/* procesa la cadena ingresada por teclado */
		ldr r0, =cadena
		ldr r1, =total_car
		ldr r2, =desplazamiento
		ldr r3, =opcion
		bl fn_procesar_cadena_ingresada

		/* Genera la palabra clave si la opcion es codificar */
		ldr r3, =opcion
		ldrb r3, [r3]
		cmp r3, #0x63
		beq generando_clave
		bal verificando_paridad
	generando_clave:
		ldr r0, =cadena
		ldr r1, =clave
		ldr r2, =desp_interno
		bl fn_generar_clave
		bal continue_program

	verificando_paridad:
		/* verifica el bit de paridad si el mensaje es para decodificar */
		ldr r0, =cadena
		ldr r1, =total_car
		bl fn_verificar_bit_paridad
		cmp r0, #1
		beq print_msg_error
		ldr r1, =total_car // resto el bit de paridad en el conteo
		ldr r2, [r1]
		sub r2, #1
		str r2, [r1]

		/* Si opcion decodificar es 'c'(clave) descifro la clave
			sino continua el programa */
		/* Descifro la clave */
		ldr r0, =opcion_deco
		ldrb r0, [r0]
		cmp r0, #0x63
		bne continue_program

		ldr r0, =clave
		ldr r1, =clave_des
		ldr r2, =desp_interno
		bl fn_descifrar_clave

		/* Guarda la clave descifrada en el vector de desplazamientos */
		ldr r0, =cadena
		ldr r1, =clave_des
		ldr r2, =cadena_des
		ldr r3, =desplazamiento
		bl fn_guardar_desplazamiento



	continue_program:

		/* tranforma el nro de total de caracteres en una cadena para imprimir */
		ldr r1, =cant_caracteres
		ldr r0, =total_car
		ldr r0, [r0]
		bl fn_cantidad_caracteres

		/* Proocesa el mensaje a codificar / decodificar *****/
		ldr r2, =desplazamiento
		ldr r0, =cadena
		ldr r1, =codificado // puntero a la cadena codificada
		ldr r3, =opcion
		ldrb r3, [r3]
		bl fn_codificar_texto

		/* Salida por patanlla del mensaje
			Cadena procesada: */
		bl fn_print_linea
		mov r2, #30
		ldr r1, =msg_salida
		bl fn_print

		/* muestro la palabra codificada */
		mov r2, #60 // agrego un caracter mas para el salto de linea
 		ldr r1, =codificado
		bl fn_print

		/* Muestra los mensajes de salida de acuerdo a codificar /decodificar */
		ldr r0, =opcion
		ldrb r0, [r0]
		cmp r0, #0x64
		beq mensajes_decodificar

		/* Salida por pantalla del mensaje palabra clave */
		bl fn_print_linea
		mov r2, #33
		ldr r1, =msg_pal_clave
		bl fn_print

		/* Salida por pantalla de la palabra clave */
		bl fn_print_linea
		mov r2, #6
		ldr r1, =clave
		bl fn_print

		bal mensaje_caracteres

	mensajes_decodificar:
		/* Copia el vector de desplazamiento en la cadena_des */
		ldr r0, =desplazamiento
		ldr r1, =cadena_des
		bl fn_guardar_des_en_cadena

		/* salida por pantalla mensaje vector */
		bl fn_print_linea
		mov r2, #36
		ldr r1, =msg_vector
		bl fn_print

		/* Muestra el vector de desplazamientos */
		bl fn_print_linea
		mov r2, #6
		ldr r1, =cadena_des
		bl fn_print

	mensaje_caracteres:
		/* Salida por pantalla del mensaje
			Cantidad de caracteres: */
		bl fn_print_linea
		mov r2, #30
		ldr r1, =conteo
		bl fn_print

		/* Salida por pantalla la cadena con
			el nurmero de cantidad de caracteres */
		mov r2, #3
		ldr r1, =cant_caracteres
		bl fn_print
		bal fin

	print_msg_error:
		/* muestra por pantalla el mensaje de error de bit de paridad */
		bl fn_print_linea
		mov r2, #48
		ldr r1, =msg_error
		bl fn_print
		bl fn_print_linea

	fin:
		mov r7, #1
		swi 0
	

		
