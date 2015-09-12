/*
	Prio
	Priority Algorithm
*/
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class SchedulingPPrio {

	//Accepts an arraylist of Process Objects and returns a String represeting Gannt Chart
	public static String pprio(ArrayList<Process> process) {
		ArrayList<Process> queue = new ArrayList<Process>();

		SchedulingTools.sortByArrivalTime(process);

		double curTime = 0;
		String PP = new String("");

		int k = 0;

		for (int i = 0, n = process.size(); i < n; i++) {
			System.out.println("Hi");
			double nextProcessTime = process.get(i).getArrivalTime();
			if (curTime >= nextProcessTime) {
				queue.add(process.get(i));
				i++;
			}
			if (curTime < nextProcessTime && queue.size() == 0) {
				PP += "IDLE " + curTime + " " + nextProcessTime + " ";
				curTime = nextProcessTime;
				queue.add(process.get(i));
				i++;
			}

			while (queue.size() != 0) {
				SchedulingTools.sortByPriority(queue);
				PP += queue.get(0).getName() + " " + curTime;
				if ( i < n) {
					nextProcessTime = process.get(i).getArrivalTime();
					if (nextProcessTime < queue.get(0).getBurstTime() + curTime) {
						queue.add(process.get(i));
						PP += " " + nextProcessTime + " ";
						queue.get(0).setBurstTime(queue.get(0).getBurstTime() - (nextProcessTime - curTime));
						curTime = nextProcessTime;
						i++;
					}
					if (nextProcessTime == queue.get(0).getBurstTime() + curTime) {
						queue.add(process.get(i));
						PP += " " + nextProcessTime + " ";
						curTime = nextProcessTime;
						queue.remove(0);
					}
				}
				else {
					PP += " " + (curTime + queue.get(0).getBurstTime()) + " ";
					curTime = curTime + queue.get(0).getBurstTime();
					queue.remove(0);
				}
			}
		}
		System.out.println(PP);
		return PP;
	}
}
