package practica6;

import practica6.ABInt2.NodoInt;

public class ABInt3 {
	private class NodoInt {
		int elem;
		NodoInt der,izq;
		NodoInt(int valor) {
			elem=valor;
		}
	}
	
	private NodoInt raiz;
	
	public void agregar(int valor) {
		this.raiz = agregar(this.raiz,valor);
	}

	private NodoInt agregar(NodoInt nodo, int valor) {
		if(nodo == null)
			return new NodoInt(valor);
		if(valor > nodo.elem)
			nodo.der = agregar(nodo.der, valor);
		else
			nodo.izq = agregar(nodo.izq, valor);
		return nodo;
	}
	
//	public void imprimir() {
//		this.raiz = imprimir(this.raiz);
//	}
	
//	private NodoInt imprimir(NodoInt nodo) {
//		if(nodo == null)
//			return null;
//		
//		
//	}
	
//	public boolean pertenece(int elem) {
//		return pertenece(raiz, elem);
//	}
//	
//	private boolean pertenece(NodoInt nodo, int elem) {
//		if (nodo==null)
//			return false;
//			
//		if (nodo.elem==elem)
//			return true;
//		
//		return pertenece(nodo.izq,elem)
//				|| pertenece(nodo.der,elem);
//	}
	
	
	
	public boolean pertenece(int n) {
		return pertenece(this.raiz, n);
	}
	

	private boolean pertenece(NodoInt nodo, int n) {
		if(nodo == null)
			return false;
		if(nodo.elem == n)
			return true;
		if(n < nodo.elem)
			return pertenece(nodo.izq, n);
		return pertenece(nodo.der, n);
	}
	
	
//	public void eliminar(int n) {
//		this.raiz = eliminar(this.raiz, n);
//	}
//	
//
//	private NodoInt eliminar(NodoInt nodo, int n) {
//		if(nodo == null)
//			return null;
//		if(n == nodo.elem)
//			
//		return null;
//	}
//	
	
	
	
	
	public void quitar(int elem) {
		raiz = quitar(raiz, elem);
	}

	private NodoInt quitar(NodoInt nodo, int elem) {
	    if (nodo == null)
	        return null;

	    if (elem < nodo.elem)
	        nodo.izq = quitar(nodo.izq, elem);
	    else if (elem > nodo.elem)
	        nodo.der = quitar(nodo.der, elem);
	    else { // Encontré el elemento a quitar
	        if (esHoja(nodo))
	            return null;
	        if (nodo.izq == null)
	            return nodo.der;
	        if (nodo.der == null)
	            return nodo.izq;

	        nodo.elem = encontrarMinimo(nodo.der);
	        nodo.der = quitar(nodo.der, nodo.elem);
	    }
	    return nodo;
	}
	
	private boolean esHoja(NodoInt nodo) {
		return nodo.izq == null && nodo.der == null;
	}

    private int encontrarMinimo(NodoInt nodo) {
        int minimo = nodo.elem;
        while (nodo.izq != null) {
            minimo = nodo.izq.elem;
            nodo = nodo.izq;
        }
        return minimo;
    }
	
	public boolean cumpleIrep() {
		return cumpleIrep(this.raiz);
	}

	private boolean cumpleIrep(NodoInt nodo) {
		if(nodo == null)
			return true;
		if(nodo.elem < nodo.izq.elem)
			return false;
		if(nodo.elem > nodo.der.elem)
			return false;
		return cumpleIrep(nodo.izq) && cumpleIrep(nodo.der);
	}

	public static void main(String[] args) {
		ABInt3 arbol1 = new ABInt3();
		arbol1.agregar(50);
		arbol1.agregar(30);
		arbol1.agregar(70);
		arbol1.agregar(20);
		arbol1.agregar(40);
		arbol1.agregar(60);
		arbol1.agregar(80);
		arbol1.agregar(10);
		arbol1.agregar(25);
		arbol1.agregar(35);
		arbol1.agregar(45);
		arbol1.agregar(55);
		arbol1.agregar(65);
		arbol1.agregar(75);
		arbol1.agregar(85);
	System.out.println(arbol1.pertenece(85));
	arbol1.quitar(85);
	System.out.println(arbol1.pertenece(85));
	System.out.println(arbol1.pertenece(75));
	arbol1.quitar(75);
	System.out.println(arbol1.pertenece(75));
	}
	
	
}
