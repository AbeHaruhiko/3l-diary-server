package jp.caliconography.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	// public Post save(Post Post) {
	// return PostRepository.save(Post);
	// }

	public Page<Post> findByUsername(Pageable pageable, String username) {
		return postRepository.findByUsername(username, pageable);
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
	
	 @Transactional(readOnly = true)
	 public Page<Post> findAsFullTextSearch(String keyword, Pageable pageable, String username) {
		 System.out.println("############## service: keyword:" + keyword);
		 return postSearchRepository.findAsFullTextSearch(keyword, pageable, username);
	}
}