package fixBoard;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/fix/dut")
public class DutViewController extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터 추출
		request.setCharacterEncoding("utf-8");
		
		int no = Integer.parseInt(request.getParameter("no"));
		int cno = 0;
		
		if(request.getParameter("cno") != null)
			cno = Integer.parseInt(request.getParameter("cno"));

		FixDAO dao = new FixDAO();
		List<FixVO> list = null;
		try {
			list = dao.detailDut(no);
		} catch (Exception e) {
			// TODO: handle exception
		}	
		
		RequestDispatcher rd = request.getRequestDispatcher("/fix/dut.jsp");
		request.setAttribute("list", list);
		if(cno > 0) request.setAttribute("cno", cno);
		
		rd.forward(request, response);
	}
}
