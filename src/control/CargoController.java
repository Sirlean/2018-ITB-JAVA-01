package control;

import java.awt.Color;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import model.Cargo;
import model.CargoDAO;
import tabelaDados.CargoTableModel;
import view.CargoForm;
import view.FuncionarioForm;

public class CargoController {

	static List<Cargo> listaCargos;

	public CargoController() {

	}

	public static void buscaCargoId(int idCargo) {
		CargoDAO cargoDAO = new CargoDAO();
		Cargo cargo = cargoDAO.buscaPorId(idCargo);
		CargoForm.tfIdCargo.setText(String.valueOf(cargo.getIdCargo()));
		CargoForm.tfNomeCargo.setText(cargo.getNomeCargo());
		CargoForm.tfStatusCargo.setText(String.valueOf(cargo.getStatusCargo()));
	}
	public static int buscaCargoNome(String nomeCargo) {
		CargoDAO cargoDAO = new CargoDAO();
		Cargo cargo = cargoDAO.buscaPorNome(nomeCargo);
		
		return cargo.getIdCargo();
	}
	public static void insereCargo(String nomeCargo) {
		Cargo cargo = new Cargo();
		cargo.setNomeCargo(nomeCargo);

		CargoDAO cargoDAO = new CargoDAO();
		cargoDAO.insereCargo(cargo);
	}
	
	public static void alteraCargo(int codigoCargo, String nomeCargo) {
		Cargo cargo = new Cargo();
		cargo.setIdCargo(codigoCargo);
		cargo.setNomeCargo(nomeCargo);

		CargoDAO cargoDAO = new CargoDAO();
		cargoDAO.alteraCargo(cargo);
	}
	
	public static void desativaCargo(int codigoCargo, String nomeCargo) {
		Cargo cargo = new Cargo();
		cargo.setIdCargo(codigoCargo);
		cargo.setNomeCargo(nomeCargo);

		CargoDAO cargoDAO = new CargoDAO();
		cargoDAO.desativaCargo(cargo);
	}

	public static void carregaCargos() {

		listaCargos = new CargoDAO().listarTodos();
		if (listaCargos != null) {
			CargoForm.tblListaCargos.setModel(new CargoTableModel(listaCargos));

			// DEFINE A LARGURA DA COLUNA NA TABELA.
			CargoForm.tblListaCargos.getColumnModel().getColumn(0)
					.setMaxWidth(55);
			CargoForm.tblListaCargos.getColumnModel().getColumn(1)
					.setMaxWidth(290);
			CargoForm.tblListaCargos.getColumnModel().getColumn(2)
					.setMaxWidth(50);
		}
	}
	public static void carregaTodosCargos() {

		listaCargos = new CargoDAO().listarTodosCargos();
		if (listaCargos != null) {
			CargoForm.tblListaCargos.setModel(new CargoTableModel(listaCargos));
		
			// DEFINE A LARGURA DA COLUNA NA TABELA.
			CargoForm.tblListaCargos.getColumnModel().getColumn(0)
					.setMaxWidth(55);
			CargoForm.tblListaCargos.getColumnModel().getColumn(1)
					.setMaxWidth(290);
			CargoForm.tblListaCargos.getColumnModel().getColumn(2)
					.setMaxWidth(50);
		}
	}
	public static void carregaTodosCargosPorNome(String nome_Cargo) {

		listaCargos = new CargoDAO().listarTodosPorNome(nome_Cargo);
		if (listaCargos != null) {
			CargoForm.tblListaCargos.setModel(new CargoTableModel(listaCargos));

			// DEFINE A LARGURA DA COLUNA NA TABELA.
			CargoForm.tblListaCargos.getColumnModel().getColumn(0)
					.setMaxWidth(55);
			CargoForm.tblListaCargos.getColumnModel().getColumn(1)
					.setMaxWidth(290);
			CargoForm.tblListaCargos.getColumnModel().getColumn(2)
					.setMaxWidth(50);
		}
	}
	
	 public static void carregaCombo(JComboBox<Cargo> combo) {
		CargoDAO cargoDAO = new CargoDAO();
		try {
			Vector<Cargo> cargos = cargoDAO.carregaComboCargo();
			combo.setModel(new DefaultComboBoxModel<Cargo>(cargos));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	public static void limparCampos(){
		CargoForm.tfIdCargo.setText("");
		CargoForm.tfNomeCargo.setText("");
		CargoForm.tfStatusCargo.setText("");
		CargoForm.tfIdCargo.requestFocus();
	}
}
