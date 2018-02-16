package cn.rr.imp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import cn.d6_3.jdbc.util.JdbcUtil3;
import cn.rr.dao.IFoodDao;
import cn.rr.entity.Cuisine;
import cn.rr.entity.Food;
import cn.rr.jdbc.util.JdbcUtil;

public class FoodDao implements IFoodDao {

	@Override
	public List<Food> findAll() {
		Connection conn = JdbcUtil3.getConnection();
		String sql = "select f.*,c.cname from food f,cuisine c where f.cid=c.cid";
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Food> list = new ArrayList<>();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				Food food = new Food();
				food.setFid(rs.getInt("fid"));
				food.setFname(rs.getString("fname"));
				food.setCid(new Cuisine(rs.getInt("cid"),rs.getString("cname")));
				food.setPrice(rs.getDouble("price"));
				food.setVipprice(rs.getDouble("vipprice"));
				list.add(food);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}finally{
			JdbcUtil3.close(conn, ps, rs);
		}
		return list;
	}

	@Override
	public void add(Food food) {
		QueryRunner qr = JdbcUtil.getQueryRunner();
		String sql = "insert into food (fname,cid,price,vipprice,intro,photourl)values(?,?,?,?,?,?)";
		try {
			Object[] obj = new Object[]{
				food.getFname(),
				food.getCid().getCid(),
				food.getPrice(),
				food.getVipprice(),
				food.getIntro(),
				food.getPhotourl()
			};
			qr.update(sql, obj);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public void delete(Integer id) {
		QueryRunner qr = JdbcUtil.getQueryRunner();
		String sql = "delete from food where fid=?";
		try {
			qr.update(sql, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public void update(Food food) {
		QueryRunner qr = JdbcUtil.getQueryRunner();
		String sql = "update food set fname=?,cid=?,price=?,vipprice=?,intro=?,photourl=? where fid=?";
		Object[] obj = new Object[]{
				food.getFname(),
				food.getCid().getCid(),
				food.getPrice(),
				food.getVipprice(),
				food.getIntro(),
				food.getPhotourl(),
				food.getFid()
			};
		try {
			qr.update(sql, obj);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public Food findById(Integer id) {
		Connection conn = JdbcUtil3.getConnection();
		String sql = "select f.*,c.cname from food f,cuisine c where f.cid=c.cid and f.fid=?";
		PreparedStatement ps = null;
		Food food = new Food();
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()){
				food.setFid(id);
				food.setFname(rs.getString("fname"));
				food.setCid(new Cuisine(rs.getInt("cid"),rs.getString("cname")));
				food.setPrice(rs.getDouble("price"));
				food.setVipprice(rs.getDouble("vipprice"));
				food.setIntro(rs.getString("intro"));
				food.setPhotourl(rs.getString("photourl"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}finally{
			JdbcUtil3.close(conn, ps, rs);
		}
		
		return food;
		
	}

	@Override
	public List<Food> findByname(String name) {
		Connection conn = JdbcUtil3.getConnection();
		String sql = "select f.*,c.cname from food f,cuisine c where f.cid=c.cid and f.fname like?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Food> list = new ArrayList<>();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%"+name+"%");
			rs = ps.executeQuery();
			while(rs.next()){
				Food food = new Food();
				food.setFid(rs.getInt("fid"));
				food.setFname(rs.getString("fname"));
				food.setCid(new Cuisine(rs.getInt("cid"),rs.getString("cname")));
				food.setPrice(rs.getDouble("price"));
				food.setVipprice(rs.getDouble("vipprice"));
				list.add(food);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}finally{
			JdbcUtil3.close(conn, ps, rs);
		}
		
		return list;
	}

	@Override
	public List<Food> findByCid(Integer cid) {
		Connection conn = JdbcUtil3.getConnection();
		String sql = "select f.*,c.cname from food f,cuisine c where f.cid=c.cid and c.cid=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Food> list = new ArrayList<>();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1,cid);
			rs = ps.executeQuery();
			while(rs.next()){
				Food food = new Food();
				food.setFid(rs.getInt("fid"));
				food.setFname(rs.getString("fname"));
				food.setCid(new Cuisine(rs.getInt("cid"),rs.getString("cname")));
				food.setPrice(rs.getDouble("price"));
				food.setVipprice(rs.getDouble("vipprice"));
				list.add(food);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}finally{
			JdbcUtil3.close(conn, ps, rs);
		}
		
		return list;
	}
	
	
	
	/**
	 * 精确查找，只要用于判断业务逻辑，防止菜品名称重复
	 * @param fname
	 * @return
	 */
	public Food findByfname(String fname) {
		Connection conn = JdbcUtil3.getConnection();
		String sql = "select f.*,c.cname from food f,cuisine c where f.cid=c.cid and f.fname=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Food food = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, fname);
			rs = ps.executeQuery();
			while(rs.next()){
				food = new Food();
				food.setFid(rs.getInt("fid"));
				food.setFname(rs.getString("fname"));
				food.setCid(new Cuisine(rs.getInt("cid"),rs.getString("cname")));
				food.setPrice(rs.getDouble("price"));
				food.setVipprice(rs.getDouble("vipprice"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}finally{
			JdbcUtil3.close(conn, ps, rs);
		}
		
		return food;
	}
	
}
