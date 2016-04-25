package me.spencer.phc.core.task.tasks;

import com.phidgets.PhidgetException;

import me.spencer.phc.core.controllers.Controllers;
import me.spencer.phc.core.task.Task;

public class DistanceTask implements Task {
	
	private float maxDistance = 170.0F;
	
	
	@Override
	public void execute() {
		while(Controllers.getInterfaceController().getDistance() < 170) {
			System.out.println(Controllers.getInterfaceController().getDistance());
			Controllers.getMotorController().forwardBackward(50.0D);
			
		}
		
		Controllers.getMotorController().stop();
		
	}
	/**
	 * Seriously, I hate this stupid Phidget API. However, it's better than
	 * writing this shit in VB.NET which can't run natively on the Phidgets
	 * anyway lmao.
	 */

}
