package com.terrylovesolar.hostsme.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.Callable;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingWorker;
import com.terrylovesolar.hostsme.func.buttonFunc;
import com.terrylovesolar.hostsme.main.MainWindow;


/**
 * 下载按钮监听器
 * @author Terry5
 *
 */
public class DownloadBtnListener implements MouseListener {
	Properties properties = new Properties();
	InputStream fis = DownloadBtnListener.class.getResourceAsStream("/cfg/conf.properties");
	MainWindow mainWindow;
	//系统换行符
	String n = System.getProperty("line.separator");
	
	
	private boolean flag = false;
	
	public DownloadBtnListener(MainWindow mWindow) {
		try {
			mainWindow = mWindow;
			properties.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated constructor stub
	}
	
	class downloadTask implements Callable<Boolean> {
		@Override
		public Boolean call() throws Exception {
			buttonFunc bFunc = new buttonFunc(mainWindow);
			if (flag) {
				bFunc.downloadBtn();
				flag =false;
				return true;
			}
			return false;
			
		}
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		flag = true;
		new SwingWorker<Boolean, Void>() {
			@Override
			protected Boolean doInBackground() throws Exception {
				buttonFunc bFunc = new buttonFunc(mainWindow);
				if (flag) {
					bFunc.downloadBtn();
					flag =false;
					return true;
				}
				return false;
			}
			
			@Override
			protected void done() {
				mainWindow.progressArea.setText(mainWindow.progressArea.getText().replaceAll(
						n+n+"正在下载最新版本Hosts...", 
						n+n+"正在下载最新版本Hosts...  Done!"));
			}
		}.execute();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		JButton button = (JButton) e.getSource();
		ImageIcon clicked = new ImageIcon(properties.getProperty("downClicked.url"));
		button.setIcon(clicked);
		if (mainWindow.progressArea.getText().indexOf("正在下载最新版本Hosts...") >= 0) {
			mainWindow.progressArea.setText(mainWindow.progressArea.getText().replaceAll(
					n+n+"正在下载最新版本Hosts...  Done!", 
					""));
			mainWindow.progressArea.append(n+n+"正在下载最新版本Hosts...");
		} else {
			mainWindow.progressArea.append(n+n+"正在下载最新版本Hosts...");
		}
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		JButton button = (JButton) e.getSource();
		ImageIcon download = new ImageIcon(properties.getProperty("downIcon.url"));
		button.setIcon(download);
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

