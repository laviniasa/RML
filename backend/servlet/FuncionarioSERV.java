package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.stream.Collectors;

import org.json.JSONArray;

import controllers.FuncionarioProcess;
import controllers.ProdutoProcess;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Funcionario;

@WebServlet("/funcionarios")
public class FuncionarioSERV extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PrintWriter out;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		out = resp.getWriter();
		String idFuncionario = req.getParameter("id_funcionario");
		try {
			if (idFuncionario == null) {
				FuncionarioProcess.carregarAll();
				JSONArray ja = new JSONArray();
				for (Funcionario f : FuncionarioProcess.funcionarios) {
					ja.put(f.toJSON());
				}
				out.print(ja);
			} else {
				FuncionarioProcess.carregarFunc(idFuncionario);
				out.print(FuncionarioProcess.funcionario.toJSON());
			}
		} catch (SQLException e) {
			resp.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
			out.print("{ \"erro\":\"Erro ao carregar dados do BD: " + e + "\"}");
		}
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String body = req.getReader().readLine();
		out = resp.getWriter();
		if (body != null) {
			req.getReader().reset();
			body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
			try {
				int idFuncionario = FuncionarioProcess.create(body);
				if (idFuncionario > 0) {
					resp.setStatus(HttpServletResponse.SC_CREATED);
					out.print("{\"id_funcionario\":" + idFuncionario + "}");
				}
			} catch (SQLException e) {
				out.print("{\"erro\":\"Erro ao conectar ao SGBD: " + e + "\"}");
				resp.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
			}
		} else {
			resp.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
			out.print(
					"{\"nome_funcionario\":Algum nome\",\"telefone\":\"numero\",\"endereco\":\"cep,numero,complemento\",\"cpf\":\"Algum numero\",\"senha\":\"Alguma senha\"}");
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String body = req.getReader().readLine();
		out = resp.getWriter();
		if (body != null) {
			req.getReader().reset();
			body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
			try {
				if (ProdutoProcess.update(body)) {
					resp.setStatus(HttpServletResponse.SC_GONE);
				} else {
					resp.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
				}
			} catch (SQLException e) {
				out.print("{\"erro\":\"Erro ao conectar ao SGBD:" + e + "\"}");
			}
		} else {
			resp.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
			out.print("{\"id_funcionario\":\"1\",\"nome_funcionario\":\"Algum nome\",\"telefone\":\"numero\",\"endereco\":\"cep,numero,complemento\"cpf\":\"Algum numero\"senha\":\"Alguma senha\"}");
		}
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		out = resp.getWriter();
		String idFuncionario = req.getParameter("id_funcionario");
		if(idFuncionario != null) {
			try {
				if (FuncionarioProcess.delete(idFuncionario)) {
					resp.setStatus(HttpServletResponse.SC_OK);
				}else {
					resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
				}
			} catch(SQLException e) {
				out.print("{\"erro\":\"Erro ao conectar ao SGBD: " + e + "\"}");
				resp.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
			}
		}
	}
}
