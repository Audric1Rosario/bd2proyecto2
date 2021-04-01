package visual.Categorias;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import logic.JDBCPostgreSQLConnect;
import logic.Juego;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;

public class ListaCategorias extends JDialog {

	private final JPanel contentPanel = new JPanel();

	// Tablas
	private static JTable tableEnfermedades;
	private static DefaultTableModel model;
	private static Object row[];

	// Text-Field
	private static JTextField txtNombre;

	// Text-Area
	private static JTextArea txtDescripcion;
	private static JTextField txtBuscar;

	// Botones.
	private JButton btnBuscar;
	private static JButton btnModificar;
	private static JButton btnEliminar;
	private JButton btnCerrar;
	private static JTextField txtClasificacion;
	
	static Connection conexion = null;
	static PreparedStatement preparedStatement = null;
	static ResultSet resultSet = null;

	// Variables lógicas.
	// private Empleado usuarioActual;
	 private static Categoria categoriaModificar;

	/**
	 * Launch the application.
	 */
	 
		 public static void main(String[] args) { 
			 try { 
				 ListaCategorias dialog = new ListaCategorias(); 
				 dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				 dialog.setVisible(true); 
			} catch (Exception e) { 
				e.printStackTrace(); 
			} 
		}
	/**
	 * Create the dialog.
	 */
	public ListaCategorias() {
		// this.usuarioActual = usuarioActual;
		ListaCategorias.categoriaModificar = null;
		setResizable(false);
		setTitle("Control de Categorías");
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(ListaCategorias.class.getResource("/image/game-controller.png")));
		setBounds(100, 100, 720, 480);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 79, 432, 323);
		contentPanel.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel.add(scrollPane, BorderLayout.CENTER);
		{
		model = new DefaultTableModel();
		String[] headers = { "Nombre", "Descripción", "Clasificación" };
		model.setColumnIdentifiers(headers);
		
		tableEnfermedades = new JTable() {
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			}
		};
		tableEnfermedades = new JTable() {
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			}};
			tableEnfermedades.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tableEnfermedades.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
				public void valueChanged(ListSelectionEvent event) {
					if (tableEnfermedades.getSelectedRow() != -1) {
						categoriaModificar = Juego.getInstance().buscarCategoriaByNombre(tableEnfermedades.getValueAt(tableEnfermedades.getSelectedRow(), 0).toString());
						rellenarDatos();
						
						/*
						if (usuarioActual instanceof Administrador) {
							if (((Administrador)usuarioActual).getAutoridad() <= 3) {
								btnModificar.setEnabled(true);
								btnEliminar.setEnabled(true);
							}
						}
						*/
					}
				}
			});
			tableEnfermedades.setModel(model);
			tableEnfermedades.getTableHeader().setResizingAllowed(false); 
			tableEnfermedades.getTableHeader().setReorderingAllowed(false);
			scrollPane.setViewportView(tableEnfermedades);
		
		tableEnfermedades.setModel(model);
		tableEnfermedades.getTableHeader().setResizingAllowed(false);
		tableEnfermedades.getTableHeader().setReorderingAllowed(false);
		}
		
		tableEnfermedades.getSelectionModel().addListSelectionListener(new
		ListSelectionListener(){ public void valueChanged(ListSelectionEvent event) {
			if (tableEnfermedades.getSelectedRow() != -1) { 
				categoriaModificar = Juego.getInstance().buscarCategoriaByNombre(
						tableEnfermedades.getValueAt(tableEnfermedades.getSelectedRow(), 0).toString()); 
					rellenarDatos();
					/*
					if (usuarioActual instanceof Administrador) { 
						if(((Administrador)usuarioActual).getAutoridad() <= 3) {
							btnModificar.setEnabled(true); 
							btnEliminar.setEnabled(true); 
						} 
					} 
					*/
			} 
		}});
		
		scrollPane.setViewportView(tableEnfermedades);
		{
			JPanel panel_1 = new JPanel();
			panel_1.setBorder(new TitledBorder(null, "Informaci\u00F3n detallada", TitledBorder.LEADING,
					TitledBorder.TOP, null, null));
			panel_1.setBounds(452, 11, 252, 391);
			contentPanel.add(panel_1);
			panel_1.setLayout(null);

			JLabel lblNombre = new JLabel("Nombre");
			lblNombre.setBounds(10, 21, 232, 14);
			panel_1.add(lblNombre);

			txtNombre = new JTextField();
			txtNombre.setEditable(false);
			txtNombre.setColumns(10);
			txtNombre.setBounds(10, 46, 232, 20);
			panel_1.add(txtNombre);

			JLabel lblDescripcion = new JLabel("Descripción");
			lblDescripcion.setBounds(10, 79, 232, 14);
			panel_1.add(lblDescripcion);

			JLabel lblClasificacion = new JLabel("Clasificación");
			lblClasificacion.setBounds(10, 235, 232, 14);
			panel_1.add(lblClasificacion);

			txtClasificacion = new JTextField();
			txtClasificacion.setEditable(false);
			txtClasificacion.setColumns(10);
			txtClasificacion.setBounds(10, 259, 232, 20);
			panel_1.add(txtClasificacion);

			JPanel panel_2 = new JPanel();
			panel_2.setBounds(10, 104, 232, 120);
			panel_1.add(panel_2);
			panel_2.setLayout(new BorderLayout(0, 0));

			JScrollPane scrollPane_1 = new JScrollPane();
			scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			panel_2.add(scrollPane_1, BorderLayout.CENTER);

			txtDescripcion = new JTextArea();
			txtDescripcion.setEditable(false);
			txtDescripcion.setWrapStyleWord(true);
			txtDescripcion.setLineWrap(true);
			scrollPane_1.setViewportView(txtDescripcion);
		}
		{
			JPanel panel_1 = new JPanel();
			panel_1.setBorder(new TitledBorder(null, "B\u00FAsqueda por nombre", TitledBorder.LEADING, TitledBorder.TOP,
					null, null));
			panel_1.setBounds(10, 11, 432, 57);
			contentPanel.add(panel_1);
			panel_1.setLayout(null);

			JLabel lblNombre = new JLabel("Nombre:");
			lblNombre.setBounds(10, 27, 75, 14);
			panel_1.add(lblNombre);

			btnBuscar = new JButton("");
			btnBuscar.setToolTipText("Buscar");
			
			btnBuscar.addActionListener(new ActionListener() { 
				public void actionPerformed(ActionEvent e) { 
					clear(); 
					tableEnfermedades.clearSelection();
					rellenarTabla();
				} 
			});
			btnBuscar.setIcon(new ImageIcon(ListaCategorias.class.getResource("/image/magnifying-glass.png")));
			btnBuscar.setBounds(318, 23, 40, 23);
			panel_1.add(btnBuscar);

			txtBuscar = new JTextField();
			txtBuscar.setBounds(95, 24, 213, 20);
			panel_1.add(txtBuscar);
			txtBuscar.setColumns(10);

			JButton btnRefresh = new JButton("");
			btnRefresh.setToolTipText("Recargar");
			btnRefresh.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
			clear(); 
			txtBuscar.setText("");
			rellenarTabla(); 
			} });
			
			btnRefresh.setIcon(new ImageIcon(ListaCategorias.class.getResource("/image/reload.png")));
			btnRefresh.setBounds(368, 23, 40, 23);
			panel_1.add(btnRefresh);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnModificar = new JButton("Modificar");
		
				btnModificar.addActionListener(new ActionListener() { 
				public void actionPerformed(ActionEvent e) { 
					if (categoriaModificar != null /* & usuarioActual instanceof Administrador*/) { 
						CrearCategoria ventana = new CrearCategoria(); 
						ventana.setModal(true);
						ventana.setVisible(true); 
					} 
				} });
				
				btnModificar.setEnabled(false);
				btnModificar.setActionCommand("OK");
				buttonPane.add(btnModificar);

				getRootPane().setDefaultButton(btnModificar);
			}
			{
				btnEliminar = new JButton("Eliminar");
				/*
				 * btnEliminar.addActionListener(new ActionListener() { public void
				 * actionPerformed(ActionEvent e) { if (enfermedadModificar != null &&
				 * usuarioActual instanceof Administrador) { clear(); // No hacer eso,
				 * 
				 * Clinica.getInstance().getEnfermedades().remove(enfermedadModificar);
				 * enfermedadModificar = null; rellenarTabla(txtBuscar.getText()); // Borrado
				 * lógico, no es posible eliminar esta enfermedad si hay objetos que la tienen
				 * 
				 * 
				 * int index =
				 * Clinica.getInstance().getEnfermedades().indexOf(enfermedadModificar); if
				 * (enfermedadModificar.getCantPacientes() == 0 && index != -1) { // Eliminar si
				 * no tiene ningún paciente. boolean esPosible = true; // Sólo se puede eliminar
				 * si no hay vacunas de esta enfermedad
				 * 
				 * for (Vacuna vacuna : Clinica.getInstance().getVacunas()) { if
				 * (vacuna.getEnfermedadNombre().equalsIgnoreCase(enfermedadModificar.getNombre(
				 * ))) { esPosible = false; // Ya se sabe que no se puede eliminar porque hay
				 * vacunas. vacuna.setListar(false); // esa vacuna tampoco puede ser listada
				 * mas. } }
				 * 
				 * if (esPosible) { // Si no hay vacunas.
				 * Clinica.getInstance().getEnfermedades().remove(index); } else {
				 * Clinica.getInstance().getEnfermedades().get(index).setListar(false); }
				 * 
				 * } else if (index != -1) {
				 * Clinica.getInstance().getEnfermedades().get(index).setListar(false); for
				 * (Vacuna vacuna : Clinica.getInstance().getVacunas()) { if
				 * (vacuna.getEnfermedadNombre().equalsIgnoreCase(enfermedadModificar.getNombre(
				 * ))) { vacuna.setListar(false); // esa vacuna tampoco puede ser listada mas. }
				 * } } else { JOptionPane.showMessageDialog(null, "Error al eliminar.",
				 * "Advertencia.", JOptionPane.WARNING_MESSAGE); }
				 * 
				 * rellenarTabla(txtBuscar.getText()); } else {
				 * JOptionPane.showMessageDialog(null, "Error al eliminar.", "Advertencia.",
				 * JOptionPane.WARNING_MESSAGE); } } });
				 */
				btnEliminar.setEnabled(false);
				buttonPane.add(btnEliminar);
			}
			{
				btnCerrar = new JButton("Cerrar");
				btnCerrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCerrar.setActionCommand("Cancel");
				buttonPane.add(btnCerrar);
			}
		}

		// Luego de crear todo, hacer que las cosas sean visibles o no de acuerdo a
		// quien ingresa a la lista.
		/*if (usuarioActual instanceof Doctor || usuarioActual instanceof Secretaria) {
			btnModificar.setVisible(false);
			btnEliminar.setVisible(false);
		}

		if (usuarioActual instanceof Administrador) {
			if (((Administrador) usuarioActual).getAutoridad() > 3) {
				btnModificar.setVisible(false);
				btnEliminar.setVisible(false);
			}
		}
		rellenarTabla("");*/
	}
	
	private void clear() {
		txtNombre.setText("");
		txtClasificacion.setText("");
		txtDescripcion.setText("");
		btnModificar.setEnabled(false);
		btnEliminar.setEnabled(false);
	}
	
	public static void sclear() {
		txtNombre.setText(categoriaModificar.getNombre());
		txtClasificacion.setText(categoriaModificar.getClasificacion());
		txtDescripcion.setText(categoriaModificar.getDescripcion());
	}
	
	private void rellenarDatos() {
		conexion = JDBCPostgreSQLConnect.conectar();
		try {
			preparedStatement = conexion.prepareStatement("SELECT * FROM categoria");
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				txtNombre.setText(resultSet.getString("nombre"));
				txtDescripcion.setText(resultSet.getString("descripcion"));
				txtClasificacion.setText(resultSet.getString("clasificacion"));
			} else {
				JOptionPane.showMessageDialog(null, "Error al cargar datos.", "Advertencia.", JOptionPane.WARNING_MESSAGE);
			}
			conexion.close();
			
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	
	public static void rellenarTabla(){
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		
		conexion = JDBCPostgreSQLConnect.conectar();
		try {
			for (int i = 0; i < Juego.getInstance().getCategorias().size(); i++) {
				preparedStatement = conexion.prepareStatement("SELECT * FROM categoria WHERE nombre = ?");
				preparedStatement.setString(1, txtNombre.getText());
				resultSet = preparedStatement.executeQuery();
				
				if(resultSet.next()) {
					if (Juego.getInstance().getCategorias().get(i).isListar()) {	// Saber si se puede listar o no.
						row[0] = resultSet.getString("nombre");
						row[1] = resultSet.getString("descripcion");
						row[2] = resultSet.getString("clasificacion");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Error al cargar datos.", "Advertencia.", JOptionPane.WARNING_MESSAGE);
				}
				conexion.close();
			}	
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
}
