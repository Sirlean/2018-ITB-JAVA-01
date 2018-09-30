package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import util.ConnectionFactory;

public class FuncionarioDAO {
	
	Connection conexao = new 
			ConnectionFactory().getConnection();
	
	private PreparedStatement statement;
	private ResultSet rs;

	Funcionario func = null;


	
	public FuncionarioDAO() {

	}

	
	
	
	
	
	
	
	public int insereFunc(Funcionario func) {
		int resultado = 0;
		try {

			// CRIA UMA STRING COM O COMANDO SQL PARA INSERIR UM REGISTRO
			statement = conexao
					.prepareStatement("INSERT INTO tblFUNCIONARIO (idFunc, nome, sexo, dataNasc, cpf, salario, email, telefone, foto, idCargo, statusFunc)"
							+ "VALUES (seqPKFUNCIONARIO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, 1)");

			// O NÚMERO 1 REPRESENTA O PRIMEIRO SIMBOLO DE ? QUE APARECE NA
			// STRING
			statement.setString(1, func.getNome());
			statement.setString(2, func.getSexo());
			statement.setDate(3, func.getDataNasc());
			statement.setString(4, func.getCpf());
			statement.setDouble(5, func.getSalario());
			statement.setString(6, func.getEmail());
			statement.setString(7, func.getTelefone());
			statement.setString(8, func.getFoto());
			statement.setInt(9, func.getIdCargo());

			// COMO A EXECUÇÃO DO COMANDO, SE TUDO DER CERTO RETORNARÁ O VALOR 1
			// E ESSE VALOR SERÁ ADICIONADO NA VARIAVEL "resultado"
			resultado = statement.executeUpdate();
			if (resultado == 1) {
				JOptionPane.showMessageDialog(null, "Gravado com sucesso !!!");
			}
			conexao.close();
			statement.close();

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

	public int alteraFunc(Funcionario funcionario) {
		int resultado = 0;
		try {

			statement = conexao
					.prepareStatement("UPDATE tblFuncionario SET nome = ?, sexo = ?, dataNasc = ?, cpf = ?, salario = ?, email = ?, telefone = ?, foto = ?, idCargo = ?, statusFunc = ? WHERE idFunc = ?");

			statement.setString(1, funcionario.getNome());
			statement.setString(2, funcionario.getSexo());
			statement.setDate(3, funcionario.getDataNasc());
			statement.setString(4, funcionario.getCpf());
			statement.setDouble(5, funcionario.getSalario());
			statement.setString(6, funcionario.getEmail());
			statement.setString(7, funcionario.getTelefone());
			statement.setString(8, funcionario.getFoto());
			statement.setInt(9, funcionario.getIdCargo());
			statement.setInt(10, funcionario.getStatusFunc());
			
			statement.setInt(11, funcionario.getIdFunc());

			resultado = statement.executeUpdate();
			if (resultado == 1) {
				JOptionPane.showMessageDialog(null, "Alterado com sucesso !!!");
			}
			conexao.close();
			statement.close();
			return resultado;

		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return 0;
	}

	public Funcionario buscaPorId(int codigo) {

		String sql = "SELECT * FROM tblFuncionario where idFunc = ?";

		try {

			statement = conexao.prepareStatement(sql);
			statement.setInt(1, codigo);

			rs = statement.executeQuery();

			if (rs.next()) {
				func = new Funcionario();
				func.setIdFunc(rs.getInt(1));
				func.setNome(rs.getString(2));
				func.setSexo(rs.getString(3));
				func.setDataNasc(rs.getDate(4));
				func.setCpf(rs.getString(5));
				func.setSalario(rs.getDouble(6));
				func.setEmail(rs.getString(7));
				func.setTelefone(rs.getString(8));
				func.setFoto(rs.getString(9));
				func.setIdCargo(rs.getInt(10));
				func.setStatusFunc(rs.getInt(11));

			} else {
				JOptionPane.showMessageDialog(null, "Código não encontrado.");
			}

			rs.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return func;
	}
	
	 public List<Funcionario> listarFuncionarios(int codigo, String nomeFunc, String cpf) {

		List<Funcionario> funcionarios = new ArrayList<Funcionario>();

		try {
		
			statement = conexao.prepareStatement("SELECT * FROM tblFuncionario WHERE idFunc = ? OR nome LIKE '%"+nomeFunc+"%' OR cpf = ?");
			statement.setInt(1, codigo);
			statement.setString(2, cpf);
			rs = statement.executeQuery();

			
			while (rs.next()) {
				
				func = new Funcionario();
				func.setIdFunc(rs.getInt("idFunc"));
				func.setNome(rs.getString("nome"));
				func.setSexo(rs.getString("sexo"));
				func.setDataNasc(rs.getDate("dataNasc"));
				func.setCpf(rs.getString("cpf"));
				func.setSalario(rs.getDouble("salario"));
				func.setEmail(rs.getString("email"));
				func.setTelefone(rs.getString("telefone"));
				func.setFoto(rs.getString("foto"));
				func.setIdCargo(rs.getInt("idCargo"));
				func.setStatusFunc(rs.getInt("statusFunc"));

				funcionarios.add(func);			
			}
			rs.close();
			statement.close();
			// conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

if (funcionarios.size() == 1) {
	JOptionPane.showMessageDialog(null, "só tem um.");
}
		return funcionarios;
	}
	 public Funcionario buscarFuncionario(int codigo, String cpf) {

		try {
		
			statement = conexao.prepareStatement("SELECT * FROM tblFuncionario WHERE idFunc = ? OR cpf = ?");
			statement.setInt(1, codigo);
			statement.setString(2, cpf);
			rs = statement.executeQuery();

			if (rs.next()) {
				func = new Funcionario();
				func.setIdFunc(rs.getInt("idFunc"));
				func.setNome(rs.getString("nome"));
				func.setSexo(rs.getString("sexo"));
				func.setDataNasc(rs.getDate("dataNasc"));
				func.setCpf(rs.getString("cpf"));
				func.setSalario(rs.getDouble("salario"));
				func.setEmail(rs.getString("email"));
				func.setTelefone(rs.getString("telefone"));
				func.setFoto(rs.getString("foto"));
				func.setIdCargo(rs.getInt("idCargo"));
				func.setStatusFunc(rs.getInt("statusFunc"));

			}
			rs.close();
			statement.close();
			// conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return func;
	}
	 

}
