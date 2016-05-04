package info.jeczmien.services;

import info.jeczmien.dto.SearchApplicationsParam;
import info.jeczmien.model.ApplicationModel;
import info.jeczmien.model.HistoryModel;
import info.jeczmien.model.ListModel;

import java.util.List;

public interface SearchService {

	List<ListModel> searchApplications(SearchApplicationsParam searchParams);

	int countApplications(SearchApplicationsParam searchParams);

	ApplicationModel getApplication(Long id);

	List<HistoryModel> getHistory(Long applicationId);
}
