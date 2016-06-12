package find_the_number;

import java.awt.BorderLayout;
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

import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField;

@SuppressWarnings("serial")
public class TimerEj extends JFrame {
	private int segundos;
	private JPanel contentPane;
	Timer tiempo=new Timer();
	TimerTask task=new TimerTask(){
		public void run(){
			segundos+=1;
			String a=String.format("%02d:%02d", 
				    TimeUnit.SECONDS.toMinutes(segundos),
				    TimeUnit.SECONDS.toSeconds(segundos) - 
				    TimeUnit.MINUTES.toSeconds(TimeUnit.SECONDS.toMinutes(segundos))
				);
			textField.setText(a);
		}
	};
	
	private JTextField textField;
	private JFormattedTextField formattedTextField;
	public void start(){
		tiempo.scheduleAtFixedRate(task, 1000, 1000);
	}
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
		start();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setEditable(false);
		textField.setBounds(376, 11, 48, 20);
	/*	textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			textField.setText(String.valueOf(seg));
			}
		});
		*/
		contentPane.add(textField);
		textField.setColumns(10);
		
		formattedTextField = new JFormattedTextField(createFormatter("####"));		//4 "#" para ingresar
		formattedTextField.setBounds(10, 10, 110, 40);								//4 numeros solamente
		contentPane.add(formattedTextField);
		
	}
}
