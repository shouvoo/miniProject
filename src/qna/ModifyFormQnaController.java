package qna;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/qna/modifyForm")
public class ModifyFormQnaController extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		QnaDomain qna = new QnaDomain();
		QnaDAO dao = new QnaDAO();
		try {
			qna = dao.detailQna(no);
			request.setAttribute("qna", qna);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ServletException(e);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/qna/modifyForm.jsp");
		rd.forward(request, response);
	}
	
}
