package painting;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import geometry.Circle;
import geometry.Point;
import geometry.Shape;
import help.Help;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Console;
import java.io.LineNumberInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Observable;
import java.util.Observer;
import java.util.Stack;
import java.awt.Color;
import java.awt.Component;

public class Drawing extends JFrame implements Observer {

	private JPanel contentPane;
	JToggleButton tglbtnPoint;
	JToggleButton tglbtnLine;
	JToggleButton tglbtnRectangle;
	JToggleButton tglbtnCircle;
	JToggleButton tglbtnCircleWithHole;
	JToggleButton tglbtnHexagon;
	JToggleButton tglbtnSelect;
	JToggleButton tglbtnModify;
	JToggleButton tglbtnDelete;
	JButton btnUndo;
	JButton btnRedo;
	JButton btnEdgeColor;
	JButton btnInsideColor;
	JButton btnToFront;
	JButton btnToBack;
	JButton btnBringToFront;
	JButton btnBringToBack;
	private String mode=Help.NORMAL;
	JButton btnLoadLog;
	private boolean variable=false;
	
	private PnlDrawing view=new PnlDrawing();
	private DrawingController controller;
	private DefaultListModel<String> logListModel=new DefaultListModel<String>();
	private JList<String> logList;
	
	private final JPanel logInfoPanel=new JPanel();
	
	private final JMenuBar mbMain=new JMenuBar();
	private final JMenu mnFile=new JMenu("File");
	private final JMenu mnSave=new JMenu("Save");
	private final JMenuItem mnSaveLog=new JMenuItem("Log");
	private final JMenuItem mnSaveCanvas=new JMenuItem("Canvas");
	
	private final JMenu mnLoad=new JMenu("Load");
	private final JMenuItem mnLoadCanvas=new JMenuItem("Canvas");
	private final JMenuItem mnLoadLog=new JMenuItem("Log"); 
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Drawing frame = new Drawing();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Drawing() {
		
		view=new PnlDrawing(this);
		setExtendedState(MAXIMIZED_BOTH);
		setTitle("IT42-2017 Komanovic Kosta");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 837, 534);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		ButtonGroup grupa=new ButtonGroup();
		
		JToolBar toolBar = new JToolBar();
		
		logList=new JList<String>(logListModel);
		logList.setBounds(15, 500, 500, 100);
		JScrollPane spLog=new JScrollPane();
		spLog.setBounds(15, 500, 500, 100);
		spLog.setViewportView(this.logList);
		this.contentPane.add(spLog,BorderLayout.SOUTH);
		
		logInfoPanel.setBounds(1150, 500, 150, 100);
		this.contentPane.add(logInfoPanel,BorderLayout.SOUTH);
		
		mbMain.setBounds(600, 500, 500, 100);
		this.contentPane.add(mbMain,BorderLayout.SOUTH);
		
		mbMain.add(mnFile);
		
		mnFile.add(mnSave);
		mnSave.add(mnSaveLog);
		mnSave.add(mnSaveCanvas);
		
		mnFile.add(mnLoad);
		mnLoad.add(mnLoadCanvas);
		mnLoad.add(mnLoadLog);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(view, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, 1350, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(22, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(view, GroupLayout.PREFERRED_SIZE, 399, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(25, Short.MAX_VALUE))
		);
		
	    tglbtnPoint = new JToggleButton("Point");
	    tglbtnPoint.setBackground(Color.GREEN);
	    tglbtnPoint.setForeground(Color.BLACK);
		tglbtnPoint.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mode=Help.POINT;
			}
		});
		grupa.add(tglbtnPoint);
		tglbtnPoint.setFont(new Font("Tahoma", Font.PLAIN, 20));
		toolBar.add(tglbtnPoint);
		
		tglbtnLine = new JToggleButton("Line");
		tglbtnLine.setBackground(Color.GREEN);
		tglbtnLine.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mode=Help.LINE;
			}
		});
		grupa.add(tglbtnLine);
		tglbtnLine.setFont(new Font("Tahoma", Font.PLAIN, 20));
		toolBar.add(tglbtnLine);
		
		tglbtnRectangle = new JToggleButton("Rectangle");
		tglbtnRectangle.setBackground(Color.GREEN);
		tglbtnRectangle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mode=Help.RECTANGLE;
			}
		});
		grupa.add(tglbtnRectangle);
		tglbtnRectangle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		toolBar.add(tglbtnRectangle);
		
		tglbtnCircle = new JToggleButton("Circle");
		tglbtnCircle.setBackground(Color.GREEN);
		tglbtnCircle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mode=Help.CIRCLE;
			}
		});
		grupa.add(tglbtnCircle);
		tglbtnCircle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		toolBar.add(tglbtnCircle);
		
		tglbtnCircleWithHole = new JToggleButton("Circle with hole");
		tglbtnCircleWithHole.setBackground(Color.GREEN);
		tglbtnCircleWithHole.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mode=Help.DONUT;
			}
		});
		grupa.add(tglbtnCircleWithHole);
		tglbtnCircleWithHole.setFont(new Font("Tahoma", Font.PLAIN, 20));
		toolBar.add(tglbtnCircleWithHole);
		
		tglbtnHexagon = new JToggleButton("Hexagon");
		tglbtnHexagon.setBackground(Color.GREEN);
		tglbtnHexagon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mode=Help.HEXAGON;
			}
		});
		grupa.add(tglbtnHexagon);
		tglbtnHexagon.setFont(new Font("Tahoma", Font.PLAIN, 20));
		toolBar.add(tglbtnHexagon);
		
		
		tglbtnSelect = new JToggleButton("Select");
		tglbtnSelect.setBackground(Color.GREEN);
		tglbtnSelect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mode=Help.SELECT;
				
			}
		});
		this.tglbtnSelect.setEnabled(false);
		grupa.add(tglbtnSelect);
		tglbtnSelect.setFont(new Font("Tahoma", Font.PLAIN, 20));
		toolBar.add(tglbtnSelect);
		
		tglbtnModify = new JToggleButton("Modify");
		tglbtnModify.setBackground(Color.GREEN);
		tglbtnModify.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
					controller.modifySelectedShape();
			}
		});
		this.tglbtnModify.setEnabled(false);
		grupa.add(tglbtnModify);
		tglbtnModify.setFont(new Font("Tahoma", Font.PLAIN, 20));
		toolBar.add(tglbtnModify);
		
		tglbtnDelete = new JToggleButton("Delete");
		tglbtnDelete.setBackground(Color.GREEN);
		tglbtnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.deleteSelectedShape();
			}
		});
		this.tglbtnDelete.setEnabled(false);
		grupa.add(tglbtnDelete);
		tglbtnDelete.setFont(new Font("Tahoma", Font.PLAIN, 20));
		toolBar.add(tglbtnDelete);
		
		btnUndo= new JButton("Undo");
		btnUndo.setEnabled(false);
		btnUndo.setBackground(Color.GREEN);
		btnUndo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.undo();
				variable=true;
			}
		});
		grupa.add(btnUndo);
		btnUndo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		toolBar.add(btnUndo);
		
		btnRedo = new JButton("Redo");
		btnRedo.setEnabled(false);
		btnRedo.setBackground(Color.GREEN);
		btnRedo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.redo();
			}
		});
		grupa.add(btnRedo);
		btnRedo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		toolBar.add(btnRedo);
		
		
		btnEdgeColor = new JButton("Edge color");
		btnEdgeColor.setBackground(Color.GREEN);
		btnEdgeColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.edgeColor(e);
			}
		});
		grupa.add(btnEdgeColor);
		btnEdgeColor.setFont(new Font("Tahoma", Font.PLAIN, 20));
		toolBar.add(btnEdgeColor);
		
		btnInsideColor = new JButton("Inside color");
		btnInsideColor.setBackground(Color.GREEN);
		btnInsideColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.insideColor(e);
			}
		});
		grupa.add(btnInsideColor);
		btnInsideColor.setFont(new Font("Tahoma", Font.PLAIN, 20));
		toolBar.add(btnInsideColor);
		
		btnToFront = new JButton("To front");
		btnToFront.setBackground(Color.GREEN);
		btnToFront.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.toFront();
			}
		});
		this.btnToFront.setEnabled(false);
		grupa.add(btnToFront);
		btnToFront.setFont(new Font("Tahoma", Font.PLAIN, 20));
		toolBar.add(btnToFront);
		
		btnToBack = new JButton("To back");
		btnToBack.setBackground(Color.GREEN);
		btnToBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.toBack(e);
			}
		});
		this.btnToBack.setEnabled(false);
		grupa.add(btnToBack);
		btnToBack.setFont(new Font("Tahoma", Font.PLAIN, 20));
		toolBar.add(btnToBack);
		
		btnBringToFront = new JButton("Bring to front");
		btnBringToFront.setBackground(Color.GREEN);
		btnBringToFront.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.bringToFront(e);
			}
		});
		this.btnBringToFront.setEnabled(false);
		grupa.add(btnBringToFront);
		btnBringToFront.setFont(new Font("Tahoma", Font.PLAIN, 20));
		toolBar.add(btnBringToFront);
		
		btnBringToBack = new JButton("Bring to back");
		btnBringToBack.setBackground(Color.GREEN);
		btnBringToBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.bringToBack(e);
			}
		});
		this.btnBringToBack.setEnabled(false);
		grupa.add(btnBringToBack);
		btnBringToBack.setFont(new Font("Tahoma", Font.PLAIN, 20));
		logInfoPanel.add(btnBringToBack);
		
		mnSaveLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.saveLog();
			}
		});
		
		mnSaveCanvas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.saveCanvas();
			}
		});
		
		mnLoadCanvas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.loadCanvas();
			}
		});
		
		mnLoadLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.loadLog();
			}
		});
		
		btnLoadLog= new JButton("Cmd log");
		btnLoadLog.setBackground(Color.GREEN);
		btnLoadLog.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.loadLogCommand();
			}
		});
		this.btnLoadLog.setEnabled(false);
		grupa.add(btnLoadLog);
		btnLoadLog.setFont(new Font("Tahoma", Font.PLAIN, 20));
		logInfoPanel.add(btnLoadLog);
		
		GroupLayout gl_panel = new GroupLayout(view);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 424, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 251, Short.MAX_VALUE)
		);
		view.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		

	}
	
	public boolean getTglbtnPoint() {
		return tglbtnPoint.isSelected();
	}
	
	public boolean getTglbtnLine() {
		return tglbtnLine.isSelected();
	}
	
	public boolean getTglbtnRectangle() {
		return tglbtnRectangle.isSelected();
	}
	
	public boolean getTglbtnCircle() {
		return tglbtnCircle.isSelected();
	}
	
	public boolean getTglbtnCircleWithHole() {
		return tglbtnCircleWithHole.isSelected();
	}
	
	public boolean getTglbtnHexagon() {
		return tglbtnHexagon.isSelected();
	}
	
	public boolean getTglbtnSelect() {
		return tglbtnSelect.isSelected();
	}
	
	public boolean getTglbtnModify() {
		return tglbtnModify.isSelected();
	}
	
	public boolean getTglbtnDelete() {
		return tglbtnDelete.isSelected();
	}

	public JButton getbtnUndo() {
		return btnUndo;
	}

	public void setbtnUndo(JButton btnUndo) {
		this.btnUndo = btnUndo;
	}

	public JButton getbtnRedo() {
		return btnRedo;
	}

	public void setbtnRedo(JButton btnRedo) {
		this.btnRedo = btnRedo;
	}

	public JButton getBtnEdgeColor() {
		return btnEdgeColor;
	}

	public void setBtnEdgeColor(JButton btnEdgeColor) {
		this.btnEdgeColor = btnEdgeColor;
	}

	public JButton getBtnInsideColor() {
		return btnInsideColor;
	}

	public void setBtnInsideColor(JButton btnInsideColor) {
		this.btnInsideColor = btnInsideColor;
	}

	public JButton getBtnToFront() {
		return btnToFront;
	}

	public void setBtnToFront(JButton btnToFront) {
		this.btnToFront = btnToFront;
	}

	public JButton getBtnToBack() {
		return btnToBack;
	}

	public void setBtnToBack(JButton btnToBack) {
		this.btnToBack = btnToBack;
	}

	public JButton getBtnBringToFront() {
		return btnBringToFront;
	}

	public void setBtnBringToFront(JButton btnBringToFront) {
		this.btnBringToFront = btnBringToFront;
	}

	public JButton getBtnBringToBack() {
		return btnBringToBack;
	}

	public void setBtnBringToBack(JButton btnBringToBack) {
		this.btnBringToBack = btnBringToBack;
	}

	public DefaultListModel<String> getLogListModel() {
		return this.logListModel;
	}

	public void setLogListModel(DefaultListModel<String> logListModel) {
		this.logListModel = logListModel;
	}

	public PnlDrawing getView() {
		return view;
	}
	
	public DrawingController getController() {
		return controller;
	}

	public void setController(DrawingController controller) {
		this.controller=controller;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public JButton getBtnLoadLog() {
		return btnLoadLog;
	}

	public void setBtnLoadLog(JButton btnLoadLog) {
		this.btnLoadLog = btnLoadLog;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable o, Object arg) {
			int counter=0;
			int i=0;
			List<Shape> shapes=(List<Shape>) arg;
			for(Shape shape:shapes) {
				i++;
				if(shape.isSelected()) {
					counter++;
				}
			}
			
			this.tglbtnDelete.setEnabled(counter>0);
			this.tglbtnModify.setEnabled(counter==1);
			this.tglbtnSelect.setEnabled(shapes.size()>0);
			this.btnToFront.setEnabled(counter==1);
			this.btnToBack.setEnabled(counter==1);
			this.btnBringToFront.setEnabled(counter==1);
			this.btnBringToBack.setEnabled(counter==1);
			
			if(counter>0 || i>0) {
				this.btnUndo.setEnabled(true);
			}
			
			if(variable==true) {
				this.btnRedo.setEnabled(true);
			}
	}

}
