/**
 * Passenger class that represents entered passenger to queue
 * @author Anshul
 */
public class PassengerQueued {
	private int passengerID;
	
	/**
	 * Constructor to set the id for passenger
	 * @param id passenger id
	 */
	public PassengerQueued(int passengerID){
		this.passengerID = passengerID;
	}
	
	/**
	 * Getter method to get the passenger's id.
	 * @return passenger id
	 */
	public int getPassengerID(){
		return passengerID;
	}
}

