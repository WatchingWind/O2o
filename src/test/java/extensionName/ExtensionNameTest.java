package extensionName;

import java.io.File;

public class ExtensionNameTest {
	public static void main(String[] args) {
		File file = new File("G:/A/Thumbnails/1.jpg");
		String filename = file.getName();
		System.out.println(filename.substring(filename.lastIndexOf(".")));
		
	}
}
