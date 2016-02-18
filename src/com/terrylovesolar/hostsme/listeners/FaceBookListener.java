package com.terrylovesolar.hostsme.listeners;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import com.terrylovesolar.hostsme.main.MainWindow;

/**
 * FaceBook复选框监听器
 * @author Terry5
 *
 */
public class FaceBookListener implements ItemListener{
	
	MainWindow mainWindow;
	public FaceBookListener(MainWindow mWindow) {
		mainWindow = mWindow;
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			mainWindow.hostsSelected.append("FaceBook\n");
		} else {	
			String hosts = mainWindow.hostsSelected.getText();
			mainWindow.hostsSelected.setText(hosts.replaceAll("FaceBook\n", ""));
		}
	}
}
