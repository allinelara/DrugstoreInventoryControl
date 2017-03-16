package br.com.farmacia.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.farmacia.DAO.FornecedoresDAO;
import br.com.farmacia.DAO.ProdutosDAO;
import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.domain.Produtos;
import br.com.farmacia.util.JSFUtil;

@ManagedBean(name = "MBProdutos")
@ViewScoped
public class ProdutosBean {

	private Produtos produtos;
	private ArrayList<Produtos> itens;
	private ArrayList<Produtos> itensFiltrados;
	private ArrayList<Fornecedores> comboFornecedores;



	public ArrayList<Fornecedores> getComboFornecedores() {
		return comboFornecedores;
	}
	public void setComboFornecedores(ArrayList<Fornecedores> comboFornecedores) {
		this.comboFornecedores = comboFornecedores;
	}
	public Produtos getProdutos() {
		return produtos;
	}
	public void setProdutos(Produtos produtos) {
		this.produtos = produtos;
	}
	public ArrayList<Produtos> getItens() {
		return itens;
	}
	public void setItens(ArrayList<Produtos> itens) {
		this.itens = itens;
	}
	public ArrayList<Produtos> getItensFiltrados() {
		return itensFiltrados;
	}
	public void setItensFiltrados(ArrayList<Produtos> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	@PostConstruct
	public void prepararPesquisa(){
		ProdutosDAO dao = new ProdutosDAO();

		try {

			itens = dao.listar();

		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("e.getMessage()");
			e.printStackTrace();
		}
	}
	public void prepararNovo() throws SQLException{
		produtos = new Produtos();

		FornecedoresDAO dao = new FornecedoresDAO();
		comboFornecedores = dao.listar();
	}
	public void novo() throws SQLException{
		ProdutosDAO dao = new ProdutosDAO();
		dao.salvar(produtos);
		JSFUtil.adicionarMensagemSucesso("Salvo com sucesso");

		itens = dao.listar();
	}

	public void excluir(){
		try {
			ProdutosDAO fdao = new ProdutosDAO();
			fdao.excluir(produtos);


			itens = fdao.listar();


			JSFUtil.adicionarMensagemSucesso("Produto excluido com sucesso!");

		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("e.getMessage()");
			e.printStackTrace();
		}
	}

	public void editar(){
		ProdutosDAO dao = new ProdutosDAO();
		try {
			dao.editar(produtos);
			JSFUtil.adicionarMensagemSucesso("Editado com sucesso");

			itens = dao.listar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}	

	public void prepararEditar() throws SQLException{
		produtos = new Produtos();

		FornecedoresDAO dao = new FornecedoresDAO();
		comboFornecedores = dao.listar();
	}
}


