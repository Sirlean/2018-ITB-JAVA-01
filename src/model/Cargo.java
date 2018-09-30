package model;

public class Cargo {

	int idCargo;
	String nomeCargo;
	int statusCargo;
	
	public Cargo() {
	
	}

	public int getIdCargo() {
		return idCargo;
	}

	public void setIdCargo(int idCargo) {
		this.idCargo = idCargo;
	}

	public String getNomeCargo() {
		return nomeCargo;
	}

	public void setNomeCargo(String nomeCargo) {
		this.nomeCargo = nomeCargo;
	}

	public int getStatusCargo() {
		return statusCargo;
	}

	public void setStatusCargo(int statusCargo) {
		this.statusCargo = statusCargo;
	}

	public String toString() {
		return nomeCargo;
	}
	
	

}
