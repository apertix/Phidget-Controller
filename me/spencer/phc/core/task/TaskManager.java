package me.spencer.phc.core.task;

import java.util.ArrayList;

public class TaskManager {
	
	private Runnable thread;
	private final ArrayList<Task> tasks = new ArrayList<>();
	
	public TaskManager() {
		thread = () -> {
			for(Task task : tasks) {
				executeTask(task);
			}
		};
	}
	
	public void addTask(Task task) {
		if (!tasks.contains(task)) {
			tasks.add(task);
		}
	}
	
	public void start() {
		thread.run();
	}
	
	public void executeTask(Task task) {
		task.execute();
	}
	
}
