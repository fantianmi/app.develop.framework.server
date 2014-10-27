package com.bkl.sos.helper;

import java.util.List;

import com.bkl.sos.entity.User;
import com.bkltech.framework.multicoin.message.Transaction;
import com.km.common.dao.DaoFactory;
import com.km.common.dao.GeneralDao;

public class TransactionHelper {

	public static void loadTransaction(String account) {
		GeneralDao<User> userDao = DaoFactory.createGeneralDao(User.class);
		User user = userDao.find("email", account);

		if (user != null) {
		}
	}
}
