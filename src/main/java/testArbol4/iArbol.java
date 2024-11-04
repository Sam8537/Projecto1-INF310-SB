package testArbol4;

import java.util.List;

public interface iArbol <K extends Comparable<K>,V> {




	void insertar(K clave, V valor);// buscar la manera de hacerlo recursivo
	V eliminar(K clave);  //buscar la manera de hacerlo iterativo
	V buscar(K claveABuscar);
	boolean contiene(K clave);
	int size();
	int altura();
	void vaciar();
	boolean esArbolVacio();
	int nivel();

	//V delete (K clave);

	//recorridos iterativos  --- todos los metodos iterativos estan completos
	List<K> recorridoEnInOrden();
	List<K> recorridoEnPreOrden();
	List<K> recorridoEnPostOrden();
	List<K> recorridoPorNiveles();
	void printArbol();



}

