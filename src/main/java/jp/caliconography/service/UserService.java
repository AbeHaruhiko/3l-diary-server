package jp.caliconography.service;

import jp.caliconography.domain.model.UserEntity;
import jp.caliconography.service.shared.RegisterUserInit;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

	UserEntity registerUser(RegisterUserInit init);

}
