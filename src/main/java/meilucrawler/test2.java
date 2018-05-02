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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class test2 {
	private static Logger logger;
	static {
		PropertyConfigurator.configure("/Users/xiningwang/images/log4j.properties");
		logger = Logger.getLogger(test2.class);
	}

	public static void main(String[] args) throws IOException {
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
		ArrayList<Future<List<String>>> list = new ArrayList<>();
		List<String> urls = new ArrayList<String>();
		for (int i = 1; i <= 72; i++) {
			try {
				List<String> urls2 = getUrls(i);
				Future<List<String>> result = executor.submit(new Task(urls2));
				list.add(result);
			} catch (IOException e) {
				e.printStackTrace();
				logger.error("page:" + i);
			}

		}
		for (Future<List<String>> f : list) {
			try {
				// while(!f.isDone()){};
				// 阻塞等待获取返回值f.get()
				List<String> list2 = f.get();
				if (!list2.isEmpty()) {
					for (String string : list2) {
						System.out.println(string);
						logger.error("===" + string);
					}
				}

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		executor.shutdown();
		// for (String url : urls) {
		// run(url);
		// }
	}

	public static List<String> getUrls() {
		List<String> urls = new ArrayList<String>();
		for (int i = 1; i <= 72; i++) {
			try {
				urls.addAll(getUrls(i));
			} catch (IOException e) {
				e.printStackTrace();

			}
		}
		return urls;
	}

	public static List<String> getUrls(int page) throws IOException {
		List<String> urls = new ArrayList<String>();
		Document doc = Jsoup.connect("http://fenchenyue.lofter.com/?page=" + page).get();
		Element body = doc.getElementsByTag("body").first();
		Element elemdoc = body.getElementsByClass("doc").first();
		Element elemindex = elemdoc.getElementsByClass("index").first();
		Element elemul = elemindex.getElementsByTag("ul").first();
		// Element elemwrap = elemul.getElementsByClass("wrap").first();
		// Element image = elemwrap.getElementsByClass("clear
		// cntphoto").first().getElementsByClass("image").first();
		Elements children = elemul.children();
		// System.out.println(children);
		for (Element child : children) {
			Elements elems = child.getElementsByTag("a");
			if (elems != null && elems.size() > 0) {
				String bigimgsrc = elems.get(0).attr("href");
				// System.out.println(bigimgsrc);
				urls.add(bigimgsrc);
			}

		}
		return urls;
	}

}
