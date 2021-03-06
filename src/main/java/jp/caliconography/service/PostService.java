package jp.caliconography.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.caliconography.domain.PastPost;
import jp.caliconography.domain.Post;
import jp.caliconography.respository.PastPostRepository;
import jp.caliconography.respository.PostRepository;

@Service
public class PostService {
	@Autowired
	PostRepository postRepository;

	@Autowired
	PastPostRepository pastPostRepository;

	public Page<Post> findByUsername(Pageable pageable, String username) {
		return postRepository.findByUsername(username, pageable);
	}

	@SuppressWarnings({ "serial" })
	public Page<Post> findAtRandomByUsername(String username) {
		
//		Date now = new Date();
		LocalDateTime now = LocalDateTime.now();
		// すでにその日用の過去投稿が保存されているか確認
		PastPost pastPost = pastPostRepository.findByForDateAndUsername(Date.valueOf(now.toLocalDate()), username);
		System.out.println("############### pastPost: " + pastPost);
		if (pastPost == null) {
			// まだないので過去投稿から検索
			int totalPostCount = this.countByUsername(username);
			Random random = new Random();
			int randomPosition = random.nextInt(totalPostCount);

			// その日用の過去投稿として保存（ブラウザリロード時などに毎回違う投稿が表示されないように保存しておく。
			Page<Post> page = postRepository.findByUsername(username, 
					new PageRequest(
							randomPosition,
							1,
							Sort.Direction.DESC,
							"createdAt"
					)
			);
			if (page != null && page.getSize() > 0) {
				Post post = page.getContent().get(0);
				System.out.println("################ " + now);
				pastPostRepository.save(new PastPost(null, post.getId(), Date.valueOf(now.toLocalDate()), username, Timestamp.valueOf(now), Timestamp.valueOf(now)));
			}
			return page;
			
		} else {
			// ある
			System.out.println("################ " + pastPost.getPostId());
			Post post = this.findOne(pastPost.getPostId());
			System.out.println(post);
			return new PageImpl<Post>(new ArrayList<Post>(){{ add(post); }});
		}
		
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
}