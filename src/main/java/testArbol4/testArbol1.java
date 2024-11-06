package testArbol4;

import com.uagrm.ficct.ejemplo2.ArbolB;
import com.uagrm.ficct.ejemplo2.excepciones.OrdenInvalidoException;



public class testArbol1 {

	public static void main(String[] arg) throws OrdenInvalidoException {
		// ArbolBinarioDeBusqueda<Integer, String> arbol= new
		// ArbolBinarioDeBusqueda<>();
		iArbol<Integer, String> arbol;

		//arbol = new ArbolBinario<>();
		//arbol = new ArbolMV<>(4);
		arbol = new ArbolB<>(4);
		// arbol= new ArbolAVL<>();
		// arbol = new ArbolMViasBusqueda<>(4);

		arbol.insertar(40, "henry");
		arbol.insertar(36, "diego");


	/*	arbol.insertar("Arroz", "Precio : 15bs -> Descripcion : abarrotes");
		arbol.insertar("Coca Cola", "Precio : 20bs -> Descripcion : bebidas");
		arbol.insertar("Harina", "Precio : 16bs -> Descripcion : abarrotes");
		arbol.insertar("Pan Molido", "Precio : 8bs -> Descripcion : para hacer milaneza");
		arbol.insertar("agua santa", "Precio : 10bs -> Descripcion : bebidas");*/



		arbol.insertar(69, "sandra");
		arbol.insertar(50, "kasandra");

		arbol.insertar(34, "emily");
		arbol.insertar(38, "maggui");
		arbol.insertar(45, "leo");
		arbol.insertar(55, "santi");
		arbol.insertar(53, "harry");
		System.out.println("recorrido inorden : " + arbol.recorridoEnInOrden());
		/*arbol.insertar(48, "carlos");

		arbol.insertar(42, "piter");
		arbol.insertar(100, "esthefania");
		arbol.insertar(96, "belen");
		arbol.insertar(76, "fabiana");
		arbol.insertar(78, "alizon");*/

//	arbol.printArbol();
//	System.out.println("nivel: " + arbol.nivel());
	/*	System.out.println("recorrido inorden : " + arbol.recorridoEnInOrden());
		System.out.println("tamaño : " + arbol.size());
		System.out.println("altura : " + arbol.altura());*/

	}
}