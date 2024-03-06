package painting;

import javax.swing.JFrame;


public class Main {

	public static void main(String[] args) {
		DrawingModel model=new DrawingModel();
		Drawing frame=new Drawing();
		frame.getView().setModel(model);
		DrawingController controller=new DrawingController(model, frame);
		controller.addObserver(frame);
		frame.setController(controller);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
