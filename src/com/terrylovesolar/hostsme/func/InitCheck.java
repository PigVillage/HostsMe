package com.terrylovesolar.hostsme.func;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JTextArea;

import com.terrylovesolar.hostsme.common.Constants;

/**
 * 窗体初始化参数设置
 * @author Terry5
 */

public class InitCheck {
	Constants constants = new Constants();
	HostsIO hostsIO = new HostsIO();
	Spider spider = new Spider();
	/**
	 * 检测网络连通性，host为阿里云公共DNS服务器
	 * @return boolean
	 */
	public boolean netCheck() {
		try {
			InetAddress address = InetAddress.getByName(constants.DNS_SERVER);
			if (address.isReachable(6666)) {
				return true;
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 检测当前操作系统
	 * @return osString 操作系统名称
	 */
	public String osCheck() {
		String osString;
		try {
			osString = System.getProperty("os.name");
			return osString;	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "未知";
		}
	}

	/**
	 * 读取本地hosts文件,并写入hostsArea
	 * @param hostsArea JTextArea对象
	 */
	public void read_hosts(JTextArea hostsArea) {
		InitCheck check = new InitCheck();
		String os = check.osCheck().toLowerCase();
			if (os.indexOf("win") >= 0) {
				File file = new File(constants.WINDOWS_HOSTS);
				check.writeHostsArea(file, hostsArea);
			} else if (os.indexOf("mac") >= 0) {
				File file = new File(constants.MAC_HOSTS);
				check.writeHostsArea(file, hostsArea);
			} else if (os.indexOf("linux") >= 0) {
				File file = new File(constants.LINUX_HOSTS);
				check.writeHostsArea(file, hostsArea);
			}
	}
	
	/**
	 * 向hostsArea写入当前本地Hosts内容
	 * @param file hosts文件路径
	 * @param hostsArea 写入的JTextArea
	 */
	public void writeHostsArea(File file, JTextArea hostsArea) {
		hostsIO.read_local_hosts(file, hostsArea);
	}
	
	/**
	 * 初始化检测本地Hosts是否存在
	 * @return 如果Hosts存在返回True，不存在返回False
	 */
	public boolean checkIfHosts() {
		if (hostsIO.ifHosts()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 分割Hosts文件
	 * 调用HostsIO类中的spiltHosts方法
	 */
	public void spiltHost() {
		InitCheck initCheck = new InitCheck();
		if (initCheck.checkIfHosts()) {
			hostsIO.spiltHosts("google");
			hostsIO.spiltHosts("twitter");
			hostsIO.spiltHosts("github");
			hostsIO.spiltHosts("facebook");
		}
	}
	
	
	public String update_check() {
		String info =  spider.update_check();
		return info;
	}
	
	public void upload_user_info() {
		spider.upload_user_info();
	}
}
