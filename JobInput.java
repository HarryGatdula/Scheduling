import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.*;

/*
	Improve UI
	Adjust Font
*/

public class JobInput extends JPanel {

	//Input Fields Declared as global
	JTextField name = new JTextField(10);
	JTextField at = new JTextField(4);
	JTextField bt = new JTextField(4);
	JTextField prio = new JTextField(4);

	//Constructor method for JobInput
	public JobInput() {
		setLayout(new FlowLayout());
		this.add(name);
		name.setToolTipText("Name of the Job");
		this.add(at);
		at.setToolTipText("Arrival Time");
		this.add(bt);
		bt.setToolTipText("Burst Time");
		this.add(prio);
		prio.setToolTipText("Priority");
		this.setBackground(Color.GRAY);
	}

	//get methods
	public String getName() {
		return name.getText();
	}

	//Get AT
	public double getAT() {
		return Double.parseDouble(at.getText());
	}

	//Get BT
	public double getBT() {
		return Double.parseDouble(bt.getText());
	}

	//Get Prio
	public int getPrio() {
		return Integer.parseInt(prio.getText());
	}

	public void clearFields() {
		name.setText("");
		at.setText("");
		bt.setText("");
		prio.setText("");
	}
}