package qna;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.PageResult;

@WebServlet("/qna/list")
public class ListQnaController extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse respnse) throws ServletException, IOException {
		int pageNo = 1; 
		try {
			
			pageNo=Integer.parseInt(request.getParameter("pageNo"));
			
		}catch(NumberFormatException e){}
		
		try {
			QnaDAO dao = new QnaDAO();
			Page page = new Page(pageNo);
			List<QnaDomain> list = dao.listQna(page);
			
			int count = dao.listBoardCount();
			PageResult pageResult = new PageResult(pageNo, count);
			
			request.setAttribute("pageResult", pageResult);
			
			request.setAttribute("list", list);
			
			RequestDispatcher rd = request.getRequestDispatcher("/qna/list.jsp");
			rd.forward(request, respnse);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ServletException(e);
		}
		
	}
	
}
