package com.test;

import java.sql.SQLException;

import com.alibaba.fastjson.JSON;
import com.bean.dao.UserDao;
import com.bean.po.User;


public class FastJsonTest{
	public static void main(String[] args) throws SQLException {
		UserDao userdao = new UserDao();
		User user = userdao.getUsers().get(0);
		
		String userStr = JSON.toJSONString(user);
		System.out.println(userStr);
	}

	
}
