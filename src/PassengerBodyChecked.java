/**
 * Class that represents a scanned body
 * @author Anshul
 */
public class PassengerBodyChecked {
	private int passengerID;
	private boolean result;
	
	/**
	 * Public constructor for the checked body class
	 * @param passID passenger id
	 * @param result true/false indicating if the body passed or failed the scan
	 */
	public PassengerBodyChecked(int passID, boolean result){
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
