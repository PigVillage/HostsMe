package com.terrylovesolar.hostsme.func;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.terrylovesolar.hostsme.common.Constants;
import com.terrylovesolar.hostsme.main.MainWindow;


/**
 * 爬虫类，使用JSoup抓取网页
 * @author Terry5
 * @serial Pig Village
 */

public class Spider {
	//加载属性文件
	Properties properties = new Properties();
	InputStream fis = MainWindow.class.getResourceAsStream("/cfg/conf.properties");
	public Spider() {
		try {
			properties.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	private String SOURCE_URL = "https://raw.githubusercontent.com/highsea/Hosts/master/hosts";
//	private String SOURCE_URL = "https://github.com/highsea/Hosts/blob/master/hosts";
	/**
	 * 使用JSoup获取Hosts网页Document对象
	 * @return 返回JSoup抓取的Document类型的网页源码
	 */
	public Document get_source() {
		try {
			Document doc = Jsoup.connect(Constants.SOURCE_URL).timeout(10000).get();
			return doc;
		} catch (IOException e) {
			
		}
		return null;
	}
	
	/**
	 * 获取所有含有Hosts信息的td标签
	 * @param doc JSoup抓取的Document类型的网页源码
	 * @return Elements 返回分析出的网页节点
	 */
	public Elements get_content(Document doc) {
		Elements content = doc.select("td.blob-code-inner");
		return content;
	}
	
	/**
	 * 从获取的网页源码中提取时间
	 * @param doc
	 * @return String: time
	 */
//	public String get_time(Document doc) {
//		String time = doc.select("time").text();
//		return time;
//	}
	
	
	
	/**
	 * 从网页源码Doc中找到Time标签，提取时间信息
	 * @param doc JSoup抓取的Document类型的网页源码
	 * @return date 返回时间字符串
	 */
	public String get_date(Document doc) {
//		Pattern pattern = Pattern.compile("[0-9]+-[0-9]+-[0-9]+");
//		Matcher matcher = pattern.matcher(html);
//		String html = doc.toString();
//		Matcher matcher = Pattern.compile("[0-9]+-[0-9]+-[0-9]+").matcher(html);
//		while (matcher.find()) {
//			String date = matcher.group();
//			return date;
//		}
//		return "未知";
		try {
			String date = doc.select("time").text();
			return date;
		} catch (Exception e) {
			return "未知";
		}
	}
	
	
	/**
	 * 检测软件版本更新
	 * @return String 更新信息
	 */
	public String update_check() {
		try {
			Document doc = Jsoup.connect(Constants.UPDATE_URL).userAgent(
					Constants.USER_AGENT_UPDATE).timeout(8000).get();
			Elements version = doc.select("version");
			double serverVer = Double.parseDouble(version.text());
			double localVer = Double.parseDouble(properties.getProperty("version"));
			if (serverVer > localVer) {
				Elements url = doc.select("download");
				return url.text();
			} else {
				return "当前为最新版本!";
			}
		} catch (Exception e) {
			return "未知错误！ 请访问作者主页查看详情：http://terrylovesolar.com （Ctrl + C 复制到浏览器访问）";
		}
	}

	public void upload_user_info() {
		InitCheck initCheck = new InitCheck();
		try {
			Date utilDate = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Jsoup.connect(Constants.UPDATE_URL).userAgent(Constants.USER_AGENT_INFO).data("os",initCheck.osCheck(),
					"version",properties.getProperty("version"),
					"time",formatter.format(utilDate)).post();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
//	public static void main(String[] args) {
//		Spider spider = new Spider();
//		System.out.println(spider.update_check());
//	}
}
