package com.test.AndroidXML.xml;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.test.AndroidXML.entity.Book;
/*
 *  Sax的事件处理类必须实现ContentHandler接口，但我们在这个例子中
 *  不需要使用到ContentHandler接口的所有方法，我们仅需要其中的3个方法。
 *  所以Sax为我们提供了一个没有进行任何操作的ContentHandler实现类DefaultHandler。
 *  我们直接继承DefaultHandler类，并重写我们需要的方法即可。
 * */
public class SaxXmlContentHandler extends DefaultHandler {

	private List<Book> books;
	private Book book;
	private String tagName;
	
	public List<Book> getBooks(){
		return books;
	}
	/**
	 * 接受文档的开始通知
	 */
	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		books = new ArrayList<Book>();
	}
	/**
	 * 接受字符数据的通知
	 * */
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		if(tagName!=null){
			String data = new String(ch,start,length);
			if(tagName.equals("name")){
				book.setName(data);
			}else if(tagName.equals("price")){
				book.setPrice(Float.parseFloat(data));
			}
		}
	}
   /**
     *  接收元素开始的通知。
     *  namespaceURI:元素的命名空间
     *  localName:元素的本地名称（不带前缀）
     *  qName:元素的限定名（带前缀）
     *  atts:元素的属性集合
     */


	@Override
	public void startElement(String uri, String localName, String name,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		if(localName.equals("book")){
			book = new Book();
			book.setId(Integer.parseInt(attributes.getValue(0)));
		}
		this.tagName=localName;
	}
  /**
     *     接收文档的结尾的通知。
     *     uri:元素的命名空间
     *     localName:元素的本地名称（不带前缀）
     *     name:元素的限定名（带前缀）
  */


	@Override
	public void endElement(String uri, String localName, String name)
			throws SAXException {
		// TODO Auto-generated method stub
		if(localName.equals("book")){
			this.books.add(this.book);
		}
		this.tagName=null;
	}



	
	

}
