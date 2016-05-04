package info.jeczmien.rest.model;

import javax.ws.rs.QueryParam;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ApplicationRequest")
public class ApplicationLoadObject {
	@QueryParam("id")
	private String idString;

	public String getIdString() {
		return idString;
	}

	public void setIdString(String idString) {
		this.idString = idString;
	}

}
