package jp.caliconography.api;
import java.net.URI;
import java.util.Date;
import java.util.List;

import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestBody;
import jp.caliconography.domain.Template;
import jp.caliconography.service.TemplateService;

@Named
@Path("/api/templates")
public class TemplateRestController {

	@Autowired
	TemplateService TemplateService;

	// 全件取得
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Template> getTemplates() {

		User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		return TemplateService.findByUsernameOrderByCreatedAt(principal.getUsername());
	}

	// 一件取得
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Template getTemplate(@PathParam("id") String id) {
		return TemplateService.findOne(id);
	}

	// 新規作成
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response TemplateTemplates(Template Template, @Context UriInfo uriInfo) {
		
		User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		// ユーザ名をセット
		Template.setUsername(principal.getUsername());
		
		// 処理時刻をセット
		Date now = new Date();
		Template.setCreatedAt(now);
		Template.setUpdatedAt(now);
		Template.getTemplateItems().forEach(item ->  {
			item.setCreatedAt(now);
			item.setUpdatedAt(now);
		});


		
		Template created = TemplateService.create(Template);
		URI uri = uriInfo.getAbsolutePathBuilder().path(created.getId()).build();
		return Response.created(uri).entity(created).build();
	}

	// 一件更新
	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Template putTemplate(@PathParam("id") String id, @RequestBody Template Template) {
		Template.setId(id);
		
		// 処理時刻をセット
		Date now = new Date();
		Template.setUpdatedAt(now);
		Template.getTemplateItems().forEach(item -> item.setUpdatedAt(now));

		return TemplateService.update(Template);
	}

	@DELETE
	@Path("{id}")
	public void deleteTemplate(@PathParam("id") String id) {
		TemplateService.delete(id);
	}
}