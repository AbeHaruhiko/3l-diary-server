package jp.caliconography.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jp.caliconography.domain.Template;

@Repository
public interface TemplateRepository extends JpaRepository<Template, String> {

	@Query(value ="select distinct x from Template x join fetch x.templateItems order by x.createdAt desc")
//	@Query("select x from Template x order by x.createdAt desc")
	List<Template> findAllOrderByCreatedAt();
	
	List<Template> findByUsernameOrderByCreatedAt(String username);
}