package cn.rr.jdbc.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class JsonManager {
	
	
	public static void date2Json(JsonBean bean,HttpServletResponse response){
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(Date.class, new DateJson("yyyy-MM-dd HH:mm:ss"));
		JSONObject object = JSONObject.fromObject(bean,config);
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(object.toString());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void data2Json(JsonBean bean,HttpServletResponse response){
		JSONObject object = JSONObject.fromObject(bean);
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(object.toString());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
