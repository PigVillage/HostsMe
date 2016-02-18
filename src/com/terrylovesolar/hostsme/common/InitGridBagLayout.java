package com.terrylovesolar.hostsme.common;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.JScrollPane;


/**
 * 初始化GridBagLayout参数
 * @author Terry5
 *
 */
public class InitGridBagLayout {
	
	/**
	 * 初始化GridBagLayout参数
	 * @param gbc GridBagConstraints对象
	 * @param gbLayout GridBagLayout对象
	 * @param box 设置的Box
	 * @param gridwidth gridwidth参数
	 * @param weightx weightx参数
	 * @param weighty weighty参数
	 * @param up Insets的第一个参数
	 * @param left Insets的第二个参数
	 * @param down Insets的第三个参数
	 * @param right Insets的第四个参数
	 */
	public void initGBLaout(GridBagConstraints gbc, GridBagLayout gbLayout, Box box,
			int gridwidth, int weightx, int weighty, int up,
			int left, int down, int right) {
		gbc.gridwidth = gridwidth;
		gbc.weightx = weightx;
		gbc.weighty = weighty;
		gbc.insets = new Insets(up, left, down, right);
		gbLayout.setConstraints(box, gbc);
	}
	
	/**
	 * 初始化GridBagLayout参数
	 * @param gbc GridBagConstraints对象
	 * @param gbLayout GridBagLayout对象
	 * @param jp 设置的JScrollPane
	 * @param gridwidth gridwidth参数
	 * @param weightx weightx参数
	 * @param weighty weighty参数
	 * @param up Insets的第一个参数
	 * @param left Insets的第二个参数
	 * @param down Insets的第三个参数
	 * @param right Insets的第四个参数
	 */
	public void initGBLaout(GridBagConstraints gbc, GridBagLayout gbLayout, JScrollPane jp,
			int gridwidth, int weightx, int weighty, int up,
			int left, int down, int right) {
		gbc.gridwidth = gridwidth;
		gbc.weightx = weightx;
		gbc.weighty = weighty;
		gbc.insets = new Insets(up, left, down, right);
		gbLayout.setConstraints(jp, gbc);
	}
}
