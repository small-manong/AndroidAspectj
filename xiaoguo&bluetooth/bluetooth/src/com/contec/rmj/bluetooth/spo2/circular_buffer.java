package com.contec.rmj.bluetooth.spo2;

import java.lang.reflect.Array;
public class circular_buffer<T>
{
	private T[] m_buff;
	private int m_first;
	private int m_last;
	private int m_size;
	private int m_end;
	public circular_buffer(Class<T[]> array_clazz, int len )
	{
		m_buff =  (T[]) Array.newInstance(array_clazz.getComponentType(), len);
		m_end = len;
		m_first = 0;
		m_last = 0;
		m_size = 0;
	}
	public synchronized void clear()
	{
		m_buff = null;
		m_end = 0;
		m_first = 0;
		m_last = 0;
		m_size = 0;
		
	}
	public synchronized T get(int index)
	{
		if(index>m_size)
		{
			throw new ArrayIndexOutOfBoundsException("in get");
		}
		int i = m_first+(index<(m_end-m_first)?index:index-capacity());
		return m_buff[i];
	}
	public int capacity()
	{
		return m_end;
	}
	public synchronized void push_back(T t) 
	{
		if(m_last<0||m_last>m_end-1)
			throw new ArrayIndexOutOfBoundsException("in push_back0");
		if(m_size<m_end)
		{
			m_buff[m_last] = t;
			m_last++;	
			m_size++;
			if(m_last==m_end)
			{
				m_last=0;
			}
		}
		else 
		{
			if(m_size!=m_end)
				throw new ArrayIndexOutOfBoundsException("in push_back1");
			m_buff[m_last] = t;
			m_first++;
			m_last++;
			if(m_last==m_end)
			{
				m_first = 0;
				m_last = 0;
			}

		}
	}
	
	public synchronized void push_front(T t)
	{
		if(m_first<0||m_first>m_end-1)
			throw new ArrayIndexOutOfBoundsException("in push_front0");
		if(m_first==0&&m_size<m_end)
		{
			m_first = m_end-1;
			m_buff[m_first]=t;
			m_size++;
		}
		else if(m_first==0&&m_size==m_end)
		{
			m_first = m_end-1;
			m_last = m_end-1;
			m_buff[m_first]=t;
		}
		else if( m_first!=0&&m_size<m_end)
		{
			m_first--;
			m_buff[m_first] = t;
			m_size++;
		}
		else if(m_first!=0&&m_size==m_end)
		{
			m_first--;
			m_last--;
			m_buff[m_first] = t;
		}
		else
		{
			throw new ArrayIndexOutOfBoundsException("in push_front3");
		}
	}
	
	public synchronized T back()
	{
		if(empty())
		{
			throw new ArrayIndexOutOfBoundsException("in back");
		}
		if(m_last==0)
		{
			return m_buff[m_end-1];
		}
		else 
		{
			return m_buff[m_last-1];
		}
	}
	
	public synchronized boolean full()
	{
		return m_size==m_end;
	}
	
	public synchronized boolean empty()
	{
		return m_size==0;
	}
	
	public synchronized int size()
	{
		return m_size;
	}
	
	public array_range array_one()
	{
		int first = m_first;
		int second  = (m_last <= m_first && !empty() ? m_end : m_last) - m_first;
		MyArray<T> array = new MyArray<T>(first,second,m_buff);
		array_range ret = new array_range(array,second);
		return ret;
	}
	
	public array_range array_two()
	{
		int first =0;
		int second = m_last <= m_first && !empty() ? m_last - 0 : 0;
		MyArray<T> array = new MyArray<T>(first,second,m_buff);
		array_range ret = new array_range(array,second);
		return ret;
	}
	
	
	public static <T> void show(T[] t)
	{
		for(int i=0;i<t.length;i++)
		{
			System.out.println(i+"="+t[i]);
		}
	}

	
	public static void main(String[] args)
	{
		circular_buffer<Integer> buf = new circular_buffer<Integer>(Integer[].class,4);
		/*buf.push_back(1);
		buf.push_back(2);
		buf.push_back(3);
		buf.push_back(4);
		buf.push_back(5);
		buf.push_back(6);
		buf.push_back(7);
		buf.push_back(8);
		buf.push_back(9);
		buf.push_front(1);
		buf.push_front(2);
		buf.push_front(3);
		buf.push_front(4);
		buf.push_front(5);
		buf.push_front(6);
		buf.push_front(7);
		buf.push_front(8);
		buf.push_front(9);
		buf.push_front(1);
		buf.push_back(2);
		buf.push_front(4);
		buf.push_front(5);*/
		/*buf.push_back(3);

		buf.push_front(4);
		buf.push_front(2);*/
		buf.push_back(4);
		buf.push_back(2);
		buf.push_front(3);
		array_range a = buf.array_one();
		array_range b = buf.array_two();
		MyArray<Integer> arr = a.first;
		MyArray<Integer> arrb = b.first;
		for(int i=0;i<arr.length();i++)
			System.out.println(arr.get(i));
		show(buf.m_buff);
		return ;
	}
}
