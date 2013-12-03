package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
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
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.text.rtf.RTFEditorKit;

import java.awt.BorderLayout;
import java.awt.Component;

public class FundWebSema {

	public JFrame frmFundamentosDeLa;
	private JTextField textField;
	public static FundWebSema CurrentWindow;
	private final JButton searchBtn = new JButton("Buscar");
	private JLayeredPane mainPnl;
	
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
					window.frmFundamentosDeLa.setLocationRelativeTo(null);
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
		frmFundamentosDeLa.setResizable(false);
		frmFundamentosDeLa.getContentPane().setForeground(new Color(30, 144, 255));
		frmFundamentosDeLa.getContentPane().setBackground(Color.DARK_GRAY);
		frmFundamentosDeLa.setTitle("Fundamentos de la WebSem\u00E1ntica - Proyecto Final (2013)");
		frmFundamentosDeLa.setBounds(100, 100, 816, 766);
		//frmFundamentosDeLa.setExtendedState(JFrame.MAXIMIZED_BOTH);
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
		frmFundamentosDeLa.getContentPane().setLayout(new BorderLayout(0, 0));
		
		mainPnl = new JLayeredPane();
		frmFundamentosDeLa.getContentPane().add(mainPnl, BorderLayout.CENTER);
		mainPnl.setLayout(null);
		
		JPanel intoPnl = new JPanel();
		intoPnl.setBounds(0, 0, 787, 158);
		mainPnl.add(intoPnl);
		intoPnl.setBackground(Color.DARK_GRAY);
		intoPnl.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 127, 668, 20);
		intoPnl.add(textField);
		textField.setColumns(10);
		
		JTextPane txtpnLoremIpsumDolor = new JTextPane();
		txtpnLoremIpsumDolor.setForeground(new Color(30, 144, 255));
		txtpnLoremIpsumDolor.setBackground(Color.DARK_GRAY);
		txtpnLoremIpsumDolor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtpnLoremIpsumDolor.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur tristique pretium scelerisque. Vivamus mauris arcu, euismod varius varius a, feugiat eu justo. Curabitur sed nisi accumsan ligula viverra euismod. Integer vitae metus velit. Morbi luctus dui elit, sed facilisis magna posuere vitae. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Mauris rhoncus ultrices malesuada. Integer euismod, sem eu hendrerit ultrices, turpis urna vehicula erat, nec consectetur nulla mauris vitae mauris. Pellentesque nisi lectus, molestie et nulla quis, rutrum volutpat ipsum.");
		txtpnLoremIpsumDolor.setBounds(10, 34, 767, 79);
		intoPnl.add(txtpnLoremIpsumDolor);
		searchBtn.setBounds(0, 0, 0, 0);
		intoPnl.add(searchBtn);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				FundWebSema.CurrentWindow.showLoadingMask();
			}
		});
		btnBuscar.setBounds(688, 124, 89, 23);
		intoPnl.add(btnBuscar);
		
		JLabel lblProyetoFinal = new JLabel(" Proyeto Final");
		lblProyetoFinal.setForeground(new Color(30, 144, 255));
		lblProyetoFinal.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblProyetoFinal.setBounds(10, 0, 175, 28);
		intoPnl.add(lblProyetoFinal);
		
		JPanel resultsPnl = new JPanel();
		resultsPnl.setBounds(0, 179, 798, 498);
		mainPnl.add(resultsPnl);
		resultsPnl.setBackground(Color.DARK_GRAY);
		resultsPnl.setLayout(null);
		
		JTextPane textArea = new JTextPane();
		textArea.setEditable(false);
		textArea.setEnabled(false);
		RTFEditorKit rtf = new RTFEditorKit();
		textArea.setEditorKit(rtf);
		textArea.setBounds(10, 5, 779, 482);
		resultsPnl.add(textArea);
		
		
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 169, 777, 2);
		mainPnl.add(separator);
		separator.setBackground(new Color(30, 144, 255));
		separator.setForeground(new Color(30, 144, 255));
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 688, 777, 8);
		mainPnl.add(separator_1);
		separator_1.setForeground(new Color(30, 144, 255));
		separator_1.setBackground(new Color(30, 144, 255));
		
		JLabel lblFundamentosDeLa = new JLabel("Caramello, Leonardo Jos\u00E9 - LU:83767");
		lblFundamentosDeLa.setForeground(new Color(30, 144, 255));
		lblFundamentosDeLa.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFundamentosDeLa.setBounds(559, 688, 228, 30);
		mainPnl.add(lblFundamentosDeLa);
		
		JLabel label = new JLabel("Fundamentos de la Web Semantica (2013)");
		label.setForeground(new Color(30, 144, 255));
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		label.setBounds(10, 688, 294, 30);
		mainPnl.add(label);
	}
	
	public void close()
	{
		frmFundamentosDeLa.dispose();
	}
	
	public void showLoadingMask(){
		LoadingMask.main(this.frmFundamentosDeLa);
	}
}
