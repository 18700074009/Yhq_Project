package cn.rr.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.rr.entity.Desk;
import cn.rr.isnull.IsInfoNull;
import cn.rr.jdbc.util.Constant;
import cn.rr.jdbc.util.JsonBean;
import cn.rr.jdbc.util.JsonManager;
import cn.rr.service.DeskService;

/**
 * Servlet implementation class DeskServlet
 */
public class DeskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private DeskService ds = new DeskService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeskServlet() {
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
		case "update":
			update(request,response);
			break;
		case "del":
			del(request,response);
			break;
		case "find":
			find(request,response);
			break;

		default:{
				JsonBean bean = new JsonBean();
				bean.setCode(Constant.ERROR_CONSTANT);
				bean.setMessage("未找到对应的方法");
				JsonManager.data2Json(bean, response);
				break;
			}
		}
		
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		list(request, response);
	}
	
	private void list(HttpServletRequest request, HttpServletResponse response){
		JsonBean bean = new JsonBean();
		List<Desk> list = null;
		try {
				list = ds.listDesk();
				bean.setCode(Constant.CORRECT_CONSTANT);
				bean.setMessage(list);
		} catch (Exception e) {
			// TODO: handle exception
			bean.setCode(Constant.ERROR_CONSTANT);
			bean.setMessage(e.getMessage());
		}
		//将数据传过去进行格式处理并发送给浏览器端
		JsonManager.date2Json(bean, response);
		
		
	}
	
	public void add(HttpServletRequest request, HttpServletResponse response){
		String dname = request.getParameter("dname");
		Desk desk = new Desk();
		desk.setDname(dname);
		JsonBean bean = new JsonBean();
		try {
			ds.addDesk(desk);
			bean.setCode(Constant.CORRECT_CONSTANT);
		} catch (Exception e) {
			// TODO: handle exception
			bean.setCode(Constant.ERROR_CONSTANT);
			bean.setMessage(e.getMessage());
		}
		JsonManager.data2Json(bean, response);
		
	}
	
	public void update(HttpServletRequest request, HttpServletResponse response){
		String did = request.getParameter("did");
		String status = request.getParameter("status");
		Desk desk = new Desk();
		desk.setDid(Integer.parseInt(did));
		desk.setStatus(Integer.parseInt(status));
		JsonBean bean = new JsonBean();
		try {
			ds.updateDesk(desk);
			bean.setCode(Constant.CORRECT_CONSTANT);
		} catch (Exception e) {
			// TODO: handle exception
			bean.setCode(Constant.ERROR_CONSTANT);
			bean.setMessage(e.getMessage());
		}
		JsonManager.data2Json(bean, response);
	}
	
	public void del(HttpServletRequest request, HttpServletResponse response){
		
		String did = request.getParameter("did");
		JsonBean bean = new JsonBean();
		if(IsInfoNull.isInfoNull(did)){
			bean.setCode(Constant.ERROR_CONSTANT);
			bean.setMessage("id错误");
			JsonManager.data2Json(bean, response);
			return;
		}
		try {
			ds.del(Integer.parseInt(did));
			bean.setCode(Constant.CORRECT_CONSTANT);
		} catch (Exception e) {
			// TODO: handle exception
			bean.setCode(Constant.ERROR_CONSTANT);
			bean.setMessage(e.getMessage());
		}
		JsonManager.data2Json(bean, response);
	}
	
	
	private void find(HttpServletRequest request, HttpServletResponse response) {
		String dname = request.getParameter("dname");
		JsonBean bean = new JsonBean();
		try {
			List<Desk> list = ds.findBydname(dname);
			bean.setCode(Constant.CORRECT_CONSTANT);
			bean.setMessage(list);
		} catch (Exception e) {
			// TODO: handle exception
			bean.setCode(Constant.ERROR_CONSTANT);
			bean.setMessage(e.getMessage());
		}
		JsonManager.date2Json(bean, response);
	}
	
}
