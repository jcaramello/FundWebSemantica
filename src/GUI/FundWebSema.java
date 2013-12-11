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
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;
import javax.swing.text.rtf.RTFEditorKit;

import endpoints.EndPoint;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.ScrollPaneConstants;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.ComponentOrientation;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
		Logger.log("Iniciando Applicacion");
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
		frmFundamentosDeLa.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				Logger.log("Finalizando Applicacion");
				Logger.close();				
			}
			
			@Override
			public void windowClosed(WindowEvent arg0) {
				Logger.log("Finalizando Applicacion");
				Logger.close();				
			}
		});
		frmFundamentosDeLa.setResizable(false);
		frmFundamentosDeLa.getContentPane().setForeground(new Color(30, 144, 255));
		frmFundamentosDeLa.getContentPane().setBackground(Color.DARK_GRAY);
		frmFundamentosDeLa.setTitle("Fundamentos de la WebSem\u00E1ntica - Proyecto Final (2013)");
		frmFundamentosDeLa.setBounds(100, 100, 816, 726);
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
		
		JMenuItem mntmOpciones = new JMenuItem("Opciones");
		mntmOpciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Options.CurrentDialog == null)
					Options.main(null);
				else Options.CurrentDialog.setVisible(true);
			}
		});
		mnFile.add(mntmOpciones);
		mnFile.add(mntmCerrrar);
		
		JMenu mnHelp = new JMenu("Ayuda");
		mnHelp.setForeground(Color.BLACK);
		menuBar.add(mnHelp);
		
		JMenuItem mntmModoDeUso = new JMenuItem("Modo de uso");
		mntmModoDeUso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ModoDeUso.CurrentDialog == null)
					ModoDeUso.main(null);
				else ModoDeUso.CurrentDialog.setVisible(true);
			}
		});
		mnHelp.add(mntmModoDeUso);
		
		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de");
		mntmAcercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(AcercaDe.CurrentDialog == null)
					AcercaDe.main(null);
				else AcercaDe.CurrentDialog.setVisible(true);
			}
		});
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
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				int key = arg0.getKeyCode();
				if(key == KeyEvent.VK_ENTER)
					try {
						search();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						Logger.log(e.getMessage());
						
					}
			}
		});
		textField.setBounds(10, 138, 668, 20);
		intoPnl.add(textField);
		textField.setColumns(10);
		
		JTextPane txtpnLoremIpsumDolor = new JTextPane();
		txtpnLoremIpsumDolor.setForeground(new Color(30, 144, 255));
		txtpnLoremIpsumDolor.setBackground(Color.DARK_GRAY);
		txtpnLoremIpsumDolor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtpnLoremIpsumDolor.setText("El objtivo de este proyeto final es desarrollar una aplicacion a modo de prueba de concepto, donde " + 
									 "poder aplicar los conocimientos y conceptos adquiridos durante el cursado de la materia Fundamentos de la Web Semantica y que ademas sirviera para comenzar a aprender " +
									 "y familiarizarme con el framework Apache Jena de Apache Foundations.\n\n"+ 
									 "La aplicacion basicamente consiste en un front-end para realizar consultas a DBpedia utilizando SparQL. Para comenzar debe ingresar una o mas keywords "+
									 "y se consultara a BDpedia por cualquier tipo de contenido en el cual se haga referencia a las keywords ingresadas");
		txtpnLoremIpsumDolor.setBounds(10, 34, 767, 96);
		intoPnl.add(txtpnLoremIpsumDolor);
		searchBtn.setBounds(0, 0, 0, 0);
		intoPnl.add(searchBtn);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					search();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Logger.log(e.getMessage());
				}				
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
		panel.setBounds(10, 182, 777, 458);
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
		resultsPnl.setBounds(new Rectangle(0, 0, 777, 458));
		panel.add(resultsPnl);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 169, 777, 2);
		mainPnl.add(separator);
		separator.setBackground(new Color(30, 144, 255));
		separator.setForeground(new Color(30, 144, 255));
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 645, 777, 8);
		mainPnl.add(separator_1);
		separator_1.setForeground(new Color(30, 144, 255));
		separator_1.setBackground(new Color(30, 144, 255));
		
		JLabel lblFundamentosDeLa = new JLabel("Caramello, Leonardo Jos\u00E9 - LU:83767");
		lblFundamentosDeLa.setForeground(new Color(30, 144, 255));
		lblFundamentosDeLa.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFundamentosDeLa.setBounds(559, 645, 228, 30);
		mainPnl.add(lblFundamentosDeLa);
		
		JLabel label = new JLabel("Fundamentos de la Web Semantica (2013)");
		label.setForeground(new Color(30, 144, 255));
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		label.setBounds(10, 645, 294, 30);
		mainPnl.add(label);
	}
	
	public void close()
	{
		frmFundamentosDeLa.dispose();
	}
	
	public void showLoadingMask() throws InterruptedException{
		LoadingMask.main(this.frmFundamentosDeLa);
	}
	
	public void hideLoadingMask(){
		LoadingMask.CurrentWindow.dispose();
		LoadingMask.buscando = false;
	}
	
	public static void highlight(JTextComponent textComp, String pattern) {

	    try {
	        Highlighter hilite = textComp.getHighlighter();
	        pattern = pattern.toLowerCase();
	        javax.swing.text.Document doc = textComp.getDocument();
	        String text = doc.getText(0, doc.getLength()).toLowerCase();
	        int pos = 0;
	        Highlighter.HighlightPainter myHighlightPainter = new MyHighlightPainter(Color.YELLOW);
	        // Search for pattern
	        	       		
        	while ((pos = text.indexOf(pattern, pos)) >= 0) {
	            // Create highlighter using private painter and apply around pattern
	            hilite.addHighlight(pos, pos + pattern.length(), myHighlightPainter);
	            pos += pattern.length();
	        }
				       
	    } catch (BadLocationException e) {
	    	e.printStackTrace();
	    	Logger.log(e.getMessage());
	    }
	}	
	
	public void search() throws InterruptedException{
		
		if(!textField.getText().equals(""))
		{
			textArea.setText("");
			searchBtn.setEnabled(false);
			FundWebSema.CurrentWindow.showLoadingMask();								
		    Runnable r = new Runnable() {
				public void run() {
					try {
						String[] keywords = textField.getText().split(" ");
						List<String> results = EndPoint.main(Arrays.asList(keywords));	
						textArea.setText(CommonHelper.joinResults(results));
						
						for (String pattern : keywords) {
							highlight(textArea, pattern);	
						}
						
						FundWebSema.CurrentWindow.hideLoadingMask();
						searchBtn.setEnabled(true);
					} catch (Exception e) {
						e.printStackTrace();
						Logger.log(e.getMessage());
						
					}
				}
		     };
		     
		     ExecutorService executor = Executors.newCachedThreadPool();
		     executor.submit(r);	
		}
	}
}
