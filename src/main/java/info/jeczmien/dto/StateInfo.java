package info.jeczmien.dto;

import info.jeczmien.model.ApplicationState;

public class StateInfo {
	private ApplicationState currentState;

	public ApplicationState getCurrentState() {
		return currentState;
	}

	public void setCurrentState(ApplicationState currentState) {
		this.currentState = currentState;
	}
}
