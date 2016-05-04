package info.jeczmien.rest.model;

import javax.ws.rs.QueryParam;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "listrequest")
public class ListQueryObject {

	@QueryParam("name")
	private String nameString;
	@QueryParam("state")
	private String stateString;

	public String getNameString() {
		return nameString;
	}

	public void setNameString(String nameString) {
		this.nameString = nameString;
	}

	public String getStateString() {
		return stateString;
	}

	public void setStateString(String stateString) {
		this.stateString = stateString;
	}

}
