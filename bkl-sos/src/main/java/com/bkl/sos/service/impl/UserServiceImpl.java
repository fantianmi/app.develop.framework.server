package com.bkl.sos.service.impl;

import java.util.Map;

import com.bkl.sos.SosConfig;
import com.bkl.sos.entity.User;
import com.bkl.sos.service.UserService;
import com.km.common.dao.DaoFactory;
import com.km.common.dao.GeneralDao;
import com.km.common.utils.DbUtil;
import com.km.common.utils.MD5Util;
import com.km.common.vo.Condition;
import com.km.common.vo.Page;
import com.km.common.vo.PageReply;
import com.km.common.vo.RetCode;

public class UserServiceImpl implements UserService {
	GeneralDao<User> dao = DaoFactory.createGeneralDao(User.class);
	public UserServiceImpl() {
		
	}

	@Override
	public Long save(User user) {
		return dao.save(user);
	}
	
	@Override
	public Long createUser(User user) {
		user.saveMD5Password(user.getPassword());
		
		if (!exist(user.getEmail())) {
			Long id = save(user);
			return id;
		}
		return 0L;
	}
	
	@Override
	public User get(long id) {
		return dao.find("id", id);
	}
	
	@Override
	public User find(String email) {
		return dao.find("email", email);
	}

	public User findByMobile(String mobile){
		Condition mobileCon = DbUtil.generalEqualWhere("mobile", mobile);
		return dao.find(new Condition[]{mobileCon}, null);
	}
	
	@Override
	public User findByBtcAddr(String btcAddr) {
		return dao.find("btc_in_addr", btcAddr);
	}

	@Override
	public User findByLtcAddr(String btcAddr) {
		return dao.find("ltc_in_addr", btcAddr);
	}
	@Override
	public RetCode login(String email, String password) {
		User user = find(email);
		if (user == null) {
			return RetCode.USERNAME_OR_PASSWORD_ERROR;
		}
		if (user.getEmail_validated() == 0 && !SosConfig.isCnVersion()) {
			return RetCode.USER_NOT_ACTIVE;
		}
		if (user.getPassword().equals(MD5Util.md5(password))) {
			return RetCode.OK;
		} 
		return RetCode.USERNAME_OR_PASSWORD_ERROR;
	}

	@Override
	public boolean exist(String username) {
		User user = find(username);
		return user != null;
	}
	
	public boolean existMobile(String mobile){
		User user = this.findByMobile(mobile);
		return user!=null;
	}
	
	
	@Override
	public PageReply<User> findUser(Map searchMap, Page page) {
		GeneralDao<User> dao = DaoFactory.createGeneralDao(User.class);
		PageReply<User> users = dao.getPage(page, searchMap);
		return users;
	}
	

	public User findPin(String pin) {
		Condition pinCon=DbUtil.generalEqualWhere("pin", pin);
		GeneralDao<User> userDao = DaoFactory.createGeneralDao(User.class);
		return userDao.find(new Condition[]{pinCon}, null);
	}

	@Override
	public long modifyUser(User user, long id) {
		GeneralDao<User> userDao = DaoFactory.createGeneralDao(User.class);
		return userDao.update(user, id);
	}
	


}
