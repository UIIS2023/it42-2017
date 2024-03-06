package painting;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Line;
import geometry.Point;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogLine extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtStartCoorX;
	private JTextField txtStartCoorY;
	private JTextField txtEndCoorX;
	private JTextField txtEndCoorY;
	private boolean ok;
	private Color colorBorder;
	private Point startPoint;
	private Point endPoint;
	
	private Line dlgLine;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogLine dialog = new DialogLine();
			dialog.setTitle("Line");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogLine() {
		setTitle("Line");
		setBounds(100, 100, 638, 477);
		getContentPane().setLayout(new BorderLayout());
		setModal(true);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblStartPoint = new JLabel("Start point");
		lblStartPoint.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblStartCoorX = new JLabel("Coordinate X");
		lblStartCoorX.setFont(new Font("Tahoma", Font.PLAIN, 20));
		JLabel lblStartCoorY = new JLabel("Coordinate Y");
		lblStartCoorY.setFont(new Font("Tahoma", Font.PLAIN, 20));
		JLabel lblEndPoint = new JLabel("End point");
		lblEndPoint.setFont(new Font("Tahoma", Font.PLAIN, 20));
		JLabel lblEndCoorX = new JLabel("Coordinate X");
		lblEndCoorX.setFont(new Font("Tahoma", Font.PLAIN, 20));
		JLabel lblEndCoorY = new JLabel("Coordinate Y");
		lblEndCoorY.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtStartCoorX = new JTextField();
		txtStartCoorX.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtStartCoorX.setColumns(10);
		
		txtStartCoorX.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
					if (c=='-') {
						e.consume();
						getToolkit().beep();
					}
			}
		});
		
		txtStartCoorY = new JTextField();
		txtStartCoorY.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtStartCoorY.setColumns(10);
		
		txtStartCoorY.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
					if (c=='-') {
						e.consume();
						getToolkit().beep();
					}
			}
		});
		
		txtEndCoorX = new JTextField();
		txtEndCoorX.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtEndCoorX.setColumns(10);
		
		txtEndCoorX.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
					if (c=='-') {
						e.consume();
						getToolkit().beep();
					}
			}
		});
		
		txtEndCoorY = new JTextField();
		txtEndCoorY.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtEndCoorY.setColumns(10);
		
		txtEndCoorY.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
					if (c=='-') {
						e.consume();
						getToolkit().beep();
					}
			}
		});
		
		JButton btnColorBorder = new JButton("Set Border Color");
		btnColorBorder.setBackground(Color.CYAN);
		btnColorBorder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				colorBorder=JColorChooser.showDialog(null, "Izaberite boju linije", null);
			}
		});
		btnColorBorder.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblStartCoorX)
								.addComponent(lblStartCoorY)
								.addComponent(lblEndCoorX)
								.addComponent(lblEndCoorY))
							.addGap(51)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(txtEndCoorY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 142, Short.MAX_VALUE)
									.addComponent(btnColorBorder)
									.addGap(29))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(txtEndCoorX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtStartCoorY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtStartCoorX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addContainerGap(260, Short.MAX_VALUE))))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblStartPoint)
								.addComponent(lblEndPoint))
							.addContainerGap(477, Short.MAX_VALUE))))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblStartPoint)
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(txtStartCoorX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtStartCoorY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblStartCoorY))
							.addGap(113)
							.addComponent(btnColorBorder))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblStartCoorX)
							.addGap(82)
							.addComponent(lblEndPoint)
							.addGap(18)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblEndCoorX)
								.addComponent(txtEndCoorX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtEndCoorY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblEndCoorY))))
					.addContainerGap(123, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setBackground(Color.YELLOW);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							dlgLine = new Line();
							startPoint = new Point(Integer.parseInt(getTxtStartCoorX()),
									Integer.parseInt(getTxtStartCoorY()),Color.BLACK);
							endPoint = new Point(Integer.parseInt(getTxtEndCoorX()),
									Integer.parseInt(getTxtEndCoorY()),Color.BLACK);


							dlgLine.setStartPoint(startPoint);
							dlgLine.setEndPoint(endPoint);
							dlgLine.setBorderColor(getColorBorder());
							
							setOk(true);
							setVisible(false);
						} catch (Exception g) {
							JOptionPane.showMessageDialog(null, "Title");
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setBackground(Color.YELLOW);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dlgLine = null;
						setOk(false);
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
	}
		
		public String getTxtStartCoorX() {
			return txtStartCoorX.getText();
		}

		public void setTxtStartCoorX(String txtStartCoorX) {
			this.txtStartCoorX.setText(txtStartCoorX);
		}

		public String getTxtStartCoorY() {
			return txtStartCoorY.getText();
		}

		public void setTxtStartCoorY(String txtStartCoorY) {
			this.txtStartCoorY.setText(txtStartCoorY);
		}

		public String getTxtEndCoorX() {
			return txtEndCoorX.getText();
		}

		public void setTxtEndCoorX(String txtEndCoorX) {
			this.txtEndCoorX.setText(txtEndCoorX);
		}

		public String getTxtEndCoorY() {
			return txtEndCoorY.getText();
		}

		public void setTxtEndCoorY(String txtEndCoorY) {
			this.txtEndCoorY.setText(txtEndCoorY);
		}

		public boolean isOk() {
			return ok;
		}

		public void setOk(boolean ok) {
			this.ok = ok;
		}

		public Color getColorBorder() {
			return colorBorder;
		}

		public void setColorBorder(Color colorBorder) {
			this.colorBorder = colorBorder;
		}
		
		public void setTxtStartCoorXEdt(boolean b)
		{
			this.txtStartCoorX.setEditable(b);
		}
		
		public void setTxtStartCoorYEdt(boolean b)
		{
			this.txtStartCoorY.setEditable(b);
		}
		
		public void setTxtEndCoorXEdt(boolean b)
		{
			this.txtEndCoorX.setEditable(b);
		}
		
		public void setTxtEndCoorYEdt(boolean b)
		{
			this.txtEndCoorY.setEditable(b);
		}

		public Point getStartPoint() {
			return startPoint;
		}

		public void setStartPoint(Point startPoint) {
			this.startPoint = startPoint;
		}

		public Point getEndPoint() {
			return endPoint;
		}

		public void setEndPoint(Point endPoint) {
			this.endPoint = endPoint;
		}

		public Line getDlgLine() {
			return dlgLine;
		}

		public void setDlgLine(Line dlgLine) {
			this.dlgLine = dlgLine;
		}
	
}
