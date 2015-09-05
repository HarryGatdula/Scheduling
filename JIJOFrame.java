/*

WARNING: THIS IS PRETTY HARD SOURCE CODE THAT ONLY THE PERSON WHO WROTE THIS AND GOD CAN UNDERSTAND

The window where the gannt chart and TurnAround Time and Waiting Time computations will be displayed
*/

// TODO: ADD SCROLL

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.*;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.awt.Toolkit;

//Block Object to represent the Blocks in a Gannt Chart
class Block extends JPanel {
	public Block(String name, double start, double end) {
		JLabel lblName = new JLabel(name);
		JLabel lblStart = new JLabel(Double.toString(start));
		JLabel lblEnd =  new JLabel(Double.toString(end));
		setLayout(new GridLayout(2, 1, 0, 0));

		JPanel pnlBlockName = new JPanel();
		JPanel pnlBlockTime = new JPanel();
		JPanel pnlBlockEndTime = new JPanel();
		JPanel pnlBlockStartTime = new JPanel();
		pnlBlockTime.setLayout(new GridLayout(1, 2, 5, 5));

		pnlBlockName.setBackground(Color.GRAY);
		pnlBlockTime.setBackground(Color.GRAY);

		setPreferredSize(new Dimension(100, 40));
		lblName.setFont(new Font("Arial", Font.BOLD, 14));

		pnlBlockStartTime.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnlBlockEndTime.setLayout(new FlowLayout(FlowLayout.RIGHT));

		pnlBlockStartTime.setBackground(Color.GRAY);
		pnlBlockEndTime.setBackground(Color.GRAY);
		pnlBlockStartTime.add(lblStart);
		pnlBlockEndTime.add(lblEnd);

		pnlBlockName.add(lblName);
		pnlBlockTime.add(pnlBlockStartTime);
		pnlBlockTime.add(pnlBlockEndTime);

		this.add(pnlBlockName);
		this.add(pnlBlockTime);

		this.setBackground(Color.GRAY);
	}
}

class ComputationBlock extends JPanel {
	JLabel lblaverage = new JLabel();
	JLabel lblIndiComp = new JLabel();
	public ComputationBlock(int nOfJobs) {
		setLayout(new FlowLayout());
		setBackground(Color.RED);
		lblaverage.setText("AVE = 9.82");
		add(lblIndiComp);
		add(lblaverage);
		setPreferredSize(new Dimension(200, 600));
	}
}

public class JIJOFrame extends JFrame {

	//The Dimensions of the frame
	final int WIDTH = 640;
	final int HEIGHT = 480;

	Container con = getContentPane();
	JobInput[] jobs = new JobInput[50];
	JPanel pnlGanntChart = new JPanel();
	JPanel pnlComputations = new JPanel();


	ComputationBlock turnAroundTime = new ComputationBlock(50);
	ComputationBlock waitingTime = new ComputationBlock(50);

	JButton Test = new JButton("Mawawala Dapat");

	public JIJOFrame() {
		super("JIJO - A CPU Scheduling Algorithms Program");
		setSize(640, 500);	
		setLayout(new GridLayout(2, 1, 5, 5));
		con.add(Test);
		pnlGanntChart.setLayout(new FlowLayout());
		pnlComputations.setLayout(new GridLayout(1, 2, 5, 5));

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int dx = (int)(screenSize.getWidth() - 940)/2 + 300 + 5;
		int dy = (int)(screenSize.getHeight() - 500)/2;
		setLocation(dx, dy);

		con.add(pnlGanntChart);
		con.add(pnlComputations);

	}

	public void makeGanntChart(ArrayList<Process> process, String strAlgorithm) {
		clearWindow();

		//The output string of the processes in the format JobName TimeStart TimeStop/TimeEnd
		String gannt = "";

		if (strAlgorithm.equals("FCFS"))
			gannt = SchedulingFCFS.fcfs(process);
		if (strAlgorithm.equals("SJF"))
			gannt = SchedulingSJF.doSJF(process);

		//Splits the gannt chart for processing
		String[] data = gannt.split(" ");

		//The number of blocks in the gannt chart
		int n = (data.length)/3;

		//Creates and fill the blocks with data
		for (int i = 0; i<n ;i++) {
			pnlGanntChart.add(new Block(data[i*3], Double.parseDouble(data[i*3+1]), Double.parseDouble(data[i*3+2])));
		}
		
		//Does the computation
		doComputation(process, data);

		add(pnlGanntChart);
		add(pnlComputations);

	}

	public void clearWindow() {
		pnlGanntChart.removeAll();
		pnlComputations.removeAll();
		con.removeAll();
		con.revalidate();
		con.repaint();
	}

	public void doComputation(ArrayList<Process> process, String[] data) {
		String strTTComputation = "";
		String strWTComputation = "";

		for (int i = 0, n = process.size(); i < n; i++) {
			String name = process.get(i).getName();
			double endTime = 0;
			for (int k = data.length - 1; k >= 0; k--) {
				if (name.equals(data[k])) {
					endTime = Double.parseDouble(data[k+2]);
					break;
				}
			}
			double resultTT = endTime - process.get(i).getArrivalTime();
			double resultWT = resultTT - process.get(i).getBurstTime();
			strTTComputation += process.get(i).getName() + " " + endTime + " - " + process.get(i).getArrivalTime() + " = " + resultTT + "\n";
			strWTComputation += process.get(i).getName() + " " + resultTT + " - " + process.get(i).getBurstTime() + " = " + resultWT + "\n";
		}

		turnAroundTime.lblIndiComp.setText(strTTComputation);
		waitingTime.lblIndiComp.setText(strWTComputation);
		pnlComputations.add(turnAroundTime);
		pnlComputations.add(waitingTime);
	}

}
