package com.uagrm.ficct.ejemplo2;

import java.util.List;
import java.util.Stack;

import com.uagrm.ficct.ejemplo2.excepciones.OrdenInvalidoException;



public class ArbolB  <K extends Comparable<K>,V> extends ArbolMV<K,V> {
	 @Override
	protected int buscarPosicionDeClave(K claveAInsertar, NodoMV<K, V> nodoActual) {
		// TODO Auto-generated method stub
		return super.buscarPosicionDeClave(claveAInsertar, nodoActual);
	}

	@Override
	protected int buscarPosicionPorDondeBajar(K claveAInsertar, NodoMV<K, V> nodoActual) {
		// TODO Auto-generated method stub
		return super.buscarPosicionPorDondeBajar(claveAInsertar, nodoActual);
	}

	@Override
	protected void insertarClaveYValorOrdenado(NodoMV<K, V> nodoActual, K claveAInsertar, V valorAsociado) {
		// TODO Auto-generated method stub
		super.insertarClaveYValorOrdenado(nodoActual, claveAInsertar, valorAsociado);
	}

	/*@Override
	public V eliminar(K claveAEliminar) {
		// TODO Auto-generated method stub
		return super.eliminar(claveAEliminar);
	}*/

	@Override
	public V buscar(K datoABuscar) {
		// TODO Auto-generated method stub
		return super.buscar(datoABuscar);
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
	protected int altura(NodoMV<K, V> nodoActual) {
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
	protected int nivel(NodoMV<K, V> nodoActual) {
		// TODO Auto-generated method stub
		return super.nivel(nodoActual);
	}

	@Override
	public int nivel() {
		// TODO Auto-generated method stub
		return super.nivel();
	}

	@Override
	public List<K> recorridoEnInOrden() {
		// TODO Auto-generated method stub
		return super.recorridoEnInOrden();
	}

	@Override
	public List<K> recorridoEnPreOrden() {
		// TODO Auto-generated method stub
		return super.recorridoEnPreOrden();
	}

	@Override
	public List<K> recorridoEnPostOrden() {
		// TODO Auto-generated method stub
		return super.recorridoEnPostOrden();
	}

	@Override
	public List<K> recorridoPorNiveles() {
		// TODO Auto-generated method stub
		return super.recorridoPorNiveles();
	}

	@Override
	public void printArbol() {
		// TODO Auto-generated method stub
		super.printArbol();
	}

	@Override
	public int cantidadDeHijosVacios(int nivel) {
		// TODO Auto-generated method stub
		return super.cantidadDeHijosVacios(nivel);
	}

	@Override
	public int cantidad1(int nivel) {
		// TODO Auto-generated method stub
		return super.cantidad1(nivel);
	}

	@Override
	public int cantidadDeNodosAPartirDeUnNivel(int k, int nivel) {
		// TODO Auto-generated method stub
		return super.cantidadDeNodosAPartirDeUnNivel(k, nivel);
	}



	private final int nroMaximoDeClaves ;
	 private final int nroMinimoDeClaves ;
	 private final int nroMinimoDeHijos ;
	 private final int nroMaximoDeHijos ;

	 public ArbolB() {
		 super();//setea ya el orden o lo que es lo mismo
		 // nroMaximoDeHijos en 3
		 nroMaximoDeClaves= 2;
		 nroMinimoDeClaves= 1;
		 nroMinimoDeHijos= 2;
		 nroMaximoDeHijos=3;
	 }

	 public ArbolB(int orden) throws OrdenInvalidoException{// throws ExceptionOrdenInvalido{
		 super(orden);
		 nroMaximoDeClaves = super.orden-1;
		 nroMinimoDeClaves = nroMaximoDeClaves /2;
		 nroMinimoDeHijos = nroMinimoDeClaves +1;
		 nroMaximoDeHijos = super.orden;//puede que no lo usen

	 }


	 private void insertarRaiz(int posicionDeClavePadre, NodoMV<K,V> nodoAux) {
		 K claveRaiz = nodoAux.getClave(posicionDeClavePadre);
		 V valorRaiz = nodoAux.getValor(posicionDeClavePadre);



		 //crea los dos nodos divididos
		 NodoMV<K,V> nodo1= new NodoMV<>(super.orden+1, nodoAux.getClave(0),nodoAux.getValor(0));
		 nodo1.setHijo(0, nodoAux.getHijo(0));

		 NodoMV<K,V> nodo2= new NodoMV<>(super.orden+1, nodoAux.getClave(posicionDeClavePadre+1),
				 nodoAux.getValor(posicionDeClavePadre+1));
		 nodo2.setHijo(0, nodoAux.getHijo(posicionDeClavePadre+1));

		 for (int i=1; i < posicionDeClavePadre; i++) {
			insertarClaveYValorALosNuevosNodos(nodo1, nodoAux.getClave(i), nodoAux.getValor(i),nodoAux.getHijo(i));
		 }
		 nodo1.setHijo(posicionDeClavePadre, nodoAux.getHijo(posicionDeClavePadre));

		 for (int i=posicionDeClavePadre+2; i < super.orden; i++) {
			 insertarClaveYValorALosNuevosNodos(nodo2, nodoAux.getClave(i), nodoAux.getValor(i), nodoAux.getHijo(i));
		 }
		 nodo2.setHijo(nodo2.nroDeClavesNoVacias()-1, nodoAux.getHijo(super.orden-1));
		 nodo2.setHijo(nodo2.nroDeClavesNoVacias(), nodoAux.getHijo(super.orden));

		 super.raiz= new NodoMV<>(super.orden+1, claveRaiz, valorRaiz );
		 //inserta los nuevos hijos
		 super.raiz.setHijo(0, nodo1);
		 super.raiz.setHijo(1, nodo2);
	 }



	 private void insertarClaveYValorOrdenadoConHijos(NodoMV<K,V> nodoPadre,K claveRaiz,V valorRaiz,
			 																NodoMV<K,V> nodo1, NodoMV<K,V>nodo2){
		 for (int i = nodoPadre.nroDeClavesNoVacias(); i > 0; i--) {
	            if (claveRaiz.compareTo(nodoPadre.getClave(i-1)) < 0) {
	            	nodoPadre.setClave(i, nodoPadre.getClave(i-1));
	            	nodoPadre.setValor(i, nodoPadre.getValor(i-1));
	            	nodoPadre.setHijo(i+1, nodoPadre.getHijo(i));
	                if ((i-1)==0) {
	                	nodoPadre.setClave(0 , claveRaiz);
	                	nodoPadre.setValor(0 , valorRaiz);
	                	nodoPadre.setHijo(0, nodo1);
	                	nodoPadre.setHijo(1, nodo2);
	                }

	            } else {
	            	nodoPadre.setClave(i , claveRaiz);
	            	nodoPadre.setValor(i , valorRaiz);
	            	nodoPadre.setHijo(i, nodo1);
	            	nodoPadre.setHijo(i+1, nodo2);
	                break;
	            }
	    	}
	 }
	 private void insertarClaveYValorALosNuevosNodos(NodoMV<K,V> nodoActual,K claveAInsertar,
			 V valorAsociado, NodoMV<K,V> hijoActual){
		 nodoActual.setClave(nodoActual.nroDeClavesNoVacias(), claveAInsertar);
		 nodoActual.setValor(nodoActual.nroDeClavesNoVacias(), valorAsociado);
		 nodoActual.setHijo(nodoActual.nroDeClavesNoVacias(), hijoActual);


	 }

	 private void insertarClavesYHijosAlPadre(int posicionDeClavePadre,NodoMV<K,V> nodoAux,NodoMV<K,V> nodoPadre){
		 K claveRaiz = nodoAux.getClave(posicionDeClavePadre);
		 V valorRaiz = nodoAux.getValor(posicionDeClavePadre);

		//crea los dos nodos

		 NodoMV<K,V> nodo1= new NodoMV<>(super.orden+1, nodoAux.getClave(0),nodoAux.getValor(0));
		 nodo1.setHijo(0, nodoAux.getHijo(0));

		 NodoMV<K,V> nodo2= new NodoMV<>(super.orden+1, nodoAux.getClave(posicionDeClavePadre+1),
				 nodoAux.getValor(posicionDeClavePadre+1));
		 nodo2.setHijo(0, nodoAux.getHijo(posicionDeClavePadre+1));

		 for (int i=1; i < posicionDeClavePadre; i++) {
			insertarClaveYValorALosNuevosNodos(nodo1, nodoAux.getClave(i), nodoAux.getValor(i),nodoAux.getHijo(i));
		 }
		 nodo1.setHijo(posicionDeClavePadre, nodoAux.getHijo(posicionDeClavePadre));

		 for (int i=posicionDeClavePadre+2; i < super.orden; i++) {
			 insertarClaveYValorALosNuevosNodos(nodo2, nodoAux.getClave(i), nodoAux.getValor(i), nodoAux.getHijo(i));
		 }
		 nodo2.setHijo(nodo2.nroDeClavesNoVacias()-1, nodoAux.getHijo(super.orden-1));
		 nodo2.setHijo(nodo2.nroDeClavesNoVacias(), nodoAux.getHijo(super.orden));

		//inserta los nuevos hijos

		 insertarClaveYValorOrdenadoConHijos(nodoPadre,claveRaiz,valorRaiz,nodo1,nodo2);


	 }

	 private void dividirNodo(NodoMV<K,V> nodoAux, Stack<NodoMV<K,V>> pilaDeAncestros) {

		 int posicionDeClavePadre= nroMinimoDeClaves;
		 if (pilaDeAncestros.isEmpty()) {
			 insertarRaiz(posicionDeClavePadre, nodoAux) ;
			 return;
		 }else {
			 NodoMV<K,V> nodoPadre= pilaDeAncestros.pop();
			 insertarClavesYHijosAlPadre(posicionDeClavePadre,nodoAux, nodoPadre);

			 if(nodoPadre.nroDeClavesNoVacias()>nroMaximoDeClaves) {
				 dividirNodo(nodoPadre, pilaDeAncestros);
			 }
		 }


	 }

	 @Override
		public void insertar(K claveAInsertar, V valorAsociado) {
			if (claveAInsertar== null) {
				throw new IllegalArgumentException("dato a insertar no puede ser nula");
			}


			if (this.esArbolVacio()) {
				super.raiz = new NodoMV<>(super.orden+1, claveAInsertar, valorAsociado);

			}else {

			NodoMV<K,V> nodoAux = this.raiz;
			Stack<NodoMV<K,V>> pilaDeAncestros = new Stack<>();
			do {//buscarPosicionDeClave -> lo que hace es revisar si ya existe la clave dentro de nodo
				//int posicionDeDatoEnNodo = this.buscarPosicionDeDatoEnNodo(claveAInsertar, nodoAux);
				int posicionDeDatoEnNodo = this.buscarPosicionDeClave(claveAInsertar, nodoAux);


				if (posicionDeDatoEnNodo != ArbolMV.POSICION_INVALIDA) {
					//throw new ExceptionDatoYaExiste();
					nodoAux.setValor(posicionDeDatoEnNodo, valorAsociado);
					nodoAux = NodoMV.nodoVacio();
				}else {
					if (nodoAux.esHoja()) {
						// el nodo auxiliar es hoja y la clave no esta en el nodo
						this.insertarClaveYValorOrdenado(nodoAux, claveAInsertar,valorAsociado);

						if (nodoAux.nroDeClavesNoVacias() > this.nroMaximoDeClaves) {
							dividirNodo(nodoAux, pilaDeAncestros);
						}

						nodoAux= NodoMV.nodoVacio();
					}else {
						//el nodo auxiliar no es una hoja y sabemos que la clave no esta en este nodo
						int posicionPorDondeBajar = this.buscarPosicionPorDondeBajar(claveAInsertar,nodoAux);
						pilaDeAncestros.push(nodoAux);
							nodoAux = nodoAux.getHijo(posicionPorDondeBajar);

					}
				}

			} while (!NodoMV.esNodoVacio(nodoAux));
			}

			//en  un arbol mvias siempre se inserta en un nodo hoja

		}



	 private K prestarseSiguiente(NodoMV<K, V> hijoNodoPadre) {

			if (hijoNodoPadre.nroDeClavesNoVacias() > nroMinimoDeClaves) {

				return hijoNodoPadre.getClave(0);
			}
			return (K) NodoMV.datoVacio();
		}

		private K prestarseAnterior(NodoMV<K, V> hijoNodoPadre) {
			if (hijoNodoPadre.nroDeClavesNoVacias() > nroMinimoDeClaves) {

				return hijoNodoPadre.getClave(hijoNodoPadre.nroDeClavesNoVacias() - 1);
			}
			return (K) NodoMV.datoVacio();
		}

		private void insertarDatosDelNodoAEliminar(NodoMV<K, V> nodoDelDatoAEliminar,
				NodoMV<K, V> siguienteHijoDelNodoPadre) {
			for (int i = 0; i < siguienteHijoDelNodoPadre.nroDeClavesNoVacias(); i++) {
				this.insertarClaveYValorOrdenado(nodoDelDatoAEliminar, siguienteHijoDelNodoPadre.getClave(i),
						siguienteHijoDelNodoPadre.getValor(i));
			}
		}

		private void prestarseOFusionar(NodoMV<K, V> nodoDelDatoAEliminar, Stack<NodoMV<K, V>> pilaDeAncestros,
				K datoAEliminar) {
			if (pilaDeAncestros.isEmpty()) {
				return;
			} else {

				NodoMV<K, V> nodoPadre = pilaDeAncestros.pop();
				int posicionInicialDelNodoPadre = super.buscarPosicionPorDondeBajar(datoAEliminar, nodoPadre);

				// PREGUNTA PARA PRESTARSE LA SIGUIENTE O ANTERIOR POSICION
				// verifica posicion siguiente pero antes verifica que la posicion de los hijos
				// sea correcta para hacer sus preguntas sobre
				// si va sacar la clave del siguiente o el anterior
				// if (posicionInicialDelNodoPadre < nodoPadre.nroDeClavesNoVacias()) {
				NodoMV<K, V> siguienteHijoDelNodoPadre = nodoPadre.getHijo(posicionInicialDelNodoPadre + 1);

				K claveReemplazoEnElNodoPadre = prestarseSiguiente(siguienteHijoDelNodoPadre);
				if (claveReemplazoEnElNodoPadre != null) {
					V valorAsociado = super.buscar(claveReemplazoEnElNodoPadre);
					super.insertarClaveYValorOrdenado(nodoDelDatoAEliminar,
							nodoPadre.getClave(posicionInicialDelNodoPadre),
							nodoPadre.getValor(posicionInicialDelNodoPadre));

					nodoDelDatoAEliminar.setHijo(nodoDelDatoAEliminar.nroDeClavesNoVacias(),
							siguienteHijoDelNodoPadre.getHijo(0));
					super.eliminarClaveDeNodoDePosicion(siguienteHijoDelNodoPadre, 0);
					nodoPadre.setClave(posicionInicialDelNodoPadre, claveReemplazoEnElNodoPadre);
					nodoPadre.setValor(posicionInicialDelNodoPadre, valorAsociado);
				} else {
					if (posicionInicialDelNodoPadre != 0) {
						NodoMV<K, V> anteriorHijoDelNodoPadre = nodoPadre.getHijo(posicionInicialDelNodoPadre - 1);
						claveReemplazoEnElNodoPadre = prestarseAnterior(anteriorHijoDelNodoPadre);
						if (claveReemplazoEnElNodoPadre != null) {
							V valorAsociado = super.buscar(claveReemplazoEnElNodoPadre);
							super.insertarClaveYValorOrdenado(nodoDelDatoAEliminar,
									nodoPadre.getClave(posicionInicialDelNodoPadre - 1),
									nodoPadre.getValor(posicionInicialDelNodoPadre - 1));
							super.eliminarClaveDeNodoDePosicion(anteriorHijoDelNodoPadre,
									anteriorHijoDelNodoPadre.nroDeClavesNoVacias() - 1);
							nodoPadre.setClave(posicionInicialDelNodoPadre - 1, claveReemplazoEnElNodoPadre);
							nodoPadre.setValor(posicionInicialDelNodoPadre - 1, valorAsociado);
						}
					} else {

						// desde esta parte se hace el fucionar
						super.insertarClaveYValorOrdenado(nodoDelDatoAEliminar,
								nodoPadre.getClave(posicionInicialDelNodoPadre),
								nodoPadre.getValor(posicionInicialDelNodoPadre));

						this.insertarDatosDelNodoAEliminar(nodoDelDatoAEliminar, siguienteHijoDelNodoPadre);
						nodoPadre.setHijo(posicionInicialDelNodoPadre + 1, NodoMV.nodoVacio());
						nodoPadre.setClave(posicionInicialDelNodoPadre, (K) NodoMV.datoVacio());
						nodoPadre.setValor(posicionInicialDelNodoPadre, (V) NodoMV.datoVacio());
					}
				}

				if (nodoPadre.nroDeClavesNoVacias() < nroMinimoDeClaves) {
					prestarseOFusionar(nodoPadre, pilaDeAncestros, datoAEliminar);
				}

			}
		}

		private NodoMV<K, V> buscarNodoDelPredecesor(NodoMV<K, V> nodoHijo,
				Stack<NodoMV<K, V>> pilaDeAncestros) {
			while (!nodoHijo.esHijoVacio(nodoHijo.nroDeClavesNoVacias())) {
				nodoHijo = nodoHijo.getHijo(nodoHijo.nroDeClavesNoVacias());
			}
			return nodoHijo;
		}

		@Override
		public V eliminar(K datoAEliminar) {
			if (datoAEliminar == null) {
				throw new IllegalArgumentException("dato a eliminar no puede ser nulo");
			}

			NodoMV<K, V> nodoAux = this.raiz;
			V valorRespuesta = super.buscar(datoAEliminar);
			NodoMV<K, V> nodoDelDatoAEliminar = NodoMV.nodoVacio();
			Stack<NodoMV<K, V>> pilaDeAncestros = new Stack<>();
			int posicionDeDatoAEliminar = ArbolMV.POSICION_INVALIDA;
			do {// buscarPosicionDeClave -> lo que hace es revisar si ya existe la clave dentro
				// de nodo
				// posicionDeDatoEnNodo = this.buscarPosicionDeDatoEnNodo(datoAEliminar,
				// nodoAux);
				posicionDeDatoAEliminar = super.buscarPosicionDeClave(datoAEliminar, nodoAux);

				if (posicionDeDatoAEliminar != ArbolMV.POSICION_INVALIDA) {
					// throw new ExceptionDatoYaExiste();
					/*
					 * nodoAux.setValor(posicionDeClaveAInsertar, valorAsociado); nodoAux =
					 * NodoMV.nodoVacio();
					 */
					nodoDelDatoAEliminar = nodoAux;
					nodoAux = NodoMV.nodoVacio();
				} else {
					// el nodo auxiliar no es una hoja y sabemos que la clave no esta en este nodo
					int posicionPorDondeBajar = this.buscarPosicionPorDondeBajar(datoAEliminar, nodoAux);
					pilaDeAncestros.push(nodoAux);
					nodoAux = nodoAux.getHijo(posicionPorDondeBajar);
				}

			} while (!NodoMV.esNodoVacio(nodoAux));// fin del if

			if (NodoMV.esNodoVacio(nodoDelDatoAEliminar)) {
				// throw new ExceptionDatoNoExiste();
				throw new IllegalArgumentException("dato a eliminar no existe");
			}

			if (nodoDelDatoAEliminar.esHoja()) {
				// super.eliminarElDatoEnElNodo(nodoDelDatoAEliminar, posicionDeDatoAEliminar);
				// notz revisar si este metodo me funciona para ver si hago otro que funcione
				// con las especificaciones que necesito
				super.eliminarClaveDeNodoDePosicion(nodoDelDatoAEliminar, posicionDeDatoAEliminar);
				if (nodoDelDatoAEliminar.nroDeClavesNoVacias() < this.nroMinimoDeClaves) {
					// preguntar al ingeriero como conseguir la pos siguiente y anterior cuando solo
					// mandas nodoDelDatoAEliminar y la
					// pila de ancestros
					// prestarseOFusionar(nodoDelDatoAEliminar, pilaDeAncestros);
					prestarseOFusionar(nodoDelDatoAEliminar, pilaDeAncestros, datoAEliminar);
				}

				// hasta aqui ya esta resuelto

			} else {// el dato a eliminar no esta en una hoja,
					// entonces buscamos predecesor inOrden
				pilaDeAncestros.push(nodoDelDatoAEliminar);
				NodoMV<K, V> nodoDelPredecesor = buscarNodoDelPredecesor(
						nodoDelDatoAEliminar.getHijo(posicionDeDatoAEliminar), pilaDeAncestros);
				int posicionDelReemplazo = nodoDelPredecesor.nroDeClavesNoVacias() - 1;
				K claveDeReemplazo = nodoDelPredecesor.getClave(posicionDelReemplazo);
				V valorDeReemplazo = nodoDelPredecesor.getValor(posicionDelReemplazo);
				nodoDelPredecesor.setClave(posicionDelReemplazo, (K) NodoMV.datoVacio());
				nodoDelPredecesor.setValor(posicionDelReemplazo, (V) NodoMV.datoVacio());
				nodoDelDatoAEliminar.setClave(posicionDeDatoAEliminar, claveDeReemplazo);
				nodoDelDatoAEliminar.setValor(posicionDeDatoAEliminar, valorDeReemplazo);

				if (nodoDelPredecesor.nroDeClavesNoVacias() < this.nroMinimoDeClaves) {
					prestarseOFusionar(nodoDelPredecesor, pilaDeAncestros, datoAEliminar);
				}
			}

			// verificar que va devolver revisa bien el codigo hasta mañana
			return valorRespuesta;

		}

}

