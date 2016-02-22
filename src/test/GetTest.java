package test;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class GetTest {

	public static void main(String[] args) {
		try {
			Document document = Jsoup.connect("http://re.terry5.com").userAgent("bG92ZXNvbGFyNGV2ZXI=").get();
			Elements version = document.select("version");
			System.out.println(version.text());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
