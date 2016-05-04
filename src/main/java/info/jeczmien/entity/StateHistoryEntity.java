package info.jeczmien.entity;

import info.jeczmien.model.ApplicationState;

import java.sql.Timestamp;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity(name = "StateHistory")
@Cacheable(false)
@Table(name = "StateHistories")
public class StateHistoryEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private ApplicationEntity application;

	@Version
	private Timestamp modificationDate;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ApplicationState previousState;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ApplicationState currentState;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ApplicationEntity getApplication() {
		return application;
	}

	public void setApplication(ApplicationEntity application) {
		this.application = application;
	}

	public Timestamp getModificationDate() {
		return modificationDate;
	}

	public ApplicationState getPreviousState() {
		return previousState;
	}

	public void setPreviousState(ApplicationState previousState) {
		this.previousState = previousState;
	}

	public ApplicationState getCurrentState() {
		return currentState;
	}

	public void setCurrentState(ApplicationState currentState) {
		this.currentState = currentState;
	}
}
