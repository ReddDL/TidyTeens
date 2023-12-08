package application;


public class GameTimer implements Runnable{
	Thread gameThread;
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	@Override
	public void run() {
		
		
	}

}
