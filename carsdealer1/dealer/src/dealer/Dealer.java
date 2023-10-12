package dealer;

import javax.swing.*;  
import java.awt.*;  
import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Dealer {

	private JFrame frame;
	private JTextField txtbrand;
	private JTextField txtmodel;
	private JTextField txtyear;
	private JTextField txtprice;
	private JTable table;
	private JTextField txtid;
	private JTextField txtkm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dealer window = new Dealer();
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
	public Dealer() {
		initialize();
		Connect();
		table_load();
	}

	Connection con;
	PreparedStatement pst;
	ResultSet rs;

	
	public void Connect()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/javadealer", "root","");
        }
        catch (ClassNotFoundException ex)
        {
          ex.printStackTrace();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
 
    }
	
	public void table_load()
   {
     try
     {
    pst = con.prepareStatement("select * from cars");
    rs = pst.executeQuery();
    table.setModel(DbUtils.resultSetToTableModel(rs));
}
    catch (SQLException e)
     {
     e.printStackTrace();
  }
   }
	
	
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 844, 503);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u0410\u0432\u0442\u043E\u043A\u044A\u0449\u0430");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(332, 0, 227, 65);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u0420\u0435\u0433\u0438\u0441\u0442\u0440\u0430\u0446\u0438\u044F", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(33, 76, 359, 229);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("\u041C\u0430\u0440\u043A\u0430:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 27, 80, 36);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("\u041C\u043E\u0434\u0435\u043B:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(10, 53, 80, 36);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("\u0413\u043E\u0434\u0438\u043D\u0430:");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(10, 81, 80, 36);
		panel.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("\u0426\u0435\u043D\u0430:");
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_2.setBounds(22, 154, 80, 36);
		panel.add(lblNewLabel_1_1_2);
		
		txtbrand = new JTextField();
		txtbrand.setBounds(100, 37, 221, 20);
		panel.add(txtbrand);
		txtbrand.setColumns(10);
		
		txtmodel = new JTextField();
		txtmodel.setBounds(100, 63, 221, 20);
		panel.add(txtmodel);
		txtmodel.setColumns(10);
		
		txtyear = new JTextField();
		txtyear.setBounds(100, 91, 221, 20);
		panel.add(txtyear);
		txtyear.setColumns(10);
		
		txtprice = new JTextField();
		txtprice.setBounds(100, 164, 221, 20);
		panel.add(txtprice);
		txtprice.setColumns(10);
		
		txtkm = new JTextField();
		txtkm.setColumns(10);
		txtkm.setBounds(100, 123, 221, 20);
		panel.add(txtkm);
		
		JLabel lblNewLabel_1_2 = new JLabel("\u041A\u0438\u043B\u043E\u043C\u0435\u0442\u0440\u0438:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(10, 117, 92, 29);
		panel.add(lblNewLabel_1_2);
		
		JButton btnNewButton = new JButton("\u0414\u043E\u0431\u0430\u0432\u0438");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String brand,model,year,price,kilometers;
					brand = txtbrand.getText();
					year = txtyear.getText();
					model = txtmodel.getText();
					price = txtprice.getText();
					kilometers = txtkm.getText();
					try {
					pst = con.prepareStatement("insert into cars(brand,model,year,price,kilometers)values(?,?,?,?,?)");
					pst.setString(1, brand);
					pst.setString(2, model);
					pst.setString(3, year);
					pst.setString(4, price);
					pst.setString(5, kilometers);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Addedddd!!!!!");
					table_load();
					          
					txtbrand.setText("");
					txtmodel.setText("");
					txtyear.setText("");
					txtprice.setText("");
					txtkm.setText("");
					txtbrand.requestFocus();
					   }
					 
					catch (SQLException e1)
					        {
					e1.printStackTrace();
					}
				
				
				
				
			}

		});
		btnNewButton.setBounds(33, 316, 106, 46);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnExit = new JButton("\u0418\u0437\u043B\u0435\u0437");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			System.exit(0);
			}
		});
		btnExit.setBounds(149, 316, 105, 46);
		frame.getContentPane().add(btnExit);
		
		JButton btnClear = new JButton("\u0418\u0437\u0447\u0438\u0441\u0442\u0438");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				txtbrand.setText("");
				txtmodel.setText("");
				txtyear.setText("");
				txtprice.setText("");
				txtkm.setText("");
				txtid.setText("");
				txtbrand.requestFocus();
				
			}
		});
		btnClear.setBounds(274, 316, 105, 46);
		frame.getContentPane().add(btnClear);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(402, 76, 416, 287);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "\u0422\u044A\u0440\u0441\u0435\u043D\u0435", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(33, 373, 359, 65);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_1_3 = new JLabel("ID:");
		lblNewLabel_1_1_3.setBounds(42, 21, 55, 17);
		lblNewLabel_1_1_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_1.add(lblNewLabel_1_1_3);
		
		txtid = new JTextField();
		txtid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
			          
		            String id = txtid.getText();
		 
		                pst = con.prepareStatement("select brand,model,year,price,kilometers from cars where id = ?");
		                pst.setString(1, id);
		                ResultSet rs = pst.executeQuery();
		 
		            if(rs.next()==true)
		            {
		              
		                String brand = rs.getString(1);
		                String model = rs.getString(2);
		                String year = rs.getString(3);
		                String price = rs.getString(4);
		                String kilometers = rs.getString(5);
		                
		                txtbrand.setText(brand);
		                txtmodel.setText(model);
		                txtyear.setText(year);
		                txtprice.setText(price);
		                txtkm.setText(kilometers);
		                
		                
		            }  
		            else
		            {
		             txtbrand.setText("");
		             txtmodel.setText("");
		             txtyear.setText("");
		             txtprice.setText("");
		             txtkm.setText("");
		                
		            }
		            
		 
		 
		        }
		catch (SQLException ex) {
		          
		        }
		}
			
			
		});
		txtid.setBounds(107, 21, 187, 20);
		txtid.setColumns(10);
		panel_1.add(txtid);
		
		JButton btnUpdate = new JButton("\u0417\u0430\u043F\u0430\u0437\u0438 \u043F\u0440\u043E\u043C\u0435\u043D\u0438");
		btnUpdate.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
					
				String brand,model,price,year,kilometers,id;
					brand = txtbrand.getText();
					model = txtmodel.getText();
					year = txtyear.getText();
					price = txtprice.getText();
					id  = txtid.getText();
					kilometers = txtkm.getText();
					try {
					pst = con.prepareStatement("update cars set brand= ?,model=?,year=?,price=?,kilometers= ? where id =?");
					            pst.setString(1, brand);
					            pst.setString(2, model);
					            pst.setString(3, year);
					            pst.setString(4, price);
					            pst.setString(5, kilometers);
					            pst.setString(6, id);
					          
					            pst.executeUpdate();
					            JOptionPane.showMessageDialog(null, "Record Update!!!!!");
					            table_load();
					          
					            txtbrand.setText("");
					            txtmodel.setText("");
					            txtyear.setText("");
					            txtprice.setText("");
					            txtkm.setText("");
					            txtbrand.requestFocus();
					}
					 
					            catch (SQLException e1) {
					e1.printStackTrace();
					}
					}
			
		});
		btnUpdate.setBounds(421, 373, 155, 65);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnClear_1_1 = new JButton("\u041F\u0440\u043E\u0434\u0430\u0439");
		btnClear_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
               
				String id;
                id  = txtid.getText();
           try {
                pst = con.prepareStatement("delete from cars where id =?");
                pst.setString(1, id);
                pst.executeUpdate();
      JOptionPane.showMessageDialog(null, "Record Delete!!!!!");
      table_load();
    
               txtbrand.setText("");
               txtmodel.setText("");
               txtyear.setText("");
               txtprice.setText("");
               txtkm.setText("");
               txtid.setText("");
               txtbrand.requestFocus();
}

      catch (SQLException e1) {
e1.printStackTrace();
}
			}
		});
		btnClear_1_1.setBounds(586, 373, 89, 65);
		frame.getContentPane().add(btnClear_1_1);
	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	}
}
