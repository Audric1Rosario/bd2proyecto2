package visual.Categorias;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.Toolkit;
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultCaret;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;


public class CrearCategoria extends JDialog {

	private final JPanel contentPanel = new JPanel();
	// Textbox
	private JTextField txtNombreCategoria;
	// Text areas
	private JTextArea txtDescripcion;
	private JTextField txtClasificacion;
	
	// variables logicas.
	//private Categoria modificar;
	/**
	 * Launch the application.
	 *//*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearCategoria frame = new CrearCategoria();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the dialog.
	 */
	public CrearCategoria(/*Categoria modificar*/) {
		setResizable(false);
		//this.modificar = modificar;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		//if (modificar == null)
			setTitle("Crear categor\u00EDa");
		//else 
		//	setTitle("Modificar categoria");
		setIconImage(Toolkit.getDefaultToolkit().getImage(CrearCategoria.class.getResource("/image/game-controller.png")));
		setBounds(100, 100, 480, 304);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 444, 220);
		contentPanel.add(panel);
		panel.setLayout(null);

		txtNombreCategoria = new JTextField();
		txtNombreCategoria.setBounds(196, 11, 238, 20);

		panel.add(txtNombreCategoria);
		txtNombreCategoria.setColumns(10);

		JLabel lblNombreDeLa = new JLabel("Nombre de la categor\u00EDa: ");
		lblNombreDeLa.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNombreDeLa.setBounds(10, 14, 176, 14);
		panel.add(lblNombreDeLa);

		JPanel pnlClasificacion = new JPanel();
		pnlClasificacion.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Clasificaci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlClasificacion.setBounds(10, 42, 424, 48);
		panel.add(pnlClasificacion);
		pnlClasificacion.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		pnlClasificacion.add(scrollPane, BorderLayout.CENTER);

		txtClasificacion = new JTextField();
		scrollPane.setViewportView(txtClasificacion);

		JPanel pnlDescripcion = new JPanel();
		pnlDescripcion.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Descripci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlDescripcion.setBounds(10, 100, 424, 110);
		panel.add(pnlDescripcion);
		pnlDescripcion.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		pnlDescripcion.add(scrollPane_1, BorderLayout.CENTER);

		txtDescripcion = new JTextArea();
		txtDescripcion.setLineWrap(true);
		txtDescripcion.setWrapStyleWord(true);
		scrollPane_1.setViewportView(txtDescripcion);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnAceptar = new JButton("Aceptar");
				btnAceptar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						// Verificación de que hay datos.
						if (txtNombreCategoria.getText().equals("")) {
							JOptionPane.showMessageDialog(null, "Por favor, escriba el nombre de la categoria.", "Advertencia.", JOptionPane.WARNING_MESSAGE);
							return;
						}

						if (txtClasificacion.getText().equals("")) {
							JOptionPane.showMessageDialog(null, "Por favor, escriba los síntomas que presenta esta categoria.", "Advertencia.", JOptionPane.WARNING_MESSAGE);
							return;
						}

						// Verificación de que no se repite el nombre de la categoria.
						// Revisar que no se repita
						/*if (modificar == null) {
							if (Clinica.getInstance().verificarCategoria(txtNombreCategoria.getText()) == false) {
								JOptionPane.showMessageDialog(null, "El nombre de la categoria ya existe, favor coloque uno nuevo.", "Advertencia.", JOptionPane.WARNING_MESSAGE);
								return;
							} 
						}*/
						/*
						if (modificar != null) {							
							modificar.setClasificacion(txtClasificacion.getText());
							modificar.setDescripcion(txtDescripcion.getText());
							ListaCategoriaes.rellenarTabla(txtNombreCategoria.getText());
							ListaCategoriaes.sclear();
							JOptionPane.showMessageDialog(null, "Categoria modificada exitosamente.", 
									"Modificar categoria", JOptionPane.INFORMATION_MESSAGE);
							dispose();
						} else {
							// Primero buscar si hay una categoria que tenga el mismo nombre guardada
							// Esto significa que solo hay que volver a activar una categoria
							Categoria activar = null;
							boolean volverActivar = false; 
							int aux = 0;
							while (!volverActivar && aux < Clinica.getInstance().getCategoriaes().size()) {
								if (!Clinica.getInstance().getCategoriaes().get(aux).isListar()) { // Categoriaes desactivadas.
									if (Clinica.getInstance().getCategoriaes().get(aux).getNombre().equalsIgnoreCase(txtNombreCategoria.getText())) {
										// Significa que no es una creación, sólo se vuelve a activar y a cambiar los datos de una categoria que ya existe.
										volverActivar = true;
										activar = Clinica.getInstance().getCategoriaes().get(aux);
									}
								}
								aux++;
							}

							if (!volverActivar) {	// Si no estaba desactivada, crear categoria
								Categoria nueva = new Categoria(txtNombreCategoria.getText(), txtClasificacion.getText(), txtDescripcion.getText());
								JOptionPane.showMessageDialog(null, "Categoria creada exitosamente.", 
										"Crear categoria", JOptionPane.INFORMATION_MESSAGE);
								Clinica.getInstance().addCategoria(nueva); 
							} else { // De lo contrario
								activar.setClasificacion(txtClasificacion.getText());
								activar.setDescripcion(txtDescripcion.getText());
								activar.setListar(true);   // Esta categoria vuelve a estar activa.
							}
							clear();
						}			*/							
					}
				});
				btnAceptar.setActionCommand("OK");
				buttonPane.add(btnAceptar);
				getRootPane().setDefaultButton(btnAceptar);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
	}
}
