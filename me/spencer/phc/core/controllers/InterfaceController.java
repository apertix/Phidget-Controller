package me.spencer.phc.core.controllers;

import com.phidgets.InterfaceKitPhidget;
import com.phidgets.PhidgetException;

/**
 * Created by Spencer on 4/22/2016.
 */
public class InterfaceController {

    private int serial, port;
    private String ip;
    private InterfaceKitPhidget interfaceKitPhidget;

    public InterfaceController() {
        try {
            interfaceKitPhidget = new InterfaceKitPhidget();
            interfaceKitPhidget.addAttachListener(ac -> {System.out.println("Interface Phidget attached...");});
            interfaceKitPhidget.addDetachListener(dc -> {System.out.println("Interface Phidget detached...");});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public double getDistanceInCM() {
        try {
            return 9462 /(interfaceKitPhidget.getSensorRawValue(0) - 16.92);
        } catch (PhidgetException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void setProperties(int serial, String ip, int port) {
        this.serial = serial;
        this.ip = ip;
        this.port = port;
    }

    public void openDevice() {
        try {
            interfaceKitPhidget.open(serial, ip, port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public InterfaceKitPhidget getInterfaceKitPhidget() {
        return interfaceKitPhidget;
    }

}
