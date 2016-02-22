package test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.terrylovesolar.hostsme.common.Constants;
import com.terrylovesolar.hostsme.func.InitCheck;

public class PostTest {
	public static void main(String[] args) {
		InitCheck initCheck = new InitCheck();
		try {
			Date utilDate = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Document doc = Jsoup.connect(Constants.UPDATE_URL).data("os",initCheck.osCheck(),"version","Ver 1.1").post();
			System.out.println(formatter.format(utilDate));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
