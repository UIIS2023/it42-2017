package painting;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import geometry.Shape;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class PnlDrawing extends JPanel {
	
	private Drawing frame;
	
	private DrawingModel model;
	
	/**
	 * Create the panel.
	 */

	public PnlDrawing() {
		
	}
	
	public PnlDrawing(Drawing frame) {
		this.frame=frame;
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				thisMouseClicked(e);
			}

		});
	}
	
	
	protected void thisMouseClicked(MouseEvent e) {
		frame.getController().mouseClicked(e);
		
	}

	public void paint(Graphics g) {
		super.paint(g);
		if(!model.getShapes().isEmpty()) {
		Iterator<Shape> it=model.getShapes().iterator();
		while(it.hasNext() ) {
			it.next().draw(g);
		}
		}
		repaint();
	}

	public DrawingModel getModel() {
		return model;
	}

	public void setModel(DrawingModel model) {
		this.model = model;
	}
	

}
