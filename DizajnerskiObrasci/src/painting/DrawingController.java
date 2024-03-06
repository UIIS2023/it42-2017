package painting;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Observable;
import java.util.Stack;

import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import command.CmdBringToBack;
import command.CmdBringToFront;
import command.CmdCircleUpdate;
import command.CmdDiselectShape;
import command.CmdDonutUpdate;
import command.CmdHexagonUpdate;
import command.CmdLineUpdate;
import command.CmdPointUpdate;
import command.CmdRectangleUpdate;
import command.CmdRedo;
import command.CmdSelectShape;
import command.CmdShapeAdd;
import command.CmdShapeRemove;
import command.CmdToBack;
import command.CmdToFront;
import command.CmdUndo;
import command.Command;
import geometry.Circle;
import geometry.Donut;
import geometry.HexagonAdapter;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import help.Help;
import help.LogMapper;
import strategy.LoadLog;
import strategy.LoadManager;
import strategy.LoadShape;
import strategy.SaveLog;
import strategy.SaveManager;
import strategy.SaveShapes;

public class DrawingController extends Observable {

	private DrawingModel model;
	private Drawing frame;
	private Point startPoint;
	private boolean selected=false;
	
	private Point point;
	private Line line;
	private Rectangle rectangle;
	private Circle circle;
	private Donut donut;
	private HexagonAdapter hexagon;
	private Color edgeColor;
	private Color insideColor;
	
	
	private Stack<Command> executeCommand=new Stack<Command>();
	private Stack<Command> unexecuteCommand=new Stack<Command>();
	private List<Command> command=new ArrayList<Command>();
	
	private Stack<Command> execStack=new Stack<Command>();
	private Stack<Command> unexecStack=new Stack<Command>();
	
	private int index=0;
	
	
	public DrawingController() {
	}
	
	public DrawingController(DrawingModel model,Drawing frame) {
		this.model=model;
		this.frame=frame;
	}
	
	public void mouseClicked(MouseEvent e) {
		
		if(frame.getMode()==Help.SELECT) {
			Point p =new Point(e.getX(),e.getY());
			selected=false;
			
			for(int i=model.getShapes().size()-1;i>=0;i--) {
				if(model.getShapes().get(i).contains(p)) {
					if(!model.getShapes().get(i).isSelected()) {
						selected=true;
						CmdSelectShape cmd=new CmdSelectShape(model, model.getShapes().get(i));
						addInStack(cmd);
						frame.getLogListModel().addElement(cmd.toString());
						this.emitChangesToObservers();
						frame.btnRedo.setEnabled(false);
						return;
						
						
					} else {
						CmdDiselectShape cmd=new CmdDiselectShape(model,model.getShapes().get(i));
						addInStack(cmd);
						frame.getLogListModel().addElement(cmd.toString());
						this.emitChangesToObservers();
						frame.btnRedo.setEnabled(false);
						return;
						
					}
				}
			}

			if(selected==false) {
				if(model.numberSelectedObject()>1) {
					for(int i=0;i<model.getShapes().size();i++) {
						if(model.getShapes().get(i).isSelected()) {
							CmdDiselectShape cmd=new CmdDiselectShape(model,model.getShapes().get(i));
							addInStack(cmd);
							frame.getLogListModel().addElement(cmd.toString());
						}
					}
				} else if(model.numberSelectedObject()==1) {
					for(int i=0;i<model.getShapes().size();i++) {
						if(model.getShapes().get(i).isSelected()) {
							CmdDiselectShape cmd=new CmdDiselectShape(model,model.getShapes().get(i));
							addInStack(cmd);
							frame.getLogListModel().addElement(cmd.toString());
							this.emitChangesToObservers();
							frame.btnRedo.setEnabled(false);
							return;
						}
					}
				}
			}
		}
		if(frame.getMode()==Help.POINT) {
			Point p=new Point(e.getX(),e.getY());
			DialogPoint dp=new DialogPoint();
			dp.setTxtXEdt(false);
			dp.setTxtYEdt(false);
			dp.setTxtx(Integer.toString(p.getX()));
			dp.setTxty(Integer.toString(p.getY()));
			dp.setVisible(true);
			if(dp.getColorBorder()!=null) {
				p.setBorderColor(dp.getColorBorder());
			} else {
				p.setBorderColor(frame.getBtnEdgeColor().getBackground());
			}
			CmdShapeAdd cmd=new CmdShapeAdd(model,p);
			addInStack(cmd);
			this.frame.getLogListModel().addElement(cmd.toString());
		} else if(frame.getMode()==Help.LINE) {
			if(startPoint==null)
				startPoint=new Point(e.getX(),e.getY());
				else {
				Line l=new Line(startPoint,new Point(e.getX(),e.getY()));
				DialogLine dl=new DialogLine();
				dl.setTxtEndCoorXEdt(false);
				dl.setTxtEndCoorYEdt(false);
				dl.setTxtStartCoorXEdt(false);
				dl.setTxtStartCoorYEdt(false);
				dl.setTxtStartCoorX(Integer.toString(l.getStartPoint().getX()));
				dl.setTxtStartCoorY(Integer.toString(l.getStartPoint().getY()));
				dl.setTxtEndCoorX(Integer.toString(l.getEndPoint().getX()));
				dl.setTxtEndCoorY(Integer.toString(l.getEndPoint().getY()));
				l.getStartPoint().setBorderColor(Color.BLACK);
				l.getEndPoint().setBorderColor(Color.BLACK);
				dl.setVisible(true);
				if(dl.getColorBorder()!=null) {
					l.setBorderColor(dl.getColorBorder());
				} else {
					l.setBorderColor(frame.getBtnEdgeColor().getBackground());
				}
				CmdShapeAdd cmd=new CmdShapeAdd(model,l);
				addInStack(cmd);
				frame.getLogListModel().addElement(cmd.toString());
				}
		} else if(frame.getMode()==Help.RECTANGLE) {
			Point p=new Point(e.getX(),e.getY());
			DialogRectangle dija=new DialogRectangle();
			dija.setTxtXCoordinata(Integer.toString(p.getX()));
			dija.setTxtYCoordinata(Integer.toString(p.getY()));
			dija.setTxtXCoordinataEnabled(false);
			dija.setTxtYCoordinataEnabled(false);
			p.setBorderColor(Color.BLACK);
			dija.setVisible(true);
			if(dija.isOk()) {
			try {
			int width=Integer.parseInt(dija.getTxtWidth());
			int height=Integer.parseInt(dija.getTxtHeight());
			Rectangle rct=new Rectangle(p,width,height);
			if(dija.getColorBorder()!=null) {
				rct.setBorderColor(dija.getColorBorder());
			} else {
				rct.setBorderColor(frame.getBtnEdgeColor().getBackground());
			}
			if(dija.getColorFill()!=null) {
				rct.setColorFill(dija.getColorFill());
			} else {
				rct.setColorFill(frame.getBtnInsideColor().getBackground());
			}
			CmdShapeAdd cmd=new CmdShapeAdd(model,rct);
			addInStack(cmd);
			frame.getLogListModel().addElement(cmd.toString());
			}
			catch(NumberFormatException ex)
			{
				JOptionPane.showMessageDialog(new JFrame(), "Neispravan unos podataka.Proverite da li su sva polja popunjena brojnim vrednostima!", "Greška", JOptionPane.WARNING_MESSAGE);
			}
			}
		} else if(frame.getMode()==Help.CIRCLE) {
			Point center=new Point(e.getX(),e.getY());
			DialogCircle dija=new DialogCircle();
			dija.setTxtCoorXEdt(false);
			dija.setTxtCoorYEdt(false);
			dija.setTxtCoorX(Integer.toString(center.getX()));
			dija.setTxtCoorY(Integer.toString(center.getY()));
			center.setBorderColor(Color.BLACK);
			dija.setVisible(true);
			try
			{
			if(dija.isOk())
			{
				int radius=Integer.parseInt(dija.getTxtRadius());
				Circle circle=new Circle(center,radius);
				if(dija.getColorBorder()!=null) {
					circle.setBorderColor(dija.getColorBorder());
				} else {
					circle.setBorderColor(frame.getBtnEdgeColor().getBackground());
				}
				if(dija.getColorFill()!=null) {
					circle.setColorFill(dija.getColorFill());
				} else {
					circle.setColorFill(frame.getBtnInsideColor().getBackground());
				}
				CmdShapeAdd cmd=new CmdShapeAdd(model,circle);
				addInStack(cmd);
				frame.getLogListModel().addElement(cmd.toString());
			
			}
			}
			catch(NumberFormatException ex)
			{
				JOptionPane.showMessageDialog(new JFrame(), "Neispravan unos podataka.Proverite da li su sva polja popunjena brojnim vrednostima!!", "Greška", JOptionPane.WARNING_MESSAGE);
			}
		} else if(frame.getMode()==Help.DONUT) {
			Point center=new Point(e.getX(),e.getY());
			DialogDonut dija=new DialogDonut();
			dija.setTxtCoorX(Integer.toString(center.getX()));
			dija.setTxtCoorY(Integer.toString(center.getY()));
			dija.setTxtCoorXEditable(false);
			dija.setTxtCoorYEditable(false);
			center.setBorderColor(Color.BLACK);
			dija.setVisible(true);
			try
			{
			if(dija.isOk())
			{
				int innerRadius=Integer.parseInt(dija.getTxtInside());
				int outerRadius=Integer.parseInt(dija.getTxtOuter());
				Donut donut=new Donut(center,outerRadius,innerRadius);
				if(dija.getBorderColor()!=null) {
					donut.setBorderColor(dija.getBorderColor());
				} else {
					donut.setBorderColor(frame.getBtnEdgeColor().getBackground());
				}
				if(dija.getColorLessBorder()!=null) {
					donut.setColorLessBorder(dija.getColorLessBorder());
				} else {
					donut.setColorLessBorder(frame.getBtnEdgeColor().getBackground());
				}
				if(dija.getColorFill()!=null) {
					donut.setColorFill(dija.getColorFill());
				} else {
					donut.setColorFill(frame.getBtnInsideColor().getBackground());
				}
				CmdShapeAdd cmd=new CmdShapeAdd(model,donut);
				addInStack(cmd);
				frame.getLogListModel().addElement(cmd.toString());
			}
			}
			catch(NumberFormatException ex)
			{
				JOptionPane.showMessageDialog(new JFrame(), "Neispravan unos podataka.Proverite da li su sva polja popunjena brojnim vrednostima!", "Greška", JOptionPane.WARNING_MESSAGE);
			}
			
		} else if(frame.getMode()==Help.HEXAGON) {
			Point center=new Point(e.getX(),e.getY());
			DialogHexagon dija = new DialogHexagon();
			dija.setTxtCoorXEdt(false);
			dija.setTxtCoorYEdt(false);
			dija.setTxtCoorX(Integer.toString(center.getX()));
			dija.setTxtCoorY(Integer.toString(center.getY()));
			center.setBorderColor(Color.BLACK);
			dija.setVisible(true);
			try
			{
			if(dija.isOk())
			{
				int radius=Integer.parseInt(dija.getTxtRadius());
				HexagonAdapter hexagonAdap=new HexagonAdapter(center.getX(),center.getY(),radius);
				
				if(hexagonAdap.getBorderColor()!=null) {
					hexagonAdap.setBorderColor(dija.getColorBorder());
				} else {
					hexagonAdap.setBorderColor(frame.getBtnEdgeColor().getBackground());
				}
				if(hexagonAdap.getColorFill()!=null) {
					hexagonAdap.setColorFill(dija.getColorFill());
				} else {
					hexagonAdap.setColorFill(frame.getBtnInsideColor().getBackground());
				}
				CmdShapeAdd cmd=new CmdShapeAdd(model,hexagonAdap);
				addInStack(cmd);
				frame.getLogListModel().addElement(cmd.toString());	
			  }
			}
			catch(NumberFormatException ex)
			{
				JOptionPane.showMessageDialog(new JFrame(), "Neispravan unos podataka.Proverite da li su sva polja popunjena brojnim vrednostima!!", "Greška", JOptionPane.WARNING_MESSAGE);
			}
		}
		this.emitChangesToObservers();
		frame.btnRedo.setEnabled(false);
	}
	
	public void modifySelectedShape() {
		ListIterator<Shape> it1=model.getShapes().listIterator(model.getShapes().size());
		try {
		while(it1.hasPrevious()) {
		Shape shape=(Shape)it1.previous();
		if(shape.isSelected()==true) {
		if(shape instanceof Point)
		{
			Point point=(Point)shape;
			DialogPoint mt=new DialogPoint();
			mt.setTxtx(Integer.toString(point.getX()));
			mt.setTxty(Integer.toString(point.getY()));
			mt.setColorBorder(point.getBorderColor());
			mt.setVisible(true);
			try {
			if(mt.isOk())
			{
			
			this.point=mt.getDlgPoint();
			this.point.setBorderColor(point.getBorderColor());
			this.point.setSelected(true);
			CmdPointUpdate cmd=new CmdPointUpdate(point, this.point);
			addInStack(cmd);
			frame.getLogListModel().addElement(cmd.toString());
			}
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(new JFrame(), "Neispravan unos podataka.Proverite da li su sva polja popunjena brojnim vrednostima!", "Greška", JOptionPane.WARNING_MESSAGE);
			}
		}
		else if(shape instanceof Line)
		{
			Line line=(Line)shape;
			DialogLine ml=new DialogLine();
			ml.setTxtStartCoorX(Integer.toString(line.getStartPoint().getX()));
			ml.setTxtStartCoorY(Integer.toString(line.getStartPoint().getY()));
			ml.setTxtEndCoorX(Integer.toString(line.getEndPoint().getX()));
			ml.setTxtEndCoorY(Integer.toString(line.getEndPoint().getY()));
			ml.setColorBorder(line.getBorderColor());
			ml.setVisible(true);
			try
			{
			if(ml.isOk())
			{
				
				this.line=ml.getDlgLine();
				this.line.setSelected(true);
				
				CmdLineUpdate cmd=new CmdLineUpdate(line,this.line);
				addInStack(cmd);
				frame.getLogListModel().addElement(cmd.toString());
			}
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(new JFrame(), "Neispravan unos podataka.Proverite da li su sva polja popunjena brojnim vrednostima!", "Greška", JOptionPane.WARNING_MESSAGE);
			}
		}
		else if(shape instanceof Rectangle)
		{
			Rectangle rectangle=(Rectangle)shape;
			DialogRectangle dp=new DialogRectangle();
			dp.setTxtXCoordinata(Integer.toString(rectangle.getUpperLeftPoint().getX()));
			dp.setTxtYCoordinata(Integer.toString(rectangle.getUpperLeftPoint().getY()));
			dp.setTxtWidth(Integer.toString(rectangle.getWidth()));
			dp.setTxtHeight(Integer.toString(rectangle.getHeight()));
			dp.setColorFill(rectangle.getColorFill());
			dp.setColorBorder(rectangle.getBorderColor());
			dp.setVisible(true);
			try
			{
			if(dp.isOk())
			{
				
				this.rectangle=dp.getDlgRectangle();
				this.rectangle.setSelected(true);
				CmdRectangleUpdate cmd=new CmdRectangleUpdate(rectangle, this.rectangle);
				addInStack(cmd);
				frame.getLogListModel().addElement(cmd.toString());
			}
			}
			catch(NumberFormatException ex)
			{
				JOptionPane.showMessageDialog(new JFrame(), "Neispravan unos podataka.Proverite da li su sva polja popunjena brojnim vrednostima!", "Greška", JOptionPane.WARNING_MESSAGE);
			}
			
		}
		else if(shape instanceof Donut)
		{
			Donut donut=(Donut)shape;
			DialogDonut dk=new DialogDonut();
			dk.setTxtCoorX(Integer.toString(donut.getCenter().getX()));
			dk.setTxtCoorY(Integer.toString(donut.getCenter().getY()));
			dk.setTxtInside(Integer.toString(donut.getInnerRadius()));
			dk.setTxtOuter(Integer.toString(donut.getRadius()));
			dk.setBorderColor(donut.getBorderColor());
			dk.setColorFill(donut.getColorFill());
			dk.setColorLessBorder(donut.getColorLessBorder());
			dk.setVisible(true);
			try {
			if(dk.isOk())
			{	
				this.donut=dk.getDlgDonut();
				this.donut.setSelected(true);
				CmdDonutUpdate cmd=new CmdDonutUpdate(donut, this.donut);
				addInStack(cmd);
				frame.getLogListModel().addElement(cmd.toString());
			}
			}
			catch(NumberFormatException ex)
			{
				JOptionPane.showMessageDialog(new JFrame(), "Neispravan unos podataka.Proverite da li su sva polja popunjena brojnim vrednostima!", "Greška", JOptionPane.WARNING_MESSAGE);
			}
			
		}else if(shape instanceof Circle)
		{
			Circle circle=(Circle)shape;
			DialogCircle dk=new DialogCircle();
			dk.setTxtCoorX(Integer.toString(circle.getCenter().getX()));
			dk.setTxtCoorY(Integer.toString(circle.getCenter().getY()));
			dk.setTxtRadius(Integer.toString(circle.getRadius()));
			dk.setColorFill(circle.getColorFill());
			dk.setColorBorder(circle.getBorderColor());
			dk.setVisible(true);
			try
			{
			if(dk.isOk())
			{
				
				this.circle=dk.getDlgCircle();
				this.circle.setSelected(true);
				CmdCircleUpdate cmd=new CmdCircleUpdate(circle,this.circle);
				addInStack(cmd);
				frame.getLogListModel().addElement(cmd.toString());
			}
			}
			catch(NumberFormatException ex)
			{
				JOptionPane.showMessageDialog(new JFrame(), "Neispravan unos podataka.Proverite da li su sva polja popunjena brojnim vrednostima!", "Greška", JOptionPane.WARNING_MESSAGE);
			}
		}else if(shape instanceof HexagonAdapter)
		{
			HexagonAdapter hexagonAdapter=(HexagonAdapter)shape;
			DialogHexagon dk=new DialogHexagon();
			dk.setTxtCoorX(Integer.toString(hexagonAdapter.getHexagon().getX()));
			dk.setTxtCoorY(Integer.toString(hexagonAdapter.getHexagon().getY()));
			dk.setTxtRadius(Integer.toString(hexagonAdapter.getHexagon().getR()));
			dk.setColorFill(hexagonAdapter.getHexagon().getAreaColor());
			dk.setColorBorder(hexagonAdapter.getHexagon().getBorderColor());
			dk.setVisible(true);
			try
			{
			if(dk.isOk())
			{	
				if(dk.getColorBorder()!=null) {
					hexagonAdapter.getHexagon().setBorderColor(dk.getColorBorder());
				} else {
					hexagonAdapter.getHexagon().setBorderColor(frame.getBtnEdgeColor().getBackground());
				}
				if(dk.getColorFill()!=null) {
					hexagonAdapter.getHexagon().setAreaColor(dk.getColorFill());
				} else {
					hexagonAdapter.getHexagon().setAreaColor(frame.getBtnInsideColor().getBackground());
				}
				
				this.hexagon=dk.getHexagon();
				this.hexagon.setSelected(true);
				CmdHexagonUpdate cmd=new CmdHexagonUpdate(hexagonAdapter,this.hexagon);
				addInStack(cmd);
				frame.getLogListModel().addElement(cmd.toString());
			}
			}
			catch(NumberFormatException ex)
			{
				JOptionPane.showMessageDialog(new JFrame(), "Neispravan unos podataka.Proverite da li su sva polja popunjena brojnim vrednostima!", "Greška", JOptionPane.WARNING_MESSAGE);
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(new JFrame(), "Vrednost poluprecnika mora da bude pozitivna!", "Greška", JOptionPane.WARNING_MESSAGE);
			}
		  }
		}
		}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(frame, ex.getMessage());
		}
		this.emitChangesToObservers();
		frame.btnRedo.setEnabled(false);
	}
		
	public void deleteSelectedShape() {
		String[] options = { "Cancel", "Yes" };
		int option = JOptionPane.showOptionDialog(this.frame, "Are you sure you want to delete selected shape/shapes?",
				"Delete shapes?", JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
		if (option == 1) {
			List<Shape> deletedShapes = new ArrayList<Shape>();
			for (Shape shape : this.model.getShapes()) {
				if (shape.isSelected())
					deletedShapes.add(shape);
			}
			CmdShapeRemove deleteCmd = new CmdShapeRemove(this.model, deletedShapes,false);
			addInStack(deleteCmd);
			frame.getLogListModel().addElement(deleteCmd.toString());
			this.emitChangesToObservers();
			frame.btnRedo.setEnabled(false);
			this.diselectedAll();
		}
	}
	
	public void addInStack(Command command) {
		command.execute();
		executeCommand.push(command);
		if(!executeCommand.isEmpty()) {
			frame.getbtnUndo().setEnabled(true);
		}
		
		if(!unexecuteCommand.isEmpty()) {
			for(int i=0;i<=unexecuteCommand.size();i++) {
				unexecuteCommand.pop();
			}
			frame.getbtnRedo().setEnabled(false);		
		}
	}
	
	public boolean compare(Object o) {
		if(o instanceof CmdShapeRemove) {
			return true;
		} else {
			return false;
		}
	}
	
	public void undo() {
		try {
			frame.getLogListModel().addElement("undo");
			Command cmd=executeCommand.peek();
			
			if(compare(cmd)) {
				boolean commandRemove=true;
				while(commandRemove) {
					executeCommand.peek().unexecute();
					unexecuteCommand.push(executeCommand.pop());
					Command nextCommand=executeCommand.peek();
					
					if(!compare(nextCommand)) {
						commandRemove=false;
					}
				}
			} else {
				executeCommand.peek().unexecute();
				unexecuteCommand.push(executeCommand.pop());
			}
			
			if(executeCommand.isEmpty() && !unexecuteCommand.isEmpty()) {
				frame.getbtnUndo().setEnabled(false);
				frame.getbtnRedo().setEnabled(true);
			} else {
				frame.getbtnUndo().setEnabled(true);
				frame.getbtnRedo().setEnabled(true);
			}
			
		}catch(Exception er) {
			System.out.println(er.getStackTrace());
		}
	}
	
	public void redo() {
		try {
			frame.getLogListModel().addElement("redo");
			Command cmd=unexecuteCommand.peek();
			
			if(compare(cmd)) {
				boolean commandRemove=true;
				while(commandRemove) {
					unexecuteCommand.peek().execute();
					executeCommand.push(unexecuteCommand.pop());
					
					if(unexecuteCommand.isEmpty()) {
						commandRemove=false;
					} else {
						Command nextCommand=unexecuteCommand.peek();
						if(!compare(nextCommand)) {
							commandRemove=false;
						}
					}
				}
			} else {
				unexecuteCommand.peek().execute();
				executeCommand.push(unexecuteCommand.pop());
			}
			
			if(unexecuteCommand.isEmpty() && !executeCommand.isEmpty()) {
				frame.getbtnUndo().setEnabled(true);
				frame.getbtnRedo().setEnabled(false);
			} else {
				frame.getbtnUndo().setEnabled(true);
				frame.getbtnRedo().setEnabled(true);
			}
			
		} catch(Exception er) {
			er.getStackTrace();
		}
	}
	
	public void edgeColor(ActionEvent e) {
		edgeColor=JColorChooser.showDialog(null,"Edge color",edgeColor);
		if(edgeColor!=null) {
			frame.getBtnEdgeColor().setBackground(edgeColor);
		}
	}
	
	public void insideColor(ActionEvent e) {
		insideColor=JColorChooser.showDialog(null, "Inside color", insideColor);
		if(insideColor!=null) {
			frame.getBtnInsideColor().setBackground(insideColor);
		}
	}
	
	public void toFront() {
		CmdToFront cmd=new CmdToFront(model);
		addInStack(cmd);
		frame.getLogListModel().addElement(cmd.toString());
	}
	
	public void toBack(ActionEvent e) {
		CmdToBack cmd=new CmdToBack(model);
		addInStack(cmd);
		frame.getLogListModel().addElement(cmd.toString());
	}
	
	public void bringToFront(ActionEvent e) {
		int length=model.getShapes().size();
		if(length>1) {
			for(int i=0;i<length;i++) {
				if(model.getShapes().get(i).isSelected()) {
					if(i<length) {
						Shape current=model.getShapes().get(i);
						CmdBringToFront cmd=new CmdBringToFront(model, current, i,length);
						addInStack(cmd);
						frame.getLogListModel().addElement(cmd.toString());
						return;
					}
				}
			}
		}
	}
	
	public void bringToBack(ActionEvent e) {
		int length=model.getShapes().size();
		if(length>1) {
			for(int i=length-1;i>=0;i--) {
				if(model.getShapes().get(i).isSelected()) {
					if(i!=0) {
						Shape current=model.getShapes().get(i);
						CmdBringToBack cmd=new CmdBringToBack(model,current,i,length);
						addInStack(cmd);
						frame.getLogListModel().addElement(cmd.toString());
						return;
					}
				}
			}
		}
	}
	
	public void saveLog() {
		JFileChooser jFileChooser=new JFileChooser();
		if(jFileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
			SaveManager saveManager=new SaveManager(new SaveLog());
			saveManager.save(new ArrayList<Object>(Collections.list(this.frame.getLogListModel().elements())), jFileChooser.getSelectedFile().getAbsolutePath());
		}
	}
	
	public void saveCanvas() {
		JFileChooser jFileChooser=new JFileChooser();
		if(jFileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
			SaveManager saveManager=new SaveManager(new SaveShapes());
			saveManager.save(new ArrayList<Object>(this.model.getShapes()), jFileChooser.getSelectedFile().getAbsolutePath());
		}
	}
	

	public void loadCanvas() {
		JFileChooser jFileChooser=new JFileChooser();
		if(jFileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
			LoadManager loadManager=new LoadManager(new LoadShape());
			this.refreshCanvasAndLog();
			this.executeCommand.clear();
			this.frame.getLogListModel().clear();
			this.repaint();
			for(Object obj: loadManager.load(jFileChooser.getSelectedFile().getAbsolutePath())) {
				Shape shape=(Shape)obj;
				this.model.getShapes().add(shape);
			}
			this.repaint();
		}
	}
	
	public void loadLog() {
		JFileChooser jFileChooser=new JFileChooser();
		if(jFileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
			LoadManager loadManager=new LoadManager(new LoadLog());
			this.refreshCanvasAndLog();
			command=(LogMapper.mapLogsToCommand(loadManager.load(jFileChooser.getSelectedFile().getAbsolutePath()), this.model));
			frame.getBtnLoadLog().setEnabled(true);
		}
	}
	
	public void loadLogCommand() {
		if(index>=command.size()) {
			frame.getBtnLoadLog().setEnabled(false);
		} else {
			if(command.get(index) instanceof CmdUndo) {
				execStack.peek().unexecute();
				unexecStack.push(execStack.pop());
				index++;
				this.frame.getLogListModel().addElement("undo");
				this.emitChangesToObservers();
			} else if(command.get(index) instanceof CmdRedo) {
				unexecStack.peek().execute();
				execStack.push(unexecStack.pop());
				index++;
				this.frame.getLogListModel().addElement("redo");
				this.emitChangesToObservers();
			} else if(command.get(index) instanceof CmdSelectShape) {
				for(Shape s:model.getShapes()) {
					if(s.equals(((CmdSelectShape)command.get(index)).getShape())) {
						s.setSelected(true);
						command.get(index).execute();
						execStack.push(command.get(index));
						this.frame.getLogListModel().addElement(command.get(index).toString());
						this.repaint();
						this.emitChangesToObservers();
						index++;
						break;
					}
				}
			} else if(command.get(index) instanceof CmdPointUpdate) {
				Shape shp=((CmdPointUpdate)command.get(index)).getOldState();
				for(Shape s:model.getShapes()) {
					
					if(s.equals(((CmdPointUpdate)command.get(index)).getOldState())) {
						((CmdPointUpdate)command.get(index)).setOldState((Point)s);
						
						command.get(index).execute();
						execStack.push(command.get(index));
						this.frame.getLogListModel().addElement(command.get(index).toString());
						this.repaint();
						this.emitChangesToObservers();
						index++;
						break;
					}
				}
			} else if(command.get(index) instanceof CmdLineUpdate) {
				Shape shp=((CmdLineUpdate)command.get(index)).getOldState();
				for(Shape s:model.getShapes()) {				
					if(s.equals(((CmdLineUpdate)command.get(index)).getOldState())) {
						((CmdLineUpdate)command.get(index)).setOldState((Line)s);
						command.get(index).execute();
						execStack.push(command.get(index));
						this.frame.getLogListModel().addElement(command.get(index).toString());
						this.repaint();
						this.emitChangesToObservers();
						index++;
						break;
					}
				}
			} else if(command.get(index) instanceof CmdRectangleUpdate) {
				Shape shp=((CmdRectangleUpdate)command.get(index)).getOldState();
				for(Shape s:model.getShapes()) {
					if(s.equals(((CmdRectangleUpdate)command.get(index)).getOldState())) {
						((CmdRectangleUpdate)command.get(index)).setOldState((Rectangle)s);
						command.get(index).execute();
						execStack.push(command.get(index));
						this.frame.getLogListModel().addElement(command.get(index).toString());
						this.repaint();
						this.emitChangesToObservers();
						index++;
						break;
					}
				}
			} else if(command.get(index) instanceof CmdCircleUpdate) {
				Shape shp=((CmdCircleUpdate)command.get(index)).getOldState();
				for(Shape s:model.getShapes()) {
					if(s.equals(((CmdCircleUpdate)command.get(index)).getOldState())) {
						((CmdCircleUpdate)command.get(index)).setOldState((Circle)s);
						command.get(index).execute();
						execStack.push(command.get(index));
						this.frame.getLogListModel().addElement(command.get(index).toString());
						this.repaint();
						this.emitChangesToObservers();
						index++;
						break;
					}
				}
			} else if(command.get(index) instanceof CmdDonutUpdate) {
				Shape shp=((CmdDonutUpdate)command.get(index)).getOldState();
				for(Shape s:model.getShapes()) {					
					if(s.equals(((CmdDonutUpdate)command.get(index)).getOldState())) {
						((CmdDonutUpdate)command.get(index)).setOldState((Donut)s);
						command.get(index).execute();
						execStack.push(command.get(index));
						this.frame.getLogListModel().addElement(command.get(index).toString());
						this.repaint();
						this.emitChangesToObservers();
						index++;
						break;
					}
				}
			} else if(command.get(index) instanceof CmdHexagonUpdate) {
				Shape shp=((CmdHexagonUpdate)command.get(index)).getOldState();
				for(Shape s:model.getShapes()) {	
					if(s.equals(((CmdHexagonUpdate)command.get(index)).getOldState())) {
						((CmdHexagonUpdate)command.get(index)).setOldState((HexagonAdapter)s);
						command.get(index).execute();
						execStack.push(command.get(index));
						this.frame.getLogListModel().addElement(command.get(index).toString());
						this.repaint();
						this.emitChangesToObservers();
						index++;
						break;
					}
				}
			} else {
				command.get(index).execute();
				execStack.push(command.get(index));
				this.frame.getLogListModel().addElement(command.get(index).toString());
				this.repaint();
				this.emitChangesToObservers();
				index++;
			}
		}
	}
	
	
	
	private void emitChangesToObservers() {
		this.setChanged();
		this.notifyObservers(this.model.getShapes());
	}
	
	private void refreshLog() {
		this.executeCommand.clear();
		this.frame.getLogListModel().clear();
		this.repaint();
	}
	
	private void refreshCanvas() {
		this.model.getShapes().clear();
		this.repaint();
	}
	
	private void refreshCanvasAndLog() {
		this.refreshLog();
		this.refreshCanvas();
	}
	
	private void repaint() {
		this.frame.repaint();
	}
	
	private void diselectedAll() {
		DrawingModel.deselectAll(this.model.getShapes());
		this.emitChangesToObservers();
		this.repaint();
	}
	
	public Point getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Point p) {
		this.startPoint=p;
	}
}