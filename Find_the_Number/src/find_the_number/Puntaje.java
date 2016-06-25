package find_the_number;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Puntaje {
	private int turnos;
	private String tiempoJuego;
	private String jugador;
	private int puntos;
	Puntaje(int turnos,String tiempoJuego){
		this.turnos=turnos;
		this.puntos=1500000/((turnos)*Integer.parseInt(tiempoJuego));
		tiempoJuego=String.format("%02d:%02d", 
			    TimeUnit.SECONDS.toMinutes((Integer.parseInt(tiempoJuego))),
			    TimeUnit.SECONDS.toSeconds((Integer.parseInt(tiempoJuego)) - 
			    TimeUnit.MINUTES.toSeconds(TimeUnit.SECONDS.toMinutes((Integer.parseInt(tiempoJuego)))))
			);
		this.tiempoJuego=tiempoJuego;
		this.jugador=" ";
	}
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
