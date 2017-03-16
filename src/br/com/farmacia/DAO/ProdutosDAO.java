package br.com.farmacia.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.domain.Produtos;
import br.com.farmacia.factory.ConexaoFactory;

public class ProdutosDAO {

	public void salvar(Produtos p) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO prodtos  ");
		sql.append("(descricao, quantidade, preco, fornecedores_codigo) ");
		sql.append("VALUES (?, ?, ?, ?) ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());	
		comando.setString(1, p.getDescricao());
		comando.setInt(2, p.getQuantidade());
		comando.setDouble(3, p.getPreco());
		comando.setInt(4, p.getFornecedores().getCodigo());

		comando.executeUpdate();
	}

	public ArrayList<Produtos> listar() throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT p.codigo, p.descricao, p.quantidade, p.preco, f.codigo, f.descricao FROM prodtos p ");
		sql.append("INNER JOIN fornecedores f ON f.codigo = p.fornecedores_codigo ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());	

		ResultSet resultado = comando.executeQuery();

		ArrayList<Produtos> lista = new ArrayList<Produtos>();

		while (resultado.next()){
			Produtos p = new Produtos();
			p.setCodigo(resultado.getInt("p.codigo"));
			p.setDescricao(resultado.getString("p.descricao"));
			p.setQuantidade(resultado.getInt("p.quantidade"));
			p.setPreco(resultado.getDouble("p.preco"));

			Fornecedores f = new Fornecedores();
			f.setCodigo(resultado.getInt("f.codigo"));
			f.setDescricao(resultado.getString("f.descricao"));

			p.setFornecedores(f);
			lista.add(p);
		}

		return lista;
	}

	public void excluir (Produtos p) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM prodtos ");
		sql.append("WHERE codigo = ? ");

		Connection conexao;
		conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());	
		comando.setInt(1, p.getCodigo());
		comando.executeUpdate();	

	}

	public void editar (Produtos p) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE prodtos ");
		sql.append("SET descricao = ?, quantidade = ?, preco = ?, fornecedores_codigo = ? ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());	
		comando.setString(1, p.getDescricao());
		comando.setInt(2, p.getQuantidade());
		comando.setDouble(3, p.getPreco());
		comando.setInt(4, p.getFornecedores().getCodigo());
		comando.setInt(5, p.getCodigo());
		comando.executeUpdate();

	}

	public Produtos buscaPorCodigo(Produtos p) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT p.codigo, p.descricao, p.quantidade, p.preco, f.codigo, f.descricao FROM prodtos p ");
		sql.append("INNER JOIN fornecedores f ON p.codigo = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());	
		comando.setInt(1, p.getCodigo());

		ResultSet resultado = comando.executeQuery();
		Produtos retorno = null;

		if(resultado.next()){
			retorno = new Produtos();
			retorno.setCodigo(resultado.getInt("p.codigo"));
			retorno.setDescricao(resultado.getString("p.descricao"));
			retorno.setQuantidade(resultado.getInt("p.quantidade"));
			retorno.setPreco(resultado.getDouble("p.preco"));

			Fornecedores f = new Fornecedores();
			f.setCodigo(resultado.getInt("f.codigo"));
			f.setDescricao(resultado.getString("f.descricao"));

			retorno.setFornecedores(f);
		}
		return retorno;

	}


}
