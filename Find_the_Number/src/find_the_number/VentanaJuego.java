package find_the_number;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.DropMode;

import java.awt.ComponentOrientation;

import javax.swing.ListSelectionModel;
import javax.swing.JButton;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import jdk.nashorn.internal.runtime.regexp.joni.Warnings;

@SuppressWarnings("serial")
public class VentanaJuego extends JFrame {

	private int segundos;
	private int turnos;
	private JPanel contentPane;
	private JTextField textField;
	private JFormattedTextField formattedTextField;
	private JTable table;
	private Juego juego;
	private JLabel lblError;

	public int getSegundos() {
		return this.segundos;
	}

	// Inicio Timer
	Timer tiempo = new Timer();
	TimerTask task = new TimerTask() {
		public void run() {
			segundos += 1;
			// forma de presentar el tiempo
			String a = String.format(
					"%02d:%02d",
					TimeUnit.SECONDS.toMinutes(segundos),
					TimeUnit.SECONDS.toSeconds(segundos)
							- TimeUnit.MINUTES.toSeconds(TimeUnit.SECONDS.toMinutes(segundos)));
			textField.setText(a);
		}
	};
	private JButton btnMenu;

	// orden para comenzar timer
	public void start() {
		tiempo.scheduleAtFixedRate(task, 1000, 1000);
	}

	// Termino metodos Timer

	/*
	 * Mascara para formattedTextField, No esta siendo usadoprotected
	 * MaskFormatter createFormatter(String s) { MaskFormatter formatter = null;
	 * try { formatter = new MaskFormatter(s); } catch (java.text.ParseException
	 * exc) { System.err.println("formatter is bad: " + exc.getMessage());
	 * System.exit(-1); } return formatter;}
	 */

	// Escribe en la tabla o escribe label con su respectivo error
	// Envia pistas a metodo finJuego
	private void accionIngreso(boolean opc) {
		if (opc) {
			// escribir en tabla
			((DefaultTableModel) table.getModel())
					.insertRow(table.getRowCount(), new Object[] { formattedTextField.getText(),
							juego.getComp().getToque(), juego.getComp().getFama() });
			formattedTextField.setText("");
			lblError.setText("");
			turnos++;
			finJuego(juego.getComp().getFama());

		} else {
			// pop-up error
			if (!juego.getNumIngresado().getVal().esNumero(formattedTextField.getText())) {
				lblError.setText("Error!, Longitud incorrecta");
			} else {
				lblError.setText("Error!, Numeros repetidos");

			}
			formattedTextField.setText("");
		}
	}

	// Analiza la cantidad de pistas. Si termina ve el puntaje si es que esta
	// dentro
	// de los 10 mejores para pedir nombre y guardar en el archivo
	private void finJuego(int fama) {
		if (fama == 4) {
			tiempo.cancel();
			juego.setGuardarPtje(new ColeccionPuntaje(new Puntaje(turnos, String.valueOf(segundos))));
			if (juego.getGuardarPtje().puntajeAlto()) {
				String jugador=JOptionPane.showInputDialog("Puntaje alto obtenido!\n Ingrese nombre");
				while(jugador.equals("")){
					jugador=JOptionPane.showInputDialog("Ingrese al menos un caracter");
				}
			juego.getGuardarPtje().getPtje().setJugador(jugador);
			juego.getGuardarPtje().modificarPuntaje();
			}
			new MostrarPuntaje().setVisible(true);
			dispose();

		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaJuego frame = new VentanaJuego();
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
	public VentanaJuego() {
		super("F1nd th3 Numb3r");
		setResizable(false);
		this.juego = new Juego();
		start();
		for (int i = 0; i < 4; i++){
			System.out.println(juego.getNumGen().getNum()[i]);
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// texto con timer
		textField = new JTextField();
		textField.setFocusable(false);
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setEditable(false);
		textField.setBounds(376, 11, 48, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		// texto de ingreso
		try {
			formattedTextField = new JFormattedTextField(new MaskFormatter("####"));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		formattedTextField.setVerifyInputWhenFocusTarget(false);
		formattedTextField.setFocusTraversalKeysEnabled(false);
		formattedTextField.setHorizontalAlignment(SwingConstants.CENTER);
		formattedTextField.setBounds(164, 27, 107, 39);
		contentPane.add(formattedTextField);

		// Tabla que presenta numeros ingresados y sus respectivas pistas
		table = new JTable();
		table.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent arg0) {
				table.scrollRectToVisible(table.getCellRect(table.getRowCount() - 1, 0, true));
				
			}
		});
		table.setEnabled(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		// Datos de tabla
		table.setModel(new DefaultTableModel(new Object[][] {
		// Datos agregados dinamicamente por funcion definida en
		// addComponentListener

				},// Cabecera de columnas
				new String[] { "Numero", "Toque", "Fama" }) {
			Class[] columnTypes = new Class[] { String.class, String.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.setBounds(80, 6, 160, 160);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		for(int i=0;i<3;i++){
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
		// Boton para leer texto de formattedTextField
		JButton btnEnter = new JButton("Ingresar");
		btnEnter.setFocusable(false);
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean op = juego.compararNum(formattedTextField.getText());
				accionIngreso(op);
			}
		});
		btnEnter.setBounds(284, 35, 89, 23);
		contentPane.add(btnEnter);

		// Etiqueta con mensaje
		JLabel lblNumero = new JLabel("Ingrese Numero");
		lblNumero.setBounds(54, 21, 100, 50);
		contentPane.add(lblNumero);

		// Etiqueta que aparece al producirse un error en cuanto al
		// numero ingresado
		lblError = new JLabel("");
		lblError.setBounds(136, 228, 176, 22);
		contentPane.add(lblError);

		// Boton que vuelve al menu principal
		btnMenu = new JButton("Menu");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MenuPrincipal().setVisible(true);
				dispose();
			}
		});
		btnMenu.setBounds(10, 228, 89, 23);
		contentPane.add(btnMenu);

		// Scrollpane que contiene a tabla
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setAutoscrolls(true);
		scrollPane.setBounds(98, 77, 245, 140);
		contentPane.add(scrollPane);
		setLocationRelativeTo(null);
	}
}
