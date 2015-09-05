/*
	First Come First Serve;
	Sort by Arrival Time;
*/
import java.util.ArrayList;
import java.util.Scanner;

public class SchedulingFCFS {
	
	//Accepts an arraylist of Process Objects and returns a String represeting Gannt Chart
	public static String fcfs(ArrayList<Process> process) {

		//Queue for the processes in the waiting state
		ArrayList<Process> queue = new ArrayList<Process>();
		
		//The Current Time
		double curTime = 0;

		//The Return String / Gant Chart
		String fcfs = new String("");

		//Sorts the Processes by ArrivalTime for FCFS
		SchedulingTools.sortByArrivalTime(process);

		//Loop to Process all the Sorted Processes
		for (int i = 0, n = process.size(); i < n; i++) {

			//Gets the next Process Time to Process
			double nextProcessTime = process.get(i).getArrivalTime();

			//Checks if IDLE - if sets the current Time to nextProcessTime
			if (curTime < nextProcessTime && queue.size() == 0) {
				fcfs += "IDLE " + curTime + " " + nextProcessTime + " ";
				curTime = nextProcessTime;
			}


			//Adds the nextProcess in the queue if its within the Current Time
			if (curTime >= nextProcessTime) {
				queue.add(process.get(i));
			}

			//Process the Processes in the queue and appends the result in the Gannt Chart
			for (int k = queue.size(); k > 0; k--) {
				fcfs += queue.get(0).getName() + " " + curTime;
				curTime += queue.get(0).getBurstTime();
				fcfs += " " + curTime + " ";
				queue.remove(0);
			}
		}

		return fcfs; //returns the fcfs string
	} //end of fcfs()
}
