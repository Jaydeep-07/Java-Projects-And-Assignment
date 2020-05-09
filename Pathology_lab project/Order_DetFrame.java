
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.sql.*;
import javax.swing.table.*;

public class Order_DetFrame extends JDialog implements ActionListener
{
	JLabel lHeading,lOno,lSuppno,lSuppname,lDate,lPrno,lPrname,lPrate,lQuantity,lAmount;
	JTextField tOno,tSuppno,tSuppname,tDate,tPrno,tPrname,tPrate,tQuantity,tAmount;
	JButton bAdd,bDelete,bUpdate;
    
    
    Object[] data = new Object[9];  // Array of Objects of "Object" Class of size 9

	DefaultTableModel dtm = new DefaultTableModel();

	JTable jt = new JTable(dtm);
	  
    Connection con;
	Statement st;
	ResultSet rs;
	PreparedStatement pst;
	
    public Order_DetFrame() 
    {
		setTitle("ORDER DETAIL");
		Container cc= getContentPane();
		setLayout(null);
		setLocation(100,20);
		setSize(700,680);
		
	    Font f = new Font("Arial Rounded MT Bold",Font.BOLD+Font.ITALIC,18);
    	Font f2 = new Font("Vladimir Script",Font.BOLD+Font.ITALIC,40);  
    		
    	lHeading= new JLabel("ORDER DETAILS...");
    	lHeading.setFont(f2);	
    	
    	lOno= new JLabel("ORDER NO:");
    	lOno.setFont(f);
    	lSuppno=new JLabel("SUPPLIER NO:");
    	lSuppno.setFont(f);
    	lSuppname= new JLabel("SUPPLIER NAME:");	
    	lSuppname.setFont(f);		
    	lPrno = new JLabel("PRODUCT NO:");
		lPrno.setFont(f);
		lPrname= new JLabel("PRODUCT NAME:");
		lPrname.setFont(f);
		lDate= new JLabel("DATE:");
		lDate.setFont(f);
		lPrate= new JLabel("PRODUCT RATE:");
		lPrate.setFont(f);
		lAmount=new JLabel("AMOUNT:");
		lAmount.setFont(f);
		lQuantity = new JLabel("QUANTITY:");
		lQuantity.setFont(f);
		
		tOno =new JTextField(20);
        tSuppno =new JTextField(20);
        tSuppname =new JTextField(20);
        tPrno =new JTextField(20);
        tPrname =new JTextField(20);
        tDate =new JTextField(20);
        tPrate =new JTextField(20);
        tAmount= new JTextField(20);
        tQuantity= new JTextField(20);
        
        bAdd= new JButton("ADD");
        bDelete= new JButton("DELETE");
        bUpdate= new JButton("UPDATE");
        
        lHeading.setBounds(150,10,1000,100);
        
        lOno.setBounds(20,70,200,100);              tOno.setBounds(185,100,150,30);
        lSuppno.setBounds(20,140,200,100);          tSuppno.setBounds(185,170,150,30);
        lPrno.setBounds(20,210,200,100);            tPrno.setBounds(185,240,150,30);
        lPrate.setBounds(20,280,200,100);           tPrate.setBounds(185,310,150,30);          
        lAmount.setBounds(20,350,200,100);          tAmount.setBounds(185,380,150,30);
        
        lDate.setBounds(355,70,200,100);            tDate.setBounds(530,100,150,30);
        lSuppname.setBounds(355,140,200,100);       tSuppname.setBounds(530,170,150,30);
        lPrname.setBounds(355,210,200,100);         tPrname.setBounds(530,240,150,30);
        lQuantity.setBounds(355,280,200,100);       tQuantity.setBounds(530,310,150,30);
      
        bAdd.setBounds(50,580,100,40);
        bDelete.setBounds(200,580,100,40);
        bUpdate.setBounds(350,580,100,40);
       
        cc.add(lHeading);     
        cc.add(lOno);      cc.add(tOno);
        cc.add(lPrno);     cc.add(tPrno);
        cc.add(lPrname);   cc.add(tPrname);
        cc.add(lSuppno);   cc.add(tSuppno);
        cc.add(lSuppname); cc.add(tSuppname);
        cc.add(lDate);     cc.add(tDate);
        cc.add(lPrate);    cc.add(tPrate);
        cc.add(lAmount) ;   cc.add(tAmount);
        cc.add(lQuantity); cc.add(tQuantity);
        cc.add(bAdd);      cc.add(bUpdate);
        cc.add(bDelete); 
        	
        
        JLabel Lab_img=new JLabel(new ImageIcon("plainFrame.jpg"));
		Lab_img.setSize(700,700);
		cc.add(Lab_img);
  
		setVisible(true);
        //this.cc.add(p1);  
        	
        	
        String Tcol[] = {"Ono","Odate","Sno","Sname","Prid","Prname","Prate","quantity","Amount"};  // Array of objects of String(Object type) class
		
		for(int i = 0 ; i <9  ; i++)			
		{
			dtm.addColumn(Tcol[i]);
		}
		try
		{
			
			initialize();
			while(rs.next())
			{
				for(int j = 0; j < 9; j++)
				{
					String str = rs.getString(j+1);
					data[j] = str; 	
				}
				dtm.addRow(data);
			}
			rs.previous();
            int str= Integer.parseInt(rs.getString(1))+1;		
			tOno.setText(String.valueOf(str));
			
		    
			
			JScrollPane js=new JScrollPane(jt);
			js.setBounds(40,450,500,100);
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
		bDelete.addActionListener(this);
		bUpdate.addActionListener(this);
		
    }
    public void initialize()
	{
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con = DriverManager.getConnection("jdbc:odbc:PathDSN","","");
			st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = st.executeQuery("Select * From Order_Info");
		}	
		catch(Exception e)
		{
			System.out.println("Exception start : " + e);
		}
	}
	public void actionPerformed(ActionEvent ae)
	{
		int flag1=0,flag2=0,flag3=0;
		
        String str1= tOno.getText();
        String str2= tPrno.getText();
        String str3= tSuppno.getText();
     	
     	for(int i=0;i<str1.length();i++)
     	{
     	  
     	  if(!(Character.isDigit(str1.charAt(i))))
     	  {
     	     flag1=1;
     	     break;
     	  }
    	}
    	for(int i=0;i<str2.length();i++)
     	{
     	 
     	  if(!(Character.isDigit(str2.charAt(i))))
     	  {
     	     flag2=1;
     	     break;
     	  }
    	}
		for(int i=0;i<str3.length();i++)
     	{
     	  
     	  if(!(Character.isDigit(str3.charAt(i))))
     	  {
     	     flag3=1;
     	     break;
     	  }
    	}
		
		
     	if(ae.getActionCommand().equals("ADD"))
     	{
     		
     		if(tOno.getText().equals("") || tDate.getText().equals("") || tSuppno.getText().equals("")||tSuppname.getText().equals("")||tPrno.getText().equals("")||tPrname.getText().equals("")||tPrate.getText().equals("")||tQuantity.getText().equals(""))
			{
				String msg = "Please fill all Necessory Data";
			   	JOptionPane.showMessageDialog(null,msg,"Pathology Lab System",
				JOptionPane.INFORMATION_MESSAGE);
				
			}
			else if(!(tSuppname.getText().matches("([a-zA-Z.]+\\s*([a-zA-Z.]+\\s*([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]*)))")))
            {
            	String msg="Invalid Supplier Name";
                JOptionPane.showMessageDialog((Component)null,msg,"Pathology Lab System",
               	JOptionPane.WARNING_MESSAGE);
               	tSuppname.setText("");
               	tSuppname.requestFocus();
          	}
          	else if(flag1==1)// Invalid order No
          	{
     		      tOno.setText("");
     		      tOno.requestFocus();
     		      String msg = "Invalid Order Number";
     		      JOptionPane.showMessageDialog((Component)null,msg,"Pathology Lab System",
               	   JOptionPane.WARNING_MESSAGE);
          	}
          	else if(flag2==1)// Invalid Product No
          	{
     		      tPrno.setText("");
     		      tPrno.requestFocus();
     		      String msg = "Invalid Product Number";
     		      JOptionPane.showMessageDialog((Component)null,msg,"Pathology Lab System",
               	   JOptionPane.WARNING_MESSAGE);
          	}
          	else if(flag3==1)// Invalid Supplier No
          	{
     		      tSuppno.setText("");
     		      tSuppno.requestFocus();
     		      String msg = "Invalid Supplier Number";
     		      JOptionPane.showMessageDialog((Component)null,msg,"Pathology Lab System",
               	   JOptionPane.WARNING_MESSAGE);
          	}
          	else     
          	{
     		try
     		{
     		int amt= (Integer.parseInt(tPrate.getText()))*(Integer.parseInt(tQuantity.getText()));
     		System.out.println (amt);
     		tAmount.setText(String.valueOf(amt));
     	
     			initialize();
     			pst= con.prepareStatement("Insert into Order_Info  values(?,?,?,?,?,?,?,?,?)");
     			pst.setInt(1,Integer.parseInt(tOno.getText()));
     			pst.setString(2,tDate.getText());
     			pst.setString(3,tSuppno.getText());
     			pst.setString(4,tSuppname.getText());
     			pst.setString(5,tPrno.getText());
     			pst.setString(6,tPrname.getText());
     			pst.setString(7,tPrate.getText());
     			pst.setString(8,tQuantity.getText());
     			pst.setString(9,tAmount.getText());
     			pst.executeUpdate();
     			tOno.setText(""); tDate.setText(""); tSuppno.setText(""); tSuppname.setText(""); tPrno.setText(""); 
     			tPrname.setText(""); tPrate.setText(""); tQuantity.setText("");
     			JOptionPane.showMessageDialog((Component)null,"Records inserted successfully......","Inserted.",JOptionPane.INFORMATION_MESSAGE);
     			pst.close();
     			con.close();
     		}
     		catch(Exception e)
     		{
     			JOptionPane.showMessageDialog(this,"Invalid Record.Try Again!!!");
          	}//catch
            }//else
     	}//if bAdd
     	
     	if(ae.getActionCommand().equals("UPDATE"))
     	{
     		   try
     		   {
     		   initialize();
     		   String Oname= JOptionPane.showInputDialog(this,"Enter Order no to Update Record:"," ");
     		   int no= Integer.parseInt(Oname);
		       tOno.setText(""+no);
		       
		       pst=con.prepareStatement("select * from Order_Info where Ono =?");
		       pst.setInt(1,no);
		        
		       rs=pst.executeQuery();
		       if(rs.next()!=false)
		       {
		             tOno.setText(rs.getString(1)); 
      	             tDate.setText(rs.getString(2)); 
      	             tSuppno.setText(rs.getString(3));
      	             tSuppname.setText(rs.getString(4));   
      	             tPrno.setText(rs.getString(5));   
      	             tPrname.setText(rs.getString(6));   
      	             tPrate.setText(rs.getString(7));
      	             tQuantity.setText(rs.getString(8));
      	             tAmount.setText(rs.getString(9)); 
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
		  }//if
		  
		if(ae.getActionCommand().equals("SAVE"))
     	{
     	   if(tOno.getText().equals("") || tDate.getText().equals("") || tSuppno.getText().equals("")||tSuppname.getText().equals("")||tPrno.getText().equals("")||tPrname.getText().equals("")||tPrate.getText().equals("")||tQuantity.getText().equals(""))
			{
				String msg = "Please fill all Necessory Data";
			   	JOptionPane.showMessageDialog(null,msg,"Pathology Lab System",
				JOptionPane.INFORMATION_MESSAGE);
				
			}
			else if(!(tSuppname.getText().matches("([a-zA-Z.]+\\s*([a-zA-Z.]+\\s*([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]*)))")))
            {
            	String msg="Invalid Supplier Name";
                JOptionPane.showMessageDialog((Component)null,msg,"Pathology Lab System",
               	JOptionPane.WARNING_MESSAGE);
               	tSuppname.setText("");
               	tSuppname.requestFocus();
          	}
          	else if(flag1==1)// Invalid order No
          	{
     		      tOno.setText("");
     		      tOno.requestFocus();
     		      String msg = "Invalid Order Number";
     		      JOptionPane.showMessageDialog((Component)null,msg,"Pathology Lab System",
               	   JOptionPane.WARNING_MESSAGE);
          	}
          	else if(flag2==1)// Invalid Product No
          	{
     		      tPrno.setText("");
     		      tPrno.requestFocus();
     		      String msg = "Invalid Product Number";
     		      JOptionPane.showMessageDialog((Component)null,msg,"Pathology Lab System",
               	   JOptionPane.WARNING_MESSAGE);
          	}
          	else if(flag3==1)// Invalid Supplier No
          	{
     		      tSuppno.setText("");
     		      tSuppno.requestFocus();
     		      String msg = "Invalid Supplier Number";
     		      JOptionPane.showMessageDialog((Component)null,msg,"Pathology Lab System",
               	   JOptionPane.WARNING_MESSAGE);
          	}
          	else     
          	{
     		try
     		{
     			initialize();
     			int Ono =Integer.parseInt(tOno.getText());
     			pst= con.prepareStatement("Update Order_Info SET Ono=?,Odate=?, Sno=?,Sname=?,Prid=?,Prname=?,Prate=?,Quantity=?,Amount=?  where Ono=? ");
     			pst.setInt(1,Ono);
     			pst.setString(2,tDate.getText());
     			pst.setInt(3,Integer.parseInt(tSuppno.getText()));
     			pst.setString(4,tSuppname.getText());
     			pst.setInt(5,Integer.parseInt(tPrno.getText()));
     			pst.setString(6,tPrname.getText());
     			pst.setInt(7,Integer.parseInt(tPrate.getText()));
     			pst.setInt(8,Integer.parseInt(tQuantity.getText()));
     			int amt= (Integer.parseInt(tPrate.getText()))*(Integer.parseInt(tQuantity.getText()));
     			pst.setInt(9,amt);
     			pst.setInt(10,Ono);
     			pst.executeUpdate();
     			tOno.setText(""); tDate.setText(""); tSuppno.setText(""); tSuppname.setText(""); tPrno.setText(""); 
     			tPrname.setText(""); tPrate.setText(""); tQuantity.setText(""); tAmount.setText("");
     			JOptionPane.showMessageDialog((Component)null,"Records Updated successfully......","Updated.",JOptionPane.INFORMATION_MESSAGE);
     			pst.close();
     			con.close();
     			
     		}
     		catch(Exception e)
     		{
     		   System.out.println (""+e);
      		}
            }//else
     	}// if bUpadate
     	if(ae.getActionCommand().equals("DELETE"))
     	{
     		if(tOno.getText().equals(""))
			{
				String msg = "There is no Order to Delete";
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
     					String Ono = tOno.getText();
     					pst= con.prepareStatement("Delete from Order_Info where Ono=? ");
     					pst.setString(1,Ono);
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
     	
     	
		
	}// actionPerformed
  /*public static void main (String[] args)throws Exception 
    {
    	new Order_DetFrame();
    }*/
}
         
