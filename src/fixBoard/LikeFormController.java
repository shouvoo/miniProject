package fixBoard;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/fix/likeform")
public class LikeFormController extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		FixDAO dao = new FixDAO();
		int like = 0;
		try {
			like = dao.detailLike(no);
		} catch (Exception e) {}
		
		RequestDispatcher rd = request.getRequestDispatcher("/fix/like.jsp?no="+no+"&like="+like);
		rd.forward(request, response);
	}
}
