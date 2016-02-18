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
 * 开始按钮监听器
 * @author Terry5
 *
 */
public class StartBtnListener implements MouseListener {
	Properties properties = new Properties();
	InputStream fis = StartBtnListener.class.getResourceAsStream("/cfg/conf.properties");
	MainWindow mainWindow;
	public StartBtnListener(MainWindow mWindow) {
		
		try {
			mainWindow =mWindow;
			properties.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		buttonFunc bFunc = new buttonFunc(mainWindow);
		bFunc.startBtn();
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		JButton button = (JButton) e.getSource();
		ImageIcon startClicked = new ImageIcon(properties.getProperty("startClicked.url"));
		button.setIcon(startClicked);
//		System.out.println(properties.getProperty("startClicked.url"));
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		JButton button = (JButton) e.getSource();
		ImageIcon start = new ImageIcon(properties.getProperty("startIcon.url"));
		button.setIcon(start);
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

