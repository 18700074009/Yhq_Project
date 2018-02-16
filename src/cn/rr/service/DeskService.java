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
			//���ܲ�ѯʧ��
			 list = deskDao.findAll();
			 
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e.getMessage());
		}
		
		return list;
		
	}
	
	
	public void addDesk(Desk desk){
		
		//����ҵ���߼��Ϸ����жϣ��������Ϊ�����׳��쳣
		if(IsInfoNull.isInfoNull(desk.getDname())){
			throw new NullInfoException("����������Ϊ��");
		}
		//����������Դ��ڣ����׳����쳣����ֹ����
		Desk desk2 = deskDao.findBydname(desk.getDname());
		if(desk2!=null){
			throw new RuntimeException("������������Դ��ڣ�����������");
		}
		//�������������������
		try {
			deskDao.add(desk);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}
		
	}
	
	public void deleteDesk(Integer did){
		if(IsInfoNull.isInfoNull(did)){
			throw new NullInfoException("����idΪ��");
		}
		try {
			deskDao.delete(did);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e.getMessage());
		}
		
	}
	//����ڶ����������޸����ݿ����״̬��Ԥ��ʱ��
	public void updateDesk(Desk desk){
		if(desk==null||IsInfoNull.isInfoNull(desk.getDid())){
			throw new NullInfoException("��������Ϊ��");
		}
		//���֮ǰ��0��ʾ���У��������ĳ�Ԥ��״̬����֮���
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
			throw new NullInfoException("����ID����Ϊ��");
		}
		try {
			Desk desk = deskDao.findById(did);
			//�жϲ����Ƿ���Ԥ��
			if(desk.getStatus()==1){
				throw new RuntimeException("�ò�����Ԥ��������ɾ��������ɾ��������������ɾ��");	
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
			throw new NullInfoException("���ݲ���Ϊ��");
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
