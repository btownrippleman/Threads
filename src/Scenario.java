import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Scanner;

public class Scenario {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scan = new Scanner(System.in);

		System.out.print("Input the number of dressing rooms for this scenario: ");
		int NumRooms = scan.nextInt();
		System.out.println("");

		System.out.print("Input the number of customers for this scenario: ");
		int NumCus = scan.nextInt();
		System.out.println("");

		System.out.print(
				"Input the number of clothing items for this scenario(input of '0' will result in a random number of items): ");
		int NumItems = scan.nextInt();
		System.out.println("");

		Date startDate = new Date();
		System.out.println("The start time is: " + startDate.toString());
		System.out.println("");

		DressingRooms dr = new DressingRooms(NumRooms, NumCus, NumItems);

		Instant start = Instant.now();
		while (dr.itemsTried < NumCus * NumItems) {
			System.out.println(dr.available.getQueueLength()+" = total number of threads going at once");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		Date endDate = new Date();
		String totalTime = String.format("%f", dr.totalCustomerTime / 1000.0);
//		totalTime = Duration.between(start, Instant.now()).toString()

		
		System.out.println("The end time is: " + endDate.toString());
		System.out.println("");
		System.out.print("Total seconds passed: ");
		System.out.format("%.3f%n", Duration.between(start, Instant.now()).toMillis() / 1000.0);
		System.out.println("Whereas if there was only one room avaiable");
		System.out.println("It would have taken " + totalTime + " seconds");
		System.out.println("");
		System.out.println("Total number of items: " + NumCus * NumItems);
		System.out.println("");

	}

}