package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JSeparator;
import javax.swing.JTextArea;

import common.Application;
import common.Logger;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FundWebSema {

	private JFrame frmFundamentosDeLa;
	private JTextField textField;
	public static FundWebSema CurrentWindow;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {		
		Application.Initialize(args);
					
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FundWebSema window = new FundWebSema();
					FundWebSema.CurrentWindow = window;
					window.frmFundamentosDeLa.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
					Logger.log(e.getMessage());					
				}
				finally{
					Logger.close();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FundWebSema() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFundamentosDeLa = new JFrame();
		frmFundamentosDeLa.getContentPane().setForeground(new Color(30, 144, 255));
		frmFundamentosDeLa.getContentPane().setBackground(Color.DARK_GRAY);
		frmFundamentosDeLa.setTitle("Fundamentos de la WebSem\u00E1ntica - Proyecto Final (2013)");
		//frame.setBounds(100, 100, 450, 300);
		frmFundamentosDeLa.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frmFundamentosDeLa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(Color.BLACK);
		menuBar.setBackground(UIManager.getColor("MenuItem.disabledForeground"));
		frmFundamentosDeLa.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("Archivo");
		mnFile.setForeground(Color.DARK_GRAY);
		menuBar.add(mnFile);
		
		JMenuItem mntmGuardar = new JMenuItem("Guardar");
		mnFile.add(mntmGuardar);
		
		JMenuItem mntmConfigurarEndpoints = new JMenuItem("Configurar EndPoints");
		mntmConfigurarEndpoints.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(ConfigurarEndPoints.CurrentDialog == null)
					ConfigurarEndPoints.main(null);
				else ConfigurarEndPoints.CurrentDialog.setVisible(true);
			}
		});
				
		mnFile.add(mntmConfigurarEndpoints);
		
		JMenuItem mntmCerrrar = new JMenuItem("Salir");
		mntmCerrrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FundWebSema.CurrentWindow.close();
			}
		});
		mnFile.add(mntmCerrrar);
		
		JMenu mnHelp = new JMenu("Ayuda");
		mnHelp.setForeground(Color.BLACK);
		menuBar.add(mnHelp);
		
		JMenuItem mntmModoDeUso = new JMenuItem("Modo de uso");
		mnHelp.add(mntmModoDeUso);
		
		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de");
		mnHelp.add(mntmAcercaDe);
		frmFundamentosDeLa.getContentPane().setLayout(null);
		
		JPanel intoPnl = new JPanel();
		intoPnl.setBackground(Color.DARK_GRAY);
		intoPnl.setBounds(0, 0, 1342, 158);
	
		frmFundamentosDeLa.getContentPane().add(intoPnl);
		intoPnl.setLayout(null);
		
		JPanel resultsPnl = new JPanel();
		resultsPnl.setBackground(Color.DARK_GRAY);
		resultsPnl.setBounds(0, 179, 1342, 498);
		frmFundamentosDeLa.getContentPane().add(resultsPnl);
		resultsPnl.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setEnabled(false);
		textArea.setBounds(10, 5, 1322, 482);
		resultsPnl.add(textArea);
		
		
		
		JLabel lblBuscar = new JLabel("Buscar: ");
		lblBuscar.setForeground(new Color(30, 144, 255));
		lblBuscar.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBuscar.setBounds(189, 101, 82, 27);
		intoPnl.add(lblBuscar);
		
		textField = new JTextField();
		textField.setBounds(270, 106, 835, 20);
		intoPnl.add(textField);
		textField.setColumns(10);
		
		JTextPane txtpnLoremIpsumDolor = new JTextPane();
		txtpnLoremIpsumDolor.setForeground(new Color(30, 144, 255));
		txtpnLoremIpsumDolor.setBackground(Color.DARK_GRAY);
		txtpnLoremIpsumDolor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtpnLoremIpsumDolor.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur tristique pretium scelerisque. Vivamus mauris arcu, euismod varius varius a, feugiat eu justo. Curabitur sed nisi accumsan ligula viverra euismod. Integer vitae metus velit. Morbi luctus dui elit, sed facilisis magna posuere vitae. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Mauris rhoncus ultrices malesuada. Integer euismod, sem eu hendrerit ultrices, turpis urna vehicula erat, nec consectetur nulla mauris vitae mauris. Pellentesque nisi lectus, molestie et nulla quis, rutrum volutpat ipsum.");
		txtpnLoremIpsumDolor.setBounds(28, 11, 1304, 79);
		intoPnl.add(txtpnLoremIpsumDolor);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(30, 144, 255));
		separator.setForeground(new Color(30, 144, 255));
		separator.setBounds(10, 169, 1322, 2);
		frmFundamentosDeLa.getContentPane().add(separator);
	}
	
	public void close()
	{
		frmFundamentosDeLa.dispose();
	}
	
}
