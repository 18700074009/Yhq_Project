package cn.rr.jdbc.util;


import org.apache.commons.dbutils.QueryRunner;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtil {
	private static ComboPooledDataSource dataSource;
	static{
		try {
			dataSource = new ComboPooledDataSource("/c3p0-config.xml");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static QueryRunner getQueryRunner(){
		QueryRunner qr = new QueryRunner(dataSource);
		return qr;
	}
}
