package cn.rr.myexception;

public class NullInfoException extends RuntimeException {

	/**
	 * 传值为null或者有空字符串的时候抛出该异常
	 */
	private static final long serialVersionUID = 1L;
	public NullInfoException(){
		super();
	}
	public NullInfoException(String message){
		super(message);
	}
}
