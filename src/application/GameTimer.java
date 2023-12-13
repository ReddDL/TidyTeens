package application;


public class GameTimer implements Runnable{
	Thread gameThread;
	private boolean isRunning = true;
	private long startTime;
	private long elapsedTime;
	
	public void start() {
		startTime = System.currentTimeMillis();
	}
	
	public long getElapsedTime() {
		return elapsedTime;
	}
	
	
	@Override
	public void run() {
		while (isRunning) {
			elapsedTime = System.currentTimeMillis() - startTime;
    	   
		}
	}
}
