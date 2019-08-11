package demo.application.web.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import demo.application.web.entities.Driver;
import demo.application.web.util.JPAUtil;

public class DriverDaoImpl implements DriverDao {
	
	@Transactional(value = TxType.REQUIRES_NEW)
	public Driver save(Driver driver) {	
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();		
		driver = entityManager.merge(driver);		
		entityTransaction.commit();		
		return driver;
	}
	
	public Driver findDriverById(Integer id) {		
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		Driver driverReturn = entityManager.find(Driver.class, id);
		return driverReturn;
	}
	
	@SuppressWarnings("unchecked")
	public List<Driver> findAll() {		
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();		
		List<Driver> returnList = entityManager.createQuery("Select d from " + Driver.class.getSimpleName() + " d").getResultList();	
		return returnList;
	}
	
	
	
}
