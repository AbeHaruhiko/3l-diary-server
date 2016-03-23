package jp.caliconography.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	public List<Post> findAll() {
		return PostRepository.findAllOrderByName();
	}

	public Post findOne(Integer id) {
		return PostRepository.findOne(id);
	}

	public Post create(Post Post) {
		return PostRepository.save(Post);
	}

	public Post update(Post Post) {
		return PostRepository.save(Post);
	}

	public void delete(Integer id) {
		PostRepository.delete(id);
	}
}