package jp.caliconography.api;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Named;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

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
	public List<Post> getPosts() {
		List<Post> posts = PostService.findAll();
		return posts;
	}

	// 顧客一件取得
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Post getPost(@PathParam("id") String id) {
		Post Post = PostService.findOne(id);
		return Post;
	}

	// 新規作成
//	@POST
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	@ResponseStatus(HttpStatus.CREATED)
//	public ResponseEntity<Post> postPosts(Post post, UriComponentsBuilder uriBuilder) {
//		Post created = PostService.create(post);
//		URI location = uriBuilder.path("api/Posts/{id}").buildAndExpand(created.getId()).toUri();
//		HttpHeaders headers = new HttpHeaders();
//		headers.setLocation(location);
//		return new ResponseEntity<>(created, headers, HttpStatus.CREATED);
//	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response postPosts(Post post, @Context UriInfo uriInfo) {
		
		// 処理時刻をセット
		Date now = new Date();
		post.setCreatedAt(now);
		post.setUpdatedAt(now);
		
		Post created = PostService.create(post);
		URI uri = uriInfo.getAbsolutePathBuilder().path(created.getId()).build();
		return Response.created(uri).entity(created).build();
	}

//	@POST
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Post postPosts(Post post) {
//		System.out.println("################# " + post);
//		System.out.println("################# postPosts");
//		Post created = PostService.create(post);
//		return created;
//	}

//	// 一件更新
//	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
//	public Post putPost(@PathVariable Integer id, @RequestBody Post Post) {
//		Post.setId(id);
//		return PostService.update(Post);
//	}
//
//	// 削除
//	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
//	@ResponseStatus(HttpStatus.NO_CONTENT)
//	public void deletePost(@PathVariable Integer id) {
//		PostService.delete(id);
//	}
}