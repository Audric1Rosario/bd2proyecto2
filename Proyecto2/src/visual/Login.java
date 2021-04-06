package visual;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logic.Juego;
import logic.modelos.Central;
import logic.modelos.Direccion;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
import java.awt.event.ActionEvent;
import logic.modelos.*;

public class Login extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private String screenPath;
	private JPasswordField passPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		// Crear primer usuario
		/*
		 *  Usuario(String nombre, String apellido, char sexo, String correo, String telefono,
			int id_direccion, int id_central, String nombre_usuario, String clave, int monedas)
		 * *//*
		Direccion direcPersona;
		int idDirecPersona;
		int personaId;
		Usuario nuevo;
		Persona primera;

		if (Juego.getInstance().totalUsuario() == 0) {
			direcPersona = new Direccion("DOM", "", 51000);
			idDirecPersona = -1;
			idDirecPersona = Juego.getInstance().addDireccion(direcPersona);	
			
			primera = new Persona("Admin", "admin", 'M', "hola@mundo.com", "809-999-0000", idDirecPersona);
			personaId = -1;
			personaId = Juego.getInstance().addPersona(primera);	
			
			nuevo = new Usuario(primera.getNombre(), primera.getApellido(), primera.getSexo(), primera.getCorreo(),
					primera.getTelefono(), idDirecPersona, 1, "admin", "admin", 0);
			
			if (Juego.getInstance().addUsuario(nuevo)) {
				JOptionPane.showMessageDialog(null, "Primer usuario registrado correctamente");
			}
		}*/
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/image/game-controller.png")));
		setResizable(false);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 782, 498);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblLoginImage = new JLabel("");
		screenPath = "/image/user.png";
		lblLoginImage.setBounds(292, 86, 166, 149);
		lblLoginImage.setIcon(new ImageIcon(((new ImageIcon(Login.class.getResource(screenPath))).getImage()).getScaledInstance(
				lblLoginImage.getWidth(), lblLoginImage.getHeight(), Image.SCALE_SMOOTH)));
		panel.add(lblLoginImage);
		
		JLabel lblLogo = new JLabel("");
		screenPath = "/image/game-controller.png";
		lblLogo.setBounds(17, 10, 48, 52);
		lblLogo.setIcon(new ImageIcon(((new ImageIcon(Login.class.getResource(screenPath))).getImage()).getScaledInstance(
				lblLogo.getWidth(), lblLogo.getHeight(), Image.SCALE_SMOOTH)));
		panel.add(lblLogo);
		
		txtUsuario = new JTextField();
		txtUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		txtUsuario.setBounds(255, 285, 233, 27);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JButton btnEnter = new JButton("Entrar");
		/*btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = txtUsuario.getText();
				String password = String.valueOf(passPass.getPassword());
				boolean ok = false;
				
				Empleado actual = Clinica.getInstance().buscarEmpleadoByUsername(username);
				
				if (actual != null)
				{
					if (actual.getPassword().equals(password)) {
						ok = true;
					}
				}
				
				if (ok) {
					Dashboard ventana = new Dashboard(actual);				
					actual.setLastConnection(new Date());
					ventana.setVisible(true);
					
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.", "Error de inicio.", JOptionPane.WARNING_MESSAGE);
				}
				
				
			}
		});*/
		
		btnEnter.setBounds(285, 395, 173, 27);
		panel.add(btnEnter);
		
		JLabel lblUsername = new JLabel("Usuario");
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setBounds(334, 261, 74, 14);
		panel.add(lblUsername);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrasea.setBounds(334, 323, 74, 14);
		panel.add(lblContrasea);
		
		JLabel lblNewLabel = new JLabel("Central de Videojuegos");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setForeground(new Color(70, 130, 180));
		lblNewLabel.setBounds(75, 23, 297, 36);
		panel.add(lblNewLabel);
		
		passPass = new JPasswordField();
		passPass.setHorizontalAlignment(SwingConstants.CENTER);
		passPass.setBounds(255, 348, 233, 27);
		panel.add(passPass);
		panel.getRootPane().setDefaultButton(btnEnter);
	}

}
