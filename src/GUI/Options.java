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

public class Options extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	public static Options CurrentDialog;
	private JTextField LimitRlts;
	public JLabel lblIngreseLosEndpoints;
	private JTextField lang;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Options dialog = new Options();
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
	public Options() throws IOException {
		setResizable(false);
		setModal(true);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setTitle("Opciones");
		Options.CurrentDialog = this;
		setBounds(100, 100, 329, 234);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setForeground(new Color(30, 144, 255));
		contentPanel.setBackground(Color.DARK_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);		
		{
			lblIngreseLosEndpoints = new JLabel("Limite de resultados (por default 100)");
			lblIngreseLosEndpoints.setForeground(new Color(30, 144, 255));
			lblIngreseLosEndpoints.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblIngreseLosEndpoints.setBounds(10, 11, 389, 25);
			contentPanel.add(lblIngreseLosEndpoints);
		}
		{
			LimitRlts = new JTextField();
			LimitRlts.setText(Application.LimitResults + "");
			LimitRlts.setBounds(10, 33, 234, 20);
			contentPanel.add(LimitRlts);
			LimitRlts.setColumns(10);
		}
		
		JLabel lblIdioma = new JLabel("Idioma - (es, en, fr, etc.)");
		lblIdioma.setForeground(new Color(30, 144, 255));
		lblIdioma.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblIdioma.setBounds(10, 66, 239, 25);
		contentPanel.add(lblIdioma);
		
		lang = new JTextField();
		lang.setText(Application.LANG);
		lang.setColumns(10);
		lang.setBounds(10, 88, 234, 20);
		contentPanel.add(lang);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(30, 144, 255));
		separator.setBackground(new Color(30, 144, 255));
		separator.setBounds(10, 159, 303, 14);
		contentPanel.add(separator);
		
		JLabel lblHttpwwwwschoolscomtagsreflanguagecodesasp = new JLabel("http://www.w3schools.com/tags/ref_language_codes.asp");
		lblHttpwwwwschoolscomtagsreflanguagecodesasp.setForeground(new Color(30, 144, 255));
		lblHttpwwwwschoolscomtagsreflanguagecodesasp.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblHttpwwwwschoolscomtagsreflanguagecodesasp.setBounds(10, 128, 303, 20);
		contentPanel.add(lblHttpwwwwschoolscomtagsreflanguagecodesasp);
		
		JLabel lblParaMasInformacion = new JLabel("Para mas informacion acerca de los codigos de idioma visitar:");
		lblParaMasInformacion.setForeground(new Color(30, 144, 255));
		lblParaMasInformacion.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblParaMasInformacion.setBounds(10, 114, 303, 20);
		contentPanel.add(lblParaMasInformacion);
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
						
						Options.CurrentDialog.setVisible(false);
					}
				});
				
				JButton btnBorrar = new JButton("Guardar");
				btnBorrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						Application.LimitResults = Integer.parseInt(LimitRlts.getText());
						Application.LANG = lang.getText();
						Options.CurrentDialog.setVisible(false);
					}
				});
				buttonPane.add(btnBorrar);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		this.setLocationRelativeTo(null);
	}
		
}
