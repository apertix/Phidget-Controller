package me.spencer.phc.core.task.tasks;

import com.phidgets.PhidgetException;

import me.spencer.phc.core.controllers.Controllers;
import me.spencer.phc.core.task.Task;
import me.spencer.phc.core.util.Timer;

public class DistanceTask implements Task {
	
	private double maxDistance = 15.0;
	private Timer timer;
	private boolean hasRun = false;
	
	
	@Override
	public void execute() {
		while(Controllers.getInterfaceController().getDistanceInCM() < maxDistance) {
			if (Controllers.getInterfaceController().getDistanceInCM() < maxDistance && !hasRun) {
				System.out.println(Controllers.getInterfaceController().getDistanceInCM()+"cm");
				Controllers.getMotorController().forwardBackward(100.0D);
				hasRun = true;
			} else {
				Controllers.getMotorController().stop();
				Controllers.getMotorController().turnRight(100.0D);
				timer = new Timer();
				if (timer.delayExceeded(2400L)) {
					Controllers.getMotorController().stop();
					timer.resetMS();
					hasRun = false;
				}
			}
 			
		}			
		
		
		
		
		
	}
	/**
	 * Seriously, I hate this stupid Phidget API. However, it's better than
	 * writing this shit in VB.NET which can't run natively on the Phidgets
	 * anyway lmao.
	 */

}
