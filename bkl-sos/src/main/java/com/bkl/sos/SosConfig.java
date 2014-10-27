package com.bkl.sos;

import com.km.common.config.Config;

public class SosConfig {
	private static  boolean isCnVersion;
	private static  boolean enableEmailActive;
	
	static {
		try {
			flush();
			startService();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void startService() throws Exception {
		
	}
	
	private static void flush() {
		isCnVersion = "cn".equals(Config.getString("config.product.ver"));
		enableEmailActive = Boolean.parseBoolean(Config.getString("config.email.active.enable")); 
	}
	public static boolean isCnVersion() {
		return isCnVersion;
	}
	public static boolean enableEmailActive() {
		return enableEmailActive;
	}
}
