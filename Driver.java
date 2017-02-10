package Project1;

import java.util.Queue;

// Project1 should have all of the other classes such as TrafficSim, Vehicle, etc...


/*
 * Create by Mr. Lupoli for Project 1 - Traffic Queue, Spring 2014
 */

public class Driver {

	public static void main(String[] args) {

		TrafficSim x = new TrafficSim("input3.txt");
		
	// /afs/umbc.edu/users/s/l/slupoli/pub/cs341/aaiza2/proj1

	}

}

/**

private void removeVehicle(Queue<Vehicle> queue, int time)
	{		
		if (queue.size() > 0)
		{
			Vehicle temp = queue.peek();
			
			if (temp.getType() == 't')
			{
				count = time;
			}
			
			System.out.println(temp.getType() + " " + time + " " + count);
					
			if ( temp.getType() == 'c' || time == count+1 )
			{
				queue.remove();
				result.add(temp, time - temp.getTimeEntered());					
			}
		}
	}

*/
