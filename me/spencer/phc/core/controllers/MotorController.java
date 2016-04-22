package me.spencer.phc.core.controllers;

import com.phidgets.MotorControlPhidget;
import com.phidgets.PhidgetException;
import com.phidgets.event.AttachEvent;
import com.phidgets.event.AttachListener;
import com.phidgets.event.DetachEvent;
import com.phidgets.event.DetachListener;

public class MotorController {
	
	private int port, serial;
	private String ip = "";
	private static MotorControlPhidget motor;
	
	public MotorController(int serial, String ip, int port) throws PhidgetException {
		this.serial = serial;
		this.port = port;
		this.ip = ip;
		this.motor = new MotorControlPhidget();
	}
	
	public MotorController() {
		
	}
	
	public void openDevice() throws PhidgetException {
		motor.addAttachListener(new AttachListener() {
			
			@Override
			public void attached(AttachEvent attachEvent) {
				System.out.println("Attached motor controller...");
				
			}
		});
		
		motor.addDetachListener(new DetachListener() {
			
			@Override
			public void detached(DetachEvent attachEvent) {
				System.out.println("Detached motor controller...");
			}
		});
		
		motor.open(serial, ip, port);
		motor.waitForAttachment(10000); //Wait this long then time out.
		if (!motor.isAttachedToServer()) {
			System.out.println("Phidget failed to attach to server...");
		}
	}
	
	public static MotorController get() {
		return new MotorController();
	}
	
	public static MotorControlPhidget getMotors() {
		return motor;
	}
	
	/**
	 * 
	 * If it's negative it'll move backwards.
	 * If positive it'll move forwards.
	 * 
	 * @param speed
	 * @throws PhidgetException
	 */
	
	public void moveForwardBackward(double speed) throws PhidgetException {
		motor.setVelocity(0, speed); //Right
		motor.setVelocity(1, speed); //Left
	}
	
	public int getMotorCount() throws PhidgetException {
		return motor.getMotorCount();
	}
	
	public void turnLeft(double speed) throws PhidgetException {
		motor.setVelocity(0, speed);
		motor.setVelocity(1, -speed);
	}
	
	public void turnRight(double speed) throws PhidgetException {
		motor.setVelocity(0, -speed);
		motor.setVelocity(1, speed);
	}
	
	public void stop() throws PhidgetException {
		motor.setVelocity(0, 0);
		motor.setVelocity(1, 0);
	}
	
	
	
	
	
}
