//package Project1;
package project1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class TrafficSim 
{
	//Declaring classes to be used
	private IntersectionFlowRate flowRate;
	private Queue<Vehicle> eastBound; 
	private Queue<Vehicle> westBound; 
	private Queue<Vehicle> northBound; 
	private Queue<Vehicle> southBound; 
	
	private int timer = 0; 
	
	private project1.LinkedList result;

	/**
	 * Constructor: It takes name of input file as parameter. 
	 * @param inputFile
	 */
	public TrafficSim (String inputFile) 
	{
		eastBound = new LinkedList<Vehicle>();
		westBound = new LinkedList<Vehicle>();
		northBound = new LinkedList<Vehicle>();
		southBound = new LinkedList<Vehicle>();
		result = new project1.LinkedList();
		
		Vehicle v[] = new Vehicle[8];
		
		for (int i = 0; i < 8; i++)
		{
			v[i] = new Vehicle('c', i+1);
		}		

		//Adding initial 2 vehicles in each queue.
		northBound.add(v[0]); northBound.add(v[1]);
		southBound.add(v[2]); southBound.add(v[3]);
		eastBound.add(v[4]); eastBound.add(v[5]);
		westBound.add(v[6]); westBound.add(v[7]);

		flowRate = new IntersectionFlowRate();
		
		String input = readFromFile ( inputFile );	
		
		System.out.println ( printBoard() );
		
		//Calling methods
		assignFlowRates( input );
		addToQueue();
	}	


	private String readFromFile (String fileName) 
	{
		String input = "";
		
		//Dealing with exception by try and catch block.
		try {
			
			FileReader file = new FileReader(fileName);
			BufferedReader buf = new BufferedReader(file);
			
			for (int i = 0; i < 4; i++) 
			{
				input += buf.readLine() + " ";
			}
		}
		catch(IOException e) 
		{
			System.out.println ("Input problem, File not found");
		}

		return input;
	}

	private void assignFlowRates (String inputStr) 
	{
		String [] input = inputStr.split(" ");
		
//		for ( int x = 0; x < input.length; x++)
//		{
//			System.out.println( input[x] );
//		}

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
	}

	private void addVehicle (char direction, Vehicle x) 
	{
		switch (direction) 
		{
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

	public void addToQueue () 
	{
		int northCarFlow, northTruckFlow, southCarFlow, southTruckFlow, eastCarFlow, eastTruckFlow, westTruckFlow, westCarFlow;

		northCarFlow = 60/flowRate.getNorthFlowRateCars();
		northTruckFlow = 60/flowRate.getNorthFlowRateTrucks();
		
		//System.out.println(northCarFlow + " " + northTruckFlow );

		southCarFlow = 60/flowRate.getSouthFlowRateCars();
		southTruckFlow = 60/flowRate.getSouthFlowRateTrucks();

		eastCarFlow = 60/flowRate.getEastFlowRateCars();
		eastTruckFlow = 60/flowRate.getEastFlowRateTrucks();

		westCarFlow = 60/flowRate.getWestFlowRateCars();
		westTruckFlow = 60/flowRate.getWestFlowRateTrucks();	

		Vehicle c, t;
				
		boolean greenNS = true, greenEW = false;
		int minNS = 0, minEW = 0;
			
		int total = 9, count = 1; 
		//Adding cars to the queue according to the flow rates.
		for (int i = 0; i < 120; i++) 
		{
			//North Flow
			if ( count % northCarFlow == 0) 
			{
				c = new Vehicle('c', total); total++;
				c.setTimeEntered(i);

				addVehicle('N', c); //System.out.println("car added");
			}

			if (count % northTruckFlow == 0) 
			{
				t = new Vehicle('t', total); total++;
				t.setTimeEntered(i);

				addVehicle('N', t);
			}

			//South Flow
			if ( count % southCarFlow == 0) 
			{				
				c = new Vehicle('c', total); total++;
				c.setTimeEntered(i);

				addVehicle('S', c);				
			}

			if (count % southTruckFlow == 0) 
			{
				t = new Vehicle('t', total); total++;
				t.setTimeEntered(i);

				addVehicle('S', t);
			}

			//East Flow
			if ( count % eastCarFlow == 0) 
			{
				c = new Vehicle('c', total); total++;
				c.setTimeEntered(i);

				addVehicle('E', c);
			}

			if (count % eastTruckFlow == 0) 
			{
				t = new Vehicle('t', total); total++;
				t.setTimeEntered(i);

				addVehicle('E', t);
			}

			//West Flow
			if ( count % westCarFlow == 0) 
			{
				c = new Vehicle('c', total); total++;
				c.setTimeEntered(i);

				addVehicle('W', c);
			}

			if (count % westTruckFlow == 0) 
			{
				t = new Vehicle('t', total); total++;
				t.setTimeEntered(i);

				addVehicle('W', t);
			}
			
			count++;

			//Removing vehicles				
			if (minNS > 29)
			{
				if (eastBound.size() > 0 || westBound.size() > 0)
				{
					greenNS = false;
					greenEW = true;
					
					greenLightCase(eastBound, i);
					greenLightCase(westBound, i);					
					minNS = 0;
				}
			}
			
			if (minEW >= 9)
			{
				if ( minEW > 29 )
				{					
					greenNS = true;
					greenEW = false;
					
					greenLightCase(northBound, i);
					greenLightCase(southBound, i);					
					minEW = 0;
				}				
				if ( eastBound.isEmpty() )
				{	
					if ( westBound.isEmpty())
					{						
						greenNS = true;
						greenEW = false;
						
						greenLightCase(northBound, i);
						greenLightCase(southBound, i);
						minEW = 0;
					}					
				}
			}			
			
			if ( greenNS )
			{				
				removeVehicle(northBound, i);			
				removeVehicle(southBound, i);				
				minNS++;
			}
			
			if ( greenEW )
			{				
				removeVehicle(eastBound, i);				
				removeVehicle(westBound, i);				
				minEW++;
			}
			
			if (timer < 119) 
			{
				timer++;
				System.out.println (printBoard());	
			}					
		}
		
		System.out.println("The final results are:");		
		System.out.println("The number of vehicles that passed through the intersection is: " + result.getSize() ); 
		System.out.println("The number of cars that passed through the intersection is: " + result.getCars() ); 
		System.out.println("The number of trucks that passed through the intersection is: " + result.getTrucks() ); 
		System.out.println("The average wait time for this intersection is: " + result.average() ); 
	}
	
	private void greenLightCase(Queue<Vehicle> queue, int time)
	{
		if (queue.size() > 0)
		{				
			Vehicle temp = queue.remove();				
			result.add(temp, time - temp.getTimeEntered());	
			queue.peek().setGreenEntered(time);	
		}				
	}
	
	private void removeVehicle (Queue<Vehicle> queue, int time)
	{		
		if (queue.size() > 0)
		{				
			Vehicle temp = queue.peek();			
			
			if (temp.getLeaveAt() == time)
			{
				queue.remove();
				result.add(temp, time - temp.getTimeEntered());				
			}	
			
			if (queue.size() > 0)	
			{ 
				queue.peek().setGreenEntered(time);							
			}											
		}
	}

	private String printBoard( ) 
	{
		String str = "";

		//Line 6
		str += "   SB ";
		if (southBound.size() >= 6) 
		{
			str += "x  " + southBound.size() + "\n";
		}
		else 
		{
			str += "   " + southBound.size() + "\n";
		}

		//Line 5
		if (southBound.size() >= 5) 
		{
			str += "      x  \n";
		}
		else 
		{
			str += "   \n";
		}

		//Line 4
		if (southBound.size() >= 4) 
		{
			str += "      x  \n";
		}
		else 
		{
			str += "   \n";
		}

		//Line 3
		if (southBound.size() >= 3) 
		{
			str += "      x  \n";
		}
		else 
		{
			str += "   \n";
		}

		//Line 2
		if (southBound.size() >= 2) 
		{
			str += "EB    x\n";
		}
		else 
		{
			str += "EB     \n";
		}

		//Line 1
		if (southBound.size() >= 1) 
		{
			//Adding space for single digit numbers.
			if (eastBound.size() < 10) 
			{
				str += " " + eastBound.size() + "    " + southBound.element().getType() + "\n";
			}
			else 
				str += eastBound.size() + "    " + southBound.element().getType() + "\n";	
		}

		if (southBound.size() == 0) 
		{
			if (eastBound.size() < 10) 
			{
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
		if (eastBound.size() >= 6) 
		{
			str += eastBound.element().getType() + "xxxxx\n";
		}
		else 
		{
			if (eastBound.size() >= 5) 
			{
				str += eastBound.element().getType() + "xxxx \n";
			}
			else 
			{
				if (eastBound.size() >= 4) 
				{					
					str += eastBound.element().getType() + "xxx  \n";
				}
				else if (eastBound.size() >= 3) 
					{
						str += eastBound.element().getType() + "xx   \n";
					}
					else if (eastBound.size() >= 2) 
						{
							str += eastBound.element().getType() + "x    \n";
						}
						else if (eastBound.size() >= 1) 
							{
								str += eastBound.element().getType() + "     \n";
							}
							else if (eastBound.size() >= 0) 
								{
									str += "\n";
								}
			}
		}
				
		//Printing north Bound
		if (northBound.size() == 0) 
		{
			str += "       " + "\n";
		}
				
		if (northBound.size() > 0) 
		{
			str += "      " + northBound.element().getType() + "\n";
		}

		if (northBound.size() > 1) 
		{
			str += "      x     WB\n";
		}
		else 
		{
			str += "           WB\n";
		}

		if (northBound.size() <= 2) 
		{
			str += "       ";

			if (westBound.size() < 10) 
			{
				str +=  "     " + westBound.size() + "\n";
			}

			else 
			{
				str +=  "    " + westBound.size() + "\n";
			}
		}		

		if (northBound.size() >= 3) 
		{
			str += "      x";

			if (westBound.size() < 10) 
			{
				str +=  "     " + westBound.size() + "\n";
			}
			else 
			{
				str +=  "     " + westBound.size() + "\n";
			}
		}		

		if (northBound.size() >= 4) 
		{
			str += "      x\n";
		}
		else 
		{
			str += "       \n";
		}

		if (northBound.size() >= 5) 
		{
			str += "      x\n";
		}
		else 
		{
			str += "       \n";
		}

		str += "   NB ";

		if (northBound.size() >=6) 
		{
			str += "x"; 
		}
		else 
		{
			str += " ";
		}

		if (northBound.size() >= 10) 
		{
			str += " " + northBound.size() + "\n"; 
		}		
		else
			str += "  " + northBound.size() + "\n";  

		if (timer < 10)
			str += "at clock:   " + timer;

		if (timer < 100 && timer >= 10)
			str += "at clock:  " + timer;

		if ( timer > 100)
			str += "at clock: " + timer;

		str += "\n--------------------------------------\n";

		return str;
	}
	

	//----------------------------------------------------------------
	//Unit testing
	public static void main (String [] args) {

		TrafficSim  TS = new TrafficSim ("input1.txt");	
	} 
}
