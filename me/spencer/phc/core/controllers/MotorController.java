package me.spencer.phc.core.controllers;

import com.phidgets.MotorControlPhidget;
import com.phidgets.PhidgetException;

/**
 * Created by Spencer on 4/22/2016.
 */
public class MotorController {

    private int serial, port;
    private String ip;
    private MotorControlPhidget motorControlPhidget;

    public MotorController() {
        try {
            motorControlPhidget = new MotorControlPhidget();
            motorControlPhidget.addAttachListener(ac -> {System.out.println("Motor Phidget attached...");});
            motorControlPhidget.addDetachListener(dc -> {System.out.println("Motor Phidget detached...");});
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void setProperties(int serial, String ip, int port) {
        this.serial = serial;
        this.ip = ip;
        this.port = port;
    }

    public void openDevice() {
        try {
            motorControlPhidget.open(serial, ip, port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void forwardBackward(double speed) {
        try {
        	stop();
            motorControlPhidget.setVelocity(0, speed);
            motorControlPhidget.setVelocity(1, speed);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void turnLeft(double speed) {
        try {
        	stop();
            motorControlPhidget.setVelocity(0, speed);
        	motorControlPhidget.setVelocity(1, -speed);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void turnRight(double speed) {
        try {
        	stop();
            motorControlPhidget.setVelocity(0, -speed);
        	motorControlPhidget.setVelocity(1, speed);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        try {
            motorControlPhidget.setVelocity(0, 0.0D);
            motorControlPhidget.setVelocity(1, 0.0D);
        } catch (PhidgetException e) {
            e.printStackTrace();
        }
    }

    public MotorControlPhidget getMotorControlPhidget() {
        return motorControlPhidget;
    }
}
