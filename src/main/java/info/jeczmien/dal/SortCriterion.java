package info.jeczmien.dal;

import javax.swing.SortOrder;

public class SortCriterion {
	private final SortOrder sortOrder;
	private final String columnName;

	public SortCriterion(String columnName, SortOrder sortOrder) {
		this.columnName = columnName;
		this.sortOrder = sortOrder;
	}

	public SortOrder getSortOrder() {
		return sortOrder;
	}

	public String getColumnName() {
		return columnName;
	}

}
