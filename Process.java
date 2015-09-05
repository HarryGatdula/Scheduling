/*
Process Class
Contains the attributes of a Process in CPU scheduling
Time quantities are declared as double

arrivalTime - The Time a process gets into the queue
burstime - How long will it take the process to execute

remainingBurstTime - used for pre-emptive algorithms

To be used for computation
startTime - The time a process started processing
endTime - you know. ;)
*/


public class Process {
	private String name;
	private double arrivalTime;
	private double burstTime;
	private double remainingBurstTime;
	private double startTime;
	private double endTime;
	private int priority;

	public Process(String name, double arrivalTime, double burstTime, int priority) {
		this.name = name;
		this.arrivalTime = arrivalTime;
		this.burstTime = burstTime;
		this.priority = priority;
		this.remainingBurstTime = burstTime;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(double arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public double getBurstTime() {
		return burstTime;
	}

	public void setBurstTime(double burstTime) {
		this.burstTime = burstTime;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public double getRemainingBurstTime() {
		return remainingBurstTime;	
	}

	public void setRemainingBurstTime(int remainingBurstTime) {
		this.remainingBurstTime = remainingBurstTime;	
	}

	//The Time a process has started && ended processing in CPU	
	public double getStartTime() {
		return startTime;
	}

	public void setStartTime(double startTime) {
		this.startTime = startTime;
	}
	
	public double getEndTime() {
		return endTime;
	}

	public void setEndTime(double endTime) {
		this.endTime = endTime;
	}
}
