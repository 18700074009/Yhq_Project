package cn.rr.servlet;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.rr.entity.Cuisine;
import cn.rr.entity.Food;
import cn.rr.jdbc.util.Constant;
import cn.rr.jdbc.util.JsonBean;
import cn.rr.jdbc.util.JsonManager;
import cn.rr.service.FoodService;

/**
 * Servlet implementation class FoodServlet
 */
public class FoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private FoodService fs = new FoodService();
       
	private JsonBean bean = new JsonBean();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FoodServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");
		String method = request.getParameter("method");
		switch (method) {
		case "list":
			list(request,response);
			break;
		case "del":
			del(request,response);
			break;
		case "findByfname":
			findByfname(request,response);
			break;
		case "upload"://add ��ӣ��ϴ�
			upload(request,response);
			break;
		case "findByfid":
			findByfid(request,response);
			break;
		case "updatefood"://���أ��޸��ϴ�
			updatefood(request,response);
			break;
		default:{
			bean.setCode(Constant.ERROR_CONSTANT);
			bean.setMessage("���������ڣ�");
			break;
		}
			
		}
		
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

	
	
	private void list(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<Food> list = fs.list();
			bean.setCode(Constant.CORRECT_CONSTANT);
			bean.setMessage(list);
		} catch (Exception e) {
			// TODO: handle exception
			bean.setCode(Constant.ERROR_CONSTANT);
			bean.setMessage(e.getMessage());
		}
		JsonManager.data2Json(bean, response);
		
	}
	
	
	private void del(HttpServletRequest request, HttpServletResponse response) {
		String fid = request.getParameter("fid");
		try {
			fs.del(Integer.parseInt(fid));
			bean.setCode(Constant.CORRECT_CONSTANT);
			bean.setMessage("ɾ���ɹ�");
		} catch (Exception e) {
			// TODO: handle exception
			bean.setCode(Constant.ERROR_CONSTANT);
			bean.setMessage(e.getMessage());
		}
		JsonManager.data2Json(bean, response);
	}
	
	private void findByfname(HttpServletRequest request, HttpServletResponse response) {
		String fname = request.getParameter("fname");
		try {
			List<Food> list = fs.findByfname(fname);
			bean.setCode(Constant.CORRECT_CONSTANT);
			bean.setMessage(list);
		} catch (Exception e) {
			// TODO: handle exception
			bean.setCode(Constant.ERROR_CONSTANT);
			bean.setMessage(e.getMessage());
		}
		JsonManager.data2Json(bean, response);
	}
	
	/**
	 * �����ݽ��з��ദ���װ�������food����
	 * @param request
	 * @param response
	 */
	private void upload(HttpServletRequest request, HttpServletResponse response){
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("utf-8");
		Food food = null;
		if(ServletFileUpload.isMultipartContent(request)){
			try {
				List<FileItem> items = (List<FileItem>)upload.parseRequest(request);
				food = new Food();
				for (FileItem fileItem : items) {
					if(fileItem.isFormField()){
						//System.out.println(fileItem.getFieldName() + ":" + fileItem.getString("utf-8"));
						String foods = fileItem.getFieldName();
						String value = fileItem.getString("utf-8");
						switch (foods) {
						case "cid":{
							Cuisine cuisine = new Cuisine();
							cuisine.setCid(Integer.parseInt(value));
							food.setCid(cuisine);
							break;
						}
						case "fname":{
							//�����ж��Ƿ������ظ�
							Food foodss = fs.findByFname(value);
							if(foodss!=null){
								errorInfo("��Ʒ�����ظ�������������",response);
								return;
							}
							food.setFname(value);
							break;
						}
						case "price":
							food.setPrice(Double.parseDouble(value));
							break;
						case "vipprice":
							food.setVipprice(Double.parseDouble(value));
							break;
						case "intro":
							food.setIntro(value);
							break;
						default:{
							errorInfo("�����ڸ���Ʒ��Ϣ���������������", response);
							return;
						}
						}
					}else{
						String path = "E:/java/mytest/My9_4_HotelSys_Ajax/WebContent/detail/style/images/";
						File file = new File(path);
						if(!file.exists()){
							file.mkdirs();
						}
						String photoname = fileItem.getName();
						Date date = new Date();
						long time = date.getTime();
						String filename = photoname.substring(0, photoname.lastIndexOf("."));
						String ext = photoname.substring(photoname.lastIndexOf(".")+1);
						//�ж��ļ���ʽ�Ƿ���ͼƬ��ʽ
						if(!ext.equalsIgnoreCase("jpg")||!ext.equalsIgnoreCase("png")||!ext.equalsIgnoreCase("gif")||!ext.equalsIgnoreCase("jpeg")){
							errorInfo("ͼƬ��ʽ����ȷ����ѡ����ȷ��ͼƬ",response);
							return;
						}
						photoname = filename + "_" + time + "." + ext;
						String photourl = path+photoname;
						File files = new File(photourl);
						if(!files.exists()){
							files.createNewFile();
						}
						//�����ļ�
						fileItem.write(files);
						food.setPhotourl("/My9_4_HotelSys_Ajax/detail/style/images/"+photoname);
						//ɾ����ʱ�ļ�
						fileItem.delete();
					}
					
				}
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException(e.getMessage());
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException(e.getMessage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new RuntimeException(e.getMessage());
			}
			
		}
		add(food,response);
	}
	
	/**
	 * ��������ӵ����ݿ�
	 * @param food
	 * @param response
	 */
	private void add(Food food,HttpServletResponse response) {
		try {
			fs.add(food);
			bean.setCode(Constant.CORRECT_CONSTANT);
			bean.setMessage("��ӳɹ�");
		} catch (Exception e) {
			// TODO: handle exception
			bean.setCode(Constant.ERROR_CONSTANT);
			bean.setMessage(e.getMessage());
		}
		JsonManager.data2Json(bean, response);
	}
	
	private void findByfid(HttpServletRequest request, HttpServletResponse response) {
		String fid = request.getParameter("fid");
		try {
			Food food = fs.findByid(Integer.parseInt(fid));
			bean.setCode(Constant.CORRECT_CONSTANT);
			bean.setMessage(food);
		} catch (Exception e) {
			// TODO: handle exception
			bean.setCode(Constant.ERROR_CONSTANT);
			bean.setMessage(e.getMessage());
		}
		JsonManager.data2Json(bean, response);
	}
	/**
	 * �����޸ĺ����Ϣ�����װ
	 * @param request
	 * @param response
	 */
	
	private void updatefood(HttpServletRequest request, HttpServletResponse response){
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("utf-8");
		Food food = null;
		if(ServletFileUpload.isMultipartContent(request)){
			try {
				List<FileItem> items = (List<FileItem>)upload.parseRequest(request);
				food = new Food();
				food.setFid(Integer.parseInt(request.getParameter("fid")));
				for (FileItem fileItem : items) {
					if(fileItem.isFormField()){
						//System.out.println(fileItem.getFieldName() + ":" + fileItem.getString("utf-8"));
						String foods = fileItem.getFieldName();
						String value = fileItem.getString("utf-8");
						switch (foods) {
						case "cid":{
							Cuisine cuisine = new Cuisine();
							cuisine.setCid(Integer.parseInt(value));
							food.setCid(cuisine);
							break;
						}
						case "fname":{
							//�����ж��Ƿ������ظ�
							Food foodss = fs.findByFname(value);
							if(foodss!=null){
								errorInfo("��Ʒ�����ظ�������������",response);
								return;
							}
							food.setFname(value);
							break;
						}
						case "price":
							food.setPrice(Double.parseDouble(value));
							break;
						case "vipprice":
							food.setVipprice(Double.parseDouble(value));
							break;
						case "intro":
							food.setIntro(value);
							break;
						default:{
							errorInfo("�����ڸ���Ʒ��Ϣ���������������",response);
							return;
						}
						}
					}else{
						String path = "E:/java/mytest/My9_4_HotelSys_Ajax/WebContent/detail/style/images/";
						File file = new File(path);
						if(!file.exists()){
							file.mkdirs();
						}
						String photoname = fileItem.getName();
						Date date = new Date();
						long time = date.getTime();
						String filename = photoname.substring(0, photoname.lastIndexOf("."));
						String ext = photoname.substring(photoname.lastIndexOf(".")+1);
						photoname = filename + "_" + time + "." + ext;
						String photourl = path+photoname;
						File files = new File(photourl);
						if(!files.exists()){
							files.createNewFile();
						}
						//�����ļ�
						fileItem.write(files);
						food.setPhotourl("/My9_4_HotelSys_Ajax/detail/style/images/"+photoname);
						//ɾ����ʱ�ļ�
						fileItem.delete();
						
					}
				}
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException(e.getMessage());
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException(e.getMessage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new RuntimeException(e.getMessage());
			}
			
		}
		update(food,response);
	}
	/**
	 * ����food�޸Ĳ���
	 * @param food
	 * @param response
	 */
	private void update(Food food, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			fs.update(food);
			bean.setCode(Constant.CORRECT_CONSTANT);
			bean.setMessage("�޸ĳɹ�");
		} catch (Exception e) {
			// TODO: handle exception
			bean.setCode(Constant.ERROR_CONSTANT);
			bean.setMessage(e.getMessage());
		}
		JsonManager.data2Json(bean, response);
		
	}
	
	/**
	 * ���ݻ�ĳЩ���ݲ����������߼�ʱ����errorInfo
	 * @param message
	 * @param response
	 */
	public void errorInfo(String message,HttpServletResponse response){
		bean.setCode(Constant.ERROR_CONSTANT);
		bean.setMessage(message);
		JsonManager.data2Json(bean, response);
	}
	
}
