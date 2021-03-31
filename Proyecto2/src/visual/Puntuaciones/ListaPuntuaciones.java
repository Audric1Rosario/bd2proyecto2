package visual.Puntuaciones;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import java.awt.BorderLayout;
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

public class ListaPuntuaciones extends JDialog {

	private final JPanel contentPanel = new JPanel();

	// Tablas
	private static JTable tableListaPuntuaciones;
	private static DefaultTableModel model;
	private static Object row[];

	// Text-Field
	private static JTextField txtNombre;
	private static JTextField txtBuscar;

	// Botones.
	private JButton btnBuscar;
	private JButton btnCerrar;
	private JTextField txtApellido;
	private JTextField txtUsuario;
	private JTextField txtClave;
	private JTextField txtCorreo;

	/**
	 * Launch the application.
	 *//*
	public static void main(String[] args) {
		try {
			ListaPuntuaciones dialog = new ListaPuntuaciones();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public ListaPuntuaciones(/* Empleado usuarioActual */) {
		// this.usuarioActual = usuarioActual;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("Puntuaciones");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListaPuntuaciones.class.getResource("/image/game-controller.png")));
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

		String[] headers = { "Nombre", "Apellido", "Puntuación" };
		model = new DefaultTableModel();
		model.setColumnIdentifiers(headers);
		tableListaPuntuaciones = new JTable() {
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			}
		};
		tableListaPuntuaciones.setModel(model);
		tableListaPuntuaciones.getTableHeader().setResizingAllowed(false);
		tableListaPuntuaciones.getTableHeader().setReorderingAllowed(false);
		/*
		 * tableListaPuntuaciones.getSelectionModel().addListSelectionListener(new
		 * ListSelectionListener(){ public void valueChanged(ListSelectionEvent event) {
		 * if (tableListaPuntuaciones.getSelectedRow() != -1) { enfermedadModificar =
		 * Clinica.getInstance().buscarEnfermedadByNombre(tableListaPuntuaciones.getValueAt(
		 * tableListaPuntuaciones.getSelectedRow(), 0).toString()); rellenarDatos();
		 * 
		 * if (usuarioActual instanceof Administrador) { if
		 * (((Administrador)usuarioActual).getAutoridad() <= 3) {
		 * btnModificar.setEnabled(true); btnEliminar.setEnabled(true); } } } } });
		 */
		scrollPane.setViewportView(tableListaPuntuaciones);
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

			JLabel lblApellido = new JLabel("Apellido");
			lblApellido.setBounds(10, 79, 232, 14);
			panel_1.add(lblApellido);

			JLabel lblUsuario = new JLabel("Usuario");
			lblUsuario.setBounds(10, 137, 232, 14);
			panel_1.add(lblUsuario);

			txtApellido = new JTextField();
			txtApellido.setEditable(false);
			txtApellido.setColumns(10);
			txtApellido.setBounds(10, 104, 232, 20);
			panel_1.add(txtApellido);

			txtUsuario = new JTextField();
			txtUsuario.setEditable(false);
			txtUsuario.setColumns(10);
			txtUsuario.setBounds(10, 162, 232, 20);
			panel_1.add(txtUsuario);

			JLabel lblClave = new JLabel("Clave");
			lblClave.setBounds(10, 195, 232, 14);
			panel_1.add(lblClave);

			txtClave = new JTextField();
			txtClave.setEditable(false);
			txtClave.setColumns(10);
			txtClave.setBounds(10, 220, 232, 20);
			panel_1.add(txtClave);

			JLabel lblCorreo = new JLabel("Clave");
			lblCorreo.setBounds(10, 253, 232, 14);
			panel_1.add(lblCorreo);

			txtCorreo = new JTextField();
			txtCorreo.setEditable(false);
			txtCorreo.setColumns(10);
			txtCorreo.setBounds(10, 278, 232, 20);
			panel_1.add(txtCorreo);
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
			 * actionPerformed(ActionEvent e) { clear(); tableListaPuntuaciones.clearSelection();
			 * rellenarTabla(txtBuscar.getText()); } });
			 */
			btnBuscar.setIcon(new ImageIcon(ListaPuntuaciones.class.getResource("/image/magnifying-glass.png")));
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
			btnRefresh.setIcon(new ImageIcon(ListaPuntuaciones.class.getResource("/image/reload.png")));
			btnRefresh.setBounds(368, 23, 40, 23);
			panel_1.add(btnRefresh);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
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
