package cn.rr.jdbc.util;


import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class DateJson implements JsonValueProcessor{
	
	private SimpleDateFormat sdf = null;
	public DateJson(String sdf){
		this.sdf = new SimpleDateFormat(sdf);
	}
	@Override
	public Object processArrayValue(Object value, JsonConfig arg1) {
		
		
		return sdf.format((Date)value);
	}

	@Override
	public Object processObjectValue(String key, Object value, JsonConfig arg2) {
		if(value!=null){
			return sdf.format((Date)value);
		}else{
			return "";
		}
	}

}
