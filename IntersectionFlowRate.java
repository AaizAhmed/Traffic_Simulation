/**
  * This class monitors the flow rate of traffic coming from different directions.
  *	@author Aaiz Ahmed <aaiza2@umbc.edu>
  * @version Feb 20, 2014
  * @project CMSC 341 - Spring 2014 - Project #1 Traffic simulator.
  * @section 01
*/
package Project1;

public class IntersectionFlowRate {
	
	private int eastFlowRateCars;
	private int westFlowRateCars;
	private int northFlowRateCars;
	private int southFlowRateCars;
	
	private int eastFlowRateTrucks;
	private int westFlowRateTrucks;
	private int northFlowRateTrucks;
	private int southFlowRateTrucks;
	
	
	public int getEastFlowRateCars() {
		return eastFlowRateCars;
	}

	/**
	 * @param eastFlowRateCars
	 */
	public void setEastFlowRateCars(int eastFlowRateCars) {
		this.eastFlowRateCars = eastFlowRateCars;
	}

	public int getWestFlowRateCars() {
		return westFlowRateCars;
	}

	public void setWestFlowRateCars(int westFlowRateCars) {
		this.westFlowRateCars = westFlowRateCars;
	}

	public int getNorthFlowRateCars() {
		return northFlowRateCars;
	}

	public void setNorthFlowRateCars(int northFlowRateCars) {
		this.northFlowRateCars = northFlowRateCars;
	}

	public int getSouthFlowRateCars() {
		return southFlowRateCars;
	}

	public void setSouthFlowRateCars(int southFlowRateCars) {
		this.southFlowRateCars = southFlowRateCars;
	}

	public int getEastFlowRateTrucks() {
		return eastFlowRateTrucks;
	}

	public void setEastFlowRateTrucks(int eastFlowRateTrucks) {
		this.eastFlowRateTrucks = eastFlowRateTrucks;
	}

	public int getWestFlowRateTrucks() {
		return westFlowRateTrucks;
	}

	public void setWestFlowRateTrucks(int westFlowRateTrucks) {
		this.westFlowRateTrucks = westFlowRateTrucks;
	}

	public int getNorthFlowRateTrucks() {
		return northFlowRateTrucks;
	}

	public void setNorthFlowRateTrucks(int northFlowRateTrucks) {
		this.northFlowRateTrucks = northFlowRateTrucks;
	}

	public int getSouthFlowRateTrucks() {
		return southFlowRateTrucks;
	}

	public void setSouthFlowRateTrucks(int southFlowRateTrucks) {
		this.southFlowRateTrucks = southFlowRateTrucks;
	}

	
	
	
}
