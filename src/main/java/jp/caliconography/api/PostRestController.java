package jp.caliconography.api;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Named;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
	PostService postService;

	// 顧客全件取得
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Page<Post> getPosts(
			@QueryParam("page") @DefaultValue("0") int page,
	        @QueryParam("size") @DefaultValue("20") int size,
	        @QueryParam("sort") @DefaultValue("createdAt") List<String> sort,
	        @QueryParam("direction") @DefaultValue("desc") String direction) {
		
		System.out.println("############### page:" + page + ", size:" + size + ", sort:" + sort + ", direction:" + direction);
		Page<Post> posts = postService.findAll(
				new PageRequest(
	                    page, 
	                    size, 
	                    Sort.Direction.fromString(direction), 
	                    sort.toArray(new String[0])
	            )
		);
		return posts;
	}

	// 顧客一件取得
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Post getPost(@PathParam("id") String id) {
		Post Post = postService.findOne(id);
		return Post;
	}

	// 新規作成
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response postPosts(Post post, @Context UriInfo uriInfo) {
		
		// 処理時刻をセット
		Date now = new Date();
		post.setCreatedAt(now);
		post.setUpdatedAt(now);
		
		Post created = postService.create(post);
		URI uri = uriInfo.getAbsolutePathBuilder().path(created.getId()).build();
		return Response.created(uri).entity(created).build();
	}

	// 一件更新
	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Post putPost(@PathParam("id") String id, @RequestBody Post post) {
		post.setId(id);
		
		// 処理時刻をセット
		Date now = new Date();
		post.setUpdatedAt(now);

		return postService.update(post);
	}

//	// 削除
//	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
//	@ResponseStatus(HttpStatus.NO_CONTENT)
//	public void deletePost(@PathVariable Integer id) {
//		PostService.delete(id);
//	}
	@DELETE
	@Path("{id}")
	public void deletePost(@PathParam("id") String id) {
		postService.delete(id);
	}
	
	@GET
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Post> searchPost(@QueryParam("q") String keyword) {
		System.out.println("############## api: keyword:" + keyword);
		return postService.findAsFullTextSearch(keyword);
	}
}