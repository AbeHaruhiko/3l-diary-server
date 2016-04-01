package jp.caliconography.respository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jp.caliconography.domain.Template;

@Repository
public interface TemplateRepository extends JpaRepository<Template, String> {

//	@Query("select x from Template x join fetch x.templateItems order by x.createdAt desc")
	@Query("select x from Template x order by x.createdAt desc")
	Page<Template> findAllOrderByName(Pageable pageable);
}