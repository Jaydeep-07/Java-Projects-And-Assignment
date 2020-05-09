//
import java.awt.*;
import java.awt.print.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Heamatology_form extends JDialog  implements ActionListener  
{
	JLabel ltname,lpname,l1,l2;
	JTextField t1; 
	
	JButton b1,b2;
	List list1,list2;
	
	JLabel RN,NAME,REFBY,AGE,SEX,DATE;
	JLabel RN1,NAME1,REFBY1,AGE1,SEX1,DATE1,Ldrname;
	String regno,date,refby,age,sex;
	
    Font f1 = new Font("Arial Rounded MT Bold",Font.BOLD+Font.ITALIC,20);
	Font f2 = new Font("Arial Rounded MT Bold",Font.BOLD+Font.ITALIC,14);
    

	Container cc= getContentPane();
	Connection con;
	Statement st;
    ResultSet rs;
    PreparedStatement pst;

    public Heamatology_form() 
    {
    	setTitle("TEST HEAMATOLOGY");
		
	    cc.setLayout(null);
		setLocation(100,20);
		setSize(700,650);
		cc.setBackground(Color.white);
	
    	setVisible(true);
    	
    	
    	ltname= new JLabel("HEAMATOLOGY");
    	lpname= new JLabel("PATIENT NAME:");
    	l1= new JLabel("BLOOD GROUP:");    
        
        ltname.setFont(f1);
    	lpname.setFont(f2);
    	ltname.setBounds(150,10,1000,100);
    	lpname.setBounds(50,350,1000,80);
    	
    	l1.setBounds(20,120,200,100);     
        l1.setFont(f2);
    	
    	  
    	b1= new JButton("ADD"); // button for adding the records in the database
    	b1.setBounds(200,520,100,40);
    	b2= new JButton("EXIT");// button for exit
    	b2.setBounds(350,520,100,40);
    
    	list1= new List();// list for Patients Name
    	list2=new List();// List for Blood group
    	
    	String items[] = {"A+","A-","B+","B-","AB+","AB-","O+","O-"}; 	
    	
    	for(int i = 0 ; i < items.length ; i++)
    	{
    		list2.add(items[i]); // To add blood groups in list
    	}	
    	
    	list2.setBounds(350,150,160,50);
    	
    	try
        {
         	
          initialize();// Connecting with database
          rs = st.executeQuery("Select * From PatientEntry_Mast");// exicuting query to get result of Patients name in resultset 
         
          String Tname= new String("");
          while(rs.next())
          { 
          	Tname= rs.getString(3);
         	list1.add(Tname);  // adding Patients name in the list
          }
          
          list1.setBounds(40,410,200,100);
          cc.add(list1);      // Adding components in container
          cc.add(b1);   cc.add(b2);
          cc.add(ltname); cc.add(lpname);
          cc.add(l1);   cc.add(list2);
                           
          rs.close();
          st.close();
          con.close();
        }
        catch(Exception e)
		{
			JOptionPane.showMessageDialog(this,"Invalid Record.Try Again!!!");
		}
		b1.addActionListener(this);
		b2.addActionListener(this);
 
        
    }
    public void actionPerformed(ActionEvent ae)
    {
    	String Pname= list1.getSelectedItem();
    	String bldgrp=list2.getSelectedItem();	
    		
    	if(ae.getActionCommand().equals("EXIT"))
    	{
    		dispose();
    	}
     	if(ae.getActionCommand().equals("ADD"))
     	{
            	try
     		    {
     		    initialize();
     			pst= con.prepareStatement("Insert into Heamatology values(?,?)");
     			pst.setString(1,Pname);
     			pst.setString(2,bldgrp);
     	    	pst.executeUpdate();
     		
     		    rs = st.executeQuery("Select * From PatientEntry_Mast ");
                while(rs.next())
                {
                    if(rs.getString(3).equals(Pname))
                    {
                             regno= rs.getString(1);
     		             	 date= rs.getString(2);
     			             refby= rs.getString(4);
     		 	             age= rs.getString(7);
     			             sex= rs.getString(8);
     		                 break; 
                    }
                }
     		    JOptionPane.showMessageDialog((Component)null,"Records inserted successfully......","Inserted.",JOptionPane.INFORMATION_MESSAGE);
     			rs.close();
     			pst.close();
     			con.close();
     		  }
     		  catch(Exception e)
     		  {
     			JOptionPane.showMessageDialog(this,"Invalid Record.Try Again!!!");
     		  }
     		  	
     		  this.setBounds(0,0,700,1000); this.setBackground(Color.WHITE);
     		  this.setBackground(Color.white);
     		  ltname.setText("Jadhav Pathology Lab");
     		  ltname.setBounds(100,-110,300,300);
     		
     		
 
     	      RN= new JLabel("REG NO.:");    RN.setBounds(50,50,150,60);      RN.setFont(f2);
     	      NAME=new JLabel("DR.NAME  :");    NAME.setBounds(50,70,150,60);    NAME.setFont(f2);
     	      REFBY= new JLabel("REF BY: "); REFBY.setBounds(50,90,150,60);   REFBY.setFont(f2);
     	      AGE= new JLabel("AGE :");      AGE.setBounds(350,50,150,60);    AGE.setFont(f2);
     	      SEX= new JLabel("SEX :");      SEX.setBounds(350,70,150,60);    SEX.setFont(f2);
     	      DATE= new JLabel("DATE:");     DATE.setBounds(350,90,150,60);   DATE.setFont(f2);
     	    
     	      RN1= new JLabel(regno);        RN1.setBounds(150,50,150,60);    RN1.setFont(f2);
     	      NAME1=new JLabel("DR MANOHAR JADHAV"); NAME1.setBounds(150,70,200,60);  NAME1.setFont(f2);
     	      REFBY1= new JLabel(refby);     REFBY1.setBounds(150,90,150,60); REFBY1.setFont(f2);
     	      AGE1= new JLabel(age);         AGE1.setBounds(450,50,150,60);    AGE1.setFont(f2);
     	      SEX1= new JLabel(sex);         SEX1.setBounds(450,70,150,60);   SEX1.setFont(f2);
     	      DATE1= new JLabel(date);       DATE1.setBounds(450,90,150,60);  DATE1.setFont(f2);
     	    
     	      lpname.setText("HEAMATOLOGY");
     	      lpname.setBounds(100,100,150,100);
     	      l2=new JLabel(bldgrp);
     	      l2.setBounds(250,235,150,30);
     	      l2.setFont(f2);	     
     	      l1.setBounds(50,200,200,100); 
     	      
     	      b1.setBounds(200,600,100,40);   b1.setLabel("PRINT");
     		  b2.setVisible(false);
     		  list2.setVisible(false);
     		  list1.setVisible(false);
     		
     		  cc.add(RN); cc.add(RN1);
     		  cc.add(NAME); cc.add(NAME1);
     		  cc.add(REFBY);cc.add(REFBY1);
     		  cc.add(AGE); cc.add(AGE1);
     		  cc.add(SEX);cc.add(SEX1);
     		  cc.add(DATE);cc.add(DATE1);
     		  cc.add(l2); 
              		
     	
    }//if Add
    if(ae.getActionCommand().equals("PRINT"))
    {
        PrintUtilities.printComponent(this);
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
	/*public static void main (String[] args) 
	{
		new Heamatology_form();
    }*/
    
    
}