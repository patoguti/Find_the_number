package find_the_number;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
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
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.DropMode;
import java.awt.ComponentOrientation;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

@SuppressWarnings("serial")
public class TimerEj extends JFrame {
	
	private int segundos;
	private int turnos;
	private JPanel contentPane;
	private JTextField textField;
	private JFormattedTextField formattedTextField;
	private JTable table;
	private Juego juego;
	private JLabel lblError;

	
	//Inicio Timer
	Timer tiempo=new Timer();
	TimerTask task=new TimerTask(){
		public void run(){
			segundos+=1;
			//forma de presentar el tiempo
			String a=String.format("%02d:%02d", 
				    TimeUnit.SECONDS.toMinutes(segundos),
				    TimeUnit.SECONDS.toSeconds(segundos) - 
				    TimeUnit.MINUTES.toSeconds(TimeUnit.SECONDS.toMinutes(segundos))
				);
			textField.setText(a);
		}
	};
	//orden para comenzar timer
	public void start(){
		tiempo.scheduleAtFixedRate(task, 1000, 1000);
	}
	//fin Timer
	
	public int getSegundos(){
		return this.segundos;
	}
	protected MaskFormatter createFormatter(String s) {					//Solo deja ingresar numeros
	    MaskFormatter formatter = null;
	    try {
	        formatter = new MaskFormatter(s);
	    } catch (java.text.ParseException exc) {
	        System.err.println("formatter is bad: " + exc.getMessage());
	        System.exit(-1);
	    }
	    return formatter;
	}
	
	private void accionIngreso(boolean opc){
		if(opc){
			//escribir en tabla
			((DefaultTableModel) table.getModel()).insertRow(table.getRowCount(),new Object[]{formattedTextField.getText(),juego.getComp().getToque(),juego.getComp().getFama()});
			formattedTextField.setText("");
			lblError.setText("");
			turnos++;
			finJuego(juego.getComp().getFama());
			
		}else{
			//pop-up error
			if(!juego.getNumIngresado().getVal().esNumero(formattedTextField.getText())){
				lblError.setText("Error!, Longitud incorrecta");
			}else{
				lblError.setText("Error!, Numeros repetidos");
				
			}
			formattedTextField.setText("");
		}
	}
	private void finJuego(int fama){
		if(fama==4){
			tiempo.cancel();
			ColeccionPuntaje colecc=new ColeccionPuntaje(new Puntaje(turnos,String.valueOf(segundos)));
			//System.out.println(colecc.puntajeModificado());
			//System.out.println(segundos);
		}
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TimerEj frame = new TimerEj();
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
	public TimerEj() {
		super("Toque o Fama");
		this.juego=new Juego();
		start();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		textField = new JTextField();
		textField.setFocusable(false);
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setEditable(false);
		textField.setBounds(376, 11, 48, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		formattedTextField = new JFormattedTextField(createFormatter("####"));
		formattedTextField.setHorizontalAlignment(SwingConstants.CENTER);
		formattedTextField.setBounds(164, 27, 107, 39);								//4 numeros solamente
		contentPane.add(formattedTextField);
		
        
        table = new JTable();
        table.addComponentListener(new ComponentAdapter() {
        	@Override
        	public void componentResized(ComponentEvent arg0) {
        		 table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
        	}
        });
        table.setEnabled(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//Datos de tabla
		table.setModel(new DefaultTableModel(
			new Object[][] {
					
				
			},//Cabecera de columnas
			new String[] {
				"Numero", "Toque", "Fama"
			}
		) {	//Tipo de las columnas
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.setBounds(80, 6, 160, 160);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setAutoscrolls(true);
		
		scrollPane.setBounds(98, 77, 245, 140);
		contentPane.add(scrollPane);
		
		JButton btnEnter = new JButton("Enter");
		btnEnter.setFocusable(false);
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			boolean op=juego.compararNum(formattedTextField.getText());
			accionIngreso(op);
			}
		});
		btnEnter.setBounds(284, 35, 89, 23);
		contentPane.add(btnEnter);
		
		JLabel lblNumero = new JLabel("Ingrese Numero");
		lblNumero.setBounds(54, 21, 100, 50);
		contentPane.add(lblNumero);
		
		lblError = new JLabel("");
		lblError.setBounds(136, 228, 176, 22);
		contentPane.add(lblError);
		//Agregar columnas con lo de abajo
		//((DefaultTableModel) table.getModel()).insertRow(table.getRowCount(),new Object[]{"hello","50","readyyy"});
	
	}
}
