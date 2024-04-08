package es.studium.MiJuego;

public class Jugador {
	
	private Integer id;
	private String nombre;
	private Integer victorias;
	private String contrase�a;
	
	public Jugador(Integer id, String nombre, Integer victorias, String contrase�a) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.victorias = victorias;
		this.contrase�a = contrase�a;
	}

	public Jugador() {
		super();
		this.id = null;
		this.nombre = "";
		this.victorias = null;
		this.contrase�a = "";
	}			
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getVictorias() {
		return victorias;
	}

	public void setVictorias(Integer victorias) {
		this.victorias = victorias;
	}

	public String getContrase�a() {
		return contrase�a;
	}

	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
	}
}

