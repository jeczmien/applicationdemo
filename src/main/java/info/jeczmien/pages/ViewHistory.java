/**
 * 
 */
package info.jeczmien.pages;

import info.jeczmien.model.ApplicationModel;
import info.jeczmien.model.HistoryModel;
import info.jeczmien.services.SearchService;

import java.util.List;

import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 * @author jeczmien
 *
 */
public class ViewHistory {

	@Property(write = false)
	@Persist
	private ApplicationModel application;

	@Property(write = false)
	@Persist
	private List<HistoryModel> history;

	@Inject
	private SearchService searchService;

	@InjectPage
	private Index index;

	public void setApplication(ApplicationModel application) {
		this.application = application;
		history = searchService.getHistory(application.getId());
	}

	Object onCanceled() {
		return index;
	}

}