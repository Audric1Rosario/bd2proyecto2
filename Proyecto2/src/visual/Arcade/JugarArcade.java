package visual.Arcade;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JList;

public class JugarArcade extends JDialog {

private final JPanel contentPanel = new JPanel();
	
	private JList lstArcades;
	private JTextField txtMonedasReq;
	private JTextField txtMonedas;
	private JTextField txtTicket;
	private JTextField textField;

	/**
	 * Launch the application.
	 *//*
	public static void main(String[] args) {
		try {
			JugarArcade dialog = new JugarArcade();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public JugarArcade() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 480, 280);
		setTitle("Jugar Arcade");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(JugarArcade.class.getResource("/image/game-controller.png")));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Tablero", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(10, 10, 197, 192);
			contentPanel.add(panel);
			panel.setLayout(new BorderLayout(0, 0));
			{
				lstArcades = new JList();
				panel.add(lstArcades, BorderLayout.CENTER);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Informaci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(217, 10, 239, 192);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblNombre = new JLabel("Monedas Requeridas");
				lblNombre.setBounds(10, 20, 219, 15);
				panel.add(lblNombre);
			}
			{
				txtMonedasReq = new JTextField();
				txtMonedasReq.setBounds(10, 42, 219, 19);
				txtMonedasReq.setEditable(false);
				txtMonedasReq.setEnabled(false);
				panel.add(txtMonedasReq);
				txtMonedasReq.setColumns(10);
			}
			{
				JLabel lblMonedas = new JLabel("Monedas");
				lblMonedas.setBounds(10, 84, 66, 15);
				panel.add(lblMonedas);
			}
			{
				txtMonedas = new JTextField();
				txtMonedas.setEnabled(false);
				txtMonedas.setEditable(false);
				txtMonedas.setColumns(10);
				txtMonedas.setBounds(86, 82, 143, 19);
				panel.add(txtMonedas);
			}
			{
				JLabel lblTickets = new JLabel("Tickets");
				lblTickets.setBounds(10, 111, 66, 15);
				panel.add(lblTickets);
			}
			{
				txtTicket = new JTextField();
				txtTicket.setEnabled(false);
				txtTicket.setEditable(false);
				txtTicket.setColumns(10);
				txtTicket.setBounds(86, 109, 143, 19);
				panel.add(txtTicket);
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
				JButton btnJugar = new JButton("Jugar");
				btnJugar.setActionCommand("OK");
				buttonPane.add(btnJugar);
				getRootPane().setDefaultButton(btnJugar);
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
