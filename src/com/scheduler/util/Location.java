package com.scheduler.util;

import com.lib.connection.MessageReceiver;

public class Location  {
	private MessageReceiver receiver;

	public Location() {
		receiver=new MessageReceiver(5888);
		receiver.start();
	}

	public String getMessage(){
		return receiver.getRequest();
	}
	
	public boolean isAlive(){
		return receiver.isReceiverAlive();
	}
	
	public void stopLocation(){
		if(receiver!=null){
			receiver.stopReceiver();
		}
	}
}
