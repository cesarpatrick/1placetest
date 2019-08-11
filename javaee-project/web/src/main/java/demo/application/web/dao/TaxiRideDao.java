package demo.application.web.dao;

import java.util.List;

import demo.application.web.entities.TaxiRide;

public interface TaxiRideDao {
	public TaxiRide save(TaxiRide taxiDrive);
	public TaxiRide findTaxiRideById(Integer id);
	public List<TaxiRide> findAll();
}
