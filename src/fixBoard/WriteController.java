package fixBoard;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import common.HanbitFileRenamePolicy;

@WebServlet("/fix/writeinsert")
public class WriteController extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		new ThumbMake();
		
		String uploadPath = "c:/lee/jsp/miniProject/WebContent/img/fix/image";
		File f = new File(uploadPath);
		
		if(!f.exists()) f.mkdirs();
		
		MultipartRequest mRequest = new MultipartRequest(
				request,
				uploadPath,
				1024*1024*10,
				"utf-8",
				new HanbitFileRenamePolicy()
		);

		String title = mRequest.getParameter("title");
		String writer = mRequest.getParameter("writer");
		String cate = mRequest.getParameter("cate");
		String brand = mRequest.getParameter("brand");
		String content = "";
		String thumb = new ThumbMake().Thumb(mRequest.getFile("file1"));
		
		for (int i = 0; i < mRequest.getParameterValues("content").length; i++) {
			
			if(!mRequest.getParameterValues("content")[i].equals("")) {
				String str = "";
				
				if(i != 0)
					str = "☎AIE☎";
				
				content += str+mRequest.getParameterValues("content")[i];
			}
			
		}
		
		System.out.println(content);
		
		Enumeration<String> fNames = mRequest.getFileNames();
		
		FixDAO dao = new FixDAO();
		int no = 0;
		
		try {
			no = dao.number();
		} catch (Exception e1) {}
		
		FixVO fix = new FixVO();
		
		fix.setBrand(brand);
		fix.setCate(cate);
		fix.setContent(content);
		fix.setWriter(writer);
		fix.setTitle(title);
		fix.setNo(no);
		fix.setThumb(thumb);
		fix.setIp(request.getLocalAddr());
		fix.setId((String)request.getAttribute("id"));
		
		try {
			dao.insertFix(fix);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		while (fNames.hasMoreElements()) {
			FixVO ff = new FixVO();
			String fName = fNames.nextElement();
	
			File file = mRequest.getFile(fName);
	
		if(file != null) {
			String oriName = mRequest.getOriginalFileName(fName);
			String systemName  = mRequest.getFilesystemName(fName);

			ff.setNo(no);
			ff.setSysName(systemName);
			ff.setOriName(oriName);
			
			try {
				dao.insertFile(ff);
			} catch (Exception e) {}
			
			}
		}

		response.sendRedirect(request.getContextPath()+"/fix/list");
	}
}
