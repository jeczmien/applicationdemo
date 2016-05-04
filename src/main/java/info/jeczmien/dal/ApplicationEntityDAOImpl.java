package info.jeczmien.dal;

import info.jeczmien.entity.ApplicationEntity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.apache.tapestry5.ioc.annotations.Inject;

public class ApplicationEntityDAOImpl implements ApplicationEntityDAO {

	private static final String ID_PARAM = "id";
	private static final String NAME_PARAM = "name";
	private static final String STATE_PARAM = "state";
	private static final String DATE_PARAM = "modificationDate";

	@Inject
	@PersistenceContext(unitName = "default")
	private EntityManager entityManager;

	@Override
	public ApplicationEntity merge(ApplicationEntity entity, boolean delete) {
		Validate.notNull(entity);
		ApplicationEntity retVal = entityManager.merge(entity);
		if (delete) {
			entityManager.remove(retVal);
			retVal = null;
		}
		return retVal;
	}

	@Override
	public int count(ApplicationEntitySearch parameters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		final CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
		final Root<ApplicationEntity> countRoot = countQuery.from(ApplicationEntity.class);
		countQuery.select(cb.count(countRoot));
		countQuery.where(createAndPredicate(getPredicates(parameters, countRoot, cb), cb));
		return entityManager.createQuery(countQuery).getSingleResult().intValue();
	}

	@Override
	public List<ApplicationEntity> find(ApplicationEntitySearch parameters) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<ApplicationEntity> resultQuery = cb.createQuery(ApplicationEntity.class);
		Root<ApplicationEntity> all = resultQuery.from(ApplicationEntity.class);
		Predicate criteria = createAndPredicate(getPredicates(parameters, all, cb), cb);
		resultQuery.where(criteria).select(all);
		resultQuery.orderBy(getSort(parameters.getSortCriteria(), all, cb));
		TypedQuery<ApplicationEntity> lq = entityManager.createQuery(resultQuery);
		return lq.getResultList();
	}

	private List<Predicate> getPredicates(ApplicationEntitySearch parameters, Root<ApplicationEntity> all,
			CriteriaBuilder cb) {
		List<Predicate> retVal = new ArrayList<>();
		if (parameters.getId() != null) {
			retVal.add(cb.equal(all.get(ID_PARAM), parameters.getId()));
		}
		if (StringUtils.isNotBlank(parameters.getName())) {
			retVal.add(cb.equal(all.get(NAME_PARAM), parameters.getName()));
		}
		if (parameters.getState() != null) {
			retVal.add(cb.equal(all.get(STATE_PARAM), parameters.getState()));
		}
		if (retVal.isEmpty()) {
			retVal.add(cb.isTrue(cb.literal(true)));
		}
		return retVal;
	}

	private List<Order> getSort(List<SortCriterion> sorting, Root<ApplicationEntity> all, CriteriaBuilder cb) {
		List<Order> retVal = new ArrayList<>();
		for (SortCriterion sc : sorting) {
			switch (sc.getSortOrder()) {
			case ASCENDING:
				retVal.add(cb.asc(all.get(sc.getColumnName())));
				break;
			case DESCENDING:
				retVal.add(cb.desc(all.get(sc.getColumnName())));
				break;
			default:
				// no sort
			}
		}
		if (retVal.isEmpty()) {
			retVal.add(cb.asc(all.get(DATE_PARAM)));
		}
		return retVal;
	}

	private Predicate createAndPredicate(List<Predicate> predicates, CriteriaBuilder cb) {
		return cb.and(predicates.toArray(new Predicate[0]));
	}
}
