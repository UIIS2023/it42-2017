package painting;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import geometry.Point;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogPoint extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private boolean ok;
	private Color colorBorder;
	private JLabel lblCoordinateY;
	private JLabel lblCoordinateX;
	private JTextField txtx;
	private JTextField txty;
	
	private Point dlgPoint;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogPoint dialog = new DialogPoint();
			dialog.setTitle("Point");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogPoint() {
		setTitle("Point");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		setModal(true);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		lblCoordinateY = new JLabel("Coordinate Y");
		lblCoordinateY.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		lblCoordinateX = new JLabel("Coordinate X");
		lblCoordinateX.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		txtx = new JTextField();
		txtx.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtx.setColumns(10);
		
		txtx.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
					if (c=='-') {
						e.consume();
						getToolkit().beep();
					}
			}
		});
		
		txty = new JTextField();
		txty.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txty.setColumns(10);
		
		txty.addKeyListener(new KeyAdapter() {
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
				colorBorder=JColorChooser.showDialog(null, "Izaberite boju tacke", null);
			}
		});
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(35)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCoordinateX)
						.addComponent(lblCoordinateY))
					.addGap(49)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(txty)
						.addComponent(txtx, GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE))
					.addContainerGap(126, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addContainerGap(313, Short.MAX_VALUE)
					.addComponent(btnColorBorder)
					.addGap(22))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(54)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCoordinateX)
						.addComponent(txtx, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCoordinateY)
						.addComponent(txty, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addComponent(btnColorBorder)
					.addContainerGap(44, Short.MAX_VALUE))
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
							dlgPoint = new Point();
							dlgPoint.setX(Integer.parseInt(getTxtx()));
							dlgPoint.setY(Integer.parseInt(getTxty()));
							dlgPoint.setBorderColor(Color.BLACK);
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
						setVisible(false);
						dlgPoint = null;
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

	public Color getColorBorder() {
		return colorBorder;
	}

	public void setColorBorder(Color colorBorder) {
		this.colorBorder = colorBorder;
	}

	public String getTxtx() {
		return txtx.getText();
	}

	public void setTxtx(String txtx) {
		this.txtx.setText(txtx);
	}

	public String getTxty() {
		return txty.getText();
	}

	public void setTxty(String txty) {
		this.txty.setText(txty);
	}
	public void setTxtXEdt(boolean b)
	{
		this.txtx.setEditable(b);
	}
	
	public void setTxtYEdt(boolean b)
	{
		this.txty.setEditable(b);
	}

	public Point getDlgPoint() {
		return dlgPoint;
	}

	public void setDlgPoint(Point dlgPoint) {
		this.dlgPoint = dlgPoint;
	}
	
}
