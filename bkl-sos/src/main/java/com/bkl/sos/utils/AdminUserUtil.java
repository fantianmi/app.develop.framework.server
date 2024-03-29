package com.bkl.sos.utils;

import javax.servlet.http.HttpServletRequest;

import com.bkl.sos.entity.AdminUser;
import com.bkl.sos.entity.User;
import com.bkl.sos.service.UserService;
import com.bkl.sos.service.impl.UserServiceImpl;
import com.km.common.dao.DaoFactory;
import com.km.common.dao.GeneralDao;

public class AdminUserUtil {
	public static AdminUser getCurrentUser(HttpServletRequest request) {
		
		String username = (String)request.getSession(true).getAttribute("adminusername");
		AdminUser user = null;
		if (username != null) {
			GeneralDao<AdminUser> dao = DaoFactory.createGeneralDao(AdminUser.class);
			user = dao.find("username", username);;
		}
		return user;
	}
	
	public static boolean islogin(HttpServletRequest request) {
		String username = (String)request.getSession(true).getAttribute("adminusername");
		if (username != null) {
			return true;
		}
		return false;
	}
	
	
}
