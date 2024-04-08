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
	Label lblContraseña = new Label("Contraseña");
	
	TextField txtUsuario = new TextField(15);
	TextField txtContraseña = new TextField(15);
	
	Button btnLogin = new Button("Iniciar Sesión");
	Button btnNuevoJugador = new Button("Crear nuevo usuario");
	Button btnAtras = new Button("Atrás");
	
	// Diálogo de la alerta de la vista
	Dialog dlgMensaje = new Dialog(this, "Mensaje", true);
	Label lblMensaje = new Label("");
	Button btnContinuar = new Button("Continuar");
	
	public VistaSeleccionUsuario() {
		
		// Título del Frame
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
                        .addComponent(lblContraseña)
                        .addComponent(btnLogin))
                // Segunda columna
                .addGroup(layout.createParallelGroup(TRAILING)
                        .addComponent(txtUsuario)
                        .addComponent(txtContraseña)
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
                        .addComponent(lblContraseña)
                        .addComponent(txtContraseña))
                // Tercera fila
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(btnLogin)
                        .addComponent(btnNuevoJugador)
                        .addComponent(btnAtras))
        );
        
        pack();
        
        // Para que en vez de mostrar la contraseña se sustituyan los caracteres por *
        txtContraseña.setEchoChar('*'); 
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
			txtContraseña.setText("");
			// Focus siempre en Usuario
			txtUsuario.requestFocus(); 
		}
}

