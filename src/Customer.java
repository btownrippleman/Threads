import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Customer {
	
	private final static Random NumTimeGen = new Random(  );
	private static int timeMin = 6000/10;
    private static int timeMax = 18000/10;
	
	Customer(int NumItems, DressingRooms dr) 
	{		
		Thread t1 = new Thread(new Runnable() {
			public void run()  {
				
				for(int j = 1; j <= NumItems; j++) {

					Instant start = Instant.now();
					
					try {
						
						float timeInRoom = NumTimeGen.nextInt(timeMax-timeMin) + timeMin;
						dr.totalCustomerTime = dr.totalCustomerTime + timeInRoom;
						Thread.sleep((long) timeInRoom);
						dr.itemsTried++;
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Instant finish = Instant.now();
					long timeElapsed = Duration.between(start, finish).toMillis();
					System.out.println("Customer " + Thread.currentThread().getId() + " tried on clothing Item Number " + j + " ,time spent: " + timeElapsed);
					
				}
			}
			
			
		});

		t1.start();	
		try {
			t1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}		

}
