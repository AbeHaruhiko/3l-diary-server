package jp.caliconography.api;

import jp.caliconography.domain.Post;
import jp.caliconography.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestBody;

import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

@Named
@Path("/api/posts")
public class PostRestController {

	@Autowired
	PostService postService;

    @OPTIONS
    public Response cors() {
        return Response.ok()
//            .header("Access-Control-Allow-Origin", "http://localhost:8888")
//            .header("Access-Control-Allow-Methods",
//                    "GET, POST, PUT, DELETE, OPTIONS, HEAD")
//            .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization, X-Authorization-Firebase")
            .build();
    }

	// 全件取得
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Page<Post> getPosts(
			@QueryParam("page") @DefaultValue("0") int page,
	        @QueryParam("size") @DefaultValue("20") int size,
	        @QueryParam("sort") @DefaultValue("createdAt") List<String> sort,
	        @QueryParam("direction") @DefaultValue("desc") String direction,
	        @QueryParam("q") @DefaultValue("") String keyword) {
		
//		User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println("############### page:" + page + ", size:" + size + ", sort:" + sort + ", direction:" + direction);
		
		Page<Post> posts = null;
		if ("".equals(keyword)) {
			// 検索でない
			posts = postService.findByUsername(
					new PageRequest(
		                    page, 
		                    size, 
		                    Sort.Direction.fromString(direction), 
		                    sort.toArray(new String[0])
		            ),
//					principal.getUsername()
                    ""
			);
		} else {
			// 検索のとき
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
		
		User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		// ユーザ名をセット
		post.setUsername(principal.getUsername());

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

	// 削除
	@DELETE
	@Path("{id}")
	public void deletePost(@PathParam("id") String id) {
		postService.delete(id);
	}

	// 件数
	@GET
	@Path("/count")
	public long count() {
		
		User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		return postService.countByUsername(principal.getUsername());
	}

	// 過去の投稿
	@GET
	@Path("/past")
	@Produces(MediaType.APPLICATION_JSON)
	public Page<Post> getPastPost() throws NoSuchAlgorithmException {
		
		User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		return postService.findAtRandomByUsername(principal.getUsername());
	}
}