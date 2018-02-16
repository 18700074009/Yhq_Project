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
			//���ܲ�ѯʧ��
			 list = cuisineDao.findAll();
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e.getMessage());
		}
		return list;
		
	}
	
	public void add(Cuisine cuisine){
		if(cuisine==null||IsInfoNull.isInfoNull(cuisine.getCname())){
			throw new NullInfoException("δ����������������ʵ����cuisineΪ��");
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
			throw new NullInfoException("��ϵID����Ϊ��");
		}
		FoodService fs = new FoodService();
		 
		List<Food> list = fs.findFoodByCid(cid);
		if(list.size()!=0){
			//�жϸò�ϵ�Ƿ��ж�Ӧ�Ĳ�Ʒ�����������ɾ����������ʾ
			throw new RuntimeException("�Բ��𣬲���ɾ���ò�ϵ������ɾ���������޸Ļ�ɾ���ò�ϵ�µĲ�Ʒ��");
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
			throw new NullInfoException("��ϵID����Ϊ��");
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
			throw new NullInfoException("��ϵID����Ϊ��");
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
			throw new NullInfoException("���Ʋ���Ϊ��");
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
