package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
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

import java.awt.LayoutManager;
import java.awt.SystemColor;
import java.awt.Color;

import javax.swing.UIManager;
import javax.swing.JSeparator;
import javax.swing.JTextArea;

import common.Application;
import common.CommonHelper;
import common.Logger;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.text.rtf.RTFEditorKit;

import endpoints.EndPoint;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.ScrollPaneConstants;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.ComponentOrientation;

public class FundWebSema {

	public JFrame frmFundamentosDeLa;
	private JTextField textField;
	public static FundWebSema CurrentWindow;
	private final JButton searchBtn = new JButton("Buscar");
	private JLayeredPane mainPnl;
	private JTextArea textArea;
	
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
		textField.setBounds(10, 138, 668, 20);
		intoPnl.add(textField);
		textField.setColumns(10);
		
		JTextPane txtpnLoremIpsumDolor = new JTextPane();
		txtpnLoremIpsumDolor.setForeground(new Color(30, 144, 255));
		txtpnLoremIpsumDolor.setBackground(Color.DARK_GRAY);
		txtpnLoremIpsumDolor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtpnLoremIpsumDolor.setText("El objtivo de este proyeto final es desarrollar una aplicacion a modo de prueba de concepto donde" + 
									 "pudiera aplicar los conocimientos adquiridos sobre la web semantica y que ademas sirviera para comenzar a aprender " +
									 "y familiarizarme con el framework Apache Jena de Apache Foundations.\n\n"+ 
									 "La aplicacion basicamente consiste en front-end para realizar consultas a DBpedia utilizando SparQL. PAra comenzar debe ingresar 1 o mas keywords "+
									 "y se consultara a BDpedia por cualquier tipo de contenido en el cual se haga referencia a las keywords ingresadas");
		txtpnLoremIpsumDolor.setBounds(10, 34, 767, 96);
		intoPnl.add(txtpnLoremIpsumDolor);
		searchBtn.setBounds(0, 0, 0, 0);
		intoPnl.add(searchBtn);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setText("");
				searchBtn.setEnabled(false);
				FundWebSema.CurrentWindow.showLoadingMask();								
			    Runnable r = new Runnable() {
					public void run() {
						try {
							List<String> results = EndPoint.main(Arrays.asList(textField.getText().split(" ")));	
							textArea.setText(CommonHelper.joinResults(results));													
							FundWebSema.CurrentWindow.hideLoadingMask();
							searchBtn.setEnabled(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
			     };
			     
			     ExecutorService executor = Executors.newCachedThreadPool();
			     executor.submit(r);
			}
		});
		btnBuscar.setBounds(688, 137, 89, 23);
		intoPnl.add(btnBuscar);

		JLabel lblProyetoFinal = new JLabel(" Proyeto Final");
		lblProyetoFinal.setForeground(new Color(30, 144, 255));
		lblProyetoFinal.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblProyetoFinal.setBounds(10, 0, 175, 28);
		intoPnl.add(lblProyetoFinal);
		
		JPanel panel = new JPanel();
		panel.setSize(new Dimension(798, 498));
		panel.setBounds(new Rectangle(0, 0, 798, 498));
		panel.setBounds(10, 182, 777, 495);
		mainPnl.add(panel);			
		panel.setLayout(null);
				
		textArea = new JTextArea();
		textArea.setBounds(new Rectangle(10, 182, 777, 495));
		textArea.setDisabledTextColor(Color.BLACK);
		textArea.setEnabled(false);
		textArea.setEditable(false);		
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setForeground(Color.BLACK);
		
		
		JScrollPane resultsPnl = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		resultsPnl.setBounds(new Rectangle(0, 0, 767, 495));
		panel.add(resultsPnl);
		
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
	
	public void hideLoadingMask(){
		LoadingMask.CurrentWindow.dispose();
	}
}
