package com.terrylovesolar.hostsme.common;

/**
 * 常量类
 * @author Terry5
 *
 */
public class Constants {
	//label间距
	public static int LABEL_STRUCT_SIZE = 50;
	//CheckBox间距
	public static int CHECK_STRUCT_SIZE = 30;
	//按钮图标间距
	public static int ICON_STRUCT_SIZE = 30;
	
	//软件下载的Hosts文件
	public static String LOCAL_HOSTS_DIR = "./hosts";
	
	//下载的Hosts目录
	public static String LOCAL_HOSTS = "./hosts/hosts";
	
	//Hosts备份目录
	public static String BACKUP_DIR = "./backup";
	
	//Hosts系统位置
	public static String WINDOWS_HOSTS = "C:/Windows/System32/drivers/etc/hosts";
	public static String MAC_HOSTS = "/private/etc/hosts";
	public static String LINUX_HOSTS = "/etc/hosts";
	
	//Hosts源地址
//	public static String SOURCE_URL = "https://github.com/highsea/Hosts/blob/master/hosts";
	public static String SOURCE_URL = "https://github.com/PigVillage/Hosts/blob/master/hosts";
	
	//检测网络连通性的DNS服务器
	public static String DNS_SERVER = "223.5.5.5";
	
	//警告框图标-Restore
	public static String RESTORE_ICON = "./img/restore.png";

	//检查更新地址
	public static String UPDATE_URL = "http://re.terry5.com";
	
	//http头base64密文 upload_info
	public static String USER_AGENT_INFO = "bG92ZXNvbGFyNGV2ZXItaW5mbw==";
	
	//http头base64密文 update_check
		public static String USER_AGENT_UPDATE = "bG92ZXNvbGFyNGV2ZXItdXBkYXRl";
}
