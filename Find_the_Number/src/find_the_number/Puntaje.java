package find_the_number;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Puntaje {
	private int turnos;
	private String tiempoJuego;
	private String jugador;
	private int puntos;
	public int getTurnos() {
		return turnos;
	}
	public void setTurnos(int turnos) {
		this.turnos = turnos;
	}
	public String getTiempoJuego() {
		return tiempoJuego;
	}
	public void setTiempoJuego(String tiempoJuego) {
		this.tiempoJuego = tiempoJuego;
	}
	public String getJugador() {
		return jugador;
	}
	public void setJugador(String jugador) {
		this.jugador = jugador;
	}
	public int getPuntos() {
		return puntos;
	}
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	
}
