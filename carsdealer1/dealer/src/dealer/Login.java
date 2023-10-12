package dealer;

import java.sql.*;
import java.awt.EventQueue;

import javax.swing.*;  
import java.awt.event.*;  
import java.lang.Exception;  

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.sql.DriverManager;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import javax.swing.JPasswordField;
import dealer.Dealer;
public class Login {

	private JFrame frame;
	private JTextField txtuser;
	private JPasswordField txtpass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 354, 303);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lblNewLabel.setBounds(120, 11, 102, 34);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("User Name:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(20, 72, 84, 23);
		frame.getContentPane().add(lblNewLabel_1);
		
		txtuser = new JTextField();
		txtuser.setBounds(20, 106, 296, 20);
		frame.getContentPane().add(txtuser);
		txtuser.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Password:");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(20, 137, 102, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("LOGIN");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con=DriverManager.getConnection("jdbc:mysql://localhost/javadealer", "root","");
					    Statement stmt=con.createStatement();
						String sql="Select * from login where UserName='"+txtuser.getText()+"' and Password='"+txtpass.getText()+"'";
						ResultSet rs=stmt.executeQuery(sql);
						if(rs.next()) {
							JOptionPane.showMessageDialog(null, "Login Sucessfully");
			                dispose();
						}
							else {
							JOptionPane.showMessageDialog(null, "Login Incorrect username or password");
						con.close();
						}
					}catch(Exception e) {System.out.print(e);}
				
				
			}

			private void setVisible(boolean b) {
				// TODO Auto-generated method stub
				
			}
			});
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		btnNewButton.setBounds(10, 207, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		txtpass = new JPasswordField();
		txtpass.setBounds(20, 162, 296, 20);
		frame.getContentPane().add(txtpass);

}	
};
