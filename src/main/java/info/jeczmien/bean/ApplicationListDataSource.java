package info.jeczmien.bean;

import info.jeczmien.dto.SearchApplicationsParam;
import info.jeczmien.model.FilterModel;
import info.jeczmien.model.ListModel;
import info.jeczmien.services.SearchService;

import java.util.List;

import org.apache.tapestry5.grid.GridDataSource;
import org.apache.tapestry5.grid.SortConstraint;

public class ApplicationListDataSource implements GridDataSource {

	private final FilterModel filter;
	private final SearchService searchService;

	private List<ListModel> preparedList;
	private int startIndex;

	public ApplicationListDataSource(FilterModel filter, SearchService searchService) {
		startIndex = 0;
		this.filter = filter;
		this.searchService = searchService;
	}

	@Override
	public int getAvailableRows() {
		SearchApplicationsParam searchParams = new SearchApplicationsParam();
		searchParams.setName(filter.getName());
		searchParams.setState(filter.getState());
		return searchService.countApplications(searchParams);
	}

	@Override
	public void prepare(int startIndex, int endIndex, List<SortConstraint> sortConstraints) {
		this.startIndex = startIndex;
		SearchApplicationsParam searchParams = new SearchApplicationsParam();
		searchParams.setName(filter.getName());
		searchParams.setState(filter.getState());
		searchParams.setStartIndex(startIndex);
		searchParams.setEndIndex(endIndex);
		preparedList = searchService.searchApplications(searchParams);
	}

	@Override
	public Object getRowValue(int index) {
		return preparedList.get(index - startIndex);
	}

	@Override
	public Class<ListModel> getRowType() {
		return ListModel.class;
	}

}
