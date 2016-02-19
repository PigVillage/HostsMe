package test;
import java.io.IOException;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Test {
	public Test(){
		Document document = getDoc();
		System.out.println(":");
		Scanner input = new Scanner(System.in);
		String label = input.next();
		Elements element = getElement(document,label);
		System.out.println(getText(element));
	}
	public Document getDoc(){
		Document document;
		try {
			document = Jsoup.connect("http://www.qq.com").get();
			return document;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Elements getElement(Document doc, String label){
		Elements element = doc.select(label);
		return element;
	}
	
	public String getText(Elements element){
		return element.text();
	}
	
	public static void main(String[] args){
		Test ts = new Test();
    }
}


