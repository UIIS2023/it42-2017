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
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

import geometry.Circle;
import geometry.HexagonAdapter;
import geometry.Point;
import hexagon.Hexagon;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogHexagon extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Color colorFill;
	private Color colorBorder;
	private boolean ok;
	private JTextField txtCoorX;
	private JTextField txtCoorY;
	private JTextField txtRadius;
	private HexagonAdapter hexagon=new HexagonAdapter();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogHexagon dialog = new DialogHexagon();
			dialog.setTitle("Hexagon");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogHexagon() {
		setTitle("Hexagon");
		setModal(true);
		setBounds(100, 100, 568, 391);
		getContentPane().setLayout(new BorderLayout());
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
		txtCoorX.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtCoorX.setColumns(10);
		
		txtCoorY = new JTextField();
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
		txtCoorY.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtCoorY.setColumns(10);
		
		JButton btnColorBorder = new JButton("Set Border Color");
		btnColorBorder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				colorBorder=JColorChooser.showDialog(null, "Izabari boju", Color.BLACK);
			}
		});
		
		JButton btnColorFill = new JButton("Set Fill Color");
		btnColorFill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				colorFill=JColorChooser.showDialog(null, "Izaberite boju", Color.WHITE);
			}
		});
		
		txtRadius = new JTextField();
		txtRadius.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (c=='-') {
					e.consume();
					getToolkit().beep();
				}
			}
		});
		txtRadius.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtRadius.setColumns(10);
		
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
							.addGap(48)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(txtRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtCoorY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtCoorX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(193, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addContainerGap(399, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnColorBorder)
						.addComponent(btnColorFill))
					.addGap(32))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblCenter)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblCoordinateX)
							.addGap(18)
							.addComponent(lblCoordinateY))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(txtCoorX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtCoorY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(36)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(txtRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRadius))
					.addGap(4)
					.addComponent(btnColorBorder)
					.addGap(18)
					.addComponent(btnColorFill)
					.addContainerGap(60, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try
						{
						int radius=Integer.parseInt(getTxtRadius());
						int i=Integer.parseInt(getTxtCoorX());
						int j=Integer.parseInt(getTxtCoorY());
						if(radius > 0) {
							Hexagon hex=new Hexagon(i, j, radius);
							hexagon.setHexagon(hex);
							hexagon.setBorderColor(getColorBorder());
							hexagon.setColorFill(getColorFill());
							setVisible(false);
							setOk(true);
						} else {
							JOptionPane.showMessageDialog(new JFrame(), "Vrednost poluprecnika mora da bude pozitivna!", "Greška", JOptionPane.WARNING_MESSAGE);
							setVisible(true);
							setOk(false);
						}
						}
						catch(NumberFormatException ex)
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
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						hexagon = null;
						setOk(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
	}

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
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

	public Color getColorFill() {
		return colorFill;
	}

	public void setColorFill(Color colorFill) {
		this.colorFill = colorFill;
	}

	public Color getColorBorder() {
		return colorBorder;
	}

	public void setColorBorder(Color colorBorder) {
		this.colorBorder = colorBorder;
	}

	public String getTxtRadius() {
		return txtRadius.getText();
	}

	public void setTxtRadius(String txtRadius) {
		this.txtRadius.setText(txtRadius);
	}
	
	public void setTxtCoorXEdt(boolean b)
	{
		this.txtCoorX.setEditable(b);
	}
	
	public void setTxtCoorYEdt(boolean b)
	{
		this.txtCoorY.setEditable(b);
	}

	public HexagonAdapter getHexagon() {
		return hexagon;
	}

	public void setHexagon(HexagonAdapter hexagon) {
		this.hexagon = hexagon;
	}
	
	
}
