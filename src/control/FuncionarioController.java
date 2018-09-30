package control;

import java.sql.Date;
import java.util.List;
import javax.swing.ImageIcon;
import model.CargoDAO;
import model.Funcionario;
import model.FuncionarioDAO;
import tabelaDados.FuncionarioTableModel;
import view.FuncionarioForm;
import view.FuncionarioListaForm;
import view.UsuarioForm;

public class FuncionarioController {

	public static List<Funcionario> listaFuncionarios;
	
	public FuncionarioController() {

	}

	public static void insereFuncionario(String nomeFunc, String sexo,
			Date dataNasc, String cpf, double salario, String email,
			String telefone, int idCargo, String foto) 
	{
		Funcionario funcionario = new Funcionario();
		
		funcionario.setNome(nomeFunc);
		funcionario.setSexo(sexo);
		funcionario.setDataNasc(dataNasc);
		funcionario.setCpf(cpf);
		funcionario.setSalario(salario);
		funcionario.setEmail(email);
		funcionario.setTelefone(telefone);
		funcionario.setIdCargo(idCargo);
		funcionario.setFoto(foto);

		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		funcionarioDAO.insereFunc(funcionario);

	}

	public static void alteraFuncionario(int idFunc, String nomeFunc, String sexo,
			Date dataNasc, String cpf, double salario, String email,
			String telefone, String foto, int idCargo, int statusFunc) 
	{
		Funcionario funcionario = new Funcionario();
		
		funcionario.setIdFunc(idFunc);
		funcionario.setNome(nomeFunc);
		funcionario.setSexo(sexo);
		funcionario.setDataNasc(dataNasc);
		funcionario.setCpf(cpf);
		funcionario.setSalario(salario);
		funcionario.setEmail(email);
		funcionario.setTelefone(telefone);
		funcionario.setFoto(foto);
		funcionario.setIdCargo(idCargo);
		funcionario.setStatusFunc(statusFunc);

		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		funcionarioDAO.alteraFunc(funcionario);
	}

	public static void buscaFuncionarioId(int idFunc) {
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscaPorId(idFunc);

		FuncionarioForm.tfCodigo.setText(String.valueOf(funcionario.getIdFunc()));
		FuncionarioForm.tfNome.setText(funcionario.getNome());
		if (funcionario.getSexo().equals("M")) {
			FuncionarioForm.rdbtnM.setSelected(true);
		} else {
			FuncionarioForm.rdbtnF.setSelected(true);
		}
		FuncionarioForm.tfDtNasc.setText(funcionario.getDataNasc().toLocaleString().replace("-", ""));/* ARRUMAR */
		FuncionarioForm.ftfCPF.setText(funcionario.getCpf());
		FuncionarioForm.tfSalario.setText(String.valueOf(funcionario.getSalario()));
		FuncionarioForm.ftfTelefone.setText(funcionario.getTelefone());
		FuncionarioForm.tfEmail.setText(funcionario.getEmail());
		FuncionarioForm.cbCargo.setSelectedItem(new CargoDAO().buscaPorId(funcionario.getIdCargo()));
		FuncionarioForm.lblFoto.setIcon(new ImageIcon(funcionario.getFoto()));
	}
	

	 	public static void buscaFuncionarios(int codigo, String nome, String cpf) {

		listaFuncionarios = new FuncionarioDAO().listarFuncionarios(codigo, nome, cpf);
		if (listaFuncionarios != null) {
			FuncionarioListaForm.tblFuncionarios.setModel(new FuncionarioTableModel(listaFuncionarios));
			
			// DEFINE A LARGURA DA COLUNA NA TABELA.
			FuncionarioListaForm.tblFuncionarios.getColumnModel().getColumn(0)
					.setMaxWidth(50);
			FuncionarioListaForm.tblFuncionarios.getColumnModel().getColumn(1)
					.setMaxWidth(200);
			FuncionarioListaForm.tblFuncionarios.getColumnModel().getColumn(2)
					.setMaxWidth(35);
			FuncionarioListaForm.tblFuncionarios.getColumnModel().getColumn(3)
					.setMaxWidth(70);
			FuncionarioListaForm.tblFuncionarios.getColumnModel().getColumn(4)
					.setMaxWidth(90);
			FuncionarioListaForm.tblFuncionarios.getColumnModel().getColumn(5)
					.setMaxWidth(55);
			FuncionarioListaForm.tblFuncionarios.getColumnModel().getColumn(6)
					.setMaxWidth(100);
			FuncionarioListaForm.tblFuncionarios.getColumnModel().getColumn(7)
					.setMaxWidth(80);
			FuncionarioListaForm.tblFuncionarios.getColumnModel().getColumn(8)
					.setMaxWidth(40);
			FuncionarioListaForm.tblFuncionarios.getColumnModel().getColumn(9)
					.setMaxWidth(40);
			
			//FAZ COM QUE A COLUNA NÃO SEJA EXIBIDA, PORÉM O SEU CONTEÚDO AINDA PODE SER TRABALHADO.
			FuncionarioListaForm.tblFuncionarios.getColumnModel().getColumn(10).setMinWidth(0);
			FuncionarioListaForm.tblFuncionarios.getColumnModel().getColumn(10).setMaxWidth(0);
		}
		
	}
	 	public static void buscarFuncionario(int codigo, String cpf) {
	 		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			Funcionario funcionario = funcionarioDAO.buscarFuncionario(codigo, cpf);
			
			UsuarioForm.tfCodigo.setText(String.valueOf(funcionario.getIdFunc()));
			UsuarioForm.ftfCPF.setText(funcionario.getCpf());
			UsuarioForm.tfNome.setText(funcionario.getNome());
			
			String codFunc = "";
			switch (String.valueOf(funcionario.getIdFunc()).length()) {
			case 1: codFunc = "00000" + funcionario.getIdFunc();break;
			case 2: codFunc = "0000" + funcionario.getIdFunc();break;
			case 3: codFunc = "000" + funcionario.getIdFunc();break;
			case 4: codFunc = "00" + funcionario.getIdFunc();break;
			case 5: codFunc = "0" + funcionario.getIdFunc();break;
			}
			String primeiroNome = funcionario.getNome().substring(0, funcionario.getNome().indexOf(" ")).toLowerCase();
			
			UsuarioForm.tfUsuario.setText(primeiroNome+"_"+codFunc);
			UsuarioForm.pfSenha.setText("123456");
			
	 	}
	 
	
	public static void limparCampos(){
		FuncionarioForm.tfCodigo.setText("");
		FuncionarioForm.tfNome.setText("");
		FuncionarioForm.buttonGroup.clearSelection();
		FuncionarioForm.tfDtNasc.setText("");
		FuncionarioForm.ftfCPF.setText("");
		FuncionarioForm.tfSalario.setText("");
		FuncionarioForm.ftfTelefone.setText("");
		FuncionarioForm.tfEmail.setText("");
		FuncionarioForm.cbCargo.setSelectedIndex(0);
		FuncionarioForm.lblFoto.setIcon(null);
		FuncionarioForm.cbCargo.setEditable(false);
		FuncionarioForm.cbCargo.setEnabled(true);
		FuncionarioForm.tfCodigo.requestFocus();
	}

}
