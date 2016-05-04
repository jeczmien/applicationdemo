package info.jeczmien.dal;

import info.jeczmien.model.ApplicationState;

import java.util.ArrayList;
import java.util.List;

public class ApplicationEntitySearch {

	private Long id;
	private int startIndex;
	private int endIndex;
	private String name;
	private ApplicationState state;

	private final List<SortCriterion> sortCriteria = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public List<SortCriterion> getSortCriteria() {
		return sortCriteria;
	}
}
