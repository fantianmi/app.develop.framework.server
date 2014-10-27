package com.bkl.sos.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import com.bkl.sos.entity.User;
import com.bkl.sos.service.UserService;
import com.bkl.sos.service.impl.UserServiceImpl;
import com.bkl.sos.utils.UserUtil;
import com.km.common.config.Config;
import com.km.common.dao.DaoFactory;
import com.km.common.dao.GeneralDao;
import com.km.common.servlet.CommonServlet;
import com.km.common.utils.MD5Util;
import com.km.common.utils.ServletUtil;
import com.km.common.utils.ValidUtils;
import com.km.common.vo.RetCode;

public class UserServlet extends CommonServlet {
	
	/**
	 * 获得当前登陆用户信息
	 */
	public void get(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = UserUtil.getCurrentUser(request);
		ServletUtil.writeOkCommonReply(user, response);
	}
	
	/**
	 * 修改呢称
	 */
	public void modifyNickname(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int uid=Integer.parseInt(request.getParameter("uid"));
		UserService userServ = new UserServiceImpl();
		
		User user = userServ.get(uid);
		String nick_name = request.getParameter("nick_name");
		
		// 参数错误
		if(StringUtils.isBlank(nick_name)) {
			// TODO returns retcode
			return;
		}
		
		user.setNick_name(nick_name);
		
		UserService userSrv = new UserServiceImpl();
		userSrv.save(user);
		ServletUtil.writeOkCommonReply(user, response);
	}
	
	/**
	 * 修改交易密码
	 */
	public void modifyPwd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserService userServ = new UserServiceImpl();
		int uid=Integer.parseInt(request.getParameter("uid"));
		User user = userServ.get(uid);
		//如果不包含密码说明是用QQ和微博登陆注册，则可以绑定邮箱，然后设置密码
		if(user.getPassword()==null){
			ServletUtil.writeCommonReply(null, RetCode.USER_PASSWORD_NOT_SET, response);
		}
		String originPwd = StringUtils.trim(request.getParameter("originPwd"));
		String newPwd = StringUtils.trim(request.getParameter("newPwd"));
		
		if (!StringUtils.isEmpty(user.getPassword())) {
			if (StringUtils.isEmpty(originPwd)) {
				ServletUtil.writeCommonReply(null, RetCode.ORGINAL_PASSWORD_EMPTY, response);
				return;
			}
			if (!user.checkPassword(originPwd)) {
				ServletUtil.writeCommonReply(null, RetCode.ORGINAL_PASSWORD_ERROR, response);
				return;
			}
		}
		if (newPwd.length() < 6) {
			ServletUtil.writeCommonReply(null, RetCode.PASSWORD_ILLEGAL, response);
			return;
		}
		user.saveMD5Password(newPwd);
		GeneralDao<User> userDao = DaoFactory.createGeneralDao(User.class);
		userDao.update(user);
		ServletUtil.writeCommonReply(null, RetCode.OK, response);
	}
	
	public void bindingEmail(HttpServletRequest request,HttpServletResponse response) throws IOException{
		UserService userServ = new UserServiceImpl();
		long id=Integer.parseInt(request.getParameter("id"));
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		RetCode ret=RetCode.OK;
		if (userServ.exist(email)) {
			ret = RetCode.USER_EXIST;
			ServletUtil.writeCommonReply(null, ret, response);
			return;
		} else if (!ValidUtils.emailFormat(email)) {
			ret = RetCode.EMAIL_ERROR;
			ServletUtil.writeCommonReply(null, ret, response);
			return;
		}
		User user = userServ.get(id);
		user.setEmail(email);
		user.saveMD5Password(password);
		GeneralDao<User> userDao = DaoFactory.createGeneralDao(User.class);
		userDao.update(user);
		ServletUtil.writeOkCommonReply(user, response);
	}
	
	/**
	 * 设置头像
	 * @throws IOException 
	 * @throws FileUploadException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public void modifyHeadIcon(HttpServletRequest request,HttpServletResponse response) throws Exception{
		int uid=Integer.parseInt(request.getParameter("uid"));
		UserService userServ = new UserServiceImpl();
		User user = userServ.get(uid);
		//如果有文件字段有内容即可修改
		String uploadFileURL=request.getParameter("uploadFileURL");
		user.setHeadIcon(uploadFileURL);
		userServ.modifyUser(user, uid);
		ServletUtil.writeOkCommonReply(user, response);
	}
	
	/**
	 * 设置真实姓名和卡号
	 */
	public void validateIdentity(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String picHome = request.getServletContext().getRealPath("/pic/user-identity");
		String absoluteUploadDir = picHome;
		if (!new File(picHome).isAbsolute()) {
			absoluteUploadDir = request.getServletContext().getRealPath(picHome);
		}
		if (!new File(absoluteUploadDir).isDirectory()) {
			com.km.common.utils.IOUtils.createDirRecursive(new File(absoluteUploadDir));
		}
		User user = UserUtil.getCurrentUser(request);
		
		// SESSION超时或未登陆
		if (null == user) {
			response.sendRedirect("/userinfo.jsp");
			return;
		}
		
		DiskFileItemFactory factory = new DiskFileItemFactory();  
		ServletFileUpload upload = new ServletFileUpload(factory); 
		List<FileItem> list = upload.parseRequest(request);  
		Map params = new HashMap();
		for (FileItem item : list) {
			if (!item.isFormField()) {
				//String fileType = item.getName().substring(item.getName().lastIndexOf("."));
				InputStream in = item.getInputStream();
				OutputStream os = new FileOutputStream(new File(picHome, user.getId() + ".jpg"));
				IOUtils.copy(in, os);
				os.flush();
				IOUtils.closeQuietly(os);
				IOUtils.closeQuietly(in);
			} else {
				 String name = item.getFieldName();
                 String value = item.getString("UTF-8");
                 params.put(name, value);
			}
		}
		
		User user2 = new User();
		BeanUtils.populate(user2, params);
		
		user.setName(user2.getName());
		user.setIdentity_type(user2.getIdentity_type());
		user.setIdentity_no(user2.getIdentity_no());
		user.setRealname_validated(0);//提交审批的固定是0的状态
		// 更新用户信息
		UserService userSrv = new UserServiceImpl();
		userSrv.save(user);
		response.sendRedirect("/userinfo.jsp");
		// ServletUtil.writeCommonReply(null, ret, response);
	}
	
	/**
	 * 设置人民币提现地址
	 */
	public void modifyRmbWithdrawAddr(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = UserUtil.getCurrentUser(request);
		// SESSION超时或未登陆
		if (null == user) {
			// TODO returns retcode
		}

		String bank = request.getParameter("bank");
		String bank_account = request.getParameter("bank_account");
		String bank_number = request.getParameter("bank_number");
		String mobile = request.getParameter("mobile");
		
		// 参数错误
		if(StringUtils.isBlank(bank) || StringUtils.isBlank(bank_account)) {
			// TODO returns retcode
		}
		
		// TODO 使用验证码?
		
		user.setBank(bank);
		user.setBank_account(bank_account);
		user.setBank_number(bank_number);
		UserService userSrv = new UserServiceImpl();
		userSrv.save(user);
		
		Map data = new HashMap();
		data.put("bank", bank);
		data.put("bank_account", bank_account);
		ServletUtil.writeCommonReply(data, RetCode.OK, response);
	}
	
	/**
	 * 设置X币提现地址
	 */
	public void modifyCoinWithdrawAddr(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = UserUtil.getCurrentUser(request);
		// SESSION超时或未登陆
		if (null == user) {
			// TODO returns retcode
		}

		String addr = request.getParameter("addr");
		String type = request.getParameter("type");
		
		// 参数错误
		if(StringUtils.isBlank(addr) || StringUtils.isBlank(type)) {
			// TODO returns retcode
		}
		
		
		UserService userSrv = new UserServiceImpl();
		userSrv.save(user);
		
		Map data = new HashMap();
		data.put("addr", addr);
		ServletUtil.writeCommonReply(data, RetCode.OK, response);
	}
	
}
