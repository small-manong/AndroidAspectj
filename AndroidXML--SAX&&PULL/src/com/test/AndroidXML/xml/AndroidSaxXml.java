package com.test.AndroidXML.xml;

import java.io.InputStream;
import java.util.List;


import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import com.test.AndroidXML.entity.Book;

public class AndroidSaxXml {
	public static List<Book> readXML(InputStream inputStream) throws Exception, SAXException{
		//创建sax解析
		SAXParserFactory saxParseFac = SAXParserFactory.newInstance();
		SAXParser saxParser = saxParseFac.newSAXParser();
		
		SaxXmlContentHandler handler = new SaxXmlContentHandler();
		//解析xml文件
		saxParser.parse(inputStream, handler);
		inputStream.close();
		return handler.getBooks();
	}

}
