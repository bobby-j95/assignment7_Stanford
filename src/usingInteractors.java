import java.awt.event.ActionEvent;
import java.util.*;
import acm.graphics.*;
import acm.program.GraphicsProgram;
import acmx.export.javax.swing.*;

public class usingInteractors extends GraphicsProgram {

	private static final double BOX_WIDTH = 120;
	private static final double BOX_HEIGHT = 50;

	private static JTextField field = new JTextField(40);
	private static JLabel name = new JLabel("Name:   ");
	private static JButton addValue = new JButton("Add");
	private static JButton removeValue = new JButton("Remove");
	private static JButton clearAll = new JButton("Clear");

	private HashMap<String, GCompound> compoundList;

	public void init() {

		field.addActionListener(this);
		addMouseListener();

		add(name, SOUTH);
		add(field, SOUTH);
		add(addValue, SOUTH);
		add(removeValue, SOUTH);
		add(clearAll, SOUTH);

		compoundList = new HashMap<String, GCompound>();

	}

	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals("Add")) {
			GCompound g = new boxCompound(field.getText());
			compoundList.add(field.getText(), g);
			println("   " + field.getText());
		} else if (cmd.equals("Remove")) {
			removeBox(field.getText());
		}
	}

	public GCompound boxCompound(String s) {
		GCompound boxCompound = new GCompound();
		boxCompound.add(box());
		boxCompound.add(boxLabel(s));
		return boxCompound;
	}

	public GRect box() {
		GRect newBox = new GRect(34, 50, BOX_WIDTH, BOX_HEIGHT);
		newBox.setFilled(false);
		return newBox;
	}

	public GLabel boxLabel(String val) {
		GLabel newLabel = new GLabel(val);
		return newLabel;
	}

	private void removeBox(String name) {
		GCompound g = compoundList.get(name);
		if (g != null) {
			remove(g);
		}
	}

	private void removeContents() {
		Iterator<String> i = compoundList.keySet().iterator();
		while (i.hasNext()) {
			removeBox(i.next());
		}
		compoundList.clear();
	}

}
