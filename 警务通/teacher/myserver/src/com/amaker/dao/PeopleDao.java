package com.amaker.dao;
import com.amaker.entity.People;
/**
 * @author ����־
 * �����˿ڲ�ѯ�ӿ�
 */
public interface PeopleDao {
	/*
	 * ����People��Ϣ�ַ���
	 */
	public String getString(String idno);
	/*
	 * ����People����
	 */
	public People get(String idno);
}
