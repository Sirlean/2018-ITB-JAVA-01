package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import util.ConnectionFactory;

public class UsuarioDAO {

	Connection conexao = new ConnectionFactory().getConnection();
	PreparedStatement statement = null;
	ResultSet rs = null;
	
	Usuario usuario = null;
	
	public UsuarioDAO() {

	}

	public int insereUsuario(Usuario usuario){
		int resultado = 0;
		try {
			statement = conexao.prepareStatement("INSERT INTO tblUSUARIO (idUsuario, senha, nivelAcesso, idFunc, statusFunc) VALUES (?,?,?,?,1)");
			
			statement.setString(1, usuario.getIdUsuario());
			statement.setString(2, usuario.getSenha());
			statement.setInt(3, usuario.getNivelAcesso());
			statement.setInt(4, usuario.getIdFunc());
			
			resultado = statement.executeUpdate();
			if (resultado == 1) {
				JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
			}
			statement.close();
			conexao.close();
			
			return resultado;
			
		} catch (SQLException e) {

			try {
				if (conexao != null) {
					conexao.rollback();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			e.printStackTrace();
		}
		return 0;
		
	}
	 public Usuario buscarUsuario(String usu) {

			try {
			
				statement = conexao.prepareStatement("SELECT * FROM tblUsuario WHERE idUsuario = ?");
				statement.setString(1, usu);

				rs = statement.executeQuery();

				if (rs.next()) {
					usuario = new Usuario();
					usuario.setIdUsuario(rs.getString("idUsuario"));
					usuario.setSenha(rs.getString("senha"));
					usuario.setNivelAcesso(rs.getInt("nivelAcesso"));
					usuario.setIdFunc(rs.getInt("idFunc"));
					usuario.setStatusFunc(rs.getInt("statusFunc"));
					
				} else {
					JOptionPane.showMessageDialog(null, "Código não encontrado.");
				}
				rs.close();
				statement.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return usuario;
		}
	
	
}
