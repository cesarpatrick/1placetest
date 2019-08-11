package demo.application.web.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import demo.application.web.entities.Passeger;
import demo.application.web.util.JPAUtil;

public class PassegerDaoImpl implements PassegerDao {
	
	public Passeger save(Passeger passeger) {	
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();		
		entityManager.merge(passeger);		
		entityTransaction.commit();	
		return passeger;
	}
	
	public Passeger findPassegerById(Integer id) {		
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();	
		Passeger passegerReturn = entityManager.find(Passeger.class, id);		
		return passegerReturn;
	}
	
	@SuppressWarnings("unchecked")
	public List<Passeger> findAll() {		
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();		
		List<Passeger> returnList = entityManager.createQuery("Select p from " + Passeger.class.getSimpleName() + " p").getResultList();	
		return returnList;
	}
	
	
	
}
