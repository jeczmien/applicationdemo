package info.jeczmien.services;

import info.jeczmien.dto.OperationResult;
import info.jeczmien.model.ApplicationModel;
import info.jeczmien.model.ApplicationState;

public interface ApplicationService {
	OperationResult<ApplicationModel> updateState(ApplicationModel model, ApplicationState oldState);

	OperationResult<Boolean> validate(ApplicationModel model, ApplicationState oldState);
}
