package cn.rr.imp.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.rr.dao.ICuisineDao;
import cn.rr.entity.Cuisine;
import cn.rr.jdbc.util.JdbcUtil;

public class CuisineDao implements ICuisineDao{

	@Override
	public List<Cuisine> findAll() {
		QueryRunner qr = JdbcUtil.getQueryRunner();
		String sql = "select * from cuisine";
		List<Cuisine> list = null;
		try {
			list = qr.query(sql, new BeanListHandler<>(Cuisine.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}
		return list;
	}

	@Override
	public void add(Cuisine cuisine) {
		QueryRunner qr = JdbcUtil.getQueryRunner();
		String sql = "insert into cuisine(cname)values(?)";
		try {
			qr.update(sql, cuisine.getCname());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public void delete(Integer id) {
		QueryRunner qr = JdbcUtil.getQueryRunner();
		String sql = "delete from cuisine where cid=?";
		try {
			qr.update(sql, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public void update(Cuisine cuisine) {
		QueryRunner qr = JdbcUtil.getQueryRunner();
		String sql = "update cuisine set cname=? where cid=?";
		try {
			qr.update(sql, cuisine.getCname(),cuisine.getCid());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public Cuisine findById(Integer id) {
		QueryRunner qr = JdbcUtil.getQueryRunner();
		String sql = "select * from cuisine where cid=?";
		Cuisine cuisine = null;
		try {
		 cuisine = qr.query(sql, new BeanHandler<>(Cuisine.class),id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}
		
		return cuisine;
	}

	@Override
	public List<Cuisine> findByname(String name) {
		QueryRunner qr = JdbcUtil.getQueryRunner();
		String sql = "select * from cuisine where cname like?";
		List<Cuisine> list = null;
		try {
		 list = qr.query(sql, new BeanListHandler<>(Cuisine.class),"%"+ name +"%");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}
		
		return list;
	}


	
}
