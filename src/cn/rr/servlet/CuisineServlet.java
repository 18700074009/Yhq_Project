package cn.rr.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.rr.entity.Cuisine;
import cn.rr.jdbc.util.Constant;
import cn.rr.jdbc.util.JsonBean;
import cn.rr.jdbc.util.JsonManager;
import cn.rr.service.CuisineService;

/**
 * Servlet implementation class CuisineServlet
 */
public class CuisineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private CuisineService cs = new CuisineService();
	
	private JsonBean bean = new JsonBean();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CuisineServlet() {
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
		case "add":
			add(request,response);
			break;
		case "del":
			del(request,response);
			break;
		case "findByCid":
			findByCid(request,response);
			break;
		case "update":
			update(request,response);
		case "findByCname":
			findByCname(request,response);
		default:
			break;
		}
	}

	

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void list(HttpServletRequest request, HttpServletResponse response){
		List<Cuisine> list = null;
		try {
				list = cs.listCuisine();
				bean.setCode(Constant.CORRECT_CONSTANT);
				bean.setMessage(list);
		} catch (Exception e) {
			// TODO: handle exception
			bean.setCode(Constant.ERROR_CONSTANT);
			bean.setMessage(e.getMessage());
		}
		//将数据传过去进行格式处理并发送给浏览器端
		JsonManager.data2Json(bean, response);
	}
	
	private void add(HttpServletRequest request, HttpServletResponse response){
		String cname = request.getParameter("cname");
		Cuisine cuisine = new Cuisine();
		cuisine.setCname(cname);
		try {
				cs.add(cuisine);
				bean.setCode(Constant.CORRECT_CONSTANT);
				bean.setMessage("添加成功");
		} catch (Exception e) {
			// TODO: handle exception
			bean.setCode(Constant.ERROR_CONSTANT);
			bean.setMessage(e.getMessage());
		}
		JsonManager.data2Json(bean, response);
	}
	
	private void del(HttpServletRequest request, HttpServletResponse response){
		String cid = request.getParameter("cid");
		try {
				cs.del(Integer.parseInt(cid));
				bean.setCode(Constant.CORRECT_CONSTANT);
				bean.setMessage("删除成功");
		} catch (Exception e) {
			// TODO: handle exception
			bean.setCode(Constant.ERROR_CONSTANT);
			bean.setMessage(e.getMessage());
		}
		JsonManager.data2Json(bean, response);
	}
	
	
	
	private void findByCid(HttpServletRequest request, HttpServletResponse response) {
		String cid = request.getParameter("cid");
		try {
			Cuisine cuisine  = cs.findByCid(Integer.parseInt(cid));
			bean.setCode(Constant.CORRECT_CONSTANT);
			bean.setMessage(cuisine);
		} catch (Exception e) {
			// TODO: handle exception
			bean.setCode(Constant.ERROR_CONSTANT);
			bean.setMessage(e.getMessage());
		}
		JsonManager.data2Json(bean, response);
		
	}
	
	
	private void update(HttpServletRequest request, HttpServletResponse response) {
		String cid = request.getParameter("cid");
		String cname = request.getParameter("cname");
		Cuisine cuisine = new Cuisine();
		cuisine.setCid(Integer.parseInt(cid));
		cuisine.setCname(cname);
		try {
			cs.update(cuisine);
			bean.setCode(Constant.CORRECT_CONSTANT);
			bean.setMessage("修改成功");
		} catch (Exception e) {
			// TODO: handle exception
			bean.setCode(Constant.ERROR_CONSTANT);
			bean.setMessage(e.getMessage());
		}
		JsonManager.data2Json(bean, response);
		
	}
	
	
	private void findByCname(HttpServletRequest request, HttpServletResponse response) {
		String cname = request.getParameter("cname");
		try {
			List<Cuisine> list = cs.findByCname(cname);
			bean.setCode(Constant.CORRECT_CONSTANT);
			bean.setMessage(list);
		} catch (Exception e) {
			// TODO: handle exception
			bean.setCode(Constant.ERROR_CONSTANT);
			bean.setMessage(e.getMessage());
		}
		JsonManager.data2Json(bean, response);
	}
}
