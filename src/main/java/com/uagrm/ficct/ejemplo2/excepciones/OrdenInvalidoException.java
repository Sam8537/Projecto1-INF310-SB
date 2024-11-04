package com.uagrm.ficct.ejemplo2.excepciones;

public class OrdenInvalidoException extends Exception{
	public OrdenInvalidoException() {
		super("Orden del arbol debe ser al menos de 3");
	}

	public OrdenInvalidoException(String message) {
		super(message);
	}

}
