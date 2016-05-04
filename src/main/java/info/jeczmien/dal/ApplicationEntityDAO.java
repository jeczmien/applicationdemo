package info.jeczmien.dal;

import info.jeczmien.entity.ApplicationEntity;

import java.util.List;

import org.apache.tapestry5.jpa.annotations.CommitAfter;

public interface ApplicationEntityDAO {
	@CommitAfter
	ApplicationEntity merge(ApplicationEntity entity, boolean delete);

	int count(ApplicationEntitySearch parameters);

	List<ApplicationEntity> find(ApplicationEntitySearch parameters);
}
