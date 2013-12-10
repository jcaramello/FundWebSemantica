package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import java.awt.Dialog.ModalExclusionType;
import java.awt.Color;

import javax.swing.JProgressBar;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Window.Type;

import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

import java.awt.Cursor;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

import common.Logger;

public class LoadingMask extends JDialog {

	protected JPanel contentPane;
	public static LoadingMask CurrentWindow;
	public static JFrame ParentWindow;
	public static JProgressBar progressBar;
	public static boolean buscando;
	
	/**
	 * Launch the application.
	 * @throws InterruptedException 
	 */
	public static void main(JFrame parent) throws InterruptedException {
		LoadingMask.ParentWindow = parent;
		UIManager.put("ProgressBar.background", Color.WHITE); //colour of the background
        UIManager.put("ProgressBar.foreground", new Color(50, 205, 50)); //colour of progress bar
        UIManager.put("ProgressBar.selectionBackground",new Color(30, 144, 255)); //colour of percentage counter on black background
        UIManager.put("ProgressBar.selectionForeground",Color.DARK_GRAY); //colour of precentage counter on red background
		
        progressBar = new JProgressBar();
		progressBar.setFont(new Font("Tahoma", Font.BOLD, 11));
		progressBar.setString("Buscando...");
		progressBar.setStringPainted(true);
		progressBar.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		progressBar.setIndeterminate(true);
		progressBar.setBounds(48, 46, 362, 28);
		buscando = true;
		Runnable r = new Runnable() {
			public void run() {
				try {					
					LoadingMask frame = new LoadingMask();
					LoadingMask.CurrentWindow = frame;
					LoadingMask.CurrentWindow.setVisible(true);						
				} catch (Exception e) {
					e.printStackTrace();
					Logger.log(e.getMessage());					
				}
				finally{
					Logger.close();
				}
			}
		};	
		
		ExecutorService executor = Executors.newCachedThreadPool();
		executor.submit(r);		
		
		Runnable r2 = new Runnable() {
			public void run() {
				while(buscando){					
					try {
						progressBar.setString("Buscando.");
						Thread.sleep(500);
						progressBar.setString("Buscando..");
						Thread.sleep(500);
						progressBar.setString("Buscando...");
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}										
				}
			}
		};	
		executor.submit(r2);		
			
	}

	/**
	 * Create the frame.
	 */
	public LoadingMask() {
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setModal(true);
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		LoadingMask.CurrentWindow = this;
		setUndecorated(true);
		setType(Type.POPUP);
		setTitle("Buscando...");
		setAlwaysOnTop(true);
		setBackground(Color.DARK_GRAY);
		setForeground(new Color(30, 144, 255));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 131);
		contentPane = new JPanel();
		contentPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(30, 144, 255), new Color(0, 191, 255)));
		contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		contentPane.setBackground(Color.DARK_GRAY);
		setContentPane(contentPane);
		contentPane.setLayout(null);
				
		contentPane.add(progressBar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoadingMask.CurrentWindow.dispose();
			}
		});		
		btnCancelar.setBounds(335, 97, 89, 23);
		contentPane.add(btnCancelar);
		
		JLabel lblPorFavorEspere = new JLabel("Por favor espere, la operacion puede tardar unos segundos");
		lblPorFavorEspere.setForeground(new Color(30, 144, 255));
		lblPorFavorEspere.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPorFavorEspere.setBounds(10, 11, 430, 24);
		contentPane.add(lblPorFavorEspere);
		setLocationRelativeTo(LoadingMask.ParentWindow);
	}
	
	public static void center(){
		
		CurrentWindow.setLocationRelativeTo(null);
	}
}
