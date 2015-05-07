<%@page import="com.sds.icto.guestbook.dao.GuestBookDao"%>
<%
	request.setCharacterEncoding("utf-8");
	try{
		GuestBookDao dao = new GuestBookDao();
		boolean isSuccess = dao.delete(Integer.parseInt(request.getParameter("id")), request.getParameter("password"));
		if(isSuccess){
			response.sendRedirect("/20150507_guestBook");
		}else{
			response.sendRedirect("deleteform.jsp");
		}
	}catch(Exception e){
		e.printStackTrace();
	}
%>
