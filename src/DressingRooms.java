import java.util.Random;
import java.util.concurrent.Semaphore;

public class DressingRooms {
	public Semaphore available = null;
	private final static Random ClothingItemsGenerator = new Random();
	public static int itemsTried = 0;
	public float totalCustomerTime = 0;
	private static int max = 20;
	private static int min = 1;

	public DressingRooms(int NumRooms, int CustNum, int NumItems) {
		available = new Semaphore(NumRooms, false);
		for (int i = 0; i < CustNum; i++) {
			itemsTried++;
			if (NumItems == 0)
				NumItems = ClothingItemsGenerator.nextInt((max - min) + 1) + min;
			if (available.hasQueuedThreads()) {
				available.release();
			} else {
				try {
					available.acquire();

				} catch (InterruptedException e) {
					available.release();
					e.printStackTrace();
				}
			}
			Customer c = new Customer( NumItems, this);

		}
	}
}
