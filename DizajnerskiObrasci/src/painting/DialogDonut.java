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

import geometry.Donut;
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

public class DialogDonut extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCoorX;
	private JTextField txtCoorY;
	private JTextField txtInside;
	private JTextField txtOuter;
	private Color colorFill;
	private Color borderColor;
	private Color colorLessBorder;
	private boolean ok;
	
	private Donut dlgDonut;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogDonut dialog = new DialogDonut();
			dialog.setTitle("A circle with a hole");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogDonut() {
		setTitle("Donut");
		setBounds(100, 100, 730, 492);
		getContentPane().setLayout(new BorderLayout());
		setModal(true);
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
		JLabel lblInside = new JLabel("Inside");
		lblInside.setFont(new Font("Tahoma", Font.PLAIN, 20));
		JLabel lblOuter = new JLabel("Outer");
		lblOuter.setFont(new Font("Tahoma", Font.PLAIN, 20));
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
		
		txtInside = new JTextField();
		txtInside.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtInside.setColumns(10);
		txtOuter = new JTextField();
		txtOuter.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtOuter.setColumns(10);
		JButton btnColorFill = new JButton("Set Fill Color");
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
				borderColor=JColorChooser.showDialog(null, "Izaberite boju", Color.BLUE);
			}
		});
		
		JButton btnColorLessBorder = new JButton("Set Border Less Color");
		btnColorLessBorder.setBackground(Color.CYAN);
		btnColorLessBorder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				colorLessBorder=JColorChooser.showDialog(null, "Izaberite boju", Color.BLUE);
			}
		});

		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblRadius)
						.addComponent(lblCenter)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCoordinateX)
								.addComponent(lblCoordinateY)
								.addComponent(lblInside)
								.addComponent(lblOuter))
							.addGap(55)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(txtOuter, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtInside, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtCoorY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtCoorX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.RELATED, 126, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnColorFill)
						.addComponent(btnColorBorder)
						.addComponent(btnColorLessBorder))
					.addContainerGap(87, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(23)
					.addComponent(lblCenter)
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCoordinateX)
						.addComponent(txtCoorX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblCoordinateY)
							.addGap(51)
							.addComponent(lblRadius))
						.addComponent(txtCoorY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblInside)
							.addGap(18)
							.addComponent(lblOuter))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(txtInside, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(txtOuter, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(96, Short.MAX_VALUE))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap(150, Short.MAX_VALUE)
					.addGap(31)
					.addComponent(btnColorFill)
					.addGap(30)
					.addComponent(btnColorBorder)
					.addGap(18)
					.addComponent(btnColorLessBorder)
					.addGap(89))
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
							dlgDonut = new Donut();
							Point center=new Point(Integer.parseInt(getTxtCoorX()),Integer.parseInt(getTxtCoorY()),Color.BLACK);
							dlgDonut.setCenter(center);
							dlgDonut.setInnerRadius(Integer.parseInt(getTxtInside()));
							dlgDonut.setRadius(Integer.parseInt(getTxtOuter()));
							dlgDonut.setBorderColor(getBorderColor());
							dlgDonut.setColorFill(getColorFill());
							dlgDonut.setColorLessBorder(Color.BLACK);
							if(dlgDonut.getInnerRadius()>0 && dlgDonut.getRadius()>0 && dlgDonut.getInnerRadius()<dlgDonut.getRadius()) {
								setOk(true);
								setVisible(false);
							} else {
								JOptionPane.showMessageDialog(new JFrame(), "Poluprecnici moraju da budu veci od nule i poluprecnik unutrasnjeg kruga mora da bude manji od poluprecnika velikog kruga!", "Error", JOptionPane.WARNING_MESSAGE);
								setOk(false);
								setVisible(true);
							}
						}catch(NumberFormatException ex)
						{
							JOptionPane.showMessageDialog(new JFrame(), "Neispravan unos podataka.Proverite da li su sva polja popunjena brojnim vrednostima!", "Error", JOptionPane.WARNING_MESSAGE);
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
						dlgDonut = null;
						setOk(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public Color getColorFill() {
		return colorFill;
	}

	public void setColorFill(Color colorFill) {
		this.colorFill = colorFill;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}
	
	public Color getColorLessBorder() {
		return colorLessBorder;
	}

	public void setColorLessBorder(Color colorLessBorder) {
		this.colorLessBorder = colorLessBorder;
	}

	public void setTxtCoorXEditable(boolean b)
	{
		this.txtCoorX.setEditable(b);
	}
	
	public void setTxtCoorYEditable(boolean b)
	{
		this.txtCoorY.setEditable(b);
	}
	
	public void setTxtInsideEditable(boolean b)
	{
		this.txtInside.setEditable(b);
	}
	
	public void setTxtOuterEditable(boolean b)
	{
		this.txtOuter.setEditable(b);
	}
	

	public String getTxtInside() {
		return txtInside.getText();
	}

	public void setTxtInside(String txtInside) {
		this.txtInside.setText(txtInside);
	}

	public String getTxtOuter() {
		return txtOuter.getText();
	}

	public void setTxtOuter(String txtOuter) {
		this.txtOuter.setText(txtOuter);
	}

	public String getTxtCoorX() {
		return txtCoorX.getText();
	}

	public void setTxtCoorX(String txtCoorX) {
		this.txtCoorX.setText(txtCoorX);
	}

	public String getTxtCoorY() {
		return txtCoorY.getText();
	}

	public void setTxtCoorY(String txtCoorY) {
		this.txtCoorY.setText(txtCoorY);
	}
	
	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

	public Donut getDlgDonut() {
		return dlgDonut;
	}

	public void setDlgDonut(Donut dlgDonut) {
		this.dlgDonut = dlgDonut;
	}
}
