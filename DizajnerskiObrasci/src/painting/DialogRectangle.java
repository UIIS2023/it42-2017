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

import geometry.Point;
import geometry.Rectangle;

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
import javax.swing.LayoutStyle.ComponentPlacement;

public class DialogRectangle extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtXCoordinate;
	private JTextField txtYCoordinate;
	private JTextField txtHeight;
	private JTextField txtWidth;
	private boolean ok;
	private Color colorFill;
	private Color colorBorder;
	private JLabel lblOptional;
	
	private Rectangle dlgRectangle;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogRectangle dialog = new DialogRectangle();
			dialog.setTitle("Rectangle");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogRectangle() {
		setTitle("Rectangle");
		setBounds(100, 100, 802, 508);
		getContentPane().setLayout(new BorderLayout());
		this.setModal(true);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblXCoordinate = new JLabel("X coordinate");
		lblXCoordinate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		JLabel lblYCoordinate = new JLabel("Y coordinate");
		lblYCoordinate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		JLabel lblHeight = new JLabel("Height");
		lblHeight.setFont(new Font("Tahoma", Font.PLAIN, 20));
		JLabel lblWidth = new JLabel("Width");
		lblWidth.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtXCoordinate = new JTextField();
		txtXCoordinate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtXCoordinate.setColumns(10);
		
		txtXCoordinate.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
					if (c=='-') {
						e.consume();
						getToolkit().beep();
					}
			}
		});
		
		txtYCoordinate = new JTextField();
		txtYCoordinate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtYCoordinate.setColumns(10);
		
		txtYCoordinate.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
					if (c=='-') {
						e.consume();
						getToolkit().beep();
					}
			}
		});
		
		txtHeight = new JTextField();
		txtHeight.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtHeight.setColumns(10);
		txtWidth = new JTextField();
		txtWidth.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtWidth.setColumns(10);
		JButton btnColorFill = new JButton("Set Fill Color");
		btnColorFill.setBackground(Color.CYAN);
		btnColorFill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				colorFill=JColorChooser.showDialog(null, "Select a color", Color.WHITE);
			}
		});
		JButton btnColorBorder = new JButton("Set Border Color");
		btnColorBorder.setBackground(Color.CYAN);
		btnColorBorder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				colorBorder=JColorChooser.showDialog(null, "Select a color", Color.BLACK);
			}
		});
		
		lblOptional = new JLabel("");
		lblOptional.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblXCoordinate)
								.addComponent(lblYCoordinate)
								.addComponent(lblHeight)
								.addComponent(lblWidth))
							.addGap(55)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(txtYCoordinate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtXCoordinate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(txtHeight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtWidth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGap(164)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(btnColorBorder)
										.addComponent(btnColorFill)))))
						.addComponent(lblOptional))
					.addContainerGap(139, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(9)
							.addComponent(lblOptional)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblXCoordinate)
								.addComponent(txtXCoordinate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(26)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblYCoordinate)
								.addComponent(txtYCoordinate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(32)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblHeight)
								.addComponent(txtHeight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(34)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblWidth)
								.addComponent(txtWidth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(138)
							.addComponent(btnColorFill)
							.addGap(52)
							.addComponent(btnColorBorder)))
					.addContainerGap(176, Short.MAX_VALUE))
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
							dlgRectangle = new Rectangle();
							Point p=new Point(Integer.parseInt(getTxtXCoordinata()),
									Integer.parseInt(getTxtYCoordinata()),Color.BLACK);
							dlgRectangle.setUpperLeftPoint(p);
							dlgRectangle.setWidth(Integer.parseInt(getTxtWidth()));
							dlgRectangle.setHeight(Integer.parseInt(getTxtHeight()));
							dlgRectangle.setBorderColor(getColorBorder());
							dlgRectangle.setColorFill(getColorFill());
							if(dlgRectangle.getWidth()>0 && dlgRectangle.getHeight()>0) {
								setOk(true);
								setVisible(false);
							} else {
								JOptionPane.showMessageDialog(new JFrame(), "Visina i sirina moraju da budu pozitivne!", "Greška", JOptionPane.WARNING_MESSAGE);
								setOk(false);
								setVisible(true);
							}
						} catch(NumberFormatException ex)
						{
							JOptionPane.showMessageDialog(new JFrame(), "Neispravan unos podataka.Proverite da li su sva polja popunjena brojnim vrednostima!", "Greška", JOptionPane.WARNING_MESSAGE);
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
						dlgRectangle = null;
						setOk(false);
						setVisible(false);
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
			this.colorFill= colorFill;
		}

		public Color getColorBorder() {
			return colorBorder;
		}

		public void setColorBorder(Color colorBorder) {
			this.colorBorder = colorBorder;
		}

		public void setTxtXCoordinataEnabled(boolean b)
		{
			this.txtXCoordinate.setEnabled(b);
		}
		
		public void setTxtYCoordinataEnabled(boolean b)
		{
			this.txtYCoordinate.setEnabled(b);
		}
		
		public void setTxtHeightEnabled(boolean b)
		{
			this.txtHeight.setEnabled(b);
		}
		
		public void setTxtWidthEnabled(boolean b)
		{
			this.txtWidth.setEnabled(b);
		}
		public void setLblOptionalTxt(String text) {
			lblOptional.setText(text);
		}

		public String getTxtXCoordinata() {
			return txtXCoordinate.getText();
		}

		public void setTxtXCoordinata(String s) {
			this.txtXCoordinate.setText(s);
		}

		public boolean isOk() {
			return ok;
		}

		public void setOk(boolean ok) {
			this.ok = ok;
		}

		public String getTxtYCoordinata() {
			return txtYCoordinate.getText();
		}

		public void setTxtYCoordinata(String s) {
			this.txtYCoordinate.setText(s);
		}

		public String getTxtHeight() {
			return txtHeight.getText();
		}

		public void setTxtHeight(String broj) {
			this.txtHeight.setText(broj);
		}

		public String getTxtWidth() {
			return txtWidth.getText();
		}

		public void setTxtWidth(String broj) {
			this.txtWidth.setText(broj);
		}

		public Rectangle getDlgRectangle() {
			return dlgRectangle;
		}

		public void setDlgRectangle(Rectangle dlgRectangle) {
			this.dlgRectangle = dlgRectangle;
		}

}
