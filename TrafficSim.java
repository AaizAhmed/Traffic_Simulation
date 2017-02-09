package Project1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class TrafficSim {

	private String fileName;
	private IntersectionFlowRate flowRate;
	private Queue<Vehicle> eastBound; 
	private Queue<Vehicle> westBound; 
	private Queue<Vehicle> northBound; 
	private Queue<Vehicle> southBound; 
	private static int timer = 0; 
	private String direction;
	private int wait;
	private Project1.LinkedList result;

	/**
	 * Constructor: It takes name of input file as parameter. 
	 * @param inputFile
	 */
	public TrafficSim (String inputFile) {

		eastBound = new LinkedList<Vehicle>();
		westBound = new LinkedList<Vehicle>();
		northBound = new LinkedList<Vehicle>();
		southBound = new LinkedList<Vehicle>();
		result = new Project1.LinkedList();
		
		Vehicle v = new Vehicle('c');
		Vehicle v2 = new Vehicle('c');

		//Adding initial 2 vehicles in each queue.
		northBound.add(v); northBound.add(v2);
		southBound.add(v); southBound.add(v2);
		eastBound.add(v); eastBound.add(v2);
		westBound.add(v); westBound.add(v2);

		flowRate = new IntersectionFlowRate();
		fileName = inputFile;
		readFromFile ();
		System.out.println (printBoard() );
		
		//Calling methods
		assignFlowRates();
		addToQueue();		
	}	


	private String readFromFile () {

		String input = "";
		//Dealing with exception by try and catch block.
		try {
			FileReader file = new FileReader(fileName);
			BufferedReader buf = new BufferedReader(file);
			for (int i = 0; i < 4; i++) {

				input += buf.readLine() + " ";
			}

		}
		catch(IOException e) {
			System.out.println ("Input problem, File not found");
		}

		return input;
	}

	private void assignFlowRates () {

		String temp = readFromFile();
		String [] input = temp.split(" ");

		//Setting flow rate of North side cars & trucks.
		int num = Integer.parseInt(input[1]);
		flowRate.setNorthFlowRateCars(num);	

		num = Integer.parseInt(input[2]);
		flowRate.setNorthFlowRateTrucks(num);

		//South
		num = Integer.parseInt(input[4]);
		flowRate.setSouthFlowRateCars(num);

		num = Integer.parseInt(input[5]);
		flowRate.setSouthFlowRateTrucks(num);

		//East
		num = Integer.parseInt(input[7]);
		flowRate.setEastFlowRateCars(num);

		num = Integer.parseInt(input[8]);
		flowRate.setEastFlowRateTrucks(num);

		//West
		num = Integer.parseInt(input[10]);
		flowRate.setWestFlowRateCars(num);

		num = Integer.parseInt(input[11]);
		flowRate.setWestFlowRateTrucks(num);		

		//Getting direction.
		String direction = input[0].substring(0, 1);
		direction += input[3].substring(0, 1);
		direction += input[6].substring(0, 1);
		direction += input[9].substring(0, 1);



		this.direction = direction;
		//	Vehicle v = new Vehicle ('t');

		//	addVehicle('N', v); System.out.println (northBound.size());

		//	return direction;
	}

	private void addVehicle (char direction, Vehicle x) {

		switch (direction) {		

		case 'N' : northBound.add(x);	
		break;
		case 'S' : southBound.add(x);
		break;
		case 'E' : eastBound.add(x);
		break;
		case 'W' : westBound.add(x);
		break;

		}

	}

	public void addToQueue () {

		int northCarFlow, northTruckFlow, southCarFlow, southTruckFlow, eastCarFlow, eastTruckFlow, westTruckFlow, westCarFlow;

		northCarFlow = 60/flowRate.getNorthFlowRateCars();
		northTruckFlow = 60/flowRate.getNorthFlowRateTrucks();

		southCarFlow = 60/flowRate.getSouthFlowRateCars();
		southTruckFlow = 60/flowRate.getSouthFlowRateTrucks();

		eastCarFlow = 60/flowRate.getEastFlowRateCars();
		eastTruckFlow = 60/flowRate.getEastFlowRateTrucks();

		westCarFlow = 60/flowRate.getWestFlowRateCars();
		westTruckFlow = 60/flowRate.getWestFlowRateTrucks();	

		Vehicle c;  Vehicle temp;
		Vehicle t;
		
		int count = 1, leave = 0;   boolean car = false, truck = false;
		//Adding cars to the queue according to the flow rates.
		for (int i = 1; i < 121; i++) {			

			//North Flow
			if ( i % northCarFlow == 0) {

				c = new Vehicle('c');
				c.setTimeEntered(i);

				addVehicle('N', c); //System.out.println("car added");
			}

			if (i % northTruckFlow == 0) {

				t = new Vehicle('t');
				t.setTimeEntered(i);

				addVehicle('N', t);
			}

			//South Flow
			if ( i % southCarFlow == 0) {

				c = new Vehicle('c');
				c.setTimeEntered(i);

				addVehicle('S', c);
			}

			if (i % southTruckFlow == 0) {

				t = new Vehicle('t');
				t.setTimeEntered(i);

				addVehicle('S', t);
			}

			//East Flow
			if ( i % eastCarFlow == 0) {

				c = new Vehicle('c');
				c.setTimeEntered(i);

				addVehicle('E', c);
			}

			if (i % eastTruckFlow == 0) {

				t = new Vehicle('t');
				t.setTimeEntered(i);

				addVehicle('E', t);
			}

			//West Flow
			if ( i % westCarFlow == 0) {

				c = new Vehicle('c');
				c.setTimeEntered(i);

				addVehicle('W', c);
			}

			if (i % westTruckFlow == 0) {

				t = new Vehicle('t');
				t.setTimeEntered(i);

				addVehicle('W', t);
			}

			//Removing vehicles
			if (i < 31  ) {			
			
				if (northBound.size() > 0) { 
					
					temp = northBound.peek();
					
					
					if ( count == 1 && temp.getType() == 'c') {							
											
						result.add(temp, i - temp.getTimeEntered());					
						northBound.remove(); 
						count = 0; leave = i;
					}		
					
					if ( count == 2 && temp.getType() == 't') {									

						result.add(temp, i - temp.getTimeEntered());					
						northBound.remove();
						count = 0;
					}			
				
					
					if ( temp.getType() == 'c' && i > leave)  { 
						count = 1;   
						
					}
				
					if (temp.getType() == 't') {
						count = 2;
					}  

				}
				
				//South bound remove
				if (southBound.size() > 0) { 
					
					temp = southBound.peek();
					
					
					if ( count == 1 && temp.getType() == 'c') {							
											
						result.add(temp, i - temp.getTimeEntered());					
						southBound.remove(); 
						count = 0; leave = i;
					}		
					
					if ( count == 2 && temp.getType() == 't') {									

						result.add(temp, i - temp.getTimeEntered());					
						southBound.remove();
						count = 0;
					}			
				
					
					if ( temp.getType() == 'c' && i > leave)  { 
						count = 1;   
						
					}
				
					if (temp.getType() == 't') {
						count = 2;
					}  

				}/*{
					
					temp = southBound.peek();
					result.add(temp, i - temp.getTimeEntered());

					southBound.remove();
				} */

			} 

			if( i >= 31 && (eastBound.size() > 0 || westBound.size() > 0 ) ) {
				
			/*	if (i == 31) {
					System.out.println ("Lights change");
					} */

				if (eastBound.size() > 0) {
					
					temp = eastBound.peek();
					result.add(temp, i - temp.getTimeEntered());
					
					eastBound.remove();
				}
				
				if ( westBound.size() > 0) {

					temp = westBound.peek();
					result.add(temp, i - temp.getTimeEntered());
					
					westBound.remove();
				}
			}   
			
			if ( (i >= 41 && i < 61) && (eastBound.size() > 0|| westBound.size() > 0) ) {
				
				if (eastBound.size() > 0) {
					
					temp = eastBound.peek();
					result.add(temp, i - temp.getTimeEntered());
					
					eastBound.remove();
				}
				
				if ( westBound.size() > 0) {

					temp = westBound.peek();
					result.add(temp, i - temp.getTimeEntered());
					
					westBound.remove();
				}
				
				
			}
			//System.out.println("i = " + i);
			timer++;
			System.out.println (printBoard());
		}
		
		
		System.out.println("Size of linked list: " + result.getSize());
		System.out.println("Average: " + result.average() + "\nCar, Trucks: " + result.getCars() + " " + result.getTrucks());
	}

	public String printBoard( ) {

		String str = "";

		//Line 6
		str += "   SB ";
		if (southBound.size() >= 6) {

			str += "x  " + southBound.size() + "\n";
		}
		else {

			str += "   " + southBound.size() + "\n";
		}

		//Line 5
		if (southBound.size() >= 5) {

			str += "      x  \n";
		}
		else {

			str += "   \n";
		}

		//Line 4
		if (southBound.size() >= 4) {

			str += "      x  \n";
		}
		else {

			str += "   \n";
		}

		//Line 3
		if (southBound.size() >= 3) {

			str += "      x  \n";
		}
		else {

			str += "   \n";
		}

		//Line 2
		if (southBound.size() >= 2) {

			str += "EB    x\n";
		}
		else {
			str += "EB     \n";
		}

		//Line 1
		if (southBound.size() >= 1) {

			if (eastBound.size() < 10) {

				str += " " + eastBound.size() + "    " + southBound.element().getType() + "\n";
			}
			else 
				str += eastBound.size() + "    " + southBound.element().getType() + "\n";	
		}

		if (southBound.size() == 0) {

			if (eastBound.size() < 10) {

				str += " " + eastBound.size() + "\n";
			}
			else 
				str += eastBound.size() + "\n";
		}


		//Intersection, Nested If Else to print out West bound list
		if (westBound.size() >= 6) {

			str += "xxxxx" + westBound.element().getType() + " ";
		}
		else {
			if (westBound.size() >= 5) {

				str += " xxxx" + westBound.element().getType() + " ";
			}
			else 
				if (westBound.size() >= 4) {

					str += "  xxx" + westBound.element().getType() + " ";
				}
				else
					if (westBound.size() >= 3) {

						str += "   xx" + westBound.element().getType() + " ";
					}
					else 
						if (westBound.size() >= 2) {

							str += "    x" + westBound.element().getType() + " ";
						}
						else 
							if (westBound.size() >= 1) {

								str += "     " + westBound.element().getType() + " ";
							}
							else 
								if (westBound.size() == 0) {
									
									str += "       ";
								}
		}

		//Nested If Else statements to print out west bound queue.
		if (eastBound.size() >= 6) {

			str += eastBound.element().getType() + "xxxxx\n";
		}
		else {
			if (eastBound.size() >= 5) {

				str += eastBound.element().getType() + "xxxx \n";
			}
			else {
				if (eastBound.size() >= 4) {

					str += eastBound.element().getType() + "xxx  \n";
				}
				else 
					if (eastBound.size() >= 3) {

						str += eastBound.element().getType() + "xx   \n";
					}
					else
						if (eastBound.size() >= 2) {

							str += eastBound.element().getType() + "x    \n";
						}
						else
							if (eastBound.size() >= 1) {

								str += eastBound.element().getType() + "     \n";
							}
							else
								if (eastBound.size() >= 0) {

									str += "\n";
								}
			}
		}
		
		//Printing north Bound
		if (northBound.size() > 0) {

			str += "      " + northBound.element().getType() + "\n";
		}

		if (northBound.size() > 1) {

			str += "      x     WB\n";
		}
		else {
			str += "           WB\n";
		}

		//Fix this 
		if (northBound.size() <= 2) {

			str += "       ";

			if (westBound.size() < 10) {
				str +=  "     " + westBound.size() + "\n";
			}

			else {
				//adding space here
				str +=  "    " + westBound.size() + "\n";
			}
		}		

		if (northBound.size() >= 3) {

			str += "      x";

			if (westBound.size() < 10) {

				str +=  "     " + westBound.size() + "\n";
			}

			else {
				str +=  "     " + westBound.size() + "\n";
			}

		}		

		if (northBound.size() >= 4) {

			str += "      x\n";
		}
		else {

			str += "       \n";
		}

		if (northBound.size() >= 5) {

			str += "      x\n";
		}
		else {

			str += "       \n";
		}

		str += "   NB ";

		if (northBound.size() >=6) {

			str += "x"; 
		}
		else {

			str += " ";
		}

		if (northBound.size() >= 10) {

			str += " " + northBound.size() + "\n"; 
		}
		
		else
			str += "  " + northBound.size() + "\n";  

		if (timer < 10)
			str += "at clock:   " + timer;

		if (timer < 100 && timer >= 10)
			str += "at clock  " + timer;

		if ( timer > 100)
			str += "at clock: " + timer;

		str += "\n--------------------------------------";

		return str;
	}

	//----------------------------------------------------------------
	//Unit testing
	public static void main (String [] args) {

		TrafficSim  TS = new TrafficSim ("input1.txt");

		System.out.println(TS.readFromFile());
		String test = TS.readFromFile();

		TS.assignFlowRates();
		TS.addToQueue();
	
		System.out.println("Size of linked list: " + TS.result.getSize());
		System.out.println("Average: " + TS.result.average() + "\nCar, Trucks: " + TS.result.getCars() + " " + TS.result.getTrucks());
	} 
}
