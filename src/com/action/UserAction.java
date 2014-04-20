package com.action;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.bean.dao.UserDao;
import com.bean.po.User;
import com.createJavaFile.createModel.SqlColumn;
import com.wll7821.filter.WebContext;

public class UserAction {
	
	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	private User user;
	public void setUser(User user) {
		this.user = user;
	}
	
	private String password;
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String register(){
		HttpServletRequest request = WebContext.getRequest();
		if( user.getPassword().equals(password) ){
			try {
				userDao.save(user);
			} catch (SQLException e) {
				request.setAttribute("error", "输入不合法，请重新填写！");
			}
		}else{
			request.setAttribute("error", "两次输入密码不一致，请重新填写！");
		}
		return "jsonp";
	}
	
	public String login(){
		HttpServletRequest request = WebContext.getRequest();
		HttpSession session = WebContext.getSession();
		
		if( user.getName().length() > 0 && user.getPassword().length() > 0  ){
			SqlColumn nameColumn =  new SqlColumn("name", user.getName());
			SqlColumn passwordColumn =  new SqlColumn("password", user.getPassword());
			
			try {
				List<?> list = userDao.getUsers(nameColumn,passwordColumn);
				if( list.isEmpty() ){
					request.setAttribute("error", "用户名或密码错误！");
				}else{
					session.setAttribute("user", list.get(0));
					request.setAttribute("success", true);
				}
			} catch (SQLException e) {
				request.setAttribute("error", "用户名或密码错误！");
			}
		}else{
			request.setAttribute("error", "请填写用户名和密码");
		}
		return "jsonp";
	}
	
	public String logout(){
		HttpSession session = WebContext.getSession();
		
		session.removeAttribute("user");
		
		return "jsonp";
	}
	public String islogin(){
		return "jsonp";
	}
	
}
