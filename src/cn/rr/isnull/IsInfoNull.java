package cn.rr.isnull;

public class IsInfoNull {
	
	
	public static boolean isInfoNull(String str){
		if(str==null||str.isEmpty()){
			return true;
		}
		
		return false;
	}
	
	public static boolean isInfoNull(Integer id){
		
		if(id==null){
			return true;
		}
		return false;
	}
	
	public static boolean isInfoNull(Double price){
		
		if(price==null){
			return true;
		}
		return false;
	}
}
