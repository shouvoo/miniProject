package fixBoard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/fix/search")
public class ListController2 extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		FixDAO dao = new FixDAO();
		List<FixVO> list = null;
		int page = 1, size = 0, lsize = 0, tab = 1;
		//	페이지	   게시물 갯수	페이지 갯수	     페이지 탭
		String order = "";
		boolean chk = false;
		
		String cate = request.getParameter("cate");
		String title = "";
		if(request.getParameter("title") != null)
			title = "%"+request.getParameter("title")+"%";
		String brand = request.getParameter("brand");
		
		if(title != null)
			if(title.length() > 2) {
				order += "title like ? ";
				chk = true;
			}
		
		if(cate != null)
			if(cate.length() > 0 && !cate.equals("전체")) {
				if(title.length() > 2)
					order += "and ";
				order += "cate = ? ";
				chk = true;
			}
		
		if(brand != null)
			if(brand.length() > 0 && !brand.equals("전체") && !brand.equals("전체")) {
				if(cate.length() > 0 && !cate.equals("전체"))
					order += "and ";
				order += "brand = ?";
				chk = true;
			}
		
		try {
			if(chk) {
				list = dao.listFix(order, title, cate, brand, page);
				size = dao.Page(order, title, cate, brand);
			} else {
				list = dao.listFix("no desc", page);
				size = dao.Page();
			}
			
			lsize = (size%8 == 0) ? size/8 : size/8+1;	
			
			for (FixVO vo : list) {
				vo.setLike(dao.detailLike(vo.getNo()));
			}
			
		} catch (Exception e) {}
		
		RequestDispatcher rd = request.getRequestDispatcher("/fix/list.jsp");
		if(list.size() > 0) {
			request.setAttribute("list", list);
		} else {
			request.setAttribute("msg", "검색된 결과가 없습니다.");
		}
		request.setAttribute("page", page);
		request.setAttribute("size", size);
		request.setAttribute("lsize", lsize);
		request.setAttribute("tab", tab);
		request.setAttribute("cate", cate);
		request.setAttribute("brand", brand);
		
		
		rd.forward(request, response);
	}
}
