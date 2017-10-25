package fixBoard;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import common.HanbitFileRenamePolicy;

@WebServlet("/fix/modifyinsert")
public class ModifyController2 extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uploadPath = "c:/lee/jsp/miniProject/WebContent/img/fix/image";
		
		MultipartRequest mRequest = new MultipartRequest(
				request,
				uploadPath,
				1024*1024*10,
				"utf-8",
				new HanbitFileRenamePolicy()
		);
		
		int no  = Integer.parseInt(mRequest.getParameter("no"));
		String title = mRequest.getParameter("title");
		String content = "";
		String cate = mRequest.getParameter("cate");
		String brand = mRequest.getParameter("brand");
		
		for (int i = 0; i < mRequest.getParameterValues("content").length; i++) {
			
			if(!mRequest.getParameterValues("content")[i].equals("")) {
				String str = "";
				
				if(i != 0)
					str = "☎AIE☎";
				
				content += str+mRequest.getParameterValues("content")[i];
			}
			
		}
		
		FixDAO dao = new FixDAO();
		FixVO fix = new FixVO();
		if(mRequest.getFile("0") != null) {
			fix.setThumb(new ThumbMake().Thumb(mRequest.getFile("0")));
			new File("c:/lee/jsp/miniProject/WebContent/img/fix/thumb", mRequest.getParameter("0name")).delete();
		}

		fix.setTitle(title);
		fix.setContent(content);
		fix.setNo(no);
		fix.setCate(cate);
		fix.setBrand(brand);
		
		try {
			dao.modifyFix(fix);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		for(int i = 0; i < 5; i++) {
			FixVO ff = new FixVO();
			String fName = ""+i;
	
			File file = mRequest.getFile(fName);
	
		if(file != null) {
			String oriName = mRequest.getOriginalFileName(fName);
			String sysName = mRequest.getFilesystemName(fName);
			String delFile = mRequest.getParameter(i+"name");
			int imageNo = Integer.parseInt(mRequest.getParameter(i+"no"));
			
			new File(uploadPath, delFile).delete();
			
			ff.setOriName(oriName);
			ff.setSysName(sysName);
			ff.setImageNo(imageNo);
			
			BufferedImage img = ImageIO.read(file);
			
			ff.setWidth(img.getWidth());
			
			try {
				dao.modifyFile(ff);
			} catch (Exception e) {}
			
			}
		}
		
		response.sendRedirect(request.getContextPath()+"/fix/list");
	}
}
