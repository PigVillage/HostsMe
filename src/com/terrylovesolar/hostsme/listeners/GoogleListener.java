package com.terrylovesolar.hostsme.listeners;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.IOException;

import com.terrylovesolar.hostsme.common.Constants;
import com.terrylovesolar.hostsme.func.HostsIO;
import com.terrylovesolar.hostsme.main.MainWindow;


/**
 * Google复选框监听器
 * @author Terry5
 *
 */
public class GoogleListener implements ItemListener{
	HostsIO hostsIO = new HostsIO();
	Constants constants = new Constants();
	MainWindow mainWindow;
	
	public GoogleListener(MainWindow mWindow) {
		mainWindow = mWindow;
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			mainWindow.hostsSelected.append("Google\n");
		} else {	
			String hosts = mainWindow.hostsSelected.getText();
			mainWindow.hostsSelected.setText(hosts.replaceAll("Google\n", ""));
		}
		
	}
	
}
