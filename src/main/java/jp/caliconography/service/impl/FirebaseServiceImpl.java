package jp.caliconography.service.impl;

import jp.caliconography.conf.auth.firebase.FirebaseTokenHolder;
import jp.caliconography.service.FirebaseService;
import jp.caliconography.service.shared.FirebaseParser;
import jp.caliconography.spring.conditionals.FirebaseCondition;
import org.springframework.stereotype.Service;

@Service
@FirebaseCondition
public class FirebaseServiceImpl implements FirebaseService {
	@Override
	public FirebaseTokenHolder parseToken(String firebaseToken) {
		return new FirebaseParser().parseToken(firebaseToken);
	}
}
