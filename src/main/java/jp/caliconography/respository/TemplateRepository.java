package jp.caliconography.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.caliconography.domain.Template;

@Repository
public interface TemplateRepository extends JpaRepository<Template, String> {

	@Query(value ="select distinct x from Template x join fetch x.templateItems as i where x.username = :username order by x.createdAt desc, i.sequence asc")
	List<Template> findByUsernameOrderByCreatedAt(@Param("username") String username);
	
//	List<Template> findByUsernameOrderByCreatedAt(String username);
}