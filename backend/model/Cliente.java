package model;

import java.util.Objects;

import org.json.JSONException;
import org.json.JSONObject;

public class Cliente {
	private int idCliente;
	private String cpf;
	private String nome;
	private String telefone;
	private String endereco;

	public Cliente() {

	}

	public Cliente(String idCliente) {
		this.idCliente = Integer.valueOf(idCliente);
	}

	public Cliente(int idCliente, String nome, String cpf, String telefone, String endereco) {
		this.idCliente = idCliente;
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.endereco = endereco;
	}

	public Cliente(String idCliente, String nome, String cpf, String telefone, String endereco) {
		this.idCliente = Integer.valueOf(idCliente);
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.endereco = endereco;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idCliente);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return idCliente == other.idCliente;
	}

	@Override
	public String toString() {
		return idCliente + "\t" + cpf + "\t" + nome + "\t" + telefone + "\t" + endereco + "\n";
	}

	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		try {
			json.put("id_cliente", idCliente);
			json.put("cpf", cpf);
			json.put("nome", nome);
			json.put("telefone", telefone);
			json.put("endereco", endereco);

		} catch (JSONException e) {
			System.out.println("Erro ao Converter JSON:" + e);
		}
		return json;
	}

	public void add(Cliente cliente) {
	}


}
