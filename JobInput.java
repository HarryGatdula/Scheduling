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
		this.add(at);
		this.add(bt);
		this.add(prio);
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