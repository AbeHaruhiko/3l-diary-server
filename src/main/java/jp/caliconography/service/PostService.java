package jp.caliconography.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jp.caliconography.domain.Post;
import jp.caliconography.respository.PostRepository;

@Service
public class PostService {
	@Autowired
	PostRepository PostRepository;

	// public Post save(Post Post) {
	// return PostRepository.save(Post);
	// }

	public Page<Post> findAll(Pageable pageable) {
		return PostRepository.findAllOrderByName(pageable);
	}

	public Post findOne(String id) {
		return PostRepository.findOne(id);
	}

	public Post create(Post Post) {
		return PostRepository.save(Post);
	}

	public Post update(Post Post) {
		return PostRepository.save(Post);
	}

	public void delete(String id) {
		PostRepository.delete(id);
	}
}