package jp.caliconography.domain.dao;

import jp.caliconography.domain.model.RoleEntity;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<RoleEntity, Long> {

	RoleEntity findByAuthority(String authority);
}
