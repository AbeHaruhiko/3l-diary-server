package jp.caliconography.respository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.caliconography.domain.PastPost;

@Repository
public interface PastPostRepository extends JpaRepository<PastPost, String> {

	// 命名規則でSQL発行
	PastPost findByForDateAndUsername(LocalDate forDate, String username);
}