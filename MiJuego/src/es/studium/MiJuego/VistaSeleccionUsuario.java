package es.studium.MiJuego;

import static javax.swing.GroupLayout.Alignment.BASELINE;
import static javax.swing.GroupLayout.Alignment.TRAILING;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;

import javax.swing.GroupLayout;


public class VistaSeleccionUsuario extends Frame {
	
	private static final long serialVersionUID = 1L;
	
	Label lblUsuario = new Label("Usuario");
	Label lblContrase�a = new Label("Contrase�a");
	
	TextField txtUsuario = new TextField(15);
	TextField txtContrase�a = new TextField(15);
	
	Button btnLogin = new Button("Iniciar Sesi�n");
	Button btnNuevoJugador = new Button("Crear nuevo usuario");
	Button btnAtras = new Button("Atr�s");
	
	// Di�logo de la alerta de la vista
	Dialog dlgMensaje = new Dialog(this, "Mensaje", true);
	Label lblMensaje = new Label("");
	Button btnContinuar = new Button("Continuar");
	
	public VistaSeleccionUsuario() {
		
		// T�tulo del Frame
		setTitle("Registro");
		// Color del Frame
		setBackground(new Color(179, 213, 243));
		
		// Layout usado
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);

		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
        		// Primera columna
                .addGroup(layout.createParallelGroup(TRAILING)
                        .addComponent(lblUsuario)
                        .addComponent(lblContrase�a)
                        .addComponent(btnLogin))
                // Segunda columna
                .addGroup(layout.createParallelGroup(TRAILING)
                        .addComponent(txtUsuario)
                        .addComponent(txtContrase�a)
                        .addComponent(btnNuevoJugador))
                // Tercera columna
                .addGroup(layout.createParallelGroup(TRAILING)
                        .addComponent(btnAtras))                  
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
        		// Primera fila
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(lblUsuario)
                        .addComponent(txtUsuario))
                // Segunda fila
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(lblContrase�a)
                        .addComponent(txtContrase�a))
                // Tercera fila
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(btnLogin)
                        .addComponent(btnNuevoJugador)
                        .addComponent(btnAtras))
        );
        
        pack();
        
        // Para que en vez de mostrar la contrase�a se sustituyan los caracteres por *
        txtContrase�a.setEchoChar('*'); 
        // Fijar que la ventana salga siempre en el medio
		setLocationRelativeTo(null);
		// Mensaje de alerta de la vista
		dlgMensaje.setBackground(new Color(179, 213, 243));
		dlgMensaje.setSize(300,120);
		dlgMensaje.setLayout(new GridLayout(2,1));
		dlgMensaje.setResizable(false);
		dlgMensaje.add(lblMensaje);
		dlgMensaje.setLocationRelativeTo(null);
		dlgMensaje.add(btnContinuar);
		dlgMensaje.setBackground(new Color(179, 213, 243));	
		}

		public void MostrarVistaSeleccionUsuario() 
		{
			this.setVisible(true);
		}
		
		public void OcultarVistaSeleccionUsuario()
		{
			this.setVisible(false);
		}	
		
		void limpiar()
		{
			txtUsuario.setText("");
			txtContrase�a.setText("");
			// Focus siempre en Usuario
			txtUsuario.requestFocus(); 
		}
}

