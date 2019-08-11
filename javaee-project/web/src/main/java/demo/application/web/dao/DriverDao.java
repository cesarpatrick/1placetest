package demo.application.web.dao;

import java.util.List;

import demo.application.web.entities.Driver;

public interface DriverDao {
	public Driver save(Driver driver);
	public Driver findDriverById(Integer id);
	public List<Driver> findAll();
}
