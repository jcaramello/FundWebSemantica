package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JProgressBar;
import javax.swing.border.EtchedBorder;

public class SplashScreen extends JFrame {
	
	public static JFrame CurrentSplashScreen;

	private JPanel contentPane;

	/**
	 * Launch the application.
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SplashScreen frame = new SplashScreen();
					SplashScreen.CurrentSplashScreen = frame;
					frame.setVisible(true);										
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		Thread.sleep(2000);
		SplashScreen.CurrentSplashScreen.dispose();
		FundWebSema.main(null);
	}

	/**
	 * Create the frame.
	 */
	public SplashScreen() {
	        
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 156);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(30, 144, 255), new Color(0, 191, 255)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFundamentosDeLa = new JLabel("Fundamentos de la Web Semantica");
		lblFundamentosDeLa.setForeground(new Color(30, 144, 255));
		lblFundamentosDeLa.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblFundamentosDeLa.setBounds(67, 0, 328, 42);
		contentPane.add(lblFundamentosDeLa);
		
		JLabel lblProyectoFinal = new JLabel("Proyecto Final - 2013");
		lblProyectoFinal.setForeground(new Color(30, 144, 255));
		lblProyectoFinal.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblProyectoFinal.setBounds(135, 38, 174, 30);
		contentPane.add(lblProyectoFinal);
		
		UIManager.put("ProgressBar.background", Color.WHITE); //colour of the background
        UIManager.put("ProgressBar.foreground", new Color(50, 205, 50)); //colour of progress bar
        UIManager.put("ProgressBar.selectionBackground",new Color(30, 144, 255)); //colour of percentage counter on black background
        UIManager.put("ProgressBar.selectionForeground",Color.LIGHT_GRAY); //colour of precentage counter on red background
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setFont(new Font("Tahoma", Font.BOLD, 11));
		progressBar.setStringPainted(true);
		progressBar.setString("Cargando...");
		progressBar.setIndeterminate(true);
		progressBar.setBounds(10, 90, 430, 30);
		contentPane.add(progressBar);
		
		JLabel lblJoseCaramello = new JLabel("Jose Caramello - LU:83767");
		lblJoseCaramello.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblJoseCaramello.setForeground(new Color(30, 144, 255));
		lblJoseCaramello.setBounds(258, 131, 182, 23);
		contentPane.add(lblJoseCaramello);
		this.setLocationRelativeTo(null);
	}
}
