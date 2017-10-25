package fixBoard;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/fix/view")
public class ViewController extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터 추출
		request.setCharacterEncoding("utf-8");
		
		int no = 0;
		
		if(request.getParameter("no") != null)
			no = Integer.parseInt(request.getParameter("no"));
		
		FixDAO dao = new FixDAO();
		FixVO fix = new FixVO();
		List<FixVO> file = null;
		
		try {
			dao.lookUp(no);
			fix = dao.detailFix(no);
			file = dao.detailFile(no);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		List<String> content = new ArrayList<>();
		
		for (String str : fix.getContent().split("[☎AIE☎]")) {
			if(!str.equals(""))
			content.add(str);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/fix/view.jsp");

		request.setAttribute("fix", fix);
		request.setAttribute("content", content);
		request.setAttribute("file", file);
		
		rd.forward(request, response);
	}
}
