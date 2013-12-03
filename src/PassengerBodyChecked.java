
public class PassengerBodyChecked {
	private int passengerID;
	private boolean result;
	
	public PassengerBodyChecked(int passID, boolean result){
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
