package es.studium.MiJuego;


import static javax.swing.GroupLayout.Alignment.BASELINE;
import static javax.swing.GroupLayout.Alignment.TRAILING;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Toolkit;

import javax.swing.GroupLayout;

public class VistaNuevaPartida extends Frame {
	
	private static final long serialVersionUID = 1L;
	
	// Etiquetas con las rondas ganadas de cada jugador
	Label lblRondasGanadas = new Label("Rondas ganadas: 0");
	Label lblRondasPerdidas = new Label("Rondas perdidas: 0");
	
	// Etiquetas con las apuestas de cada jugador
	Label lblChinosApostadoJugador = new Label("Tu apuesta:");
	Label lblChinosApostadosRival = new Label("Su apuesta:");
	
	// Etiquetas con los chinos en mano de cada jugador
	Label lblChinosManoJugador = new Label("Chinos en tu mano:");
	Label lblChinosManoRival = new Label("Chinos en su mano:");
	
	// Botones para realizar la apuesta y avanzar de jugada
	Button btnJugar = new Button("Jugar");
	Button btnVerResultadoRonda = new Button("Ver resultado ronda");
	
	// Botones para volver a inicio o reiniciar la partida
	Button btnAtras = new Button("Atrás");
	Button btnReiniciar = new Button("Reiniciar");
		
	// Diálogo del resultado de la ronda
	Dialog dlgJugada = new Dialog(this, "Jugada", true);
	Label lblApuesta = new Label("Elige tu apuesta:");
	Label lblMano = new Label("Elige los chinos en tu mano:");
	TextField txtChinosApostadosJugador = new TextField(1);
	TextField txtChinosMano = new TextField(1);
	Button btnAceptarJugada = new Button("Aceptar Jugada");	
	
	// Diálogo de error en datos introducidos
    Dialog dlgErrorDatos = new Dialog(this, "Error", true);
	Label lblMensajeError = new Label("");
	Button btnCerrarMensajeError = new Button("Aceptar");
	
	
	// Diálogo de la jugada del usuario
	Dialog dlgResultadoRonda = new Dialog(this, "Resultado", true);
	Label lblResultadoRondaApuestaJugador = new Label("");
	Label lblResultadoRondaApuestaCPU = new Label("");
	Label lblResultadoRondaManoJugador = new Label("");
	Label lblResultadoRondaManoCPU = new Label("");
	Label lblResultadoRondaSumaChinos = new Label("");	
	Label lblResultadoRonda = new Label ("");
	Button btnSiguienteRonda = new Button("Siguiente ronda");
	Button btnSiguientePartida = new Button("Nueva partida");
	
	// Diálogo de alerta retroceder
	Dialog dlgMensajeAtras = new Dialog(this, "Retroceder", true);
	Label lblMensajeAtras = new Label("¿Seguro que quiere volver al Menú Principal? Se cerrará su sesión.");
	Button btnContinuarAtras = new Button("Sí");
	Button btnCancelarAtras = new Button("No");		
	
	// Diálogo de alerta reiniciar
	Dialog dlgMensajeReiniciar = new Dialog(this, "Reiniciar", true);
	Label lblMensajeReiniciar = new Label("¿Seguro que quiere reiniciar? Se perderá el progreso.");
	Button btnContinuarReiniciar = new Button("Sí");
	Button btnCancelarReiniciar = new Button("No");	
	
	// Declarar el objeto Image
	Image imagen;
	Toolkit herramienta;
	
	public VistaNuevaPartida() {
		
		// Establecer el método de trabajo con imágenes
		herramienta = getToolkit();
		
		// Especificar la ruta de la imagen
		imagen = herramienta.getImage("chinos.jpg");
		
		// Indicamos el titulo
		setTitle("El juego de los chinos");
		
		// Por defecto se deshabilita el botón de ver resultado ronda
		btnVerResultadoRonda.setEnabled(false);
		
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
        layout.setHorizontalGroup(layout.createSequentialGroup()
        		// Primera columna
                .addGroup(layout.createParallelGroup(TRAILING)
                        .addComponent(lblRondasGanadas)
                        .addComponent(lblChinosApostadoJugador)
                        .addComponent(lblChinosManoJugador))                        
                // Segunda columna
                .addGroup(layout.createParallelGroup(TRAILING)
                		.addComponent(btnJugar)
                        .addComponent(btnAtras))
                // Tercera columna
                .addGroup(layout.createParallelGroup(TRAILING)
                		.addComponent(btnVerResultadoRonda)
                		.addComponent(btnReiniciar))                

                // Cuarta columna
                .addGroup(layout.createParallelGroup(TRAILING)
                        .addComponent(lblRondasPerdidas)
                        .addComponent(lblChinosApostadosRival))
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
        		// Primera fila
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(lblRondasGanadas)
                        .addComponent(lblRondasPerdidas))
                // Segunda fila
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(lblChinosApostadoJugador)
                        .addComponent(lblChinosApostadosRival))
                // Tercera fila
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(lblChinosManoJugador)
                		.addComponent(btnJugar)                        
                		.addComponent(btnVerResultadoRonda))
                // Cuarta fila
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(btnAtras)
                		.addComponent(btnReiniciar))                    
        );
        
        pack();
		
		setLocationRelativeTo(null);
		setSize(600,400);

		// Mensaje del diálogo la jugada de la vista
		dlgJugada.setSize(300,130);
		dlgJugada.setLayout(new FlowLayout());
		dlgJugada.setResizable(false);
		dlgJugada.add(lblApuesta);
		dlgJugada.add(txtChinosApostadosJugador);
		dlgJugada.add(lblMano);
		dlgJugada.add(txtChinosMano);		
		dlgJugada.add(btnAceptarJugada);
		dlgJugada.setLocationRelativeTo(null);
		dlgJugada.setBackground(new Color(179, 213, 243));
		
		// Diálogo de error en datos introducidos
		dlgErrorDatos.setSize(300,150);
		dlgErrorDatos.setLayout(new GridLayout(2,1));
		dlgErrorDatos.setResizable(false);
		dlgErrorDatos.add(lblMensajeError);
		dlgErrorDatos.add(btnCerrarMensajeError);
		dlgErrorDatos.setLocationRelativeTo(null);
		dlgErrorDatos.setBackground(new Color(179, 213, 243));

		// Mensaje de final de ronda
		dlgResultadoRonda.setSize(320,180);
		dlgResultadoRonda.setLayout(new FlowLayout());
		dlgResultadoRonda.setResizable(false);
		dlgResultadoRonda.add(lblResultadoRondaApuestaJugador);
		dlgResultadoRonda.add(lblResultadoRondaApuestaCPU);
		dlgResultadoRonda.add(lblResultadoRondaManoJugador);
		dlgResultadoRonda.add(lblResultadoRondaManoCPU);
		dlgResultadoRonda.add(lblResultadoRondaSumaChinos);		
		dlgResultadoRonda.add(lblResultadoRonda);
		dlgResultadoRonda.add(btnSiguienteRonda);
		dlgResultadoRonda.add(btnSiguientePartida);
		dlgResultadoRonda.setLocationRelativeTo(null);
		dlgResultadoRonda.setBackground(new Color(179, 213, 243));
				
		// Mensaje de alerta de retroceder a vista principal
		dlgMensajeAtras.setSize(400,100);
		dlgMensajeAtras.setLayout(new FlowLayout());
		dlgMensajeAtras.setResizable(false);
		dlgMensajeAtras.add(lblMensajeAtras);
		dlgMensajeAtras.add(btnContinuarAtras);
		dlgMensajeAtras.add(btnCancelarAtras);
		dlgMensajeAtras.setLocationRelativeTo(null);
		dlgMensajeAtras.setBackground(new Color(179, 213, 243));	
		
		// Mensaje de alerta de reiniciar partida
		dlgMensajeReiniciar.setSize(350,100);
		dlgMensajeReiniciar.setLayout(new FlowLayout());
		dlgMensajeReiniciar.setResizable(false);
		dlgMensajeReiniciar.add(lblMensajeReiniciar);
		dlgMensajeReiniciar.add(btnContinuarReiniciar);
		dlgMensajeReiniciar.add(btnCancelarReiniciar);
		dlgMensajeReiniciar.setLocationRelativeTo(null);
		dlgMensajeReiniciar.setBackground(new Color(179, 213, 243));
		}

		public void MostrarVistaNuevaPartida() 
		{
			this.setVisible(true);
		}
		
		public void OcultarVistaNuevaPartida()
		{
			this.setVisible(false);
		}
		
		public void paint(Graphics g)
		{
			// Dibujar la imagen
			g.drawImage(imagen,4,23,this);
		}
}

