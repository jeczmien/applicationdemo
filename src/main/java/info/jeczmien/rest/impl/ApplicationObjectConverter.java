package info.jeczmien.rest.impl;

import info.jeczmien.model.ApplicationModel;
import info.jeczmien.rest.model.ApplicationObject;

public class ApplicationObjectConverter {
	private ApplicationObjectConverter() {
		//
	}

	public static final ApplicationObject convert(ApplicationModel model) {
		ApplicationObject retVal = new ApplicationObject();
		retVal.setContent(model.getContent());
		retVal.setDeleteReason(model.getDeleteReason());
		retVal.setId(model.getId());
		retVal.setName(model.getName());
		retVal.setPublishedId(model.getPublishedId());
		retVal.setRejectReason(model.getRejectReason());
		retVal.setState(model.getState());
		return retVal;
	}

	public static final ApplicationModel convert(ApplicationObject model) {
		ApplicationModel retVal = new ApplicationModel();
		retVal.setContent(model.getContent());
		retVal.setDeleteReason(model.getDeleteReason());
		retVal.setId(model.getId());
		retVal.setName(model.getName());
		retVal.setPublishedId(model.getPublishedId());
		retVal.setRejectReason(model.getRejectReason());
		retVal.setState(model.getState());
		return retVal;
	}
}
