package info.jeczmien.model;

import java.util.Date;

public class HistoryModel {

	private Date modificationDate;
	private ApplicationState previousState;
	private ApplicationState currentState;

	public Date getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
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
