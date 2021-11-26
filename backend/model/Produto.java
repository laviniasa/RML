package model;

import org.json.JSONException;
import org.json.JSONObject;

public class Produto {
	private int idProduto;
	private String nome;
	private int quantidade;
	private String umValor;
	private double precoBruto;
	private double precoVenda;
	private String url;

	public Produto() {
	}
	
	public Produto(String idProduto, String quantidade, String precoBruto, String precoVenda) {
		this.idProduto = Integer.valueOf(idProduto);
		this.quantidade = Integer.valueOf(quantidade);
		this.precoBruto = Double.valueOf(precoBruto);
		this.precoVenda = Double.valueOf(precoVenda);
	}

	public Produto(int idProduto, String nome, double precoBruto, double precoVenda, int quantidade, String umValor, String url) {
		this.idProduto = idProduto;
		this.nome = nome;
		this.umValor = umValor;
		this.precoBruto = precoBruto;
		this.precoVenda = precoVenda;
		this.quantidade = quantidade;
		this.url = url;
	}

	public Produto(String idProduto, String nome, String precoBruto, String precoVenda, String quantidade, String umValor, String url) {
		this.idProduto = Integer.valueOf(idProduto);
		this.nome = nome;
		this.umValor = umValor;
		this.precoBruto = Double.valueOf(precoBruto);
		this.precoVenda = Double.valueOf(precoVenda);
		this.quantidade = Integer.valueOf(quantidade);
		this.url = url;
		
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public double getPrecoBruto() {
		return precoBruto;
	}

	public void setPrecoBruto(double precoBruto) {
		this.precoBruto = precoBruto;
	}

	public String getUmValor() {
		return umValor;
	}

	public void setUmValor(String umValor) {
		this.umValor = umValor;
	}

	public double getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(double precoVenda) {
		this.precoVenda = precoVenda;
	}

	public int getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	

	@Override
	public String toString() {
		return idProduto + "\t" + nome + "\t" + quantidade + "\t"
				+ umValor + "\t" + precoBruto + "\t" + precoVenda + "\t" + url + "\n";
	}

	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		try {
			json.put("id_produto", idProduto);
			json.put("nome_produto", nome);
			json.put("quantidade_estoque", quantidade);
			json.put("um", umValor );
			json.put("preco_bruto", precoBruto);
			json.put("preco_venda", precoVenda);
			json.put("img", url);
		} catch (JSONException e) {
			System.out.println("Erro ao converter JSON: " + e);
		}
		return json;
	}
	
}
