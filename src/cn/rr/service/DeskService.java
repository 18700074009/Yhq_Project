package cn.rr.service;

import java.util.List;

import cn.rr.entity.Desk;
import cn.rr.imp.dao.DeskDao;
import cn.rr.isnull.IsInfoNull;
import cn.rr.myexception.NullInfoException;

public class DeskService {
	private DeskDao deskDao = new DeskDao();
	public List<Desk> listDesk(){
		List<Desk> list = null;
		try {
			//可能查询失败
			 list = deskDao.findAll();
			 
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e.getMessage());
		}
		
		return list;
		
	}
	
	
	public void addDesk(Desk desk){
		
		//进行业务逻辑合法性判断，如果桌名为空则抛出异常
		if(IsInfoNull.isInfoNull(desk.getDname())){
			throw new NullInfoException("餐桌名不能为空");
		}
		//如果该名称以存在，则抛出该异常。防止重名
		Desk desk2 = deskDao.findBydname(desk.getDname());
		if(desk2!=null){
			throw new RuntimeException("您输入的名称以存在，请重新输入");
		}
		//如果符合则进行数据添加
		try {
			deskDao.add(desk);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}
		
	}
	
	public void deleteDesk(Integer did){
		if(IsInfoNull.isInfoNull(did)){
			throw new NullInfoException("餐桌id为空");
		}
		try {
			deskDao.delete(did);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e.getMessage());
		}
		
	}
	//针对于订桌退桌，修改数据库餐桌状态与预定时间
	public void updateDesk(Desk desk){
		if(desk==null||IsInfoNull.isInfoNull(desk.getDid())){
			throw new NullInfoException("餐桌不能为空");
		}
		//如果之前是0表示空闲，点击后更改成预定状态，反之如此
		if(desk.getStatus()==0){
			desk.setStatus(1);
		}else{
			desk.setStatus(0);
		}
		try {
			deskDao.update(desk);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public void del(Integer did){
		if(IsInfoNull.isInfoNull(did)){
			throw new NullInfoException("餐桌ID不能为空");
		}
		try {
			Desk desk = deskDao.findById(did);
			//判断餐桌是否以预定
			if(desk.getStatus()==1){
				throw new RuntimeException("该餐桌以预定，不能删除！如需删除，请退桌后在删除");	
			}else{
				deskDao.delete(did);
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e.getMessage());
		}
	}
	
	
	public List<Desk>findBydname(String dname){
		if(IsInfoNull.isInfoNull(dname)){
			throw new NullInfoException("内容不能为空");
		}
		List<Desk> list = null;
		try {
			 list = deskDao.findByname(dname);
		
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e.getMessage());
		}
		return list;
		
	}
}
