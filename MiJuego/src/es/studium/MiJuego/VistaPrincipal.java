package es.studium.MiJuego;

import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;


public class VistaPrincipal extends Frame {
	private static final long serialVersionUID = 1L;
	Label lblMenuPrincipal = new Label ("Men� Principal", 1);

	Button btnNuevaPartida = new Button("Nueva Partida");
	Button btnRanking = new Button("Ranking");
	Button btnAyuda = new Button("C�mo se juega");
	Button btnSalir = new Button("Salir");
	
	public VistaPrincipal() {
		// T�tulo del Frame
		setTitle("Los Chinos");
		 // Color de fondo del Frame
		setBackground(new Color(187, 232, 242));
		// Layout del Frame
		setLayout(new GridLayout(5,1));	
		// A�adir Label y Botones al Frame
		add(lblMenuPrincipal);
		add(btnNuevaPartida); 
		add(btnRanking);
		add(btnAyuda); 
		add(btnSalir);	
		// Tama�o del Frame
		setSize(300, 200);
		// Fijar que la ventana salga siempre en el medio
		setLocationRelativeTo(null);
		// Mostrarlo
		MostrarPrincipal();
	}

	public void MostrarPrincipal() {
		this.setVisible(true);
	}
	
	public void OcultarPrincipal() {
		this.setVisible(false);
	}
}
