package com.terrylovesolar.hostsme.common;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;


/**
 * 初始化按钮
 * @author Terry5
 *
 */
public class InitButton {
	Constants constants = new Constants();
	
	/**
	 * 创建一个Button对象
	 * @param tip 按钮显示的Tip
	 * @param jButton 按钮对象
	 * @param imageIcon 按钮图标对象
	 * @param imgUrl 图标的路径
	 * @param box 添加到的Box
	 * @return 一个JButton对象
	 */
	public JButton create(String tip, JButton jButton, ImageIcon imageIcon, String imgUrl, Box box) {
		imageIcon = new ImageIcon(imgUrl);
		jButton = new JButton(imageIcon);
		jButton.setToolTipText(tip);
		box.add(jButton);
		box.add(Box.createVerticalStrut(constants.ICON_STRUCT_SIZE));
		//Mac OS仅需设置setBorderPainted即可实现无背景按钮，Win三个都需要设置
		jButton.setBorderPainted(false);
		jButton.setFocusPainted(false);
		jButton.setContentAreaFilled(false);
		return jButton;
	}
}
