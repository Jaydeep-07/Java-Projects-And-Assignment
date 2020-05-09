
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.sql.*;
import javax.swing.table.*;

public class PEntry_Frame extends JDialog implements ActionListener
{
    JLabel lHeading,lPno,lDate,lRefBy,lPname,lPadd,lPphno,lPage,lPgender,lTest,lSelectdTest,lCharges;
    JButton bAdd,bDelete,bUpdate,bSearch,bSelect,bRemove,bTotal;
    JTextField tPno,tDate,tRegCode,tRefBy,tPname,tPadd,tPphno,tPage,tGender,tCharges;
    
    List l1,l2;
    
    int cost=0; 
   
	Connection con;
	Statement st;
	PreparedStatement pst,pst1;
	ResultSet rs,rs1;
	
	ResultSetMetaData rsmd;
	Container cc= getContentPane();
	JScrollPane js;
	
    public PEntry_Frame() 
    {
    	
       
		setTitle("PATIENT ENTRY");
	    cc.setLayout(null);
		setLocation(100,20);
		setSize(700,650);
		

		
	    Font f = new Font("Arial Rounded MT Bold",Font.BOLD+Font.ITALIC,18);
    	Font f2 = new Font("Vladimir Script",Font.BOLD+Font.ITALIC,40);
    	
    	lHeading= new JLabel("PATIENT ENTRY...");
    	lHeading.setFont(f2);
    	lHeading.setBounds(150,10,1000,100);
    	
    	lPno= new JLabel("PATIENT NO:");          tPno= new JTextField(20);
    	lPno.setFont(f);
    	lDate= new JLabel("DATE:");				  tDate= new JTextField(20);
    	lDate.setFont(f);
    	lRefBy= new JLabel("REF BY:");			  tRefBy= new JTextField(20);
    	lRefBy.setFont(f);
    	lPname= new JLabel("PATIENT NAME:");	  tPname= new JTextField(20);
    	lPname.setFont(f);
    	lPadd= new JLabel("PATIENT ADDRESS:");    tPadd= new JTextField(20);
    	lPadd.setFont(f);
    	lPphno= new JLabel("PH NO:");             tPphno= new JTextField(20);
    	lPphno.setFont(f);
    	lPage= new JLabel("AGE:");				  tPage= new JTextField(20);	
    	lPage.setFont(f);
    	lPgender= new JLabel("GENDER:");          tGender= new JTextField(20);
    	lPgender.setFont(f); 
    	lTest= new JLabel("Test");           tCharges= new JTextField(10);
    	lTest.setFont(f);
    	lSelectdTest= new JLabel("Selected Test");
    	lSelectdTest.setFont(f);
    	lCharges= new JLabel("Charges:");
    	lCharges.setFont(f);
    		
    		
    	
    	bAdd= new JButton("ADD");				bUpdate= new JButton("UPDATE");
    	bSearch= new JButton("SEARCH");			bDelete= new JButton("DELETE");	
    	bSelect= new JButton(">>");               bRemove= new JButton("<<");
    	bTotal= new JButton("=");
    	
    	
    	lPno.setBounds(20,70,200,100);             tPno.setBounds(230,100,150,30);
    	lDate.setBounds(20,120,200,100);		   tDate.setBounds(230,160,150,30);
    	lPname.setBounds(20,170,200,100);		   tPname.setBounds(230,210,150,30);
    	lPadd.setBounds(20,220,200,100);		   tPadd.setBounds(230,260,150,30);
    	lPphno.setBounds(20,270,200,100);		   tPphno.setBounds(230,310,150,30);
    	
    	lPgender.setBounds(405,70,200,100);       tGender.setBounds(520,100,150,30); 
    	lRefBy.setBounds(405,140,200,100);		   tRefBy.setBounds(520,180,150,30);
    	lPage.setBounds(405,210,200,100 );		   tPage.setBounds(520,250,150,30);					
    	lTest.setBounds(40,350,100,50);            tCharges.setBounds(560,450,100,30);
    	
    	lSelectdTest.setBounds(350,350,200,50);
    	lCharges.setBounds(560,400,100,50);
    	
    	bAdd.setBounds(50,525,100,40);                                 
    	bDelete.setBounds(200,525,100,40);
    	bSearch.setBounds(350,525,100,40);
    	bUpdate.setBounds(500,525,100,40);
    	
    	bSelect.setBounds(270,400,50,30);
    	bRemove.setBounds(270,430,50,30);
    	bTotal.setBounds(270,460,50,30);
    	
    	l1= new List();
    	l2= new List();
    	
    	cc.add(lHeading);
    	cc.add(lPno);			cc.add(tPno);			
    	cc.add(lDate);			cc.add(tDate);		
    	cc.add(lRefBy);		cc.add(tRefBy);       
    	cc.add(lPname);		cc.add(tPname);
    	cc.add(lPadd);			cc.add(tPadd);
    	cc.add(lPphno);		cc.add(tPphno);
    	cc.add(lPage);			cc.add(tPage);
    	cc.add(lPgender);		cc.add(tGender);       
      	cc.add(lTest);         cc.add(tCharges);
      	cc.add(lSelectdTest);
      	cc.add(lCharges);
      	
      	cc.add(bAdd);			
     	cc.add(bDelete);	
    	cc.add(bUpdate);
    	cc.add(bSearch);
    	cc.add(bSelect);
    	cc.add(bRemove);
    	cc.add(bTotal);
    	
       initialize();
    	try
    	{
    	
    	         rs = st.executeQuery("Select * From PatientEntry_Mast");
    	         rs.last();
    	         int pno = Integer.parseInt(rs.getString(1))+1;
		         tPno.setText(String.valueOf(pno));
		         
    	}
    	catch(Exception e)
    	{
    		System.out.println (""+e);
    	}
    	bAdd.addActionListener(this);
    	bDelete.addActionListener(this);
    	bUpdate.addActionListener(this);
    	bSearch.addActionListener(this);
    	
    	bSelect.addActionListener(this);
    	bRemove.addActionListener(this);
    	bTotal.addActionListener(this);
    	
    	JLabel Lab_img=new JLabel(new ImageIcon("plainFrame.jpg"));
		Lab_img.setSize(700,700);
		cc.add(Lab_img);
  
		setVisible(true);
        try
        {
         	
          
          rs= st.executeQuery("Select * From Test_Charges");
         
          String TestName= new String("");
          while(rs.next())
          { 
          	TestName= rs.getString(1);
         	l1.add(TestName);
          }
        
          l1.setBounds(40,400,200,100);
          cc.add(l1);
          l2.setBounds(350,400,200,100);
          cc.add(l2);
          
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
			
		}	
		catch(Exception e)
		{
			System.out.println("Exception start : " + e);
		}
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		int flag1=0,flag2=2;
        String strpno= tPno.getText();
        String strpage= tPage.getText();

     	for(int i=0;i<strpno.length();i++)
     	{
     	  if(!(Character.isDigit(strpno.charAt(i))))
     	  {
     	     flag1=1;
     	     break;
     	  }
    	}
    	
    	for(int i=0;i<strpage.length();i++)
     	{
     	  char c= strpage.charAt(i);
     	  if(!(Character.isDigit(strpage.charAt(i))))
     	  {
     	     flag2=1;
     	     break;
     	  }
    	}
     	
		if(ae.getActionCommand().equals(">>"))
		{
			l2.add(l1.getSelectedItem());
		}
     	if(ae.getActionCommand().equals("<<"))
		{
			l2.remove(l2.getSelectedIndex());
		}
     	
     	if(ae.getActionCommand().equals("="))
		{
    		try
    		{
    		   initialize();
    		   rs = st.executeQuery("Select * From Test_Charges");
    		   for(int i = 0 ; i < l2.getItemCount() ; i++)
    		   {
    		   	    rs.beforeFirst();
    		        String item = l2.getItem(i); 
    	            while(rs.next()) 
      		        {
      		        	
    			       String str=rs.getString(1);
    			       
    			       
    			       if(item.equals(str))
    				   {
    				   	int chrg= Integer.parseInt(rs.getString(2));
    				   	cost = cost + chrg;
    			        break;
    				   }
    			    }
    			 
    		     }
    		     tCharges.setText(""+cost);
    		
    		  rs.close();
    	      st.close();
    	      con.close();
    		}
    		catch(Exception e)
		    {
			   System.out.println("Exception : " + e);
		    }
         
    		
		}
     	if(ae.getActionCommand().equals("ADD"))
     	{
     		if(tPno.getText().equals("") || tPname.getText().equals("") || tPadd.getText().equals("")||tPphno.getText().equals("")||tPage.getText().equals("")||tGender.getText().equals("")||tDate.getText().equals("")||tRefBy.getText().equals(""))
			{
				String msg = "Please fill all Necessory Data";
			   	JOptionPane.showMessageDialog(null,msg,"Pathology Lab System",
				JOptionPane.INFORMATION_MESSAGE);
				
			}
			else if(!(tPname.getText().matches("([a-zA-Z.]+\\s*([a-zA-Z.]+\\s*([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]*)))")))
            {
            	String msg="Invalid Patient Name";
                JOptionPane.showMessageDialog((Component)null,msg,"Pathology Lab System",
               	JOptionPane.WARNING_MESSAGE);
               	tPname.setText("");
               	tPname.requestFocus();
          	}
          	else if(!(tRefBy.getText().matches("([a-zA-Z.]+\\s*([a-zA-Z.]+\\s*([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]*)))")))
            {
            	String msg="Invalid Reference doctor Name";
                JOptionPane.showMessageDialog((Component)null,msg,"Pathology Lab System",
               	JOptionPane.WARNING_MESSAGE);
               	tRefBy.setText("");
               	tRefBy.requestFocus();
          	}
          	else if(flag1==1)// Invalid order No
          	{
     		      tPno.setText("");
     		      tPno.requestFocus();
     		      String msg = "Invalid Patient Number";
     		      JOptionPane.showMessageDialog((Component)null,msg,"Pathology Lab System",
               	   JOptionPane.WARNING_MESSAGE);
          	}
          	else if(flag2==1)// Invalid Product No
          	{
     		      tPage.setText("");
     		      tPage.requestFocus();
     		      String msg = "Invalid age";
     		      JOptionPane.showMessageDialog((Component)null,msg,"Pathology Lab System",
               	   JOptionPane.WARNING_MESSAGE);
          	}
          	else if(!(tPphno.getText().matches("\\d{10}")))
            {
               	 String msg="Invalid Contact Number";
               	 JOptionPane.showMessageDialog((Component)null,msg,"MSEB Billing System",
               	 JOptionPane.WARNING_MESSAGE);
               	 tPphno.setText("");
               	 tPphno.requestFocus();
            }
            else
            {
            
     		try
     		{
     			initialize();
     			pst= con.prepareStatement("Insert into PatientEntry_Mast values(?,?,?,?,?,?,?,?)");
     			pst.setInt(1,Integer.parseInt(tPno.getText()));
     			pst.setString(2,tDate.getText());
     			pst.setString(3,tPname.getText());
     			pst.setString(4,tRefBy.getText());
     			pst.setString(5,tPadd.getText());
     			pst.setString(6,tPphno.getText());
     			pst.setString(7,tPage.getText());
     			pst.setString(8,tGender.getText());
     			tPno.setText(""); tDate.setText(""); tPname.setText(""); tRefBy.setText(""); tPadd.setText(""); 
     			tPphno.setText(""); tPage.setText(""); tGender.setText("");          
     			pst.executeUpdate();
     			JOptionPane.showMessageDialog((Component)null,"Records inserted successfully......","Inserted.",JOptionPane.INFORMATION_MESSAGE);
     			
     			pst.close();
     			con.close();
     		}
     		catch(Exception e)
     		{
     			System.out.println ("Exception inInserting.."+e);
     		}
            }//else
     		
     		
     		
     	}
     	if(ae.getActionCommand().equals("UPDATE"))
     	{
     		   try
     		   {
     		   initialize();
     		   String tname= JOptionPane.showInputDialog(this,"Enter Patient no to Update Record:"," ");
     		   int no= Integer.parseInt(tname);
		       tPno.setText(""+no);
		       
		       pst=con.prepareStatement("select * from PatientEntry_Mast  where Pno =?");
		       pst.setInt(1,no);
		        
		       rs1=pst.executeQuery();
		       if(rs1.next()!=false)
		       {
		            	tPno.setText(rs1.getString(1));
     		           	tDate.setText(rs1.getString(2));
     			        tPname.setText(rs1.getString(3));
     			        tRefBy.setText(rs1.getString(4));
     			        tPadd.setText(rs1.getString(5));
     			        tPphno.setText(rs1.getString(6));
     			        tPage.setText(rs1.getString(7));
     			        tGender.setText(rs1.getString(8)); 
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
		       {
		       	JOptionPane.showMessageDialog(this,"Invalid Record.Try Again!!!");
		       	//System.out.println (""+e);
		       }
		  }
     	
     	if(ae.getActionCommand().equals("SAVE"))
     	{
            if(tPno.getText().equals("") || tPname.getText().equals("") || tPadd.getText().equals("")||tPphno.getText().equals("")||tPage.getText().equals("")||tGender.getText().equals("")||tDate.getText().equals("")||tRefBy.getText().equals(""))
			{
				String msg = "Please fill all Necessory Data";
			   	JOptionPane.showMessageDialog(null,msg,"Pathology Lab System",
				JOptionPane.INFORMATION_MESSAGE);
				
			}
			else if(!(tPname.getText().matches("([a-zA-Z.]+\\s*([a-zA-Z.]+\\s*([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]*)))")))
            {
            	String msg="Invalid Patient Name";
                JOptionPane.showMessageDialog((Component)null,msg,"Pathology Lab System",
               	JOptionPane.WARNING_MESSAGE);
               	tPname.setText("");
               	tPname.requestFocus();
          	}
          	else if(!(tRefBy.getText().matches("([a-zA-Z.]+\\s*([a-zA-Z.]+\\s*([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]*)))")))
            {
            	String msg="Invalid Reference doctor Name";
                JOptionPane.showMessageDialog((Component)null,msg,"Pathology Lab System",
               	JOptionPane.WARNING_MESSAGE);
               	tRefBy.setText("");
               	tRefBy.requestFocus();
          	}
          	else if(flag1==1)// Invalid Patient No
          	{
     		      tPno.setText("");
     		      tPno.requestFocus();
     		      String msg = "Invalid Patient Number";
     		      JOptionPane.showMessageDialog((Component)null,msg,"Pathology Lab System",
               	   JOptionPane.WARNING_MESSAGE);
          	}
          	else if(flag2==1)// Invalid age
          	{
     		      tPage.setText("");
     		      tPage.requestFocus();
     		      String msg = "Invalid age";
     		      JOptionPane.showMessageDialog((Component)null,msg,"Pathology Lab System",
               	   JOptionPane.WARNING_MESSAGE);
          	}
          	else if(!(tPphno.getText().matches("\\d{10}")))
            {
               	 String msg="Invalid Contact Number";
               	 JOptionPane.showMessageDialog((Component)null,msg,"MSEB Billing System",
               	 JOptionPane.WARNING_MESSAGE);
               	 tPphno.setText("");
               	 tPphno.requestFocus();
            }
            else
            {
                 	
     		try
     		{
     			initialize();
     			int Pno = Integer.parseInt(tPno.getText());
     			pst1= con.prepareStatement("Update PatientEntry_Mast SET Date=?, Pname=?, RDname=?,Padd=?, Phno=?,Age=?, Gender=? where Pno="+Pno);
     			//pst.setInt(1,Pno);
     			pst1.setString(1,tDate.getText());
     			pst1.setString(2,tPname.getText());
     			pst1.setString(3,tRefBy.getText());
     			pst1.setString(4,tPadd.getText());
     			pst1.setString(5,tPphno.getText());
     			pst1.setString(6,tPage.getText());
     			pst1.setString(7,tGender.getText());
     			//pst1.setInt(8,Pno);
     			pst1.executeUpdate();
     			tPno.setText(""); tDate.setText(""); tPname.setText(""); tRefBy.setText(""); tPadd.setText(""); 
     			tPphno.setText(""); tPage.setText(""); tGender.setText("");       
     			JOptionPane.showMessageDialog((Component)null,"Records Updated successfully......","Updated.",JOptionPane.INFORMATION_MESSAGE);
     			pst1.close();
     			con.close();
     			
     		}
     		catch(Exception e)
     		{
     			System.out.println ("Exception in Updating.."+e);
     		}
            }//else
     	}
     	/*if(ae.getActionCommand().equals("DELETE"))
     	{
     		if(tPno.getText().equals(""))
			{
				String msg = "There is no Patient to Delete";
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
     			       int Pno = Integer.parseInt(tPno.getText());
     			       pst= con.prepareStatement("Delete from PatientEntry_Mast WHERE Pno=? ");
     			       pst.setInt(1,Pno);
     			       pst.executeUpdate();
     			
     			       JOptionPane.showMessageDialog((Component)null,"Records Deleted successfully......","Deleted.",JOptionPane.INFORMATION_MESSAGE);
     			       pst.close();
     			       con.close();
     		         }
     		         catch(Exception e)
     		         {																																		
     			         System.out.println ("Exception in Deleting.."+e);
     		         }
				}//if
			}//else
     	}*/
     	if(ae.getActionCommand().equals("DELETE"))
     	{
				   initialize();
 
     		if(tPno.getText().equals(""))
			{
				String msg = "There is no Patient to Delete";
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
     		//System.out.println("hiiii");

     		         try
     		         {
     			       int Pno = Integer.parseInt(tPno.getText());
				System.out.println(Pno);
     			       pst= con.prepareStatement("Delete from PatientEntry_Mast WHERE Pno=?");
     			       pst.setInt(1,Pno);
     			       pst.executeUpdate();
     			
     			       JOptionPane.showMessageDialog((Component)null,"Records Deleted successfully......","Deleted.",JOptionPane.INFORMATION_MESSAGE);
     			       pst.close();
     			       con.close();
     		         }
     		         catch(SQLException e)
     		         {																																		
     			         System.out.println ("Exception in Deleting.."+e);
     		         }
				}//if
			}//else
     	}

     	if(ae.getActionCommand().equals("SEARCH"))
     	{
     		try
     		{
     			initialize();
    			String Pname= JOptionPane.showInputDialog(this,"Enter Patient name to search:"," ");
    			pst=con.prepareStatement("select * from PatientEntry_Mast  where Pname =?");
		        pst.setString(1,Pname);
		        rs=pst.executeQuery();
     			if(rs.next())
     			{
     				JOptionPane.showMessageDialog((Component)null,"Record Found.....","Found",JOptionPane.INFORMATION_MESSAGE);
     				tPno.setText(rs.getString(1));
     		        tDate.setText(rs.getString(2));
     			    tPname.setText(rs.getString(3));
     			    tRefBy.setText(rs.getString(4));
     			    tPadd.setText(rs.getString(5));
     			    tPphno.setText(rs.getString(6));
     			    tPage.setText(rs.getString(7));
     			    tGender.setText(rs.getString(8));  
     			}
     			else
     			{
     				JOptionPane.showMessageDialog((Component)null,"Record Not Found.....","Not Found",JOptionPane.INFORMATION_MESSAGE);
     				tPno.setText(""); tDate.setText(""); tPname.setText(""); tRefBy.setText(""); tPadd.setText(""); 
     			    tPphno.setText(""); tPage.setText(""); tGender.setText("");       
     			}
     			
     			rs.close();
     			st.close();
     			con.close();
     		}
     		catch(Exception e)
     		{
     			System.out.println ("Exception in Searching.."+e);
     		}
     	}
	}
    
   /*public static void main (String[] args)throws Exception 
    {
    	new PEntry_Frame();
    }*/
    
    
}