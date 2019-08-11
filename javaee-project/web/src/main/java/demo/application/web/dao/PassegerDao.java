package demo.application.web.dao;

import java.util.List;

import demo.application.web.entities.Passeger;

public interface PassegerDao {
	public Passeger save(Passeger passeger);
	public Passeger findPassegerById(Integer id);
	public List<Passeger> findAll();
}
