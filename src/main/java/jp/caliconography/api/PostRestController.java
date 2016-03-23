package jp.caliconography.api;
import java.net.URI;
import java.util.List;

import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jp.caliconography.domain.Post;
import jp.caliconography.service.PostService;

@Named
@Path("/api/posts")
public class PostRestController {

	@Autowired
	PostService PostService;

	// 顧客全件取得
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	List<Post> getPosts() {
		System.out.println("get posts.");
		List<Post> Posts = PostService.findAll();
		return Posts;
	}

	// 顧客一件取得
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	Post getPost(@PathParam("id") Integer id) {
		Post Post = PostService.findOne(id);
		return Post;
	}

//	// 新規作成
//	@RequestMapping(method = RequestMethod.POST)
//	@ResponseStatus(HttpStatus.CREATED)
//	ResponseEntity<Post> postPosts(@RequestBody Post Post, UriComponentsBuilder uriBuilder) {
//		Post created = PostService.create(Post);
//		URI location = uriBuilder.path("api/Posts/{id}").buildAndExpand(created.getId()).toUri();
//		HttpHeaders headers = new HttpHeaders();
//		headers.setLocation(location);
//		return new ResponseEntity<>(created, headers, HttpStatus.CREATED);
//	}
//
//	// 一件更新
//	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
//	Post putPost(@PathVariable Integer id, @RequestBody Post Post) {
//		Post.setId(id);
//		return PostService.update(Post);
//	}
//
//	// 削除
//	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
//	@ResponseStatus(HttpStatus.NO_CONTENT)
//	void deletePost(@PathVariable Integer id) {
//		PostService.delete(id);
//	}
}