package com.scheduler.util;

import java.util.Hashtable;

import com.lib.file.ProjectProperties;

public class Properties {
	
	public static void setProperties(String days,String time,String file,String appArgs,String vmArgs){
		ProjectProperties pjp=new ProjectProperties();
		pjp.addProperty("TIME", time);
		pjp.addProperty("DAYS", days);
		pjp.addProperty("FILE", file);
		pjp.addProperty("APP-ARGS", appArgs);
		pjp.addProperty("VM-ARGS", vmArgs);
		pjp.createProperties("config.properties", true,true);
	}
	
	public static String getTime(){
		Hashtable<String,String> configurations=new ProjectProperties().getConfigurations("config.properties",true);
		return configurations.get("TIME");
	}
	
	public static String getDays(){
		Hashtable<String,String> configurations=new ProjectProperties().getConfigurations("config.properties",true);
		return configurations.get("DAYS");
	}
	
	public static String getFile(){
		Hashtable<String,String> configurations=new ProjectProperties().getConfigurations("config.properties",true);
		return configurations.get("FILE");
	}
	
	public static String getVmArgs(){
		Hashtable<String,String> configurations=new ProjectProperties().getConfigurations("config.properties",true);
		return configurations.get("VM-ARGS");
	}
	
	public static String getAppArgs(){
		Hashtable<String,String> configurations=new ProjectProperties().getConfigurations("config.properties",true);
		return configurations.get("APP-ARGS");
	}
}
