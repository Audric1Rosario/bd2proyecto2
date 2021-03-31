package visual.Perfil;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VerPerfil extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	private JList lstPremios;
	private JTextField txtNombre;
	private JTextField txtPuntos;
	private JTextField txtMonedas;
	private JTextField textField;
	
	/**
	 * Launch the application.
	 *//*
	public static void main(String[] args) {
		try {
			VerPerfil dialog = new VerPerfil();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public VerPerfil() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 480, 280);
		setTitle("Perfil");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VerPerfil.class.getResource("/image/game-controller.png")));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Premios Adquiridos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(10, 10, 197, 192);
			contentPanel.add(panel);
			panel.setLayout(new BorderLayout(0, 0));
			{
				lstPremios = new JList();
				panel.add(lstPremios, BorderLayout.CENTER);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Informaci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(217, 10, 239, 192);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblNombre = new JLabel("Nombre");
				lblNombre.setBounds(26, 23, 50, 15);
				panel.add(lblNombre);
			}
			{
				txtNombre = new JTextField();
				txtNombre.setBounds(86, 20, 143, 19);
				txtNombre.setEditable(false);
				txtNombre.setEnabled(false);
				panel.add(txtNombre);
				txtNombre.setColumns(10);
			}
			{
				JLabel lblPuntos = new JLabel("Puntos");
				lblPuntos.setBounds(26, 63, 50, 15);
				panel.add(lblPuntos);
			}
			{
				txtPuntos = new JTextField();
				txtPuntos.setEnabled(false);
				txtPuntos.setEditable(false);
				txtPuntos.setColumns(10);
				txtPuntos.setBounds(86, 61, 143, 19);
				panel.add(txtPuntos);
			}
			{
				JLabel lblMonedas = new JLabel("Monedas");
				lblMonedas.setBounds(26, 103, 50, 15);
				panel.add(lblMonedas);
			}
			{
				txtMonedas = new JTextField();
				txtMonedas.setEnabled(false);
				txtMonedas.setEditable(false);
				txtMonedas.setColumns(10);
				txtMonedas.setBounds(86, 101, 143, 19);
				panel.add(txtMonedas);
			}
			{
				JLabel lblPuesto = new JLabel("Rango");
				lblPuesto.setHorizontalAlignment(SwingConstants.CENTER);
				lblPuesto.setBounds(10, 138, 219, 15);
				panel.add(lblPuesto);
			}
			{
				textField = new JTextField();
				textField.setEnabled(false);
				textField.setEditable(false);
				textField.setColumns(10);
				textField.setBounds(10, 163, 219, 19);
				panel.add(textField);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnCerrar = new JButton("Cancel");
				btnCerrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCerrar.setActionCommand("Cerrar");
				buttonPane.add(btnCerrar);
			}
		}
	}

}
