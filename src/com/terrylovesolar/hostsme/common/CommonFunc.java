package com.terrylovesolar.hostsme.common;

import javax.swing.JTextArea;

/**
 * 通用类
 * @author Terry5
 *
 */
public class CommonFunc {
	
	/**
	 * 使TextArea滚动条始终保持最低端
	 * @param textArea 需要控制的TextArea
	 */
	public void scrollToBottom(JTextArea textArea) {
		textArea.setCaretPosition(textArea.getText().length());
	}
}
