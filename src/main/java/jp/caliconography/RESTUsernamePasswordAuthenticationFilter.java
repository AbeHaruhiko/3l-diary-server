package jp.caliconography;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Authentication filter for REST services
 */
public class RESTUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	@Override
	protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
		boolean retVal = false;
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if (username != null && password != null) {
			Authentication authResult = null;
			try {
				authResult = attemptAuthentication(request, response);
				if (authResult == null) {
					retVal = false;
				}
			} catch (AuthenticationException failed) {
				try {
					unsuccessfulAuthentication(request, response, failed);
				} catch (IOException e) {
					retVal = false;
				} catch (ServletException e) {
					retVal = false;
				}
				retVal = false;
			}
			try {
				successfulAuthentication(request, response, null, authResult);
			} catch (IOException e) {
				retVal = false;
			} catch (ServletException e) {
				retVal = false;
			}
			return false;
		} else {
			retVal = true;
		}
		return retVal;
	}
}