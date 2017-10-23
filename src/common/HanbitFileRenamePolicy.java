package common;

import java.io.File;
import java.util.UUID;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class HanbitFileRenamePolicy implements FileRenamePolicy
{
	public File rename(File f) 
	{
		String name = f.getName();
		String ext = ""; // 확장자 저장하기 위한 변수
		int index =  name.lastIndexOf(".");
		
		if(index != -1)
		{
			ext = name.substring(index);
		}
		
		String parent = f.getParent();
		String fName = UUID.randomUUID().toString(); // 유니크한 값을 설정해 준다
		
		return new File(parent, fName + ext);
	}
}
