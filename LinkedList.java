/**
 * This is a linked list and it will store how many vehicles passed the intersection.
 * @author   Aaiz Ahmed <aaiza2@umbc.edu>
 * @version  Feb 20, 2014
 * @modified Feb 09, 2017
 * 
 * @project  CMSC 341 - Spring 2014 - Project #1 Traffic simulator.
 * @section  01
 */

package Project1;

public class LinkedList 
{
	private int car = 0, truck = 0, size = 0;
	
	private Node FRONT, REAR;

	public LinkedList () 
	{
		FRONT =  new Node (null, 0);
		REAR = new Node(null, 0);
		FRONT.link = REAR;
	}

	/**
	 * Making a Node class. * 
	 */
	public class Node  
	{
		int waitTime;
		Vehicle vehicle;
		Node link;

		public Node (Vehicle v,  int time) 
		{
			waitTime = time;
			vehicle = v;
			link = null;
		}		

	}//Node class ends

	public int getSize() 
	{	return size;	}

	public int getCars () 
	{	return car;		}

	public int getTrucks () 
	{	return truck;	}

	public float average() 
	{
		int time = 0; 
		Node x = FRONT.link;

		while (x != null) 
		{
			time += x.waitTime; 
			x = x.link;			
		}		
		
		return (float) time/size;
	}
	
	public boolean isEmpty () 
	{	return size == 0;		}

	public void add (Vehicle x, int time) {

		Node n = new Node (x, time);

		if (isEmpty()) 
		{
			FRONT.link = n;
			n.link = REAR;
		}
		else 
		{
			n.link = REAR.link;
			REAR.link = n;
		}
		
		size++;
		if (x.getType() == 'c') car++; 	
		else truck++;
	}
	
}
