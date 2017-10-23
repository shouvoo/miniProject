package fixBoard;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/fix/dutdelete")
public class DutDeleteController extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터 추출
		request.setCharacterEncoding("utf-8");
		
		int cno = Integer.parseInt(request.getParameter("cno"));
		int no = Integer.parseInt(request.getParameter("no"));

		FixDAO dao = new FixDAO();
		try {
			dao.deleteDut(cno);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect(request.getContextPath()+"/fix/view?no="+no);
	}
}
