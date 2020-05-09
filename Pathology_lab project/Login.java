// Login for Path_Lab Project..


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;


class Login extends JFrame implements ActionListener
{
    
    JTextField tUser;
    JPasswordField Pass; 
    JLabel lName,lUser,lPass;
    JButton bLogin,bCancel;
    Login()
    {
       super("LOGIN");
       Container cc= getContentPane();
       this.setBounds(100,100,500,470);
       cc.setLayout(null);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
       setVisible(true);
       
       Font f1 = new Font("Papyrus",Font.BOLD+Font.ITALIC,20);  
       Font f2 = new Font("Arial Rounded MT Bold",Font.BOLD+Font.ITALIC,20);
       Font f3 = new Font("Bookman Old Style", Font.BOLD+Font.ITALIC,20);

       lName=new JLabel(" JADHAV PATHOLOGY LAB");
       lName.setBounds(50,50,400,50);
       lName.setFont(f1);
       lName.setForeground(Color.BLACK);
       lName.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	   cc.add(lName);
	   
	   lUser=new JLabel("LOGIN :");
       lUser.setBounds(60,150,100,50);
       lUser.setFont(f2);
       lUser.setForeground(Color.BLACK);
	   cc.add(lUser);
	   
	   lPass=new JLabel("PASSWORD :");
       lPass.setBounds(60,230,200,50);
       lPass.setFont(f2);
       lPass.setForeground(Color.BLACK);
  	   cc.add(lPass);
	  
	   tUser= new JTextField(20);
	   tUser.setBounds(250,160,150,30);
	   tUser.setVisible(true);
	   cc.add(tUser);
	   
	   Pass= new JPasswordField();
	   Pass.setBounds(250,240,150,30);
	   Pass.setVisible(true);
	   cc.add(Pass);
	   
	   bLogin=new JButton("LOGIN");
       bLogin.setBounds(80,400,100,30);
       bLogin.addActionListener(this);
       bLogin.setVisible(true);
       cc.add(bLogin);
       
       bCancel=new JButton("CANCEL");
	   bCancel.setBounds(350,400,100,30);
       bCancel.addActionListener(this);
       bCancel.setVisible(true);
       cc.add(bCancel);
	   
	   ImageIcon ic=new ImageIcon("P7.jpg");
       JLabel lc=new JLabel(ic);
       setLayout(null);
       add(lc);
       lc.setBounds(-1,-100,500,650);
         
	   
	   
       
    }
    public void actionPerformed(ActionEvent a1)
    {
       int j=0;
       String s1=a1.getActionCommand();
       if(s1.equals("CANCEL"))
       {
         dispose();
       }
       else
       {
         String login=tUser.getText();
         String pwd= Pass.getText();
         if(login.equals("Jaydeep"))
         {
           if(pwd.equals("Patil"))
           { //JOptionPane.showMessageDialog(this,"Login Successful","Message",JOptionPane.INFORMATION_MESSAGE);
				j = JOptionPane.showConfirmDialog
							(this,"WELCOME","Login :Pathology Lab System",
							JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
				if(j == JOptionPane.OK_OPTION)
				{
					dispose();
					Path_menu p= new Path_menu();
					
				}
           }		
           else
           {
             JOptionPane.showMessageDialog(this,"Login Fail:Invalid Password","Message",JOptionPane.ERROR_MESSAGE);
             Pass.setText("");
             Pass.requestFocus();
          
           }
         }
         else
         {
           JOptionPane.showMessageDialog(this,"Login Fail:Invalid Login Name","Message",JOptionPane.ERROR_MESSAGE);
           tUser.setText("");
           tUser.requestFocus();
           
         }
         
       }
     }
     public static void main (String[] args) throws Exception 
     {
     	new Login();

     }
}



