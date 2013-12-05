/**
 * Class that represents a checked in passenger
 * @author Anshul
 */
public class CheckPassenger {
	private int passengerID;
	
	/**
	 * Public constructor for the class
	 * @param passID passenger id
	 */
	public CheckPassenger(int passID){
		this.passengerID = passID;
	}
	
	/**
	 * Getter method for getting the passenger id
	 * @return
	 */
	public int getPassengerID(){
		return passengerID;
	}
}
