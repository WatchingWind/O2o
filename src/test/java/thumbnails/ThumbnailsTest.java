package thumbnails;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Position;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;	

public class ThumbnailsTest {
	public static void main(String[] args) {
		
		try {
			Thumbnails.of(new File("G:/A/Thumbnails/1.jpg"))
			.size(100, 100)
			.watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File("G:/A/Thumbnails1/5.jpg")),(float) 0.5)
			.outputFormat("jpg")
			.toFile(new File("G:/A1/Thumbnails/2.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
