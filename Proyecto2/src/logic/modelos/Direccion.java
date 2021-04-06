package logic.modelos;

public class Direccion {
	private int id_direccion;
	private String pais;
	private String region;
	private int codigo_postal;
	
	public Direccion() {
		super();
	}

	public Direccion(String pais, String region, int codigo_postal) {
		super();
		this.pais = pais;
		this.region = region;
		this.codigo_postal = codigo_postal;
	}

	public int getId_direccion() {
		return id_direccion;
	}

	public void setId_direccion(int id_direccion) {
		this.id_direccion = id_direccion;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public int getCodigo_postal() {
		return codigo_postal;
	}

	public void setCodigo_postal(int codigo_postal) {
		this.codigo_postal = codigo_postal;
	}
	
	
}
