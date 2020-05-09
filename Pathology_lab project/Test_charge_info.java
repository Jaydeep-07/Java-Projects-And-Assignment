
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.table.*;

public class Test_charge_info extends JDialog implements ActionListener
 {
    
    JLabel lHname,lTestname,lCharge;
    JButton bAdd,bUpdate,bDelete;
    
    JTextField tTestname ,tCharges;
    
    Object[] data = new Object[2];  // Array of Objects of "Object" Class of size 2

	DefaultTableModel dtm = new DefaultTableModel();

	JTable jt = new JTable(dtm);

	Connection con;
	Statement st;
	ResultSet rs;
	PreparedStatement pst;
    
    
    public Test_charge_info()
    {
    	setTitle("TEST CHARGE INFORMATION");
    	Container cc= getContentPane();
		cc.setLayout(null);
		setLocation(100,20);
		setSize(700,650);
		
	    Font f = new Font("Arial Rounded MT Bold",Font.BOLD+Font.ITALIC,18);
    	Font f2 = new Font("Vladimir Script",Font.BOLD+Font.ITALIC,40);  
    	
    	//String []Test={"Neutrophills","Lymphocyttes","Eosinpills","Monocytes","Basophils","Urine Analysis","Serology","HIV I & II Antibodies ","Serology-RHE","Serology-HBSA","Heamatology","Glucose Fasting","Glocose Post"};
        
    		
    	lHname= new JLabel(" TEST CHARGES..");
    	lHname.setFont(f2);
    	lTestname= new JLabel("TEST NAME:");
    	lTestname.setFont(f);
    	lCharge= new JLabel("CHARGES:");
		lCharge.setFont(f);
		    	
    	bAdd = new JButton("ADD");
    	bUpdate= new JButton("UPDATE");
    	bDelete=new JButton("DELETE");
    	
    	tTestname=new JTextField(20);
    	tCharges= new JTextField(20);
   
       	lHname.setBounds(150,10,1000,100);
    	
    	lTestname.setBounds(50,120,200,100);
    	tTestname.setBounds(200,150,150,30);
    	lCharge.setBounds(50,200,200,100);
    	tCharges.setBounds(200,230,150,30);
    	
    	bAdd.setBounds(50,525,100,40);                    
    	bUpdate.setBounds(200,525,100,40);
    	bDelete.setBounds(350,525,100,40);
    	
    	cc.add(lHname);
    	cc.add(lTestname);
    	cc.add(lCharge);
        cc.add(tCharges);
    	cc.add(bAdd);
    	cc.add(bUpdate);
    	cc.add(bDelete);
    	cc.add(tTestname);
    	
    	bAdd.addActionListener(this);
    	bUpdate.addActionListener(this);
    	bDelete.addActionListener(this);
    	
    	JLabel Lab_img=new JLabel(new ImageIcon("plainFrame.jpg"));
		Lab_img.setSize(700,700);
		cc.add(Lab_img);
  
		setVisible(true);
        //this.cc.add(p);  
        	
        String Pcol[] = {"Tname","Charges"};  // Array of objects of String(Object type) class
		
		for(int i = 0 ; i < 2 ; i++)			
		{
			dtm.addColumn(Pcol[i]);
		}
		try
		{
			initialize();
			while(rs.next())
			{
				for(int j = 0 ; j < 2 ; j++)
				{
					String str = rs.getString(j+1);
					data[j] = str; 	
				}
				dtm.addRow(data);
			}
			
			JScrollPane js=new JScrollPane(jt);
			js.setBounds(40,300,500,100);
			cc.add(js);
			
			setVisible(true);
            //this.cc.add(p);  
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
			rs = st.executeQuery("Select * From Test_Charges");
		}	
		catch(Exception e)
		{
			System.out.println("Exception start : " + e);
		}
	}
	
	public void actionPerformed(ActionEvent ae)
     {
     	initialize();
     	String bname=ae.getActionCommand();
     	int flag=0;
          	String str= tCharges.getText();
     		for(int i=0;i<str.length();i++)
     	    {
     		    if(!(Character.isDigit(str.charAt(i))))
     		   {
     		      flag=1;
     		      break;
     		    }
     		
     		}
     	if(bname.equals("ADD"))
     	{
     		
     		if(tTestname.getText().equals("") || tCharges.getText().equals(""))
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
          	else if(flag==1)
          	{
          		
     		      tCharges.setText("");
     		      tCharges.requestFocus();
     		      String msg = "Invalid Charges";
     		      JOptionPane.showMessageDialog((Component)null,msg,"Pathology Lab System",
               	   JOptionPane.WARNING_MESSAGE);	
     		    
          	}	
          	else
          	{
     		try
     		{
     			pst= con.prepareStatement("Insert into Test_Charges values(?,?)");
     			pst.setString(1,tTestname.getText());
     			pst.setString(2,tCharges.getText());
     			pst.executeUpdate();
     			tTestname.setText(""); tCharges.setText("");
     			JOptionPane.showMessageDialog((Component)null,"Records inserted successfully......","Inserted.",JOptionPane.INFORMATION_MESSAGE);
     			pst.close();
     			con.close();
     		}
     		catch(Exception e)
     		{
     			System.out.println ("Exception inInserting.."+e);
     		}
     		
          	}//else

     	}// if bAdd
     	
     	if(bname.equals("UPDATE"))
     	{
     		   try
     		   {
     		   
     		   String tname= JOptionPane.showInputDialog(this,"Enter Test name to Update Record:"," ");
		       tTestname.setText(""+tname);
		      
		       pst=con.prepareStatement("select * from Test_Charges where Tname=?");
		       pst.setString(1,tname);
		        
		       rs=pst.executeQuery();
		       if(rs.next()!=false)
		       {
		       	 bUpdate.setLabel("Save");
		         tTestname.setText(rs.getString(1)); 
      	         tCharges.setText(rs.getString(2)); 
		         JOptionPane.showMessageDialog(this," Enter Record To Update:","Update",JOptionPane.WARNING_MESSAGE);
		       }
		       else
     		   {
     		  	  JOptionPane.showMessageDialog((Component)null,"No such record found....","Sorry..",JOptionPane.INFORMATION_MESSAGE);
     		   }
		         
		       }
		       catch(Exception e)
		       {
                       System.out.println(e);
		       }
		  }
		 if(bname.equals("Save"))
		  {
		  	
     		if(tTestname.getText().equals("") || tCharges.getText().equals(""))
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
     		else if(flag==1)
     		{
     		      tCharges.setText("");
     		       tCharges.requestFocus();
     		      String msg = "Invalid Charges";
     		      JOptionPane.showMessageDialog((Component)null,msg,"Pathology Lab System",
               	   JOptionPane.WARNING_MESSAGE);	
     		 }
          	
          	else
          	{
          	
     		try
     		{
     			String tname= tTestname.getText();
     			pst= con.prepareStatement("Update Test_Charges SET  Tname=?, Charges=? where Tname=? ");
     			pst.setString(1,tname);
     			pst.setString(2,tCharges.getText());
     			pst.setString(3,tname);
       			pst.executeUpdate();
       			
       			tTestname.setText("");   tCharges.setText("");
       			bUpdate.setLabel("UPDATE");
     			
     			JOptionPane.showMessageDialog((Component)null,"Records Updated successfully......","Updated.",JOptionPane.INFORMATION_MESSAGE);
     			pst.close();
     			con.close();
     			
     		}
     		catch(Exception e)
     		{
     			System.out.println ("Exception in Updating.."+e);
     		}
          	}//else
     	}
     	if(bname.equals("DELETE"))
     	{
     		if(tTestname.getText().equals(""))
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
     				{
     					String tname = tTestname.getText();
     					pst= con.prepareStatement("Delete from Test_Charges where Tname=? ");
     					pst.setString(1,tname);
     					pst.executeUpdate();
     			
     					JOptionPane.showMessageDialog((Component)null,"Records Deleted successfully......","Deleted.",JOptionPane.INFORMATION_MESSAGE);
     					pst.close();
     					con.close();
     				}
     				catch(Exception e)
     				{
     					System.out.println ("Exception in Updating.."+e);
         		    }
				}//if
			}//else    	
     	}
     	                                                                        
     }
    /*public static void main (String[] args) throws Exception 
    {
   	  new Test_charge_info();
    }*/
}