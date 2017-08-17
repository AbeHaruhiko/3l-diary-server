package jp.caliconography.service.impl;

import jp.caliconography.conf.SecurityConfig.Roles;
import jp.caliconography.domain.dao.TestRepository;
import jp.caliconography.domain.model.TestEntity;
import jp.caliconography.service.TestService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
public class TestServiceImpl implements TestService {

	private final static String COUNTER_TEST = "3l-diary.entity.test.";

	@Autowired
	private TestRepository testRepository;

	@Autowired
	private CounterService counterService;

	@Transactional
	@Secured(value = Roles.ROLE_USER)
	public Collection<TestEntity> findAll() {
		return testRepository.findAll();
	}

	@Transactional
	@Secured(value = Roles.ROLE_USER)
	public TestEntity create(String name) {
		if (StringUtils.isBlank(name)) {
			throw new IllegalArgumentException("TestNameIsBlank");
		}
		//TODO Create event here
		counterService.increment(COUNTER_TEST + "created");

		TestEntity entity = new TestEntity(name);
		return testRepository.save(entity);
	}
}
