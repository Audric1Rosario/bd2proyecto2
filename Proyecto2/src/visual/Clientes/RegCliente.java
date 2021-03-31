package visual.Clientes;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.UIManager;
import java.awt.GridBagLayout;
import java.util.Calendar;

import javax.swing.*;
import java.awt.Color;

public class RegCliente extends JDialog {

	private JPanel contentPane;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtName;
	private JTextField txtPhone;
	private JTextField txtCellphone;
	private JTextField txtUsuario;
	private JTextField txtClave;
	private JTextField txtRegion;
	private JTextField txtEmail;

	// Lista
	/*private JList<String> lstCliente;
	private JList<String> lstSistema;*/

	// Radio buttons
	private JRadioButton rdbtnMasculino;
	private JRadioButton rdbtnFemenino;

	// Combo box
	private JComboBox<String> cbxPais;

	// Botones:
	private JButton btnAceptar;
	private JButton cancelButton;

	// Spinner
	private JSpinner spnAge;

	// variables logicas
	private String screenPath;
	private MaskFormatter mask;
	
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegCliente frame = new RegCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public RegCliente(/*Cliente Cliente*/) throws ParseException{
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		// this.ClienteModificar = Cliente;
		setTitle("Registrar Cliente.");
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegCliente.class.getResource("/image/game-controller.png")));
		setBounds(100, 100, 560, 340);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos del Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 527, 248);
		contentPanel.add(panel);
		panel.setLayout(null);
		{
			JLabel lblNombre = new JLabel("Nombre:");
			lblNombre.setBounds(15, 30, 58, 14);
			panel.add(lblNombre);
		}

		txtName = new JTextField();
		txtName.setBounds(77, 27, 424, 20);
		panel.add(txtName);
		txtName.setColumns(10);
		{
			JLabel lblGenero = new JLabel("G\u00E9nero:");
			lblGenero.setBounds(15, 63, 58, 14);
			panel.add(lblGenero);
		}
		rdbtnMasculino = new JRadioButton("M");
		rdbtnMasculino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnMasculino.setSelected(true);
				rdbtnFemenino.setSelected(false);
			}
		});
		rdbtnMasculino.setBounds(77, 63, 46, 14);
		panel.add(rdbtnMasculino);
		rdbtnMasculino.setSelected(true);

		rdbtnFemenino = new JRadioButton("F");
		rdbtnFemenino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnMasculino.setSelected(false);
				rdbtnFemenino.setSelected(true);
			}
		});
		rdbtnFemenino.setBounds(125, 63, 46, 14);

		panel.add(rdbtnFemenino);

		JLabel lblEdad = new JLabel("Fecha de Nacimiento:");
		lblEdad.setBounds(177, 63, 109, 14);
		panel.add(lblEdad);
		
		Date today = new Date();		
		spnAge = new JSpinner(new SpinnerDateModel(today, null, null, Calendar.MONTH));
		JSpinner spinner2 = new JSpinner(new SpinnerDateModel(today, null, null, Calendar.MONTH));
	    JSpinner.DateEditor editor = new JSpinner.DateEditor(spnAge, "dd/MM/yy");
		spnAge.setEditor(editor);
		spnAge.setBounds(296, 61, 133, 20);
		panel.add(spnAge);

		JLabel lblTelefono = new JLabel("Tel\u00E9fono:");
		lblTelefono.setBounds(15, 95, 58, 14);
		panel.add(lblTelefono);

		mask = new MaskFormatter("(###) ###-####");
		txtPhone = new JFormattedTextField(mask);
		txtPhone.setBounds(80, 92, 176, 20);
		panel.add(txtPhone);
		txtPhone.setColumns(10);

		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(15, 124, 58, 14);
		panel.add(lblUsuario);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(80, 121, 176, 20);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);

		JLabel lblClave = new JLabel("Clave:");
		lblClave.setBounds(275, 124, 46, 14);
		panel.add(lblClave);

		txtClave = new JTextField();
		txtClave.setBounds(325, 123, 176, 20);
		panel.add(txtClave);
		txtClave.setColumns(10);

		JLabel lblPais = new JLabel("Pais:");
		lblPais.setBounds(15, 156, 58, 14);
		panel.add(lblPais);

		cbxPais = new JComboBox<String>();
		cbxPais.setModel(new DefaultComboBoxModel<String>(new String[] {"DOM", "HTI", "USA", "CAN", "MEX", "COL", "CHL", "VEN"}));

		cbxPais.setBounds(80, 153, 301, 20);
		panel.add(cbxPais);

		JLabel lblRegion = new JLabel("Region:");
		lblRegion.setBounds(15, 187, 58, 14);
		panel.add(lblRegion);

		txtRegion = new JTextField();
		txtRegion.setBounds(80, 184, 393, 20);
		panel.add(txtRegion);
		txtRegion.setColumns(10);

		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(15, 214, 58, 14);
		panel.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setBounds(80, 211, 310, 20);
		panel.add(txtEmail);
		txtEmail.setColumns(10);


		{
			JPanel btnCerrar = new JPanel();
			btnCerrar.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			btnCerrar.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(btnCerrar, BorderLayout.SOUTH);
			{
				btnAceptar = new JButton("Aceptar");
				btnAceptar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(txtName.getText().equalsIgnoreCase("")) {
							JOptionPane.showMessageDialog(null, "Nombre del Cliente vacio", "Notificación", JOptionPane.INFORMATION_MESSAGE);
							return;
						}

						if(txtPhone.getText().equalsIgnoreCase("")) {
							JOptionPane.showMessageDialog(null, "Telefono del Cliente vacio","Notificacion", JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						if(txtCellphone.getText().equalsIgnoreCase("")) {
							JOptionPane.showMessageDialog(null, "Celular del Cliente vacio","Notificacion", JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						if(txtRegion.getText().equalsIgnoreCase("")) {
							JOptionPane.showMessageDialog(null, "Region del Cliente vacio","Notificacion", JOptionPane.INFORMATION_MESSAGE);
							return;
						}					
/*
						Enfermedad buscar;
						if (ClienteModificar == null) { 						
							Cliente aux = new Cliente( txtName.getText(), txtClave.getText(), txtUsuario.getText(),Integer.valueOf(spnAge.getValue().toString()),
									rdbtnMasculino.isSelected() ? "M" : "F", rdbtnSoltero.isSelected() ? "Soltero" : "Casado", cbxBlood.getSelectedItem().toString(), 
											Float.valueOf(spnHeight.getValue().toString()), txtRegion.getText(), cbxPais.getSelectedItem().toString(),txtPhone.getText(),
											txtCellphone.getText(), txtEmail.getText(), enfermedadesSelec);
							Clinica.getInstance().addCliente(aux);	

							JOptionPane.showMessageDialog(null, "Cliente agregado.","Notificación.", JOptionPane.INFORMATION_MESSAGE);
							// Saber a cuales enfermedades se le esta agregando...							
							for (String enfer : enfermedadesSelec) {
								buscar = Clinica.getInstance().buscarEnfermedadByNombre(enfer);
								buscar.setCantClientes(buscar.getCantClientes() + 1);  	// Sumar a la cantidad de Clientes.
							}

							clear();
							iniciarLista();
							Dashboard.cargarEnferCrit();
							Dashboard.cargarEdades();
							Dashboard.cargarSangre();
						} else {
							// Agregar los datos al Cliente a modificar.
							ClienteModificar.setNombre(txtName.getText());
							ClienteModificar.setClave(txtClave.getText());
							ClienteModificar.setUsuario(txtUsuario.getText());
							ClienteModificar.setEdad(Integer.valueOf(spnAge.getValue().toString()));
							ClienteModificar.setSexo(rdbtnMasculino.isSelected() ? "M" : "F");
							ClienteModificar.setEstadoCivil(rdbtnSoltero.isSelected() ? "Soltero" : "Casado");
							ClienteModificar.setTipoSangre(cbxBlood.getSelectedItem().toString());
							ClienteModificar.setEstatura(Float.valueOf(spnHeight.getValue().toString()));
							ClienteModificar.setRegion(txtRegion.getText());
							ClienteModificar.setPais(cbxPais.getSelectedItem().toString());
							ClienteModificar.setTelefono(txtPhone.getText());
							ClienteModificar.setCelular(txtCellphone.getText());
							ClienteModificar.setEmail(txtEmail.getText());

							// Saber a cuales enfermedades se les está quitando y sumando.					
							int index;
							for (String enfer : enfermedadesSelec) {
								if (enfermedadesModi.indexOf(enfer) != -1) { // Significa que se queda igual y se puede poner que no se modifico quitandolo
									index = enfermedadesModi.indexOf(enfer);
									enfermedadesModi.remove(index);
								} else {	// Se agrega por primera vez
									buscar = Clinica.getInstance().buscarEnfermedadByNombre(enfer);
									buscar.setCantClientes(buscar.getCantClientes() + 1);
								}
							}
							// Ahora lo que quede en modi, sacarlo.
							for (String enfer : enfermedadesModi) {
								buscar = Clinica.getInstance().buscarEnfermedadByNombre(enfer);
								buscar.setCantClientes(buscar.getCantClientes() - 1);
							}
							ClienteModificar.setEnfermedades(enfermedadesSelec);							
							BuscarCliente.loadTable(Clinica.getInstance().buscarClienteByNombre(ClienteModificar.getNombre()), ClienteModificar, false);							
							dispose();
							JOptionPane.showMessageDialog(null, "Cliente modificado.","Notificación.", JOptionPane.INFORMATION_MESSAGE);
						}
						Dashboard.cargarEnferCrit();
						Dashboard.cargarEdades();
						Dashboard.cargarSangre();
						Dashboard.cargarVacunacion();*/

					}});
				btnAceptar.setActionCommand("OK");
				btnCerrar.add(btnAceptar);
				getRootPane().setDefaultButton(btnAceptar);

			}
			{
				cancelButton = new JButton("Cerrar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				btnCerrar.add(cancelButton);
			}
		}
	}
}
