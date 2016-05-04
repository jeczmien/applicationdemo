package info.jeczmien.rest.impl;

import info.jeczmien.dto.SearchApplicationsParam;
import info.jeczmien.model.ApplicationState;
import info.jeczmien.model.ListModel;
import info.jeczmien.rest.model.ListObject;
import info.jeczmien.rest.model.ListQueryObject;

import org.apache.commons.lang.StringUtils;

public class ListObjectConverter {
	private ListObjectConverter() {
		//
	}

	public static SearchApplicationsParam convert(ListQueryObject query) {
		SearchApplicationsParam retVal = new SearchApplicationsParam();
		retVal.setName(query.getNameString());
		if (!StringUtils.isBlank(query.getStateString())) {
			retVal.setState(ApplicationState.valueOf(query.getStateString().trim()));
		}
		return retVal;
	}

	public static ListObject convert(ListModel model) {
		ListObject retVal = new ListObject();
		retVal.setId(model.getId());
		retVal.setLastModification(model.getLastModification());
		retVal.setName(model.getName());
		retVal.setState(model.getState());
		return retVal;
	}
}
