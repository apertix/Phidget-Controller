package me.spencer.phc.core.controllers;

import com.phidgets.InterfaceKitPhidget;
import com.phidgets.PhidgetException;
import com.phidgets.event.AttachEvent;
import com.phidgets.event.AttachListener;
import com.phidgets.event.DetachEvent;
import com.phidgets.event.DetachListener;

public class InterfaceController {
	
	private int serial, port;
	private String ip = "";
	private static InterfaceKitPhidget intController;
	
	public InterfaceController(int serial, String ip, int port) throws PhidgetException {
		this.serial = serial;
		this.ip = ip;
		this.port = port;
		intController = new InterfaceKitPhidget();
		
	}
	
	public InterfaceController() {
		
	}
	
	public void openDevice() throws PhidgetException {
		intController.addAttachListener(new AttachListener() {
			
			@Override
			public void attached(AttachEvent arg0) {
				System.out.println("Attached interface controller...");
				
			}
		});
	
		intController.addDetachListener(new DetachListener() {
			
			@Override
			public void detached(DetachEvent arg0) {
				System.out.println("Detached interface controller...");
				
			}
		});
		
		intController.open(serial, ip, port);
		intController.waitForAttachment(10000);
		if (!intController.isAttachedToServer()) {
			System.out.println("Interface controller failed to attach to the server...");
		}
	}
	
	public static InterfaceController get() {
		return new InterfaceController();
	}
	
	public static InterfaceKitPhidget getInterfacePhidget() {
		return intController;
	}
	
	public int getInputCount() throws PhidgetException {
		return intController.getInputCount();
	}
	/**
	 * Returns the distance from the IR sensor to the nearest object.
	 * @return
	 * @throws PhidgetException 
	 */
	
	public int getDistance() throws PhidgetException {
		return intController.getSensorValue(0);
	}


}
