package es.studium.MiJuego;

public class Jugador {
	
	private Integer id;
	private String nombre;
	private Integer victorias;
	private String contraseña;
	
	public Jugador(Integer id, String nombre, Integer victorias, String contraseña) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.victorias = victorias;
		this.contraseña = contraseña;
	}

	public Jugador() {
		super();
		this.id = null;
		this.nombre = "";
		this.victorias = null;
		this.contraseña = "";
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

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
}

