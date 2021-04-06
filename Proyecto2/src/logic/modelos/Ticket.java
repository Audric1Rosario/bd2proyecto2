package logic.modelos;

public class Ticket {
	private int id_ticket;
	private int id_arcade;
	private int id_jugador;
	private String nombre;
	
	public Ticket() {
		super();
	}

	public Ticket(int id_arcade, int id_jugador, String nombre) {
		super();
		this.id_arcade = id_arcade;
		this.id_jugador = id_jugador;
		this.nombre = nombre;
	}

	public Ticket(int id_ticket, int id_arcade, int id_jugador, String nombre) {
		super();
		this.id_ticket = id_ticket;
		this.id_arcade = id_arcade;
		this.id_jugador = id_jugador;
		this.nombre = nombre;
	}

	public int getId_ticket() {
		return id_ticket;
	}

	public void setId_ticket(int id_ticket) {
		this.id_ticket = id_ticket;
	}

	public int getId_arcade() {
		return id_arcade;
	}

	public void setId_arcade(int id_arcade) {
		this.id_arcade = id_arcade;
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
	
	
	
}
