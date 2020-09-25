package hw2;

/**
 * Keeps track of the amount of times you use the machine to exit. Only 
 * works if within time limit from payment time
 * 
 * @author Donald Calhoun
 */

public class ExitGate {
	
	/**
	 * used to keep track of current time
	*/
	private TimeClock givenTime;
	
	/**
	 * Gets time limit from RateUtil
	*/
	private int timeLimit = RateUtil.EXIT_TIME_LIMIT;
	
	/**
	 * Keeps track of currentExitCount
	*/
	private int currentExitCount;
	
	/**
	 * Constructs an ExitGate that uses the given clock and has an initial count of zero.
	 * @param givenClock
	 * 	 time clock
	*/
	public ExitGate(TimeClock givenClock) {
		givenTime = givenClock;
	}
	
	/**
	 * Simulates inserting a card into this machine. If the card's payment time is within
	 * RateUtil.EXIT_TIME_LIMIT minutes of this machine's clock time (and is greater
	 * than zero), the method returns true. Otherwise the method returns false. The
	 * ParkingCard object is not modified. If the method returns true, this machine's
	 * exit count is incremented. (Note that this method is a mutator method that also
	 * returns a value.)
	 * @param c
	 * 	 ParkingCard c
	 * @return
	 * 	 true/false based the time limit
	*/
	public boolean insertCard(ParkingCard c) {
		
		// If payment time is within a 15 minute time limit then returns true
		if (givenTime.getTime() - c.getPaymentTime() <= timeLimit && c.getPaymentTime() > 0) {
			currentExitCount = currentExitCount + 1; // Adds a count to exit counter
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Returns a count of the total number of successful exits. A "successful exit" is
	 * defined to be a call to insertCard() that returns true. 
	 * @return
	 * 	 count of successful exit
	*/
	public int getExitCount() {
		return currentExitCount;
	}
}
