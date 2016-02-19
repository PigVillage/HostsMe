package com.terrylovesolar.hostsme.common;

import java.awt.Font;

import javax.swing.Box;
import javax.swing.JCheckBox;

/**
 * 初始化CheckBox
 * @author Terry5
 *
 */
public class InitCheckBox {

	/**
	 * 创建一个CheckBox
	 * @param name 显示的名称
	 * @param jCheckBox CheckBox对象
	 * @param box 添加到的Box
	 * @param font CheckBox字体
	 * @return 返回一个ChexkBox对象
	 */
	public JCheckBox create(String name, JCheckBox jCheckBox, Box box, Font font) {
		jCheckBox = new JCheckBox(name);
		jCheckBox.setOpaque(false);
		jCheckBox.setFocusPainted(false);
		box.add(jCheckBox).setFont(font);
		box.add(Box.createVerticalStrut(Constants.CHECK_STRUCT_SIZE));
		return jCheckBox;
	}
}
