package jp.caliconography.service;

import java.util.List;

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

	public Page<Post> findAll(Pageable pageable) {
		return postRepository.findAllOrderByName(pageable);
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
	 public List<Post> findAsFullTextSearch(String keyword) {
		 System.out.println("############## service: keyword:" + keyword);
		 return postSearchRepository.searchTitleOrSummary(keyword);
	}
}