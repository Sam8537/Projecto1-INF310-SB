package com.uagrm.ficct.ejemplo2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.uagrm.ficct.ejemplo2.excepciones.OrdenInvalidoException;

import testArbol4.iArbol;

public class ArbolMV<K extends Comparable<K>, V> implements iArbol<K, V> {
	protected NodoMV<K, V> raiz;
	protected int orden;
	protected static final int ORDEN_MINIMO = 3;
	protected static final int POSICION_INVALIDA = -1;

	public ArbolMV() {
		this.orden = ArbolMV.ORDEN_MINIMO;
	}

	public ArbolMV(int orden) throws OrdenInvalidoException {
		if (orden < ArbolMV.ORDEN_MINIMO) {
			throw new OrdenInvalidoException();
		}
		this.orden = orden;
	}

	protected int buscarPosicionDeClave(K claveAInsertar, NodoMV<K, V> nodoActual) {
		for (int i = 0; i < nodoActual.nroDeClavesNoVacias(); i++) {
			if (claveAInsertar.compareTo(nodoActual.getClave(i)) == 0) {
				return i;
			}
		}
		return -1;
	}

	protected int buscarPosicionPorDondeBajar(K claveAInsertar, NodoMV<K, V> nodoActual) {
		for (int i = 0; i < nodoActual.nroDeClavesNoVacias(); i++) {
			if (claveAInsertar.compareTo(nodoActual.getClave(i)) < 0) {
				return i;
			}
		}
		return nodoActual.nroDeClavesNoVacias();
	}

	protected void insertarClaveYValorOrdenado(NodoMV<K, V> nodoActual, K claveAInsertar, V valorAsociado) {

		for (int i = nodoActual.nroDeClavesNoVacias(); i > 0; i--) {
			if (claveAInsertar.compareTo(nodoActual.getClave(i - 1)) < 0) {
				nodoActual.setClave(i, nodoActual.getClave(i - 1));
				nodoActual.setValor(i, nodoActual.getValor(i - 1));
				// se puede borrar las lineas de los hijos

				nodoActual.setHijo(i + 1, nodoActual.getHijo(i));

				if ((i - 1) == 0) {
					nodoActual.setClave(0, claveAInsertar);
					nodoActual.setValor(0, valorAsociado);
				}

			} else {
				nodoActual.setClave(i, claveAInsertar);
				nodoActual.setValor(i, valorAsociado);
				break;
			}
		}
		if (nodoActual.nroDeClavesNoVacias() == 0) {
			nodoActual.setClave(0, claveAInsertar);
			nodoActual.setValor(0, valorAsociado);
		}
	}

	@Override
	public void insertar(K claveAInsertar, V valorAsociado) {
		if (claveAInsertar == null) {
			throw new IllegalArgumentException("clave a insertar no puede ser nula");
		}

		if (valorAsociado == null) {
			throw new IllegalArgumentException("valor a insertar no puede ser nula");
		}

		if (this.esArbolVacio()) {
			this.raiz = new NodoMV<>(this.orden, claveAInsertar, valorAsociado);
			return;
		}

		NodoMV<K, V> nodoAux = this.raiz;
		do {// buscarPosicionDeClave -> lo que hace es revisar si ya existe la clave dentro
			// de nodo
			int posicionDeClaveAInsertar = this.buscarPosicionDeClave(claveAInsertar, nodoAux);

			if (posicionDeClaveAInsertar != ArbolMV.POSICION_INVALIDA) {
				// throw new ExceptionDatoYaExiste();
				nodoAux.setValor(posicionDeClaveAInsertar, valorAsociado);
				nodoAux = NodoMV.nodoVacio();
			} else {
				if (nodoAux.esHoja()) {
					// el nodo auxiliar es hoja y la clave no esta en el nodo
					if (nodoAux.clavesLLenas()) {
						int posicionPorDondeBajar = this.buscarPosicionPorDondeBajar(claveAInsertar, nodoAux);
						NodoMV<K, V> nodoNuevo = new NodoMV<>(this.orden, claveAInsertar, valorAsociado);
						nodoAux.setHijo(posicionPorDondeBajar, nodoNuevo);
					} else {
						this.insertarClaveYValorOrdenado(nodoAux, claveAInsertar, valorAsociado);
					}

					nodoAux = NodoMV.nodoVacio();
				} else {
					// el nodo auxiliar no es una hoja y sabemos que la clave no esta en este nodo
					int posicionPorDondeBajar = this.buscarPosicionPorDondeBajar(claveAInsertar, nodoAux);
					if (nodoAux.esHijoVacio(posicionPorDondeBajar)) {
						NodoMV<K, V> nodoNuevo = new NodoMV<>(this.orden, claveAInsertar, valorAsociado);
						nodoAux.setHijo(posicionPorDondeBajar, nodoNuevo);
						nodoAux = NodoMV.nodoVacio();
					} else {
						nodoAux = nodoAux.getHijo(posicionPorDondeBajar);
					}
				}
			}

		} while (!NodoMV.esNodoVacio(nodoAux));

	}

	@Override
	public V eliminar(K claveAEliminar) {
		V valorARetornar = this.buscar(claveAEliminar);

		if (valorARetornar == NodoMV.datoVacio()) {
			return null;
		}

		this.raiz = eliminar(this.raiz, claveAEliminar);

		return valorARetornar;
	}

	private boolean hayHijosMasAdelanteDeLaPosicion(NodoMV<K, V> nodoActual, int posicion) {

		for (int i = posicion + 1; i < nodoActual.nroDeClavesNoVacias(); i++) {
			if (!nodoActual.esHijoVacio(i)) {
				return true;
			}
		}

		if (!nodoActual.esHijoVacio(nodoActual.nroDeClavesNoVacias())) {
			return true;
		}
		return false;
	}

	protected void eliminarClaveDeNodoDePosicion(NodoMV<K, V> nodoActual, int posicion) {

		for (int i = posicion; i < nodoActual.nroDeClavesNoVacias(); i++) {
            if(i == nodoActual.nroDeClavesNoVacias() - 1){
                K claveVacia = (K)NodoMV.datoVacio();
                V valorVacio = (V)NodoMV.datoVacio();

                nodoActual.setClave(i, claveVacia);
                nodoActual.setValor(i, valorVacio);
                nodoActual.setHijo(i, nodoActual.getHijo(i+1));
                nodoActual.setHijo(i+1, NodoMV.nodoVacio());
            }else{
                nodoActual.setClave(i, nodoActual.getClave(i + 1));
                nodoActual.setValor(i, nodoActual.getValor(i + 1));
                nodoActual.setHijo(i, nodoActual.getHijo(i+1));
            }
        }
	}

	private K obtenerSucesorInOrden(NodoMV<K, V> nodoActual, int posicion) {
		List<K> listaDeClavesInOrden = this.recorridoEnInOrden();

		for (int i = 0; i < listaDeClavesInOrden.size(); i++) {
			if (nodoActual.getClave(posicion).compareTo(listaDeClavesInOrden.get(i)) == 0) {
				return listaDeClavesInOrden.get(i + 1);
			}
		}
		return (K) NodoMV.datoVacio();

	}

	private K obtenerPredecesorInOrden(NodoMV<K, V> nodoActual, int posicion) {
		List<K> listaDeClavesInOrden = this.recorridoEnInOrden();

		for (int i = 0; i < listaDeClavesInOrden.size(); i++) {
			if (nodoActual.getClave(posicion).compareTo(listaDeClavesInOrden.get(i)) == 0) {
				return listaDeClavesInOrden.get(i - 1);
			}
		}
		return (K) NodoMV.datoVacio();

	}

	private NodoMV<K, V> eliminar(NodoMV<K, V> nodoActual, K claveAEliminar) {
		if (NodoMV.esNodoVacio(nodoActual)) {
			return null;
		}
		int posicionDeDatoEnNodo = this.buscarPosicionDeClave(claveAEliminar, nodoActual);

		if (posicionDeDatoEnNodo != POSICION_INVALIDA) {
			if (nodoActual.esHoja()) {
				this.eliminarClaveDeNodoDePosicion(nodoActual, posicionDeDatoEnNodo);
				if (nodoActual.nroDeClavesNoVacias() == 0) {
					return NodoMV.nodoVacio();
				}
				return nodoActual;
			}
			// nodo actual no es hoja, pero el dato esta hay, entonces debo buscar un
			// reemplazo
			// y eliminar antes de usarlo
			K datoDeReemplazo;
			if (hayHijosMasAdelanteDeLaPosicion(nodoActual, posicionDeDatoEnNodo)) {
				datoDeReemplazo = this.obtenerSucesorInOrden(nodoActual, posicionDeDatoEnNodo);
			} else {
				datoDeReemplazo = this.obtenerPredecesorInOrden(nodoActual, posicionDeDatoEnNodo);
			}

			V valorDeReemplazo = buscar(datoDeReemplazo);
			nodoActual = eliminar(nodoActual, datoDeReemplazo);
			nodoActual.setClave(posicionDeDatoEnNodo, datoDeReemplazo);
			nodoActual.setValor(posicionDeDatoEnNodo, valorDeReemplazo);
			return nodoActual;

		}

		int posicionPorDondeBajar = buscarPosicionPorDondeBajar(claveAEliminar, nodoActual);
		NodoMV<K, V> supuestoNuevoHijo = eliminar(nodoActual.getHijo(posicionPorDondeBajar), claveAEliminar);
		nodoActual.setHijo(posicionPorDondeBajar, supuestoNuevoHijo);
		return nodoActual;
	}

	@Override
	public V buscar(K datoABuscar) {// RECURSIVO
		return buscar(raiz, datoABuscar);
	}

	private V buscar(NodoMV<K, V> nodo, K datoABuscar) {
		if (NodoMV.esNodoVacio(nodo)) {
			return null;
		}

		for (int i = 0; i < nodo.nroDeClavesNoVacias(); i++) {
			if (datoABuscar.compareTo(nodo.getClave(i)) == 0) {
				return nodo.getValor(i);
			}
			if (datoABuscar.compareTo(nodo.getClave(i)) < 0) {
				return buscar(nodo.getHijo(i), datoABuscar);

			}
		}

		return buscar(nodo.getHijo(nodo.nroDeClavesNoVacias()), datoABuscar);

	}

	@Override
	public boolean contiene(K clave) {
		// return this.buscar(clave) != null;
		return this.buscar(clave) != NodoMV.datoVacio();
	}

	@Override
	public int size() {
		return size(raiz);
	}

	private int size(NodoMV<K, V> nodo) {
		if (NodoMV.esNodoVacio(nodo)) {
			return 0;
		}
		int tamaño = 0;
		for (int i = 0; i < this.orden; i++) {
			tamaño = tamaño + size(nodo.getHijo(i));
		}

		return 1 + tamaño;
	}

	protected int altura(NodoMV<K, V> nodoActual) {
		if (NodoMV.esNodoVacio(nodoActual)) {
			return 0;
		}

		int alturaMayor = 0;
		for (int i = 0; i < this.orden; i++) {
			int alturaDelHijoActual = altura(nodoActual.getHijo(i));
			if (alturaDelHijoActual > alturaMayor) {
				alturaMayor = alturaDelHijoActual;
			}
		}
		return alturaMayor + 1;
	}

	@Override
	public int altura() {
		return this.altura(this.raiz);
	}

	@Override
	public void vaciar() {
		this.raiz = NodoMV.nodoVacio();

	}

	@Override
	public boolean esArbolVacio() {
		return NodoMV.esNodoVacio(this.raiz);
	}

	protected int nivel(NodoMV<K, V> nodoActual) {
		if (NodoMV.esNodoVacio(nodoActual)) {
			return -1;
		}

		int nivelMayor = -1;
		for (int i = 0; i < this.orden; i++) {
			int nivelActual = nivel(nodoActual.getHijo(i));
			if (nivelActual > nivelMayor) {
				nivelMayor = nivelActual;
			}
		}

		return nivelMayor + 1;
	}

	@Override
	public int nivel() {
		return nivel(this.raiz);
	}

	private void recorridoEnInOrden(NodoMV<K, V> nodoActual, List<K> lista) {
		if (NodoMV.esNodoVacio(nodoActual)) {
			return;
		}

		for (int i = 0; i < nodoActual.nroDeClavesNoVacias(); i++) {
			this.recorridoEnInOrden(nodoActual.getHijo(i), lista);
			lista.add(nodoActual.getClave(i));
		}

		this.recorridoEnInOrden(nodoActual.getHijo(nodoActual.nroDeClavesNoVacias()), lista);
	}

	@Override
	public List<K> recorridoEnInOrden() {
		List<K> recorrido = new ArrayList<>();
		recorridoEnInOrden(this.raiz, recorrido);
		return recorrido;
	}

	private void recorridoEnPreOrden(NodoMV<K, V> nodoActual, List<K> lista) {
		if (NodoMV.esNodoVacio(nodoActual)) {
			return;
		}

		for (int i = 0; i < nodoActual.nroDeClavesNoVacias(); i++) {
			lista.add(nodoActual.getClave(i));
			this.recorridoEnPreOrden(nodoActual.getHijo(i), lista);
		}

		this.recorridoEnPreOrden(nodoActual.getHijo(nodoActual.nroDeClavesNoVacias()), lista);
	}

	@Override
	public List<K> recorridoEnPreOrden() {
		List<K> recorrido = new ArrayList<>();
		recorridoEnPreOrden(this.raiz, recorrido);
		return recorrido;
	}

	private void recorridoEnPostOrden(NodoMV<K, V> nodoActual, List<K> lista) {
		if (NodoMV.esNodoVacio(nodoActual)) {
			return;
		}

		this.recorridoEnPostOrden(nodoActual.getHijo(0), lista);
		for (int i = 0; i < nodoActual.nroDeClavesNoVacias(); i++) {
			this.recorridoEnPostOrden(nodoActual.getHijo(i + 1), lista);
			lista.add(nodoActual.getClave(i));
		}
	}

	@Override
	public List<K> recorridoEnPostOrden() {
		List<K> recorrido = new ArrayList<>();
		recorridoEnPostOrden(this.raiz, recorrido);
		return recorrido;
	}

	@Override
	public List<K> recorridoPorNiveles() {

		List<K> recorrido = new ArrayList<>();
		if (!this.esArbolVacio()) {
			Queue<NodoMV<K, V>> colaDeNodos = new LinkedList<>();
			colaDeNodos.offer(this.raiz);
			do {
				NodoMV<K, V> nodoActual = colaDeNodos.poll();

				for (int i = 0; i < nodoActual.nroDeClavesNoVacias(); i++) {

					recorrido.add(nodoActual.getClave(i));

					if (!nodoActual.esHijoVacio(i)) {
						colaDeNodos.offer(nodoActual.getHijo(i));
					}

				} // fin del ciclo

				if (!nodoActual.esHijoVacio(nodoActual.nroDeClavesNoVacias())) {
					colaDeNodos.offer(nodoActual.getHijo(nodoActual.nroDeClavesNoVacias()));
				}

			} while (!colaDeNodos.isEmpty());

		}

		return recorrido;

	}

	@Override
	public void printArbol() {
		// TODO Auto-generated method stub

	}

	private int cantidadDeHijosVacios(NodoMV<K, V> nodoActual, int nivel, int nivelIni) {
		if (NodoMV.esNodoVacio(nodoActual)) {
			return 0;
		}

		int cantidadMayor = 0;
		int cantidadrecursivo = 0;

		for (int i = 0; i < this.orden; i++) {
			if (!nodoActual.esHijoVacio(i)) {
				cantidadrecursivo = cantidadrecursivo
						+ cantidadDeHijosVacios(nodoActual.getHijo(i), nivel, nivelIni + 1);
			} else {
				if (nivel == nivelIni) {
					cantidadMayor++;
				}
			}
		}

		return cantidadMayor + cantidadrecursivo;
	}

// recursivo
	public int cantidadDeHijosVacios(int nivel) {
		return cantidadDeHijosVacios(raiz, nivel, 0);
	}

	private int cantidad(NodoMV<K, V> nodo, int nivel, int nivelAct) {
		if (NodoMV.esNodoVacio(nodo)) {
			return 0;
		}

		int cant = 0;

		for (int i = 0; i < nodo.nroDeClavesNoVacias(); i++) {
			if (!nodo.esHijoVacio(i)) {
				if (nivelAct >= nivel) {
					cant++;
				}

				cant = cant + cantidad(nodo.getHijo(i), nivel, nivelAct + 1);
			}
		}
		if (!nodo.esHijoVacio(nodo.nroDeClavesNoVacias())) {
			if (nivelAct >= nivel) {
				cant++;
			}

			cant = cant + cantidad(nodo.getHijo(nodo.nroDeClavesNoVacias()), nivel, nivelAct + 1);
		}
		return cant;
	}

	public int cantidad1(int nivel) {
		return cantidad(raiz, nivel, 0);
	}

	private int cantidadDeNodosAPartirDeUnNivel(NodoMV<K, V> nodoActual, int k, int nivel, int nivelIni) {
		if (NodoMV.esNodoVacio(nodoActual)) {
			return 0;
		}

		int cantidadNodos = 0;
		int cantidadrecursivo = 0;
		int cantidadHijos = 0;

		for (int i = 0; i < nodoActual.nroDeClavesNoVacias(); i++) {
			if (!nodoActual.esHijoVacio(i)) {
				cantidadrecursivo = cantidadrecursivo
						+ cantidadDeNodosAPartirDeUnNivel(nodoActual.getHijo(i), k, nivel, nivelIni + 1);
				cantidadHijos++;
			}
		}

		if (!nodoActual.esHijoVacio(nodoActual.nroDeClavesNoVacias())) {
			cantidadrecursivo = cantidadrecursivo + cantidadDeNodosAPartirDeUnNivel(
					nodoActual.getHijo(nodoActual.nroDeClavesNoVacias()), k, nivel, nivelIni + 1);
			cantidadHijos++;
		}
		if (nivelIni >= nivel) {
			if (cantidadHijos >= k) {
				cantidadNodos++;
			}
		}

		return cantidadNodos + cantidadrecursivo;
	}

// recursivo k = hijos
	public int cantidadDeNodosAPartirDeUnNivel(int k, int nivel) {
		return cantidadDeNodosAPartirDeUnNivel(raiz, k, nivel, 0);
	}

}
