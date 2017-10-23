package fixBoard;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/fix/like")
public class LikeController extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FixDAO dao = new FixDAO();
		FixVO fix = new FixVO();
		int no = Integer.parseInt(request.getParameter("no"));
		
		fix.setIp(request.getLocalAddr());
		fix.setNo(no);
		
		try {
			if(dao.LikeJungBok(fix)) {
				dao.deleteLike(fix);
			}
			else {
				dao.insertLike(fix);
			}
		} catch (Exception e) {}
		
		response.sendRedirect(request.getContextPath()+"/fix/view?no="+no);
	}
}
