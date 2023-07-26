package practica6;

public class ABInt2 {
	protected class NodoInt {
		int elem;
		NodoInt der, izq;

		NodoInt(int valor) {
			elem = valor;
		}
	}

	private NodoInt raiz;

	public void agregar(int elem) {
		raiz = agregar(raiz, elem);
	}

	private NodoInt agregar(NodoInt nodo, int elem) {
		if (nodo == null)
			return new NodoInt(elem);

		if (nodo.der != null)
			nodo.izq = agregar(nodo.izq, elem);
		else
			nodo.der = agregar(nodo.der, elem);

		return nodo;
	}

	public boolean pertenece(int elem) {
		return pertenece(raiz, elem);
	}

	private boolean pertenece(NodoInt nodo, int elem) {
		if (nodo == null)
			return false;

		if (nodo.elem == elem)
			return true;

		return pertenece(nodo.izq, elem) || pertenece(nodo.der, elem);
	}

	public void quitar(int elem) {
		raiz = quitar(raiz, elem);
	}

	private NodoInt quitar(NodoInt nodo, int elem) {
		if (nodo == null)
			return null;
		if (nodo.elem == elem) { // encontre el elemento a quitar
			// TODO refactorizar esto para que quede mas corto
			if (esHoja(nodo))
				return null;
			if (nodo.izq == null)
				return nodo.der;
			if (nodo.der == null)
				return nodo.izq;

			int reemplazo = buscarReemplazoPorIzquierda(nodo.izq);
			nodo.elem = reemplazo;
			nodo.izq = quitar(nodo.izq, reemplazo);

		} else {
			nodo.izq = quitar(nodo.izq, elem);
			nodo.der = quitar(nodo.der, elem);
		}
		return nodo;
	}

	private int buscarReemplazoPorIzquierda(NodoInt nodo) {
		if (nodo.izq == null)
			return nodo.elem;
		return buscarReemplazoPorIzquierda(nodo.izq);
	}

	private boolean esHoja(NodoInt nodo) {

		return nodo.izq == null && nodo.der == null;
	}

	public int cantNodos() {
		return cantNodos(raiz);
	}

	private int cantNodos(NodoInt nodo) {
		if (nodo == null)
			return 0;
		return 1 + cantNodos(nodo.izq) + cantNodos(nodo.der);
	}

	public int altura() {
		return altura(raiz);
	}

	private int altura(NodoInt nodo) {
		if (nodo == null)
			return 0;
		return 1 + max(altura(nodo.izq), altura(nodo.der));
	}

	public boolean estaBalanceado() {
		throw new RuntimeException("Implementar de TAREA");
	}

	public int minimo() {
		throw new RuntimeException("Implementar de TAREA");
	}

	public int maximo() {
		throw new RuntimeException("Implementar de TAREA");
	}

	public boolean esABB() {
		throw new RuntimeException("Implementar de TAREA");
	}

	// --------------------------------------------------
	// ------------------- AUXILIARES -------------------
	// --------------------------------------------------

	private int max(int a, int b) {
		if (a > b)
			return a;
		return b;
	}

	private int min(int a, int b) {
		if (a < b)
			return a;
		return b;
	}

}
