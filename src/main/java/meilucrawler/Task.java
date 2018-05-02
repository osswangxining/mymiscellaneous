package meilucrawler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Task implements Callable<List<String>> {
	private static Logger logger;
	static {
		PropertyConfigurator.configure("/Users/xiningwang/images/log4j.properties");
		logger = Logger.getLogger(test2.class);
	}
	private List<String> urls;

	public Task(List<String> urls) {
		this.urls = urls;
	}

	@Override
	public List<String> call() throws Exception {
		List<String> result = new ArrayList<String>();
		for (String url : urls) {
			List<String> list = run(url);
			result.addAll(list);
		}
		return result;
	}

	public static List<String> run(String url) throws IOException {
		List<String> result = new ArrayList<String>();
		Document doc = Jsoup.connect(url).get();
		Element body = doc.getElementsByTag("body").first();
		Element elemdoc = body.getElementsByClass("doc").first();
		Element elembody = elemdoc.getElementsByClass("body").first();
		Element elemcontent = elembody.getElementsByClass("content").first();
		Element elemwrap = elemcontent.getElementsByClass("wrap").first();
		Element image = elemwrap.getElementsByClass("clear cntphoto").first().getElementsByClass("image").first();
		Elements children = image.children();
		// System.out.println(children);
		for (Element child : children) {
			Elements elems = child.getElementsByTag("a");
			if (elems != null && elems.size() > 0) {
				String bigimgsrc = elems.get(0).attr("bigimgsrc");
				System.out.println(bigimgsrc);
				// String[] urlname = bigimgsrc.split("/");
				// int len = urlname.length - 1;
				// String uname = urlname[len];// 获取文件名
				// String[] filename = uname.split("\\?");
				// System.out.println(filename[0]);
				try {
					generate(bigimgsrc, "/Users/xiningwang/images/meilu");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					result.add(bigimgsrc);
				}
			}

		}
		return result;
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
			System.out.println("读取失败！");
			e.printStackTrace();
			logger.error("error:" + fileUrl);
			throw e;
		}
		System.out.println("生成文件路径：" + downPath + filename);
	}
}
