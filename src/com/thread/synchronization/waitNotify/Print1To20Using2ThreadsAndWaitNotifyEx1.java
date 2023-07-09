package com.thread.synchronization.waitNotify;

//Class on which wait and notify is getting called
class Holder{
	int state;
	boolean stateChanged;
	Holder(int i, boolean state){
		this.state=i;
		this.stateChanged = state;
	}
	void printer() throws InterruptedException {
		for(int i=1;i<=10;i++) {
			synchronized (this) {
				if(stateChanged == false) {
					//System.out.println(Thread.currentThread().getName() + " entering into waiting state");
					stateChanged = true; 
					this.wait();  
				}
				System.out.println(Thread.currentThread().getName() + " printed " + state);
				Thread.sleep(1000);
				state = state+1;
				stateChanged = false;
				notify();
			}
		}
	}
}

//class to create a new thread
class Threads extends Thread{
	
	Holder obj;
	
	Threads(Holder obj){
		this.obj = obj;
	}

	@Override
	public void run() {
		try {
			obj.printer();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}


//Main method to start the new thread
public class Print1To20Using2ThreadsAndWaitNotifyEx1 {

	public static void main(String[] args) throws InterruptedException {
		Holder hold = new Holder(1, false);
		Threads thread1 = new Threads(hold);
		thread1.start();
		//System.out.println(Thread.currentThread().getName() + " sleeping");
		Thread.sleep(5000);
		hold.printer();
	}

}
