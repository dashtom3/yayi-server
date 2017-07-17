package com.yayiabc.common.listener;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import com.yayiabc.common.utils.CheckIsSignUtils;

public class SessionListener implements HttpSessionAttributeListener{

	@Override
	public void attributeAdded(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub
		CheckIsSignUtils checkIsSignUtils=CheckIsSignUtils.getInstance();	
		ArrayList<String> list=checkIsSignUtils.getList();
		list.add((String) se.getValue());
		System.out.println(se.getName()+" "+se.getValue());
		System.out.println("登录成功list:"+list);
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub
		CheckIsSignUtils checkIsSignUtils=CheckIsSignUtils.getInstance();	
		ArrayList<String> list=checkIsSignUtils.getList();
		list.remove(se.getValue());
		System.out.println(se.getName()+" "+se.getValue());
		System.out.println("2分钟后list:"+list);
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub
		//....
	}

}
