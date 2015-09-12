/*
	Prio
	Priority Algorithm
*/
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class SchedulingPP {
	
	//Accepts an arraylist of Process Objects and returns a String represeting Gannt Chart
	public static String prio(ArrayList<Process> process) {

		//Queue for the processes in the waiting state
		ArrayList<Process> queue = new ArrayList<Process>();
		
		//The Current Time
		double curTime = 0;

		//The Return String / Gant Chart
		String prio = new String("");

		//Sorts the Processes by ArrivalTime
		SchedulingTools.sortByPriority(process);
		SchedulingTools.sortByArrivalTime(process);

		int k = 0;
		for (int i = 0, n = process.size(); i < n; i++) {

			if (k < n) {
				double nextProcessTime = process.get(k).getArrivalTime();
				while (nextProcessTime <= curTime && k < n) {
					queue.add(process.get(k));
					k++;
					if (k < n) 	
						nextProcessTime = process.get(k).getArrivalTime();
				}

				if (queue.size() == 0) {
					prio += "IDLE " + curTime + " ";
					curTime = nextProcessTime;
					prio += curTime + " ";
					queue.add(process.get(k));
				}
			}

			SchedulingTools.sortByPriority(queue);

			prio += queue.get(0).getName() + " " + curTime + " ";
			curTime += queue.get(0).getBurstTime();
			prio += curTime + " ";

			queue.remove(0);

		}
		//System.out.println(sjf);
		return prio; //returns the fcfs string

	}  //end of fcfs()
}
