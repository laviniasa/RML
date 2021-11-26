package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.stream.Collectors;

import org.json.JSONArray;

import controllers.ProdutoProcess;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Produto;

@WebServlet("/produtos")
public class ProdutoSERV extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PrintWriter out;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		out = resp.getWriter();
		String idProduto = req.getParameter("id_produto");
		try {
			if (idProduto == null) {
				ProdutoProcess.carregarDados();
				JSONArray ja = new JSONArray();
				for (Produto p : ProdutoProcess.produtos) {
					ja.put(p.toJSON());
				}
				out.print(ja);
			} else {
				ProdutoProcess.carregarProdutos(idProduto);
				out.print(ProdutoProcess.produto.toJSON());
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
				int idProduto = ProdutoProcess.create(body);
				if (idProduto > 0) {
					resp.setStatus(HttpServletResponse.SC_CREATED);
					out.print("{\"id_produto\":" + idProduto + "}");
				} else {
					resp.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
				}
			} catch (SQLException e) {
				out.print("{\"erro\":\"Erro ao conectar ao SGBD: " + e + "\"}");
				resp.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
			}
		} else {
			resp.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
			out.print(
					"{\"nome_produto\":\"Algum nome\",\"quantidade_estoque\":\"Algum numero\",\"um\":\"peso\",\"preco_bruto\":\"Valor B\",\"preco_venda\":\"Valor V\",\"img\":\"imagem\"}");
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
			} catch (SQLException e){
				out.print("{\"erro\":\"Erro ao conectar ao SGBD:" + e + "\"}");
			}
		} else {
			resp.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
			out .print("{\"id_produto\":\"1\",\"nome_produto\": null,\"um\":\"peso\",\"quantidade_estoque\":\"Algum numero\",\"preco_bruto\":\"Valor B\",\"preco_venda\":\"Valor V\",\"img\":\"imagem\"}");
		}
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		out = resp.getWriter();
		String idProduto = req.getParameter("id_produto");
		if (idProduto != null) {
			try {
				if(ProdutoProcess.delete(idProduto)) {
					resp.setStatus(HttpServletResponse.SC_OK);
				} else {
					resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
				}
			} catch (SQLException e) {
				out.print("{\"erro\":\"Erro ao conectar ao SGBD: " + e + "\"}");
				resp.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
			}
		}
	}
}
