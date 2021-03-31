package visual.Arcade;

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

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;


//import visual.Administracion.ListaArcades;

public class ListaArcades extends JDialog {

	private final JPanel contentPanel = new JPanel();

	// Tablas
	private static JTable tableListaArcades;
	private static DefaultTableModel model;
	private static Object row[];

	// Text-Field
	private static JTextField txtNombre;
	private static JTextField txtBuscar;

	// Botones.
	private JButton btnBuscar;
	private static JButton btnModificar;
	private static JButton btnEliminar;
	private JButton btnCerrar;
	private JSpinner spnTicket;
	private JSpinner spnMonedas;

	/**
	 * Launch the application.
	 *//*
	public static void main(String[] args) {
		try {
			ListaArcades dialog = new ListaArcades();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public ListaArcades(/* Empleado usuarioActual */) {
		// this.usuarioActual = usuarioActual;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("Control de Arcades");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListaArcades.class.getResource("/image/game-controller.png")));
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

		String[] headers = { "Nombre", "Cantidad de Ticket", "Monedas Requeridas"};
		model = new DefaultTableModel();
		model.setColumnIdentifiers(headers);
		tableListaArcades = new JTable() {
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			}
		};
		tableListaArcades.setModel(model);
		tableListaArcades.getTableHeader().setResizingAllowed(false);
		tableListaArcades.getTableHeader().setReorderingAllowed(false);
		/*
		 * tableListaArcades.getSelectionModel().addListSelectionListener(new
		 * ListSelectionListener(){ public void valueChanged(ListSelectionEvent event) {
		 * if (tableListaArcades.getSelectedRow() != -1) { enfermedadModificar =
		 * Clinica.getInstance().buscarEnfermedadByNombre(tableListaArcades.getValueAt(
		 * tableListaArcades.getSelectedRow(), 0).toString()); rellenarDatos();
		 * 
		 * if (usuarioActual instanceof Administrador) { if
		 * (((Administrador)usuarioActual).getAutoridad() <= 3) {
		 * btnModificar.setEnabled(true); btnEliminar.setEnabled(true); } } } } });
		 */
		scrollPane.setViewportView(tableListaArcades);
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

			JLabel lblTicket = new JLabel("Cantidad de Ticket");
			lblTicket.setBounds(10, 79, 232, 14);
			panel_1.add(lblTicket);

			JLabel lblMonedas = new JLabel("Monedas requeridas");
			lblMonedas.setBounds(10, 137, 232, 14);
			panel_1.add(lblMonedas);
			
			spnTicket = new JSpinner();
			spnTicket.setModel(new SpinnerNumberModel(5, 5, 100, 1));
			spnTicket.setEnabled(false);
			spnTicket.setBounds(10, 104, 232, 20);
			panel_1.add(spnTicket);
			
			spnMonedas = new JSpinner();
			spnMonedas.setEnabled(false);
			spnMonedas.setBounds(10, 162, 232, 20);
			panel_1.add(spnMonedas);
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
			/*
			 * btnBuscar.addActionListener(new ActionListener() { public void
			 * actionPerformed(ActionEvent e) { clear(); tableListaArcades.clearSelection();
			 * rellenarTabla(txtBuscar.getText()); } });
			 */
			btnBuscar.setIcon(new ImageIcon(ListaArcades.class.getResource("/image/magnifying-glass.png")));
			btnBuscar.setBounds(318, 23, 40, 23);
			panel_1.add(btnBuscar);

			txtBuscar = new JTextField();
			txtBuscar.setBounds(95, 24, 213, 20);
			panel_1.add(txtBuscar);
			txtBuscar.setColumns(10);

			JButton btnRefresh = new JButton("");
			btnRefresh.setToolTipText("Recargar");
			/*
			 * btnRefresh.addActionListener(new ActionListener() { public void
			 * actionPerformed(ActionEvent e) { clear(); txtBuscar.setText("");
			 * rellenarTabla(""); } });
			 */
			btnRefresh.setIcon(new ImageIcon(ListaArcades.class.getResource("/image/reload.png")));
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
				/*
				 * btnModificar.addActionListener(new ActionListener() { public void
				 * actionPerformed(ActionEvent e) { if (enfermedadModificar != null &&
				 * usuarioActual instanceof Administrador) { CrearEnfermedad ventana = new
				 * CrearEnfermedad(enfermedadModificar); ventana.setModal(true);
				 * ventana.setVisible(true); } } });
				 */
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
		}*/

		/*if (usuarioActual instanceof Administrador) {
			if (((Administrador) usuarioActual).getAutoridad() > 3) {
				btnModificar.setVisible(false);
				btnEliminar.setVisible(false);
			}
		}
		rellenarTabla("");*/
	}

}
