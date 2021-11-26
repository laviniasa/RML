package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Funcionario;

public class FuncionarioDAO {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private ArrayList<Funcionario> funcionarios;
	private Funcionario funcionario;

	public ArrayList<Funcionario> readAll() throws SQLException {
		funcionarios = new ArrayList<>();
		String query = "select * from funcionarios;";
		con = ConnectionDB.getConnection();
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		while (rs.next()) {
			funcionario = new Funcionario();
			funcionario.setIdFuncionario(rs.getInt("id_funcionario"));
			funcionario.setNomeFuncionario(rs.getString("nome_funcionario"));
			funcionario.setTelefone(rs.getString("telefone"));
			funcionario.setEndereco(rs.getString("endereco"));
			funcionario.setCpf(rs.getString("cpf"));
			funcionario.setSenha(rs.getString("senha"));
			funcionarios.add(funcionario);
		}
		con.close();
		return funcionarios;
	}

	public Funcionario readFunc(String id) throws SQLException {
		funcionarios = new ArrayList<>();
		String query = "select * from funcionarios WHERE id_funcionario = " + id + ";";
		con = ConnectionDB.getConnection();
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		funcionario = new Funcionario();
		while (rs.next()) {
			// funcionario.setIdFuncionario(rs.getInt("id_funcionario"));
			funcionario.setNomeFuncionario(rs.getString("nome_funcionario"));
			funcionario.setTelefone(rs.getString("telefone"));
			funcionario.setEndereco(rs.getString("endereco"));
			funcionario.setCpf(rs.getString("cpf"));
			funcionario.setSenha(rs.getString("senha"));
			
		}
		con.close();
		return funcionario;
	}

	public int create(Funcionario funcionario) throws SQLException {
		String sql = "insert into funcionarios(nome_funcionario, telefone, endereco, cpf, senha) value (?, ?, ?, ?, ?);";
		con = ConnectionDB.getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, funcionario.getNomeFuncionario());
		ps.setString(2, funcionario.getTelefone());
		ps.setString(3, funcionario.getEndereco());
		ps.setString(4, funcionario.getCpf());
		ps.setString(5, funcionario.getSenha());
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

	public int update(Funcionario funcionario) throws SQLException {
		String sql = "update funcionarios set nome_funcionario = ?, telefone = ?, endereco = ?, cpf = ?, senha = ?; where id_funcionarios = ?;";
		con = ConnectionDB.getConnection();
		ps = con.prepareStatement(sql);
		ps.setString(1, funcionario.getNomeFuncionario());
		ps.setString(2, funcionario.getTelefone());
		ps.setString(3, funcionario.getEndereco());
		ps.setString(4, funcionario.getCpf());
		ps.setString(5, funcionario.getSenha());
		ps.setInt(6, funcionario.getIdFuncionario());
		return ps.executeUpdate();
	}

	public boolean delete(String id) throws SQLException {
		String sql = "delete from funcionarios where id_funcionario = ?;";
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
