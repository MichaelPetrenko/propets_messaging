package telran.messaging.security;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import telran.messaging.service.TokenValidationRequestor;

@Service
public class BasicFilter implements Filter {
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		String path = request.getServletPath();

		if (
			(path.matches("/en/v1/login/[^/]+"))
		  ||(path.matches("/en/v1/[^/]+") && request.getMethod().equalsIgnoreCase("PUT"))
		  ||(path.matches("/en/v1/[^/]+") && request.getMethod().equalsIgnoreCase("GET"))
		  ||(path.matches("/en/v1/view/"))
		  
		 ) {

				String xToken = request.getHeader("X-Token");
				if(xToken==null || xToken=="") {
					response.sendError(401);
					return;
				}
							
				try {
					TokenValidationRequestor tvr = new TokenValidationRequestor();
					String newToken = tvr.validateToken(xToken);
					response.setHeader("X-Token", newToken);
				} catch (Exception e) {
					response.sendError(403);
					return;
				}
		}
		chain.doFilter(request, response);
	}
}
