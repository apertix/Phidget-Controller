package me.spencer.phc.core;

import com.phidgets.PhidgetException;
import me.spencer.phc.core.controllers.Controllers;
import me.spencer.phc.core.task.TaskManager;
import me.spencer.phc.core.task.tasks.DistanceTask;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Main {

    private final static TaskManager task = new TaskManager();

    public static void main(String[] args) {

        JFrame jFrame = new JFrame(); //Have to draw a JFrame to use the keyboard event stuff. Didn't want to use external library.
        JLabel jLabel = new JLabel();
        jFrame.setVisible(true);
        jFrame.setSize(360, 160);
        jFrame.setTitle("Click on window");
        if (args.length > 0 && Boolean.parseBoolean(args[0])) {
            task.addTask(new DistanceTask());
            task.start();
        } else {
            try {
                Controllers.getInterfaceController().setProperties(45633, "192.168.0.26", 5001);
                Controllers.getMotorController().setProperties(100337, "192.168.0.26", 5001);
                Controllers.getInterfaceController().openDevice();
                Controllers.getInterfaceController().getInterfaceKitPhidget().waitForAttachment(10000);
                Controllers.getMotorController().openDevice();
                Controllers.getMotorController().getMotorControlPhidget().waitForAttachment(10000);
            } catch (PhidgetException e) {
                e.printStackTrace();
            }

        }

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(e -> {
            switch(e.getKeyCode()) {
                case KeyEvent.VK_W:
                    Controllers.getMotorController().forwardBackward(50.0D);
                    System.out.println("W");
                    break;
                case KeyEvent.VK_A:
                    Controllers.getMotorController().turnLeft(50.0D);
                    System.out.println("A");
                    break;
                case KeyEvent.VK_S:
                    Controllers.getMotorController().forwardBackward(-50.0D);
                    System.out.println("S");
                    break;
                case KeyEvent.VK_D:
                    Controllers.getMotorController().turnRight(50.0D);
                    System.out.println("D");
                    break;
                case KeyEvent.VK_Q:
                    Controllers.getMotorController().stop();
                    System.out.println("Stop");
                    break;
            }
          return false;
        });
    }


}