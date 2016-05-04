package info.jeczmien.entity;

import info.jeczmien.model.ApplicationState;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity(name = "Application")
@Cacheable(false)
@Table(name = "Applications")
public class ApplicationEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 256)
	private String name;

	@Column(length = 2048)
	private String content;

	@Version
	private Timestamp modificationDate;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ApplicationState state;

	@Column(unique = true)
	private Integer publishedId;

	@Column(length = 256)
	private String rejectReason;

	@Column(length = 256)
	private String deleteReason;

	@OneToMany(mappedBy = "application")
	private final List<StateHistoryEntity> history = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Timestamp getModificationDate() {
		return modificationDate;
	}

	public ApplicationState getState() {
		return state;
	}

	public void setState(ApplicationState state) {
		this.state = state;
	}

	public Integer getPublishedId() {
		return publishedId;
	}

	public void setPublishedId(Integer publishedId) {
		this.publishedId = publishedId;
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

	public List<StateHistoryEntity> getHistory() {
		return history;
	}

}
