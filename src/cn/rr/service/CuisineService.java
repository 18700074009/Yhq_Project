package cn.rr.service;

import java.util.List;

import cn.rr.entity.Cuisine;
import cn.rr.entity.Food;
import cn.rr.imp.dao.CuisineDao;
import cn.rr.isnull.IsInfoNull;
import cn.rr.myexception.NullInfoException;

public class CuisineService {
	
	private CuisineDao cuisineDao = new CuisineDao();
	public List<Cuisine> listCuisine(){
		List<Cuisine> list = null;
		try {
			//可能查询失败
			 list = cuisineDao.findAll();
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e.getMessage());
		}
		return list;
		
	}
	
	public void add(Cuisine cuisine){
		if(cuisine==null||IsInfoNull.isInfoNull(cuisine.getCname())){
			throw new NullInfoException("未将对象引用至对象实例，cuisine为空");
		}
		try {
			cuisineDao.add(cuisine);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public void del(Integer cid){
		if(cid==null||IsInfoNull.isInfoNull(cid)){
			throw new NullInfoException("菜系ID不能为空");
		}
		FoodService fs = new FoodService();
		 
		List<Food> list = fs.findFoodByCid(cid);
		if(list.size()!=0){
			//判断该菜系是否有对应的菜品，如果有则不能删除并给出提示
			throw new RuntimeException("对不起，不能删除该菜系。如需删除，请先修改或删除该菜系下的菜品！");
		}
		try {
			cuisineDao.delete(cid);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public Cuisine findByCid(Integer cid){
		if(cid==null||IsInfoNull.isInfoNull(cid)){
			throw new NullInfoException("菜系ID不能为空");
		}
		Cuisine cuisine = null;
		try {
		 cuisine = cuisineDao.findById(cid);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e.getMessage());
		}
		return cuisine;
		
	}
	
	public void update(Cuisine cuisine){
		if(cuisine==null||IsInfoNull.isInfoNull(cuisine.getCid())){
			throw new NullInfoException("菜系ID不能为空");
		}
		try {
		 cuisineDao.update(cuisine);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e.getMessage());
		}
		
	}
	
	public List<Cuisine> findByCname(String cname){
		if(IsInfoNull.isInfoNull(cname)){
			throw new NullInfoException("名称不能为空");
		}
		List<Cuisine> list = null;
		try {
			 list = cuisineDao.findByname(cname);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e.getMessage());
		}
		return list;
	}
}
