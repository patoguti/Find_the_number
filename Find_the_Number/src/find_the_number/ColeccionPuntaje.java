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
	ColeccionPuntaje(){};
	ColeccionPuntaje(Puntaje puntaje) {
		this.ptje = puntaje;
	}

	// Extrae texto de archivo
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
	//Convierte texto en una matriz
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
	public boolean puntajeAlto(){
		for (int i=0; i < 10; i++) {
			if (this.ptje.getPuntos() > Integer.parseInt(puntajeMatriz()[i][3])) {
				return true;
			}
		}
		return false;
	}
	
	// Modifica y posteriormente guarda puntaje (si es necesario)
	private String[][] modificarPuntaje() {
		String tabla[][] = puntajeMatriz();
		int i=0;
		boolean flag=true;
		for (i=0; i < 10; i++) {
			if (this.ptje.getPuntos() > Integer.parseInt(tabla[i][3])) {
				break;
			}
		}
		System.out.println("asd"+i);
		if(i<10)flag=true;
				for(int j=9;j>=i;j--){
					
					if(j>0){
						tabla[j][0]=tabla[j-1][0];
						tabla[j][1]=tabla[j-1][1];
						tabla[j][2]=tabla[j-1][2];
						tabla[j][3]=tabla[j-1][3];
						
					}
					if(j==i){
						tabla[i][0] = this.ptje.getJugador();
						tabla[i][1] = String.valueOf(this.ptje.getTurnos());
						tabla[i][2] = this.ptje.getTiempoJuego();
						tabla[i][3] = String.valueOf(this.ptje.getPuntos());
					}				
				}
		textoGuardar(tabla);
		if(flag){
			return tabla;
		}else{
			String[][] a = new String[2][2];
			a[1][1] = "void";
			return a;
		}
			
		
	}

	// retorna true si el puntaje se modifico
	public boolean puntajeModificado() {
		String prueba[][] = modificarPuntaje();
		if (prueba[1][1].equals("void")) {
			return false;
		}
		return true;
	}
	//Convierte matriz para guardar en texto
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
	public String mostrarPuntaje() {
		String lectura[][]=puntajeMatriz();
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
