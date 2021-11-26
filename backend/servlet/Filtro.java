package servlet;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class Filtro extends HttpServlet implements Filter {
	private static final long serialVersionUID = 1L;

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {		
		HttpServletRequest request = (HttpServletRequest) req;
		
        ((HttpServletResponse) resp).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) resp).addHeader("Access-Control-Allow-Headers", "*");
        ((HttpServletResponse) resp).addHeader("Access-Control-Allow-Methods","GET, OPTIONS, HEAD, PUT, POST, DELETE");
 
        HttpServletResponse response = (HttpServletResponse) resp;
 
        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
            return;
        }
 
        chain.doFilter(request, resp);
	}
}