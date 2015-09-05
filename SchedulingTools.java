/*
	SchedulingTools.java
	This class contains function/tools to help in manipulating process/jobs
*/

import java.util.ArrayList;

public class SchedulingTools {

	public static void sortByArrivalTime(ArrayList<Process> process) {
		for (int i = 0, n = process.size(); i < n-1; i++) {
			for (int k = i+1; k < n; k++) {
				if (process.get(i).getArrivalTime() > process.get(k).getArrivalTime()) {
					Process temp = process.get(i);
					process.set(i, process.get(k));
					process.set(k, temp);
				}
			}
		}
	} //end of SortByArrivalTime

	public static void sortByBurstTime(ArrayList<Process> process) {
		for (int i = 0, n = process.size(); i < n-1; i++) {
			for (int k = i+1; k < n; k++) {
				if (process.get(i).getBurstTime() > process.get(k).getBurstTime()) {
					Process temp = process.get(i);
					process.set(i, process.get(k));
					process.set(k, temp);
				}
			}
		}
	} //end of SortByBurstTime

}