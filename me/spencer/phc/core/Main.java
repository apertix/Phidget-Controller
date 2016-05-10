package me.spencer.phc.core;

import com.phidgets.PhidgetException;
import me.spencer.phc.core.controllers.Controllers;
import me.spencer.phc.core.task.TaskManager;
import me.spencer.phc.core.task.tasks.DistanceTask;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.nio.file.WatchKey;

public class Main {

	private final static TaskManager task = new TaskManager();
	private static boolean wKey, aKey, sKey, dKey, qKey = false;

	public static void main(String[] args) {
		JFrame jFrame = new JFrame(); //Have to draw a JFrame to use the keyboard event stuff. Didn't want to use external library.
		JLabel jLabel = new JLabel();
		jFrame.setVisible(true);
		jFrame.setSize(360, 160);
		jFrame.setTitle("Click on window");
		try {
			Controllers.getInterfaceController().setProperties(45633, "192.168.0.62", 5001);
			Controllers.getMotorController().setProperties(100337, "192.168.0.62", 5001);
			Controllers.getInterfaceController().openDevice();
			Controllers.getInterfaceController().getInterfaceKitPhidget().waitForAttachment();
			Controllers.getMotorController().openDevice();
			Controllers.getMotorController().getMotorControlPhidget().waitForAttachment(10);
		} catch (PhidgetException e) {
			e.printStackTrace();
		}

		if (args.length > 0 && Boolean.parseBoolean(args[0])) {
			task.addTask(new DistanceTask());
			task.start();

		}

		jFrame.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				Controllers.getMotorController().stop();
				System.out.println("Stop");
				wKey = wKey ? false : true;
				aKey = aKey ? false : true;
				sKey = sKey ? false : true;
				dKey = dKey ? false : true;
			}

			@Override
			public void keyPressed(KeyEvent e) {
				switch(e.getKeyCode()) {
				case KeyEvent.VK_W:
					if (!wKey) {
						Controllers.getMotorController().forwardBackward(100.0D);
						System.out.println("W");
						wKey = true;
						
					}
					break;
				case KeyEvent.VK_A:
					if (!aKey) {
						Controllers.getMotorController().turnLeft(100.0D);
						System.out.println("A");
						aKey = true;
						
					}
					break;
				case KeyEvent.VK_S:
					if (!sKey) {
						Controllers.getMotorController().forwardBackward(-100.0D);
						System.out.println("S");
						sKey = true;
						
					}
					break;
				case KeyEvent.VK_D:
					if (!dKey) {
						Controllers.getMotorController().turnRight(100.0D);
						System.out.println("D");
						dKey = true;
						
					}
					break;
					
				}
				

			}

		});


	}}