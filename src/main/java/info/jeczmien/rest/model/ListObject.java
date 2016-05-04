package info.jeczmien.rest.model;

import info.jeczmien.model.ApplicationState;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ListElement")
public class ListObject {

	private Long id;
	private Date lastModification;
	private ApplicationState state;
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getLastModification() {
		return lastModification;
	}

	public void setLastModification(Date lastModification) {
		this.lastModification = lastModification;
	}

	public ApplicationState getState() {
		return state;
	}

	public void setState(ApplicationState state) {
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
