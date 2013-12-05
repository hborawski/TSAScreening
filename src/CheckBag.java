/**
 * Class that represents a checked bag
 * @author Anshul
 */
public class CheckBag {
	private int bagID;
	
	/**
	 * Constructor for the bag class
	 * @param bagID id of the bag
	 */
	public CheckBag(int bagID){
		this.bagID = bagID;
	}
	
	/**
	 * Getter method to get the bag id
	 * @return
	 */
	public int getBagID(){
		return bagID;
	}
}
