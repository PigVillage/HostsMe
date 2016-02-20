package com.terrylovesolar.hostsme.func;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.terrylovesolar.hostsme.common.CommonFunc;
import com.terrylovesolar.hostsme.common.Constants;
import com.terrylovesolar.hostsme.main.MainWindow;


/**
 * 按钮功能实现
 * @author Terry5
 *
 */
public class buttonFunc {
	MainWindow mainWindow;
	HostsIO hostsIO = new HostsIO();
	InitCheck initCheck = new InitCheck();
	CommonFunc commonFunc = new CommonFunc();
	String n = System.getProperty("line.separator");
	
	public buttonFunc(MainWindow mWindow) {
		mainWindow = mWindow;
		commonFunc.scrollToBottom(mainWindow.progressArea);
	}
	
	
	/**
	 * 开始按钮功能实现
	 */
	public void startBtn() {
		mainWindow.progressArea.append(n+n+"替换Hosts开始执行...");
		if (mainWindow.google.isSelected()) {
			hostsIO.update_hosts("google");
			hostsIO.append_hosts("google");
			hostsIO.copy_hosts(hostsIO.get_hosts_path() + "1", hostsIO.get_hosts_path());
		}
		if (mainWindow.twitter.isSelected()) {
			hostsIO.update_hosts("twitter");
			hostsIO.append_hosts("twitter");
			hostsIO.copy_hosts(hostsIO.get_hosts_path() + "1", hostsIO.get_hosts_path());
		}
		if (mainWindow.github.isSelected()) {
			hostsIO.update_hosts("github");
			hostsIO.append_hosts("github");
			hostsIO.copy_hosts(hostsIO.get_hosts_path() + "1", hostsIO.get_hosts_path());
		}
		if (mainWindow.facebook.isSelected()) {
			hostsIO.update_hosts("facebook");
			hostsIO.append_hosts("facebook");
			hostsIO.copy_hosts(hostsIO.get_hosts_path() + "1", hostsIO.get_hosts_path());
		}
		
		hostsIO.deleteHosts(hostsIO.get_hosts_path() + "1");
		mainWindow.progressArea.setText(mainWindow.progressArea.getText().replaceAll(
				n+n+"替换Hosts开始执行...$", 
				n+n+"替换Hosts开始执行...  Done!"));
		String selectedHosts = mainWindow.hostsSelected.getText().replaceAll("\n", "  ");
		mainWindow.progressArea.append(selectedHosts.replaceAll("添加的Hosts：", "\n") + "已经成功添加/替换");
		//替换Hosts后刷新显示区
		initCheck.read_hosts(mainWindow.hostsArea);
	}
	
	/**
	 * 下载按钮功能实现
	 */
	public void downloadBtn() {
		if (hostsIO.ifHosts()) {
			if (hostsIO.deleteHosts(Constants.LOCAL_HOSTS)) {
				hostsIO.write_hosts();
			};
		} else {
			hostsIO.write_hosts();
		}
		
	}
	
	
	/**
	 * 备份按钮功能实现
	 */
	public void backupBtn() {
		hostsIO.ifBackup();
		if (hostsIO.makeBackup()) {
			mainWindow.progressArea.setText(mainWindow.progressArea.getText().replaceAll(
					n+n+"正在备份本地Hosts...$", 
					n+n+"正在备份本地Hosts...  Done!"));
		} else {
			mainWindow.progressArea.setText(mainWindow.progressArea.getText().replaceAll(
					n+n+"正在备份本地Hosts...$", 
					n+n+"正在备份本地Hosts...  失败!"));
		}

		
	}
	
	
	/**
	 * 恢复按钮功能实现
	 */
	public void restoreBtn() {
		ImageIcon icon = new ImageIcon(Constants.RESTORE_ICON);
		if (hostsIO.ifBackup()) {
			int option = JOptionPane.showConfirmDialog(null, "是否恢复Backup文件夹中的备份", "确认恢复？", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icon);
			if (option == 0) {
				mainWindow.progressArea.append("\n\n正在恢复本地备份Hosts...");
				if (hostsIO.restore_from_bak()) {
					mainWindow.progressArea.setText(mainWindow.progressArea.getText().replaceAll(
							"\n\n正在恢复本地备份Hosts...$", 
							"\n\n正在恢复本地备份Hosts...  Done!"));
					//替换Hosts后刷新显示区
					initCheck.read_hosts(mainWindow.hostsArea);
				} else {
					mainWindow.progressArea.setText(mainWindow.progressArea.getText().replaceAll(
							"\n\n正在恢复本地备份Hosts...$", 
							"\n\n正在恢复本地备份Hosts...  失败!"));
				}
			}
		} else {
			mainWindow.progressArea.append("\n\n未找到备份文件！！");
		}

	}
}
