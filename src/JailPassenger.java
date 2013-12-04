
public class JailPassenger {
	private Passenger p;
	
	public JailPassenger(Passenger p){
		this.p = p;
	}
	
	public int getPassengerID(){
		return p.getId();
	}
}
