package info.jeczmien.services;

import info.jeczmien.model.ApplicationModel;

public interface PersistentService {
	Long save(ApplicationModel model, boolean delete);
}
