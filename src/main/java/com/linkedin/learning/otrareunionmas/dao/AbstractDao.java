package com.linkedin.learning.otrareunionmas.dao;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.linkedin.learning.otrareunionmas.util.EntityManagerUtil;

public abstract class AbstractDao<T> implements Dao<T> {
	
	private EntityManager entityManager = EntityManagerUtil.getEntityManager();
	private Class<T> clazz;
	
	@Override
	public Optional<T> get(long id) {
		return Optional.ofNullable(getEntityManager().find(clazz, id));
	}

	@Override
	public List<T> getAll() {
		String qlString = "FROM " + clazz.getName();
		Query query = getEntityManager().createQuery(qlString);
		return query.getResultList();
	}

	@Override
	public void save(T t) {
		executeInsideTransacction(entityManager -> entityManager.persist(t));
	}

	@Override
	public void update(T t) {
		executeInsideTransacction(entityManager -> entityManager.merge(t));
		
	}

	@Override
	public void delete(T t) {
		executeInsideTransacction(entityManager -> entityManager.remove(t));
	}
	
	public void setClazz(Class<T> clazz) {
		this.clazz = clazz;
	}
	
	private void executeInsideTransacction(Consumer<EntityManager> action) {
		EntityTransaction tx = getEntityManager().getTransaction();
		try {
			tx.begin();
			action.accept(getEntityManager());
			tx.commit();
		}catch(RuntimeException ex) {
			tx.rollback();
			throw ex;
		}
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

}
