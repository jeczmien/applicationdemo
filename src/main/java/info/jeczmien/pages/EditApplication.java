/**
 * 
 */
package info.jeczmien.pages;

import info.jeczmien.dto.ErrorCode;
import info.jeczmien.dto.OperationResult;
import info.jeczmien.dto.OperationStatus;
import info.jeczmien.model.ApplicationModel;
import info.jeczmien.model.ApplicationState;
import info.jeczmien.services.ApplicationService;
import info.jeczmien.services.StateService;

import java.text.MessageFormat;
import java.util.List;

import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 * @author jeczmien
 *
 */
public class EditApplication {

	@Property(write = false)
	@Persist
	private ApplicationModel application;

	@Persist
	private ApplicationState oldState;

	@Component(id = "editor", parameters = { "object=application", "exclude=modificationDate,id,publishedId",
			"submitLabel=Save" })
	private BeanEditForm editor;

	@Inject
	private StateService stateService;

	@Inject
	private ApplicationService applicationService;

	@Inject
	private AlertManager alertManager;

	@Inject
	private Messages messages;

	@InjectPage
	private Index index;

	public void setApplication(ApplicationModel application) {
		this.application = application;
		oldState = application.getState();
	}

	void onValidate() {
		OperationResult<Boolean> errors = applicationService.validate(application, oldState);
		if (OperationStatus.ERROR == errors.getStatus()) {
			recordErrors(errors.getErrorCodes());
		}
	}

	Object onSuccess() {
		OperationResult<ApplicationModel> errors = applicationService.updateState(application, oldState);
		if (OperationStatus.ERROR == errors.getStatus()) {
			recordErrors(errors.getErrorCodes());
			return this;
		}
		return index;
	}

	Object onCanceled() {
		return index;
	}

	private void recordErrors(List<ErrorCode> errors) {
		for (ErrorCode code : errors) {
			MessageFormat mf = new MessageFormat(messages.get(code.name()));
			editor.recordError(mf.format(new Object[] { application.getState(), oldState }, new StringBuffer(), null)
					.toString());
		}
	}
}