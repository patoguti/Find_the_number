package find_the_number;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import java.awt.ComponentOrientation;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Instrucciones extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Instrucciones frame = new Instrucciones();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Instrucciones() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JTextPane texto = new JTextPane();
		texto.setFont(new Font("Tahoma", Font.PLAIN, 12));
		texto.setAutoscrolls(false);
		texto.setEditable(false);
		texto.setText("\u00A1Bienvenido a 'F1ND TH3 NUMB3R'!\r\nEl objetivo principal del juego es descubrir el n\u00FAmero oculto creado por nuestro RNG. (Random Number Generator).\r\nEl n\u00FAmero oculto posee 4 cifras, y \u00E9stas deben ser descubiertas \u00A1por ti!, usando tu perspicacia y capacidad de analizar las pistas que te entregaremos.\r\nPara alcanzar este objetivo deber\u00E1s ir ingresando diversas soluciones hasta dar con la acertada,  intentando resolver el acertijo en la menor cantidad de intentos y tiempo posibles.\r\nLas pistas son las siguientes: En la pantalla aparecera una tabla la cual tendra las columnas toque y fama.\r\nEl valor que aparezca en la columna toque se refiere a n\u00FAmeros ingresados que est\u00E1n presentes en el n\u00FAmero generado pero en una posici\u00F3n distinta.\r\nEl valor que aparezca en la columna fama se refiere a n\u00FAmeros ingresados que estan exactamente en la misma posici\u00F3n que el n\u00FAmero generado.\r\n El juego finaliza cuando aciertas a todas las cifras en su respectiva posici\u00F3n, es decir, cuando obtienes el valor de 4 en la columna fama.\r\n\u00A1Buena Suerte!");
		JScrollPane scrollPane = new JScrollPane(texto);
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			   public void run() { 
			       scrollPane.getVerticalScrollBar().setValue(0);
			   }
			});
		scrollPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		scrollPane.setBounds(0, 11, 434, 215);
		contentPane.add(scrollPane);
		
		JButton btnMenu = new JButton("Menu");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			new MenuPrincipal().setVisible(true);
			dispose();
			}
		});
		btnMenu.setBounds(163, 227, 89, 23);
		contentPane.add(btnMenu);
		setLocationRelativeTo(null);
		
	}
}
