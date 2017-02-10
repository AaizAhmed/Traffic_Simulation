/**
  * This class represents a vehicle for this project.
  *	@author Aaiz Ahmed <aaiza2@umbc.edu>
  * @version Feb 20, 2014
  * @project CMSC 341 - Spring 2014 - Project #1 Traffic simulator.
  * @section 01
*/
package Project1;

public class Vehicle {
	
	private char type;
	private int timeEntered, ID;
   	
	private int leaveAt;
	
	private boolean waiting = false;

	public void setGreenEntered(int i)
	{
		if (waiting == false)
		{	
			if (type == 'c')
			{
				leaveAt = i + 1;
			}
			else if (type == 't')
			{
				leaveAt = i + 2;
			}
			
			//System.out.println( leaveAt + " " + i );
			waiting = true;
		}
	}

	public int getLeaveAt()
	{
		return leaveAt;
	}
	
	public int getID()
	{
		return ID;
	}

/**
 * Constructor of the class. Default Vehicles will be created, however changes can be made using mutators/setters.	
 * @param type heavy = h or light = l vehicle.
 */
	public Vehicle (char type, int id) {
		
		if (type == 'c' || type == 't')
		{
			this.type = type;
			ID = id;
		}						
	}

	public char getType () {
		
		return type;
	}
	
	public void setTimeEntered (int i) {
		
		timeEntered = i;
	}
	
	public int getTimeEntered () {
		
		return timeEntered;
	}
	
 public String toString () {
	String str = "";
	
	str += "Vehicle Type: " + type + "  Vehicle ID: " + ID;
	
	return str;
 }
 
 //----------------------------------------------------------------------------------
 //Unit testing
 
 public static void main (String [] args) {
	 
	 Vehicle car = new Vehicle ('l', 0);
	 
	System.out.println( car.toString() );
 }
}
