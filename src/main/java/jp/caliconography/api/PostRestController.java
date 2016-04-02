package jp.caliconography.api;
import java.net.URI;
import java.util.Date;
import java.util.List;

import javax.inject.Named;
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
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestBody;
import jp.caliconography.domain.Post;
import jp.caliconography.service.PostService;

@Named
@Path("/api/posts")
public class PostRestController {

	@Autowired
	PostService postService;

	// 全件取得
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Page<Post> getPosts(
			@QueryParam("page") @DefaultValue("0") int page,
	        @QueryParam("size") @DefaultValue("20") int size,
	        @QueryParam("sort") @DefaultValue("createdAt") List<String> sort,
	        @QueryParam("direction") @DefaultValue("desc") String direction,
	        @QueryParam("q") @DefaultValue("") String keyword) {
		
		System.out.println("############### page:" + page + ", size:" + size + ", sort:" + sort + ", direction:" + direction);
		
		Page<Post> posts = null;
		if ("".equals(keyword)) {
			// 検索でない
			posts = postService.findAll(
					new PageRequest(
		                    page, 
		                    size, 
		                    Sort.Direction.fromString(direction), 
		                    sort.toArray(new String[0])
		            )
			);
		} else {
			// 検索のとき
			posts = postService.findAsFullTextSearch(keyword,
					new PageRequest(
		                    page, 
		                    size, 
		                    Sort.Direction.fromString(direction), 
		                    sort.toArray(new String[0])
		            )
			);
		}
		return posts;
	}

	// 一件取得
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Post getPost(@PathParam("id") String id) {
		return postService.findOne(id);
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
	@DELETE
	@Path("{id}")
	public void deletePost(@PathParam("id") String id) {
		postService.delete(id);
	}
}