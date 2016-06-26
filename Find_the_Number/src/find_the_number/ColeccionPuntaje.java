package find_the_number;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ColeccionPuntaje {
	private Puntaje ptje;

	public Puntaje getPtje() {
		return ptje;
	}

	public void setPtje(Puntaje ptje) {
		this.ptje = ptje;
	}

	ColeccionPuntaje() {
	};

	ColeccionPuntaje(Puntaje puntaje) {
		this.ptje = puntaje;
	}

	// Lee texto de archivo,retorna string
	private ArrayList<String> leerPuntaje() {
		ArrayList<String> lectura = new ArrayList<String>();
		File puntaje = new File("D:\\puntaje.txt");
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

	// Escribe en el texto la string de entrada
	private void guardarPuntaje(String texto) {
		try {
			FileWriter puntaje = new FileWriter("D:\\puntaje.txt");
			puntaje.write(texto);
			puntaje.close();
		} catch (Exception ex) {
			System.out.println("");
		}
	}

	// Convierte texto en una matriz
	public String[][] puntajeMatriz() {
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

	// Retorna true si el puntaje se debe modificar
	public boolean puntajeAlto() {
		for (int i = 0; i < 10; i++) {
			if (this.ptje.getPuntos() > Integer.parseInt(puntajeMatriz()[i][3])) {
				return true;
			}
		}
		return false;
	}

	// Modifica puntaje
	public void modificarPuntaje() {
		String tabla[][] = puntajeMatriz();
		int i = 0;

		for (i = 0; i < 10; i++) {
			if (this.ptje.getPuntos() > Integer.parseInt(tabla[i][3])) {
				break;
			}
		}
		if (i != 9) {
			for (int j = 9; j > i; j--) {

				tabla[j][0] = tabla[j - 1][0];
				tabla[j][1] = tabla[j - 1][1];
				tabla[j][2] = tabla[j - 1][2];
				tabla[j][3] = tabla[j - 1][3];

			}

			tabla[i][0] = this.ptje.getJugador();
			tabla[i][1] = String.valueOf(this.ptje.getTurnos());
			tabla[i][2] = this.ptje.getTiempoJuego();
			tabla[i][3] = String.valueOf(this.ptje.getPuntos());

		}
		textoGuardar(tabla);

	}

	// Convierte matriz a String con el formato del texto y lo guarda
	private void textoGuardar(String lectura[][]) {
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
		guardarPuntaje(pantalla);
	}

	// SIN USO
	/* public String mostrarPuntaje() { String lectura[][] = puntajeMatriz();
	 * String pantalla = ""; for (int i = 0; i < 10; i++) { for (int j = 0; j <
	 * 4; j++) { if (j == 3) { pantalla += lectura[i][j]; } else { pantalla +=
	 * lectura[i][j] + "\t"; } } if (i != 9) { pantalla += "\n"; } } return
	 * pantalla;}
	 */
}
