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
	private int timeEntered;
    private int numDoors;
    private int numWheels;
	private int year;
	private String color;	
	private String make;

/**
 * Constructor of the class. Default Vehicles will be created, however changes can be made using mutators/setters.	
 * @param type heavy = h or light = l vehicle.
 */
	public Vehicle (char type) {
		
		if (type == 'c' || type == 't')
			
		if (type == 't') {
			this.type = 't';
			numDoors = 2;
			year = 2000;
			numWheels = 18;
			color = "White";
			make = "GM";			
		}
		
		else {
			
			this.type = 'c';
			numDoors = 4;
			year = 2010;
			numWheels = 4;
			color = "Red";
			make = "Toyota";
		}
		
		this.type = type;				
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
	
	public int getNumDoors() {
		return numDoors;
	}

	public void setNumDoors(int numDoors) {
		
		if (numDoors >= 2 && numDoors <= 4)
		this.numDoors = numDoors;
	}


	public int getNumWheels() {
		return numWheels;
	}

	public void setNumWheels(int numWheels) {
		
		if (numWheels > 4 && numWheels < 18 && numWheels%2 == 0 )
			
		this.numWheels = numWheels;
	}


	public int getYear() {
		
		return year;
	}


	public void setYear(int year) {
		
		this.year = year;
	}


	public String getColor() {
		
		return color;
	}


	public void setColor(String color) {
		
		this.color = color;
	}


	public String getMake() {
		
		return make;
	}


	public void setMake(String make) {
		
		this.make = make;
	}
	
 public String toString () {
	String str = "";
	
	str += "Make: " + make + " Color: " + color + " Number of doors: " + numDoors + " Number of wheels: " + numWheels + " Year: " + year;
	
	return str;
 }
 
 //----------------------------------------------------------------------------------
 //Unit testing
 
 public static void main (String [] args) {
	 
	 Vehicle car = new Vehicle ('l');
	 car.setMake("Honda");
	System.out.println( car.toString() );
 }
}
