package qna;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/qna/answer")
public class AnswerQnaController extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int no = Integer.parseInt(request.getParameter("no"));
		String aComment = request.getParameter("aComment");
		
		QnaDAO dao = new QnaDAO();
		QnaDomain qna = new QnaDomain();
		
		qna.setNo(no);
		qna.setaComment(aComment);
		try {
			dao.insertAnswerQna(qna);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ServletException(e);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/qna/detail?no="+no);
		rd.forward(request, response);
		
	}
	
}
