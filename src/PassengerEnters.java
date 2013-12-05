/**
 * Passenger class that represents entered passenger to document check
 * @author Anshul
 */
public class PassengerEnters {
	private int passengerID;
	
	/**
	 * Constructor to set the id for passenger
	 * @param id passenger id
	 */
	public PassengerEnters(int passengerID){
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
