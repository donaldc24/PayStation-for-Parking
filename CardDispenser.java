package hw2;

/**
 * Constructs a new ParkingCard and gives a start time based on clock and sets a \
 * payment time as zero
 * 
 * @author Donald Calhoun
 */

public class CardDispenser {
	
	/**
	 * used to keep track of current time
	*/
	private TimeClock givenTime;
	
	/**
	 * Constructs a CardDispenser that uses the given clock.
	 * @param
	 * 	 time clock
	*/
	public CardDispenser(TimeClock givenClock) {
		givenTime = givenClock;
	}
	
	/**
	 * Constructs and returns a new ParkingCard object. The constructed card will
	 * have a start time based on the current value of the card dispenser's clock and a
	 * payment time of zero.
	 * @return
	 *	 parking card
	*/
	public ParkingCard takeCard() {
		int time = givenTime.getTime();
		ParkingCard card = new ParkingCard(time);
		card.setPaymentTime(0);
		
		return card;
	}

}
