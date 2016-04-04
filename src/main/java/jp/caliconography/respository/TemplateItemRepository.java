package jp.caliconography.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jp.caliconography.domain.TemplateItem;

@Repository
public interface TemplateItemRepository extends JpaRepository<TemplateItem, String> {

	@Query(value ="select distinct x from TemplateItem x order by x.createdAt desc")
	List<TemplateItem> findAllOrderByCreatedAt();
	
	List<TemplateItem> findByUsernameOrderByCreatedAt(String username);
}