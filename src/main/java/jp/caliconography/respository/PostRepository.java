package jp.caliconography.respository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import jp.caliconography.domain.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

	@Query("select x from Post x order by x.createdAt")
	List<Post> findAllOrderByName();
}