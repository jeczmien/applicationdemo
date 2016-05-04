package info.jeczmien.services.impl;

import info.jeczmien.dto.StateInfo;
import info.jeczmien.model.ApplicationAction;
import info.jeczmien.model.ApplicationState;
import info.jeczmien.services.StateService;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;

public class StateServiceImpl implements StateService {

	private static final ApplicationState[] CONTENT_EDITABLE = { ApplicationState.NEW, ApplicationState.CREATED,
			ApplicationState.VERIFIED };

	private static final ApplicationState[] FINAL_STATE = { ApplicationState.DELETED, ApplicationState.REJECTED,
			ApplicationState.PUBLISHED };

	@Override
	public List<ApplicationState> getNextAllowedState(StateInfo queryDto) {
		List<ApplicationState> retVal = new ArrayList<>();
		switch (queryDto.getCurrentState()) {
		case NEW:
			retVal.add(ApplicationState.CREATED);
			break;
		case CREATED:
			retVal.add(ApplicationState.DELETED);
			retVal.add(ApplicationState.VERIFIED);
			break;
		case VERIFIED:
			retVal.add(ApplicationState.ACCEPTED);
			retVal.add(ApplicationState.REJECTED);
			break;
		case ACCEPTED:
			retVal.add(ApplicationState.PUBLISHED);
			retVal.add(ApplicationState.REJECTED);
			break;
		default:
			// no states
		}
		return retVal;
	}

	@Override
	public List<ApplicationAction> getAllowedActions(StateInfo queryDto) {
		List<ApplicationAction> retVal = new ArrayList<>();
		switch (queryDto.getCurrentState()) {
		case NEW:
			retVal.add(ApplicationAction.CREATE);
			break;
		case CREATED:
			retVal.add(ApplicationAction.DELETE);
			retVal.add(ApplicationAction.VERIFY);
			break;
		case VERIFIED:
			retVal.add(ApplicationAction.REJECT);
			retVal.add(ApplicationAction.ACCEPT);
			break;
		case ACCEPTED:
			retVal.add(ApplicationAction.REJECT);
			retVal.add(ApplicationAction.PUBLISH);
			break;
		default:
			// no actions
		}
		return retVal;
	}

	@Override
	public boolean isContentEditable(StateInfo queryDto) {
		return ArrayUtils.contains(CONTENT_EDITABLE, queryDto.getCurrentState());
	}

	@Override
	public boolean isFinalState(StateInfo queryDto) {
		return ArrayUtils.contains(FINAL_STATE, queryDto.getCurrentState());
	}

}
