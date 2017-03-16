package br.com.farmacia.test;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;

import br.com.farmacia.DAO.ProdutosDAO;
import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.domain.Produtos;

public class ProdutosDAOTest {

	@Ignore
	public void salvar() throws SQLException {

		Fornecedores f = new Fornecedores();
		f.setCodigo(7);

		Produtos p1 = new Produtos(); 
		p1.setDescricao("dipirona");
		p1.setQuantidade(10); 
		p1.setPreco(3.2);
		p1.setFornecedores(f);



		Produtos p2 = new Produtos(); 
		p2.setDescricao("aspirina");
		p2.setQuantidade(6); 
		p2.setPreco(1.82);
		p2.setFornecedores(f);


		ProdutosDAO dao = new ProdutosDAO();
		dao.salvar(p1);
		dao.salvar(p2);
	}

	public void listar() throws SQLException{
		ProdutosDAO dao = new ProdutosDAO();
		ArrayList<Produtos> lista = dao.listar();

		for(Produtos p:lista){
			System.out.println(p.toString());
		}
	}

	public void excluir() throws SQLException{
		ProdutosDAO dao = new ProdutosDAO();
		Produtos p = new Produtos();
		p.setCodigo(4);
		dao.excluir(p);
	}
	@Test
	public void editar ()throws SQLException{
		Produtos p = new Produtos();
		ProdutosDAO dao = new ProdutosDAO();
		p.setCodigo(1);
		p.setDescricao("Dipirona Gotas");
		p.setQuantidade(8);
		p.setPreco(8.23);
		Fornecedores f = new Fornecedores();
		f.setCodigo(1);
		p.setFornecedores(f);
		dao.editar(p);
	}
}
