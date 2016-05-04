package info.jeczmien.services.impl;

import info.jeczmien.dto.ErrorCode;
import info.jeczmien.dto.OperationResult;
import info.jeczmien.dto.OperationStatus;
import info.jeczmien.dto.StateInfo;
import info.jeczmien.model.ApplicationModel;
import info.jeczmien.model.ApplicationState;
import info.jeczmien.services.ApplicationService;
import info.jeczmien.services.PersistentService;
import info.jeczmien.services.SearchService;
import info.jeczmien.services.StateService;

import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.tapestry5.ioc.annotations.Inject;

public class ApplicationServiceImpl implements ApplicationService {

	@Inject
	private PersistentService persistentService;

	@Inject
	private SearchService searchService;

	@Inject
	private StateService stateService;

	@Override
	public OperationResult<ApplicationModel> updateState(ApplicationModel model, ApplicationState oldState) {
		OperationResult<ApplicationModel> retVal = new OperationResult<>();
		retVal.getErrorCodes().addAll(validate(model, oldState).getErrorCodes());
		if (retVal.getErrorCodes().isEmpty()) {
			if (isNewState(model)) {
				model.setState(ApplicationState.CREATED);
			}
			if (isPublishedState(model)) {
				model.setPublishedId(model.getId().intValue());
			}
			Long id = persistentService.save(model, false);
			ApplicationModel saved = loadModel(id);
			if (saved == null) {
				retVal.getErrorCodes().add(ErrorCode.DATA_NOT_SAVED);
				retVal.setStatus(OperationStatus.ERROR);
			} else {
				retVal.setStatus(OperationStatus.SUCCESS);
				retVal.getResults().add(saved);
			}
		} else {
			retVal.setStatus(OperationStatus.ERROR);
		}
		return retVal;
	}

	@Override
	public OperationResult<Boolean> validate(ApplicationModel model, ApplicationState oldState) {
		OperationResult<Boolean> retVal = new OperationResult<>();
		List<ErrorCode> errorList = retVal.getErrorCodes();
		if (isFinalState(oldState)) {
			errorList.add(ErrorCode.FINAL_STATE);
		} else {
			if (!isValidStateTransition(model, oldState)) {
				errorList.add(ErrorCode.INVALID_STATE_CHANGE);
			}
			if (StringUtils.isBlank(model.getName())) {
				errorList.add(ErrorCode.NO_NAME);
			}
			if (StringUtils.isBlank(model.getContent())) {
				errorList.add(ErrorCode.NO_DESCRIPTION);
			}
			if (!isNewState(model) && !isContentEditable(oldState)
					&& !StringUtils.equals(model.getContent(), loadModel(model.getId()).getContent())) {
				errorList.add(ErrorCode.CONTENT_MODIFICATION_FORBIDDEN);
			}
			if (ApplicationState.DELETED == model.getState() && StringUtils.isBlank(model.getDeleteReason())) {
				errorList.add(ErrorCode.NO_DELETE_REASON);
			}
			if (ApplicationState.REJECTED == model.getState() && StringUtils.isBlank(model.getRejectReason())) {
				errorList.add(ErrorCode.NO_REJECT_REASON);
			}
			if (errorList.isEmpty()) {
				retVal.setStatus(OperationStatus.SUCCESS);
			} else {
				retVal.setStatus(OperationStatus.ERROR);
			}
		}
		return retVal;
	}

	private boolean isValidStateTransition(ApplicationModel model, ApplicationState oldState) {
		if (model.getState() == oldState) {
			return true;
		}
		StateInfo queryDto = new StateInfo();
		queryDto.setCurrentState(oldState);
		List<ApplicationState> allowedStates = stateService.getNextAllowedState(queryDto);
		return ArrayUtils.contains(allowedStates.toArray(new ApplicationState[0]), model.getState());
	}

	private boolean isFinalState(ApplicationState oldState) {
		StateInfo queryDto = new StateInfo();
		queryDto.setCurrentState(oldState);
		return stateService.isFinalState(queryDto);
	}

	private ApplicationModel loadModel(Long id) {
		return searchService.getApplication(id);
	}

	private boolean isNewState(ApplicationModel model) {
		return model.getState() == ApplicationState.NEW;
	}

	private boolean isContentEditable(ApplicationState oldState) {
		StateInfo queryDto = new StateInfo();
		queryDto.setCurrentState(oldState);
		return stateService.isContentEditable(queryDto);
	}

	private boolean isPublishedState(ApplicationModel model) {
		return ApplicationState.PUBLISHED == model.getState();
	}
}
