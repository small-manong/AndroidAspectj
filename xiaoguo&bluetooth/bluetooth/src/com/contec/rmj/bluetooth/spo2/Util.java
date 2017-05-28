package com.contec.rmj.bluetooth.spo2;

import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;

public class Util {
	public static boolean writeLong(OutputStream out,long l)
	{
		byte[] temp = new byte[4];
		temp[0] = (byte)((l&0x000000ff));
		temp[1] = (byte)((l&0x0000ff00)>>8);
		temp[2] = (byte)((l&0x00ff0000)>>16);
		temp[3] = (byte)((l&0xff000000)>>24);
		try
		{
			out.write(temp, 0, 4);
		}
		catch(IOException e)
		{
			e.printStackTrace();
			return false;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static boolean writeString(OutputStream out, String str,int len)
	{
		if(str!=null)
		{
		str.getBytes();
		for(int i=0;(i<str.length())&&(i<len);i++)
		{
			char t = str.charAt(i);
			
			System.out.println(t);
			
		}
		}
		
		byte[] temp = new byte[len*2];
		byte[] t=null;
		if(str!=null)
		{
			
			try {
				t = str.getBytes("UTF-16");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(t!=null)
		for(int i=0;i<t.length&&i<len*2;i++)
		{
			temp[i] = t[i];
		}
		else
		{
			for(int i=0;i<len*2;i++)
			{
				temp[i] = 0;
			}
		}
		temp[len*2-1] = 0;
		try
		{
			out.write(temp, 0, len*2);
		}
		catch(IOException e)
		{
			e.printStackTrace();
			return false;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static boolean writeShort( OutputStream out, int s)
	{
		byte[] temp = new byte[2];
		temp[0] = (byte)(s&0x000000ff);
		temp[1] = (byte)((s&0x0000ff00)>>8);
		try
		{
			out.write(temp, 0, 2);
		}
		catch(IOException e)
		{
			e.printStackTrace();
			return false;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public static boolean writeChar(OutputStream out, int c)
	{
		byte[] temp= new byte[1];
		temp[0] = (byte)c;
		try {
			out.write(temp);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public static boolean writeLong(RandomAccessFile file,long l)
	{
		byte[] temp = new byte[4];
		temp[0] = (byte)((l&0x000000ff));
		temp[1] = (byte)((l&0x0000ff00)>>8);
		temp[2] = (byte)((l&0x00ff0000)>>16);
		temp[3] = (byte)((l&0xff000000)>>24);
		try
		{
			file.write(temp, 0, 4);
		}
		catch(IOException e)
		{
			e.printStackTrace();
			return false;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static boolean writeString(RandomAccessFile file, String str,int len)
	{
		
		byte[] temp = new byte[len*2];
		byte[] t=null;
		if(str!=null)
		t = str.getBytes();
		if(t!=null)
		for(int i=0;i<t.length&&i<len*2;i++)
		{
			temp[i] = t[i];
		}
		else
		{
			for(int i=0;i<len*2;i++)
			{
				temp[i] = 0;
			}
		}
		temp[2*len-1] = 0;
		try
		{
			file.write(temp, 0, len*2);
		}
		catch(IOException e)
		{
			e.printStackTrace();
			return false;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static boolean writeShort( RandomAccessFile file, int s)
	{
		byte[] temp = new byte[2];
		temp[0] = (byte)(s&0x000000ff);
		temp[1] = (byte)((s&0x0000ff00)>>8);
		try
		{
			file.write(temp, 0, 2);
		}
		catch(IOException e)
		{
			e.printStackTrace();
			return false;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public static boolean writeChar(RandomAccessFile file, int c)
	{
		byte[] temp= new byte[1];
		temp[0] = (byte)c;
		try {
			file.write(temp);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
