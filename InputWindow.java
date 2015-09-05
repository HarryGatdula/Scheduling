/*
InputWindow Class extends JFrame
The window where inputs of Job Name, At, Bt, Prio will be made
Button Make Sends the gannt chart to the JIJOFrame(the display frame)
*/

// TO DO
/*
	Add ScrollBar
	Improve UI
	Add other Scheduling Algorithms in the choice
	Adjust the input labels
*/

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JComboBox;

public class InputWindow extends JFrame implements ActionListener, ItemListener {

	//Numbers of Max Jobs Inputs
	final int MAX_JOBS = 50;

	//The Jobs inputs Class
	JobInput[] jobs = new JobInput[MAX_JOBS];
	JButton btnMake = new JButton("MAKE");
	JButton btnClear = new JButton("CLEAR");

	String[] strAlgos = {"FCFS", "SJF"};

	JComboBox cboAlgo = new JComboBox(strAlgos);

	//The output Window
	JIJOFrame frame = new JIJOFrame();

	JLabel lblInputName = new JLabel("Job Name");
	JLabel lblInputAT = new JLabel("AT");
	JLabel lblInputBT = new JLabel("BT");
	JLabel lblInputPrio = new JLabel("Prio");

	//InputWindow Constructor, Accepts a parameter of int
	public InputWindow(int MAX_JOBS) {


		super("Input Window - JIJOFrame");
		//Set ups the input window
		setSize(300, 500);
		setLayout(new FlowLayout());
		lblInputName.setForeground(Color.WHITE);
		lblInputAT.setForeground(Color.WHITE);
		lblInputBT.setForeground(Color.WHITE);
		lblInputPrio.setForeground(Color.WHITE);
		this.getContentPane().setBackground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		cboAlgo.setPreferredSize(new Dimension(100, 30));

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
		int dx = (int)(screenSize.getWidth() - 940)/2 - 5;
		int dy = (int)(screenSize.getHeight() - 500)/2;
		System.out.println(dx + " " + dy);
		setLocation(dx, dy);

		//Adds the button make
		btnMake.addActionListener(this);
		add(btnMake);

		//Adds the clear Button
		btnClear.addActionListener(this);
		add(btnClear);

		add(cboAlgo);

		//Adds the labels for input Fields 
		this.add(lblInputName);
		this.add(lblInputAT);
		this.add(lblInputBT);
		this.add(lblInputPrio);

		//Adds the Job Input
		for (int i = 0; i < MAX_JOBS; i++) {
			jobs[i] = new JobInput();
			add(jobs[i]);
		}

	}

	//Checks for Events(Clikcs, mouse, etc)
	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();

		//If the button clicked is btnMake and the process has contents
		//Make the gannt chart on the frame
		if (source == btnMake) {

			//ArrayList to Store processes information
			ArrayList<Process> processes = new ArrayList<Process>();

			//Checks the input fields
			for (int i = 0, n = 50; i < n; i++) {

				//Checks if the current JobInput box is the last
				if (jobs[i].name.getText().equals("") || jobs[i].at.getText().equals("") || jobs[i].bt.getText().equals("")) {
					break;
				}

				//Extracts the values from the input fields
				String name = jobs[i].name.getText();
				double at = Double.parseDouble(jobs[i].at.getText());
				double bt = Double.parseDouble(jobs[i].bt.getText());
				int prio = Integer.parseInt(jobs[i].prio.getText());

				//Create a new Process Object
				processes.add(new Process(name, at, bt, prio));
			}

			//Checks if the there is no input and the make button is clicked, if true, ends the function
			if (processes.size() == 0) {
				JOptionPane.showMessageDialog(null, "Check Input");
				return;
			}


			if (cboAlgo.getSelectedItem() == "FCFS")
				frame.makeGanntChart(processes, "FCFS");
			else if (cboAlgo.getSelectedItem() == "SJF")
				frame.makeGanntChart(processes, "SJF");
		} //end (if source == btnMake)

		//If the Clear button is clicked, clears
		if (source == btnClear) {
			//clears the input fields
			for (int i = 0; i < MAX_JOBS; i++) {
				jobs[i].clearFields();
			}
		}
	}

	public void itemStateChanged(ItemEvent e) {

	}
}