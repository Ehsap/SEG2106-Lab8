public class Chopstick {
	private int ID;
	private boolean free;
	private int waitTime = 2000;
// hint: use a local variable to indicate whether the chopstick is free 
//                        (lying on the table), e.g.  private boolean free;

	Chopstick(int ID) {
		  this.ID = ID;
		  this.free = true;
	}
	
	synchronized boolean take() {
		//Wait until the chopstick is free
		if (!free) {
			try {
				wait(waitTime);

			} catch (InterruptedException e) { }
		}

		if (free) {
			free = false;
			notifyAll();
			return true;
		}else{
			return false;
		}
	}
	
	synchronized void release() {
		while (free) {
			try {
				wait();
			} catch (InterruptedException e){}
		}
		free = true;
		//System.out.println("Chopstick: " + getID() + " is released");
		notifyAll();
	}
	    
	public int getID() {
	    return(ID);
	}
}
