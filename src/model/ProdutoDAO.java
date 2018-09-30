package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import util.ConnectionFactory;

public class ProdutoDAO {

	Connection conexao = new ConnectionFactory().getConnection();
	private PreparedStatement statement;
	private ResultSet rs;

	Produto produto = null;

	public ProdutoDAO() {
	}

	public int insereProduto(Produto produto) {
		int resultado = 0;
		try {
			statement = conexao
					.prepareStatement("INSERT INTO tblProduto VALUES (seqPKProduto.NEXTVAL, ?, ?, ?, 'Ativo')");

			statement.setString(1, produto.getCodBarras());
			statement.setString(2, produto.getNomeProd());
			statement.setDouble(3, produto.getPreco());

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

	public int alteraProduto(Produto produto) {
		int resultado = 0;
		try {
			statement = conexao
					.prepareStatement("UPDATE tblProduto SET codBarras = ?, nomeProd = ?, preco = ?, statusProd = ? WHERE codProd = ?");

			statement.setString(1, produto.getCodBarras());
			statement.setString(2, produto.getNomeProd());
			statement.setDouble(3, produto.getPreco());
			statement.setString(4, produto.getStatusProd());

			statement.setInt(5, produto.getCodProd());

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

	public List<Produto> listarProduto(int codProd, String codBarras, String nomeProd) 
	{
		List<Produto> produtos = new ArrayList<Produto>();

		try {
			statement = conexao
					.prepareStatement("SELECT * FROM tblProduto WHERE codProd = ? OR codBarras = ? OR nomeProd LIKE '%"
							+ nomeProd + "%'");
			statement.setInt(1, codProd);
			statement.setString(2, codBarras);
			rs = statement.executeQuery();

			while (rs.next()) {
				produto = new Produto();
				produto.setCodProd(rs.getInt("codProd"));
				produto.setCodBarras(rs.getString("codBarras"));
				produto.setNomeProd(rs.getString("NomeProd"));
				produto.setPreco(rs.getDouble("preco"));
				produto.setStatusProd(rs.getString("statusProd"));

				produtos.add(produto);
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return produtos;
	}

}
