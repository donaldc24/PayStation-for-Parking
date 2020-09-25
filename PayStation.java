package hw2;

/**
 * Allows you to enter card and if transaction is in progress it will calulate
 * amount due amd the total payments made. Also allows to eject card.
 * 
 * @author Donald Calhoun
 */

public class PayStation {
	
	/**
	 * used to keep track of current time
	*/
	private TimeClock clock;
	
	/**
	 * True/False depending on if card is in machine
	*/
	private boolean currentlyInProgress;
	
	/**
	 * Used to store current ParkingCard
	*/
	private ParkingCard current;
	
	/**
	 * Keeps track of machine total
	*/
	private double machineTotal;
	
	/**
	 * Constructs a PayStation that uses the given clock. Initially, total payments are 0.0.
	 * @param givenClock
	 * 	 given clock
	*/
	public PayStation(TimeClock givenClock) {
		clock = givenClock;
	}
	
	/**
	 * Simulates inserting the given card into this machine. This method does not
	 * modify the ParkingCard object or perform any calculation with it. After calling
	 * this method, the inProgress() method returns true until a subsequent call to
	 * ejectCard(). Calling insertCard() while a transaction is in progress has no
	 * effect.
	 * @param t
	 * 	 uses ParkingCard
	*/
	public void insertCard(ParkingCard t) {
		currentlyInProgress = true;
		current = t;
	}
	
	/**
	 * Returns a reference to the card currently in this machine, or null if no transaction
	 * is in progress.
	 * @return
	 * 	 the card in the machine or null if none
	*/
	public ParkingCard getCurrentCard() {
		if (inProgress() == true) {
			return current;
		} else {
			return null;
		}
	}
	
	/**
	 * Returns true if there is currently a card in this machine, false otherwise.
	 * @return
	 * 	 true/false depending on if card is in machine
	*/
	public boolean inProgress() {
		return currentlyInProgress;
	}
	
	/**
	 * Returns the payment due for the card currently in the machine. If no transaction
	 * is in progress, returns 0.0. This method does not modify the ParkingCard object
	 * or update this machine's total payments. The payment due is based the current
	 * time (according to this machine's clock) and on the start time and payment time
	 * for the current card. The basic amount due is the result of calling
	 * RateUtil.calculateCost() for the difference current time - start time.
	 * However, if the payment time is nonzero (indicating that some payment was
	 * already made), the cost of parking from start time to payment time (i.e., the
	 * amount that must have already been paid) is subtracted from the amount due.
	 * @return
	 * 	 the amount owed for parking
	*/
	public double getPaymentDue() {
		double amtDue = 0.0;
		if (inProgress() == true) {
			// If there is a given payment time other than 0 then calculate amount due
			if (current.getPaymentTime() != 0) {
				amtDue = RateUtil.calculateCost(clock.getTime() - current.getStartTime()) - RateUtil.calculateCost(current.getPaymentTime() - current.getStartTime());
			} else {
				amtDue = RateUtil.calculateCost(clock.getTime() - current.getStartTime());
			}
		}
		return amtDue;
	}

	/**
	 * Updates the current card with the payment time and adds the payment amount to
	 * this machine's total. If there is no transaction in progress, this method has no
	 * effect.
	*/
	public void makePayment() {
		int payTime = current.getPaymentTime();
		if (inProgress() == true) {
			// MAchine Total is calculated based on whether a payment time is given
			if (current.getPaymentTime() != 0) {
				machineTotal =  machineTotal + RateUtil.calculateCost(clock.getTime() - current.getStartTime()) - RateUtil.calculateCost(payTime - current.getStartTime());
			} else {
				machineTotal =  machineTotal + RateUtil.calculateCost(clock.getTime() - current.getStartTime());
			}
			current.setPaymentTime(clock.getTime()); // Sets payment time to the current time
		}
	}

	/**
	 * Simulates ejecting a card from this machine, after which another card can be
	 * inserted. This method does not modify the current card object or update this
	 * machine's total payments. If there is no transaction in progress, this method has
	 * no effect.
	*/
	public void ejectCard() {
		if (inProgress() == true) {
			currentlyInProgress = false;
		}
	}

	/**
	 * Returns the total payments that have been made at this machine.*
	 * @return
	 * 	  payments been made
	*/
	public double getTotalPayments() {
		return machineTotal;
	}
}
