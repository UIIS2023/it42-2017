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

import geometry.Circle;
import geometry.Point;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogCircle extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCoorX;
	private JTextField txtCoorY;
	private JTextField txtRadius;
	private Color colorFill;
	private Color colorBorder;
	private boolean ok;
	
	private Circle dlgCircle;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogCircle dialog = new DialogCircle();
			dialog.setTitle("Circle");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogCircle() {
		setTitle("Circle");
		setBounds(100, 100, 568, 391);
		getContentPane().setLayout(new BorderLayout());
		this.setModal(true);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		
		JLabel lblCenter = new JLabel("Center");
		lblCenter.setFont(new Font("Tahoma", Font.PLAIN, 20));
		JLabel lblCoordinateX = new JLabel("Coordinate X");
		lblCoordinateX.setFont(new Font("Tahoma", Font.PLAIN, 20));
		JLabel lblCoordinateY = new JLabel("Coordinate Y");
		lblCoordinateY.setFont(new Font("Tahoma", Font.PLAIN, 20));
		JLabel lblRadius = new JLabel("Radius");
		lblRadius.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtCoorX = new JTextField();
		txtCoorX.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtCoorX.setColumns(10);
		
		txtCoorX.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
					if (c=='-') {
						e.consume();
						getToolkit().beep();
					}
			}
		});
		
		txtCoorY = new JTextField();
		txtCoorY.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtCoorY.setColumns(10);
		
		txtCoorY.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
					if (c=='-') {
						e.consume();
						getToolkit().beep();
					}
			}
		});
		
		txtRadius = new JTextField();
		txtRadius.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtRadius.setColumns(10);
		JButton btnColorFill = new JButton("Set Fill Color ");
		btnColorFill.setBackground(Color.CYAN);
		btnColorFill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				colorFill=JColorChooser.showDialog(null, "Izaberite boju", Color.WHITE);
			}
		});
		JButton btnColorBorder = new JButton("Set Border Color");
		btnColorBorder.setBackground(Color.CYAN);
		btnColorBorder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				colorBorder=JColorChooser.showDialog(null, "Izabari boju", Color.BLACK);
			}
		});
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCenter)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCoordinateX)
								.addComponent(lblCoordinateY)
								.addComponent(lblRadius))
							.addGap(52)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(txtRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(48)
									.addComponent(btnColorBorder))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(txtCoorY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(40)
									.addComponent(btnColorFill))
								.addComponent(txtCoorX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(60, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(31)
					.addComponent(lblCenter)
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCoordinateX)
						.addComponent(txtCoorX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCoordinateY)
						.addComponent(txtCoorY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(51)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRadius)
						.addComponent(txtRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnColorBorder))
					.addContainerGap(102, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addContainerGap(149, Short.MAX_VALUE)
					.addComponent(btnColorFill)
					.addGap(137))
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
							dlgCircle = new Circle();
							Point center = new Point(
									Integer.parseInt(getTxtCoorX()),
									Integer.parseInt(getTxtCoorY()),Color.BLACK);
							dlgCircle.setCenter(center);
							dlgCircle.setRadius(Integer.parseInt(getTxtRadius()));
							dlgCircle.setBorderColor(getColorBorder());
							dlgCircle.setColorFill(getColorFill());
							if(dlgCircle.getRadius()>0) {
								setVisible(false);
								setOk(true);
							} else {
								JOptionPane.showMessageDialog(new JFrame(), "Vrednost poluprecnika mora da bude pozitivna!", "Greška", JOptionPane.WARNING_MESSAGE);
								setVisible(true);
								setOk(false);
							}
							} catch(NumberFormatException ex)
							{
								JOptionPane.showMessageDialog(new JFrame(), "Neispravan unos podataka.Proverite da li su sva polja popunjena brojnim vrednostima!!", "Greška", JOptionPane.WARNING_MESSAGE);
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
						setVisible(false);
						dlgCircle = null;
						setOk(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	public Color getColorBorder() {
		return colorBorder;
	}

	public void setColorBorder(Color colorBorder) {
		this.colorBorder = colorBorder;
	}

	public Color getColorFill() {
		return colorFill;
	}

	public void setColorFill(Color colorFill) {
		this.colorFill = colorFill;
	}

	public String getTxtCoorY() {
		return txtCoorY.getText();
	}

	public void setTxtCoorY(String txtCoorY) {
		this.txtCoorY.setText(txtCoorY);
	}

	public String getTxtCoorX() {
		return txtCoorX.getText();
	}

	public void setTxtCoorX(String txtCoorX) {
		this.txtCoorX.setText(txtCoorX);
	}
	
	public void setTxtCoorXEdt(boolean b)
	{
		this.txtCoorX.setEditable(b);
	}
	
	public void setTxtCoorYEdt(boolean b)
	{
		this.txtCoorY.setEditable(b);
	}

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

	public String getTxtRadius() {
		return txtRadius.getText();
	}

	public void setTxtRadius(String textField) {
		this.txtRadius.setText(textField);
		}

	public Circle getDlgCircle() {
		return dlgCircle;
	}

	public void setDlgCircle(Circle dlgCircle) {
		this.dlgCircle = dlgCircle;
	}

}
