package logic.modelos;

import java.sql.Time;

public class Puntuaciones {
	private int id_puntuacion;
	private int id_central;
	private int id_jugador;
	private String nombre;
	private int monedas_usadas;
	private Time tiempo_jugado;
	
	public Puntuaciones() {
		super();
	}
	
	public Puntuaciones(int id_central, int id_jugador, String nombre, int monedas_usadas) {
		super();
		this.id_central = id_central;
		this.id_jugador = id_jugador;
		this.nombre = nombre;
		this.monedas_usadas = monedas_usadas;
	}

	public Puntuaciones(int id_central, int id_jugador, String nombre, int monedas_usadas, Time tiempo_jugado) {
		super();
		this.id_central = id_central;
		this.id_jugador = id_jugador;
		this.nombre = nombre;
		this.monedas_usadas = monedas_usadas;
		this.tiempo_jugado = tiempo_jugado;
	}

	public Puntuaciones(int id_puntuacion, int id_central, int id_jugador, String nombre, int monedas_usadas,
			Time tiempo_jugado) {
		super();
		this.id_puntuacion = id_puntuacion;
		this.id_central = id_central;
		this.id_jugador = id_jugador;
		this.nombre = nombre;
		this.monedas_usadas = monedas_usadas;
		this.tiempo_jugado = tiempo_jugado;
	}

	public int getId_puntuacion() {
		return id_puntuacion;
	}

	public void setId_puntuacion(int id_puntuacion) {
		this.id_puntuacion = id_puntuacion;
	}

	public int getId_central() {
		return id_central;
	}

	public void setId_central(int id_central) {
		this.id_central = id_central;
	}

	public int getId_jugador() {
		return id_jugador;
	}

	public void setId_jugador(int id_jugador) {
		this.id_jugador = id_jugador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getMonedas_usadas() {
		return monedas_usadas;
	}

	public void setMonedas_usadas(int monedas_usadas) {
		this.monedas_usadas = monedas_usadas;
	}

	public Time getTiempo_jugado() {
		return tiempo_jugado;
	}

	public void setTiempo_jugado(Time tiempo_jugado) {
		this.tiempo_jugado = tiempo_jugado;
	}
}
