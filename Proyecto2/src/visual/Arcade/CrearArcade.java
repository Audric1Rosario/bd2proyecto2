package visual.Arcade;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTextField;

public class CrearArcade extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;

	/**
	 * Launch the application.
	 *//*
	public static void main(String[] args) {
		try {
			CrearArcade dialog = new CrearArcade();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public CrearArcade() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Crear Arcade");
		setBounds(100, 100, 420, 230);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Crear Arcade", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel);
			panel.setLayout(null);
			
			JLabel lblNombreArcade = new JLabel("Nombre: ");
			lblNombreArcade.setBounds(10, 35, 75, 15);
			panel.add(lblNombreArcade);
			
			JLabel lblTicket = new JLabel("Ticket: ");
			lblTicket.setBounds(10, 75, 75, 15);
			panel.add(lblTicket);
			
			JLabel lblCategoria = new JLabel("Categor\u00EDa");
			lblCategoria.setBounds(10, 115, 75, 15);
			panel.add(lblCategoria);
			
			JComboBox cbxCategoria = new JComboBox();
			cbxCategoria.setBounds(95, 110, 250, 25);
			panel.add(cbxCategoria);
			
			JSpinner spnTicket = new JSpinner();
			spnTicket.setBounds(95, 70, 200, 25);
			panel.add(spnTicket);
			
			txtNombre = new JTextField();
			txtNombre.setBounds(95, 30, 250, 25);
			panel.add(txtNombre);
			txtNombre.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnAgregar = new JButton("Agregar");
				btnAgregar.setActionCommand("OK");
				buttonPane.add(btnAgregar);
				getRootPane().setDefaultButton(btnAgregar);
			}
			{
				JButton btnCerrar = new JButton("Cerrar");
				btnCerrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCerrar.setActionCommand("Cancel");
				buttonPane.add(btnCerrar);
			}
		}
	}
}
