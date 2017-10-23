package common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/common/down")
public class DownServlet extends HttpServlet
{
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//파일이 저장된 경로
		String filePath = request.getParameter("filePath");
		//실제 저장된 파일명
		String sName = request.getParameter("sName");
		//다운로드할 파일명
		String dName = request.getParameter("dName");
		
		//브라우져에서 전송된 데이터를 화면에 출력하는 것이 아니라
		//다운로드 하도록 설정한다
		response.setHeader("Content-Type", "application/octet-stream");
		
		//다운로드시 파일명 지정
		//파일명이 한글일 경우 처리
		//dName = new String(byte[], "인코딩");
		dName = new String(dName.getBytes("utf-8"), "8859_1");
		response.setHeader("Content-Disposition", "attachment;filename=" + dName);
		
		
		//전송되는 데이터가 바이너리 인코딩 입을 알려준다
		response.setHeader("Content-Transfer-Encoding", "binary");
		
		File f = new File("C:/!SHOU/upload" + filePath, sName); // 수업

		response.setHeader("Content-Length", String.valueOf(f.length()));
		//------------------------------------------------------------------
		
		
		FileInputStream fis = new FileInputStream(f);
		BufferedInputStream bis = new BufferedInputStream(fis);
		
		OutputStream out = response.getOutputStream();
		BufferedOutputStream bos = new BufferedOutputStream(out);
		while(true)
		{
			int ch = bis.read();
			if(ch == -1) break;
			bos.write(ch);
		}
		
		bos.close();
		out.close();
		bis.close();
		fis.close();
	}
}
