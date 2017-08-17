package jp.caliconography.service.shared;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.tasks.Task;
import com.google.firebase.tasks.Tasks;
import jp.caliconography.conf.auth.firebase.FirebaseTokenHolder;
import jp.caliconography.service.exception.FirebaseTokenInvalidException;
import org.apache.commons.lang.StringUtils;

public class FirebaseParser {
	public FirebaseTokenHolder parseToken(String idToken) {
		if (StringUtils.isBlank(idToken)) {
			throw new IllegalArgumentException("FirebaseTokenBlank");
		}
		try {
			Task<FirebaseToken> authTask = FirebaseAuth.getInstance().verifyIdToken(idToken);

			Tasks.await(authTask);

			return new FirebaseTokenHolder(authTask.getResult());
		} catch (Exception e) {
			throw new FirebaseTokenInvalidException(e.getMessage());
		}
	}
}
