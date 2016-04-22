package me.spencer.phc.core;

import com.phidgets.PhidgetException;
import me.spencer.phc.core.controllers.InterfaceController;
import me.spencer.phc.core.controllers.MotorController;
import me.spencer.phc.core.task.TaskManager;
import me.spencer.phc.core.task.tasks.DistanceTask;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class Main {

    private static InterfaceController interfaceController;
    private static MotorController motorController;
    private static TaskManager taskManager;

    public static void main(String[] args) throws NumberFormatException, PhidgetException, InterruptedException {
        if (args.length >= 2) {
            interfaceController = new InterfaceController(Integer.parseInt(args[0]), args[1], Integer.parseInt(args[2]));
            motorController = new MotorController(Integer.parseInt(args[0]), args[1], Integer.parseInt(args[2]));
            if (args.length == 3) {
                taskManager = new TaskManager();
                taskManager.addTask(new DistanceTask());
                taskManager.start();
            }
        } else {
            System.out.println("Usage: java -jar <jar> <serial> <ip> <port> [task]\n Press <Enter> to exit.");
            try{ System.in.read(); }catch(IOException e){e.printStackTrace();}
            System.exit(0);
        }

        interfaceController.openDevice();
        motorController.openDevice();
        System.out.println("Devices initialized...");

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(ke -> {
            try {
                switch (KeyEvent.KEY_PRESSED) {
                    case KeyEvent.VK_W:
                        motorController.moveForwardBackward(20.0D);
                        break;
                    case KeyEvent.VK_A:
                        motorController.moveForwardBackward(-20.0D);
                        break;
                    case KeyEvent.VK_D:
                        motorController.turnLeft(20.0D);
                        break;
                    case KeyEvent.VK_S:
                        motorController.turnRight(20.0D);
                        break;
                    default:
                        motorController.stop();
                        break;
                }
            } catch (PhidgetException e) {
                e.printStackTrace();
            }
            return false;
        });
    }

}