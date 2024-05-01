package ai.acintyo.ezykle.config;

import java.io.IOException;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import ai.acintyo.ezykle.entities.Token;
import ai.acintyo.ezykle.repositories.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class CustomLogoutHandler implements LogoutHandler {

	private final TokenRepository tokenRepository;

	public CustomLogoutHandler(TokenRepository tokenRepository) {
		this.tokenRepository = tokenRepository;
	}

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		String authHeader = request.getHeader("Authorization");

		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			return;
		}

		String token = authHeader.substring(7);
		Token storedToken = tokenRepository.findByToken(token).orElse(null);

		if (storedToken != null) {
			storedToken.setLoggedOut(true);
			tokenRepository.save(storedToken);
			SecurityContextHolder.clearContext();
		}
		response.setStatus(HttpStatus.OK.value());
		response.setContentType("application/json");
		try {
			response.getWriter().write(" logout sucess fully  ");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}