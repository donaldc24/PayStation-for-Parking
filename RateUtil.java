package hw2;

/**
 * Calculates the cost of parking based on time spent parked.
 * 
 * @author Donald Calhoun
 */

public class RateUtil {
	
	/**
	 * Time limit for machine
	*/
	public static final int EXIT_TIME_LIMIT = 15;
	
	private RateUtil() {
		
	}
	
	/**
	 * Returns the cost of parking for the given total number of minutes, based on the
	 * current rates for the MU garage. See the section "Current parking rates", below,
	 * for details.
	 * @param minutes
	 * 	 the minutes passed for cost
	 * @return
	 * 	 the cost of parking
	*/
	public static double calculateCost(int minutes) {
		double cost = 0;
		int minutesOverDay;
		int hours = (int)Math.ceil((double)minutes/60); // Rounds up to nearest hour
		
	    if(minutes <= 30) {
			cost = 1;
		} else if (minutes < 60 && minutes > 30) {
			cost = 2;
		} else if (minutes >= 60 && minutes <= 300) {
			cost = 2 + (hours - 1) * 1.50;
		} else if (minutes > 300 && minutes <= 480) {
			cost = 8 + (hours - 5) * 1.25;
		} else {
			if (minutes > 1440) {
				minutesOverDay = minutes - 1440;
				cost = 13 + calculateCost(minutesOverDay) + cost;
			} else {
				cost = 13;
			}
		}
	    
	    return cost;
	}

}
