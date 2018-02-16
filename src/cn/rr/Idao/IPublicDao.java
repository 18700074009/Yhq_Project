package cn.rr.Idao;

import java.util.List;


public interface IPublicDao<T> {
	public List<T> findAll();
	
	public void add (T t);
	
	public void delete (Integer id);
	
	public void update (T t);
	
	public T findById (Integer id);
	
	public List<T> findByname(String name);
}
