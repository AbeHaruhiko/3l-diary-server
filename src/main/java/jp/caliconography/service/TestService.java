package jp.caliconography.service;

import jp.caliconography.domain.model.TestEntity;

import java.util.Collection;

public interface TestService {

	Collection<TestEntity> findAll();

	TestEntity create(String name);

}
