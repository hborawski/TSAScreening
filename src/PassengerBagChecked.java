
public class PassengerBagChecked {
	private int passengerID;
	private boolean result;
	
	public PassengerBagChecked(int passID, boolean result){
		this.result = result;
		passengerID = passID;
	}
	
	public int getPassengerID(){
		return passengerID;
	}
	
	public boolean getResult(){
		return result;
	}
}
