package model;

import java.util.Objects;

import org.json.JSONException;
import org.json.JSONObject;

public class Funcionario {
	private int idFuncionario;
	private String nomeFuncionario;
	private String telefone;
	private String endereco;
	private String cpf;
	private String senha;
	private boolean online;

	public Funcionario() {

	}
	
	
	
	public Funcionario(boolean online) {
		super();
		this.online = online;
	}



	public Funcionario(String cpf, String senha) {
		super();
		this.cpf = cpf;
		this.senha = senha;
	}

	public Funcionario(String idFuncionario) {
		this.idFuncionario = Integer.valueOf(idFuncionario);
	}
	
	public Funcionario(String idFuncionario, String nomeFuncionario, String telefone, String endereco, String cpf, String senha) {
		this.idFuncionario = Integer.valueOf(idFuncionario);
		this.nomeFuncionario = nomeFuncionario;
		this.telefone = telefone;
		this.endereco = endereco;
		this.cpf = cpf;
		this.senha = senha;
	}

	public Funcionario(int idFuncionario, String nomeFuncionario, String telefone, String endereco, String cpf, String senha) {
		this.idFuncionario = idFuncionario;
		this.nomeFuncionario = nomeFuncionario;
		this.telefone = telefone;
		this.endereco = endereco;
		this.cpf = cpf;
		this.senha = senha;
	}
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public int getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(int idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public String getNomeFuncionario() {
		return nomeFuncionario;
	}

	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
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
	
	public boolean getOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idFuncionario);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funcionario other = (Funcionario) obj;
		return idFuncionario == other.idFuncionario;
	}

	@Override
	public String toString() {
		return idFuncionario + "\t" + nomeFuncionario + "\t"
				+ telefone + "\t" + endereco + "\t" + cpf + "\n";
	}
	
	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		try {
			json.put("id_funcionario", idFuncionario);
			json.put("nome_funcionario", nomeFuncionario);
			json.put("telefone", telefone);
			json.put("endereco", endereco);
			json.put("cpf", cpf);
			json.put("senha", senha);
		}catch (JSONException e) {
			System.out.println("Erro ao Converter JSON:" + e);
		}
		return json;
	}
	
}
