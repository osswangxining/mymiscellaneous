package meilucrawler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ImageFetcher {

	public static void main(String[] args) throws IOException {
		for (int i = 1; i <= 72; i++) {
			FileInputStream fis = null;
			InputStreamReader isr = null;
			BufferedReader br = null;
			String temp = "";
			try {
				fis = new FileInputStream("/Users/xiningwang/images/urls/" + i + "a.txt");
				isr = new InputStreamReader(fis);
				br = new BufferedReader(isr);
				while ((temp = br.readLine()) != null) {
					if (temp.trim().length() == 0) {
						continue;
					}	
					try {
						generate(temp.trim(), "/Users/xiningwang/images/meilu");
					} catch (Exception e) {
						System.out.println(temp);
						e.printStackTrace();
						//System.exit(-1);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (br != null) {
					br.close();
				}
				if (isr != null) {
					isr.close();
				}
				if(fis != null) {
					fis.close();
				}
			}
			System.out.println("[" + i + "] is done.");
		}
	}

	public static void generate(String fileUrl, String downPath) throws Exception {
		File savePath = new File(downPath);
		if (!savePath.exists()) {
			savePath.mkdir();
		}
		String[] urlname = fileUrl.split("/");
		int len = urlname.length - 1;
		String filename = urlname[len].split("\\?")[0];
		try {
			File file = new File(savePath + "/" + filename);// 创建新文件
			if (file != null && file.exists()) {
				System.out.println("文件已经存在：" + downPath + filename);

				return;
			}
			if (file != null && !file.exists()) {
				file.createNewFile();
			} 
			OutputStream oputstream = new FileOutputStream(file);
			URL url = new URL(fileUrl);
			HttpURLConnection uc = (HttpURLConnection) url.openConnection();
			uc.setDoInput(true);// 设置是否要从 URL 连接读取数据,默认为true
			uc.connect();
			InputStream iputstream = uc.getInputStream();
			System.out.println("file size is:" + uc.getContentLength());// 打印文件长度
			byte[] buffer = new byte[4 * 1024];
			int byteRead = -1;
			while ((byteRead = (iputstream.read(buffer))) != -1) {
				oputstream.write(buffer, 0, byteRead);
			}
			oputstream.flush();
			iputstream.close();
			oputstream.close();
		} catch (Exception e) {
			System.out.println("读取失败:" +fileUrl);
			e.printStackTrace();
		}
		System.out.println("生成文件路径：" + downPath + filename);
	}
}
