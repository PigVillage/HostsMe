package com.terrylovesolar.hostsme.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.terrylovesolar.hostsme.func.buttonFunc;
import com.terrylovesolar.hostsme.main.MainWindow;


/**
 * 备份按钮监听器
 * @author Terry5
 *
 */
public class BackupBtnListener implements MouseListener {
	Properties properties = new Properties();
	InputStream fis = BackupBtnListener.class.getResourceAsStream("/cfg/conf.properties");
	MainWindow mainWindow;
	//系统换行符
	String n = System.getProperty("line.separator");
	
	public BackupBtnListener(MainWindow mWindow) {
		try {
			mainWindow = mWindow;
			properties.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated constructor stub
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		buttonFunc bFunc = new buttonFunc(mainWindow);
		bFunc.backupBtn();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		JButton button = (JButton) e.getSource();
		ImageIcon clicked = new ImageIcon(properties.getProperty("backupClicked.url"));
		button.setIcon(clicked);
		mainWindow.progressArea.append(n+n+"正在备份本地Hosts...");

//		System.out.println(properties.getProperty("startClicked.url"));
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		JButton button = (JButton) e.getSource();
		ImageIcon backup = new ImageIcon(properties.getProperty("backup.url"));
		button.setIcon(backup);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}

