package info.jeczmien.dto;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.functors.NotNullPredicate;
import org.apache.commons.collections.list.PredicatedList;

public class OperationResult<T> {
	private final List<Throwable> exceptions;

	private final List<ErrorCode> errorCodes;

	private final List<T> results;

	private OperationStatus status;

	@SuppressWarnings("unchecked")
	public OperationResult() {
		exceptions = PredicatedList.decorate(new ArrayList<Throwable>(), NotNullPredicate.INSTANCE);
		results = new ArrayList<>();
		errorCodes = PredicatedList.decorate(new ArrayList<ErrorCode>(), NotNullPredicate.INSTANCE);
		status = OperationStatus.NOTPROCESSED;
	}

	public OperationStatus getStatus() {
		return status;
	}

	public void setStatus(OperationStatus status) {
		this.status = status;
	}

	public List<Throwable> getExceptions() {
		return exceptions;
	}

	public List<ErrorCode> getErrorCodes() {
		return errorCodes;
	}

	public List<T> getResults() {
		return results;
	}

}
