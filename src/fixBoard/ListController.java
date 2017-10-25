package fixBoard;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/fix/list")
public class ListController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		FixDAO dao = new FixDAO();
		List<FixVO> list = null;
		int page = 1, size = 0, lsize = 0, tab = 1;
		//	페이지	   게시물 갯수	페이지 갯수	     페이지 탭
		String order = "no desc";
		
		if(request.getParameter("order") != null && !request.getParameter("order").equals(""))
			order = request.getParameter("order");
		
		if(request.getParameter("page") != null)
			page = Integer.parseInt(request.getParameter("page"));
		
		if(request.getParameter("tab") != null && Integer.parseInt(request.getParameter("tab")) > 1)
			tab = Integer.parseInt(request.getParameter("tab"));
		
		try {
			list = dao.listFix(order, page);
			size = dao.Page();
			lsize = (size%8 == 0) ? size/8 : size/8+1;	
			
			for (FixVO vo : list) {
				vo.setLike(dao.detailLike(vo.getNo()));
			}
			
		} catch (Exception e) {}
		
		RequestDispatcher rd = request.getRequestDispatcher("/fix/list.jsp");
		request.setAttribute("list", list);
		request.setAttribute("page", page);
		request.setAttribute("size", size);
		request.setAttribute("lsize", lsize);
		request.setAttribute("tab", tab);
		
		rd.forward(request, response);
	}
}
