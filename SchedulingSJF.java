/*
	SJF
	Shortest job first algorithm
*/
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class SchedulingSJF {
	
	//Accepts an arraylist of Process Objects and returns a String represeting Gannt Chart
	public static String doSJF(ArrayList<Process> process) {

		//Queue for the processes in the waiting state
		ArrayList<Process> queue = new ArrayList<Process>();
		
		//The Current Time
		double curTime = 0;

		//The Return String / Gant Chart
		String sjf = new String("");

		//Sorts the Processes by ArrivalTime
		SchedulingTools.sortByBurstTime(process);
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
					sjf += "IDLE " + curTime + " ";
					curTime = nextProcessTime;
					sjf += curTime + " ";
					queue.add(process.get(k));
				}
			}

			SchedulingTools.sortByBurstTime(queue);

			sjf += queue.get(0).getName() + " " + curTime + " ";
			curTime += queue.get(0).getBurstTime();
			sjf += curTime + " ";

			queue.remove(0);

		}
		System.out.println(sjf);
		return sjf; //returns the fcfs string

	}  //end of fcfs()
}
