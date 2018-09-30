package model;

public class Usuario {

	String idUsuario;
	String senha;
	int nivelAcesso;
	int idFunc;
	int statusFunc;

	public Usuario() {

	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getNivelAcesso() {
		return nivelAcesso;
	}

	public void setNivelAcesso(int nivelAcesso) {
		this.nivelAcesso = nivelAcesso;
	}

	public int getIdFunc() {
		return idFunc;
	}

	public void setIdFunc(int idFunc) {
		this.idFunc = idFunc;
	}

	public int getStatusFunc() {
		return statusFunc;
	}

	public void setStatusFunc(int statusFunc) {
		this.statusFunc = statusFunc;
	}
	

}
