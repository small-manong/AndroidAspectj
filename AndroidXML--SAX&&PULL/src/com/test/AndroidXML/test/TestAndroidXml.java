package com.test.AndroidXML.test;

import java.io.InputStream;
import java.util.List;

import com.test.AndroidXML.AndroidXML;
import com.test.AndroidXML.entity.Book;
import com.test.AndroidXML.xml.AndroidPullXml;
import com.test.AndroidXML.xml.AndroidSaxXml;

import android.test.AndroidTestCase;
import android.util.Log;

public class TestAndroidXml extends AndroidTestCase {

	private static final String TAG="TestAndroidXml";
	
	/**
	 * 测试Sax解析xml
	 * */
	public void testAndroidSaxReadXml()throws Throwable{
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("books.xml");
		try{
			List<Book> books = AndroidSaxXml.readXML(is);
			Log.i(TAG, books.toString());
			
		}catch(Exception e){
			Log.e(TAG,e.toString());
		}
		
	}
	
	/**
	 * 测试pull解析xml
	 * */
	public void testAndroidPullReadXml()throws Throwable{
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("books.xml");
		try{
			List<Book> books = AndroidPullXml.readXML(is, "utf-8");
			Log.i(TAG, books.toString());
		}catch(Exception e){
			Log.e(TAG, e.toString());
		}
	}
}
