package controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import model.Funcionario;
import model.dao.FuncionarioDAO;
import model.dao.LoginDAO;

public class FuncionarioProcess {
	public static FuncionarioDAO fd;
	public static ArrayList<Funcionario> funcionarios;
	public static Funcionario funcionario;
	private static JSONObject jo;
	public static LoginDAO ld;
	
	public static void carregarAll() throws SQLException {
		fd = new FuncionarioDAO();
		funcionarios = fd.readAll();
	}
	
	public static void carregarFunc(String id) throws SQLException {
		fd = new FuncionarioDAO();
		funcionario = fd.readFunc(id);
	}
	
	public static Funcionario login(String body) {
		fd = new FuncionarioDAO();
		ld = new LoginDAO();
		funcionario = new Funcionario();
		Funcionario teste = new Funcionario();
		try {
			jo = new JSONObject(body);
			funcionario.setCpf(jo.getString("cpf"));
			funcionario.setSenha(jo.getString("senha"));
			teste = LoginDAO.loginFuncionario(funcionario);
		} catch (JSONException e) {
			System.out.println("Erro ao receber JSON:" + e);
		}
		return teste;
	}
	
	public static int create(String body) throws SQLException {
		fd = new FuncionarioDAO();
		try {
			jo = new JSONObject(body);
			funcionario = new Funcionario();
			funcionario.setNomeFuncionario(jo.getString("nome_funcionario"));
			funcionario.setTelefone(jo.getString("telefone"));
			funcionario.setEndereco(jo.getString("endereco"));
			funcionario.setCpf(jo.getString("cpf"));
			funcionario.setSenha(jo.getString("senha"));
		} catch (JSONException e) {
			System.out.println("Erro ao receber JSON:" + e);
		}
		return fd.create(funcionario);
	}
	
	public static boolean update(String body) throws SQLException {
		fd = new FuncionarioDAO();
		try {
			jo = new JSONObject(body);
			funcionario = new Funcionario();
			funcionario.setIdFuncionario(jo.getInt("id_funcionario"));
			funcionario.setNomeFuncionario(jo.getString("nome_funcionario"));
			funcionario.setTelefone(jo.getString("telefone"));
			funcionario.setEndereco(jo.getString("endereco"));
			funcionario.setCpf(jo.getString("cpf"));
			funcionario.setSenha(jo.getString("senha"));
		} catch (JSONException e) {
			System.out.println("Erro ao receber JSON:" + e);
		}
		funcionario = new Funcionario();
		return fd.create(funcionario) > 0;
	}
	
	public static boolean delete(String id) throws SQLException {
		fd = new FuncionarioDAO();
		return fd.delete(id);
	}
}
