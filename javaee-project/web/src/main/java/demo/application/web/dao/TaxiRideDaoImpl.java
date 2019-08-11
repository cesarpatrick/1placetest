package demo.application.web.dao;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import demo.application.web.entities.Passeger;
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
	public List<TaxiRide> findAll2() {		
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();		
		List<TaxiRide> returnList = entityManager.createQuery("Select t from " + TaxiRide.class.getSimpleName() + " t").getResultList();	
		return returnList;
	}
	
	@SuppressWarnings("unchecked")
	public List<TaxiRide> findTaxiRideByFilter(TaxiRide taxiRide) {		
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();		
		
		String query = "Select t from "+ TaxiRide.class.getSimpleName() + " t ";
		
		if(taxiRide.getPasseger() != null && taxiRide.getPasseger().size() > 0) {
			
			query = query + "  inner join t.passeger passeger where 0 = 0  and  passeger.id in ( ";
			
			int size = taxiRide.getPasseger().size();
			int count = 1;
			
			for (Passeger p : taxiRide.getPasseger()) {
				if(size > count) {
					count++;
					query = query +  " " + p.getId() +" , ";
				}else {
					query = query + " " + p.getId();
				}
			}
			
			query = query + " )";
			
		}else {
			query = query +  "where 0 = 0 ";
		}
				
		
		if(taxiRide.getDriver() != null) {
			query = query + " and t.driver.id = " + taxiRide.getDriver().getId();
		}
		
		if(taxiRide.getDate() != null) {			
			LocalDateTime ldt = taxiRide.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			query = query + "and  t.date = '" + DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH).format(ldt) + "' ";
		}
		
		List<TaxiRide> returnList = entityManager.createQuery(query).getResultList();	
		return returnList;
	}
	
}
