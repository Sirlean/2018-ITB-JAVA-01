package control;

import javax.swing.JOptionPane;

import view.FormPrincipal;
import view.LoginForm;
import view.PainelEntrada;
import view.PainelLoginEntrada;
import model.Usuario;
import model.UsuarioDAO;

public class UsuarioController {

	public UsuarioController() {

	}

	public static void insereUsuario(String idUsuario, String senha,
			int nivelAcesso, int idFunc) {

		Usuario usuario = new Usuario();

		usuario.setIdUsuario(idUsuario);
		usuario.setSenha(senha);
		usuario.setNivelAcesso(nivelAcesso);
		usuario.setIdFunc(idFunc);

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.insereUsuario(usuario);

	}

	public static boolean loginUsuario(String usu) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscarUsuario(usu);

		if (!usuario.getIdUsuario().equals(LoginForm.tfUsuario.getText()) || 
				  usuario.getSenha().equals(String.valueOf(LoginForm.pfSenha.getPassword()))) {
			return false;
		
		}
		else{
			return true;			
		}
		
	}
	
}
