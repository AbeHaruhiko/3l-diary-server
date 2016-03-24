package jp.caliconography;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import jp.caliconography.domain.Post;
import jp.caliconography.service.PostService;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Named
@Path("/api")
public class HelloEndpoint {

	@Autowired
	PostService PostService;

	@Inject
    NamedParameterJdbcTemplate jdbcTemplate;

    @GET
    public String hello() {
        return "Hello World!!!???";
    }

    @Data
    static class Result {
        private final int left;
        private final int right;
        private final long answer;
    }

    // SQL sample
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("calc")
    public Result calc(@QueryParam("left") int left, @QueryParam("right") int right) {
        MapSqlParameterSource source = new MapSqlParameterSource()
                .addValue("left", left)
                .addValue("right", right);
        return jdbcTemplate.queryForObject("SELECT :left + :right AS answer", source,
                (rs, rowNum) -> new Result(left, right, rs.getLong("answer")));
    }
    
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    @Path("posts")
//    public String getPosts() {
//    	System.out.println("here");
//        return "posts";
//    }
    
    @Data
    static class User {
    	private final String username;
    }
    
    @Path("user")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public User get(@FormParam("username") String username) {
    	return new User(username);
    }
    
//	// 顧客全件取得
//	@GET
//	@Path("posts")
//	@Produces(MediaType.APPLICATION_JSON)
//	List<Post> getPosts() {
//		List<Post> Posts = PostService.findAll();
//		return Posts;
//	}

}
