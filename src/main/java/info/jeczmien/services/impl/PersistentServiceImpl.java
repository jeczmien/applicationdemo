package info.jeczmien.services.impl;

import info.jeczmien.dal.ApplicationEntityDAO;
import info.jeczmien.dal.ApplicationEntitySearch;
import info.jeczmien.entity.ApplicationEntity;
import info.jeczmien.entity.StateHistoryEntity;
import info.jeczmien.model.ApplicationModel;
import info.jeczmien.model.ApplicationState;
import info.jeczmien.services.PersistentService;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.tapestry5.ioc.annotations.Inject;

public class PersistentServiceImpl implements PersistentService {

	@Inject
	private ApplicationEntityDAO applicationDao;

	@Override
	public Long save(ApplicationModel model, boolean delete) {
		ApplicationEntity entity = loadEntity(model.getId());
		fillEntity(entity, model);
		entity = applicationDao.merge(entity, delete);
		return entity.getId();
	}

	private ApplicationEntity loadEntity(Long id) {
		if (id != null) {
			ApplicationEntitySearch searchParams = new ApplicationEntitySearch();
			searchParams.setId(id);
			List<ApplicationEntity> list = applicationDao.find(searchParams);
			if (!list.isEmpty()) {
				return list.get(0);
			}
		}
		return new ApplicationEntity();
	}

	private void fillEntity(ApplicationEntity entity, ApplicationModel model) {
		if (entity.getState() != model.getState()) {
			StateHistoryEntity history = new StateHistoryEntity();
			history.setApplication(entity);
			history.setPreviousState(entity.getState() == null ? ApplicationState.NEW : entity.getState());
			history.setCurrentState(model.getState());
			entity.getHistory().add(history);
		}
		entity.setName(model.getName());
		entity.setContent(StringUtils.trimToNull(model.getContent()));
		entity.setDeleteReason(StringUtils.trimToNull(model.getDeleteReason()));
		entity.setPublishedId(model.getPublishedId());
		entity.setRejectReason(StringUtils.trimToNull(model.getRejectReason()));
		entity.setState(model.getState());
	}
}
