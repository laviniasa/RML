package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Funcionario;

public class LoginDAO {
	private static Connection con;
	private static PreparedStatement ps;
	private static  ResultSet rs;
	private static Funcionario funcionario;
	
	// funcionando
	public static Funcionario loginFuncionario(Funcionario f) { 
		try {
			funcionario = new Funcionario();
			String query = "select * from funcionarios where cpf = '" + f.getCpf() + "' and senha = '" + f.getSenha() + "';";
			con = ConnectionDB.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			if(rs != null && rs.next()) {
				funcionario.setIdFuncionario(rs.getInt("id_funcionario"));
				funcionario.setNomeFuncionario(rs.getString("nome_funcionario"));
				funcionario.setTelefone(rs.getString("telefone"));
				funcionario.setEndereco(rs.getString("endereco"));
				funcionario.setCpf(rs.getString("cpf"));
			}
			
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return funcionario;
	}
}
