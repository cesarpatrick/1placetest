package demo.application.web.beans;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import demo.application.web.dao.PassegerDaoImpl;
import demo.application.web.entities.Passeger;

@Named("passegerBean")
@RequestScoped
public class PassegerBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private Passeger passeger = new Passeger();
	private PassegerDaoImpl dao = new PassegerDaoImpl();
	private MessegersBean messegersBean = new MessegersBean();

	public void save() {
		dao.save(passeger);	
		passeger = new Passeger();
		messegersBean.info("The passeger was saved !");	
	}
	
	public Passeger getPasseger() {
		return passeger;
	}

	public void setPasseger(Passeger passeger) {
		this.passeger = passeger;
	}
	
}
