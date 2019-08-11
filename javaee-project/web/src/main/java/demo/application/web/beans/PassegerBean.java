package demo.application.web.beans;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import demo.application.web.dao.PassegerDaoImpl;
import demo.application.web.entities.Driver;
import demo.application.web.entities.Passeger;

@Named("passegerBean")
@RequestScoped
public class PassegerBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private Passeger passeger = new Passeger();
	private PassegerDaoImpl dao = new PassegerDaoImpl();
	private MessegersBean messegersBean = new MessegersBean();

	@Transactional(value = TxType.REQUIRES_NEW)
	public void save() {
		if(passeger.getFirstName() == null || passeger.getFirstName().isEmpty()) {
			messegersBean.error("First name is required. !");
		}else if(passeger.getLastName() == null || passeger.getLastName().isEmpty()){
			messegersBean.error("Last Name is required. !");
		}else if(passeger.getAge() == null){
			messegersBean.error("Age is required. !");
		}else {
			dao.save(passeger);		
			passeger = new Passeger();
			messegersBean.info("The passeger was saved !");		
		}
	}
	
	public Passeger getPasseger() {
		return passeger;
	}

	public void setPasseger(Passeger passeger) {
		this.passeger = passeger;
	}
	
}
