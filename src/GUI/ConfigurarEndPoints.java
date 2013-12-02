package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.AbstractListModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class ConfigurarEndPoints extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	protected static ConfigurarEndPoints CurrentDialog;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ConfigurarEndPoints dialog = new ConfigurarEndPoints();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ConfigurarEndPoints() {
		setTitle("Configurar EndPoints");
		ConfigurarEndPoints.CurrentDialog = this;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setForeground(new Color(30, 144, 255));
		contentPanel.setBackground(Color.DARK_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JList list = new JList();
			list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			list.setModel(new AbstractListModel() {
				String[] values = new String[] {" http://dbpedia.org/sparql"};
				public int getSize() {
					return values.length;
				}
				public Object getElementAt(int index) {
					return values[index];
				}
			});
			list.setBounds(414, 199, -393, -105);
			contentPanel.add(list);
		}
		{
			JLabel lblIngreseLosEndpoints = new JLabel("Ingrese los endpoints a los que desea conectarse:");
			lblIngreseLosEndpoints.setForeground(new Color(30, 144, 255));
			lblIngreseLosEndpoints.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblIngreseLosEndpoints.setBounds(10, 11, 389, 25);
			contentPanel.add(lblIngreseLosEndpoints);
		}
		{
			textField = new JTextField();
			textField.setBounds(10, 33, 312, 20);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(332, 32, 89, 23);
		contentPanel.add(btnAgregar);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.DARK_GRAY);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						
						//TODO: Save the endpoint
						ConfigurarEndPoints.CurrentDialog.dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						
						ConfigurarEndPoints.CurrentDialog.dispose();
					}
				});
				
				JButton btnBorrar = new JButton("Borrar");
				buttonPane.add(btnBorrar);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
