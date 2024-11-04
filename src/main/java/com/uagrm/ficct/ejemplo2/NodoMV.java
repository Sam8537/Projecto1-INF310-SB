package com.uagrm.ficct.ejemplo2;

import java.util.ArrayList;
import java.util.List;



public class NodoMV<K,V> {
	private List<K> listaDeClaves;
	private List<V> listaDeValores;

	private List<NodoMV<K,V>> listaDeHijos;

	public static NodoMV nodoVacio() {
		return null;
	}

	public static Object datoVacio() {
		return null;
	}

	public static boolean esNodoVacio(NodoMV nodo) {
		return nodo == NodoMV.nodoVacio();
	}

	public NodoMV (int orden) {
		this.listaDeClaves = new ArrayList<>();
		this.listaDeValores =  new ArrayList<>();
		this.listaDeHijos = new ArrayList<>();

		for (int i = 0; i < (orden - 1) ; i++) {
			this.listaDeClaves.add((K)NodoMV.datoVacio());
			this.listaDeValores.add((V)NodoMV.datoVacio());
			this.listaDeHijos.add(NodoMV.nodoVacio());
		}
		this.listaDeHijos.add(NodoMV.nodoVacio());
	}

	public NodoMV(int orden, K primerClave, V primerValor) {
		this(orden);
		this.listaDeClaves.set(0, primerClave);
		this.listaDeValores.set(0, primerValor);
	}

	public K getClave(int posicion) {
		return this.listaDeClaves.get(posicion);
	}

	public void setClave(int posicion, K unaClave) {
		this.listaDeClaves.set(posicion, unaClave);

	}

	public V getValor(int posicion) {
		return this.listaDeValores.get(posicion);
	}

	public void setValor(int posicion, V unValor) {
		this.listaDeValores.set(posicion, unValor);
	}

	public NodoMV<K,V> getHijo(int posicion) {
		return this.listaDeHijos.get(posicion);
	}

	public void setHijo(int posicion, NodoMV<K,V> nodo) {// revisar si esta bien hecho esto
		this.listaDeHijos.set(posicion, nodo);
	}

	public boolean esHijoVacio(int posicion) {
		return NodoMV.esNodoVacio(this.getHijo(posicion));
	}

	public boolean esDatoVacio(int posicion) {
		//return this.getValor(posicion) == NodoMV.datoVacio();
		return this.getClave(posicion) == NodoMV.datoVacio();
	}

	public boolean esHoja() {
		for (int i=0 ; i < this.listaDeHijos.size(); i++ ) {
			if (!this.esHijoVacio(i)) {
				return false;
			}
		}
		return true;
	}

	public int nroDeClavesNoVacias() {
		int cantidad = 0;
		for (int i = 0; i < this.listaDeClaves.size(); i++) {
			if (!this.esDatoVacio(i)) {
				cantidad++;
			}
		}
		return cantidad;
	}

	public boolean hayClavesNoVacias() {
		return this.nroDeClavesNoVacias() != 0;
 	}

	public boolean clavesLLenas() {
		return this.nroDeClavesNoVacias() == this.listaDeClaves.size();
	}
}
