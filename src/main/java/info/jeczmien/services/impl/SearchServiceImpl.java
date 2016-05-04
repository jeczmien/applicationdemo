package info.jeczmien.services.impl;

import info.jeczmien.dal.ApplicationEntityDAO;
import info.jeczmien.dal.ApplicationEntitySearch;
import info.jeczmien.dto.SearchApplicationsParam;
import info.jeczmien.entity.ApplicationEntity;
import info.jeczmien.entity.StateHistoryEntity;
import info.jeczmien.model.ApplicationModel;
import info.jeczmien.model.HistoryModel;
import info.jeczmien.model.ListModel;
import info.jeczmien.services.SearchService;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.ioc.annotations.Inject;

public class SearchServiceImpl implements SearchService {

	@Inject
	private ApplicationEntityDAO applicationDao;

	@Override
	public List<ListModel> searchApplications(SearchApplicationsParam searchParams) {
		List<ListModel> retVal = new ArrayList<>(searchParams.getEndIndex() - searchParams.getStartIndex());
		for (ApplicationEntity entity : applicationDao.find(convert(searchParams))) {
			retVal.add(convertToList(entity));
		}
		return retVal;
	}

	@Override
	public int countApplications(SearchApplicationsParam searchParams) {
		return applicationDao.count(convert(searchParams));
	}

	@Override
	public ApplicationModel getApplication(Long id) {
		ApplicationEntity entity = getEntity(id);
		if (entity != null) {
			return convertToApplication(entity);
		}
		return null;
	}

	@Override
	public List<HistoryModel> getHistory(Long applicationId) {
		ApplicationEntity model = getEntity(applicationId);
		List<HistoryModel> retVal = null;
		if (model != null) {
			retVal = new ArrayList<>(model.getHistory().size());
			for (StateHistoryEntity entity : model.getHistory()) {
				retVal.add(convert(entity));
			}
		}
		return retVal;
	}

	private ApplicationEntity getEntity(Long id) {
		ApplicationEntitySearch parameters = new ApplicationEntitySearch();
		parameters.setId(id);
		List<ApplicationEntity> list = applicationDao.find(parameters);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	private ApplicationEntitySearch convert(SearchApplicationsParam searchParams) {
		ApplicationEntitySearch retVal = new ApplicationEntitySearch();
		retVal.setEndIndex(searchParams.getEndIndex());
		retVal.setStartIndex(searchParams.getStartIndex());
		retVal.setName(searchParams.getName());
		retVal.setState(searchParams.getState());
		return retVal;
	}

	private ApplicationModel convertToApplication(ApplicationEntity entity) {
		ApplicationModel retVal = new ApplicationModel();
		retVal.setContent(entity.getContent());
		retVal.setDeleteReason(entity.getDeleteReason());
		retVal.setId(entity.getId());
		retVal.setName(entity.getName());
		retVal.setPublishedId(entity.getPublishedId());
		retVal.setRejectReason(entity.getRejectReason());
		retVal.setState(entity.getState());
		return retVal;
	}

	private ListModel convertToList(ApplicationEntity entity) {
		ListModel retVal = new ListModel();
		retVal.setId(entity.getId());
		retVal.setLastModification(entity.getModificationDate());
		retVal.setName(entity.getName());
		retVal.setState(entity.getState());
		return retVal;
	}

	private HistoryModel convert(StateHistoryEntity entity) {
		HistoryModel retVal = new HistoryModel();
		retVal.setCurrentState(entity.getCurrentState());
		retVal.setModificationDate(entity.getModificationDate());
		retVal.setPreviousState(entity.getPreviousState());
		return retVal;
	}
}
