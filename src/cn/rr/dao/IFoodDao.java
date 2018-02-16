package cn.rr.dao;

import java.util.List;

import cn.rr.Idao.IPublicDao;
import cn.rr.entity.Food;

public interface IFoodDao extends IPublicDao<Food>{
	
	public List<Food> findByCid(Integer cid);
}
