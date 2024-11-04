package com.uagrm.ficct.ejemplo2;



public class Nodo<K, V> {
	private K clave;
	private V valor;

	private Nodo<K, V> hijoIzquierdo;
	private Nodo<K, V> hijoDerecho;

	public Nodo() {  //Constructor Sin parametro
		//si no inicializo el los atributos, se inicializa de manera predeterminada
		//con valores nulos en este caso yo los voy a inicializar para practicar igual con valores nulos
		clave = null;
		valor = null;
		hijoIzquierdo = null;
		hijoDerecho = null;
	}

	public Nodo(K clave, V valor) {//Constructor con parametro

		this.clave = clave;
		this.valor = valor;
		/*clave = null;
		valor = null;*/
		this.hijoIzquierdo = null;
		this.hijoDerecho = null;

	}

	public K getClave() {
		return clave;
	}

	public void setClave(K clave) {
		this.clave = clave;
	}

	public V getValor() {
		return valor;
	}

	public void setValor(V valor) {
		this.valor = valor;
	}

	public Nodo<K, V> getHijoIzquierdo() {
		return hijoIzquierdo;
	}

	public void setHijoIzquierdo(Nodo<K, V> hijoIzquierdo) {
		this.hijoIzquierdo = hijoIzquierdo;
	}

	public Nodo<K, V> getHijoDerecho() {
		return hijoDerecho;
	}

	public void setHijoDerecho(Nodo<K, V> hijoDerecho) {
		this.hijoDerecho = hijoDerecho;
	}

	public boolean esVacioHijoIzquierdo() {
		return Nodo.esNodoVacio(this.getHijoIzquierdo());

	}

	public boolean esVacioHijoDerecho() {
		return Nodo.esNodoVacio(this.getHijoDerecho());

	}

	public boolean esHoja() {// si el hijo izquierdo es null y el derecho es nulo entonces es hoja
		return this.esVacioHijoIzquierdo() && this.esVacioHijoDerecho();

	}

	public static boolean esNodoVacio(Nodo nodo) {
		return nodo == Nodo.nodoVacio();
	}

	public static Nodo nodoVacio() {

		return null;
	}
}
