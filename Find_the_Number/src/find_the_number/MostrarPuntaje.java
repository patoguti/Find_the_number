package find_the_number;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;

import java.awt.Dimension;
import java.awt.Font;

public class MostrarPuntaje extends JFrame {
	private ColeccionPuntaje cp;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	private void accionIngreso(){
		cp=new ColeccionPuntaje();
		String tabla[][]=cp.puntajeModificar();
		for(int i=0;i<10;i++)((DefaultTableModel) table.getModel()).insertRow(table.getRowCount(),new Object[]{tabla[i][0],tabla[i][1],tabla[i][2],tabla[i][3]});

	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MostrarPuntaje frame = new MostrarPuntaje();
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
	public MostrarPuntaje() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 212);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 444, 183);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Jugador", "Turnos", "Tiempo", "Puntaje"
			}
		));
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		for(int i=0;i<4;i++){
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
		scrollPane.setViewportView(table);
		table.getColumnModel().getColumn(0).setPreferredWidth(90);
		table.getColumnModel().getColumn(1).setPreferredWidth(90);
		table.getColumnModel().getColumn(2).setPreferredWidth(90);
		table.getColumnModel().getColumn(3).setPreferredWidth(90);
		accionIngreso();
	}
}
