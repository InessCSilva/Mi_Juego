package es.studium.MiJuego;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.ArrayList;

public class Controlador implements WindowListener, ActionListener
{
	VistaPrincipal vistaPrincipal;
	Modelo modelo;
	VistaRanking vistaRanking = new VistaRanking(); 
	VistaNuevaPartida vistaNuevaPartida = new VistaNuevaPartida();
	VistaSeleccionUsuario vistaSeleccionUsuario = new VistaSeleccionUsuario();
	
	// Valores donde almacenaremos los datos de la partida y el jugador
	Integer idJugador = null;
	Integer victoriasJugador = null;
	Integer manoJugador = null;
	Integer manoCPU = null;
	Integer apuestaJugador = null;
	Integer apuestaCPU = null;
	Integer rondasGanadasJugador = 0;
	Integer rondasGanadasCPU = 0;
	
	public Controlador(Modelo m, VistaPrincipal v) {
	
		this.vistaPrincipal = v;
		this.modelo = m;
		vistaPrincipal = v;
		vistaPrincipal.addWindowListener(this);
		vistaRanking.addWindowListener(this);
		vistaSeleccionUsuario.addWindowListener(this);
		vistaSeleccionUsuario.dlgMensaje.addWindowListener(this);
		vistaNuevaPartida.addWindowListener(this);
		vistaNuevaPartida.dlgJugada.addWindowListener(this);
		vistaNuevaPartida.dlgResultadoRonda.addWindowListener(this);
		vistaNuevaPartida.dlgMensajeAtras.addWindowListener(this);
		vistaNuevaPartida.dlgMensajeReiniciar.addWindowListener(this);
		
		
		// Botones Vista Principal
		// Da función al botón Nueva Partida
		vistaPrincipal.btnNuevaPartida.addActionListener(this);	
		// Da función al botón Ranking
		vistaPrincipal.btnRanking.addActionListener(this);		
		// Da función al botón atrás de la vista del ranking
		vistaRanking.btnAtras.addActionListener(this);			
		// Da función al botón Ayuda
		vistaPrincipal.btnAyuda.addActionListener(this);		
		// Da función al botón Salir
		vistaPrincipal.btnSalir.addActionListener(this);
		
		// Botones vista selección usuario
		// Botón para logear usuario existente
		vistaSeleccionUsuario.btnLogin.addActionListener(this);
		// Botón para crear usuario
		vistaSeleccionUsuario.btnNuevoJugador.addActionListener(this);
		// Botón para volver a la vista principal
		vistaSeleccionUsuario.btnAtras.addActionListener(this);
		// Botón para el diálogo de alerta
		vistaSeleccionUsuario.btnContinuar.addActionListener(this);
		
		// Botones vista nueva partida	
		// Botón para abrir el diálogo donde se indicará la jugada del usuario
		vistaNuevaPartida.btnJugar.addActionListener(this);
		// Botón para aceptar la jugada del diálogo
		vistaNuevaPartida.btnAceptarJugada.addActionListener(this);
		// Botón para cerrar la alerta de datos introducidos inválidos
		vistaNuevaPartida.btnCerrarMensajeError.addActionListener(this);
		// Botón para finalizar la ronda y ver las manos de los jugadores
		vistaNuevaPartida.btnVerResultadoRonda.addActionListener(this);		
		// Botón para pasar a la siguiente ronda del diálogo de finalizar ronda
		vistaNuevaPartida.btnSiguienteRonda.addActionListener(this);	
		// Botón para pasar a la siguiente partida del diálogo de finalizar ronda
		vistaNuevaPartida.btnSiguientePartida.addActionListener(this);			
		// Botón para retroceder al menú principal
		vistaNuevaPartida.btnAtras.addActionListener(this);
		// Botones si/no del dialogo de retroceder al menú
		vistaNuevaPartida.btnContinuarAtras.addActionListener(this);
		vistaNuevaPartida.btnCancelarAtras.addActionListener(this);		
		// Botón para reiniciar partida
		vistaNuevaPartida.btnReiniciar.addActionListener(this);
		// Botones si/no del diálogo de reiniciar partida
		vistaNuevaPartida.btnContinuarReiniciar.addActionListener(this);
		vistaNuevaPartida.btnCancelarReiniciar.addActionListener(this);	
	}	
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		Object botonPulsado = e.getSource();
		
		// Control botones Vista Principal
		// Botón mostrar Vista Selección Usuario desde Vista Principal
		if(botonPulsado.equals(vistaPrincipal.btnNuevaPartida)) 
		{
			vistaSeleccionUsuario.MostrarVistaSeleccionUsuario();
			vistaPrincipal.OcultarPrincipal();
			vistaSeleccionUsuario.limpiar();
		}	
		
		// Botón mostrar Vista Ranking desde Vista Principal
		else if(botonPulsado.equals(vistaPrincipal.btnRanking)) 
		{
			// Inicializamos el array de jugadores
			ArrayList<Jugador> jugadores = modelo.consultarRanking();
			// Creamos un Integer que almacene la posición en el Ranking del jugador
			Integer posicion = 1;
			// Recorremos con un foreach cada jugador del array de jugadores
			for (Jugador jugador : jugadores) {
				// Por cada jugador recuperamos su nombre y victorias y creamos la cadena a añadir al ranking
				String nuevaFila = "#"+posicion+"\t\t"+jugador.getNombre()+"\t\t"+jugador.getVictorias()+"\n";
				// Añadimos la cadena al ranking
				vistaRanking.taRanking.append(nuevaFila);
				// Sumamos uno a posicion para representar la posición del siguiente jugador
				posicion++;
			}
			vistaRanking.MostrarRanking();
			vistaPrincipal.OcultarPrincipal();
		}
		
		// Botón Salir de Vista Principal
		else if(botonPulsado.equals(vistaPrincipal.btnSalir))
		{
			System.exit(0);
		}
		
		// Botón Cómo se juega de la Vista Principal
		else if(botonPulsado.equals(vistaPrincipal.btnAyuda))
		{
			try {
				Runtime.getRuntime().exec("hh.exe Ayuda.chm");
			} 
			catch (IOException ea) {
				ea.printStackTrace();
			}
		}
		
		// Control botón Vista Ranking
		// Botón volver a Vista Principal desde Vista Ranking
		else if(botonPulsado.equals(vistaRanking.btnAtras)) 
		{
			vistaRanking.OcultarRanking();
			vistaPrincipal.MostrarPrincipal();
		}
		
		// Control botones Vista Selección Usuario
		// Botón para seleccionar usuario ya existente
		else if(botonPulsado.equals(vistaSeleccionUsuario.btnLogin)) {
			
			String nombre = vistaSeleccionUsuario.txtUsuario.getText();
			String contraseña = vistaSeleccionUsuario.txtContraseña.getText();
			
			// Comprobamos que se han rellenado los campos
			if (nombre.isEmpty() || contraseña.isEmpty()) {
				vistaSeleccionUsuario.dlgMensaje.setTitle("Error");
				vistaSeleccionUsuario.lblMensaje.setText("Debe introducir usuario y contraseña.");
				vistaSeleccionUsuario.dlgMensaje.setVisible(true);		
			}
			
			// Si se han rellenado procedemos con el login
			else {
				
				// Comprobamos si en BBDD existe dicho usuario
				Jugador jugador = modelo.comprobarJugador(nombre, contraseña);
				
				// Si existe (tendrá un id diferente a -1) iniciamos la partida con dicho jugador y mostrarmos un mensaje de bienvenida.
				if (jugador.getId() != -1) {
						
						vistaSeleccionUsuario.dlgMensaje.setTitle("Registro correcto");
						vistaSeleccionUsuario.lblMensaje.setText("Bienvenid@ " + nombre+".");
						vistaSeleccionUsuario.dlgMensaje.setVisible(true);
						
						vistaSeleccionUsuario.OcultarVistaSeleccionUsuario();
						
						// Reiniciamos todos los valores de las etiquetas del tablero y los valores almacenados
						// Lo hacemos antes de abrir la vista de partida, por si se había jugado una partida previamente
						reiniciarValoresYTablero();
						
						vistaNuevaPartida.MostrarVistaNuevaPartida();
						
						// Almacenamos el identificador y las victorias del jugador para usarlas posteriormente
						idJugador = jugador.getId();
						victoriasJugador = jugador.getVictorias();
				}
				
				// Si no existe (tendrá -1 como id)  mostramos un mensaje indicando credenciales incorrectos y borramos la contraseña
				else if (jugador.getId() == -1) {
					
					vistaSeleccionUsuario.lblMensaje.setText("Credenciales incorrectos");
					vistaSeleccionUsuario.dlgMensaje.setVisible(true);
					vistaSeleccionUsuario.txtContraseña.setText("");	
					vistaSeleccionUsuario.txtUsuario.requestFocus();
				}
			}	
		}
		
		// Botón para crear un nuevo usuario
		else if(botonPulsado.equals(vistaSeleccionUsuario.btnNuevoJugador)) {
			
			String nombre = vistaSeleccionUsuario.txtUsuario.getText();
			String contraseña = vistaSeleccionUsuario.txtContraseña.getText();
			
			// Comprobamos que se han rellenado ambos campos
			if (nombre.isEmpty() || contraseña.isEmpty()) {
				
				vistaSeleccionUsuario.dlgMensaje.setTitle("Error");
				vistaSeleccionUsuario.lblMensaje.setText("Debe introducir usuario y contraseña.");
				vistaSeleccionUsuario.dlgMensaje.setVisible(true);
				vistaSeleccionUsuario.txtUsuario.requestFocus();
			}
			
			// Si se han rellenado procedemos con el alta de jugador
			else {
				
				// Comprobamos si en BBDD existe dicho usuario
				Jugador jugador = modelo.comprobarJugador(nombre);
						
				// Si se devuelve -1 en el id, no existe el usuario y procedemos a crearlo
				if (jugador.getId() == -1) {
						
						jugador.setNombre(nombre);
						jugador.setContraseña(contraseña);
						jugador.setVictorias(0);
						
						modelo.crearJugador(jugador);
						
						vistaSeleccionUsuario.dlgMensaje.setTitle("Registro correcto");
						vistaSeleccionUsuario.lblMensaje.setText("Se ha creado su usuario, bienvenid@ "+ jugador.getNombre()+".");
						vistaSeleccionUsuario.dlgMensaje.setVisible(true);
					
						vistaSeleccionUsuario.OcultarVistaSeleccionUsuario();
						
						// Reiniciamos todos los valores de las etiquetas del tablero y los valores almacenados
						// Lo hacemos antes de abrir la vista de partida, por si se habia jugado una partida previamente
						reiniciarValoresYTablero();
						
						vistaNuevaPartida.MostrarVistaNuevaPartida();
					
						// Almacenamos el identificador y las victorias del jugador para usarlas posteriormente
						idJugador = jugador.getId();
						victoriasJugador = jugador.getVictorias();
				}
				
				// Si se devuelve -1 en el id, es que ya existe un usuario con dicho nombre
				else if (jugador.getId() != -1) {

					vistaSeleccionUsuario.dlgMensaje.setTitle("Error");
					vistaSeleccionUsuario.lblMensaje.setText("Ya existe un jugador con este nombre.");
					vistaSeleccionUsuario.dlgMensaje.setVisible(true);	
				}
			}
		}
		
		// Botón volver a Vista Principal desde Vista Selección Usuario
		else if(botonPulsado.equals(vistaSeleccionUsuario.btnAtras)) 
		{
			vistaSeleccionUsuario.OcultarVistaSeleccionUsuario();
			vistaPrincipal.MostrarPrincipal();
		}
		
		// Botón continuar del diálogo de alerta de la Vista Selección Usuario
		else if (botonPulsado.equals(vistaSeleccionUsuario.btnContinuar)){
			vistaSeleccionUsuario.dlgMensaje.setVisible(false);
		}
		
		// Botones de la Vista de Nueva Partida
		// Botón de siguiente ronda
		else if (botonPulsado.equals(vistaNuevaPartida.btnJugar)) {
			vistaNuevaPartida.dlgJugada.setVisible(true);
			// Reiniciamos los campos por si habian quedado rellenos de una ronda anterior y se cerró el diálogo
			vistaNuevaPartida.txtChinosMano.setText("");
			vistaNuevaPartida.txtChinosApostadosJugador.setText("");
		}
		
		// Botón para aceptar la jugada del diálogo de jugada
		else if (botonPulsado.equals(vistaNuevaPartida.btnAceptarJugada)){
			
			// Comprobamos los datos introducidos
			try { 
				manoJugador = Integer.parseInt(vistaNuevaPartida.txtChinosMano.getText());
				apuestaJugador = Integer.parseInt(vistaNuevaPartida.txtChinosApostadosJugador.getText());
				
				// Comprobamos que los números introducidos estan entre el margen válido del juego
				// Comprobamos que la apuesta está entre 0 y 6
				if (apuestaJugador <= 6 && apuestaJugador > 0 ) {
					// Comprobamos que en la mano hay entre 0 y 3 chinos
					if (manoJugador <= 3 && manoJugador >= 0) {
						
						// Si se cumple todo, modificamos los textos del tablero y cambiamos el botón
						vistaNuevaPartida.lblChinosManoJugador.setText("Chinos en tu mano: " + manoJugador);
						vistaNuevaPartida.lblChinosApostadoJugador.setText("Tu apuesta: " + apuestaJugador);
						vistaNuevaPartida.dlgJugada.setVisible(false);
						vistaNuevaPartida.btnJugar.setEnabled(false);
						vistaNuevaPartida.btnVerResultadoRonda.setEnabled(true);
						
						// También calculamos la mano del rival y la almacenamos
						manoCPU = modelo.calcularChinosManoRival();
						// Calculamos su apuesta y la almacenamos
						apuestaCPU = modelo.calcularApuestaRival(apuestaJugador, manoCPU);
						// Y mostramos la apuesta en el tablero
						vistaNuevaPartida.lblChinosApostadosRival.setText("Su apuesta: " + apuestaCPU);											
					}
					// Si el número de chinos en la mano es incorrecto, mostramos un error
					else {
						vistaNuevaPartida.lblMensajeError.setText("Solo puede jugar entre 0 y 3 chinos.");
						vistaNuevaPartida.dlgErrorDatos.setVisible(true);
					}
				}
				
				// Si el número de chinos apostados es incorrecto, mostramos un error
				else {
					vistaNuevaPartida.lblMensajeError.setText("Solo puede apostar que hay entre 0 y 6 chinos.");
					vistaNuevaPartida.dlgErrorDatos.setVisible(true);
				}
			
			// Controlamos en el try/catch que se hayan introducido números en ambos campos, en caso contrario no podrían pasarse de String a Integer
			} catch (NumberFormatException ne) {
				vistaNuevaPartida.lblMensajeError.setText("Debe introducir un número en ambos campos.");
				vistaNuevaPartida.dlgErrorDatos.setVisible(true);
			}
		}
		
		// Botones de la alerta de datos erróneos
		else if (botonPulsado.equals(vistaNuevaPartida.btnCerrarMensajeError)) {
			
			vistaNuevaPartida.dlgErrorDatos.setVisible(false);
		}
		
		// Botón de comprobar resultado de ronda
		else if (botonPulsado.equals(vistaNuevaPartida.btnVerResultadoRonda)) {
			
			
			// Calculamos los chinos en ambas manos
			Integer totalChinos = manoJugador+manoCPU;
			
			// Calculamos el resultado de la ronda
			Integer ganador = modelo.calcularGanador(apuestaJugador, apuestaCPU, totalChinos);
			
			// Si no gana nadie, ganador será -1
			if (ganador == -1) {
				vistaNuevaPartida.lblResultadoRonda.setText("No ha ganado nadie, se pasa a la siguiente ronda.");
			}
			// Si ha ganado la CPU, el valor ganador estará a 
			else if (ganador == 0) {
				
				// Incrementamos el número de rondas ganadas por la CPU
				rondasGanadasCPU++;
				
				// Comprobamos el número de rondas ganadas por la CPU
				// Si ha ganado menos de 3 rondas, actualizamos el contador
				if (rondasGanadasCPU < 3) {
					
					// Mostramos las rondas ganadas	por la CPU en el resultado
					vistaNuevaPartida.lblResultadoRonda.setText("El rival gana la ronda, lleva "+rondasGanadasCPU+" ganadas.");
					// Actualizamos el marcador de rondas del tablero
					vistaNuevaPartida.lblRondasPerdidas.setText("Rondas perdidas: "+rondasGanadasCPU);
					// Mostramos el botón de siguiente ronda
					vistaNuevaPartida.btnSiguienteRonda.setEnabled(true);
					// Ocultamos el botón de siguiente partida
					vistaNuevaPartida.btnSiguientePartida.setEnabled(false);			
				}
				// Si ha ganado 3 rondas, habrá ganado la partida, lo indicamos y mostramos el botón de siguiente partida
				else {
					
					// Mostramos las rondas ganadas	por la CPU en el resultado
					vistaNuevaPartida.lblResultadoRonda.setText("El rival gana la partida, lleva "+rondasGanadasCPU+" ganadas.");
					// Ocultamos el botón de siguiente ronda
					vistaNuevaPartida.btnSiguienteRonda.setEnabled(false);
					// Mostramos el botón de siguiente partida
					vistaNuevaPartida.btnSiguientePartida.setEnabled(true);							
				}
			}
			// Si ha ganado el jugador, el valor ganador estará a 1
			else if (ganador == 1) {
				
				// Incrementamos el número de rondas ganadas por el jugador
				rondasGanadasJugador++;
				
				// Comprobamos el número de rondas ganadas por el jugador
				// Si ha ganado menos de 3 rondas, actualizamos el contador
				if (rondasGanadasJugador < 3) {	
					// Mostramos las rondas ganadas	por la CPU en el resultado
					vistaNuevaPartida.lblResultadoRonda.setText("Ganas la ronda, llevas "+rondasGanadasJugador+" ganadas.");
					// Actualizamos el marcador de rondas del tablero
					vistaNuevaPartida.lblRondasGanadas.setText("Rondas ganadas: "+rondasGanadasJugador);
					// Mostramos el boton de siguiente ronda
					vistaNuevaPartida.btnSiguienteRonda.setEnabled(true);
					// Ocultamos el boton de siguiente partida
					vistaNuevaPartida.btnSiguientePartida.setEnabled(false);				
				}
				// Si ha ganado 3 rondas, habrá ganado la partida, lo indicamos y mostramos el botón de siguiente partida
				else {
					
					// Mostramos las rondas ganadas	por la CPU en el resultado
					vistaNuevaPartida.lblResultadoRonda.setText("¡Felicidades, has ganado la partida!");
					// Ocultamos el botón de siguiente ronda
					vistaNuevaPartida.btnSiguienteRonda.setEnabled(false);
					// Mostramos el botón de siguiente partida
					vistaNuevaPartida.btnSiguientePartida.setEnabled(true);
					// Actualizamos el número de victorias en BBDD
					victoriasJugador++;
					modelo.actualizarVictorias(idJugador, victoriasJugador);							
				}
			}
			
			// Rellenamos los demás textos del diálogo
			vistaNuevaPartida.lblResultadoRondaApuestaJugador.setText("Tu apuesta: "+apuestaJugador);
			vistaNuevaPartida.lblResultadoRondaApuestaCPU.setText("Su apuesta: "+apuestaCPU);
			vistaNuevaPartida.lblResultadoRondaManoJugador.setText("Tu mano: "+manoJugador);
			vistaNuevaPartida.lblResultadoRondaManoCPU.setText("Su mano: "+manoCPU);
			vistaNuevaPartida.lblResultadoRondaSumaChinos.setText("Total de chinos en ambas manos: " + totalChinos.toString());
			
			// Lo mostramos
			vistaNuevaPartida.dlgResultadoRonda.setVisible(true);
		}
		
		// Botón de Siguiente ronda del diálogo de Siguiente Ronda
		else if (botonPulsado.equals(vistaNuevaPartida.btnSiguienteRonda)) {
			
			// Reiniciamos botones del tablero
			// Activamos botón Jugar de la Vista Nueva Partida
			vistaNuevaPartida.btnJugar.setEnabled(true);
			// Desactivamos botón Ver resultado ronda de la Vista de Nueva Partida
			vistaNuevaPartida.btnVerResultadoRonda.setEnabled(false);
			
			// Reiniciamos los textos del tablero
			vistaNuevaPartida.lblChinosApostadoJugador.setText("Tu apuesta:");
			vistaNuevaPartida.lblChinosApostadosRival.setText("Su apuesta:");
			vistaNuevaPartida.lblChinosManoJugador.setText("Chinos en tu mano:");
			vistaNuevaPartida.lblChinosManoRival.setText("Chinos en su mano:");	
			
			// Volvemos al tablero
			// El diálogo de Resultado Ronda se oculta
			vistaNuevaPartida.dlgResultadoRonda.setVisible(false);
			// Mostramos la Vista Nueva Partida
			vistaNuevaPartida.MostrarVistaNuevaPartida();		
		}
		
		// Botón de siguiente ronda del diálogo de siguiente partida
		else if (botonPulsado.equals(vistaNuevaPartida.btnSiguientePartida)) {
			
			// Reiniciamos los valores de la partida antes de volver al tablero
			reiniciarValoresYTablero();
			vistaNuevaPartida.dlgResultadoRonda.setVisible(false);
			vistaNuevaPartida.MostrarVistaNuevaPartida();	
		}	
		
		// Botón de Retroceder
		else if (botonPulsado.equals(vistaNuevaPartida.btnAtras)) {
			
			vistaNuevaPartida.dlgMensajeAtras.setVisible(true);	
		}
		
		// Botones del diálogo Atrás de la Vista de Nueva Partida
		// Botón Sí
		else if (botonPulsado.equals(vistaNuevaPartida.btnContinuarAtras)) {
			
			// Borramos el identificador y las victorias del jugador almacenado
			idJugador = null;
			victoriasJugador = null;
			// Cerramos las ventanas de diálogo y de partida y mostramos la Vista Principal
			vistaNuevaPartida.dlgMensajeAtras.setVisible(false);
			vistaNuevaPartida.OcultarVistaNuevaPartida();
			vistaPrincipal.MostrarPrincipal();
		}
		
		// Botón No
		else if (botonPulsado.equals(vistaNuevaPartida.btnCancelarAtras)) {
			
			vistaNuevaPartida.dlgMensajeAtras.setVisible(false);
		}
		
		// Botón de Reiniciar de la Vista de Nueva Partida
		else if (botonPulsado.equals(vistaNuevaPartida.btnReiniciar)) {
			
			vistaNuevaPartida.dlgMensajeReiniciar.setVisible(true);
		}
		
		// Botones del diálogo reiniciar de la Vista de Nueva Partida
		// Botón Sí
		else if (botonPulsado.equals(vistaNuevaPartida.btnContinuarReiniciar)) {
			
			// Reiniciamos todos los valores de las etiquetas del tablero y los valores almacenados
			reiniciarValoresYTablero();
			
			// Cerramos la ventana de diálogo y reiniciamos los botones de jugadas
			vistaNuevaPartida.dlgMensajeReiniciar.setVisible(false);
		}
		
		// Botones del diálogo Atrás de la Vista de Nueva Partida
		// Botón No
		else if (botonPulsado.equals(vistaNuevaPartida.btnCancelarReiniciar)) {
			
			vistaNuevaPartida.dlgMensajeReiniciar.setVisible(false);
		}	
	}
	
	public void reiniciarValoresYTablero() {
		
		// Reiniciamos todos los valores de las etiquetas del tablero
		vistaNuevaPartida.lblRondasGanadas.setText("Rondas ganadas: 0");
		vistaNuevaPartida.lblRondasPerdidas.setText("Rondas perdidas: 0");
		vistaNuevaPartida.lblChinosApostadoJugador.setText("Tu apuesta:");
		vistaNuevaPartida.lblChinosApostadosRival.setText("Su apuesta:");
		vistaNuevaPartida.lblChinosManoJugador.setText("Chinos en tu mano:");
		vistaNuevaPartida.lblChinosManoRival.setText("Chinos en su mano:");	
		
		// Reiniciamos los valores almacenados
		rondasGanadasJugador = 0;
		rondasGanadasCPU = 0;
		apuestaJugador = null;
		apuestaCPU = null;
		manoJugador = null;
		manoCPU = null;
		
		// Reiniciamos los botones de la jugada
		// Activamos botón Jugar de la Vista Nueva Partida
		vistaNuevaPartida.btnJugar.setEnabled(true);
		// Desactivamos botón Ver reultado ronda de la Vista de Nueva Partida
		vistaNuevaPartida.btnVerResultadoRonda.setEnabled(false);
		
		// Reiniciamos los botones del diálogo de siguiente ronda
		vistaNuevaPartida.btnSiguienteRonda.setEnabled(true);
		vistaNuevaPartida.btnSiguientePartida.setEnabled(false);		
	}
	
	@Override
	public void windowOpened(WindowEvent e) {}

	@Override
	public void windowClosing(WindowEvent e) 
	{
		
		if(vistaSeleccionUsuario.dlgMensaje.isActive())
		{
			vistaSeleccionUsuario.dlgMensaje.setVisible(false);
		}
		else if (vistaNuevaPartida.dlgJugada.isActive()) {
			vistaNuevaPartida.dlgJugada.setVisible(false);
		}
		else if (vistaNuevaPartida.dlgErrorDatos.isActive()) {
			vistaNuevaPartida.dlgErrorDatos.setVisible(false);
		}
		// No permitimos cerrar el diálogo de final de ronda sin pulsar uno de los botones, afectaría a la partida
		else if (vistaNuevaPartida.dlgResultadoRonda.isActive()) {
		}
		else if (vistaNuevaPartida.dlgMensajeAtras.isActive()) {
			vistaNuevaPartida.dlgMensajeAtras.setVisible(false);
		}
		else if (vistaNuevaPartida.dlgMensajeReiniciar.isActive()) {
			vistaNuevaPartida.dlgMensajeReiniciar.setVisible(false);
		}
		else {
			System.exit(0);
		}
	}
	
	@Override
	public void windowClosed(WindowEvent e) {}

	@Override
	public void windowIconified(WindowEvent e) {}

	@Override
	public void windowDeiconified(WindowEvent e) {}

	@Override
	public void windowActivated(WindowEvent e) {}

	@Override
	public void windowDeactivated(WindowEvent e) {}
}
		