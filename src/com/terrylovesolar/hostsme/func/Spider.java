package com.terrylovesolar.hostsme.func;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.terrylovesolar.hostsme.common.Constants;


/**
 * 爬虫类，使用JSoup抓取网页
 * @author Terry5
 * @serial Pig Village
 */

public class Spider {
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
	
//	public static void main(String[] args) {
//		Spider spider = new Spider();
//		System.out.println("kaishi");
//		spider.get_content(spider.get_source());
//		
//	}
}
