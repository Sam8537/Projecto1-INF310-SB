package com.uagrm.ficct.ejemplo2;

import java.util.List;



public class ArbolAVL <K extends Comparable<K> ,V>
extends ArbolBinario<K, V>
/*Henry Salas Huatta*/
{


private static final byte RANGO_SUPERIOR = 1;
private static final byte RANGO_INFERIOR = -1;


public ArbolAVL() {

}
// arreglar este codigo

public ArbolAVL(List<K> clavesInOrden, List<V> valoresInOrden, List<K> clavesNoInOrden, List<V> valoresNoInOrden,
		boolean esConPostOrden) {
super(clavesInOrden, valoresInOrden, clavesNoInOrden, valoresNoInOrden, esConPostOrden);
}


private Nodo<K, V> insertar(Nodo<K, V> nodoActual, K claveAInsertar, V valorAsociado) {

if (Nodo.esNodoVacio(nodoActual)) {
Nodo<K, V> nodoNuevo = new Nodo<>(claveAInsertar, valorAsociado);
return nodoNuevo;
}

K claveDelNodoActual = nodoActual.getClave();


if (claveAInsertar.compareTo(claveDelNodoActual) < 0) {
Nodo<K, V> supuestoNuevoHI =
		insertar(nodoActual.getHijoIzquierdo(), claveAInsertar, valorAsociado);
nodoActual.setHijoIzquierdo(supuestoNuevoHI);
return balancear(nodoActual);
}

if (claveAInsertar.compareTo(claveDelNodoActual) > 0) {
Nodo<K, V> supuestoNuevoHD = insertar(nodoActual.getHijoDerecho(), claveAInsertar,
		valorAsociado);
nodoActual.setHijoDerecho(supuestoNuevoHD);
return balancear(nodoActual);
}

nodoActual.setValor(valorAsociado);
return nodoActual;

}

private Nodo<K, V> balancear(Nodo<K, V> nodoActual){

int alturaPorIzquierda = super.altura(nodoActual.getHijoIzquierdo());
int alturaPorDerecha = super.altura(nodoActual.getHijoDerecho());

int diferenciaDeAlturas = alturaPorDerecha - alturaPorIzquierda;     //revisar si esto esta bien?


if(diferenciaDeAlturas > ArbolAVL.RANGO_SUPERIOR){
    //rotación a la Izquierda
	Nodo<K, V> hijoDerechoDelAct = nodoActual.getHijoDerecho();
    alturaPorIzquierda = super.altura(hijoDerechoDelAct.getHijoIzquierdo());
    alturaPorDerecha = super.altura(hijoDerechoDelAct.getHijoDerecho());

    diferenciaDeAlturas = alturaPorDerecha - alturaPorIzquierda;

    if(diferenciaDeAlturas == ArbolAVL.RANGO_INFERIOR){
        return rotacionDobleAIzquierda(nodoActual);
    }
    return rotacionSimpleAIzquierda(nodoActual);

}else if(diferenciaDeAlturas < ArbolAVL.RANGO_INFERIOR){
    //rotación a la Derecha

    Nodo<K,V> hijoIzquierdoDelAct = nodoActual.getHijoIzquierdo();
    alturaPorIzquierda = super.altura(hijoIzquierdoDelAct.getHijoIzquierdo());
    alturaPorDerecha = super.altura(hijoIzquierdoDelAct.getHijoDerecho());

    diferenciaDeAlturas = alturaPorDerecha - alturaPorIzquierda;

    if(diferenciaDeAlturas == ArbolAVL.RANGO_SUPERIOR){
        return rotacionDobleADerecha(nodoActual);
    }
    return rotacionSimpleADerecha(nodoActual);
}

return nodoActual;

}

private Nodo<K, V> rotacionSimpleADerecha(Nodo<K, V> nodoActual){
Nodo<K, V> nodoARetornar = nodoActual.getHijoIzquierdo();
nodoActual.setHijoIzquierdo(nodoARetornar.getHijoDerecho());
nodoARetornar.setHijoDerecho(nodoActual);

return nodoARetornar;
}


private Nodo<K, V> rotacionSimpleAIzquierda(Nodo<K, V> nodoActual) {
Nodo<K, V> nodoARetornar = nodoActual.getHijoDerecho();
nodoActual.setHijoDerecho(nodoARetornar.getHijoIzquierdo());
nodoARetornar.setHijoIzquierdo(nodoActual);

return nodoARetornar;
}

private Nodo<K, V> rotacionDobleADerecha(Nodo<K, V> nodoActual){
// Rotación doble: Primero a la izquierda y luego a la derecha
nodoActual.setHijoIzquierdo(rotacionSimpleAIzquierda(nodoActual.getHijoIzquierdo()));
return rotacionSimpleADerecha(nodoActual);
}

private Nodo<K, V> rotacionDobleAIzquierda(Nodo<K, V> nodoActual) {
// Rotación doble: Primero a la derecha y luego a la izquierda
nodoActual.setHijoDerecho(rotacionSimpleADerecha(nodoActual.getHijoDerecho()));
return rotacionSimpleAIzquierda(nodoActual);
}


/*NOTA REALIZAR TAMBIEN EL ELIMINAR*/

@Override
public void insertar(K claveAInsertar, V valorAsociado) {

if(claveAInsertar == null){
throw new IllegalArgumentException("La clave no puede ser nula");
}
if (valorAsociado == null){
throw new IllegalArgumentException("El valor no puede ser nulo");
}

//this.raiz = insertar(super.raiz, claveAInsertar, valorAsociado);
this.raiz = insertar(super.raiz, claveAInsertar, valorAsociado);


//super.insertar(claveAInsertar, valorAsociado);
}

@Override
protected Nodo<K, V> getSucesor(Nodo<K, V> nodoAux) {
// TODO Auto-generated method stub
return super.getSucesor(nodoAux);
}

//---------------------------------------------------------------

private Nodo<K,V> eliminar(Nodo<K, V> nodoActual, K claveAEliminar){

K claveDelNodoActual = nodoActual.getClave();
if (claveAEliminar.compareTo(claveDelNodoActual) < 0 ) {
Nodo<K, V> supuestoNuevoHijoIzquierdo =
	eliminar(nodoActual.getHijoIzquierdo(), claveAEliminar);
nodoActual.setHijoIzquierdo(supuestoNuevoHijoIzquierdo);
return balancear(nodoActual);
}

if(claveAEliminar.compareTo(claveDelNodoActual) > 0){
Nodo<K, V> supuestoNuevoHijoDerecho =
	eliminar(nodoActual.getHijoDerecho(), claveAEliminar);
nodoActual.setHijoDerecho(supuestoNuevoHijoDerecho);
return balancear(nodoActual);
}


//caso 1: clave a eliminar es un nodo hoja
if(nodoActual.esHoja()){
return Nodo.nodoVacio();
}

//caso2A: clave a eliminar solo tiene hijo izquierdo
if(!nodoActual.esVacioHijoIzquierdo() && nodoActual.esVacioHijoDerecho()){
return nodoActual.getHijoIzquierdo();
}

//caso2B: clave a eliminar solo tiene hijo derecho
if(!nodoActual.esVacioHijoDerecho() && nodoActual.esVacioHijoIzquierdo()){
return nodoActual.getHijoDerecho();
}

//caso3: clave a eliminar tiene ambos hijos
Nodo<K, V> nodoDelSucesor = this.getSucesor(nodoActual.getHijoDerecho());
Nodo<K, V> supuestoNuevoHijoDerecho =
eliminar(nodoActual.getHijoDerecho(), nodoDelSucesor.getClave());

nodoActual.setHijoDerecho(supuestoNuevoHijoDerecho);
nodoActual.setClave(nodoDelSucesor.getClave());    // no se cambia de nodo solo se cambia de clave y valor
nodoActual.setValor(nodoDelSucesor.getValor());	// porque si lo cambio el nodo cambiaria su direccion
return balancear(nodoActual);								//el cual perderia todas sus ramas

}



@Override
public V eliminar(K claveAEliminar) {


V valorARetornar = this.buscar(claveAEliminar);

if (valorARetornar == null) {
return null;
}

this.raiz = eliminar(this.raiz, claveAEliminar);

return valorARetornar;
}




//--------------------------------------------------------------




/*@Override	 NOTA REVISAR ESTE CODIGO DE AVL
public V eliminar(K claveAEliminar) {	//falta hacer el eliminar
// TODO Auto-generated method stub
//return super.eliminar(claveAEliminar);


}+*/

@Override
public V buscar(K clave) {
// TODO Auto-generated method stub
return super.buscar(clave);
}

@Override
public String toString() {
// TODO Auto-generated method stub
return super.toString();
}

@Override
public boolean contiene(K clave) {
// TODO Auto-generated method stub
return super.contiene(clave);
}

@Override
public int size() {
// TODO Auto-generated method stub
return super.size();
}

@Override
protected int altura(Nodo<K, V> nodoActual) {
// TODO Auto-generated method stub
return super.altura(nodoActual);
}

@Override
public int altura() {
// TODO Auto-generated method stub
return super.altura();
}

@Override
public void vaciar() {
// TODO Auto-generated method stub
super.vaciar();
}

@Override
public boolean esArbolVacio() {
// TODO Auto-generated method stub
return super.esArbolVacio();
}

@Override
protected int nivel(Nodo<K, V> nodoActual) {
// TODO Auto-generated method stub
return super.nivel(nodoActual);
}

@Override
public int nivel() {
// TODO Auto-generated method stub
return super.nivel();
}

@Override
public void printArbol() {
// TODO Auto-generated method stub
super.printArbol();
}




}
