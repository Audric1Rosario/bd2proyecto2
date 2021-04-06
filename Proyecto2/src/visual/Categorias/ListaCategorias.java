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
import logic.JTextFieldLimit;
import logic.Juego;
import logic.modelos.Categoria;

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
	private static JTable tableCategorias;
	//private static JTable tableCategorias;
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
	private static JButton btnRefresh;
	
	static Connection conexion = null;
	static PreparedStatement preparedStatement = null;
	static ResultSet resultSet = null;

	// Variables lógicas.
	// private Empleado usuarioActual;
	 private static Categoria categoriaModificar;

	/**
	 * Launch the application.
	 */
	
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
		String[] headers = { "ID", "Nombre", "Clasificación", "Descripción" };
		model.setColumnIdentifiers(headers);
		
		tableCategorias = new JTable() {
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			}
		};
		tableCategorias = new JTable() {
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			}};
			tableCategorias.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tableCategorias.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
				public void valueChanged(ListSelectionEvent event) {
					if (tableCategorias.getSelectedRow() != -1) {
						int index = Integer.valueOf(tableCategorias.getValueAt(tableCategorias.getSelectedRow(), 0).toString());
						categoriaModificar = Juego.getInstance().buscarCategoriaPorId(index);
						rellenarDatos();
						
						btnModificar.setEnabled(true);
						btnEliminar.setEnabled(true);
						// Activar edicion...
						modificar(1);
					}
				}
			});
			tableCategorias.setModel(model);
			tableCategorias.getTableHeader().setResizingAllowed(false); 
			tableCategorias.getTableHeader().setReorderingAllowed(false);
			scrollPane.setViewportView(tableCategorias);
		
		tableCategorias.setModel(model);
		tableCategorias.getTableHeader().setResizingAllowed(false);
		tableCategorias.getTableHeader().setReorderingAllowed(false);
		}
		
		tableCategorias.getSelectionModel().addListSelectionListener(new
		ListSelectionListener(){ public void valueChanged(ListSelectionEvent event) {
			if (tableCategorias.getSelectedRow() != -1) { 
				categoriaModificar = Juego.getInstance().buscarCategoriaByNombre(
						tableCategorias.getValueAt(tableCategorias.getSelectedRow(), 0).toString()); 
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
		
		scrollPane.setViewportView(tableCategorias);
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
			txtNombre.setDocument(new JTextFieldLimit(19));
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
			txtClasificacion.setDocument(new JTextFieldLimit(5));
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
			txtDescripcion.setDocument(new JTextFieldLimit(255));
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
					if (txtBuscar.getText() != "") {
						clear(); 
						//tableCategorias.clearSelection();
						rellenarTabla(txtBuscar.getText(), false);
					}
				} 
			});
			btnBuscar.setIcon(new ImageIcon(ListaCategorias.class.getResource("/image/magnifying-glass.png")));
			btnBuscar.setBounds(318, 23, 40, 23);
			panel_1.add(btnBuscar);

			txtBuscar = new JTextField();
			txtBuscar.setBounds(95, 24, 213, 20);
			panel_1.add(txtBuscar);
			txtBuscar.setColumns(10);

			btnRefresh = new JButton("");
			btnRefresh.setToolTipText("Llenar");
			btnRefresh.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
			clear(); 
			
			if (btnRefresh.getToolTipText() == "Llenar") {
				txtBuscar.setText("");
				rellenarTabla("", true);
				btnRefresh.setToolTipText("Vaciar");
			} else {
				txtBuscar.setText("");
				clear();
				btnRefresh.setToolTipText("Llenar");
			}
			 
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
						Boolean isVoid = true;
						if (txtNombre.getText() != "") {
							if (txtClasificacion.getText() != "") {
								if (txtDescripcion.getText() != "") {
									isVoid = false;
									modificar(2); 
								}
							}
						}
						
						if (isVoid) {
							JOptionPane.showMessageDialog(null, "No puede dejar un campo vacío.", "Información.", JOptionPane.INFORMATION_MESSAGE);
						}
					} 
				} });
				
				btnModificar.setEnabled(false);
				btnModificar.setActionCommand("OK");
				buttonPane.add(btnModificar);

				getRootPane().setDefaultButton(btnModificar);
			}
			{
				btnEliminar = new JButton("Eliminar");
				btnEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Juego.getInstance().eliminarObjeto("categoria", categoriaModificar.getId_categoria());
						JOptionPane.showMessageDialog(null, "Se ha eliminado la categoría: " + categoriaModificar.getId_categoria() + ".", "Información.", JOptionPane.INFORMATION_MESSAGE);
						clear();
						rellenarTabla("", true);
					}
				});
				
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
		}*/
		//rellenarTabla();
	}
	
	private void clear() {
		model.setRowCount(0);
		
		txtNombre.setEnabled(false);
		txtClasificacion.setEnabled(false);
		txtDescripcion.setEnabled(false);
		
		txtNombre.setEditable(false);
		txtClasificacion.setEditable(false);
		txtDescripcion.setEditable(false);
		
		txtNombre.setText("");
		txtClasificacion.setText("");
		txtDescripcion.setText("");
		
		
		btnModificar.setEnabled(false);
		btnEliminar.setEnabled(false);
		
		categoriaModificar = null;
	}
	
	private static void setSelection() {
		txtNombre.setText(categoriaModificar.getNombre());
		txtDescripcion.setText(categoriaModificar.getDescripcion());
		txtClasificacion.setText(categoriaModificar.getClasificacion());
	}
	
	private void rellenarDatos() {
		if (categoriaModificar != null) {
			JOptionPane.showMessageDialog(null, "Puede modificar o eliminar esta categoría.", "Información.", JOptionPane.INFORMATION_MESSAGE);
			setSelection();
			modificar(1);
		}
	}
	
	private void modificar( int razon )  {
		
		switch (razon) {
		case 1: 
			txtNombre.setEnabled(true);
			txtClasificacion.setEnabled(true);
			txtDescripcion.setEnabled(true);
			
			txtNombre.setEditable(true);
			txtClasificacion.setEditable(true);
			txtDescripcion.setEditable(true);
			break;
			
		case 2:
			if (categoriaModificar != null) {
				// Cambiar los parametros originales para mandar a modificar...
				categoriaModificar.setNombre(txtNombre.getText());
				categoriaModificar.setClasificacion(txtClasificacion.getText());
				categoriaModificar.setDescripcion(txtDescripcion.getText());
				// Mandar a modificar... 
				Juego.getInstance().modificarObjeto("categoria", categoriaModificar, categoriaModificar.getId_categoria());
				// Avisar modificacion: 
				JOptionPane.showMessageDialog(null, "Se ha modificado la categoría: " + categoriaModificar.getId_categoria() + ".", "Información.", JOptionPane.INFORMATION_MESSAGE);
				// Clear 
				clear();
				// Rellenar tabla
				rellenarTabla("", true);
			}

			break;
			
		case 3:
			
			break;
			
		default: 
			clear();
			break;
		}
		
	}
	
	public static void rellenarTabla(String buscar, Boolean todo){
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		btnRefresh.setToolTipText("Vaciar");
		
		ArrayList<Categoria> categorias = Juego.getInstance().buscarCategoria(buscar, todo);
		
		for (int i = 0; i < categorias.size(); i++) {
			row[0] = categorias.get(i).getId_categoria();
			row[1] = categorias.get(i).getNombre();
			row[2] = categorias.get(i).getClasificacion();
			row[3] = categorias.get(i).getDescripcion();
			model.addRow(row);
		}
	}
}
