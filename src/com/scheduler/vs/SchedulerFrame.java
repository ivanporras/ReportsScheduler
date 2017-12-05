package com.scheduler.vs;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import com.lib.vs.Dialog;
import com.lib.vs.FileChooser;
import com.scheduler.sys.TriggerTask;
import com.scheduler.util.Location;
import com.scheduler.util.Properties;
import com.scheduler.util.SystemValues;

//VS4E -- DO NOT REMOVE THIS LINE!
public class SchedulerFrame extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private static JCheckBox wednesdayCheckBox;
	private static JCheckBox thursdayCheckBox;
	private static JCheckBox fridayCheckBox;
	private static JCheckBox saturdayCheckBox;
	private static JCheckBox sundayCheckBox;
	private static JCheckBox tuesdayCheckBox;
	private static JCheckBox mondayCheckBox;
	private static JTextField hourTextField;
	private JLabel hourLabel;
	private JLabel minuteLabel;
	private static JTextField minuteTextField;
	private JLabel secondLabel;
	private static JTextField secondTextField;
	private JButton scheduleButton;
	private JLabel fileLabel;
	private JButton fileButton;
	private static JLabel fileChosenLabel;
	private JButton saveButton;
	private JButton stopButton;
	private TriggerTask trigger;
	private JPanel weekPanel;
	private JPanel timePanel;
	private JPanel filePanel;
	private JPanel buttonsPanel;
	private Location location;
	private JPanel vmArgsPanel;
	private JPanel appArgsPanel;
	private static JTextArea appArgsTextArea;
	private JScrollPane appArgsScrollPane;
	private static JTextArea vmArgsTextArea;
	private JScrollPane vmArgsScrollPane;

	public SchedulerFrame() {
		initComponents();
		new TrayController(this,"JAR Scheduler");
		fileButton.addActionListener(this);
		scheduleButton.addActionListener(this);
		saveButton.addActionListener(this);
		stopButton.addActionListener(this);
	}

	private void initComponents() {
		setTitle("JAR Scheduler");
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setLayout(new GroupLayout());
		add(getWeekPanel(), new Constraints(new Leading(6, 537, 10, 10), new Leading(6, 71, 12, 12)));
		add(getTimePanel(), new Constraints(new Leading(6, 278, 12, 12), new Leading(86, 52, 12, 12)));
		add(getFilePanel(), new Constraints(new Leading(6, 279, 12, 12), new Leading(154, 80, 10, 10)));
		add(getButtonsPanel(), new Constraints(new Leading(6, 227, 12, 12), new Leading(240, 148, 12, 12)));
		add(getVmparamsPanel(), new Constraints(new Leading(243, 296, 12, 12), new Leading(242, 144, 12, 12)));
		add(getAppParamsPanel(), new Constraints(new Leading(297, 242, 10, 10), new Leading(86, 146, 12, 12)));
		setSize(553, 423);
	}

	private JScrollPane getVmArgsScrollPane() {
		if (vmArgsScrollPane == null) {
			vmArgsScrollPane = new JScrollPane();
			vmArgsScrollPane.setViewportView(getVmArgsTextArea());
		}
		return vmArgsScrollPane;
	}

	private JTextArea getVmArgsTextArea() {
		if (vmArgsTextArea == null) {
			vmArgsTextArea = new JTextArea();
		}
		return vmArgsTextArea;
	}

	private JScrollPane getAppArgsScrollPane() {
		if (appArgsScrollPane == null) {
			appArgsScrollPane = new JScrollPane();
			appArgsScrollPane.setViewportView(getAppArgsTextArea());
		}
		return appArgsScrollPane;
	}

	private JTextArea getAppArgsTextArea() {
		if (appArgsTextArea == null) {
			appArgsTextArea = new JTextArea();
		}
		return appArgsTextArea;
	}
	
	public static String getAppArgs(){
		return appArgsTextArea.getText();
	}
	
	public static String getVmArgs(){
		return vmArgsTextArea.getText();
	}

	private JPanel getAppParamsPanel() {
		if (appArgsPanel == null) {
			appArgsPanel = new JPanel();
			appArgsPanel.setBorder(BorderFactory.createTitledBorder(null, "APP Args", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, null, null));
			appArgsPanel.setLayout(new GroupLayout());
			appArgsPanel.add(getAppArgsScrollPane(), new Constraints(new Leading(4, 221, 10, 10), new Leading(4, 115, 10, 10)));
		}
		return appArgsPanel;
	}

	private JPanel getVmparamsPanel() {
		if (vmArgsPanel == null) {
			vmArgsPanel = new JPanel();
			vmArgsPanel.setBorder(BorderFactory.createTitledBorder(null, "VM Args", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, null, null));
			vmArgsPanel.setLayout(new GroupLayout());
			vmArgsPanel.add(getVmArgsScrollPane(), new Constraints(new Leading(4, 274, 10, 10), new Leading(2, 112, 10, 10)));
		}
		return vmArgsPanel;
	}

	private JPanel getButtonsPanel() {
		if (buttonsPanel == null) {
			buttonsPanel = new JPanel();
			buttonsPanel.setBorder(BorderFactory.createTitledBorder(null, "Actions", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, null, null));
			buttonsPanel.setLayout(new GroupLayout());
			buttonsPanel.add(getSaveButton(), new Constraints(new Leading(79, 12, 12), new Leading(-3, 29, 10, 10)));
			buttonsPanel.add(getStopButton(), new Constraints(new Leading(63, 95, 12, 12), new Leading(29, 36, 10, 10)));
			buttonsPanel.add(getScheduleButton(), new Constraints(new Leading(57, 107, 12, 12), new Leading(71, 36, 12, 12)));
		}
		return buttonsPanel;
	}

	private JPanel getFilePanel() {
		if (filePanel == null) {
			filePanel = new JPanel();
			filePanel.setBorder(BorderFactory.createTitledBorder(null, "File", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, null, null));
			filePanel.setLayout(new GroupLayout());
			filePanel.add(getFileButton(), new Constraints(new Leading(35, 96, 12, 12), new Leading(5, 20, 10, 10)));
			filePanel.add(getFileLabel(), new Constraints(new Leading(10, 12, 12), new Leading(6, 12, 12)));
			filePanel.add(getFileChosenLabel(), new Constraints(new Leading(10, 257, 10, 10), new Leading(31, 12, 12)));
		}
		return filePanel;
	}

	private JPanel getTimePanel() {
		if (timePanel == null) {
			timePanel = new JPanel();
			timePanel.setBorder(BorderFactory.createTitledBorder(null, "Time", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, null, null));
			timePanel.setLayout(new GroupLayout());
			timePanel.add(getHourTextField(), new Constraints(new Leading(54, 10, 10), new Leading(0, 12, 12)));
			timePanel.add(getMinuteTextField(), new Constraints(new Leading(114, 10, 10), new Leading(0, 12, 12)));
			timePanel.add(getSecondTextField(), new Constraints(new Leading(180, 10, 10), new Leading(0, 12, 12)));
			timePanel.add(getSecondLabel(), new Constraints(new Leading(134, 10, 10), new Leading(4, 12, 12)));
			timePanel.add(getMinuteLabel(), new Constraints(new Leading(73, 10, 10), new Leading(4, 12, 12)));
			timePanel.add(getHourLabel(), new Constraints(new Leading(21, 12, 12), new Leading(2, 12, 12)));
		}
		return timePanel;
	}

	private JPanel getWeekPanel() {
		if (weekPanel == null) {
			weekPanel = new JPanel();
			weekPanel.setLayout(new GroupLayout());
			weekPanel.setBorder(BorderFactory.createTitledBorder(null, "Days of the week", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, null, null));
			weekPanel.add(getMondayCheckBox(), new Constraints(new Leading(4, 73, 10, 10), new Leading(14, 10, 10)));
			weekPanel.add(getTuesdayCheckBox(), new Constraints(new Leading(73, 10, 10), new Leading(14, 10, 10)));
			weekPanel.add(getWednesdayCheckBox(), new Constraints(new Leading(144, 10, 10), new Leading(14, 10, 10)));
			weekPanel.add(getThursdayCheckBox(), new Constraints(new Leading(236, 10, 10), new Leading(14, 10, 10)));
			weekPanel.add(getFridayCheckBox(), new Constraints(new Leading(316, 10, 10), new Leading(16, 10, 10)));
			weekPanel.add(getSaturdayCheckBox(), new Constraints(new Leading(378, 10, 10), new Leading(16, 10, 10)));
			weekPanel.add(getSundayCheckBox(), new Constraints(new Leading(458, 10, 10), new Leading(14, 10, 10)));
		}
		return weekPanel;
	}

	private JButton getStopButton() {
		if (stopButton == null) {
			stopButton = new JButton();
			stopButton.setText("Stop");
			stopButton.setEnabled(false);
		}
		return stopButton;
	}

	private JButton getSaveButton() {
		if (saveButton == null) {
			saveButton = new JButton();
			saveButton.setText("Save");
		}
		return saveButton;
	}

	private JLabel getFileChosenLabel() {
		if (fileChosenLabel == null) {
			fileChosenLabel = new JLabel();
			fileChosenLabel.setText("---");
		}
		return fileChosenLabel;
	}

	private JButton getFileButton() {
		if (fileButton == null) {
			fileButton = new JButton();
			fileButton.setFont(new Font("Dialog", Font.BOLD, 10));
			fileButton.setText("Choose JAR");
		}
		return fileButton;
	}

	private JLabel getFileLabel() {
		if (fileLabel == null) {
			fileLabel = new JLabel();
			fileLabel.setText("File");
		}
		return fileLabel;
	}

	private JButton getScheduleButton() {
		if (scheduleButton == null) {
			scheduleButton = new JButton();
			scheduleButton.setText("Schedule");
		}
		return scheduleButton;
	}

	private JTextField getSecondTextField() {
		if (secondTextField == null) {
			secondTextField = new JTextField();
			secondTextField.setText("00");
		}
		return secondTextField;
	}

	private JLabel getSecondLabel() {
		if (secondLabel == null) {
			secondLabel = new JLabel();
			secondLabel.setText("Second");
		}
		return secondLabel;
	}

	private JTextField getMinuteTextField() {
		if (minuteTextField == null) {
			minuteTextField = new JTextField();
			minuteTextField.setText("00");
		}
		return minuteTextField;
	}

	private JLabel getMinuteLabel() {
		if (minuteLabel == null) {
			minuteLabel = new JLabel();
			minuteLabel.setText("Minute");
		}
		return minuteLabel;
	}

	private JLabel getHourLabel() {
		if (hourLabel == null) {
			hourLabel = new JLabel();
			hourLabel.setText("Hour");
		}
		return hourLabel;
	}

	private JTextField getHourTextField() {
		if (hourTextField == null) {
			hourTextField = new JTextField();
			hourTextField.setText("00");
		}
		return hourTextField;
	}

	//If edited, make sure the checkBox has setName("SUN");
	private JCheckBox getSundayCheckBox() {
		if (sundayCheckBox == null) {
			sundayCheckBox = new JCheckBox();
			sundayCheckBox.setText("Sunday");
			sundayCheckBox.setName("SUN");
		}
		return sundayCheckBox;
	}

	//If edited, make sure the checkBox has setName("SAT");
	private JCheckBox getSaturdayCheckBox() {
		if (saturdayCheckBox == null) {
			saturdayCheckBox = new JCheckBox();
			saturdayCheckBox.setText("Saturday");
			saturdayCheckBox.setName("SAT");
		}
		return saturdayCheckBox;
	}

	//If edited, make sure the checkBox has setName("FRI");
	private JCheckBox getFridayCheckBox() {
		if (fridayCheckBox == null) {
			fridayCheckBox = new JCheckBox();
			fridayCheckBox.setText("Friday");
			fridayCheckBox.setName("FRI");
		}
		return fridayCheckBox;
	}

	//If edited, make sure the checkBox has setName("THU");
	private JCheckBox getThursdayCheckBox() {
		if (thursdayCheckBox == null) {
			thursdayCheckBox = new JCheckBox();
			thursdayCheckBox.setText("Thursday");
			thursdayCheckBox.setName("THU");
		}
		return thursdayCheckBox;
	}

	//If edited, make sure the checkBox has setName("WED");
	private JCheckBox getWednesdayCheckBox() {
		if (wednesdayCheckBox == null) {
			wednesdayCheckBox = new JCheckBox();
			wednesdayCheckBox.setText("Wednesday");
			wednesdayCheckBox.setName("WED");
		}
		return wednesdayCheckBox;
	}

	//If edited, make sure the checkBox has setName("MON");
	private JCheckBox getMondayCheckBox() {
		if (mondayCheckBox == null) {
			mondayCheckBox = new JCheckBox();
			mondayCheckBox.setText("Monday");
			mondayCheckBox.setName("MON");
		}
		return mondayCheckBox;
	}

	//If edited, make sure the chekbox has setName("TUE");
	private JCheckBox getTuesdayCheckBox() {
		if (tuesdayCheckBox == null) {
			tuesdayCheckBox = new JCheckBox();
			tuesdayCheckBox.setText("Tuesday");
			tuesdayCheckBox.setName("TUE");
		}
		return tuesdayCheckBox;
	}

	public static String getTime(){
		String time=secondTextField.getText()+" "+minuteTextField.getText()+" "+hourTextField.getText();
		return time;
	}
	
	public static boolean loadConfiguration(){
		String days=null,time=null,file=null,appArgs=null,vmArgs=null;
		try{
			days=Properties.getDays();
			time=Properties.getTime();
			file=Properties.getFile();
			appArgs=Properties.getAppArgs();
			vmArgs=Properties.getVmArgs();
			System.out.println(days+time+file);
			if(days.contains(",")){
				String [] daysList=days.split(",");
				for(String day:daysList){
					checkDay(day);
				}
			}else{
				checkDay(days);
			}
			String[] times=time.split(" ");
			secondTextField.setText(times[0]);
			minuteTextField.setText(times[1]);
			hourTextField.setText(times[2]);
			fileChosenLabel.setText(file);
			appArgsTextArea.setText(appArgs);
			vmArgsTextArea.setText(vmArgs);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		if(days==null||time==null){
			return false;
		}else{
			return true;
		}
	}
	
	public static void checkDay(String day){
		if(day.equals("MON")){
			mondayCheckBox.setSelected(true);
		}else if(day.equals("TUE")){
			tuesdayCheckBox.setSelected(true);
		}else if(day.equals("WED")){
			wednesdayCheckBox.setSelected(true);
		}else if(day.equals("THU")){
			thursdayCheckBox.setSelected(true);
		}else if(day.equals("FRI")){
			fridayCheckBox.setSelected(true);
		}else if(day.equals("SAT")){
			saturdayCheckBox.setSelected(true);
		}else if(day.equals("SUN")){
			sundayCheckBox.setSelected(true);
		}
	}
	
	public static String getDays(){
		ArrayList<String> days=new ArrayList<String>();
		String daysFormated="";
		if(mondayCheckBox.isSelected()){
			days.add(mondayCheckBox.getName());
		} 
		if(tuesdayCheckBox.isSelected()){
			days.add(tuesdayCheckBox.getName());
		}
		if(wednesdayCheckBox.isSelected()){
			days.add(wednesdayCheckBox.getName());
		}
		if(thursdayCheckBox.isSelected()){
			days.add(thursdayCheckBox.getName());
		}
		if(fridayCheckBox.isSelected()){
			days.add(fridayCheckBox.getName());
		}
		if(saturdayCheckBox.isSelected()){
			days.add(saturdayCheckBox.getName());
		}
		if(sundayCheckBox.isSelected()){
			days.add(sundayCheckBox.getName());
		}
		for(String d:days){
			daysFormated+=d+",";
		}
		if(daysFormated.length()>0){
			daysFormated=daysFormated.substring(0,daysFormated.length()-1);
		}
		return daysFormated;
	}
	
	public void changeGUIState(boolean state){
		scheduleButton.setEnabled(state);
		hourTextField.setEnabled(state);
		minuteTextField.setEnabled(state);
		secondTextField.setEnabled(state);
		mondayCheckBox.setEnabled(state);
		tuesdayCheckBox.setEnabled(state);
		wednesdayCheckBox.setEnabled(state);
		thursdayCheckBox.setEnabled(state);
		fridayCheckBox.setEnabled(state);
		saturdayCheckBox.setEnabled(state);
		sundayCheckBox.setEnabled(state);
		fileButton.setEnabled(state);
		appArgsTextArea.setEnabled(state);
		vmArgsTextArea.setEnabled(state);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		String cmd=e.getActionCommand();
		if(cmd.equalsIgnoreCase("CHOOSE JAR")){
			FileChooser file=new FileChooser();
			fileChosenLabel.setText(file.showFileChooser(JFileChooser.FILES_ONLY));
		}else if(cmd.equalsIgnoreCase("SCHEDULE")){
			if(fileChosenLabel.getText()!=null){
				if(!fileChosenLabel.getText().equals("---")&&!fileChosenLabel.getText().equals("")){
					if(SchedulerFrame.getDays().length()!=0){
						changeGUIState(false);
						trigger=new TriggerTask();
						trigger.trigger();
						stopButton.setEnabled(true);
					}else{
						Dialog.showDialog("At least one day must be selected", "ERROR", JOptionPane.WARNING_MESSAGE, this);
					}
				}else{
					Dialog.showDialog("The file hasn't been selected", "ERROR", JOptionPane.WARNING_MESSAGE, this);
				}
			}
			else{
				Dialog.showDialog("The file hasn't been selected", "ERROR", JOptionPane.WARNING_MESSAGE, this);
			}
		}else if(cmd.equalsIgnoreCase("SAVE")){
			Properties.setProperties(SchedulerFrame.getDays(), SchedulerFrame.getTime(), fileChosenLabel.getText(),SchedulerFrame.getAppArgs(),SchedulerFrame.getVmArgs());
			Dialog.showDialog("Configuration saved", "SAVED", JOptionPane.INFORMATION_MESSAGE, this);
		}else if(cmd.equalsIgnoreCase("STOP")){
			if(Dialog.showPasswordDialog(SystemValues.PASSWORD, this)){
				trigger.stopJob();
				changeGUIState(true);
				scheduleButton.setEnabled(true);
				stopButton.setEnabled(false);
			}
		}
	}
	
	public static void main(String...args){
		SchedulerFrame frame=new SchedulerFrame();
		//Closes the application if there's another open
		frame.location=new Location();
		try{
			Thread.sleep(500);
			if(!frame.location.isAlive()){
				System.exit(0);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		/////////////////////////////////////////////
		frame.setVisible(true);
		if(loadConfiguration()){
			ActionEvent action=new ActionEvent(frame.scheduleButton,ActionEvent.ACTION_PERFORMED,frame.scheduleButton.getActionCommand());
			for(ActionListener act:frame.scheduleButton.getActionListeners()){
				act.actionPerformed(action);
			}
		}
	}

	public static String getFile() {
		return fileChosenLabel.getText();
	}
}
