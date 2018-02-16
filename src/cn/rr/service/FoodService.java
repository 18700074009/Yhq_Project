package cn.rr.service;

import java.util.List;

import cn.rr.entity.Food;
import cn.rr.imp.dao.FoodDao;
import cn.rr.isnull.IsInfoNull;
import cn.rr.myexception.NullInfoException;

public class FoodService {
	private FoodDao foodDao = new FoodDao();
	
	
	
	
	public List<Food> list(){
		List<Food> list = null;
		try {
			list = foodDao.findAll();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
			// TODO: handle exception
		}
		
		
		return list;
		
	}
	
	public void del(Integer fid){
		if(IsInfoNull.isInfoNull(fid)){
			throw new NullInfoException("菜品ID不能为空");
		}
		try {
			foodDao.delete(fid);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public List<Food> findFoodByCid(Integer cid){
		if(IsInfoNull.isInfoNull(cid)){
			throw new NullInfoException("菜品ID不能为空");
		}
		List<Food> list = null;
		try {
			list = foodDao.findByCid(cid);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e.getMessage());
		}
		
		return list;
	}
	
	public List<Food> findByfname(String fname){
		if(IsInfoNull.isInfoNull(fname)){
			throw new NullInfoException("菜品名称不能为空");
		}
		List<Food> list = null;
		try {
			list = foodDao.findByname(fname);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e.getMessage());
		}
		return list;
	}
	
	public void add(Food food){
		if(food == null || IsInfoNull.isInfoNull(food.getFname()) || IsInfoNull.isInfoNull(food.getPrice())){
			throw new NullInfoException("名称或价钱不能为空，food is null");
		}
		try {
			foodDao.add(food);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e.getMessage());
		}
		
	}
	
	public Food findByid(Integer fid){
		if(IsInfoNull.isInfoNull(fid)){
			throw new NullInfoException("菜品ID不能为空");
		}
		Food food = null;
		try {
		 food = foodDao.findById(fid);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e.getMessage());
		}
		return food;
		
	}
	
	public Food findByFname(String fname){
		if(IsInfoNull.isInfoNull(fname)){
			throw new NullInfoException("菜品名称不能为空");
		}
		Food food = null;
		try {
		 food = foodDao.findByfname(fname);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e.getMessage());
		}
		return food;
		
	}
	
	public void update(Food food){
		if(food == null||IsInfoNull.isInfoNull(food.getFid())){
			throw new NullInfoException("菜品ID不能为空");
		}
		try {
			foodDao.update(food);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e.getMessage());
		}
	}
}
