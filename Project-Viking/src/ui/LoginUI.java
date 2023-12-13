package ui;

import main.Game;
import utilz.DatabaseConnection;
import javax.swing.*;

import java.awt.event.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginUI extends JFrame {
	private static final long serialVersionUID = -6921462126880570161L;

	JButton blogin = new JButton("Login");
	JButton bregister = new JButton("Register");
	JPanel panel = new JPanel();
	JTextField txtuser = new JTextField(15);
	JPasswordField pass = new JPasswordField(15);
	JLabel emailText = new JLabel("Email:");
	JLabel passwordText = new JLabel("Password:");
	JLabel loginLabel = new JLabel("Login Here..");
	
	public static String userSession;
	
	public static void main(String[] args) {
		new LoginUI();
	}

	public LoginUI() {
		setSize(1248, 672);
		setLocation(500, 280);
		panel.setLayout(null);

		loginLabel.setBounds(590, 130, 200, 25);
		emailText.setBounds(530, 170, 100, 25);
		txtuser.setBounds(530, 200, 200, 25);
		passwordText.setBounds(530, 240, 100, 25);
		pass.setBounds(530, 270, 200, 25);
		blogin.setBounds(580, 320, 100, 20);
		bregister.setBounds(580, 350, 100, 20);
		
		panel.add(blogin);
		panel.add(txtuser);
		panel.add(pass);
		panel.add(emailText);
		panel.add(passwordText);
		panel.add(loginLabel);
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
            	setUserSession(email);
            	new Game();
            	dispose();
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
	
	public void setUserSession(String email) {
		userSession = email;
	}
	
	public static String getUserSession() {
		return userSession;
	}
	
	public void removeUserSession() {
		userSession = null;
	}

	public void actionlogin() {
		blogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				validateUserCredentials();
			}
		});
	}
	
	public void actionRegister() {
		bregister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				new RegisterUI();
				dispose();
			}
		});
	}
}
