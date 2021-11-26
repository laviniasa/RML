package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Produto;

public class ProdutoDAO {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private ArrayList<Produto> produtos;
	private Produto produto;

	public ArrayList<Produto> readAll() throws SQLException {
		produtos = new ArrayList<>();
		String query = "select * from produtos;";
		con = ConnectionDB.getConnection();
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		while (rs.next()) {
			produto = new Produto();
			produto.setIdProduto(rs.getInt("id_produto"));
			produto.setNome(rs.getString("nome_produto"));
			produto.setUmValor(rs.getString("um"));
			produto.setQuantidade(rs.getInt("quantidade_estoque"));
			produto.setPrecoBruto(rs.getDouble("preco_bruto"));
			produto.setPrecoVenda(rs.getDouble("preco_venda"));
			produto.setUrl(rs.getString("img"));
			produtos.add(produto);
		}
		con.close();
		return produtos;
	}

	public Produto readProduto(String id) throws SQLException {
		produtos = new ArrayList<>();
		String query = "select * from produtos WHERE id_produto = " + id + ";";
		con = ConnectionDB.getConnection();
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		produto = new Produto();
		while (rs.next()) {
			produto.setIdProduto(rs.getInt("id_produto"));
			produto.setNome(rs.getString("nome_produto"));
			produto.setUmValor(rs.getString("um"));
			produto.setQuantidade(rs.getInt("quantidade_estoque"));
			produto.setPrecoBruto(rs.getDouble("preco_bruto"));
			produto.setPrecoVenda(rs.getDouble("preco_venda"));
			produto.setUrl(rs.getString("img"));
		}
		con.close();
		return produto;
	}

	public int create(Produto produto) throws SQLException {
		String sql = "insert into produtos(nome_produto, quantidade_estoque, um, preco_bruto, preco_venda, img) value (?, ?, ?, ?, ?, ?);";
		con = ConnectionDB.getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, produto.getNome());
		ps.setInt(2, produto.getQuantidade());
		ps.setString(3, produto.getUmValor());
		ps.setDouble(4, produto.getPrecoBruto());
		ps.setDouble(5, produto.getPrecoVenda());
		ps.setString(6, produto.getUrl());
		if (ps.executeUpdate() > 0) {
			rs = ps.getGeneratedKeys();
			rs.next();
			con.close();
			return rs.getInt(1);
		} else {
			con.close();
			return 0;
		}
	}

	public int update(Produto produto) throws SQLException {
		String sql = "update produtos set nome_produto = ?, quantidade_estoque = ?, um = ?, preco_bruto = ?, preco_venda = ?, img = ?;";
		con = ConnectionDB.getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, produto.getNome());
		ps.setInt(2, produto.getQuantidade());
		ps.setString(3, produto.getUmValor());
		ps.setDouble(4, produto.getPrecoBruto());
		ps.setDouble(5, produto.getPrecoVenda());
		ps.setString(6, produto.getUrl());
		return ps.executeUpdate();
	}

	public boolean delete(String id) throws SQLException {
		String sql = "delete from produtos where id_produto = ?;";
		con = ConnectionDB.getConnection();
		ps = con.prepareStatement(sql);
		ps.setInt(1, Integer.valueOf(id));
		if (ps.executeUpdate() == 0) {
			con.close();
			return true;
		} else {
			con.close();
			return false;
		}
	}
}
