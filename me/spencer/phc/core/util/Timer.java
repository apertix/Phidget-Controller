package me.spencer.phc.core.util;

public class Timer {
	
	private long currentMS;
	
	public Timer() {
		currentMS = System.currentTimeMillis();
	}
	
	
	public void resetMS() {
		this.currentMS = System.currentTimeMillis();
	}
	
	public boolean delayExceeded(long ms) {
		if (ms - currentMS >= ms) {
			return true;
		}
		
		return false;
	}

}
