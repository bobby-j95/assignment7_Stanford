import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.*;
import acm.graphics.*;
import acm.program.GraphicsProgram;
import javax.swing.*;

public class UsingInteractors extends GraphicsProgram {

	private static final double BOX_WIDTH = 120;
	private static final double BOX_HEIGHT = 50;

	private JTextField field = new JTextField(25);
	private JLabel name = new JLabel("Name:   ");
	private JButton addValue = new JButton("Add");
	private JButton removeValue = new JButton("Remove");
	private JButton clearAll = new JButton("Clear");
	private GObject current;
	private GPoint last;

	private HashMap<String, GCompound> compoundList;

	public void init() {

		add(name, SOUTH);
		field.addActionListener(this);
		add(field, SOUTH);
		add(addValue, SOUTH);
		add(removeValue, SOUTH);
		add(clearAll, SOUTH);

		addActionListeners();
		addMouseListeners();
		compoundList = new HashMap<String, GCompound>();

	}

	public void actionPerformed(ActionEvent e) {
		Object cmd = e.getSource();
		if (cmd == field || cmd == addValue) {
			boxCompound(field.getText());
		} else if (cmd == removeValue) {
			removeBox(field.getText());
		} else if (cmd == clearAll) {
			clearEverything();
		}

	}

	public void boxCompound(String s) {
		GCompound boxCompound = new GCompound();
		GRect boxOutline = new GRect(BOX_WIDTH, BOX_HEIGHT);
		GLabel boxLabel = new GLabel(s);
		boxCompound.add(boxOutline, -BOX_WIDTH / 2, -BOX_HEIGHT / 2);
		boxCompound.add(boxLabel, -boxLabel.getWidth() / 2, boxLabel.getAscent() / 2);
		add(boxCompound, getWidth() / 2, getHeight() / 2);
		compoundList.put(s, boxCompound);
	}

	private void removeBox(String name) {
		GCompound g = compoundList.get(name);
		if (g != null) {
			remove(g);
		}
	}

	private void clearEverything() {
		Iterator<String> i = compoundList.keySet().iterator();
		while (i.hasNext()) {
			removeBox(i.next());
		}
		compoundList.clear();
	}

	public void mousePressed(MouseEvent e) {
		last = new GPoint(e.getPoint());
		current = getElementAt(last);
	}

	public void mouseDragged(MouseEvent e) {
		if (current != null) {
			current.move(e.getX() - last.getX(), e.getY() - last.getY());
			last = new GPoint(e.getPoint());
		}
	}

	public void mouseClicked(MouseEvent e) {
		if (current != null)
			current.sendToFront();
	}

}
