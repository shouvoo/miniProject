package fixBoard;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/fix/dutmodify")
public class DutModifyController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String content = request.getParameter("content");
		int no = Integer.parseInt(request.getParameter("no"));
		int cno = Integer.parseInt(request.getParameter("cno"));
		
		FixVO dut = new FixVO();
		
		dut.setContent(content);
		dut.setCommentNo(cno);
		
		FixDAO dao = new FixDAO();
		try {
			dao.modifyDut(dut);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		response.sendRedirect(request.getContextPath()+"/fix/view?no="+no);
	}
}