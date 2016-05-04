package info.jeczmien.pages;

import info.jeczmien.bean.ApplicationListDataSource;
import info.jeczmien.model.ApplicationModel;
import info.jeczmien.model.FilterModel;
import info.jeczmien.model.ListModel;
import info.jeczmien.services.SearchService;

import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.EventContext;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.services.HttpError;

/**
 * Start page of application application.
 */
public class Index {
	@Property
	@Inject
	@Symbol(SymbolConstants.TAPESTRY_VERSION)
	private String tapestryVersion;

	@Property(write = false)
	@Persist
	private ApplicationListDataSource applicationList;

	@Property(write = false)
	@Persist
	private FilterModel filter;

	@Property
	private ListModel row;

	@InjectPage
	private EditApplication editPage;

	@InjectPage
	private ViewHistory viewPage;

	@Inject
	private SearchService searchService;

	Object onActivate(EventContext eventContext) {
		if (applicationList == null) {
			filter = new FilterModel();
			applicationList = new ApplicationListDataSource(filter, searchService);
		}
		return eventContext.getCount() > 0 ? new HttpError(404, "Resource not found") : null;
	}

	@OnEvent(component = "add", value = EventConstants.ACTION)
	Object onAdd() {
		editPage.setApplication(new ApplicationModel());
		return editPage;
	}

	@OnEvent(component = "edit", value = EventConstants.ACTION)
	Object onEdit(EventContext context) {
		ApplicationModel model = searchService.getApplication(context.get(Long.class, 0));
		editPage.setApplication(model);
		return editPage;
	}

	@OnEvent(component = "view", value = EventConstants.ACTION)
	Object onView(EventContext context) {
		ApplicationModel model = searchService.getApplication(context.get(Long.class, 0));
		viewPage.setApplication(model);
		return viewPage;
	}
}
