package fixBoard;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/fix/dutwrite")
public class DutWriteController extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터 추출
		request.setCharacterEncoding("utf-8");

		int no = Integer.parseInt(request.getParameter("no"));
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		String id = "아이디";
		//(String)request.getAttribute("id");
		
		FixVO bd = new FixVO();
		bd.setNo(no);
		bd.setWriter(writer);
		bd.setContent(content);
		bd.setId(id);
		
		FixDAO dao = new FixDAO();
		try {
			dao.insertDut(bd);
		} catch (Exception e) {}
		
		response.sendRedirect(request.getContextPath()+"/fix/view?no="+no);
	}
}
