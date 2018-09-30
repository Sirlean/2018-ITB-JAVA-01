package control;

import java.util.List;

import javax.swing.ImageIcon;

import tabelaDados.ProdutoTableModel;
import view.FuncionarioForm;
import view.ProdutoForm;

import model.CargoDAO;
import model.Funcionario;
import model.FuncionarioDAO;
import model.Produto;
import model.ProdutoDAO;




public class ProdutoController {

	public static List<Produto> listaProdutos;
	public ProdutoController() {
		// TODO Auto-generated constructor stub
	}

 
 public static void inserirProduto(String codBarras, String nomeProd, double preco) 
	{
		Produto produto = new Produto();
		
		produto.setCodBarras(codBarras);
		produto.setNomeProd(nomeProd);
		produto.setPreco(preco);

		ProdutoDAO produtoDAO = new ProdutoDAO();
		produtoDAO.insereProduto(produto);
		limpar();

	}

	public static void alteraProduto(int codProd, String codBarras, String nomeProd,
			double preco, String statusProd) 
	{
		Produto produto = new Produto();
		
		produto.setCodProd(codProd);
		produto.setCodBarras(codBarras);
		produto.setNomeProd(nomeProd);
		produto.setPreco(preco);
		produto.setStatusProd(statusProd);

		ProdutoDAO produtoDAO = new ProdutoDAO();
		produtoDAO.alteraProduto(produto);
	}
	
	
	 public static void buscaProduto(int codProd, String codBarras, String nomeProd) {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		List<Produto> produtos = produtoDAO.listarProduto(codProd, codBarras, nomeProd);
		
		ProdutoForm.tfCodProd.setText(String.valueOf(produtos.get(0).getCodProd()));
 		ProdutoForm.tfCodBarras.setText(produtos.get(0).getCodBarras());
 		ProdutoForm.tfNomeProd.setText(produtos.get(0).getNomeProd());
 		ProdutoForm.tfPreco.setText(String.valueOf(produtos.get(0).getPreco()));
		ProdutoForm.cboStatusProd.setSelectedItem(produtos.get(0).getStatusProd());
	} 
	 

 	public static void grideProduto(int codProd, String codBarras, String nomeProd) {

		listaProdutos = new ProdutoDAO().listarProduto(codProd, codBarras, nomeProd);
		if (listaProdutos != null) {
			ProdutoForm.tblProdutos.setModel(new ProdutoTableModel(listaProdutos));
			
			// DEFINE A LARGURA DA COLUNA NA TABELA.
			ProdutoForm.tblProdutos.getColumnModel().getColumn(0)
					.setMaxWidth(50);
			ProdutoForm.tblProdutos.getColumnModel().getColumn(1)
					.setMaxWidth(110);
			ProdutoForm.tblProdutos.getColumnModel().getColumn(2)
					.setMaxWidth(280);
			ProdutoForm.tblProdutos.getColumnModel().getColumn(3)
					.setMaxWidth(65);
			ProdutoForm.tblProdutos.getColumnModel().getColumn(4)
					.setMaxWidth(55);
			
		}
		
	}
 	public static void limpar(){
 		ProdutoForm.tfCodProd.setText("");
 		ProdutoForm.tfCodBarras.setText("");
 		ProdutoForm.tfNomeProd.setText("");
 		ProdutoForm.tfPreco.setText("");
 	}
 
}
