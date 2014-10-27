package com.bkl.sos.entity;

import org.apache.commons.lang.StringUtils;

import com.bkl.sos.SosConfig;
import com.bkl.sos.utils.DoubleUtil;
import com.km.common.dao.TableAonn;
import com.km.common.utils.MD5Util;
import com.km.common.utils.TimeUtil;

@TableAonn(tableName = "user")
public class User {
	private long id;
	/***
	 * 邮箱.作为帐号登陆
	 */
	private String email = "";
	private String password = "";
	/***
	 * 注册IP
	 */
	private String reg_ip = "";
	/**
	 * qq登陆 微博登陆唯一标识
	 */
	private String pin = "";
	/**
	 * 用户姓名
	 */
	private String name = "";
	
	/**
	 * 用户呢称
	 */
	private String nick_name = "";
	
	/**
	 * 证件类型
	 */
	private int identity_type;
	/**
	 * 手机号码
	 */
	private String mobile;
	
	/**
	 * 证件号
	 */
	private String identity_no;
	
	/**
	 * 创建时间
	 */
	private long ctime;
	
	/**
	 * 人民币金额
	 */
	private double rmb;
	
	/**
	 * 人民币冻结部分金额
	 */
	private double rmb_frozen;
	
	
	private int email_validated ;
	private int realname_validated;
	private int mobile_validated;
	private String secret = "";
	private String address = "";
	private float received;
	private String headIcon="";
	

	public int getMobile_validated() {
		return mobile_validated;
	}

	public void setMobile_validated(int mobile_validated) {
		this.mobile_validated = mobile_validated;
	}

	/**
	 * 行号：行号类似卡号，是每家营业网点的代码，用于提现转账减少手续费
	 */
	private String bank_number;
	
	/***
	 * 银行名称
	 */
	private String bank;
	
	/***
	 * 银行卡号
	 */
	private String bank_account;
	
	
	private int frozen;
	
	private long passwd_modify_overtime;
	
	
	
	public String getHeadIcon() {
		return headIcon;
	}

	public void setHeadIcon(String headIcon) {
		this.headIcon = headIcon;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void saveMD5Password(String password) {
		this.password = MD5Util.md5(password);
	}
	
	public void saveMD5SecretPassword(String password) {
		this.secret = MD5Util.md5(password);
	}
	
	/***
	 * 登陆密码检验
	 * @param password
	 * @return
	 */
	public boolean checkPassword(String password) {
		if (StringUtils.isBlank(password)) {
			return false;
		}
		return MD5Util.md5(password).equals(this.password);
	}
	
	/***
	 * 交易密码检验
	 * @param password
	 * @return
	 */
	public boolean checkSecretPassword(String password) {
		if (StringUtils.isBlank(password)) {
			return false;
		}
		return MD5Util.md5(password).equals(this.secret);
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getReg_ip() {
		return reg_ip;
	}

	public void setReg_ip(String reg_ip) {
		this.reg_ip = reg_ip;
	}

	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	public long getCtime() {
		return ctime;
	}
	public void setCtime(long ctime) {
		this.ctime = ctime;
	}
	public double getRmb() {
		return rmb;
	}
	public void setRmb(double rmb) {
		this.rmb = DoubleUtil.formatDouble(rmb);
	}
	public double getRmb_frozen() {
		return rmb_frozen;
	}
	public void setRmb_frozen(double rmb_frozen) {
		this.rmb_frozen = DoubleUtil.formatDouble(rmb_frozen);
	}

	public int getEmail_validated() {
		return email_validated;
	}
	public void setEmail_validated(int email_validated) {
		this.email_validated = email_validated;
	}

	public int getRealname_validated() {
		return realname_validated;
	}

	public void setRealname_validated(int realname_validated) {
		this.realname_validated = realname_validated;
	}

	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public float getReceived() {
		return received;
	}
	public void setReceived(float received) {
		this.received = received;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getBank_account() {
		return bank_account;
	}

	public void setBank_account(String bank_account) {
		this.bank_account = bank_account;
	}


	public int getIdentity_type() {
		return identity_type;
	}

	public void setIdentity_type(int identity_type) {
		this.identity_type = identity_type;
	}

	public String getIdentity_no() {
		return identity_no;
	}

	public void setIdentity_no(String identity_no) {
		this.identity_no = identity_no;
	}

	public int getFrozen() {
		return frozen;
	}

	public void setFrozen(int frozen) {
		this.frozen = frozen;
	}
	
	public String getCtimeString() {
		return TimeUtil.fromUnixTime(ctime);
	}

	public long getPasswd_modify_overtime() {
		return passwd_modify_overtime;
	}

	public void setPasswd_modify_overtime(long passwd_modify_overtime) {
		this.passwd_modify_overtime = passwd_modify_overtime;
	}

	public String getBank_number() {
		return bank_number;
	}

	public void setBank_number(String bank_number) {
		this.bank_number = bank_number;
	}
}