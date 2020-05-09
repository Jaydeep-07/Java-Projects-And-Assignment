
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.sql.*;
import javax.swing.table.*;

public class LabAss_DetFrame extends JDialog implements ActionListener
{
    JLabel lHeading,lAssno,lAssname,lAdd,lPhno,lBirthDate,lGender,lJoinDate,lSchedule;
    JButton bAdd,bUpdate,bDelete;
    JTextField tAssno,tAssname,tAdd,tPhno,tBirthDate,tGender,tJoinDate,tSchedule;
    		
    Object[] data = new Object[8];  // Array of Objects of "Object" Class of size 8
    DefaultTableModel dtm = new DefaultTableModel();
    JTable jt = new JTable(dtm);

	Connection con;
	PreparedStatement pst;
	Statement st;
	ResultSet rs;
    
    public LabAss_DetFrame() 
    {
    	
    	setTitle("LAB ASSISTANT DETAILS");
    	Container cc= getContentPane();
  	    cc.setLayout(null);
		setLocation(100,20);
		setSize(700,650);
				
	    Font f = new Font("Arial Rounded MT Bold",Font.BOLD+Font.ITALIC,18);
    	Font f2 = new Font("Vladimir Script",Font.BOLD+Font.ITALIC,40);  
	    
	    lHeading= new JLabel("LAB ASSISTANT INFO");     
	    lHeading.setFont(f2);
	    lHeading.setBounds(150,10,1000,100);
	    
	    lAssno= new JLabel("LAB ASS.NO:");                   tAssno= new JTextField(20);              
	    lAssno.setFont(f);
	    lAssname= new JLabel("LAB ASS.NAME:");               tAssname= new JTextField(20);
	    lAssname.setFont(f);
	    lAdd= new JLabel("ADDRESS:");                        tAdd= new JTextField(20);  
        lAdd.setFont(f);	   
	    lPhno= new JLabel("PH. NO:");                        tPhno=new JTextField(20);
	    lPhno.setFont(f);
	    lBirthDate= new JLabel("BIRTHDATE:");				 tBirthDate=new JTextField(20);
	    lBirthDate.setFont(f);
	    lGender= new JLabel("GENDER:");						 tGender=new JTextField(20);
	    lGender.setFont(f);
	    lJoinDate= new JLabel("JOINING DATE:");              tJoinDate=new JTextField(20);
	    lJoinDate.setFont(f);
	    lSchedule= new JLabel("SCHEDULE:");	                 tSchedule=new JTextField(20);
	    lSchedule.setFont(f);
	    
	    bAdd= new JButton("ADD");  bDelete= new JButton("DELETE");  bUpdate=new JButton("UPDATE");
	    
	    lAssno.setBounds(20,100,200,100);                   tAssno.setBounds(200,140,150,30);
	    lAssname.setBounds(20,150,200,100);                 tAssname.setBounds(200,190,150,30);
	    lAdd.setBounds(20,200,200,100);                     tAdd.setBounds(200,240,150,30);
	    lPhno.setBounds(20,250,200,100);                    tPhno.setBounds(200,290,150,30);
	    
	    lBirthDate.setBounds(355,100,200,100);              tBirthDate.setBounds(530,140,150,30);
    	lGender.setBounds(355,150,200,100);                 tGender.setBounds(530,190,150,30);
    	lJoinDate.setBounds(355,200,200,100);               tJoinDate.setBounds(530,240,150,30);
    	
    	lSchedule.setBounds(20,300,200,100);               tSchedule.setBounds(200,340,150,30);
	    	
	    bAdd.setBounds(50,525,100,40);                  cc.add(bAdd);  
    	bDelete.setBounds(200,525,100,40);              cc.add(bUpdate);
    	bUpdate.setBounds(350,525,100,40);              cc.add(bDelete);
    	              
	    
	    cc.add(lHeading);	
	    cc.add(lAssno);                   cc.add(tAssno);
	    cc.add(lAssname);				  cc.add(tAssname);
	    cc.add(lAdd);			          cc.add(tAdd);				
	    cc.add(lPhno);                    cc.add(tPhno);
	    cc.add(lBirthDate);               cc.add(tBirthDate);
	    cc.add(lGender);                  cc.add(tGender);
	    cc.add(lJoinDate);                cc.add(tJoinDate);
	    cc.add(lSchedule);                cc.add(tSchedule);
	    
	    	
    	JLabel Lab_img=new JLabel(new ImageIcon("plainFrame.jpg"));
		Lab_img.setSize(700,700);
		cc.add(Lab_img);
  
		setVisible(true);
        //this.cc.add(p);  
	    
    	String Lcol[] = {"LassisNo"," LAname","LAdd","LAphno","Birthdate","Gender","JoiningDate","Time"};  // Array of objects of String(Object type) class
		for(int i = 0 ; i < 8 ; i++)			
		{
			dtm.addColumn(Lcol[i]);
		}
		try
		{
			initialize();
			while(rs.next())
			{
				for(int j = 0 ; j < 8 ; j++)
				{
					String str = rs.getString(j+1);
					data[j] = str; 	
				}
				dtm.addRow(data);
			}
			rs.previous();
			int str= Integer.parseInt(rs.getString(1))+1;
			tAssno.setText(String.valueOf(str));
		    JScrollPane js= new JScrollPane(jt);
			js.setBounds(40,400,500,100);
			cc.add(js);
			rs.close();
			st.close();
			con.close();
		}
		catch(Exception e)
		{
			System.out.println("Exception : " + e);
		}
		bAdd.addActionListener(this);
		bUpdate.addActionListener(this);
		bDelete.addActionListener(this);
    }
    public void initialize()
	{
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con = DriverManager.getConnection("jdbc:odbc:PathDSN","","");
			st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = st.executeQuery("Select * From Lab_Assis_Mast");
		}	
		catch(Exception e)
		{
			System.out.println("Exception start : " + e);
		}
	}
	public void actionPerformed(ActionEvent ae)
	{
		int flag=0;
        String str= tAssno.getText();
     	for(int i=0;i<str.length();i++)
     	{
     	  
     	  if(!(Character.isDigit(str.charAt(i))))
     	  {
     	     flag=1;
     	     break;
     	  }
    	}
		
     	if(ae.getActionCommand().equals("ADD"))
     	{
     		if(tAssno.getText().equals("") || tAssname.getText().equals("") || tAdd.getText().equals("")||tPhno.getText().equals("")||tGender.getText().equals("")||tJoinDate.getText().equals("")||tBirthDate.getText().equals("")||tSchedule.getText().equals(""))
			{
				String msg = "Please fill all Necessory Data";
			   	JOptionPane.showMessageDialog(null,msg,"Pathology Lab System",
				JOptionPane.INFORMATION_MESSAGE);
				
			}
			else if(!(tAssname.getText().matches("([a-zA-Z.]+\\s*([a-zA-Z.]+\\s*([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]*)))")))
            {
            	String msg="Invalid Assistant  Name";
                JOptionPane.showMessageDialog((Component)null,msg,"Pathology Lab System",
               	JOptionPane.WARNING_MESSAGE);
               	tAssname.setText("");
               	tAssname.requestFocus();
          	}
          	else if(!(tPhno.getText().matches("\\d{10}")))
            {
               	 String msg="Invalid Contact Number";
               	 JOptionPane.showMessageDialog((Component)null,msg,"MSEB Billing System",
               	 JOptionPane.WARNING_MESSAGE);
               	 tPhno.setText("");
               	 tPhno.requestFocus();
            }
            else if(flag==1)// Invalid No
          	{
     		      tAssno.setText("");
     		      tAssno.requestFocus();
     		      String msg = "Invalid Number";
     		      JOptionPane.showMessageDialog((Component)null,msg,"Pathology Lab System",
               	   JOptionPane.WARNING_MESSAGE);
          	}
          	else     
          	{
     		try
     		{   initialize();
     			pst= con.prepareStatement("Insert into Labassistant values(?,?,?,?,?,?,?,?)");
     			pst.setInt(1,Integer.parseInt(tAssno.getText()));
     			pst.setString(2,tAssname.getText());
     			pst.setString(3,tAdd.getText());
     			pst.setString(4,tPhno.getText());
     			pst.setString(5,tBirthDate.getText());
     			pst.setString(6,tGender.getText());
     			pst.setString(7,tJoinDate.getText());
     			pst.setString(8,tSchedule.getText());
     			pst.executeUpdate();
     			tAssno.setText(""); tAssname.setText(""); tAdd.setText(""); tPhno.setText(""); tBirthDate.setText(""); tGender.setText(""); tJoinDate.setText(""); tSchedule.setText("");
     			JOptionPane.showMessageDialog((Component)null,"Records inserted successfully......","Inserted.",JOptionPane.INFORMATION_MESSAGE);
     			pst.close();
     			con.close();
     		}
     		catch(Exception e)
     		{
     		JOptionPane.showMessageDialog(this,"Invalid Record.Try Again!!!");
     		}
            }//else
     	}
     	if(ae.getActionCommand().equals("UPDATE"))
     	{
     		   try
     		   {
     		   initialize();
     		   String tname= JOptionPane.showInputDialog(this,"Enter Assistant no to Update Record:"," ");
     		   int no= Integer.parseInt(tname);
		       tAssno.setText(""+no);
		       
		       pst=con.prepareStatement("select * from Lab_Assis_Mast where  LassisNo=?");
		       pst.setInt(1,no);
		        
		       rs=pst.executeQuery();
		       if(rs.next()!=false)
		       {
		          tAssno.setText(rs.getString(1)); 
      	          tAssname.setText(rs.getString(2)); 
      	          tAdd.setText(rs.getString(3));
      	          tPhno.setText(rs.getString(4));   
      	          tBirthDate.setText(rs.getString(5));             
      	          tGender.setText(rs.getString(6));
      	          tJoinDate.setText(rs.getString(7));
      	          tSchedule.setText(rs.getString(8));
      	          bUpdate.setLabel("SAVE");
      	          JOptionPane.showMessageDialog(this," Enter Record To Update:","Update",JOptionPane.WARNING_MESSAGE);
		       }
		       else
     		   {
     		  	  JOptionPane.showMessageDialog((Component)null,"No such record found....","Sorry..",JOptionPane.INFORMATION_MESSAGE);
     		   }
		        
		       }
		       catch(Exception e)
		       {
		       	JOptionPane.showMessageDialog(this,"Invalid Record.Try Again!!!");
		       }
		  }
     	if(ae.getActionCommand().equals("SAVE"))
     	{
     	
     	    if(tAssno.getText().equals("") || tAssname.getText().equals("") || tAdd.getText().equals("")||tPhno.getText().equals("")||tGender.getText().equals("")||tJoinDate.getText().equals("")||tBirthDate.getText().equals("")||tSchedule.getText().equals(""))
			{
				String msg = "Please fill all Necessory Data";
			   	JOptionPane.showMessageDialog(null,msg,"Pathology Lab System",
				JOptionPane.INFORMATION_MESSAGE);
				
			}
			else if(!(tAssname.getText().matches("([a-zA-Z.]+\\s*([a-zA-Z.]+\\s*([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]*)))")))
            {
            	String msg="Invalid Assistant  Name";
                JOptionPane.showMessageDialog((Component)null,msg,"Pathology Lab System",
               	JOptionPane.WARNING_MESSAGE);
               	tAssname.setText("");
               	tAssname.requestFocus();
          	}
          	else if(!(tPhno.getText().matches("\\d{10}")))
            {
               	 String msg="Invalid Contact Number";
               	 JOptionPane.showMessageDialog((Component)null,msg,"MSEB Billing System",
               	 JOptionPane.WARNING_MESSAGE);
               	 tPhno.setText("");
               	 tPhno.requestFocus();
            }
            else if(flag==1)// Invalid No
          	{
     		      tAssno.setText("");
     		      tAssno.requestFocus();
     		      String msg = "Invalid Number";
     		      JOptionPane.showMessageDialog((Component)null,msg,"Pathology Lab System",
               	   JOptionPane.WARNING_MESSAGE);
          	}
          	else     
          	{
            
     		try
     		{
     			initialize();
     			int Assno = Integer.parseInt(tAssno.getText());
     			pst= con.prepareStatement("Update Lab_Assis_Mast SET  LassisNo=?, LAname=?,Ladd=?,LAphno=?, Birthdate=?, Gender=?,JoiningDate=?, Time=? where LassisNo=?  ");
     			pst.setInt(1,Assno);
     			pst.setString(2,tAssname.getText());
     			pst.setString(3,tAdd.getText());
     			pst.setString(4,tPhno.getText());
     			pst.setString(5,tBirthDate.getText());
     			pst.setString(6,tGender.getText());
     			pst.setString(7,tJoinDate.getText());
     			pst.setString(8,tSchedule.getText());
     			pst.setInt(9,Assno);
     			pst.executeUpdate();
     			tAssno.setText(""); tAssname.setText(""); tAdd.setText(""); tPhno.setText(""); tBirthDate.setText(""); tGender.setText(""); tJoinDate.setText(""); tSchedule.setText("");
     			
     			JOptionPane.showMessageDialog((Component)null,"Records Updated successfully......","Updated.",JOptionPane.INFORMATION_MESSAGE);
     			pst.close();
     			con.close();
     			
     		}
     		catch(Exception e)
     		{
     			System.out.println (""+e);
     			//JOptionPane.showMessageDialog(this,"Invalid Record.Try Again!!!");
     		}
            }//else
     	}
     	if(ae.getActionCommand().equals("DELETE"))
     	{
     		if(tAssno.getText().equals(""))
			{
				String msg = "There is no Assistant to Delete";
			      	JOptionPane.showMessageDialog(null,msg,"Pathology Lab System",
			      	JOptionPane.INFORMATION_MESSAGE);		
			}
			else
			{
				String msg = "Are you sure!";
				int i = JOptionPane.showConfirmDialog
					(this,msg,"Pathology Lab System",JOptionPane.YES_NO_OPTION,
					JOptionPane.WARNING_MESSAGE);			
				if(i==JOptionPane.YES_OPTION)
				{
		     		try
     				{
     				 initialize();	
     				 int Assno = Integer.parseInt(tAssno.getText());
     			     pst= con.prepareStatement("Delete * from Lab_Assis_Mast where Lassisno=? ");
     			     pst.setInt(1,Assno);
     			     pst.executeUpdate();
     			
     			     JOptionPane.showMessageDialog((Component)null,"Records Deleted successfully......","Deleted.",JOptionPane.INFORMATION_MESSAGE);
     			     pst.close();
     			     con.close();
     		        }
     			    catch(Exception e)
     		        {
     			     JOptionPane.showMessageDialog(this,"Invalid Record.Try Again!!!");
     		        }
				}//if
			}//else
     	
     	}
	}
    /*public static void main (String[] args) 
    {
    	new LabAss_DetFrame();
    }*/
    
    
}