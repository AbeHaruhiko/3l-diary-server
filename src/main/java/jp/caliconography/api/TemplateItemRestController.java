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
import jp.caliconography.domain.Template;
import jp.caliconography.domain.TemplateItem;
import jp.caliconography.service.TemplateItemService;

@Named
@Path("/api/templates/items")
public class TemplateItemRestController {

	@Autowired
	TemplateItemService TemplateItemService;

	// 全件取得
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<TemplateItem> getTemplateItems() {
		return TemplateItemService.findAll();
	}

	// 一件取得
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public TemplateItem getTemplateItem(@PathParam("id") String id) {
		return TemplateItemService.findOne(id);
	}

	// 新規作成
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response TemplateTemplateItems(TemplateItem TemplateItem, @Context UriInfo uriInfo) {
		
		// 処理時刻をセット
		Date now = new Date();
		TemplateItem.setCreatedAt(now);
		TemplateItem.setUpdatedAt(now);
		
		TemplateItem created = TemplateItemService.create(TemplateItem);
		URI uri = uriInfo.getAbsolutePathBuilder().path(created.getId()).build();
		return Response.created(uri).entity(created).build();
	}

	// 一件更新
	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public TemplateItem putTemplateItem(@PathParam("id") String id, @RequestBody TemplateItem TemplateItem) {
		TemplateItem.setId(id);
		
		// 処理時刻をセット
		Date now = new Date();
		TemplateItem.setUpdatedAt(now);

		return TemplateItemService.update(TemplateItem);
	}

	@DELETE
	@Path("{id}")
	public void deleteTemplateItem(@PathParam("id") String id) {
		TemplateItemService.delete(id);
	}
}