package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.ListModel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.AbstractListModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;

import common.Application;
import common.Logger;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dialog.ModalExclusionType;
import javax.swing.JSeparator;

public class AcercaDe extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	public static AcercaDe CurrentDialog;
	public JLabel lblIngreseLosEndpoints;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AcercaDe dialog = new AcercaDe();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @throws IOException 
	 */
	public AcercaDe() throws IOException {
		setResizable(false);
		setModal(true);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setTitle("Acerca de ...");
		AcercaDe.CurrentDialog = this;
		setBounds(100, 100, 452, 197);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setForeground(new Color(30, 144, 255));
		contentPanel.setBackground(Color.DARK_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);		
		{
			lblIngreseLosEndpoints = new JLabel("Universidad Nacional del Sur");
			lblIngreseLosEndpoints.setForeground(new Color(30, 144, 255));
			lblIngreseLosEndpoints.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblIngreseLosEndpoints.setBounds(126, 11, 187, 25);
			contentPanel.add(lblIngreseLosEndpoints);
		}
		
		JLabel lblIdioma = new JLabel("Fundamentos de la Web Semantica - 2\u00B0 Cuatrimestre del 2013");
		lblIdioma.setForeground(new Color(30, 144, 255));
		lblIdioma.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblIdioma.setBounds(22, 34, 389, 25);
		contentPanel.add(lblIdioma);
		{
			JLabel lblTrabajoFinal = new JLabel("Trabajo Final - Caramello, Leonardo Jose (LU:83767)");
			lblTrabajoFinal.setForeground(new Color(30, 144, 255));
			lblTrabajoFinal.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblTrabajoFinal.setBounds(63, 58, 319, 25);
			contentPanel.add(lblTrabajoFinal);
		}
		{
			JSeparator separator = new JSeparator();
			separator.setForeground(new Color(30, 144, 255));
			separator.setBackground(new Color(30, 144, 255));
			separator.setBounds(22, 122, 408, 14);
			contentPanel.add(separator);
		}
		{
			JLabel lblJosecaramellogmailcom = new JLabel("josecaramello@gmail.com");
			lblJosecaramellogmailcom.setForeground(new Color(30, 144, 255));
			lblJosecaramellogmailcom.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblJosecaramellogmailcom.setBounds(132, 83, 160, 25);
			contentPanel.add(lblJosecaramellogmailcom);
		}
		String[] endpoints = Application.getEndPoints();
		DefaultListModel<String> model = new DefaultListModel<>();
		
		for (String endpoint : endpoints) {
			model.addElement(endpoint);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.DARK_GRAY);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Cerrar");
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						
						AcercaDe.CurrentDialog.setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		this.setLocationRelativeTo(null);
	}
		
}
