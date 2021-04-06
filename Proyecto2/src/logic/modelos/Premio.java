package logic.modelos;

public class Premio {
	private int id_premio;
	private int id_central;
	private String nombre_premio;
	private int puntuacion_req;
	private int ticket_req;
	private int limite;
	
	public Premio() {
		super();
	}
	
	public Premio(int id_central, String nombre_premio, int puntuacion_req, int ticket_req, int limite) {
		super();
		this.id_central = id_central;
		this.nombre_premio = nombre_premio;
		this.puntuacion_req = puntuacion_req;
		this.ticket_req = ticket_req;
		this.limite = limite;
	}

	public Premio(int id_premio, int id_central, String nombre_premio, int puntuacion_req, int ticket_req, int limite) {
		super();
		this.id_premio = id_premio;
		this.id_central = id_central;
		this.nombre_premio = nombre_premio;
		this.puntuacion_req = puntuacion_req;
		this.ticket_req = ticket_req;
		this.limite = limite;
	}

	public int getId_premio() {
		return id_premio;
	}

	public void setId_premio(int id_premio) {
		this.id_premio = id_premio;
	}

	public int getId_central() {
		return id_central;
	}

	public void setId_central(int id_central) {
		this.id_central = id_central;
	}

	public String getNombre_premio() {
		return nombre_premio;
	}

	public void setNombre_premio(String nombre_premio) {
		this.nombre_premio = nombre_premio;
	}

	public int getPuntuacion_req() {
		return puntuacion_req;
	}

	public void setPuntuacion_req(int puntuacion_req) {
		this.puntuacion_req = puntuacion_req;
	}

	public int getTicket_req() {
		return ticket_req;
	}

	public void setTicket_req(int ticket_req) {
		this.ticket_req = ticket_req;
	}

	public int getLimite() {
		return limite;
	}

	public void setLimite(int limite) {
		this.limite = limite;
	}
	
	
}
