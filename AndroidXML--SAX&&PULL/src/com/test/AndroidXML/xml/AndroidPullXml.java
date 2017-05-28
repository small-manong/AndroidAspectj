package com.test.AndroidXML.xml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import com.test.AndroidXML.entity.Book;

public class AndroidPullXml {
	
	public static List<Book> readXML(InputStream inputStream,String inputEncoding) throws Exception{
		//创建pull解析
		XmlPullParserFactory pullParseFac = XmlPullParserFactory.newInstance();
		XmlPullParser pullParser = pullParseFac.newPullParser();
		
		//解析xml
		pullParser.setInput(inputStream, inputEncoding);
		
		//开始
		int eventType = pullParser.getEventType();
		
		List<Book> books = null;
		Book book = null;
		while(eventType!=XmlPullParser.END_DOCUMENT){
			String nodeName = pullParser.getName();
			switch (eventType) {
			//文档开始
			case XmlPullParser.START_DOCUMENT:
				books = new ArrayList<Book>();
				break;
			//节点开始	
			case XmlPullParser.START_TAG:
				if("book".equals(nodeName)){
					book = new Book();
					book.setId(Integer.parseInt(pullParser.getAttributeValue(0)));
				}else if("name".equals(nodeName)){
					book.setName(pullParser.nextText());
					
				}else if("price".equals(nodeName)){
					book.setPrice(Float.parseFloat(pullParser.nextText()));
				}
				break;
			//节点结束
			case XmlPullParser.END_TAG:
				if("book".equals(nodeName)){
					books.add(book);
					book=null;
				}
				break;
			}
			eventType = pullParser.next();
		}
		return books;
	}

}
