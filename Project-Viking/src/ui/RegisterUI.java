package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;

import main.Game;
import utilz.DatabaseConnection;
import utilz.LoadSave;

public class RegisterUI extends JFrame {
private static final long serialVersionUID = -6921462126880570161L;

	JButton blogin = new JButton("Login");
	JButton bregister = new JButton("Register");
	JPanel panel = new JPanel();
	JTextField txtuser = new JTextField(15);
	JPasswordField pass = new JPasswordField(15);
	JLabel emailText = new JLabel("Email:");
	JLabel passwordText = new JLabel("Password:");
	JLabel registerLabel = new JLabel("Register Here..");
	
	private int menuX, menuY, menuWidth, menuHeight;
	private BufferedImage backgroundImg;

	public RegisterUI() {
		setSize(1248, 672);
		setLocation(500, 280);
		panel.setLayout(null);

		registerLabel.setBounds(590, 130, 200, 25);
		emailText.setBounds(530, 170, 100, 25);
		txtuser.setBounds(530, 200, 200, 25);
		passwordText.setBounds(530, 240, 100, 25);
		pass.setBounds(530, 270, 200, 25);
		bregister.setBounds(580, 320, 100, 20);
		blogin.setBounds(580, 350, 100, 20);

		panel.add(blogin);
		panel.add(txtuser);
		panel.add(pass);
		panel.add(emailText);
		panel.add(passwordText);
		panel.add(registerLabel);
		panel.add(bregister);
		
		setLocationRelativeTo(null);
		getContentPane().add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		actionlogin();
		actionRegister();
	}
	
	public void validateUserCredentials() {
		String email = txtuser.getText();
		String password = pass.getText();
		
		try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from users where email = ? and password = ?");
            pst.setString(1, email);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
            	new Game();
            } else {
                JOptionPane.showMessageDialog(this, "Incorrect Username or Password!");
                txtuser.setText("");
				pass.setText("");
				txtuser.requestFocus();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public boolean checkUserInput(String email, String password) {
		return (email.equals("") && password.equals(""));
	}
	
	public boolean enterUserData() {
		String email = txtuser.getText();
		String password = pass.getText();
		
		if (checkUserInput(email, password)) {
			JOptionPane.showMessageDialog(this, "Please enter all the details!");
			return false;
		} else {
			try {
				Connection con = DatabaseConnection.getConnection();
				PreparedStatement pst1 = con.prepareStatement("select * from users where email = ?");
				pst1.setString(1, email);
				ResultSet rs = pst1.executeQuery();
				if (rs.next()) {
					JOptionPane.showMessageDialog(this, "User already exists!");
					txtuser.setText("");
					pass.setText("");
					txtuser.requestFocus();
					return false;
				} else {
					String sql = "insert into users(email, password, highscore) values(?,?,0);";
		            PreparedStatement pst = con.prepareStatement(sql);

		            pst.setString(1, email);
		            pst.setString(2, password);

		            int rowCount = pst.executeUpdate();

		            if (rowCount > 0) {
		                JOptionPane.showMessageDialog(this, "Registration Successful!");
		            } else {
		                JOptionPane.showMessageDialog(this, "Error! Please try again!");
		            }
		            return true;
				}
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
			return false;
		}
	}
	
	public void actionlogin() {
		blogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				new LoginUI();
				dispose();
			}
		});
	}
	
	public void actionRegister() {
		bregister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				if (enterUserData()) {
					new LoginUI();
					dispose();
				}
			}
		});
	}
}
