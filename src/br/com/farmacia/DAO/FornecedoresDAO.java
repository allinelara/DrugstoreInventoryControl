package br.com.farmacia.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.factory.ConexaoFactory;

public class FornecedoresDAO {

	public void salvar(Fornecedores f) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO fornecedores  ");
		sql.append("(descricao) ");
		sql.append("VALUES (?) ");
		
		Connection conexao = ConexaoFactory.conectar();
	
		PreparedStatement comando = conexao.prepareStatement(sql.toString());	
		comando.setString(1, f.getDescricao());
		comando.executeUpdate();
	}
	
	public void excluir (Fornecedores f) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM fornecedores ");
		sql.append("WHERE codigo = ? ");
		
		Connection conexao;
		conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());	
		comando.setInt(1, f.getCodigo());
		comando.executeUpdate();	
		
	}
	
	public void editar (Fornecedores f) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE fornecedores ");
		sql.append("SET descricao = ? ");
		sql.append("WHERE codigo = ? ");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());	
		comando.setString(1, f.getDescricao());
		comando.setInt(2, f.getCodigo());
		comando.executeUpdate();
		
	}
	
	public Fornecedores buscaPorCodigo(Fornecedores f) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao FROM fornecedores ");
		sql.append("WHERE codigo = ? ");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());	
		comando.setInt(1, f.getCodigo());
		
		ResultSet resultado = comando.executeQuery();
		Fornecedores retorno = null;
		
		if(resultado.next()){
			retorno = new Fornecedores();
			retorno.setCodigo(resultado.getInt("codigo"));
			retorno.setDescricao(resultado.getString("descricao"));
		}
		return retorno;
		
	}
	
	public ArrayList<Fornecedores> listar() throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao FROM fornecedores ");
		sql.append("ORDER BY descricao ASC ");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());	
		
		ResultSet resultado = comando.executeQuery();
		
		ArrayList<Fornecedores> lista = new ArrayList<Fornecedores>();
		
		while (resultado.next()){
			Fornecedores f = new Fornecedores();
			f.setCodigo(resultado.getInt("codigo"));
			f.setDescricao(resultado.getString("descricao"));
			System.out.println(f.toString());
			
			lista.add(f);
		}
		
		return lista;
	}
	public ArrayList<Fornecedores> buscaPorDescricao(Fornecedores f) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao FROM fornecedores ");
		sql.append("WHERE descricao LIKE ? ");
		sql.append("ORDER BY descricao ASC ");

		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());	
		comando.setString(1, "%" + f.getDescricao() + "%");;
		
		ResultSet resultado = comando.executeQuery();
		ArrayList<Fornecedores> lista = new ArrayList<Fornecedores>();
		
		while (resultado.next()){
			Fornecedores f1 = new Fornecedores();
			f1.setCodigo(resultado.getInt("codigo"));
			f1.setDescricao(resultado.getString("descricao"));
			System.out.println(f1.toString());
			
			lista.add(f1);
		}
		
		return lista;
	}
	
}
