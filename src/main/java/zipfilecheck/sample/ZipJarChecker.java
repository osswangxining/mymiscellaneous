package zipfilecheck.sample;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.zip.ZipEntry;

public class ZipJarChecker {

	public static void main(String[] args) {
		//File dir =new File(args[0]);
		File dir = new File("/Users/xiningwang/Downloads/test_lib");
		if(dir.isDirectory()) {
			File[] listFiles = dir.listFiles();
			for (int i = 0; i < listFiles.length; i++) {
				try {
					JarFile file = new JarFile(listFiles[i]);
					System.out.println(file.getName() + ":");
					Manifest manifest = file.getManifest();
//					Enumeration<? extends ZipEntry> entries = file.entries();
//					while(entries.hasMoreElements()) {
//						ZipEntry nextElement = entries.nextElement();
//						System.out.println(nextElement.getName());
//					}
//					int size = file.size();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

}
