package com.terrylovesolar.hostsme.main;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import org.pushingpixels.substance.api.SubstanceLookAndFeel;

import com.terrylovesolar.hostsme.common.Constants;
import com.terrylovesolar.hostsme.common.InitButton;
import com.terrylovesolar.hostsme.common.InitCheckBox;
import com.terrylovesolar.hostsme.common.InitGridBagLayout;
import com.terrylovesolar.hostsme.func.HostsIO;
import com.terrylovesolar.hostsme.func.InitCheck;
import com.terrylovesolar.hostsme.func.Spider;
import com.terrylovesolar.hostsme.listeners.BackupBtnListener;
import com.terrylovesolar.hostsme.listeners.DownloadBtnListener;
import com.terrylovesolar.hostsme.listeners.FaceBookListener;
import com.terrylovesolar.hostsme.listeners.GithubListener;
import com.terrylovesolar.hostsme.listeners.GoogleListener;
import com.terrylovesolar.hostsme.listeners.RestoreBtnListener;
import com.terrylovesolar.hostsme.listeners.StartBtnListener;
import com.terrylovesolar.hostsme.listeners.TwitterListener;
import com.terrylovesolar.hostsme.listeners.YoutubeListener;

/**
 * 程序主窗体
 * @author Terry5
 * @serial Pig Village
 */

public class MainWindow extends JFrame{
	//Hosts CheckBox
	public JCheckBox google;
	public JCheckBox facebook;
	public JCheckBox twitter;
	public JCheckBox github;
	public JCheckBox youtube;
	
	// 按钮图标
	public ImageIcon start;
	public ImageIcon download;
	public ImageIcon backup;
	public ImageIcon restore;
	
	//功能按钮
	public JButton startBtn;
	public JButton downloadBtn;
	public JButton backupBtn;
	public JButton restoreBtn;
	
	//当前Hosts显示区
	public JTextArea hostsArea;
	
	//状态标签
	public JLabel netState;
	public JLabel osInfo;
	public JLabel dateInfo;
	public JLabel originInfo;
	
	//Hosts选择容器
	public JTextArea hostsSelected;
	
	//底部进度显示区
	public JTextArea progressArea;
	
	
	//实例化常量对象
	Constants constants = new Constants();
	
	InitCheckBox initCheckBox = new InitCheckBox();
	InitButton initButton = new InitButton();
	InitGridBagLayout initGridBagLayout = new InitGridBagLayout();

	//字体、常量配置
	Properties properties = new Properties();
	InputStream fis = MainWindow.class.getResourceAsStream("/cfg/conf.properties");
	Font checkBoxFont = new Font("Courier", Font.BOLD, 20);
	Font labelFont = new Font("Courier", Font.ITALIC, 15);
	Font progressFont = new Font("Courier", Font.PLAIN, 15);
	Font hostsSelectedFont = new Font("Courier", Font.BOLD, 22);
	
	public MainWindow() {
		Init();
	}
	
	void Init() {
		JFrame frame = new JFrame();
		//加载属性文件
		try {
			properties.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//导入国际化文件
//		Locale mylocale = Locale.getDefault(Locale.Category.FORMAT); 
//		ResourceBundle bundle = ResourceBundle.getBundle("cfg.host", mylocale);
		
		JPanel mainPanel = new JPanel() {
			//设置主窗体背景图片
			protected void paintComponent(Graphics g) {
                ImageIcon icon = new ImageIcon(properties.getProperty("bg.url"));  
                Image img = icon.getImage();  
                g.drawImage(img, 0, 0, icon.getIconWidth(),  
                        icon.getIconHeight(), icon.getImageObserver());  
			}
		};
		
		GridBagLayout gbLayout = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		mainPanel.setLayout(gbLayout);
		
		//顶部左侧CheckBox
		Box topLeftBox = new Box(BoxLayout.Y_AXIS);
		google = initCheckBox.create("Google", google, topLeftBox, checkBoxFont);
		facebook = initCheckBox.create("FaceBook", facebook, topLeftBox, checkBoxFont);
		twitter = initCheckBox.create("Twitter", twitter, topLeftBox, checkBoxFont);
		github = initCheckBox.create("Github", github, topLeftBox, checkBoxFont);
		youtube = initCheckBox.create("YouTube", youtube, topLeftBox, checkBoxFont);

		//顶部中间按钮块
		Box topMidBox = new Box(BoxLayout.Y_AXIS);
		String imgUrlStart = properties.getProperty("startIcon.url");
		String imgUrlDownload = properties.getProperty("downIcon.url");
		String imgUrlBackup = properties.getProperty("backup.url");
		String imgUrlRestore = properties.getProperty("restore.url");
		startBtn = initButton.create("执行替换Hosts", startBtn, start, imgUrlStart, topMidBox);
		downloadBtn = initButton.create("下载最新Hosts", downloadBtn, download, imgUrlDownload, topMidBox);
		backupBtn = initButton.create("备份本地Hosts", backupBtn, backup, imgUrlBackup, topMidBox);
		restoreBtn = initButton.create("恢复备份Hosts", restoreBtn, restore, imgUrlRestore, topMidBox);
		
				
		//顶部右侧Box
		Box topRightBox = new Box(BoxLayout.X_AXIS);
		topRightBox.setForeground(Color.BLACK);
		//设置标题边框
		topRightBox.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK), "当前Hosts"));
		hostsArea = new JTextArea();
		JScrollPane hostsScroll = new JScrollPane(hostsArea);
		//设置jscrollpanel透明背景
		hostsArea.setOpaque(false);
		hostsScroll.setOpaque(false);
		hostsScroll.getViewport().setOpaque(false);
		//去除jscrollpanel边框
		hostsScroll.setBorder(null);
		topRightBox.add(hostsScroll);
	
		
		//中部状态条
		Box midBox = new Box(BoxLayout.X_AXIS);
		midBox.add(new JLabel("网络状态：")).setFont(labelFont);
		midBox.add(netState = new JLabel("未知")).setFont(labelFont);
		midBox.add(Box.createHorizontalStrut(constants.LABEL_STRUCT_SIZE));
		midBox.add(new JLabel("操作系统：")).setFont(labelFont);
		midBox.add(osInfo = new JLabel("未知")).setFont(labelFont);
		midBox.add(Box.createHorizontalStrut(constants.LABEL_STRUCT_SIZE));
		midBox.add(new JLabel("最新版本：")).setFont(labelFont);
		midBox.add(dateInfo = new JLabel("获取中...")).setFont(labelFont);
		midBox.add(Box.createHorizontalStrut(constants.LABEL_STRUCT_SIZE));
		midBox.add(new JLabel("服务来源：")).setFont(labelFont);;
		midBox.add(originInfo = new JLabel("Github")).setFont(labelFont);
		midBox.add(Box.createHorizontalStrut(constants.LABEL_STRUCT_SIZE));
		midBox.add(new JLabel("软件版本：")).setFont(labelFont);
		midBox.add(new JLabel(properties.getProperty("version"))).setFont(labelFont);
		midBox.add(Box.createHorizontalStrut(90));

		//底部左侧hosts选中容器
		hostsSelected = new JTextArea("添加的Hosts：\n");
		hostsSelected.setEditable(false);
		hostsSelected.setFont(hostsSelectedFont);
		hostsSelected.setForeground(Color.WHITE);
		hostsSelected.setBackground(Color.BLACK);
		hostsSelected.setLineWrap(true);
		JScrollPane hostsSelectedJp = new JScrollPane(hostsSelected); 
		//竖直方向滚动条从不显示
		hostsSelectedJp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);;
		hostsSelectedJp.setEnabled(false);
		
		
		//底部进度框
		progressArea = new JTextArea("程序初始化...  Done！");
		progressArea.setEditable(false);
		progressArea.setFont(progressFont);
		progressArea.setForeground(Color.GREEN);
		progressArea.setBackground(Color.BLACK);
		progressArea.setLineWrap(true);
		JScrollPane progressAreaJp = new JScrollPane(progressArea); 
		progressAreaJp.setEnabled(false);
		
		
		mainPanel.add(topLeftBox);
		mainPanel.add(topMidBox);
		mainPanel.add(topRightBox);
		mainPanel.add(midBox);
		mainPanel.add(hostsSelectedJp);
		mainPanel.add(progressAreaJp);
		this.add(mainPanel);
		

		gbc.fill = GridBagConstraints.BOTH;
		initGridBagLayout.initGBLaout(gbc, gbLayout, topLeftBox, 1, 0, 0, 40, 0, 0, 0);
		initGridBagLayout.initGBLaout(gbc, gbLayout, topMidBox, 1, 0, 0, 35, 30, 0, 0);
		initGridBagLayout.initGBLaout(gbc, gbLayout, topRightBox, 0, 0, 0, 35, 35, 0, 0);
		initGridBagLayout.initGBLaout(gbc, gbLayout, midBox, 0, 0, 0, 25, 0, 0, 0);
		initGridBagLayout.initGBLaout(gbc, gbLayout, hostsSelectedJp, 2, 0, 1, 0, 0, 0, 0);
		initGridBagLayout.initGBLaout(gbc, gbLayout, progressAreaJp, 0, 1, 1, 0, 0, 0, 0);

		//设置主界面参数
		frame.add(mainPanel);
		frame.setTitle("HostsMe      ---PigVillage");
//		frame.setUndecorated(true);
		frame.setResizable(false);
		frame.setSize(1000, 680);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		ImageIcon imageIcon = new ImageIcon(properties.getProperty("logo.url"));
		Image icon = imageIcon.getImage();
		frame.setIconImage(icon);

		//添加checkBox监听器
		google.addItemListener(new GoogleListener(this));
		facebook.addItemListener(new FaceBookListener(this));
		github.addItemListener(new GithubListener(this));
		twitter.addItemListener(new TwitterListener(this));
		youtube.addItemListener(new YoutubeListener(this));
		
		//添加按钮监听器
		startBtn.addMouseListener(new StartBtnListener(this));
		downloadBtn.addMouseListener(new DownloadBtnListener(this));
		restoreBtn.addMouseListener(new RestoreBtnListener(this));
		backupBtn.addMouseListener(new BackupBtnListener(this));
		
		//初始化检测
		InitCheck check = new InitCheck();
		check.read_hosts(hostsArea);
		progressArea.append("\n\n网络状态检测...");
		if (check.netCheck()) {
			netState.setText("正常");
		} else {
			netState.setText("异常");
			netState.setForeground(Color.RED);
		}
		progressArea.append("  Done！");	
		
		progressArea.append("\n\n获取系统版本...");
		osInfo.setText(check.osCheck());
		progressArea.append("  Done！");	
		
		
		//检测本地获取的Hosts
		progressArea.append("\n\n本地Hosts文件检测...");
		HostsIO hostsIO = new HostsIO();
		if (check.checkIfHosts()) {
			progressArea.append("  Done！");
			progressArea.append("\n\n本地存在Hosts文件！");
			progressArea.append("版本：" + hostsIO.get_local_time());
		} else {
			try {
				hostsIO.write_hosts();
				progressArea.append("  Done！");
				progressArea.append("\n\n成功获取Hosts文件，保存在Hosts文件夹");
			} catch (Exception e) {
				progressArea.append("  Done！");
				progressArea.append("\n\nHosts文件保存失败");
			}
		}
		
		//分割获取到的Hosts文件
		check.spiltHost();
		
		
		//设置版本时间
		progressArea.append("\n\n获取最新版本时间...");
		//滚动到最后一行
		progressArea.setCaretPosition(progressArea.getText().length());
		Spider spider = new Spider();
//		dateInfo.setText(hostsIO.get_time());
		dateInfo.setText(spider.get_date(spider.get_source()));
//		progressArea.append("  Done！");
		progressArea.setText(progressArea.getText().replace("\n\n获取最新版本时间...", "\n\n获取最新版本时间... Done!"));
		

	}
	public static void main(String[] args) {

		//初始化窗体
		MainWindow mainWindow = new MainWindow();
	}
}
