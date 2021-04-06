package logic.modelos;

public class Arcade {
	
	//region Atributos
	private int id_arcade;
	private int id_central;
	private int id_categoria;
	private String nombre;
	private int ticket_imp;
	private int req_monedas;
	//endregion
	
	//region constructores
	public Arcade() {
		super();
	}
	
	public Arcade(int id_arcade, int id_central, int id_categoria, String nombre, int ticket_imp, int req_monedas) {
		super();
		this.id_arcade = id_arcade;
		this.id_central = id_central;
		this.id_categoria = id_categoria;
		this.nombre = nombre;
		this.ticket_imp = ticket_imp;
		this.req_monedas = req_monedas;
	}
	
	public Arcade(int id_central, int id_categoria, String nombre, int ticket_imp, int req_monedas) {
		super();
		this.id_central = id_central;
		this.id_categoria = id_categoria;
		this.nombre = nombre;
		this.ticket_imp = ticket_imp;
		this.req_monedas = req_monedas;
	}
	//endregion
	
	
	//region getters and setters
	public int getId_arcade() {
		return id_arcade;
	}
	
	public void setId_arcade(int id_arcade) {
		this.id_arcade = id_arcade;
	}
	public int getId_central() {
		return id_central;
	}
	public void setId_central(int id_central) {
		this.id_central = id_central;
	}
	public int getId_categoria() {
		return id_categoria;
	}
	public void setId_categoria(int id_categoria) {
		this.id_categoria = id_categoria;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getTicket_imp() {
		return ticket_imp;
	}
	public void setTicket_imp(int ticket_imp) {
		this.ticket_imp = ticket_imp;
	}
	public int getReq_monedas() {
		return req_monedas;
	}
	public void setReq_monedas(int req_monedas) {
		this.req_monedas = req_monedas;
	}
	
	//endregion
	
	//region functions
	
	//endregion	
}
