package com.contec.rmj.bluetooth.spo2;

import java.lang.reflect.Array;

public class MyArray<T> {
	private T[] value;
	private int offset;
	private int count;
	public MyArray()
	{
		value = null;
		offset=0;
		count = 0;
	}
	public MyArray(Class<T[]> array_clazz)
	{
		value = (T[])Array.newInstance(array_clazz.getComponentType(),0);
		offset=0;
		count = 0;
	}

	
	 // Package private constructor which shares value array for speed.
    MyArray(int offset, int count, T value[]) {
    	this.value = value;
    	this.offset = offset;
    	this.count = count;
    }
    
    public int length() {
        return count;
    }

 
    public boolean isEmpty() {
	return count == 0;
    }
    
    public MyArray subarray(int beginIndex, int endIndex) {
    	if (beginIndex < 0) {
    	    throw new ArrayIndexOutOfBoundsException(beginIndex);
    	}
    	if (endIndex > count) {
    	    throw new ArrayIndexOutOfBoundsException(endIndex);
    	}
    	if (beginIndex > endIndex) {
    	    throw new ArrayIndexOutOfBoundsException(endIndex - beginIndex);
    	}
    	return ((beginIndex == 0) && (endIndex == count)) ? this :
    	    new MyArray(offset + beginIndex, endIndex - beginIndex, value);
      }
    
    public MyArray subarray(int beginIndex) {
    	return subarray(beginIndex, count);
    }
    
    
    public T get(int index)
    {
    	if ((index < 0) || (index >= count)) {
    		throw new ArrayIndexOutOfBoundsException("count="+count+";index="+index);
    	}
    	return value[index + offset];
    }
}
