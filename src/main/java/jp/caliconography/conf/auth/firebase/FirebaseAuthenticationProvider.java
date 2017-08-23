package jp.caliconography.conf.auth.firebase;

import jp.caliconography.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class FirebaseAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	@Qualifier(value = UserServiceImpl.NAME)
	private UserDetailsService userService;

	public boolean supports(Class<?> authentication) {
		return (FirebaseAuthenticationToken.class.isAssignableFrom(authentication));
	}

	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		if (!supports(authentication.getClass())) {
			return null;
		}

		FirebaseAuthenticationToken authenticationToken = (FirebaseAuthenticationToken) authentication;
//		UserDetails details = userService.loadUserByUsername(authenticationToken.getName());
//		if (details == null) {
//			throw new FirebaseUserNotExistsException();
//		}

		authenticationToken = new FirebaseAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(),
				null);

		return authenticationToken;
	}

}
