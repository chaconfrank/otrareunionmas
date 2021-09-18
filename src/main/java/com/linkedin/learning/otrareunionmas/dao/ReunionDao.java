package com.linkedin.learning.otrareunionmas.dao;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.persistence.Query;

import com.linkedin.learning.otrareunionmas.dominio.Reunion;

public class ReunionDao extends AbstractDao<Reunion> {
	
	public ReunionDao() {
		setClazz(Reunion.class);
	}
	
	public Reunion proxiamReunion() {
		String qlString = "FROM " + Reunion.class.getName() + " WHERE fecha > now() and id > 100 order by fecha";
		Query query = getEntityManager().createQuery(qlString).setMaxResults(1);
		return (Reunion) query.getSingleResult();
	}
	
	public List<Reunion> reunionesManiana(){
		String qlString = "FROM " + Reunion.class.getName() + " WHERE fecha between ?1 and ?2";
		Query query = getEntityManager().createQuery(qlString);
		LocalDate maniana = LocalDate.now().plus(1, ChronoUnit.DAYS);
		query.setParameter(1, maniana.atStartOfDay());
		query.setParameter(2, maniana.plus(1, ChronoUnit.DAYS).atStartOfDay());
		return query.getResultList();
	}
}
