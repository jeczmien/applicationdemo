package info.jeczmien.rest;

import info.jeczmien.rest.model.ApplicationLoadObject;
import info.jeczmien.rest.model.ApplicationObject;
import info.jeczmien.rest.model.ListObject;
import info.jeczmien.rest.model.ListQueryObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.jboss.resteasy.annotations.Form;

@Path("/application")
public interface ApplicationResource {
	@GET
	@Path("/list")
	@Produces({ "application/xml", "application/json" })
	ListObject[] getList(@Form ListQueryObject queryParam);

	@GET
	@Path("/load")
	@Produces({ "application/xml", "application/json" })
	ApplicationObject getLoad(@Form ApplicationLoadObject idParam);

	@GET
	@Path("/update")
	@Produces({ "application/xml", "application/json" })
	ApplicationObject getUpdate(@Form ApplicationObject queryParam);
}
