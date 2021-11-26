package controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import model.Produto;
import model.dao.ProdutoDAO;

public class ProdutoProcess {
	public static ProdutoDAO pd;
	public static ArrayList<Produto> produtos;
	public static Produto produto;
	private static JSONObject jo;
	
	public static void carregarDados() throws SQLException {
		pd = new ProdutoDAO();
		produtos = pd.readAll();
	}
	
	public static void carregarProdutos(String id) throws SQLException {
		pd = new ProdutoDAO();
		produto = pd.readProduto(id);
	}
	
	public static int create(String body) throws SQLException {
		pd = new ProdutoDAO();
		try {
			jo = new JSONObject(body);
			produto = new Produto();
			produto.setNome(jo.getString("nome_produto"));
			produto.setQuantidade(jo.getInt("quantidade_estoque"));
			produto.setUmValor(jo.getString("um"));
			produto.setPrecoBruto(jo.getDouble("preco_bruto"));
			produto.setPrecoVenda(jo.getDouble("preco_venda"));
			produto.setUrl(jo.getString("img"));
		} catch (JSONException e) {
			System.out.println("Erro ao receber JSON:" + e);
		}
		return pd.create(produto);
	}
	
	public static boolean update(String body) throws SQLException {
		pd = new ProdutoDAO();
		try {
			jo = new JSONObject(body);
			produto.setIdProduto(jo.getInt("id_produto"));
			produto.setNome(jo.getString("nome_produto"));
			produto.setQuantidade(jo.getInt("quantidade_estoque"));
			produto.setUmValor(jo.getString("um"));
			produto.setPrecoBruto(jo.getDouble("preco_bruto"));
			produto.setPrecoVenda(jo.getDouble("preco_venda"));
			produto.setUrl(jo.getString("img"));
		} catch (JSONException e) {
			System.out.println("Erro ao receber JSON:" + e);
		}
		produto = new Produto();
		return pd.update(produto) > 0;
	}
	
	public static boolean delete(String id) throws SQLException {
		pd = new ProdutoDAO();
		return pd.delete(id);
	}
}
