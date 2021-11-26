package controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import model.Cliente;
import model.dao.ClienteDAO;

public class ClienteProcess {
	public static ClienteDAO cd;
	public static ArrayList<Cliente> clientes;
	public static Cliente cliente;
	private static JSONObject jo;

	public static void carregarTodos() throws SQLException {
		cd = new ClienteDAO();
		clientes = cd.readAll();
	}

	public static void carregaCliente(String id) throws SQLException {
		cd = new ClienteDAO();
		cliente = cd.readClient(id);
	}
	
	public static int create(String body) throws SQLException {
		cd = new ClienteDAO();
		try {
			jo = new JSONObject(body);
			cliente = new Cliente();
			if (jo.has("cpf") && !jo.isNull("cpf"))
				cliente.setCpf(jo.getString("cpf"));
			else
				cliente.setCpf("");
			cliente.setNome(jo.getString("nome"));
			cliente.setTelefone(jo.getString("telefone"));
			cliente.setEndereco(jo.getString("endereco"));
		} catch (JSONException e) {
			System.out.println("Erro ao receber JSON:" + e);
		}
		return cd.create(cliente);
	}

	public static boolean update(String body) throws SQLException {
		cd = new ClienteDAO();
		try {
			jo = new JSONObject(body);
			cliente = new Cliente();
			cliente.setIdCliente(jo.getInt("id_cliente"));
			if (jo.has("cpf") && !jo.isNull("cpf"))
				cliente.setCpf(jo.getString("cpf"));
			else
				cliente.setCpf("");
			cliente.setNome(jo.getString("nome"));
			cliente.setTelefone(jo.getString("telefone"));
			cliente.setEndereco(jo.getString("endereco"));
		} catch (JSONException e) {
			System.out.println("Erro ao receber JSON:" + e);
		}
		return cd.update(cliente) > 0;
	}

	public static boolean delete(String id) throws SQLException {
		cd = new ClienteDAO();
		return cd.delete(id);
	}
}
