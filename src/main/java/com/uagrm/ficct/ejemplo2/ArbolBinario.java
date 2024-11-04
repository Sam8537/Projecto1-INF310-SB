package com.uagrm.ficct.ejemplo2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
/*
import principal.controlador.K;
import principal.controlador.Nodo;
import principal.controlador.V;*/

import testArbol4.iArbol;



public class ArbolBinario<K extends Comparable<K>, V> implements iArbol<K, V>

{// K = clave es el valor que vamos a comparar para crear el arbol
//y valor puede ser cualquier nombre

	protected Nodo<K, V> raiz;




public ArbolBinario() {   //constructor sin parametro
raiz = null;

}

//reconstruccion con 4 listas CON INORDEN Y PREORDEN

	private Nodo<K, V> reconstruirConPreOrden(List<K> clavesInOrden, List<V> valoresInOrden,
			List<K> clavesPreOrden, List<V> valoresPreOrden) {

		if (!clavesInOrden.isEmpty()) {

			K supuestaRaizK = clavesPreOrden.get(0);
			V supuestaRaizV = valoresPreOrden.get(0);

			Nodo<K, V> nodo = new Nodo<>(supuestaRaizK, supuestaRaizV);

			int posicionElementoIO = clavesInOrden.indexOf(supuestaRaizK);

//Hijo izquierdo de la raiz
			List<K> IOHIC = clavesInOrden.subList(0, posicionElementoIO);
			List<V> IOHIV = valoresInOrden.subList(0, posicionElementoIO);
			List<K> POHIC = clavesPreOrden.subList(1, IOHIC.size() + 1);
			List<V> POHIV = valoresPreOrden.subList(1, IOHIC.size() + 1);

//hijo derecho de la raiz
			List<K> IOHDC = clavesInOrden.subList(posicionElementoIO + 1, clavesInOrden.size());
			List<V> IOHDV = valoresInOrden.subList(posicionElementoIO + 1, clavesInOrden.size());
			List<K> POHDC = clavesPreOrden.subList(posicionElementoIO + 1, clavesInOrden.size());
			List<V> POHDV = valoresPreOrden.subList(posicionElementoIO + 1, clavesInOrden.size());

//INSERTA POR IZQUIERDA

			nodo.setHijoIzquierdo(this.reconstruirConPreOrden(IOHIC, IOHIV, POHIC, POHIV));

//INSERTA POR DERECHA

//this.reconstruirConPreOrden( IOHDC, IOHDV, POHDC, POHDV);
			nodo.setHijoDerecho(this.reconstruirConPreOrden(IOHDC, IOHDV, POHDC, POHDV));

			return nodo;

		} else {
			return Nodo.nodoVacio();
		}

	}

//-------------------------------------------------------------

//reconstruccion con 4 listas CON INORDEN Y POSTORDEN
	private Nodo<K, V> reconstruirConPostOrden(List<K> clavesInOrden, List<V> valoresInOrden,
			List<K> clavesPostOrden, List<V> valoresPostOrden) {

		if (!clavesInOrden.isEmpty()) {

			K supuestaRaizK = clavesPostOrden.get(clavesPostOrden.size() - 1);
			V supuestaRaizV = valoresPostOrden.get(clavesPostOrden.size() - 1);

			Nodo<K, V> nodo = new Nodo<>(supuestaRaizK, supuestaRaizV);

			int posicionElementoIO = clavesInOrden.indexOf(supuestaRaizK);

//Hijo izquierdo de la raiz
			List<K> IOHIC = clavesInOrden.subList(0, posicionElementoIO);
			List<V> IOHIV = valoresInOrden.subList(0, posicionElementoIO);
			List<K> POHIC = clavesPostOrden.subList(0, IOHIC.size());
			List<V> POHIV = valoresPostOrden.subList(0, IOHIC.size());

//hijo derecho de la raiz
			List<K> IOHDC = clavesInOrden.subList(posicionElementoIO + 1, clavesInOrden.size());
			List<V> IOHDV = valoresInOrden.subList(posicionElementoIO + 1, clavesInOrden.size());
			List<K> POHDC = clavesPostOrden.subList(IOHIC.size(), clavesInOrden.size() - 1);
			List<V> POHDV = valoresPostOrden.subList(IOHIC.size(), clavesInOrden.size() - 1);

//INSERTA POR IZQUIERDA

			nodo.setHijoIzquierdo(this.reconstruirConPostOrden(IOHIC, IOHIV, POHIC, POHIV));

//INSERTA POR DERECHA

			nodo.setHijoDerecho(this.reconstruirConPostOrden(IOHDC, IOHDV, POHDC, POHDV));

			return nodo;

		} else {
			return Nodo.nodoVacio();
		}

	}

public ArbolBinario(List<K> clavesInOrden,
List<V> valoresInOrden,
List<K> clavesNoInOrden,
List<V> valoresNoInOrden,
boolean esConPostOrden) {

/*si es falso es con preOrden
* si es verdad es con postOrden*/
if(esConPostOrden) {
this.raiz = this.reconstruirConPostOrden(clavesInOrden, valoresInOrden,
				 clavesNoInOrden, valoresNoInOrden);

}else {
this.raiz = this.reconstruirConPreOrden(clavesInOrden, valoresInOrden,
				 clavesNoInOrden, valoresNoInOrden);
}


}

	@Override
	public void insertar(K claveAInsertar, V valorAsociado) { // Insertar Revisado

		if (claveAInsertar == null) {
			throw new IllegalArgumentException("Clave a insertar no puede ser vacia");
//throw new IllegalArgumentException("hola mundo");   /*nota: solo se puede mostrar una sola exception*/
		}
		if (valorAsociado == null) {
			throw new IllegalArgumentException("Valor a insertar no puede ser vacia");
		}

		if (this.esArbolVacio()) {
			this.raiz = new Nodo<>(claveAInsertar, valorAsociado);
			return;
		}

// hasta aqui todo debe estar bien porque el arbol es no vacio y la clave es no
// nulo tambien el valor asociado es no nulo

		Nodo<K, V> nodoAnterior = Nodo.nodoVacio(); // nodoVacio es como colocar NULL
		Nodo<K, V> nodoActual = this.raiz;

		do {
			K claveDeNodoActual = nodoActual.getClave();
			int comparacion = claveAInsertar.compareTo(claveDeNodoActual); // aCompareTo(b) == a<b
//x.compareTo(y)  igual que x<y
//primero pregunta si es menor, despues pregunta si es igual, si es igual da 0 por lo contrario da 1
//si es menor devuleve -1 si es igual devuelve 0 si es mayor devuelve 1
			nodoAnterior = nodoActual;
			if (comparacion < 0) {
				nodoActual = nodoActual.getHijoIzquierdo();
			} else {
				if (comparacion > 0) {
					nodoActual = nodoActual.getHijoDerecho();
				} else {
					nodoActual.setValor(valorAsociado);
					return;
				}
			}
		} while (!Nodo.esNodoVacio(nodoActual));

		Nodo<K, V> nodoNuevo = new Nodo<>(claveAInsertar, valorAsociado);
		if (claveAInsertar.compareTo(nodoAnterior.getClave()) < 0) {
			nodoAnterior.setHijoIzquierdo(nodoNuevo);
		} else {
			nodoAnterior.setHijoDerecho(nodoNuevo);
		}

	}

	private Nodo<K, V> eliminar(Nodo<K, V> nodoActual, K claveAEliminar) {// revisado

		K claveDelNodoActual = nodoActual.getClave(); // REVISAR COMO FUNCIONA EL ELIMINAR RECURSIVO
		if (claveAEliminar.compareTo(claveDelNodoActual) < 0) {
			Nodo<K, V> supuestoNuevoHijoIzquierdo = eliminar(nodoActual.getHijoIzquierdo(), claveAEliminar);
			nodoActual.setHijoIzquierdo(supuestoNuevoHijoIzquierdo);
			return nodoActual;
		}

		if (claveAEliminar.compareTo(claveDelNodoActual) > 0) {
			Nodo<K, V> supuestoNuevoHijoDerecho = eliminar(nodoActual.getHijoDerecho(), claveAEliminar);
			nodoActual.setHijoDerecho(supuestoNuevoHijoDerecho);
			return nodoActual;
		}

//caso 1: clave a eliminar es un nodo hoja
		if (nodoActual.esHoja()) {
			return Nodo.nodoVacio();
		}

//caso2A: clave a eliminar solo tiene hijo izquierdo
		if (!nodoActual.esVacioHijoIzquierdo() && nodoActual.esVacioHijoDerecho()) {
			return nodoActual.getHijoIzquierdo();
		}

//caso2B: clave a eliminar solo tiene hijo derecho
		if (!nodoActual.esVacioHijoDerecho() && nodoActual.esVacioHijoIzquierdo()) {
			return nodoActual.getHijoDerecho();
		}

//caso3: clave a eliminar tiene ambos hijos
		Nodo<K, V> nodoDelSucesor = this.getSucesor(nodoActual.getHijoDerecho());
		Nodo<K, V> supuestoNuevoHijoDerecho = eliminar(nodoActual.getHijoDerecho(), nodoDelSucesor.getClave());

		nodoActual.setHijoDerecho(supuestoNuevoHijoDerecho);
		nodoActual.setClave(nodoDelSucesor.getClave());
		nodoActual.setValor(nodoDelSucesor.getValor());
		return nodoActual;
//Corregir esta parte del codigo, debemos ver que devuelve el return
	}

	protected Nodo<K, V> getSucesor(Nodo<K, V> nodoAux) {// revisado
		while (!nodoAux.esVacioHijoIzquierdo()) {
			nodoAux = nodoAux.getHijoIzquierdo();
		}

		return nodoAux;
	}

	@Override
	public V eliminar(K claveAEliminar) {// ver el codigo de eliminar es de manera recursiva //revisado

//revisar porque no elimina
		V valorARetornar = this.buscar(claveAEliminar);

		if (valorARetornar == null) {
			return null;
		}

		this.raiz = eliminar(this.raiz, claveAEliminar);

		return valorARetornar;
	}

	@Override
	public V buscar(K claveABuscar) {// revisado el buscar me devuelve el valor

		if (claveABuscar == null) {
			throw new IllegalArgumentException("No se puede buscar clave vacia o nula");
		}
		if (!esArbolVacio()) {

			Nodo<K, V> nodoActual = this.raiz;

			do {
				if (claveABuscar.compareTo(nodoActual.getClave()) == 0) {
					return nodoActual.getValor();
				}
				if (claveABuscar.compareTo(nodoActual.getClave()) < 0) {
					nodoActual = nodoActual.getHijoIzquierdo();
				} else {
					nodoActual = nodoActual.getHijoDerecho();
				}
			} while (!Nodo.esNodoVacio(nodoActual));
		}

		return null;

	}



	@Override
	public String toString() {

		return "ArbolBinario crear arbol [raiz= " + this.raiz.getClave() + "]";

//imprimir el arbol
	}

	public void printArbol(Nodo<K, V> nodo, String prefijo, int esUltimo) {

		System.out.println(prefijo + (esUltimo == 1 ? "└──(D) " : "├──(I) ") + nodo.getClave());

//Esto solo lo dibuja sin colocar ningun dato
		if (nodo.esVacioHijoIzquierdo() && nodo.esVacioHijoDerecho() && esUltimo == 2) {
			System.out.println(prefijo + "│   " + "├── (I) " + '\u2563');
			System.out.println(prefijo + "│   " + "└── (D) " + '\u2563');

		} else {
			if (nodo.esVacioHijoIzquierdo() && nodo.esVacioHijoDerecho() && esUltimo == 1) {
				System.out.println(prefijo + "    " + "├── (I) " + '\u2563');
				System.out.println(prefijo + "    " + "└── (D) " + '\u2563');

			}
		}

		if (!nodo.esVacioHijoIzquierdo() && !nodo.esVacioHijoDerecho()) {
			printArbol(nodo.getHijoIzquierdo(), prefijo + (esUltimo == 1 ? "    " : "│   "), 2);
			printArbol(nodo.getHijoDerecho(), prefijo + (esUltimo == 1 ? "    " : "│   "), 1);

		} else {
			if (!nodo.esVacioHijoIzquierdo() && nodo.esVacioHijoDerecho() && esUltimo == 2) {
				printArbol(nodo.getHijoIzquierdo(), prefijo + (esUltimo == 1 ? "    " : "│   "), 2);
				System.out.println(prefijo + "│   " + "└── (D) " + '\u2563');
			} else {
				if (!nodo.esVacioHijoIzquierdo() && nodo.esVacioHijoDerecho() && esUltimo == 1) {
					printArbol(nodo.getHijoIzquierdo(), prefijo + (esUltimo == 1 ? "    " : "│   "), 2);
					System.out.println(prefijo + "    " + "└── (D) " + '\u2563');
				}
			}

			if (!nodo.esVacioHijoDerecho() && nodo.esVacioHijoIzquierdo() && esUltimo == 2) {
				System.out.println(prefijo + "│   " + "├──(I) " + '\u2563');
				printArbol(nodo.getHijoDerecho(), prefijo + (esUltimo == 1 ? "    " : "│   "), 1);
			} else {
				if (!nodo.esVacioHijoDerecho() && nodo.esVacioHijoIzquierdo() && esUltimo == 1) {
					System.out.println(prefijo + "    " + "├──(I) " + '\u2563');
					printArbol(nodo.getHijoDerecho(), prefijo + (esUltimo == 1 ? "    " : "│   "), 1);
				}
			}

		}
	}

	@Override
	public void printArbol() {
		if (!this.esArbolVacio()) {
			System.out.println("└──(R) " + this.raiz.getClave());
			if (!this.raiz.esVacioHijoIzquierdo() && !this.raiz.esVacioHijoDerecho()) {
				printArbol(this.raiz.getHijoIzquierdo(), "    ", 2);
				printArbol(this.raiz.getHijoDerecho(), "    ", 1);

			} else {
				if (!this.raiz.esVacioHijoIzquierdo() && this.raiz.esVacioHijoDerecho()) {
					printArbol(this.raiz.getHijoIzquierdo(), "    ", 2);
					System.out.println("    " + "└── (D) " + '\u2563');
				} else {
					if (!this.raiz.esVacioHijoDerecho() && this.raiz.esVacioHijoIzquierdo()) {
						System.out.println("    " + "├──(I) " + '\u2563');
						printArbol(this.raiz.getHijoDerecho(), "    ", 1);

					} else {
						System.out.println("    " + "├──(I) " + '\u2563');
						System.out.println("    " + "└──(D) " + '\u2563');
					}
				}
			}
		} else {
			System.out.println("└──(R) " + '\u2563');
		}
	}

	@Override
	public boolean contiene(K clave) {// el contiene solo me devuelve true o false si la clave existe
//tomar en cuenta que en los arboles binarios no puede haber claves repetidas

		return this.buscar(clave) != null;
	}

	@Override
	public int size() {// revisado es la cantidad de nodos que tiene el arbol

		int contador = 0;

		if (!this.esArbolVacio()) {
			Stack<Nodo<K, V>> pilaDeNodos = new Stack<>();
			pilaDeNodos.push(this.raiz);
			do {
				Nodo<K, V> nodoAux = pilaDeNodos.pop();
				contador++;
				if (!nodoAux.esVacioHijoDerecho()) {
					pilaDeNodos.push(nodoAux.getHijoDerecho());
				}
				if (!nodoAux.esVacioHijoIzquierdo()) {
					pilaDeNodos.push(nodoAux.getHijoIzquierdo());
				}
			} while (!pilaDeNodos.isEmpty());
		}

		return contador;
	}

	protected int altura(Nodo<K, V> nodoActual) {
		if (Nodo.esNodoVacio(nodoActual)) {
			return 0;
		}
		int alturaIzq = this.altura(nodoActual.getHijoIzquierdo());// revisado
		int alturaDer = this.altura(nodoActual.getHijoDerecho());

		return alturaIzq > alturaDer ? alturaIzq + 1 : alturaDer + 1;

	}

	@Override
	public int altura() {

//revisado

		return this.altura(this.raiz);// codigo de manera recursiva
	}

	@Override
	public void vaciar() {

		this.raiz = Nodo.nodoVacio();
//revisado

	}

	@Override
	public boolean esArbolVacio() {// revisado

		return Nodo.esNodoVacio(raiz);
	}

	protected int nivel(Nodo<K, V> nodoActual) { // revisado

		if (Nodo.esNodoVacio(nodoActual)) {
			return -1;
		}

		int nivelIzq = this.nivel(nodoActual.getHijoIzquierdo());
		int nivelDer = this.nivel(nodoActual.getHijoDerecho());

		return nivelIzq > nivelDer ? nivelIzq + 1 : nivelDer + 1;

	}

	@Override
	public int nivel() {// revisado

		return this.nivel(this.raiz);// entenderlo el recorrido de manera recursiva
	}

	public void ultimoNodoIzq(Nodo<K, V> actuall, Stack<Nodo<K, V>> pilaDeNodos) {

		if (Nodo.esNodoVacio(actuall)) {
			return;
		}
		pilaDeNodos.push(actuall);
		ultimoNodoIzq(actuall.getHijoIzquierdo(), pilaDeNodos);
	}

	@Override
	public List<K> recorridoEnInOrden() {
		List<K> reco = new ArrayList<>();

		if (!this.esArbolVacio()) {
			Stack<Nodo<K, V>> pilaDeNodos = new Stack<>();
			ultimoNodoIzq(this.raiz, pilaDeNodos);
			do {
				Nodo<K, V> actual = pilaDeNodos.pop();
				reco.add(actual.getClave());

				if (!actual.esVacioHijoDerecho()) {
					ultimoNodoIzq(actual.getHijoDerecho(), pilaDeNodos);
				}

			} while (!pilaDeNodos.isEmpty());
		}
		return reco;
	}



	@Override
	public List<K> recorridoEnPreOrden() { // revisado
		// recorrido en preorden iterativo

		List<K> recorrido = new LinkedList<>();
		if (!this.esArbolVacio()) {
			Stack<Nodo<K, V>> pilaDeNodos = new Stack<>();
			pilaDeNodos.push(this.raiz);
			do {
				Nodo<K, V> nodoAux = pilaDeNodos.pop();
				recorrido.add(nodoAux.getClave());
				if (!nodoAux.esVacioHijoDerecho()) {
					pilaDeNodos.push(nodoAux.getHijoDerecho());
				}
				if (!nodoAux.esVacioHijoIzquierdo()) {
					pilaDeNodos.push(nodoAux.getHijoIzquierdo());
				}
			} while (!pilaDeNodos.isEmpty());
		}

		return recorrido;
	}


	private void meterALaPilaParaPostOrden(Stack<Nodo<K, V>> pila, Nodo<K, V> nodoActual) {// revisado
		while (!Nodo.esNodoVacio(nodoActual)) {
			pila.push(nodoActual);
			if (!nodoActual.esVacioHijoIzquierdo()) {
				nodoActual = nodoActual.getHijoIzquierdo();
			} else {
				nodoActual = nodoActual.getHijoDerecho();
			}
		}

	}

	@Override
	public List<K> recorridoEnPostOrden() { // revisado

		List<K> recorrido = new ArrayList<>();
		if (!this.esArbolVacio()) {
			Stack<Nodo<K, V>> pila = new Stack<>();
			this.meterALaPilaParaPostOrden(pila, this.raiz);
			do {
				Nodo<K, V> nodoActual = pila.pop();
				recorrido.add(nodoActual.getClave());
				if (!pila.isEmpty()) {
					Nodo<K, V> nodoDelTope = pila.peek();
					if (nodoDelTope.getHijoIzquierdo() == nodoActual) {
						meterALaPilaParaPostOrden(pila, nodoDelTope.getHijoDerecho());
					}
				}
			} while (!pila.isEmpty());

		}
		return recorrido;
	}

	@Override /*
				 * me puede marca error porque esta es la superclase de otra clase que en este
				 * caso es la del arbolAVL tambien porque no lo tengo definido en la interfas,
				 * nota: cuando usas la interfas debes colocar encima del metodo @override para
				 * veficar que estas implementando ese metodo
				 */
	public List<K> recorridoPorNiveles() { // revisado

		List<K> recorrido = new ArrayList<>();
		if (!this.esArbolVacio()) {
			Queue<Nodo<K, V>> colaDeNodos = new LinkedList<>();
			colaDeNodos.offer(this.raiz);
			do {
				Nodo<K, V> nodoActual = colaDeNodos.poll();
				recorrido.add(nodoActual.getClave());
				if (!nodoActual.esVacioHijoIzquierdo()) {
					colaDeNodos.offer(nodoActual.getHijoIzquierdo());
				}
				if (!nodoActual.esVacioHijoDerecho()) {
					colaDeNodos.offer(nodoActual.getHijoDerecho());
				}

			} while (!colaDeNodos.isEmpty());

		}

		return recorrido;

	}

	private void recoaux(Nodo<K, V> nodo, int nivel, List<List<K>> L) {
		if (Nodo.esNodoVacio(nodo)) {
			return;
		}

		if (nivel >= L.size()) {
			L.add(new ArrayList<>());
		}
		L.get(nivel).add(nodo.getClave());

		recoaux(nodo.getHijoIzquierdo(), nivel + 1, L);
		recoaux(nodo.getHijoDerecho(), nivel + 1, L);

	}

	public List<K> recorridoPorNiveless() {
		List<List<K>> reco = new ArrayList<>();

		recoaux(this.raiz, 0, reco);

		List<K> res = new ArrayList<>();

		for (int i = 0; i < reco.size(); i++) {
			while (reco.get(i).size() > 0) {
				res.add(reco.get(i).get(0));
				reco.get(i).remove(0);
			}
		}
		reco.clear();
		return res;

	}

//------------------------------------------------------------

//---------------CODIGO DE PRUEBA------------------------------

	private Nodo<K, V> delete(Nodo<K, V> nodoActual, K claveAEliminar) {
		K claveNodoActual = nodoActual.getClave();

		if (claveAEliminar.compareTo(claveNodoActual) < 0) {
			Nodo<K, V> supuestoHijoIzquierdo = delete(nodoActual.getHijoIzquierdo(), claveAEliminar);
			nodoActual.setHijoIzquierdo(supuestoHijoIzquierdo);
			return nodoActual;
		}

		if (claveAEliminar.compareTo(claveNodoActual) > 0) {
			Nodo<K, V> supuestoHijoDerecho = delete(nodoActual.getHijoDerecho(), claveAEliminar);
			nodoActual.setHijoDerecho(supuestoHijoDerecho);
			return nodoActual;
		}

//Caso 1 es hoja
		if (!Nodo.esNodoVacio(nodoActual)) {
			return Nodo.nodoVacio();
		}
//Caso 2.1 solo tiene hijo izquierdo
		if (!nodoActual.esVacioHijoIzquierdo() && nodoActual.esVacioHijoDerecho()) {
			return nodoActual.getHijoIzquierdo();
		}

//Caso 2.2 solo tiene hijo derecho
		if (!nodoActual.esVacioHijoDerecho() && nodoActual.esVacioHijoIzquierdo()) {
			return nodoActual.getHijoDerecho();
		}

// Caso 3 tiene ambos hijos

		Nodo<K, V> nodoSucesor = this.getNodoSucesor(nodoActual.getHijoDerecho());
		Nodo<K, V> supuestoHijoDerecho = delete(nodoActual.getHijoDerecho(), nodoSucesor.getClave());
		nodoActual.setHijoDerecho(supuestoHijoDerecho);
		nodoActual.setClave(nodoSucesor.getClave());
		nodoActual.setValor(nodoSucesor.getValor());

		return nodoActual;
	}

	private Nodo<K, V> getNodoSucesor(Nodo<K, V> nodo) {
		while (!Nodo.esNodoVacio(nodo)) {
			nodo = nodo.getHijoIzquierdo();
		}
		return nodo;
	}

	public V delete(K claveAEliminar) {

		V valorARetornar = this.buscar(claveAEliminar);
		if (valorARetornar == null) {
			return null;
		}

		this.raiz = delete(this.raiz, claveAEliminar);
		return valorARetornar;

	}

//------------------------------------------------
	private void meterALaPilaParaInOrden1(Nodo<K, V> nodo, Stack<Nodo<K, V>> pila) {
		while (!Nodo.esNodoVacio(nodo)) {
			pila.add(nodo);
			nodo = nodo.getHijoIzquierdo();
		}
	}

	public int cantidadNodosConUnSoloHijo() {
		int cantidad = 0;
		if (!esArbolVacio()) {
			Stack<Nodo<K, V>> pilaDeNodos = new Stack<>();
			meterALaPilaParaInOrden1(raiz, pilaDeNodos);

			do {
				Nodo<K, V> nodoAux = pilaDeNodos.pop();

				if ((!nodoAux.esVacioHijoDerecho() && nodoAux.esVacioHijoIzquierdo())
						|| (!nodoAux.esVacioHijoIzquierdo() && nodoAux.esVacioHijoDerecho())) {
					cantidad++;
				}

				if (!nodoAux.esVacioHijoDerecho()) {
					meterALaPilaParaInOrden1(nodoAux.getHijoDerecho(), pilaDeNodos);
				}

			} while (!pilaDeNodos.isEmpty());
		}

		return cantidad;
	}



	private int cantidadDeHijosVacios(Nodo<K, V> nodo, int nivel, int contNivel) {
		if (Nodo.esNodoVacio(nodo)) {
			return 0;
		}

		int cantidad = 0;

		if (nivel == contNivel) {
			if (nodo.esVacioHijoDerecho()) {
				cantidad++;
			}
			if (nodo.esVacioHijoIzquierdo()) {
				cantidad++;
			}
		}
		return cantidad + cantidadDeHijosVacios(nodo.getHijoDerecho(), nivel, contNivel + 1)
				+ cantidadDeHijosVacios(nodo.getHijoIzquierdo(), nivel, contNivel + 1);

	}

	public int cantidadDeHijosVacios(int nivel) { // revisado
// recorrido en preorden iterativo

		return cantidadDeHijosVacios(this.raiz, nivel, 0);
	}



	public int cantidadDeHijosVaciosInOrden() {
		int cant = 0;
		if (!esArbolVacio()) {
			Stack<Nodo<K, V>> pilaDeNodos = new Stack<>();
			meter(raiz, pilaDeNodos);
			cant++;
			do {
				Nodo<K, V> nodoAux = pilaDeNodos.pop();
				if (!nodoAux.esVacioHijoDerecho()) {
					meter(nodoAux.getHijoDerecho(), pilaDeNodos);
					cant++;
				} else {
					cant++;
				}
			} while (!pilaDeNodos.isEmpty());
		}
		return cant;
	}

	private void meter(Nodo<K, V> nodo, Stack<Nodo<K, V>> pila) {
		while (!Nodo.esNodoVacio(nodo)) {
			pila.push(nodo);
			nodo = nodo.getHijoIzquierdo();

		}
	}


	public List<V> listar() {
	    List<V> lista = new ArrayList<>();
	    listarRecursivo(raiz, lista);
	    return lista;
	}

	private void listarRecursivo(Nodo<K, V> nodo, List<V> lista) {
	    if (nodo != null && !Nodo.esNodoVacio(nodo)) {
	        listarRecursivo(nodo.getHijoIzquierdo(), lista);
	        lista.add(nodo.getValor());
	        listarRecursivo(nodo.getHijoDerecho(), lista);
	    }
	}


}
