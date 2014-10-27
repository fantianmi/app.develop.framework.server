package com.bkl.sos.servlet;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





import com.bkl.sos.entity.User;
import com.bkl.sos.service.UserService;
import com.bkl.sos.service.impl.UserServiceImpl;
import com.km.common.dao.DaoFactory;
import com.km.common.dao.GeneralDao;
import com.km.common.servlet.CommonServlet;
import com.km.common.utils.ServletUtil;
import com.km.common.vo.Page;
import com.km.common.vo.PageReply;

public class AdminServlet extends CommonServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 激活用户
	 * TODO 重复激活
	 */
	public void activeUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long userId = Long.parseLong(request.getParameter("id"));
		GeneralDao<User> userDao = DaoFactory.createGeneralDao(User.class);
		User user = userDao.find(userId);
		if (user != null) {
			user.setEmail_validated(1);
			userDao.update(user);
		}
		ServletUtil.writeOkCommonReply("", response);
	}
	/**
	 * 禁用/启用用户
	 * TODO 重复禁用/启用
	 */
	public void frozen(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long userId = Long.parseLong(request.getParameter("id"));
		int frozen = Integer.parseInt(request.getParameter("frozen"));
		GeneralDao<User> userDao = DaoFactory.createGeneralDao(User.class);
		User user = userDao.find(userId);
		if (user != null && frozen != user.getFrozen()) {
			user.setFrozen(frozen);
			userDao.update(user);
		}
		ServletUtil.writeOkCommonReply("", response);
	}
	
	/**
	 * 确认/否定实名验证
	 * status=1表示确认;status=2表示否定
	 */
	public void confirmRealName(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long userId = Long.parseLong(request.getParameter("id"));
		int status = Integer.parseInt(request.getParameter("status"));
		GeneralDao<User> userDao = DaoFactory.createGeneralDao(User.class);
		User user = userDao.find(userId);
		if (user != null && user.getRealname_validated() == 0) {
			user.setRealname_validated(status);
			userDao.update(user);
		}
		ServletUtil.writeOkCommonReply("", response);
	}
		
	
	/***
	 * 查询用户
	 */
	public void getUserDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long id = 0;
		try {
			id = Long.parseLong(request.getParameter("id"));
		} catch (NumberFormatException e) {
			e.fillInStackTrace();
		}
		GeneralDao<User> userDao = DaoFactory.createGeneralDao(User.class);
		User user = null;
		if (id > 0) {
			user = userDao.find(id);
		}
		ServletUtil.writeOkCommonReply(user, response);
	}	
	
	/***
	 * 分页查询用户
	 */
	public void getUserList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map searchMap = ServletUtil.getSearchMap(request);
		UserService userSrv = new UserServiceImpl();
		Page page = ServletUtil.getPage(request);
		PageReply<User> users = userSrv.findUser(searchMap, page);
		ServletUtil.writeOkCommonReply(users, response);
	}	
}
