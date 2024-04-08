package es.studium.MiJuego;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.TextArea;

public class VistaRanking extends Frame
{
	private static final long serialVersionUID = 1L;
	// Componentes
	TextArea taRanking = new TextArea(25, 25);
	Button btnAtras = new Button("Atrás");

	// Constructor
	public VistaRanking() {
		
		setTitle("Ranking: Los Chinos");

		// Pantalla
		// Layout del Frame
		setLayout(new BorderLayout());
		taRanking.append("Pos\t\tNombre\t\tVictorias\n");	
		taRanking.append("----------------------------------------------------------------\n");				
		add(taRanking, "Center");
		add(btnAtras, "South");
		// Tamaño de Frame
		setSize(300,300);
		// Centrar la ventana
		setLocationRelativeTo(null);
		// Evitar redimensionado
		setResizable(false);
	}
	
	public void MostrarRanking()
	{
		this.setVisible(true);
	}
	
	public void OcultarRanking()
	{
		this.setVisible(false);
	}
}
