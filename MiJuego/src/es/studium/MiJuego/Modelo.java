package es.studium.MiJuego;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

public class Modelo {

	public Modelo() {}
	
	// Método para calcular los chinos que tendrá en la mano el rival
	public Integer calcularChinosManoRival() {

		// Instanciamos la clase Random
		Random random = new Random();
		
		// Inicializamos Integer que contendrá la jugada
		Integer jugadaCPU = null;
		
		// Generamos el número de chinos en la mano
		jugadaCPU = random.nextInt(4);
		
		return jugadaCPU;	
	}
	
	// Método para calcular la apuesta de la CPU
	public Integer calcularApuestaRival(Integer apuestaJugador, Integer manoCPU) {
		
		// Inicializamos la apuesta a -1
		Integer apuestaCPU = -1;
		// Instanciamos la clase Random
		Random random = new Random();
		
		// Mientras que la apuesta de la CPU coincida con la del jugador, o sea, -1, repetimos el bucle
		while (apuestaJugador == apuestaCPU || apuestaCPU == -1) {
				
			   // Creamos un número aleatorio del 0 al 6, teniendo en cuenta la jugada de la CPU 
			   // De esta forma no apostará más cantidad del total posible según su mano
			apuestaCPU = random.nextInt(manoCPU+4);
		}	
		return apuestaCPU;
	}
		
	// Método para calcular el ganador de la ronda
	public Integer calcularGanador(Integer apuestaJugador, Integer apuestaCPU, Integer totalChinos) {
		
		// Inicializamos variable que indicará si hay ganador y quién ha sido
		// -1 = sin ganador
		// 0 = gana CPU
		// 1 = gana jugador
		Integer ganador = null;
		
		if (totalChinos == apuestaJugador) {
			ganador = 1;
		}
		else if (totalChinos == apuestaCPU) {
			ganador = 0;
		}
		else {
			ganador = -1;
		}
		
		return ganador;
	}
	
	// Conexión BD
	public Connection conectar() {

		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/JuegoLosChinos";
		String login = "juegoLosChinos"; // Usuario MySQl
		String password = "Studium2022;"; // Su clave correspondiente
		Connection connection = null;

		try {
			// Cargar los controladores para el acceso a la BD
			Class.forName(driver);
			// Establecer la conexión con la BD Empresa
			connection = DriverManager.getConnection(url, login, password);
		} 
		catch (ClassNotFoundException cnfe) {} 
		catch (SQLException sqle) {}
		return connection;
	}

	// Método para Consultar jugadores
	public ResultSet consultarJugadores() {
		ResultSet rs = null;

		Connection connection = null;
		try {
			connection = conectar();
			// Crear una sentencia
			Statement statement = connection.createStatement();
			// Crear un objeto ResultSet para guardar lo obtenido
			// Y ejecutar la sentencia SQL
			rs = statement.executeQuery("SELECT * FROM jugadores");
		} catch (SQLException sqle) {}
		return (rs);
	}

	// Método para Consultar Ranking
	public ArrayList<Jugador> consultarRanking() {
		ResultSet rs = null;
		ArrayList<Jugador> jugadoresRanking = new ArrayList<Jugador>();

		Connection connection = null;
		try {
			connection = conectar();
			// Crear una sentencia
			Statement statement = connection.createStatement();
			// Crear un objeto ResultSet para guardar lo obtenido
			// Y ejecutar la sentencia SQL
			rs = statement.executeQuery("SELECT nombreJugador AS 'nombre', victoriasJugador AS 'victorias' FROM jugadores  ORDER BY victorias DESC LIMIT 10");
			while(rs.next()) // Si hay, al menos uno
			{
				Jugador jugador = new Jugador();
				jugador.setNombre(rs.getString("nombre"));
				jugador.setVictorias(rs.getInt("victorias"));
				jugadoresRanking.add(jugador);
			
			}
		} catch (SQLException sqle) {}
		return (jugadoresRanking);
	}

	// Método para buscar jugador por nombre y contraseña
	public Jugador comprobarJugador(String nombre, String contraseña)  {
		
		// Creamos el objeto jugador a devolver
		Jugador jugador = new Jugador();
		
		// Indicamos que su id por defecto es -1
		jugador.setId(-1);
				
		ResultSet rs = null;		
		Connection connection = null;
		try {
			// Creamos la conexion a BBDD
			connection = conectar();
			// Crear una sentencia
			Statement statement = connection.createStatement();
			// Ejecutar el INSERT
			rs = statement.executeQuery("SELECT * FROM jugadores WHERE nombreJugador = '"+nombre+"' AND contraseñaJugador = '"+contraseña+"';");
			while(rs.next()) // Si hemos encontrado un jugador
			{
				jugador.setId(rs.getInt("idJugador"));
				jugador.setNombre(rs.getString("nombreJugador"));
				jugador.setVictorias(rs.getInt("victoriasJugador"));
			}
		} 
		catch (SQLException sqle) {}
		
		desconectar(connection);
		
		// Devolvemos el jugador. Si hemos encontrado datos, vendrá con su id, nombre y victorias, en caso contrario, tendra id -1
		return (jugador);		
	}
	
	// Método para buscar jugador por nombre
	public Jugador comprobarJugador(String nombre)  {
		
		// Creamos el objeto jugador a devolver
		Jugador jugador = new Jugador();
		
		// Indicamos que su id por defecto es -1
		jugador.setId(-1);
				
		ResultSet rs = null;		
		Connection connection = null;
		
		try {
			// Creamos la conexion a BBDD
			connection = conectar();
			// Crear una sentencia
			Statement statement = connection.createStatement();
			// Ejecutar el INSERT
			rs = statement.executeQuery("SELECT * FROM jugadores WHERE nombreJugador = '"+nombre+"';");
			while(rs.next()) // Si hemos encontrado un jugador
			{
				jugador.setId(rs.getInt("idJugador"));
				jugador.setNombre(rs.getString("nombreJugador"));
				jugador.setVictorias(rs.getInt("victoriasJugador"));
			}
		} 
		catch (SQLException sqle) {}
		
		desconectar(connection);
		
		// Devolvemos el jugador. Si hemos encontrado datos, vendrá con su id, nombre y victorias, en caso contrario, tendrá id -1
		return (jugador);		
	}
	
	// Método para crear jugador
	public int crearJugador(Jugador jugador) {
		int resultado = 0;

		Connection connection = null;
		try {
			// Creamos la conexión a BBDD
			connection = conectar();
			// Crear una sentencia
			Statement statement = connection.createStatement();
			// Ejecutar el INSERT
			statement.executeUpdate(
					"INSERT INTO jugadores VALUES(null, " + "'" + jugador.getNombre() + "', '"+jugador.getVictorias()+"', '" + jugador.getContraseña() + "');");

		} catch (SQLException sqle) {
			resultado = -1; // Error
		}

		desconectar(connection);
		return (resultado);
	}	
	
	// Método para actualizar en BBDD las victorias del jugador
	public int actualizarVictorias(Integer idJugador, Integer victoriasJugador){
		
		int resultado = 0;

		// Devolvemos un 0 --> Modificación con éxito
		// Devolvemos un -1 --> Modificación error
		
		Connection connection = null;
		try {
			// Creamos la conexion a BBDD
			connection = conectar();
			// Crear una sentencia
			Statement statement = connection.createStatement();
			// Ejecutar el UPDATE
			statement.executeUpdate("UPDATE jugadores SET victoriasJugador = '"+victoriasJugador+"' WHERE idJugador = " +idJugador);
		} 
		catch (SQLException sqle)
		{
			resultado = -1; // Error
		}
		
		desconectar(connection);
		return (resultado);
	}	
	
	// Desconectar a la BD
	public void desconectar(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {}
	}
}
