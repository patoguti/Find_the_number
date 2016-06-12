package find_the_number;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ColeccionPuntaje {
	private Puntaje ptje;
	ColeccionPuntaje(){};
	ColeccionPuntaje(Puntaje puntaje) {
		this.ptje = puntaje;
	}

	// Extrae texto de archivo
	private ArrayList<String> leerPuntaje() {
		ArrayList<String> lectura = new ArrayList<String>();
		File puntaje = new File("puntaje.txt");
		try {
			Scanner scanner = new Scanner(puntaje);
			while (scanner.hasNextLine()) {
				lectura.add(scanner.nextLine());
			}
			scanner.close();
		} catch (Exception ex) {
			System.out.println("");
		}

		return lectura;
	}
	//Convierte texto en una matriz
	private String[][] puntajeModificar() {
		ArrayList<String> puntaje = leerPuntaje();
		String[][] tabla = new String[10][4];
		for (int i = 0; i < 10; i++) {
			String[] partes = puntaje.get(i).split("\t");
			for (int j = 0; j < 4; j++) {
				tabla[i][j] = partes[j];
			}
		}
		return tabla;
	}

	// Sobreescribe texto con los nuevos puntajes (si es necesario)
	private void guardarPuntaje(String texto) {
		try {
			FileWriter puntaje = new FileWriter("puntaje.txt");
			puntaje.write(texto);
			puntaje.close();
		} catch (Exception ex) {
			System.out.println("");
		}

	}

	// Modifica y posteriormente guarda puntaje (si es necesario)
	private String[][] modificarPuntaje() {
		String tabla[][] = puntajeModificar();
		for (int i = 0; i < 10; i++) {
			if (this.ptje.getPuntos() > Integer.parseInt(tabla[i][1])) {
				tabla[i][0] = this.ptje.getJugador();
				tabla[i][1] = String.valueOf(this.ptje.getPuntos());
				tabla[i][2] = String.valueOf(this.ptje.getTurnos());
				tabla[i][3] = this.ptje.getTiempoJuego();
				guardarPuntaje((mostrarPuntaje()));
				return tabla;
			}
		}
		String[][] a = new String[2][2];
		a[1][1] = "void";
		return a;
	}

	// retorna true si el puntaje se modifico
	public boolean puntajeModificado() {
		String prueba[][] = modificarPuntaje();
		if (prueba[1][1].equals("void")) {
			return false;
		}
		return true;
	}
	//Convierte matriz en una string para mostrar en pantalla
	public String mostrarPuntaje() {
		String[][] lectura = puntajeModificar();
		String pantalla = "";
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 4; j++) {
				if (j == 3) {
					pantalla += lectura[i][j];
				} else {
					pantalla += lectura[i][j] + "\t";
				}
			}
			if (i != 9) {
				pantalla += "\n";
			}
		}
		return pantalla;
	}
}
