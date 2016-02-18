package com.terrylovesolar.hostsme.func;

import java.util.Properties;

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
	Constants constants = new Constants();
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
		

	}
	
	/**
	 * 下载按钮功能实现
	 */
	public void downloadBtn() {
		if (hostsIO.ifHosts()) {
			if (hostsIO.deleteHosts()) {
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
					"\n\n正在备份本地Hosts...$", 
					"\n\n正在备份本地Hosts...  Done!"));
		} else {
			mainWindow.progressArea.setText(mainWindow.progressArea.getText().replaceAll(
					"\n\n正在备份本地Hosts...$", 
					"\n\n正在备份本地Hosts...  失败!"));
		}

		
	}
	
	
	/**
	 * 恢复按钮功能实现
	 */
	public void restoreBtn() {
		ImageIcon icon = new ImageIcon(constants.RESTORE_ICON);
		if (hostsIO.ifBackup()) {
			int option = JOptionPane.showConfirmDialog(null, "是否恢复Backup文件夹中的备份", "确认恢复？", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icon);
			if (option == 0) {
				mainWindow.progressArea.append("\n\n正在恢复本地备份Hosts...");
				if (hostsIO.restore_from_bak()) {
					mainWindow.progressArea.setText(mainWindow.progressArea.getText().replaceAll(
							"\n\n正在恢复本地备份Hosts...$", 
							"\n\n正在恢复本地备份Hosts...  Done!"));
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
