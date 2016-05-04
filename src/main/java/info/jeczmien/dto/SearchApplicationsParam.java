package info.jeczmien.dto;

import info.jeczmien.model.ApplicationState;

public class SearchApplicationsParam {

	private int startIndex;
	private int endIndex;
	private String name;
	private ApplicationState state;

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ApplicationState getState() {
		return state;
	}

	public void setState(ApplicationState state) {
		this.state = state;
	}

}
