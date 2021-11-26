package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.stream.Collectors;

import org.json.JSONArray;

import controllers.ClienteProcess;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Cliente;

@WebServlet("/clientes")
public class ClienteSERV extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private PrintWriter out;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		out = resp.getWriter();
		String idCliente = req.getParameter("id_cliente");
		try {
			
			if(idCliente == null) {
				ClienteProcess.carregarTodos();
				
				JSONArray ja = new JSONArray();
				for (Cliente c : ClienteProcess.clientes) {
					ja.put(c.toJSON());
				}
				out.print(ja);
			}else {
				ClienteProcess.carregaCliente(idCliente);
				
				out.print(ClienteProcess.cliente.toJSON());
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
				int idCliente = ClienteProcess.create(body);
				if (idCliente > 0) {
					resp.setStatus(HttpServletResponse.SC_CREATED);
					out.print("{\"id_cliente\":" + idCliente + "}");
				} else {
					resp.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
				}
			} catch (SQLException e) {
				out.print("{ \"erro\":\"Erro ao conectar ao SGBD: " + e + "\"}");
				resp.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
			}
		} else {
			resp.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
			out.print(
					"{\"cpf\":null,\"nome\":\"Algum nome\",\"endereco\":\"cep,numero,complemento\",\"telefone\":\"numero\"}");
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
				if (ClienteProcess.update(body)) {
					resp.setStatus(HttpServletResponse.SC_GONE);
				} else {
					resp.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
				}
			} catch (SQLException e) {
				out.print("{\"erro\":\"Erro ao conectar ao SGBD:" + e + "\"}");
				resp.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
			}
		} else {
			resp.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
			out.print(
					"{\"id_cliente\":\"1\",\"cpf\":null,\"nome\":\"Algum nome\",\"endereco\":\"cep,numero,complemento\",\"telefone\":\"numero\"}");
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		out = resp.getWriter();
		String idCliente = req.getParameter("id_cliente");
		if (idCliente != null) {
			try {
				if (ClienteProcess.delete(idCliente)) {
					resp.setStatus(HttpServletResponse.SC_OK);
				} else {
					resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
				}
			} catch (SQLException e) {
				out.print("{\"erro\":\"Erro ao conectar ao SGBD: " + e + "\"}");
				resp.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
			}
		} else {
			out.print("{ \"erro\":\"Necessário o parâmetro 'id_cliente' para exclusão\"}");
			resp.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
		}
	}
}
