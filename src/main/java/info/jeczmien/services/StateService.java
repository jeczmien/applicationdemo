package info.jeczmien.services;

import info.jeczmien.dto.StateInfo;
import info.jeczmien.model.ApplicationAction;
import info.jeczmien.model.ApplicationState;

import java.util.List;

public interface StateService {

	List<ApplicationState> getNextAllowedState(StateInfo queryDto);

	List<ApplicationAction> getAllowedActions(StateInfo queryDto);

	boolean isContentEditable(StateInfo queryDto);

	boolean isFinalState(StateInfo queryDto);
}
