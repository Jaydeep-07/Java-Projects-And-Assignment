
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.sql.*;
import javax.swing.table.*;

public class Test_DetFrame  extends JDialog implements  ActionListener
{
    JLabel lHeading, lTestid,lTestname,lRange,lUnit;
    JButton bAdd,bDelete,bUpdate;
    JTextField tTestid,tTestname,tRange,tUnit;
    
    Object[] data = new Object[4];  // Array of Objects of "Object" Class of size 4
    DefaultTableModel dtm = new DefaultTableModel();
    JTable jt = new JTable(dtm);

	Connection con;
	Statement st;
	ResultSet rs;
	PreparedStatement pst;
	
    public Test_DetFrame() 
    { 
    	setTitle("TEST DETAILS");
	    Container cc= getContentPane();
		cc.setLayout(null);
		setLocation(100,20);
		setSize(700,650);
		
		
	    Font f = new Font("Arial Rounded MT Bold",Font.BOLD+Font.ITALIC,18);
    	Font f2 = new Font("Vladimir Script",Font.BOLD+Font.ITALIC,40);  
	    lHeading= new JLabel("TEST INFORMATION...");   
	    lHeading.setFont(f2);

        lTestid= new JLabel("TEST ID:");			tTestid= new JTextField(20);
        lTestid.setFont(f);
        lTestname= new JLabel("TEST NAME:");			tTestname= new JTextField(20);
        lTestname.setFont(f);
        lRange= new JLabel("NORMAL RANGE:");			tRange= new JTextField(20);
        lRange.setFont(f);
        lUnit= new JLabel("UNIT:");					tUnit= new JTextField(20);
        lUnit.setFont(f);
        
        bAdd= new JButton("ADD");  bDelete= new JButton("DELETE"); bUpdate= new JButton("UPDATE");
		
		lHeading.setBounds(150,10,1000,100);
		lTestid.setBounds(30,70,200,100);            tTestid.setBounds(210,110,150,30);
		lTestname.setBounds(30,140,200,100);          tTestname.setBounds(210,180,150,30);
		lRange.setBounds(30,210,200,100);             tRange.setBounds(210,250,150,30);
		lUnit.setBounds(30,280,200,100);              tUnit.setBounds(210,320,150,30);
		
		bAdd.setBounds(400,120,100,40);
		bDelete.setBounds(400,190,100,40);
		bUpdate.setBounds(400,260,100,40);
		
		cc.add(lHeading);			
		cc.add(lTestid);	      cc.add(tTestid);    
		cc.add(lTestname);       cc.add(tTestname);
		cc.add(lRange);          cc.add(tRange);
		cc.add(lUnit);           cc.add(tUnit);
		cc.add(bAdd);   cc.add(bDelete);   cc.add(bUpdate);
		
		bAdd.addActionListener(this);
		bDelete.addActionListener(this);
		bUpdate.addActionListener(this);
		
		JLabel Lab_img=new JLabel(new ImageIcon("plainFrame.jpg"));
		Lab_img.setSize(700,700);
		cc.add(Lab_img);
  
		setVisible(true);
        //this.cc.add(p);  
        	
		
		String Tcol[] = {"Tid","Tname","Range","Unit"};  // Array of objects of String(Object type) class
		
		for(int i = 0 ; i < 4 ; i++)			
		{
			dtm.addColumn(Tcol[i]);
		}
		try
		{
			initialize();
			while(rs.next())
			{
				for(int j = 0; j < 4; j++)
				{
					String str = rs.getString(j+1);
					data[j] = str; 	
				}
				dtm.addRow(data);
			}
			rs.previous();
			int str= Integer.parseInt(rs.getString(1))+1;
			tTestid.setText(String.valueOf(str));
			
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
	}
	
	public void initialize()
	{
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con = DriverManager.getConnection("jdbc:odbc:PathDSN","","");
			st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = st.executeQuery("Select * From Test_Info");
		}	
		catch(Exception e)
		{
			System.out.println("Exception start : " + e);
		}
	}
	public void actionPerformed(ActionEvent ae)
     {
    
     	int flag=0;
        String str= tTestid.getText();
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
     		if(tTestid.getText().equals("") || tTestname.getText().equals("") || tRange.getText().equals("")||tUnit.getText().equals(""))
			{
				String msg = "Please fill all Necessory Data";
			   	JOptionPane.showMessageDialog(null,msg,"Pathology Lab System",
				JOptionPane.INFORMATION_MESSAGE);
				
			}
			else if(!(tTestname.getText().matches("([a-zA-Z.]+\\s*([a-zA-Z.]+\\s*([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]*)))")))
            {
            	String msg="Invalid  Name";
                JOptionPane.showMessageDialog((Component)null,msg,"Pathology Lab System",
               	JOptionPane.WARNING_MESSAGE);
               	tTestname.setText("");
               	tTestname.requestFocus();
          	}
          	else if(flag==1)// Invalid No
          	{
     		      tTestid.setText("");
     		      tTestid.requestFocus();
     		      String msg = "Invalid Number";
     		      JOptionPane.showMessageDialog((Component)null,msg,"Pathology Lab System",
               	   JOptionPane.WARNING_MESSAGE);
          	}
          	else     
          	{
                   
     		try
     		{
     			initialize();
     			pst= con.prepareStatement("Insert into Test_Info values(?,?,?,?)");
     			pst.setString(1,tTestid.getText());
     			pst.setString(2,tTestname.getText());
     			pst.setString(3,tRange.getText());
     			pst.setString(4,tUnit.getText());
     			pst.executeUpdate();
     			tTestid.setText(""); tTestname.setText(""); tRange.setText(""); tUnit.setText("");
     			
     			JOptionPane.showMessageDialog((Component)null,"Records inserted successfully......","Inserted.",JOptionPane.INFORMATION_MESSAGE);
     			pst.close();
     			con.close();
     		}
     		catch(Exception e)
     		{
     			JOptionPane.showMessageDialog(this,"Invalid Record.Try Again!!!");
     		}
            }//else
     	}//if bAdd
     	
     	if(ae.getActionCommand().equals("UPDATE"))
     	{
     		   try
     		   {
     		   initialize();
     		   String tname= JOptionPane.showInputDialog(this,"Enter Test no to Update Record:"," ");
     		   int no= Integer.parseInt(tname);
		       tTestid.setText(""+no);
		       
		       pst=con.prepareStatement("select * from Test_info where Tid =?");
		       pst.setInt(1,no);
		        
		       rs=pst.executeQuery();
		        if(rs.next()!=false)
		        {
		           tTestid.setText(rs.getString(1)); 
      	           tTestname.setText(rs.getString(2)); 
      	           tRange.setText(rs.getString(3));
      	           tUnit.setText(rs.getString(4));   
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
     	   if(tTestid.getText().equals("") || tTestname.getText().equals("") || tRange.getText().equals("")||tUnit.getText().equals(""))
			{
				String msg = "Please fill all Necessory Data";
			   	JOptionPane.showMessageDialog(null,msg,"Pathology Lab System",
				JOptionPane.INFORMATION_MESSAGE);
				
			}
			else if(!(tTestname.getText().matches("([a-zA-Z.]+\\s*([a-zA-Z.]+\\s*([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]*)))")))
            {
            	String msg="Invalid  Name";
                JOptionPane.showMessageDialog((Component)null,msg,"Pathology Lab System",
               	JOptionPane.WARNING_MESSAGE);
               	tTestname.setText("");
               	tTestname.requestFocus();
          	}
            else if(flag==1)// Invalid No
          	{
     		      tTestid.setText("");
     		      tTestid.requestFocus();
     		      String msg = "Invalid Number";
     		      JOptionPane.showMessageDialog((Component)null,msg,"Pathology Lab System",
               	   JOptionPane.WARNING_MESSAGE);
          	}
          	else     
          	{
     		try
     		{
     			initialize();
     			String Tid =tTestid.getText();
     			pst= con.prepareStatement("Update Test_Info SET Tid=?, Tname=?, Range=? , Unit=? where Tid=? ");
     			pst.setString(1, Tid);
     			pst.setString(2,tTestname.getText());
     			pst.setString(3,tRange.getText());
     			pst.setString(4,tUnit.getText());
     			pst.setString(5,Tid);
     			pst.executeUpdate();
     			tTestid.setText(""); tTestname.setText(""); tRange.setText(""); tUnit.setText("");
     			JOptionPane.showMessageDialog((Component)null,"Records Updated successfully......","Updated.",JOptionPane.INFORMATION_MESSAGE);
     			pst.close();
     			con.close();
     			
     		}
     		catch(Exception e)
     		{
     		JOptionPane.showMessageDialog(this,"Invalid Record.Try Again!!!");
     		}
            }//else
     	}// if bUpadate
     	if(ae.getActionCommand().equals("DELETE"))
     	{
     		if(tTestid.getText().equals(""))
			{
				String msg = "There is no Test to Delete";
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
     				{	initialize();
     					String Tid = tTestid.getText();
     					pst= con.prepareStatement("Delete from Test_Info where Tid=? ");
     					pst.setString(1,Tid);
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
    	new Test_DetFrame();
    }*/
    
    
}