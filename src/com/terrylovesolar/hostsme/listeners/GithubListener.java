package com.terrylovesolar.hostsme.listeners;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import com.terrylovesolar.hostsme.main.MainWindow;


/**
 * Github复选框监听器
 * @author Terry5
 *
 */
public class GithubListener implements ItemListener{
	
	MainWindow mainWindow;
	public GithubListener(MainWindow mWindow) {
		mainWindow = mWindow;
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			mainWindow.hostsSelected.append("Github\n");
		} else {	
			String hosts = mainWindow.hostsSelected.getText();
			mainWindow.hostsSelected.setText(hosts.replaceAll("Github\n", ""));
		}
		
	}
	
}
