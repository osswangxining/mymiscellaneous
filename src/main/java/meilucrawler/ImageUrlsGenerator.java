package meilucrawler;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ImageUrlsGenerator {

	public static void main(String[] args) throws IOException {
		for (int i = 18; i <= 72; i++) {
			FileInputStream fis = null;
			InputStreamReader isr = null;
			BufferedReader br = null;
			String temp = "";
			FileOutputStream fos = null;
			PrintWriter pw = null;
			try {
				fis = new FileInputStream("/Users/xiningwang/images/urls/" + i + ".txt");
				isr = new InputStreamReader(fis);
				br = new BufferedReader(isr);
				fos = new FileOutputStream("/Users/xiningwang/images/urls/" + i + "a.txt");
				pw = new PrintWriter(fos);
				while ((temp = br.readLine()) != null) {
					if (temp.trim().length() == 0) {
						continue;
					}
					
					try {
						List<String> urls2 = get(temp);
						for (String string : urls2) {
							pw.write(string);
							pw.write(System.getProperty("line.separator"));
						}
					} catch (Exception e) {
						System.out.println(temp);
						e.printStackTrace();
						//System.exit(-1);
					}
				}
				pw.flush();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (pw != null) {
					pw.close();
				}
				if (fos != null) {
					fos.close();
				}
			}
			System.out.println("[" + i + "] is done.");
		}
	}

	public static List<String> get(String url) throws IOException {
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
				// System.out.println(bigimgsrc);
				result.add(bigimgsrc);
			}

		}
		return result;
	}
}
