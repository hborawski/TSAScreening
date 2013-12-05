
/**
 * Passenger class that represents the passenger
 * @author Anshul
 */
public class Passenger {

	int id;
	boolean legality = false;
	
	/**
	 * Constructor to set the id for a new passenger
	 * @param id passenger id
	 */
	public Passenger(int id){
		this.id = id;
	}
	
	/**
	 * Getter method to get the passenger's id.
	 * @return passenger id
	 */
	public int getId(){
		return id;
	}
	
	/**
	 * Set if a passenger is good to proceed or if it should go to Jail
	 * @param value true/false
	 */
	public void setLegality(boolean value){
		legality = value;
	}
	
	/**
	 * Getter method to check the legality of a passenger
	 * @return legality
	 */
	public boolean getLegality(){
		return legality;
	}
}

