package demo.application.web.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.primefaces.model.DualListModel;

import demo.application.web.dao.DriverDaoImpl;
import demo.application.web.dao.PassegerDaoImpl;
import demo.application.web.dao.TaxiRideDaoImpl;
import demo.application.web.entities.Driver;
import demo.application.web.entities.Passeger;
import demo.application.web.entities.TaxiRide;


@Named("taxiRideBean")
@RequestScoped
public class TaxiRideBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private TaxiRide taxiRide = new TaxiRide();
	private TaxiRide taxiRideFilter = new TaxiRide();
	
	private TaxiRideDaoImpl taxiRideDao = new TaxiRideDaoImpl();
	private DriverDaoImpl driverDao = new DriverDaoImpl();
	private PassegerDaoImpl passegerDao = new PassegerDaoImpl();
	
	private MessegersBean messegersBean = new MessegersBean();
	
	private List<TaxiRide> taxiRides = new LinkedList<TaxiRide>();
	private DualListModel<Passeger> passegers;	
	private List<Driver> drivers = new LinkedList<Driver>();
	
	private String mensagens = "";
	private Integer editId = null;
	
	@PostConstruct
    protected void init() {
        drivers = driverDao.findAll();        
        List<Passeger> passegersList = passegerDao.findAll();
        List<Passeger> passegersTargetList = new ArrayList<Passeger>();
        passegers = new DualListModel<Passeger>(passegersList,passegersTargetList);
    }

	@Transactional(value = TxType.REQUIRES_NEW)
	public void save() {	
		List<Passeger> targetPassegers = passegers.getTarget();
				
		if(targetPassegers == null || targetPassegers.isEmpty() == true) {
			messegersBean.error("The passager is required. !");
		}else {
			taxiRide.setPasseger(targetPassegers);
			taxiRideDao.save(taxiRide);
			taxiRide = new TaxiRide();
			messegersBean.info("Taxi ride sucessful !");
		}
	}
	
	public void search() {		
		taxiRideFilter.setPasseger(passegers.getTarget());
		taxiRides = taxiRideDao.findTaxiRideByFilter(taxiRideFilter);
	}
	
	public void backToList() {
		try {
			taxiRides = taxiRideDao.findTaxiRideByFilter(new TaxiRide());
			FacesContext.getCurrentInstance().getExternalContext().redirect("taxiRideList.xhtml");
		} catch (IOException e) {			
			e.printStackTrace();
		}	
	}
		
	public TaxiRide getTaxiRide() {
		return taxiRide;
	}

	public void setTaxiRide(TaxiRide taxiRide) {
		this.taxiRide = taxiRide;
	}

	public List<TaxiRide> getTaxiRides() {
		return taxiRides;
	}

	public void setTaxiRides(List<TaxiRide> taxiRides) {
		this.taxiRides = taxiRides;
	}

	public String getMensagens() {
		return mensagens;
	}

	public void setMensagens(String mensagens) {
		this.mensagens = mensagens;
	}	

	public Integer getEditId() {
		return editId;
	}

	public void setEditId(Integer editId) {
		this.editId = editId;
	}


	public List<Driver> getDrivers() {
		return drivers;
	}


	public void setDrivers(List<Driver> drivers) {
		this.drivers = drivers;
	}


	public DualListModel<Passeger> getPassegers() {
		return passegers;
	}


	public void setPassegers(DualListModel<Passeger> passegers) {
		this.passegers = passegers;
	}

	public TaxiRide getTaxiRideFilter() {
		return taxiRideFilter;
	}

	public void setTaxiRideFilter(TaxiRide taxiRideFilter) {
		this.taxiRideFilter = taxiRideFilter;
	}
}
