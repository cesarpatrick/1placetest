package demo.application.web.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import demo.application.web.dao.DriverDaoImpl;
import demo.application.web.entities.Driver;


@Named("driverBean")
@SessionScoped
public class DriverBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private Driver driver = new Driver();
	private DriverDaoImpl dao = new DriverDaoImpl();
	private MessegersBean messegersBean = new MessegersBean();
	private List<Driver> drivers = new LinkedList<Driver>();
	
	private String mensagens = "";
	private Integer editId = null;

	
	public void save() {	
		
		if(driver.getName() == null || driver.getName().isEmpty()) {
			messegersBean.error("Name is required. !");
		}else if(driver.getLicenceNo() == null || driver.getLicenceNo().isEmpty()){
			messegersBean.error("Licence Number is required. !");
		}else {
			dao.save(driver);	
			driver = new Driver();
			messegersBean.info("The driver was saved !");		
		}

	}
	
	public void search() {
		drivers = dao.findAll();
	}
	
	public void backToList() {
		try {
			drivers = dao.findAll();
			FacesContext.getCurrentInstance().getExternalContext().redirect("driverList.xhtml");
		} catch (IOException e) {			
			e.printStackTrace();
		}	
	}
	
	public void edit() {		
		try {
			driver = dao.findDriverById(editId);			
			FacesContext.getCurrentInstance().getExternalContext().redirect("driverForm.xhtml");
		} catch (IOException e) {			
			e.printStackTrace();
		}		
	}
		
	public Driver getDriver() {
		return driver;
	}
	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	
	public String getMensagens() {
		return mensagens;
	}

	public void setMensagens(String mensagens) {
		this.mensagens = mensagens;
	}

	public List<Driver> getDrivers() {
		return drivers;
	}

	public void setDrivers(List<Driver> drivers) {
		this.drivers = drivers;
	}

	public Integer getEditId() {
		return editId;
	}

	public void setEditId(Integer editId) {
		this.editId = editId;
	}
	
}
