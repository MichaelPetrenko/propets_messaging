package telran.messaging.security;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.messaging.dao.MessagingRepository;
import telran.messaging.domain.entities.MessagingEntity;
import telran.messaging.service.TokenValidationRequestor;

@Service
public class DeletingFilter implements Filter {
	
	@Autowired
	MessagingRepository repo;
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		String path = request.getServletPath();

		if (
			(path.matches("/message/en/v1/[^/]+") && request.getMethod().equalsIgnoreCase("DELETE"))
		 ) {
				TokenValidationRequestor tvr = new TokenValidationRequestor();

				String xToken = request.getHeader("X-Token");
				if(xToken==null || xToken=="") {
					response.sendError(401);
					return;
				}
				
				String postId = request.getServletPath().split("/")[4];
				MessagingEntity entity = repo.findById(postId).orElse(null);
				if(entity==null) {
					response.sendError(401);
					return;
				}
				String loginEntity = entity.getUserLogin();
				
				
				try {
					String[] cred = tvr.decompileToken(xToken);
					System.out.println("============"+cred[0]);
					System.out.println("============"+loginEntity);
					if(!cred[0].equalsIgnoreCase(loginEntity)) {
						response.sendError(400);
						return;
					}
				} catch(Exception e) {
					response.sendError(400);
					return;
				}
							
				try {
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
