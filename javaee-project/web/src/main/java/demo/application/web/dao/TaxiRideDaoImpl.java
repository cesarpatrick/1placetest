package demo.application.web.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import demo.application.web.entities.TaxiRide;
import demo.application.web.util.JPAUtil;

public class TaxiRideDaoImpl implements TaxiRideDao {
	
	public TaxiRide save(TaxiRide taxiRide) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();		
		taxiRide = entityManager.merge(taxiRide);
		entityTransaction.commit();		
		return taxiRide;
	}
	
	public TaxiRide findTaxiRideById(Integer id) {		
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();		
		TaxiRide taxiRideReturn = entityManager.find(TaxiRide.class, id);		
		return taxiRideReturn;
	}
	
	@SuppressWarnings("unchecked")
	public List<TaxiRide> findAll() {		
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();		
		List<TaxiRide> returnList = entityManager.createQuery("Select t from " + TaxiRide.class.getSimpleName() + " t").getResultList();	
		return returnList;
	}
	
}
