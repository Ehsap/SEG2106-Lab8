public class Chopstick {
	private int ID;
	private boolean free;
// hint: use a local variable to indicate whether the chopstick is free 
//                        (lying on the table), e.g.  private boolean free;

	Chopstick(int ID) {
		  this.ID = ID;
		  this.free = true;
	}
	
	synchronized void take() {
		//Wait until the chopstick is free
		while (!free) {
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		free = false;
		//System.out.println("Chopstick: " + getID() + " is taken");
		notifyAll();

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
