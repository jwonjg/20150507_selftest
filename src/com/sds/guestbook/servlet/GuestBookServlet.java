package com.sds.guestbook.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sds.guestbook.dao.GuestBookDao;
import com.sds.guestbook.vo.GuestBookVo;

@WebServlet("/guestbook")
public class GuestBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		try {
			String action = request.getParameter("action");
			if("insert".equals(action)){
				GuestBookDao dao = new GuestBookDao();
				dao.insert(new GuestBookVo(request.getParameter("name"), request.getParameter("pass"), request.getParameter("content")));
				response.sendRedirect("guestbook");
			}else if("delete".equals(action)){
				GuestBookDao dao = new GuestBookDao();
				boolean isSuccess = dao.delete(Integer.parseInt(request.getParameter("id")), request.getParameter("password"));
				if(isSuccess){
					response.sendRedirect("guestbook");
				}else{
					response.sendRedirect("guestbook");
				}
			}else{
				GuestBookDao dao = new GuestBookDao();
				List<GuestBookVo> list = dao.fetchList();
				request.setAttribute("list", list);
				request.getRequestDispatcher("/view/list_form.jsp").forward(request, response);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
