package info.jeczmien.rest.model;

import info.jeczmien.model.ApplicationState;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Application")
public class ApplicationObject {

	private Long id;
	private Integer publishedId;
	private ApplicationState state;
	private String name;
	private String content;
	private String rejectReason;
	private String deleteReason;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getPublishedId() {
		return publishedId;
	}

	public void setPublishedId(Integer publishedId) {
		this.publishedId = publishedId;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	public String getDeleteReason() {
		return deleteReason;
	}

	public void setDeleteReason(String deleteReason) {
		this.deleteReason = deleteReason;
	}

}
