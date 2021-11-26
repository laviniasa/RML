package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Cliente;

public class ClienteDAO {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private ArrayList<Cliente> clientes;
	private Cliente cliente;

	public ArrayList<Cliente> readAll() throws SQLException {
		clientes = new ArrayList<>();
		String query = "select * from clientes;";
		con = ConnectionDB.getConnection();
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		while (rs.next()) {
			cliente = new Cliente();
			cliente.setIdCliente(rs.getInt("id_cliente"));
			cliente.setCpf(rs.getString("cpf"));
			cliente.setNome(rs.getString("nome"));
			cliente.setTelefone(rs.getString("telefone"));
			cliente.setEndereco(rs.getString("endereco"));
			clientes.add(cliente);
		}
		con.close();
		return clientes;
	}

	public Cliente readClient(String id) throws SQLException {
		clientes = new ArrayList<>();
		String query = "select * from clientes WHERE id_cliente = " + id + ";";
		con = ConnectionDB.getConnection();
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		cliente = new Cliente();	
		while (rs.next()) {
			cliente.setIdCliente(rs.getInt("id_cliente"));
			cliente.setCpf(rs.getString("cpf"));
			cliente.setNome(rs.getString("nome"));
			cliente.setTelefone(rs.getString("telefone"));
			cliente.setEndereco(rs.getString("endereco"));
		}
		con.close();
		return cliente;
	}

	public int create(Cliente cliente) throws SQLException {
		String sql = "insert into clientes(cpf, nome, telefone, endereco) value (?, ?, ?, ?);";
		con = ConnectionDB.getConnection();
		ps = con.prepareStatement(sql);
		if (cliente.getCpf().length() == 0)
			ps.setString(1, null);
		else
			ps.setString(1, cliente.getCpf());
		ps.setString(2, cliente.getNome());
		ps.setString(3, cliente.getTelefone());
		ps.setString(4, cliente.getEndereco());
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
	
	public int update(Cliente cliente) throws SQLException {
		String sql = "update clientes set cpf = ?, nome = ?, telefone = ?, endereco = ?, where id_cliente = ?;";
		con = ConnectionDB.getConnection();
		ps = con.prepareStatement(sql);
		if (cliente.getCpf().length() == 0)
			ps.setString(1, null);
		else
			ps.setString(1, cliente.getCpf());
		ps.setString(2, cliente.getNome());
		ps.setString(3, cliente.getTelefone());
		ps.setString(4, cliente.getEndereco());
		ps.setInt(5, cliente.getIdCliente());
		return ps.executeUpdate();
	}
	public boolean delete(String id) throws SQLException {
		String sql = "delete from clientes where id_cliente = ?;";
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
