package info.jeczmien.rest.impl;

import info.jeczmien.dto.OperationResult;
import info.jeczmien.dto.OperationStatus;
import info.jeczmien.model.ApplicationModel;
import info.jeczmien.model.ListModel;
import info.jeczmien.rest.ApplicationResource;
import info.jeczmien.rest.model.ApplicationLoadObject;
import info.jeczmien.rest.model.ApplicationObject;
import info.jeczmien.rest.model.ListObject;
import info.jeczmien.rest.model.ListQueryObject;
import info.jeczmien.services.ApplicationService;
import info.jeczmien.services.SearchService;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.ioc.annotations.Inject;

public class ApplicationResourceImpl implements ApplicationResource {

	@Inject
	private SearchService searchService;

	@Inject
	private ApplicationService applicationService;

	@Override
	public ListObject[] getList(ListQueryObject queryParam) {
		List<ListObject> retVal = new ArrayList<>();
		for (ListModel model : searchService.searchApplications(ListObjectConverter.convert(queryParam))) {
			retVal.add(ListObjectConverter.convert(model));
		}
		return retVal.toArray(new ListObject[0]);
	}

	@Override
	public ApplicationObject getLoad(ApplicationLoadObject idParam) {
		return ApplicationObjectConverter.convert(searchService.getApplication(Long.parseLong(idParam.getIdString())));
	}

	@Override
	public ApplicationObject getUpdate(ApplicationObject queryParam) {
		ApplicationModel model = searchService.getApplication(queryParam.getId());
		OperationResult<ApplicationModel> retVal = applicationService.updateState(
				ApplicationObjectConverter.convert(queryParam), model.getState());
		if (retVal.getStatus() == OperationStatus.SUCCESS) {
			return ApplicationObjectConverter.convert(retVal.getResults().get(0));
		}
		throw new RuntimeException(retVal.getErrorCodes().get(0).name());
	}

}
