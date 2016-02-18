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
 * 恢复按钮监听器
 * @author Terry5
 *
 */
public class RestoreBtnListener implements MouseListener {
	Properties properties = new Properties();
	InputStream fis = RestoreBtnListener.class.getResourceAsStream("/cfg/conf.properties");
	MainWindow mainWindow;
	public RestoreBtnListener(MainWindow mWindow) {
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
		bFunc.restoreBtn();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		JButton button = (JButton) e.getSource();
		ImageIcon clicked = new ImageIcon(properties.getProperty("restoreClicked.url"));
		button.setIcon(clicked);
		
//		System.out.println(properties.getProperty("startClicked.url"));
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		JButton button = (JButton) e.getSource();
		ImageIcon restore = new ImageIcon(properties.getProperty("restore.url"));
		button.setIcon(restore);
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

