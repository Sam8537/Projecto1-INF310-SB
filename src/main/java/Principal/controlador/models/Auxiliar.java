package Principal.controlador.models;

public class Auxiliar {

	private String id;
	private String nombre;

	 public Auxiliar() {
	 }
	public Auxiliar( String id,String nombre) {

		this.nombre=nombre;
		this.id= id;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id=id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre=nombre;
	}



}