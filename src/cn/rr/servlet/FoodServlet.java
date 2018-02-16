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
		case "upload"://add 添加，上传
			upload(request,response);
			break;
		case "findByfid":
			findByfid(request,response);
			break;
		case "updatefood"://下载，修改上传
			updatefood(request,response);
			break;
		default:{
			bean.setCode(Constant.ERROR_CONSTANT);
			bean.setMessage("方法不存在！");
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
			bean.setMessage("删除成功");
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
	 * 对数据进行分类处理封装，打包成food对象
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
							//进行判断是否名称重复
							Food foodss = fs.findByFname(value);
							if(foodss!=null){
								errorInfo("菜品名称重复，请重新输入",response);
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
							errorInfo("不存在该商品信息，请检查后重新输入", response);
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
						//判断文件格式是否是图片格式
						if(!ext.equalsIgnoreCase("jpg")||!ext.equalsIgnoreCase("png")||!ext.equalsIgnoreCase("gif")||!ext.equalsIgnoreCase("jpeg")){
							errorInfo("图片格式不正确，请选择正确的图片",response);
							return;
						}
						photoname = filename + "_" + time + "." + ext;
						String photourl = path+photoname;
						File files = new File(photourl);
						if(!files.exists()){
							files.createNewFile();
						}
						//保存文件
						fileItem.write(files);
						food.setPhotourl("/My9_4_HotelSys_Ajax/detail/style/images/"+photoname);
						//删除临时文件
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
	 * 把数据添加到数据库
	 * @param food
	 * @param response
	 */
	private void add(Food food,HttpServletResponse response) {
		try {
			fs.add(food);
			bean.setCode(Constant.CORRECT_CONSTANT);
			bean.setMessage("添加成功");
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
	 * 处理修改后的信息处理包装
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
							//进行判断是否名称重复
							Food foodss = fs.findByFname(value);
							if(foodss!=null){
								errorInfo("菜品名称重复，请重新输入",response);
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
							errorInfo("不存在该商品信息，请检查后重新输入",response);
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
						//保存文件
						fileItem.write(files);
						food.setPhotourl("/My9_4_HotelSys_Ajax/detail/style/images/"+photoname);
						//删除临时文件
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
	 * 进行food修改操作
	 * @param food
	 * @param response
	 */
	private void update(Food food, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			fs.update(food);
			bean.setCode(Constant.CORRECT_CONSTANT);
			bean.setMessage("修改成功");
		} catch (Exception e) {
			// TODO: handle exception
			bean.setCode(Constant.ERROR_CONSTANT);
			bean.setMessage(e.getMessage());
		}
		JsonManager.data2Json(bean, response);
		
	}
	
	/**
	 * 数据或某些数据不符合正常逻辑时返回errorInfo
	 * @param message
	 * @param response
	 */
	public void errorInfo(String message,HttpServletResponse response){
		bean.setCode(Constant.ERROR_CONSTANT);
		bean.setMessage(message);
		JsonManager.data2Json(bean, response);
	}
	
}
