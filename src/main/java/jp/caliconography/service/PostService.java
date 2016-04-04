package jp.caliconography.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.caliconography.domain.Post;
import jp.caliconography.respository.PostRepository;
import jp.caliconography.respository.PostSearchRepository;

@Service
public class PostService {
	@Autowired
	PostRepository postRepository;

	@Autowired
	PostSearchRepository postSearchRepository;

	public Page<Post> findByUsername(Pageable pageable, String username) {
		return postRepository.findByUsername(username, pageable);
	}

	public Page<Post> findAtRandomByUsername(String username) {
		int totalPostCount = this.countByUsername(username);
		Random random = new Random();
		int randomPosition = random.nextInt(totalPostCount);

		return postRepository.findByUsername(username, 
				new PageRequest(
						randomPosition,
						1,
						Sort.Direction.DESC,
						"createdAt"
				)
		);
	}

	public Post findOne(String id) {
		return postRepository.findOne(id);
	}

	public Post create(Post Post) {
		return postRepository.save(Post);
	}

	public Post update(Post Post) {
		return postRepository.save(Post);
	}

	public void delete(String id) {
		postRepository.delete(id);
	}
	
	public int countByUsername(String username) {
		return postRepository.countByUsername(username);
	}
	
	 @Transactional(readOnly = true)
	 public Page<Post> findAsFullTextSearch(String keyword, Pageable pageable, String username) {
		 System.out.println("############## service: keyword:" + keyword);
		 return postSearchRepository.findAsFullTextSearch(keyword, pageable, username);
	}
}