package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.border.TitledBorder;

import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import logic.JDBCPostgreSQLConnect;
import logic.Juego;
import logic.modelos.Categoria;

import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.border.EtchedBorder;

import visual.Administracion.*;
import visual.Arcade.*;
import visual.Categorias.*;
import visual.Clientes.*;
import visual.Perfil.*;
import visual.Premio.*;
import visual.Puntuaciones.*;
import logic.modelos.*;

public class Dashboard extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	//private Empleado actual;
	
	// Label
	private static JLabel lblNombreUsuario;
	private static JLabel lblArcades;
	private static JLabel lblUsuarios;
	private static JLabel lblPremios;
	private static JLabel lblEdades;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		// Al correr por primera vez, crear primera central.
		Central instancia = Juego.getInstance().buscarCentralPorId(1);
		JOptionPane.showMessageDialog(null, instancia.toString());
		if (Juego.getInstance().totalCentral() == 0) {

			Direccion direccion = new Direccion("DOM", "", 51000);
			int idDireccion = -1;
			idDireccion = Juego.getInstance().addDireccion(direccion);			
			Central nueva = new Central(idDireccion, "Central", 1, "809-999-9999");
			
			if (Juego.getInstance().addCentral(nueva)) {
				JOptionPane.showMessageDialog(null, "Primera central registrada correctamente");
			}
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dashboard frame = new Dashboard();
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
	public Dashboard() {
		setTitle("Dashboard");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Dashboard.class.getResource("/image/game-controller.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 720);
		setLocationRelativeTo(null);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		// Menú de Clientes.
		JMenu mnRegistro = new JMenu("Clientes");
		menuBar.add(mnRegistro);

		JMenuItem mntmRegCliente = new JMenuItem("Registrar Cliente");
		mntmRegCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Juego.getInstance().totalCentral() != 0) {
					RegCliente ventana = null;
					try {
						ventana = new RegCliente();
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					ventana.setModal(true);
					ventana.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "No es posible abrir, sin una central de videojuegos donde registrar.");
				}
			}
		});
		mnRegistro.add(mntmRegCliente);

		// Menú de Arcades.
		JMenu mnArcades = new JMenu("Arcade");
		menuBar.add(mnArcades);

		JMenuItem mntmAgregarArcade = new JMenuItem("Agregar Arcade");
		mntmAgregarArcade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Juego.getInstance().totalCentral() != 0 && Juego.getInstance().totalCategoria() != 0) {
					CrearArcade ventana = new CrearArcade();
					ventana.setModal(true);
					ventana.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "No se pueden crear arcades sin central o sin categorías");
				}
			}
		});
		mnArcades.add(mntmAgregarArcade);

		JMenuItem mntmListaDeArcades = new JMenuItem("Lista de Arcades");
		mntmListaDeArcades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaArcades ventana = new ListaArcades();
				ventana.setModal(true);
				ventana.setVisible(true);
			}
		});
		mnArcades.add(mntmListaDeArcades);
		
		JMenuItem mntmJugarArcade = new JMenuItem("Jugar Arcade");
		mntmJugarArcade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JugarArcade ventana = new JugarArcade();
				ventana.setModal(true);
				ventana.setVisible(true);
			}
		});
		mnArcades.add(mntmJugarArcade);

		// Menú del Puntuaciones.
		JMenu mnPuntuaciones = new JMenu("Puntuaciones");
		menuBar.add(mnPuntuaciones);
		
		JMenuItem mntmPuntuaciones = new JMenuItem("Lista de Puntuaciones");
		mntmPuntuaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaPuntuaciones ventana = new ListaPuntuaciones();
				ventana.setModal(true);
				ventana.setVisible(true);	
			}
		});
		mnPuntuaciones.add(mntmPuntuaciones);

		// Menú de las Categorías.
		JMenu mnCategoria = new JMenu("Categor\u00EDas");
		menuBar.add(mnCategoria);

		JMenuItem mntmCategoria = new JMenuItem("Crear categor\u00EDa");
		mntmCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Juego.getInstance().totalCentral() != 0) {
					CrearCategoria ventana = new CrearCategoria();
					ventana.setModal(true);
					ventana.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "No es posible abrir, sin una central de videojuegos donde registrar.");
				}				
			}
		});
		mnCategoria.add(mntmCategoria);

		JMenuItem mntmListaDeCategorias = new JMenuItem("Lista de categorias");
		mntmListaDeCategorias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaCategorias ventana = new ListaCategorias();
				ventana.setModal(true);
				ventana.setVisible(true);
			}
		});
		mnCategoria.add(mntmListaDeCategorias);

		// Menú del Premio
		JMenu mnPremio = new JMenu("Premio");
		menuBar.add(mnPremio);

		JMenuItem mntmCrearPremio = new JMenuItem("Crear Premio");
		mntmCrearPremio.addActionListener(new ActionListener() {
			// Borrar cuando hagan push
			public void actionPerformed(ActionEvent e) {
				if (Juego.getInstance().totalCentral() != 0) {
					CrearPremio ventana = new CrearPremio();
					ventana.setModal(true);
					ventana.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "No es posible abrir, sin una central de videojuegos donde registrar.");
				}
			}
		});
		mnPremio.add(mntmCrearPremio);

		JMenuItem mntmListaPremios = new JMenuItem("Lista de Premios");
		mntmListaPremios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaPremios ventana = new ListaPremios();
				ventana.setModal(true);
				ventana.setVisible(true);
			}
		});
		mnPremio.add(mntmListaPremios);
		
		JMenuItem mntmCanjearPremio = new JMenuItem("Canjear Premio");
		mntmCanjearPremio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CanjearPremio ventana = new CanjearPremio();
				ventana.setModal(true);
				ventana.setVisible(true);
			}
		});
		mnPremio.add(mntmCanjearPremio);
		
		// Menú Perfil
		JMenu mnPerfil = new JMenu("Perfil");
		menuBar.add(mnPerfil);
		
		JMenuItem mntmVerPerfil = new JMenuItem("Ver Perfil");
		mntmVerPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VerPerfil ventana = new VerPerfil();
				ventana.setModal(true);
				ventana.setVisible(true);
			}
		});
		mnPerfil.add(mntmVerPerfil);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Guardar datos.
				Login ventana = new Login();
				ventana.setVisible(true);
				dispose();
			}
		});
		mnPerfil.add(mntmSalir);
		
		// Menú para los administradores.
		JMenu mnAdministracin = new JMenu("Administraci\u00F3n");
		menuBar.add(mnAdministracin);

		JMenuItem mntmUsuarios = new JMenuItem("Usuarios");
		mntmUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuarios ventana = new Usuarios();
				ventana.setModal(true);
				ventana.setVisible(true);
			}
		});
		mnAdministracin.add(mntmUsuarios);

		// Ocultar opciones según los permisos del usuario
		/*if (actual instanceof Secretaria) {
			mnRegistro.setVisible(false);
			mnArcades.setVisible(false);
			mnPuntuaciones.setVisible(false);
			mnRegistro.setVisible(false);
			mnCategoria.setVisible(false);
			mnAdministracin.setVisible(false);
			mntmListaPremios.setVisible(false);
			mntmVerPerfil.setVisible(false);
		}

		if (actual instanceof Doctor) {
			mntmRegCliente.setVisible(false);
			mntmAgregarArcade.setVisible(false);
			mnRegistro.setVisible(false);
			mntmCategoria.setVisible(false);
			mnAdministracin.setVisible(false);
			mntmVerPerfil.setVisible(false);
		}

		if (actual instanceof Administrador) {
			switch (((Administrador)actual).getAutoridad()) {
			case 4:
				mntmAgregarArcade.setVisible(false);
				mntmCategoria.setVisible(false);
			case 3:
				mnAdministracin.setVisible(false);
			case 2:
				//mntmAdministradores.setVisible(false);
				//mntmVerPerfil.setVisible(false);
			case 1:
				mnPremio.setVisible(false);
			}
		}*/

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JPanel panelArcades = new JPanel();
		panelArcades.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Arcades m\u00E1s jugados.", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelArcades.setBounds(10, 38, 518, 300);
		panel.add(panelArcades);
		panelArcades.setLayout(null);
		
		lblArcades = new JLabel("");
		lblArcades.setHorizontalAlignment(SwingConstants.CENTER);
		lblArcades.setBounds(10, 21, 498, 268);
		panelArcades.add(lblArcades);

		JPanel panelUsuarios = new JPanel();
		panelUsuarios.setBorder(new TitledBorder(null, "Records de jugadores.", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelUsuarios.setBounds(536, 38, 518, 300);
		panel.add(panelUsuarios);
		panelUsuarios.setLayout(null);
		
		lblUsuarios = new JLabel("");
		lblUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuarios.setBounds(10, 21, 498, 268);
		panelUsuarios.add(lblUsuarios);
		
		JPanel panelPremios = new JPanel();
		panelPremios.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Premios m\u00E1s reclamados.", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelPremios.setBounds(10, 349, 518, 300);
		panel.add(panelPremios);
		panelPremios.setLayout(null);
		
		lblPremios = new JLabel("");
		lblPremios.setHorizontalAlignment(SwingConstants.CENTER);
		lblPremios.setBounds(10, 21, 498, 268);
		panelPremios.add(lblPremios);

		JPanel panelEdad = new JPanel();
		panelEdad.setBorder(new TitledBorder(null, "Edades de los Jugadores", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelEdad.setBounds(536, 349, 518, 300);
		panel.add(panelEdad);
		panelEdad.setLayout(null);
		
		lblEdades = new JLabel("");
		lblEdades.setHorizontalAlignment(SwingConstants.CENTER);
		lblEdades.setBounds(10, 21, 498, 268);
		panelEdad.add(lblEdades);

		JLabel lblUserImage = new JLabel("");
		lblUserImage.setBounds(871, 11, 29, 25);
		lblUserImage.setIcon(new ImageIcon(((new ImageIcon(Dashboard.class.getResource("/image/user.png"))).getImage()).getScaledInstance(lblUserImage.getWidth(), 
				lblUserImage.getHeight(), Image.SCALE_SMOOTH)));
		panel.add(lblUserImage);

		lblNombreUsuario = new JLabel("Nombre del usuario");
		/*lblNombreUsuario.setText(actual.getNombre());
		lblNombreUsuario.setBounds(910, 15, 144, 16);*/
		panel.add(lblNombreUsuario);

		JLabel lblLogoTipo = new JLabel("");
		lblLogoTipo.setBounds(30, 11, 29, 25);
		lblLogoTipo.setIcon(new ImageIcon(((new ImageIcon(Dashboard.class.getResource("/image/game-controller.png"))).getImage()).getScaledInstance(lblLogoTipo.getWidth(), 
				lblLogoTipo.getHeight(), Image.SCALE_SMOOTH)));
		panel.add(lblLogoTipo);

		JLabel lblCentralVideojuegos = new JLabel("Central de videojuegos");
		lblCentralVideojuegos.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblCentralVideojuegos.setBounds(69, 11, 353, 25);
		panel.add(lblCentralVideojuegos);
		
		// Llamar a los gráficos.
/*
		Dashboard.cargarEnferCrit();
		Dashboard.cargarEdades();
		Dashboard.cargarSangre();
		Dashboard.cargarVacunacion();
*/
	}
}
