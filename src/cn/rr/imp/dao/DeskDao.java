package cn.rr.imp.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.rr.dao.IDeskDao;
import cn.rr.entity.Desk;
import cn.rr.jdbc.util.JdbcUtil;

public class DeskDao implements IDeskDao {
	
	/**
	 * 查询所有餐桌
	 */
	@Override
	public List<Desk> findAll() {
		QueryRunner qr = JdbcUtil.getQueryRunner();
		String sql = "select * from desk";
		List<Desk> list = null;
		try {
		 list = qr.query(sql, new BeanListHandler<>(Desk.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}
		return list;
	}

	@Override
	public void add(Desk desk) {
		QueryRunner qr = JdbcUtil.getQueryRunner();
		String sql = "insert into desk(dname,status) values (?,0)";
		try {
			qr.update(sql, desk.getDname());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public void delete(Integer id) {
		QueryRunner qr = JdbcUtil.getQueryRunner();
		String sql = "delete from desk where did=?";
		try {
			qr.update(sql, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public void update(Desk desk) {
		QueryRunner qr = JdbcUtil.getQueryRunner();
		String sql = "update desk set status=?,presettime=now() where did=?";
		String sql0 = "update desk set status=?,presettime=NULL where did=?";
		try {
			if(desk.getStatus()==1){
				qr.update(sql, desk.getStatus(),desk.getDid());
			}else{
				qr.update(sql0, desk.getStatus(),desk.getDid());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}
		
	}
	
	@Override
	public Desk findById(Integer id) {
		QueryRunner qr = JdbcUtil.getQueryRunner();
		String sql = "select * from desk where did=?";
		Desk desk = null;
		try {
			desk = qr.query(sql, new BeanHandler<>(Desk.class),id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}
		return desk;
	}

	//单个准确查找，主要用用判断是否有名称重复
	public Desk findBydname(String dname) {
		QueryRunner qr = JdbcUtil.getQueryRunner();
		String sql = "select * from desk where dname=?";
		Desk desk = null;
		try {
			  desk = qr.query(sql, new BeanHandler<>(Desk.class),dname);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}
		return desk;
	}
	//查找搜索功能
	@Override
	public List<Desk> findByname(String dname) {
		QueryRunner qr = JdbcUtil.getQueryRunner();
		String sql = "select * from desk where dname like?";
		List<Desk> list = null;
		try {
			  list = qr.query(sql, new BeanListHandler<>(Desk.class),"%"+dname+"%");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}
		return list;
	}

}
