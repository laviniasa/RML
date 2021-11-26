package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import controllers.FuncionarioProcess;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Funcionario;
@WebServlet("/login")
public class LoginSERV extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private PrintWriter out;
	private static Funcionario funcionario;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String body = req.getReader().readLine();
		out = resp.getWriter();
		if (body != null) {
			req.getReader().reset();
			body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
			try {
				funcionario = FuncionarioProcess.login(body);
				out.print(funcionario.toJSON());
			}catch (Exception e) {
				System.out.println(e);
			}
		} else {
			resp.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
		}
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("olá");
	}
}
