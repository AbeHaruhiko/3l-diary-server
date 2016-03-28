package jp.caliconography.respository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import jp.caliconography.domain.Post;

public interface PostRepository extends JpaRepository<Post, String> {

	@Query("select x from Post x order by x.createdAt desc")
	Page<Post> findAllOrderByName(Pageable pageable);
}