package com.scheduler.vs;

import java.awt.AWTException;
import java.awt.Frame;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import javax.swing.JFrame;

public class TrayController {
	private JFrame frame;
	private TrayIcon trayIcon = null;
	private SystemTray tray;
	private String title;
	
	public TrayController(JFrame frame,String title){
		this.frame=frame;
		this.title=title;
		controllTray();
	}
	
	private void controllTray(){
		if (SystemTray.isSupported()) {
			tray = SystemTray.getSystemTray();
			Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/logoTray.png"));
			ActionListener listener1 = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frame.setVisible(true);
					frame.setExtendedState(JFrame.NORMAL);
				}
			};
			PopupMenu popup = new PopupMenu();
			MenuItem defaultItem = new MenuItem("Open");
			defaultItem.addActionListener(listener1);
			popup.add(defaultItem);
			trayIcon = new TrayIcon(image, title, popup);
			trayIcon.addActionListener(listener1);
			frame.addWindowStateListener(new WindowStateListener() {
				public void windowStateChanged(WindowEvent e) {
					if(e.getNewState()==Frame.ICONIFIED){
						try {
							tray.add(trayIcon);
							frame.setVisible(false);
						} catch (AWTException ex) {
							ex.printStackTrace();
						}
					}
					if(e.getNewState()==7){
						try{
							tray.add(trayIcon);
							frame.setVisible(false);
						}catch(AWTException ex){
							ex.printStackTrace();
						}
					}
					if(e.getNewState()==JFrame.MAXIMIZED_BOTH){
						tray.remove(trayIcon);
						frame.setVisible(true);
					}
					if(e.getNewState()==JFrame.NORMAL){
						tray.remove(trayIcon);
						frame.setVisible(true);
					}
				}
			});
		}
	}
	
}
