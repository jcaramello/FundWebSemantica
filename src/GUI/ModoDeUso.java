package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;
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

import javax.swing.SwingConstants;
import javax.swing.JSeparator;

public class ModoDeUso extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	public static ModoDeUso CurrentDialog;
	public JTextArea lblIngreseLosEndpoints;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ModoDeUso dialog = new ModoDeUso();
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
	public ModoDeUso() throws IOException {
		setResizable(false);
		setModal(true);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setTitle("Modo de uso");
		ModoDeUso.CurrentDialog = this;
		setBounds(100, 100, 534, 201);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setForeground(new Color(30, 144, 255));
		contentPanel.setBackground(Color.DARK_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);		
		{
			lblIngreseLosEndpoints = new JTextArea("La aplicacion es en front-end para realizar consultas a DBpedia utilizando SparQL. Para comenzar debe ingresar 1 o mas keywords y se consultara a BDpedia por cualquier tipo de contenido en el cual se haga referencia a las keywords ingresadas. Desde la seccion de opciones, en Archivo -> Opciones puede limitar la cantidad de resultados para mejorar la performance de la consulta y el idioma en el que desea obtener los resultados.");			
			lblIngreseLosEndpoints.setLineWrap(true);
			lblIngreseLosEndpoints.setWrapStyleWord(true);
			lblIngreseLosEndpoints.setBackground(Color.DARK_GRAY);
			lblIngreseLosEndpoints.setDisabledTextColor(new Color(30, 144, 255));
			lblIngreseLosEndpoints.setEnabled(false);
			lblIngreseLosEndpoints.setEditable(false);
			lblIngreseLosEndpoints.setForeground(new Color(30, 144, 255));
			lblIngreseLosEndpoints.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblIngreseLosEndpoints.setBounds(10, 17, 509, 97);
			contentPanel.add(lblIngreseLosEndpoints);
		}
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(30, 144, 255));
		separator.setBackground(new Color(30, 144, 255));
		separator.setBounds(10, 125, 509, 14);
		contentPanel.add(separator);
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
						
						ModoDeUso.CurrentDialog.setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		this.setLocationRelativeTo(null);
	}
}
