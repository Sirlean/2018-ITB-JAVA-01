package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import util.ConnectionFactory;

public class CargoDAO {

	private static CargoDAO instance;
	Connection conexao = new ConnectionFactory().getConnection();
	private PreparedStatement statement;
	private ResultSet rs;
	// CRIA UM OBJETO QUE RECEBERÁ OS DADOS VINDOS DO BANCO DE DADOS.
	Cargo cargo = null;

	public CargoDAO() {
		super();
	}

	public static CargoDAO getInstance() {
		if (instance == null) {
			instance = new CargoDAO();
		}
		return instance;
	}

	public Cargo buscaPorId(int codigo) {

		// CRIA UMA STRING COM O COMANDO SQL PARA CONSULTAR UM REGISTRO PELO
		// CÓDIGO.
		String sql = "SELECT * FROM tblCARGO where idCargo = ?";

		try {
			// FAZ COM QUE O STATEMENT RECEBA O CONTEÚDO DA CONSULTA
			statement = conexao.prepareStatement(sql);

			// INDICA QUE O VALOR PASSADO PARA CONSULTA É O CÓDIGO PASSADO PELO
			// MÉTODO.
			// O nº1 REPRESENTA O PRIMEIRO SINAL DE ? ENCONTRADO NA STRING SQL.
			statement.setInt(1, codigo);

			// PASSA O QUE FOI EXECUTADO E RETORNADO PELO STATEMENT PARA O
			// RECORDSET.
			rs = statement.executeQuery();

			// CARREGA O OBJETO - criado a partir da classe com os GET's e SET's
			// - COM OS
			// DADOS DO BANCO ENQUANTO O RETORNO DO RECORDSET FOR VERDADEIRO.
			if (rs.next()) {
				cargo = new Cargo();
				// O nº1 REPRESENTA O PRIMEIRO CAMPO DA TABELA RETORNADO PELA
				// CONSULTA.
				// E ASSIM POR DIANTE PARA OS OUTROS NÚMEROS E CAMPOS.
				cargo.setIdCargo(rs.getInt(1));
				cargo.setNomeCargo(rs.getString(2));
				cargo.setStatusCargo(rs.getInt(3));
			} else {
				JOptionPane.showMessageDialog(null, "Código não encontrado.");
			}

			// DEPOIS DE FEITA A CONSULTA E CARREGO O DESEJADO O RECORDSET E O
			// STATEMENT SÃO FECHADOS.
			rs.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cargo;
	}

	public Cargo buscaPorNome(String nomeCargo) {

		String sql = "SELECT * FROM tblCARGO where nomeCargo = ?";

		try {
			statement = conexao.prepareStatement(sql);
			statement.setString(1, nomeCargo);
			rs = statement.executeQuery();

			if (rs.next()) {
				cargo = new Cargo();
				cargo.setIdCargo(rs.getInt(1));
				cargo.setNomeCargo(rs.getString(2));
				cargo.setStatusCargo(rs.getInt(3));
			} else {
				JOptionPane.showMessageDialog(null, "Cargo não encontrado.");
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cargo;
	}

	public List<Cargo> listarTodos() {

		// CRIA UMA LISTA DE REGISTROS(classe List) E OS ALOCA E UMA
		// MATRIZ(classe ArrayList).
		List<Cargo> cargos = new ArrayList<Cargo>();

		try {
												//BUSCA TODOS OS CARGOS ATIVOS
			statement = conexao.prepareStatement("SELECT * FROM tblCARGO where statusCargo = 1 ORDER BY nomeCargo");
			rs = statement.executeQuery();

			// ENQUANTO HOUVER REGISTROS SENDO RETORNADOS A MATRIZ CRIADA
			// ANTERIORMENTE
			// SERÁ PREENCHIADA COM VALORES.
			while (rs.next()) {
				cargo = new Cargo();
				cargo.setIdCargo(rs.getInt("idCargo"));
				cargo.setNomeCargo(rs.getString("nomeCargo"));
				cargo.setStatusCargo(rs.getInt("statusCargo"));

				// ADICIONA CADA LINHA DE REGISTRO RETORNADA DA TABELA À MATRIZ.
				cargos.add(cargo);
			}
			rs.close();
			statement.close();
			// conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cargos;
	}
	
	public List<Cargo> listarTodosCargos() {

		// CRIA UMA LISTA DE REGISTROS(classe List) E OS ALOCA E UMA
		// MATRIZ(classe ArrayList).
		List<Cargo> cargos = new ArrayList<Cargo>();

		try {		
												//BUSCA TODOS OS CARGOS ATIVOS SEM DISTINÇÃO
			statement = conexao.prepareStatement("SELECT * FROM tblCARGO ORDER BY idCargo");
			rs = statement.executeQuery();

			// ENQUANTO HOUVER REGISTROS SENDO RETORNADOS A MATRIZ CRIADA
			// ANTERIORMENTE
			// SERÁ PREENCHIADA COM VALORES.
			while (rs.next()) {
				cargo = new Cargo();
				cargo.setIdCargo(rs.getInt("idCargo"));
				cargo.setNomeCargo(rs.getString("nomeCargo"));
				cargo.setStatusCargo(rs.getInt("statusCargo"));

				// ADICIONA CADA LINHA DE REGISTRO RETORNADA DA TABELA À MATRIZ.
				cargos.add(cargo);
			}
			rs.close();
			statement.close();
			// conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cargos;
	}

	public List<Cargo> listarTodosPorNome(String nome_Cargo) {

		// CRIA UMA LISTA DE REGISTROS(classe List) E OS ALOCA E UMA
		// MATRIZ(classe ArrayList).
		List<Cargo> cargos = new ArrayList<Cargo>();

		try {
												//BUSCA TODOS OS CARGOS ATIVOS FILTRANDO PELO NOME
			statement = conexao.prepareStatement("SELECT * FROM tblCARGO WHERE nomeCargo LIKE '%"+nome_Cargo+"%'");
			rs = statement.executeQuery();

			// ENQUANTO HOUVER REGISTROS SENDO RETORNADOS, A MATRIZ CRIADA
			// ANTERIORMENTE SERÁ PREENCHIADA COM VALORES.
			while (rs.next()) {
				cargo = new Cargo();
				cargo.setIdCargo(rs.getInt("idCargo"));
				cargo.setNomeCargo(rs.getString("nomeCargo"));
				cargo.setStatusCargo(rs.getInt("statusCargo"));

				// ADICIONA CADA LINHA DE REGISTRO RETORNADA DA TABELA À MATRIZ.
				cargos.add(cargo);
			}
			rs.close();
			statement.close();
			// conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cargos;
	}
	
	public Vector<Cargo> carregaComboCargo()
			throws Exception {

		// CRIA UM VETOR DE REGISTROS(classe Vector) E OS ALOCA E UM OBJETO.
		Vector<Cargo> cargos = new Vector<Cargo>();

		// CRIA UMA STRING COM O COMANDO SQL PARA CONSULTAR DADOS.
		try {
			statement = conexao
					.prepareStatement("SELECT * FROM tblCARGO ORDER BY nomeCargo");
			rs = statement.executeQuery();

			while (rs.next()) {
				cargo = new Cargo();
				cargo.setIdCargo(rs.getInt(1));
				cargo.setNomeCargo(rs.getString("nomeCargo"));
				cargos.add(cargo);
				
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cargos;
	}

	public int insereCargo(Cargo cargo) {
		int resultado = 0;
		try {
			
			// CRIA UMA STRING COM O COMANDO SQL PARA INSERIR UM REGISTRO
			statement = conexao.prepareStatement("INSERT INTO tblCARGO VALUES (seqPKidCargo.NEXTVAL,?, 1)");

			// O NÚMERO 1 REPRESENTA O PRIMEIRO SIMBOLO DE ? QUE APARECE NA STRING
			statement.setString(1, cargo.getNomeCargo());
			
			//COMO A EXECUÇÃO DO COMANDO, SE TUDO DER CERTO RETORNARÁ O VALOR 1 
			//E ESSE VALOR SERÁ ADICIONADO NA VARIAVEL "resultado"
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

	public int alteraCargo(Cargo cargo) {
		int resultado = 0;
		try {
			// CRIA UMA STRING COM O COMANDO SQL PARA ATUALIZAR UM REGISTRO
			statement = conexao.prepareStatement("UPDATE tblCARGO SET nomeCargo = ?, statusCargo = 1 WHERE idCargo = ?");

			// O NÚMERO 1 REPRESENTA O PRIMEIRO SIMBOLO DE ? QUE APARECE NA STRING
			// EM SEGUIDA DEVE SER INFORMADO DE ONDE VIRÁ O DADO QUE SERÁ ARMAZENADO NO CAMPO DA TABELA.
			statement.setString(1, cargo.getNomeCargo());
			
			// O NÚMERO 2 REPRESENTA O SEGUNDO SIMBOLO DE ? QUE APARECE NA STRING
			statement.setInt(2, cargo.getIdCargo());

			resultado = statement.executeUpdate();
			if (resultado == 1) {
				JOptionPane.showMessageDialog(null, "Alterado com sucesso !!!");
			}
			//FECHAMENTOS
			conexao.close();
			statement.close();

			return resultado;

		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return 0;
	}
	public int desativaCargo(Cargo cargo) {
		int resultado = 0;
		try {
			statement = conexao.prepareStatement("UPDATE tblCARGO SET nomeCargo = ?, statusCargo = 0 WHERE idCargo = ?");

			statement.setString(1, cargo.getNomeCargo());
			statement.setInt(2, cargo.getIdCargo());

			resultado = statement.executeUpdate();
			if (resultado == 1) {
				JOptionPane.showMessageDialog(null, "Cargo desativado com sucesso !!!");
			}

			conexao.close();
			statement.close();

			return resultado;

		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return 0;
	}

}
