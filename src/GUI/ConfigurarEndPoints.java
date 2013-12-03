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

public class ConfigurarEndPoints extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	public static ConfigurarEndPoints CurrentDialog;
	private JTextField newEndpoint;
	
	public JList<String> list;
	public JLabel lblIngreseLosEndpoints;

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
	 * @throws IOException 
	 */
	public ConfigurarEndPoints() throws IOException {
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
			lblIngreseLosEndpoints = new JLabel("Ingrese los endpoints a los que desea conectarse:");
			lblIngreseLosEndpoints.setForeground(new Color(30, 144, 255));
			lblIngreseLosEndpoints.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblIngreseLosEndpoints.setBounds(10, 11, 389, 25);
			contentPanel.add(lblIngreseLosEndpoints);
		}
		{
			newEndpoint = new JTextField();
			newEndpoint.setBounds(10, 33, 312, 20);
			contentPanel.add(newEndpoint);
			newEndpoint.setColumns(10);
		}
		
		JButton btnAgregar = new JButton("Agregar");
		
		btnAgregar.setBounds(332, 32, 89, 23);
		contentPanel.add(btnAgregar);
		
		list = new JList<String>();											
		String[] endpoints = Application.getEndPoints();
		DefaultListModel<String> model = new DefaultListModel<>();
		
		for (String endpoint : endpoints) {
			model.addElement(endpoint);
		}
		
		list.setModel(model);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setVisibleRowCount(10);
		list.setBounds(10, 64, 411, 154);
		
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ConfigurarEndPoints.CurrentDialog.addEndpoint();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Logger.log(e.getMessage());
				}
			}
		});
		
		contentPanel.add(list);
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
						ConfigurarEndPoints.CurrentDialog.setVisible(false);
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
						
						ConfigurarEndPoints.CurrentDialog.setVisible(false);
					}
				});
				
				JButton btnBorrar = new JButton("Borrar");
				buttonPane.add(btnBorrar);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public void addEndpoint() throws IOException{
		DefaultListModel<String> model = (DefaultListModel<String>)list.getModel();
		if(!newEndpoint.getText().equals("")){
			model.addElement(newEndpoint.getText());
			
			
			list.setModel(model);
			list.repaint();
			Application.SaveEndpoint(newEndpoint.getText());
			newEndpoint.setText("");
		}
	}
}
