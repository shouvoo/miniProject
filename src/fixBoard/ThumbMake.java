package fixBoard;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;

public class ThumbMake {
	
	public static String Thumb(File file) {
		String thumbPath = "c:\\lee\\jsp\\miniProject\\WebContent\\img\\fix\\thumb"+File.separator;
		try {
			
			File thumbDir = new File(thumbPath);
			if(!thumbDir.exists()) thumbDir.mkdirs();
			
			BufferedImage srcImg = ImageIO.read(file);
			
			int extIndex = file.getName().lastIndexOf(".");
			String fileExt = file.getName().substring(extIndex+1);
			
			int dw = 300, dh = 100;
			
			int ow = srcImg.getWidth();
			int oh = srcImg.getHeight();
			
			int nw = ow;
			int nh = (ow*dh)/dw;
			
			if(nh > oh) {nw = (oh*dw/dh); nh = oh;}
			
			BufferedImage cropImg = Scalr.crop(srcImg, (ow-nw)/2, (oh-nh)/2, nw, nh);
			
			BufferedImage destImg = Scalr.resize(cropImg, dw, dh);
			
			String thumbName = UUID.randomUUID().toString();
			File thumbFile = new File(thumbPath+thumbName+"."+fileExt);
			
			ImageIO.write(destImg, fileExt, thumbFile);
			
			return thumbFile.getName();
			
			//System.out.println(thumbFile+" 생성");
		} catch (IOException e) {} 
		return null;
	}
	
}
