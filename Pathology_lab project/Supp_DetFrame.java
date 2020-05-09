
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.sql.*;
import javax.swing.table.*;


public class Supp_DetFrame extends JDialog implements ActionListener
 {
 	JLabel lHeading,lSuppno,lSuppname,lSuppadd,lSuppPhno;
 	JTextField tSuppno,tSuppname,tSuppadd,tSuppPhno;
 	JButton bAdd,bDelete,bUpdate;
 	
 	Object[] data = new Object[4];  // Array of Objects of "Object" Class of size 4
    DefaultTableModel dtm = new DefaultTableModel();
    JTable jt = new JTable(dtm);

	Connection con;
	PreparedStatement pst;
	Statement st1;
	ResultSet rs1;

    public Supp_DetFrame()
    {
    	setTitle("SUPPLIER DETAILS");
		Container cc= getContentPane();
		cc.setLayout(null);
		setLocation(100,20);
		setSize(700,650);
		
	    Font f = new Font("Arial Rounded MT Bold",Font.BOLD+Font.ITALIC,18);
    	Font f2 = new Font("Vladimir Script",Font.BOLD+Font.ITALIC,40);  
    		
    	lHeading=new JLabel("SUPPLIER DETAILS...");
    	lHeading.setFont(f2);
    	
    	lSuppno=new JLabel("SUPPLIER NO:");			tSuppno=new JTextField(20);					
    	lSuppno.setFont(f);
    	lSuppname=new JLabel("SUPPLIER NAME:");     tSuppname=new JTextField(20);
    	lSuppname.setFont(f);
    	lSuppadd=new JLabel("SUPPLIER ADDRESS:");   tSuppadd=new JTextField(20);
    	lSuppadd.setFont(f);
    	lSuppPhno=new JLabel("SUPPLIER PH.NO:");    tSuppPhno=new JTextField(20);
    	lSuppPhno.setFont(f);
    	
    	bAdd= new JButton("ADD");		   bDelete= new JButton("DELETE");
    	bUpdate= new JButton("UPDATE");    
    		    	
    	lHeading.setBounds(150,10,1000,100);
    	
    	lSuppno.setBounds(20,70,200,100);           tSuppno.setBounds(230,110,150,30);
    	lSuppname.setBounds(20,140,200,100);         tSuppname.setBounds(230,180,150,30);
    	lSuppadd.setBounds(20,210,200,100);          tSuppadd.setBounds(230,250,150,30);
    	lSuppPhno.setBounds(20,280,200,100);         tSuppPhno.setBounds(230,320,150,30);
    	
    	bAdd.setBounds(50,525,100,40);
        bDelete.setBounds(200,525,100,40);
        bUpdate.setBounds(350,525,100,40);
       
    	cc.add(lHeading);
    	cc.add(lSuppno);          cc.add(tSuppno);
    	cc.add(lSuppname);        cc.add(tSuppname);
    	cc.add(lSuppadd);         cc.add(tSuppadd);
    	cc.add(lSuppPhno);        cc.add(tSuppPhno);
    	cc.add(bAdd);             cc.add(bUpdate);
    	cc.add(bDelete);  
    	JLabel Lab_img=new JLabel(new ImageIcon("plainFrame.jpg"));
		Lab_img.setSize(700,700);
		cc.add(Lab_img);
  
		setVisible(true);
        //this.add(p);  	    
    	
    	String Tcol[] = {"Sno","Sname","Sadd","Sphno"};  // Array of objects of String(Object type) class
		
		for(int i = 0 ; i < 4 ; i++)			
		{
			dtm.addColumn(Tcol[i]);
		}
		try
		{
			initialize();
			while(rs1.next())
			{
				for(int j = 0; j < 4; j++)
				{
					String str = rs1.getString(j+1);
					data[j] = str; 	
				}
				dtm.addRow(data);
			}
			rs1.previous();
			int str= Integer.parseInt(rs1.getString(1))+1;
		    tSuppno.setText(String.valueOf(str));
			
			JScrollPane js=new JScrollPane(jt);
			js.setBounds(40,400,500,100);
			cc.add(js);
			
			rs1.close();
			st1.close();
			con.close();
		}
		catch(Exception e)
		{
			System.out.println("Exception : " + e);
		}
	    bAdd.addActionListener(this);
	    bDelete.addActionListener(this);
	    bUpdate.addActionListener(this);
    	
    }
    public void initialize()
	{
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con = DriverManager.getConnection("jdbc:odbc:PathDSN","","");
			st1 = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs1 = st1.executeQuery("Select * From Supplier_Mast");
		}	
		catch(Exception e)
		{
			System.out.println("Exception start : " + e);
		}
	}// initialize

	public void actionPerformed(ActionEvent ae)
	{
        int flag=0;
        String str= tSuppno.getText();
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
     		if(tSuppno.getText().equals("") || tSuppname.getText().equals("") || tSuppPhno.getText().equals("")||tSuppadd.getText().equals(""))
			{
				String msg = "Please fill all Necessory Data";
			   	JOptionPane.showMessageDialog(null,msg,"Pathology Lab System",
				JOptionPane.INFORMATION_MESSAGE);
				
			}
			else if(!(tSuppname.getText().matches("([a-zA-Z.]+\\s*([a-zA-Z.]+\\s*([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]*)))")))
            {
            	String msg="Invalid  Name";
                JOptionPane.showMessageDialog((Component)null,msg,"Pathology Lab System",
               	JOptionPane.WARNING_MESSAGE);
               	tSuppname.setText("");
               	tSuppname.requestFocus();
          	}
          	else if(!(tSuppPhno.getText().matches("\\d{10}")))
            {
               	 String msg="Invalid Contact Number";
               	 JOptionPane.showMessageDialog((Component)null,msg,"MSEB Billing System",
               	 JOptionPane.WARNING_MESSAGE);
               	 tSuppPhno.setText("");
               	 tSuppPhno.requestFocus();
            }
            else if(flag==1)// Invalid No
          	{
     		      tSuppno.setText("");
     		      tSuppno.requestFocus();
     		      String msg = "Invalid Number";
     		      JOptionPane.showMessageDialog((Component)null,msg,"Pathology Lab System",
               	   JOptionPane.WARNING_MESSAGE);
          	}
          	else     
          	{
     		try
     		{
     			initialize();
     			pst= con.prepareStatement("Insert into Supplier_Mast values(?,?,?,?)");
     			pst.setInt(1,Integer.parseInt(tSuppno.getText()));
     			pst.setString(2,tSuppname.getText());
     			pst.setString(3,tSuppadd.getText());
     			pst.setString(4,tSuppPhno.getText());
     			pst.executeUpdate();
     			tSuppno.setText(""); tSuppname.setText(""); tSuppadd.setText(""); tSuppPhno.setText("");
     			
     			JOptionPane.showMessageDialog((Component)null,"Records inserted successfully......","Inserted.",JOptionPane.INFORMATION_MESSAGE);
     			pst.close();
     			con.close();
     		}
     		catch(Exception e)
     		{
     			JOptionPane.showMessageDialog(this,"Invalid Record.Try Again!!!");
     		}
            }//else
     	}// if bAdd
     	if(ae.getActionCommand().equals("UPDATE"))
     	{
     		   try
     		   {
     		   initialize();
     		   String tname= JOptionPane.showInputDialog(this,"Enter Supplier no to Update Record:"," ");
     		   int no= Integer.parseInt(tname);
		       tSuppno.setText(""+no);
		      
		       pst=con.prepareStatement("select * from Supplier_Mast where Sno =?");
		       pst.setInt(1,no);
		        
		       rs1=pst.executeQuery();
		       if(rs1.next()!=false)
		       {
		         tSuppno.setText(rs1.getString(1)); 
      	         tSuppname.setText(rs1.getString(2)); 
      	         tSuppadd.setText(rs1.getString(3));
      	         tSuppPhno.setText(rs1.getString(4));
      	         bUpdate.setLabel("SAVE");   
      	         JOptionPane.showMessageDialog(this," Enter Record To Update:","Update",JOptionPane.WARNING_MESSAGE);
		       }
		       else
     		   {
     		  	  JOptionPane.showMessageDialog((Component)null,"No such record found....","Sorry..",JOptionPane.INFORMATION_MESSAGE);
     		   }
		         rs1.close();
		         pst.close();
     			 con.close();
     			
		       }
		       catch(Exception e)
		       {JOptionPane.showMessageDialog(this,"Invalid Record.Try Again!!!");
		       }
		  }
     	
     	if(ae.getActionCommand().equals("SAVE"))
     	{
     	    if(tSuppno.getText().equals("") || tSuppname.getText().equals("") || tSuppPhno.getText().equals("")||tSuppadd.getText().equals(""))
			{
				String msg = "Please fill all Necessory Data";
			   	JOptionPane.showMessageDialog(null,msg,"Pathology Lab System",
				JOptionPane.INFORMATION_MESSAGE);
				
			}
			else if(!(tSuppname.getText().matches("([a-zA-Z.]+\\s*([a-zA-Z.]+\\s*([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]*)))")))
            {
            	String msg="Invalid  Name";
                JOptionPane.showMessageDialog((Component)null,msg,"Pathology Lab System",
               	JOptionPane.WARNING_MESSAGE);
               	tSuppname.setText("");
               	tSuppname.requestFocus();
          	}
          	else if(!(tSuppPhno.getText().matches("\\d{10}")))
            {
               	 String msg="Invalid Contact Number";
               	 JOptionPane.showMessageDialog((Component)null,msg,"MSEB Billing System",
               	 JOptionPane.WARNING_MESSAGE);
               	 tSuppPhno.setText("");
               	 tSuppPhno.requestFocus();
            }
			else if(flag==1)// Invalid No
          	{
     		      tSuppno.setText("");
     		      tSuppno.requestFocus();
     		      String msg = "Invalid Number";
     		      JOptionPane.showMessageDialog((Component)null,msg,"Pathology Lab System",
               	   JOptionPane.WARNING_MESSAGE);
          	}
          	else     
          	{
     		  try
     		  {
     		  	initialize();
     			int Suppno = Integer.parseInt(tSuppno.getText());
     			pst= con.prepareStatement("Update Supplier_Mast SET Sno=?, Sname=?,Sadd=?, SPhno=? where Sno=?  ");
     			pst.setInt(1,Suppno);
     			pst.setString(2,tSuppname.getText());
     			pst.setString(3,tSuppadd.getText());
     			pst.setString(4,tSuppPhno.getText());
     			pst.setInt(5,Suppno);
     			pst.executeUpdate();
     			
     			tSuppno.setText(""); tSuppname.setText(""); tSuppadd.setText(""); tSuppPhno.setText("");
     			JOptionPane.showMessageDialog((Component)null,"Records Updated successfully......","Updated.",JOptionPane.INFORMATION_MESSAGE);
     			pst.close();
     			con.close();
     			
     	      }
     		  catch(Exception e)
     	      {
     			JOptionPane.showMessageDialog(this,"Invalid Record.Try Again!!!");
     		  }
            }//else
     	}// if save
     	
     	if(ae.getActionCommand().equals("DELETE"))
     	{
     		if(tSuppno.getText().equals(""))
			{
				String msg = "There is no Supplier to Delete";
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
     				int Suppno = Integer.parseInt(tSuppno.getText());
     				pst= con.prepareStatement("Delete from Supplier_Mast where Sno=? ");
     				pst.setInt(1,Suppno);
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
     	
     	}//if bDelete
	}//actionParformed
	
    /*public static void main (String[] args) 
    {
    	new Supp_DetFrame();
    }*/
    
    
}