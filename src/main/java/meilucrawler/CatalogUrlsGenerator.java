package meilucrawler;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CatalogUrlsGenerator {

	public static void main(String[] args) throws IOException {
		for (int i = 1; i <= 72; i++) {
			FileOutputStream fos = null;

			PrintWriter pw = null;
			try {
				fos = new FileOutputStream("/Users/xiningwang/images/urls/" + i + ".txt");

				pw = new PrintWriter(fos);
				List<String> urls2 = test2.getUrls(i);
				for (String string : urls2) {
					pw.write(string);
					pw.write(System.getProperty("line.separator"));
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
			System.out.println("["+i+"] is done.");
		}
	}

}
