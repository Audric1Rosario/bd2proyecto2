package visual.Premio;

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

public class CrearPremio extends JDialog {

	private final JPanel contentPanel = new JPanel();
	// Textbox
	private JTextField txtNombrePremio;
	private JTextField txtPuntuacion;
	private JTextField txtTicket;
	
	// variables logicas.
	//private Premio modificar;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearPremio frame = new CrearPremio();
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
	public CrearPremio(/*Premio modificar*/) {
		setResizable(false);
		//this.modificar = modificar;

		//if (modificar == null)
			setTitle("Crear categor\u00EDa");
		//else 
		//	setTitle("Modificar premio");
		setIconImage(Toolkit.getDefaultToolkit().getImage(CrearPremio.class.getResource("/image/game-controller.png")));
		setBounds(100, 100, 480, 187);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 456, 103);
		contentPanel.add(panel);
		panel.setLayout(null);

		txtNombrePremio = new JTextField();
		txtNombrePremio.setBounds(196, 11, 238, 20);

		panel.add(txtNombrePremio);
		txtNombrePremio.setColumns(10);

		JLabel lblNombrePremio = new JLabel("Nombre del Premio: ");
		lblNombrePremio.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNombrePremio.setBounds(10, 14, 176, 14);
		panel.add(lblNombrePremio);

		JPanel pnlClasificacion = new JPanel();
		pnlClasificacion.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Puntuaci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlClasificacion.setBounds(10, 42, 197, 48);
		panel.add(pnlClasificacion);
		pnlClasificacion.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		pnlClasificacion.add(scrollPane, BorderLayout.CENTER);

		txtPuntuacion = new JTextField();
		scrollPane.setViewportView(txtPuntuacion);
		
		JPanel pnlTicket = new JPanel();
		pnlTicket.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Tickets Requeridos", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlTicket.setBounds(237, 42, 197, 48);
		panel.add(pnlTicket);
		pnlTicket.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPaneTicket = new JScrollPane();
		scrollPaneTicket.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		pnlTicket.add(scrollPaneTicket, BorderLayout.CENTER);
		
		txtTicket = new JTextField();
		scrollPaneTicket.setViewportView(txtTicket);
		txtTicket.setColumns(10);
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
						if (txtNombrePremio.getText().equals("")) {
							JOptionPane.showMessageDialog(null, "Por favor, escriba el nombre de la premio.", "Advertencia.", JOptionPane.WARNING_MESSAGE);
							return;
						}

						if (txtPuntuacion.getText().equals("")) {
							JOptionPane.showMessageDialog(null, "Por favor, escriba los síntomas que presenta esta premio.", "Advertencia.", JOptionPane.WARNING_MESSAGE);
							return;
						}

						// Verificación de que no se repite el nombre de la premio.
						// Revisar que no se repita
						/*if (modificar == null) {
							if (Clinica.getInstance().verificarPremio(txtNombrePremio.getText()) == false) {
								JOptionPane.showMessageDialog(null, "El nombre de la premio ya existe, favor coloque uno nuevo.", "Advertencia.", JOptionPane.WARNING_MESSAGE);
								return;
							} 
						}*/
						/*
						if (modificar != null) {							
							modificar.setClasificacion(txtClasificacion.getText());
							modificar.setDescripcion(txtDescripcion.getText());
							ListaPremioes.rellenarTabla(txtNombrePremio.getText());
							ListaPremioes.sclear();
							JOptionPane.showMessageDialog(null, "Premio modificada exitosamente.", 
									"Modificar premio", JOptionPane.INFORMATION_MESSAGE);
							dispose();
						} else {
							// Primero buscar si hay una premio que tenga el mismo nombre guardada
							// Esto significa que solo hay que volver a activar una premio
							Premio activar = null;
							boolean volverActivar = false; 
							int aux = 0;
							while (!volverActivar && aux < Clinica.getInstance().getPremioes().size()) {
								if (!Clinica.getInstance().getPremioes().get(aux).isListar()) { // Premioes desactivadas.
									if (Clinica.getInstance().getPremioes().get(aux).getNombre().equalsIgnoreCase(txtNombrePremio.getText())) {
										// Significa que no es una creación, sólo se vuelve a activar y a cambiar los datos de una premio que ya existe.
										volverActivar = true;
										activar = Clinica.getInstance().getPremioes().get(aux);
									}
								}
								aux++;
							}

							if (!volverActivar) {	// Si no estaba desactivada, crear premio
								Premio nueva = new Premio(txtNombrePremio.getText(), txtClasificacion.getText(), txtDescripcion.getText());
								JOptionPane.showMessageDialog(null, "Premio creada exitosamente.", 
										"Crear premio", JOptionPane.INFORMATION_MESSAGE);
								Clinica.getInstance().addPremio(nueva); 
							} else { // De lo contrario
								activar.setClasificacion(txtClasificacion.getText());
								activar.setDescripcion(txtDescripcion.getText());
								activar.setListar(true);   // Esta premio vuelve a estar activa.
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
