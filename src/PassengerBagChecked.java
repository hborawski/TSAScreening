/**
 * Class that represents a scanned bag
 * @author Anshul
 */
public class PassengerBagChecked {
	private int passengerID;
	private boolean result;
	
	/**
	 * Public constructor for the checked bag class
	 * @param passID passenger id
	 * @param result true/false indicating if the bag passed or failed the scan
	 */
	public PassengerBagChecked(int passID, boolean result){
		this.result = result;
		passengerID = passID;
	}
	
	/**
	 * Getter method to get the passenger id
	 * @return passenger id
	 */
	public int getPassengerID(){
		return passengerID;
	}
	
	/**
	 * Getter method to get the scan result
	 * @return boolean result
	 */
	public boolean getResult(){
		return result;
	}
}
