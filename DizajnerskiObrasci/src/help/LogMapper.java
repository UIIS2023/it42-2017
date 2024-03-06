package help;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
import painting.DrawingModel;

public class LogMapper {

	private static Command mapLogToAddCommand(Object obj,DrawingModel model) {
		String log=obj.toString();
		
		String[] logSplit=log.split(",");
		
		String shape=logSplit[1].split(":")[0];
		if(shape.equals("point")) {
			String[] split=logSplit[1].split(":");
			return new CmdShapeAdd(model,new Point(Integer.parseInt(split[1]), Integer.parseInt(split[2]), new Color(
					Integer.parseInt(split[3])),Boolean.parseBoolean(split[4])));
			
		} else if(shape.equals("line")) {
			String[] split=logSplit[1].split(":");
			return new CmdShapeAdd(model,getLine(new Point(Integer.parseInt(split[2]),Integer.parseInt(split[3]),Color.BLACK),new Point(Integer.parseInt(split[7]),Integer.parseInt(split[8]),Color.BLACK),split[11],Boolean.parseBoolean(split[12])));
		} else if(shape.equals("circle")) {
			String[] split=logSplit[1].split(":");
			return new CmdShapeAdd(model,getCircle(new Point(Integer.parseInt(split[2]),Integer.parseInt(split[3]),Color.BLACK), split[6], split[7], split[8], Boolean.parseBoolean(split[9])));
		} else if(shape.equals("rectangle")) {
			String[] split=logSplit[1].split(":");
			return new CmdShapeAdd(model,getRectangle(new Point(Integer.parseInt(split[2]),Integer.parseInt(split[3]),Color.BLACK), split[6], split[7], split[8], split[9],Boolean.parseBoolean(split[10])));
		} else if(shape.equals("donut")) {
			String[] split=logSplit[1].split(":");
			return new CmdShapeAdd(model,getDonut(new Point(Integer.parseInt(split[3]),Integer.parseInt(split[4]),Color.BLACK), split[7], split[11],split[8], split[9], split[12],Boolean.parseBoolean(split[13])));
		} else if(shape.equals("hexagon")) {
			String[] split=logSplit[1].split(":");
			return new CmdShapeAdd(model,getHexagon(new Point(Integer.parseInt(split[1]),Integer.parseInt(split[2]),Color.BLACK), split[3], split[4], split[5], Boolean.parseBoolean(split[6])));
		}
		
		return null;
	}
	
	private static Command mapLogToModifyCommand(Object obj,DrawingModel model) {
		String log=obj.toString();
		String[] logSplit=log.split(",");
		
		String shape=logSplit[1].split("/")[0];
		if(shape.equals("point")) {
			String split=logSplit[1].split("/")[1];
			String pointOne=split.split("_")[0];
			int pointOneX=Integer.parseInt(pointOne.split(":")[1]);
			int pointOneY=Integer.parseInt(pointOne.split(":")[2]);
			String colorbPointOne=pointOne.split(":")[3];
			boolean selectedOne=Boolean.parseBoolean(pointOne.split(":")[4]);
			
			
			String pointTwo=split.split("_")[1];
			String colorbPointTwo=pointTwo.split(":")[3];
			int pointTwoX=Integer.parseInt(pointTwo.split(":")[1]);
			int pointTwoY=Integer.parseInt(pointTwo.split(":")[2]);
			boolean selectedTwo=Boolean.parseBoolean(pointTwo.split(":")[4]);
			
			return new CmdPointUpdate(new Point(pointOneX,pointOneY,new Color(Integer.parseInt(colorbPointOne),selectedOne)), new Point(pointTwoX,pointTwoY,new Color(Integer.parseInt(colorbPointTwo)),selectedTwo));
		} else if(shape.equals("line")) {
			String split=logSplit[1].split("/")[1];
			String lineOne=split.split("_")[0];
			int pointOneXLine1=Integer.parseInt(lineOne.split(":")[2]);
			int pointOneYLine1=Integer.parseInt(lineOne.split(":")[3]);
			int pointTwoXLine1=Integer.parseInt(lineOne.split(":")[7]);
			int pointTwoYLine1=Integer.parseInt(lineOne.split(":")[8]);
			String colorLine1=lineOne.split(":")[11];
			boolean selectedOne=Boolean.parseBoolean(lineOne.split(":")[12]);
			
			
			String lineTwo=split.split("_")[1];
			int pointOneXLine2=Integer.parseInt(lineTwo.split(":")[2]);
			int pointOneYLine2=Integer.parseInt(lineTwo.split(":")[3]);
			int pointTwoXLine2=Integer.parseInt(lineTwo.split(":")[7]);
			int pointTwoYLine2=Integer.parseInt(lineTwo.split(":")[8]);
			String colorLine2=lineTwo.split(":")[11];
			boolean selectedTwo=Boolean.parseBoolean(lineTwo.split(":")[12]);
			
			
			return new CmdLineUpdate(getLine(new Point(pointOneXLine1,pointOneYLine1,Color.BLACK),new Point(pointTwoXLine1,pointTwoYLine1,Color.BLACK), colorLine1,selectedOne), getLine(new Point(pointOneXLine2, pointOneYLine2,Color.BLACK),new Point(pointTwoXLine2,pointTwoYLine2,Color.BLACK), colorLine2,selectedTwo));
		} else if(shape.equals("circle")) {
			String split=logSplit[1].split("/")[1];
			
			String circleOne=split.split("_")[0];
			int pointXCircle1=Integer.parseInt(circleOne.split(":")[2]);
			int pointYCircle1=Integer.parseInt(circleOne.split(":")[3]);
			String radiusCircle1=circleOne.split(":")[6];
			String colorBCircle1=circleOne.split(":")[7];
			String colorFCircle1=circleOne.split(":")[8];
			boolean selectedOne=Boolean.parseBoolean(circleOne.split(":")[9]);
			
			String circleTwo=split.split("_")[1];
			int pointXCircle2=Integer.parseInt(circleTwo.split(":")[2]);
			int pointYCircle2=Integer.parseInt(circleTwo.split(":")[3]);
			String radiusCircle2=circleTwo.split(":")[6];
			String colorBCircle2=circleTwo.split(":")[7];
			String colorFCircle2=circleTwo.split(":")[8];
			boolean selectedTwo=Boolean.parseBoolean(circleTwo.split(":")[9]);
			
			return new CmdCircleUpdate(getCircle(new Point(pointXCircle1,pointYCircle1,Color.BLACK), radiusCircle1, colorBCircle1, colorFCircle1,selectedOne), getCircle(new Point(pointXCircle2,pointYCircle2,Color.BLACK), radiusCircle2, colorBCircle2, colorFCircle2,selectedTwo));
		} else if(shape.equals("rectangle")) {
			String split=logSplit[1].split("/")[1];
			
			String rectOne=split.split("_")[0];
			int pointXRect1=Integer.parseInt(rectOne.split(":")[2]);
			int pointYRect1=Integer.parseInt(rectOne.split(":")[3]);
			String widthRect1=rectOne.split(":")[6];
			String heightRect1=rectOne.split(":")[7];
			String colorBRect1=rectOne.split(":")[8];
			String colorFRect1=rectOne.split(":")[9];
			boolean selectedOne=Boolean.parseBoolean(rectOne.split(":")[10]);
			
			String rectTwo=split.split("_")[1];
			int pointXRect2=Integer.parseInt(rectTwo.split(":")[2]);
			int pointYRect2=Integer.parseInt(rectTwo.split(":")[3]);
			String widthRect2=rectTwo.split(":")[6];
			String heightRect2=rectTwo.split(":")[7];
			String colorBRect2=rectTwo.split(":")[8];
			String colorFRect2=rectTwo.split(":")[9];
			boolean selectedTwo=Boolean.parseBoolean(rectTwo.split(":")[10]);
			
			return new CmdRectangleUpdate(getRectangle(new Point(pointXRect1,pointYRect1,Color.BLACK), widthRect1, heightRect1, colorBRect1, colorFRect1,selectedOne), getRectangle(new Point(pointXRect2,pointYRect2,Color.BLACK), widthRect2, heightRect2, colorBRect2, colorFRect2,selectedTwo));
		} else if(shape.equals("donut")) {
			String split=logSplit[1].split("/")[1];
			
			String donutOne=split.split("_")[0];
			int pointXDonut1=Integer.parseInt(donutOne.split(":")[3]);
			int pointYDonut1=Integer.parseInt(donutOne.split(":")[4]);
			String radiusDonut1=donutOne.split(":")[7];
			String innerRadiusDonut1=donutOne.split(":")[11];
			String colorBorderDonut1=donutOne.split(":")[8];
			String colorFillDonut1=donutOne.split(":")[9];
			String colorBDonut1=donutOne.split(":")[12];
			boolean selectedDonut1=Boolean.parseBoolean(donutOne.split(":")[13]);
			
			String donutTwo=split.split("_")[1];
			int pointXDonut2=Integer.parseInt(donutTwo.split(":")[3]);
			int pointYDonut2=Integer.parseInt(donutTwo.split(":")[4]);
			String radiusDonut2=donutTwo.split(":")[7];
			String innerRadiusDonut2=donutTwo.split(":")[11];
			String colorBorderDonut2=donutTwo.split(":")[8];
			String colorFillDonut2=donutTwo.split(":")[9];
			String colorBDonut2=donutTwo.split(":")[12];
			boolean selectedDonut2=Boolean.parseBoolean(donutTwo.split(":")[13]);
			
			return new CmdDonutUpdate(getDonut(new Point(pointXDonut1,pointYDonut1,Color.BLACK), radiusDonut1, innerRadiusDonut1,colorBorderDonut1, colorFillDonut1, colorBDonut1,selectedDonut1), getDonut(new Point(pointXDonut2,pointYDonut2,Color.BLACK), radiusDonut2, innerRadiusDonut2, colorBorderDonut2, colorFillDonut2, colorBDonut2,selectedDonut2));
		} else if(shape.equals("hexagon")) {
			String split=logSplit[1].split("/")[1];
			
			String hexagonOne=split.split("_")[0];
			int pointXHex1=Integer.parseInt(hexagonOne.split(":")[1]);
			int pointYHex1=Integer.parseInt(hexagonOne.split(":")[2]);
			String radiusHex1=hexagonOne.split(":")[3];
			String colorBHex1=hexagonOne.split(":")[4];
			String colorFHex1=hexagonOne.split(":")[5];
			boolean selectedOne=Boolean.parseBoolean(hexagonOne.split(":")[6]);
			
			String hexagonTwo=split.split("_")[1];
			int pointXHex2=Integer.parseInt(hexagonTwo.split(":")[1]);
			int pointYHex2=Integer.parseInt(hexagonTwo.split(":")[2]);
			String radiusHex2=hexagonTwo.split(":")[3];
			String colorBHex2=hexagonTwo.split(":")[4];
			String colorFHex2=hexagonTwo.split(":")[5];
			boolean selectedTwo=Boolean.parseBoolean(hexagonTwo.split(":")[6]);
			
			return new CmdHexagonUpdate(getHexagon(new Point(pointXHex1,pointYHex1,Color.BLACK), radiusHex1, colorBHex1, colorFHex1,selectedOne), getHexagon(new Point(pointXHex2,pointYHex2,Color.BLACK), radiusHex2, colorBHex2, colorFHex2,selectedTwo));
		}
		return null;
	}
	
	private static Command mapLogToDeleteCommand(Object obj,DrawingModel model) {
		String log = obj.toString();
		String[] shapes = log.split("/")[1].split("_");
		
		List<Shape> deletedShapes = new ArrayList<>();
		
		for(String shape: shapes) {
			String type=shape.split(":")[0];
			if(type.equals("point")) {
				deletedShapes.add(new Point(Integer.parseInt(shape.split(":")[1]), Integer.parseInt(shape.split(":")[2]), new Color(
						Integer.parseInt(shape.split(":")[3])),Boolean.parseBoolean(shape.split(":")[4])));
			} else if(type.equals("line")) {
				deletedShapes.add(getLine(new Point(Integer.parseInt(shape.split(":")[2]),Integer.parseInt(shape.split(":")[3]),Color.BLACK), new Point(Integer.parseInt(shape.split(":")[7]),Integer.parseInt(shape.split(":")[8]),Color.BLACK),shape.split(":")[11],Boolean.parseBoolean(shape.split(":")[12])));
			} else if(type.equals("circle")) {
				deletedShapes.add(getCircle(new Point(Integer.parseInt(shape.split(":")[2]),Integer.parseInt(shape.split(":")[3]),Color.BLACK), shape.split(":")[6], shape.split(":")[7], shape.split(":")[8],Boolean.parseBoolean(shape.split(":")[9])));
			} else if(type.equals("rectangle")) {
				deletedShapes.add(getRectangle(new Point(Integer.parseInt(shape.split(":")[2]),Integer.parseInt(shape.split(":")[3]),Color.BLACK), shape.split(":")[6], shape.split(":")[7], shape.split(":")[8], shape.split(":")[9],Boolean.parseBoolean(shape.split(":")[10])));
			} else if(type.equals("donut")) {
				deletedShapes.add(getDonut(new Point(Integer.parseInt(shape.split(":")[3]), Integer.parseInt(shape.split(":")[4]),Color.BLACK), shape.split(":")[7], shape.split(":")[11], shape.split(":")[8], shape.split(":")[9], shape.split(":")[12],Boolean.parseBoolean(shape.split(":")[13])));
			} else if(type.equals("hexagon")) {
				deletedShapes.add(getHexagon(new Point(Integer.parseInt(shape.split(":")[1]),Integer.parseInt(shape.split(":")[2]),Color.BLACK), shape.split(":")[3], shape.split(":")[4], shape.split(":")[5], Boolean.parseBoolean(shape.split(":")[6])));
			}
		}
		
		return new CmdShapeRemove(model, deletedShapes, true);
	}
	
	private static Command mapLogToSelectCommand(Object obj,DrawingModel model) {
		String log=obj.toString();
		String[] logSplit=log.split("/");
		
		String shape=logSplit[1].split(":")[0];
		if(shape.equals("point")) {
			String[] split=logSplit[1].split(":");
			Point point=new Point(Integer.parseInt(split[1]),Integer.parseInt(split[2]),new Color(Integer.parseInt(split[3])));
			point.setSelected(true);
			return new CmdSelectShape(model, point);
		} else if(shape.equals("line")) {
			String[] split=logSplit[1].split(":");
			Line line=new Line(new Point(Integer.parseInt(split[2]),Integer.parseInt(split[3]),Color.BLACK),new Point(Integer.parseInt(split[7]),Integer.parseInt(split[8]),Color.BLACK),new Color(Integer.parseInt(split[11])));
			line.setSelected(true);
			System.out.println(line.isSelected());
			return new CmdSelectShape(model, line);
		} else if(shape.equals("rectangle")) {
			String[] split=logSplit[1].split(":");
			Rectangle rectangle=new Rectangle(new Point(Integer.parseInt(split[2]),Integer.parseInt(split[3]),Color.BLACK),Integer.parseInt(split[6]),Integer.parseInt(split[7]),new Color(Integer.parseInt(split[8])),new Color(Integer.parseInt(split[9])));
			rectangle.setSelected(true);
			return new CmdSelectShape(model, rectangle);
		} else if(shape.equals("circle")) {
			String[] split=logSplit[1].split(":");
			Circle circle=new Circle(new Point(Integer.parseInt(split[2]),Integer.parseInt(split[3]),Color.BLACK,true),Integer.parseInt(split[6]),new Color(Integer.parseInt(split[7])),new Color(Integer.parseInt(split[8])),true);
			circle.setSelected(true);
			return new CmdSelectShape(model, circle);
		} else if(shape.equals("donut")) {
			String[] split=logSplit[1].split(":");
			Donut donut=new Donut(new Point(Integer.parseInt(split[3]),Integer.parseInt(split[4]),Color.BLACK),Integer.parseInt(split[7]),Integer.parseInt(split[11]),new Color(Integer.parseInt(split[8])),new Color(Integer.parseInt(split[9])),new Color(Integer.parseInt(split[12])));
			donut.setSelected(true);
			return new CmdSelectShape(model, donut);
		} else if(shape.equals("hexagon")) {
			String[] split=logSplit[1].split(":");
			HexagonAdapter hexagon=new HexagonAdapter(new Point(Integer.parseInt(split[1]),Integer.parseInt(split[2]),Color.BLACK),Integer.parseInt(split[3]),new Color(Integer.parseInt(split[4])),new Color(Integer.parseInt(split[5])));
			hexagon.setSelected(true);
			return new CmdSelectShape(model, hexagon);
		}
		return null;
	}
	
	private static Command mapLogToDiSelectCommand(Object obj,DrawingModel model) {
		String log=obj.toString();
		String[] logSplit=log.split("/");
		
		String shape=logSplit[1].split(":")[0];
		if(shape.equals("point")) {
			String[] split=logSplit[1].split(":");
			System.out.println("Split:" + split);
			Point point=new Point(Integer.parseInt(split[1]),Integer.parseInt(split[2]),new Color(Integer.parseInt(split[3])));
			point.setSelected(false);
			return new CmdDiselectShape(model,point);
		} else if(shape.equals("line")) {
			String[] split=logSplit[1].split(":");
			Line line=new Line(new Point(Integer.parseInt(split[2]),Integer.parseInt(split[3]),Color.BLACK),new Point(Integer.parseInt(split[7]),Integer.parseInt(split[8]),Color.BLACK),new Color(Integer.parseInt(split[11])));
			line.setSelected(false);
			return new CmdDiselectShape(model, line);
		} else if(shape.equals("rectangle")) {
			String[] split=logSplit[1].split(":");
			Rectangle rectangle=new Rectangle(new Point(Integer.parseInt(split[2]),Integer.parseInt(split[3]),Color.BLACK),Integer.parseInt(split[6]),Integer.parseInt(split[7]),new Color(Integer.parseInt(split[8])),new Color(Integer.parseInt(split[9])));
			rectangle.setSelected(false);
			return new CmdDiselectShape(model, rectangle);
		} else if(shape.equals("circle")) {
			String[] split=logSplit[1].split(":");
			Circle circle=new Circle(new Point(Integer.parseInt(split[2]),Integer.parseInt(split[3]),Color.BLACK),Integer.parseInt(split[6]),new Color(Integer.parseInt(split[7])),new Color(Integer.parseInt(split[8])));
			circle.setSelected(false);
			return new CmdDiselectShape(model, circle);
		} else if(shape.equals("donut")) {
			String[] split=logSplit[1].split(":");
			Donut donut=new Donut(new Point(Integer.parseInt(split[3]),Integer.parseInt(split[4]),Color.BLACK),Integer.parseInt(split[7]),Integer.parseInt(split[11]),new Color(Integer.parseInt(split[8])),new Color(Integer.parseInt(split[9])),new Color(Integer.parseInt(split[12])));
			donut.setSelected(false);
			return new CmdDiselectShape(model, donut);
		} else if(shape.equals("hexagon")) {
			String[] split=logSplit[1].split(":");
			HexagonAdapter hexagon=new HexagonAdapter(new Point(Integer.parseInt(split[1]),Integer.parseInt(split[2]),Color.BLACK),Integer.parseInt(split[3]),new Color(Integer.parseInt(split[4])),new Color(Integer.parseInt(split[5])));
			hexagon.setSelected(false);
			return new CmdDiselectShape(model, hexagon);
		}
		return null;
	}
	
	private static Command mapLogToUndoCommand(Object obj,DrawingModel model) {
		return new CmdUndo();
	}
	
	private static Command mapLogToRedoCommand(Object obj,DrawingModel model) {
		return new CmdRedo();
	}
	
	private static Command mapLogToFrontCommand(Object obj,DrawingModel model) {
		return new CmdToFront(model);
	}
	
	private static Command mapLogToBackCommand(Object obj,DrawingModel model) {
		return new CmdToBack(model);
	}
	
	private static Command mapLogBringToFrontCommand(Object obj,DrawingModel model) {
		String log=obj.toString();
		String[] logSplit=log.split("/");
		
		String shape=logSplit[1].split(":")[0];
		if(shape.equals("point")) {
			String[] split=logSplit[1].split(":");
			Point point=new Point(Integer.parseInt(split[1]),Integer.parseInt(split[2]),new Color(Integer.parseInt(split[3])));
			point.setSelected(true);
			int i=Integer.parseInt(split[5]);
			int size=Integer.parseInt(split[6]);
			
			return new CmdBringToFront(model, point,i,size);
		} else if(shape.equals("line")) {
			String[] split=logSplit[1].split(":");
			Line line=new Line(new Point(Integer.parseInt(split[2]),Integer.parseInt(split[3]),Color.BLACK),new Point(Integer.parseInt(split[7]),Integer.parseInt(split[8]),Color.BLACK),new Color(Integer.parseInt(split[11])));
			line.setSelected(true);
			int i=Integer.parseInt(split[13]);
			int size=Integer.parseInt(split[14]);
			return new CmdBringToFront(model, line,i,size);
		} else if(shape.equals("rectangle")) {
			String[] split=logSplit[1].split(":");
			Rectangle rectangle=new Rectangle(new Point(Integer.parseInt(split[2]),Integer.parseInt(split[3]),Color.BLACK),Integer.parseInt(split[6]),Integer.parseInt(split[7]),new Color(Integer.parseInt(split[8])),new Color(Integer.parseInt(split[9])));
			rectangle.setSelected(true);
			int i=Integer.parseInt(split[11]);
			int size=Integer.parseInt(split[12]);
			return new CmdBringToFront(model, rectangle,i,size);
		} else if(shape.equals("circle")) {
			String[] split=logSplit[1].split(":");
			Circle circle=new Circle(new Point(Integer.parseInt(split[2]),Integer.parseInt(split[3]),Color.BLACK),Integer.parseInt(split[6]),new Color(Integer.parseInt(split[7])),new Color(Integer.parseInt(split[8])));
			circle.setSelected(true);
			int i=Integer.parseInt(split[10]);
			int size=Integer.parseInt(split[11]);
			return new CmdBringToFront(model, circle,i,size);
		} else if(shape.equals("donut")) {
			String[] split=logSplit[1].split(":");
			Donut donut=new Donut(new Point(Integer.parseInt(split[3]),Integer.parseInt(split[4]),Color.BLACK),Integer.parseInt(split[7]),Integer.parseInt(split[11]),new Color(Integer.parseInt(split[8])),new Color(Integer.parseInt(split[9])),new Color(Integer.parseInt(split[12])));
			donut.setSelected(true);
			int i=Integer.parseInt(split[14]);
			int size=Integer.parseInt(split[15]);
			return new CmdBringToFront(model, donut,i,size);
		} else if(shape.equals("hexagon")) {
			String[] split=logSplit[1].split(":");
			HexagonAdapter hexagon=new HexagonAdapter(new Point(Integer.parseInt(split[1]),Integer.parseInt(split[2]),Color.BLACK),Integer.parseInt(split[3]),new Color(Integer.parseInt(split[4])),new Color(Integer.parseInt(split[5])));
			hexagon.setSelected(true);
			int i=Integer.parseInt(split[7]);
			int size=Integer.parseInt(split[8]);
			return new CmdBringToFront(model, hexagon,i,size);
		}
		return null;
	}
	
	private static Command mapLogBringToBackCommand(Object obj,DrawingModel model) {
		String log=obj.toString();
		String[] logSplit=log.split("/");
		
		String shape=logSplit[1].split(":")[0];
		if(shape.equals("point")) {
			String[] split=logSplit[1].split(":");
			Point point=new Point(Integer.parseInt(split[1]),Integer.parseInt(split[2]),new Color(Integer.parseInt(split[3])));
			point.setSelected(true);
			int i=Integer.parseInt(split[5]);
			int size=Integer.parseInt(split[6]);
			return new CmdBringToBack(model, point,i,size);
		} else if(shape.equals("line")) {
			String[] split=logSplit[1].split(":");
			Line line=new Line(new Point(Integer.parseInt(split[2]),Integer.parseInt(split[3]),Color.BLACK),new Point(Integer.parseInt(split[7]),Integer.parseInt(split[8]),Color.BLACK),new Color(Integer.parseInt(split[11])));
			line.setSelected(true);
			int i=Integer.parseInt(split[13]);
			int size=Integer.parseInt(split[14]);
			return new CmdBringToBack(model, line,i,size);
		} else if(shape.equals("rectangle")) {
			String[] split=logSplit[1].split(":");
			Rectangle rectangle=new Rectangle(new Point(Integer.parseInt(split[2]),Integer.parseInt(split[3]),Color.BLACK),Integer.parseInt(split[6]),Integer.parseInt(split[7]),new Color(Integer.parseInt(split[8])),new Color(Integer.parseInt(split[9])));
			rectangle.setSelected(true);
			int i=Integer.parseInt(split[11]);
			int size=Integer.parseInt(split[12]);
			return new CmdBringToBack(model, rectangle,i,size);
		} else if(shape.equals("circle")) {
			String[] split=logSplit[1].split(":");
			Circle circle=new Circle(new Point(Integer.parseInt(split[2]),Integer.parseInt(split[3]),Color.BLACK),Integer.parseInt(split[6]),new Color(Integer.parseInt(split[7])),new Color(Integer.parseInt(split[8])));
			circle.setSelected(true);
			int i=Integer.parseInt(split[10]);
			int size=Integer.parseInt(split[11]);
			return new CmdBringToBack(model, circle,i,size);
		} else if(shape.equals("donut")) {
			String[] split=logSplit[1].split(":");
			Donut donut=new Donut(new Point(Integer.parseInt(split[3]),Integer.parseInt(split[4]),Color.BLACK),Integer.parseInt(split[7]),Integer.parseInt(split[11]),new Color(Integer.parseInt(split[8])),new Color(Integer.parseInt(split[9])),new Color(Integer.parseInt(split[12])));
			donut.setSelected(true);
			int i=Integer.parseInt(split[14]);
			int size=Integer.parseInt(split[15]);
			return new CmdBringToBack(model, donut,i,size);
		} else if(shape.equals("hexagon")) {
			String[] split=logSplit[1].split(":");
			HexagonAdapter hexagon=new HexagonAdapter(new Point(Integer.parseInt(split[1]),Integer.parseInt(split[2]),Color.BLACK),Integer.parseInt(split[3]),new Color(Integer.parseInt(split[4])),new Color(Integer.parseInt(split[5])));
			hexagon.setSelected(true);
			int i=Integer.parseInt(split[7]);
			int size=Integer.parseInt(split[8]);
			return new CmdBringToBack(model, hexagon,i,size);
		}
		return null;
	}
	
	private static Line getLine(Point start,Point end,String color,boolean selected) {
		return new Line(start,end,new Color(Integer.parseInt(color)),selected);
	}
	
	private static Circle getCircle(Point point,String radius,String colorBorder,String colorFill,boolean selected) {
		
		return new Circle(point,Integer.parseInt(radius),new Color(Integer.parseInt(colorBorder)),new Color(Integer.parseInt(colorFill)),selected);
	}
	
	private static Rectangle getRectangle(Point point,String width,String height,String colorBorder,String colorFill,boolean selected) {
		
		return new Rectangle(point,Integer.parseInt(width),Integer.parseInt(height),new Color(Integer.parseInt(colorBorder)),new Color(Integer.parseInt(colorFill)),selected);
	
	}
	
	private static Donut getDonut(Point point,String radius,String innerRadis,String borderColor,String colorFill,String colorBorder,boolean selected) {
		return new Donut(point,Integer.parseInt(radius),Integer.parseInt(innerRadis),new Color(Integer.parseInt(borderColor)),new Color(Integer.parseInt(colorFill)),new Color(Integer.parseInt(colorBorder)),selected);
	}
	
	private static HexagonAdapter getHexagon(Point point,String radius,String colorBorder,String colorFill,boolean selected) {
		return new HexagonAdapter(point,Integer.parseInt(radius),new Color(Integer.parseInt(colorBorder)),new Color(Integer.parseInt(colorFill)),selected);
	}
	
	public static List<Command> mapLogsToCommand(List<Object> logs,DrawingModel model) {
		List<Command> commands=new ArrayList<Command>();
		
		logs.forEach(log -> {
			if(log.toString().contains("modify")) {
				commands.add(mapLogToModifyCommand(log, model));
			} else if(log.toString().contains("add")) {
				commands.add(mapLogToAddCommand(log, model));
			} else if(log.toString().contains("delete")) {
				commands.add(mapLogToDeleteCommand(log, model));
			}  else if(log.toString().contains("diselected")) {
				commands.add(mapLogToDiSelectCommand(log,model));
			} else if(log.toString().contains("selected")) {
				commands.add(mapLogToSelectCommand(log,model));
			} else if(log.toString().contains("undo")) {
				commands.add(mapLogToUndoCommand(log,model));
			} else if(log.toString().contains("redo")) {
				commands.add(mapLogToRedoCommand(log,model));
			} else if(log.toString().contains("bringtofront")) {
				commands.add(mapLogBringToFrontCommand(log,model));
			} else if(log.toString().contains("bringtoback")) {
				commands.add(mapLogBringToBackCommand(log,model));
			} else if(log.toString().contains("tofront")) {
				commands.add(mapLogToFrontCommand(log,model));
			} else if(log.toString().contains("toback")) {
				commands.add(mapLogToBackCommand(log,model));
			} 
		});
		return commands;
	}
}
