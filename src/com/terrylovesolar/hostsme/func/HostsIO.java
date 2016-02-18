package com.terrylovesolar.hostsme.func;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Date;
import javax.swing.JTextArea;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.terrylovesolar.hostsme.common.Constants;



public class HostsIO {
	Constants constants = new Constants();
	
//	一定不能创建initCheck对象，否则会造成StackOverFlow异常
//	InitCheck check = new InitCheck();
	
	/**
	 * 创建Backup目录
	 * @return 如果存在备份文件返回True，不存在返回False
	 */
	public boolean ifBackup() {
		File backupFile = new File(constants.BACKUP_DIR + "/hosts.bak");
		File backupFileDir = new File(constants.BACKUP_DIR);
		if (backupFileDir.isDirectory()) {
			if (backupFile.exists()) {
				return true;
			} else {
				return false;
			}
		} else {
			backupFileDir.mkdir();
			return false;
		}
	}
	
	/**
	 * Backup当前Hosts文件
	 * @return 备份成功返回True，失败返回False
	 */
	public boolean makeBackup() {
		HostsIO hostsIO = new HostsIO();
		BufferedReader bufferedReader;
		String localHosts = hostsIO.get_hosts_path();
		try {
			bufferedReader = new BufferedReader(new FileReader(localHosts));
			FileOutputStream out = new FileOutputStream(constants.BACKUP_DIR + "/hosts.bak");
			BufferedOutputStream outB = new BufferedOutputStream(out);
			PrintStream ps = new PrintStream(outB);
			/**
			 * 定义ifCheckBakTimeStampExist变量，检测TimeStamp是否存在，不存在为0，存在为1
			 * 逐行读取Hosts文件，并检测此行是否为TimeStamp，如果是则将TimeStamp替换为最新时间
			 * 并将ifCheckBakTimeStampExist设置为1
			 * 如果不是则直接写入该行
			 */
			int ifCheckBakTimeStampExist = 0;
			for (String text = bufferedReader.readLine(); text != null; text = bufferedReader.readLine()) {
				if (ifCheckBakTimeStampExist == 0) {
					if (text.indexOf("# Backup TimeStamp") >= 0) {
						text = "# Backup TimeStamp: " + new Date().getTime();
						ps.print(text + "\r\n");
						ifCheckBakTimeStampExist = 1;
					} else {
						ps.print(text + "\r\n");
					}
				} else {
					ps.print(text + "\r\n");
				}
			}
			//以上循环结束后ifCheckBakTimeStampExist如果扔为0说明该文件第一次备份，在底部加上Backup TimeStamp
			if (ifCheckBakTimeStampExist == 0) {
				ps.print("# Backup TimeStamp: " + new Date().getTime() + "\r\n");
			}
			ps.close();
			outB.close();
			out.close();
			bufferedReader.close();
			return true;
		} catch (FileNotFoundException e) {
			return false;
		} catch (IOException e) {
			return false;
		}

	}
	
	
	/**
	 * 从本地备份文件恢复
	 * @return 恢复成功返回True，失败返回False
	 */
	public boolean restore_from_bak() {
		HostsIO hostsIO = new HostsIO();
		BufferedReader bufferedReader;
		String localHosts = hostsIO.get_hosts_path();
		try {
			bufferedReader = new BufferedReader(new FileReader(constants.BACKUP_DIR + "/hosts.bak"));
			FileOutputStream out = new FileOutputStream(localHosts);
			BufferedOutputStream outB = new BufferedOutputStream(out);
			PrintStream ps = new PrintStream(outB);
			int ifRestoreTimeStampExist = 0;
			for (String text = bufferedReader.readLine(); text != null; text = bufferedReader.readLine()) {
				if (ifRestoreTimeStampExist == 0) {
					if (text.indexOf("# Restore TimeStamp") >= 0) {
						text = "# Restore TimeStamp: " + new Date().getTime();
						ps.print(text + "\r\n");
						ifRestoreTimeStampExist = 1;
					} else {
						ps.print(text + "\r\n");
					}
				} else {
					ps.print(text + "\r\n");
				}
			}
			if (ifRestoreTimeStampExist == 0) {
				ps.print("# Restore TimeStamp: " + new Date().getTime() + "\r\n");
			}
			ps.close();
			outB.close();
			out.close();
			bufferedReader.close();
			return true;
		} catch (FileNotFoundException e) {

			return false;
		} catch (IOException e) {

			return false;
		}
		
	}
	
	
	/**
	 * 判断本地是否存在获取到的Hosts文件
	 * 判断本地是否存在Hosts目录，不存在则创建
	 * @return boolean
	 */
	
	public boolean ifHosts() {
		File hostsFile = new File(constants.LOCAL_HOSTS);
		File hostsFileDir = new File(constants.LOCAL_HOSTS_DIR);
		if (hostsFileDir.isDirectory()) {
			if (hostsFile.exists()) {
				return true;
			} else {
				return false;
			}
		} else {
			hostsFileDir.mkdir();
			return false;
		}
	}
	
	/**
	 * 读取当前Hosts文件并在HostsArea显示
	 * @param file Hosts文件
	 * @param hostsArea 显示的JTextArea
	 */
	public void read_local_hosts(File file, JTextArea hostsArea) {
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
			for (String text = bufferedReader.readLine(); text != null; text = bufferedReader.readLine()) {
				hostsArea.append(text + "\n");
			}
			bufferedReader.close();
		} catch (FileNotFoundException e) {

			hostsArea.setText("未找到Hosts文件！");
		} catch (IOException e) {

			hostsArea.setText("Hosts文件读取失败！");
		}
	}
	
	
	/**
	 * 将Github上的hosts文件写入本地保存
	 * 
	 */
	public void write_hosts() {
		Spider spider = new Spider();
		Document html = spider.get_source();
		HostsIO hostsIO = new HostsIO();
		if (!hostsIO.ifHosts()) {
			try {
				FileOutputStream out = new FileOutputStream(constants.LOCAL_HOSTS);
				BufferedOutputStream outB = new BufferedOutputStream(out);
				PrintStream ps = new PrintStream(outB);
				Elements content = spider.get_content(html);
				String time = spider.get_date(html);
				ps.print("# Updated " + time + "\r\n");
				for (Element element : content) {
					ps.print(element.text() + "\r\n");
				}
				ps.close();
				outB.close();
				out.close();
			} catch (FileNotFoundException e) {
	
				System.out.println("文件未找到！");
			} catch (IOException e) {
	
				System.out.println("Hosts文件保存出错！！");
			} 
		}
	}
	
	
	/**
	 * 分割Hosts文件
	 * @param hostName 需要分割的服务名称，如：Google
	 */
	public void spiltHosts(String hostName) {
		File file = new File(constants.LOCAL_HOSTS);
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
			FileOutputStream out = new FileOutputStream(constants.LOCAL_HOSTS_DIR + "/" + hostName);
			BufferedOutputStream outB = new BufferedOutputStream(out);
			PrintStream ps = new PrintStream(outB);
			/**
			 * 定义ifFindName变量，初始为0，说明此时未找到该字段
			 * 逐行读取Hos文件，找到需要分割的服务Start时（如Google）开始写入，ifFindName变为1
			 * ifFindName=1时，开始写入之后的每一行，直到遇到End字段，Break跳出循环
			 * 
			 */
			int ifFindName = 0;
			for (String text = bufferedReader.readLine(); text != null; text = bufferedReader.readLine()) {
				if (ifFindName == 0) {
					if (text.toLowerCase().indexOf(hostName + " start") >= 0) {
						ps.print(text + "\r\n");
						ifFindName = 1;
					}
				} else if (text.toLowerCase().indexOf(hostName + " end") >= 0) {
					ps.print(text);
					break;
				} else {
					ps.print(text + "\r\n");
				}
			}
			ps.close();
			outB.close();
			out.close();
			bufferedReader.close();
		} catch (FileNotFoundException e) {

		} catch (IOException e) {

		}
	}
	
	public void update_hosts(String hostName) {
		HostsIO hostsIO = new HostsIO();
//		File hosts = new File(constants.LOCAL_HOSTS_DIR + File.separator + hostName);
		File hosts = new File(constants.LOCAL_HOSTS);
		File localHosts = new File(hostsIO.get_hosts_path());
		try {
			//读取需要修改的Hosts文件
			BufferedReader hostsReader = new BufferedReader(new FileReader(hosts));
			//读取系统Hosts文件
			BufferedReader localHostsReader = new BufferedReader(new FileReader(localHosts));
			//实例化系统Hosts写入流
			FileOutputStream out = new FileOutputStream(constants.LOCAL_HOSTS + "1");
			BufferedOutputStream outB = new BufferedOutputStream(out);
			PrintStream ps = new PrintStream(outB);
			int ifFind = 0;
			for (String text = hostsReader.readLine(); text != null; text = hostsReader.readLine()) {
				if (ifFind == 0) {
					if (text.toLowerCase().indexOf(hostName + " start") >= 0) {
						ifFind = 1;
						continue;
					}else {
						ps.println(text);
					}
				} else if (text.toLowerCase().indexOf(hostName + " end") >= 0) {
					ifFind = 0;
					continue;
				} else {
					continue;
				}
			}
			ps.close();
			outB.close();
			out.close();
			hostsReader.close();
			localHostsReader.close();
			
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}


	}
	
	
	
	/**
	 * 读取指定的Hosts文件
	 * @param hostName
	 * @return BufferedReader 返回一个文件读取缓冲流
	 */
	public BufferedReader read_spilt_hosts(String hostName) {
		File hosts = new File(constants.LOCAL_HOSTS_DIR +File.separator + hostName);
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(hosts));
			return bufferedReader;
		} catch (FileNotFoundException e) {
			
		} catch (IOException e) {
			
		}
		return null;
	}
	
	
	/**
	 * 删除已经存在的Hosts文件
	 * @return 删除成功返回True，失败返回False
	 */
	public boolean deleteHosts() {
		File file = new File(constants.LOCAL_HOSTS);
		if (file.delete()) {
			return true;
		}
		return false;
	}
	
	/**
	 * 获取本地Hosts文件的更新日期
	 * @return 返回日期字符串
	 */
	public String get_local_time() {
		File hosts = new File(constants.LOCAL_HOSTS);
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(hosts));
			for (String text = bufferedReader.readLine(); text != null; text = bufferedReader.readLine()) {
				if (text.indexOf("# Updated") >= 0) {
					return text.toString().replace("# ", "");
				}
			}
		} catch (FileNotFoundException e) {

			return "本地文件未找到";
		} catch (IOException e) {

			return "本地文件读取失败";
		}
		return constants.LOCAL_HOSTS;
		
	}
	
	/**
	 * 获取当前系统下的Hosts路径
	 * @return localHosts 系统Hosts路径
	 */
	public String get_hosts_path() {
		String localHosts = null;
		String os = System.getProperty("os.name").toLowerCase();
		if (os.indexOf("win") >= 0) {
			localHosts = constants.WINDOWS_HOSTS;
		} else if (os.indexOf("mac") >= 0) {
			localHosts = constants.MAC_HOSTS;
		} else if (os.indexOf("linux") >= 0) {
			localHosts = constants.LINUX_HOSTS;
		}
		
		return localHosts;
	}

	
	public static void main(String[] args) {
		HostsIO hostsIO = new HostsIO();
		hostsIO.update_hosts("twitter");
		System.out.println("Done!");
	}
}
