package cn.rr.myexception;

public class NullInfoException extends RuntimeException {

	/**
	 * ��ֵΪnull�����п��ַ�����ʱ���׳����쳣
	 */
	private static final long serialVersionUID = 1L;
	public NullInfoException(){
		super();
	}
	public NullInfoException(String message){
		super(message);
	}
}
