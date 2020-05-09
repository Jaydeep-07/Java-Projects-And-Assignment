
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.table.*;

public class Ref_DocDetail extends JDialog implements ActionListener
{
    JLabel lHeading, lRefDno,lRefDname,lRefDqua;
    JTextField tRefDno,tRefDname,tRefDqua;
    JButton bAdd,bUpdate,bDelete;
    JScrollPane js1;
    
    Object[] data = new Object[3];  // Array of Objects of "Object" Class of size 2

	DefaultTableModel dtm = new DefaultTableModel();

	JTable jt = new JTable(dtm);

    Connection con;
    PreparedStatement pst;
	Statement st;
	ResultSet rs;
	Container cc= getContentPane();
	
    public Ref_DocDetail()
    {
        //super("REF. DOCTOR DETAILS",false,true,false,false);
		
    
		setLayout(null);
		Toolkit theKit = getToolkit(); 
    	Dimension wndSize = theKit.getScreenSize(); 
    		
		setLocation(100,20);
		setSize(700,650);
	
		
	    Font f = new Font("Arial Rounded MT Bold",Font.BOLD+Font.ITALIC,18);
    	Font f2 = new Font("Vladimir Script",Font.BOLD+Font.ITALIC,40);  
    	
        lHeading= new JLabel("REFERENCE DOCTOR DETAILS..");
    	lHeading.setFont(f2);
    	lRefDno= new JLabel("REFERENCE DOCTOR NO:");
    	lRefDno.setFont(f);
    	lRefDname= new JLabel("REFERNCE DOCTOR NAME:");
    	lRefDname.setFont(f);
    	lRefDqua= new JLabel("REF. DOCTOR QUALI. :");
    	lRefDqua.setFont(f);
    	
    	tRefDno= new JTextField(20);
    	tRefDname= new JTextField(20);
    	tRefDqua= new JTextField(20);
    	
    	bAdd= new JButton("ADD");
    	bUpdate = new JButton("UPDATE");
    	bDelete= new JButton("DELETE");
    	
        bAdd.setToolTipText("Add Information about Ref.Doctor");    bUpdate.setToolTipText("Update information of Ref. Doctor");	
    	bDelete.setToolTipText("Delete information of Ref.Doctor");
    	
    	lHeading.setBounds(30,10,1000,100);
    	lRefDno.setBounds(50,70,300,100);      	    tRefDno.setBounds(350,110,200,30);
    	lRefDname.setBounds(50,150,300,100);        tRefDname.setBounds(350,190,200,30);
        lRefDqua.setBounds(50,230,300,100);         tRefDqua.setBounds(350,270,200,30);
        
        bAdd.setBounds(50,525,100,40);                    
    	bUpdate.setBounds(200,525,100,40);
    	bDelete.setBounds(350,525,100,40);
    	
        add(lHeading);
    	add(lRefDno);      add(tRefDno);
    	add(lRefDname);    add(tRefDname);
    	add(lRefDqua);     add(tRefDqua);
    	add(bAdd);         add(bUpdate);
    	add(bDelete);     
    	    	
    	bAdd.addActionListener(this);
    	bUpdate.addActionListener(this);
    	bDelete.addActionListener(this);
    	
    	JLabel Lab_img=new JLabel(new ImageIcon("plainFrame.jpg"));
		Lab_img.setSize(700,700);
		add(Lab_img);
  
		setVisible(true);
        //this.cc.add(p);  
    	
    	String Rcol[] = {"RDno","RDname","RDqua"};  // Array of objects of String(Object type) class
		for(int i = 0 ; i < 3 ; i++)			
		{
			dtm.addColumn(Rcol[i]);
		}
		try
		{
			initialize();
			while(rs.next())
			{
				for(int j = 0 ; j < 3 ; j++)
				{
					String str = rs.getString(j+1);
					data[j] = str; 	
				}
				dtm.addRow(data);
			}
			rs.previous();
			int str= Integer.parseInt(rs.getString(1))+1;
		    tRefDno.setText(String.valueOf(str));
			
		    JScrollPane js=new JScrollPane(jt);
			js.setBounds(40,350,500,100);
			add(js);
			
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
			rs = st.executeQuery("Select * From RefDoc");
	    }	
		catch(Exception e)
		{
			System.out.println("Exception start : " + e);
		}
	 }
	 
	 
     public void actionPerformed(ActionEvent ae)
     {
     	
     	String bname= ae.getActionCommand();
     	int flag=0;
        String str= tRefDno.getText();
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
     		if(tRefDno.getText().equals("") || tRefDno.getText().equals("") || tRefDqua.getText().equals(""))
			{
				String msg = "Please fill all Necessory Data";
			   	JOptionPane.showMessageDialog(null,msg,"Pathology Lab System",
				JOptionPane.INFORMATION_MESSAGE);
					
			}
			else if(!(tRefDname.getText().matches("([a-zA-Z.]+\\s*([a-zA-Z.]+\\s*([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]*)))")))
            {
            	String msg="Invalid  Name";
                JOptionPane.showMessageDialog((Component)null,msg,"Pathology Lab System",
               	JOptionPane.WARNING_MESSAGE);
               	tRefDname.setText("");
               	tRefDname.requestFocus();
          	}	
          	else if(flag==1)// Invalid No
          	{
     		      tRefDno.setText("");
     		      tRefDno.requestFocus();
     		      String msg = "Invalid Number";
     		      JOptionPane.showMessageDialog((Component)null,msg,"Pathology Lab System",
               	   JOptionPane.WARNING_MESSAGE);
          	}
          	else
          	{
     		try
     		{
     			initialize();
     			pst= con.prepareStatement("Insert into RefDoc values(?,?,?)");
     			pst.setInt(1,Integer.parseInt(tRefDno.getText()));
     			pst.setString(2,tRefDname.getText());
     			pst.setString(3,tRefDqua.getText());
     			pst.executeUpdate();
     			tRefDno.setText(""); tRefDname.setText(""); tRefDqua.setText("");
     			JOptionPane.showMessageDialog((Component)null,"Records inserted successfully......","Inserted.",JOptionPane.INFORMATION_MESSAGE);
     			pst.close();
     			con.close();
     		}
     		catch(Exception e)
     		{
     			JOptionPane.showMessageDialog(this,"Invalid Record.Try Again!!!");
     		}
          	}
     	}
     	
     	if(bname.equals("UPDATE"))
     	{
     		try
     		{
     		   initialize();
     		   String tname= JOptionPane.showInputDialog(this,"Enter Refernce doctor no to Update Record:"," ");
     		   int no= Integer.parseInt(tname);
		       tRefDno.setText(""+no);
		       
		       pst=con.prepareStatement("select * from RefDoc where RDno=?");
		       pst.setInt(1,no);
		        
		       rs=pst.executeQuery();
		       if(rs.next()!=false)
		       {  
		             tRefDname.setText(rs.getString(2)); 
      	             tRefDqua.setText(rs.getString(3)); 
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
		       System.out.println(e);
		    }
     		
     		  
     	
		  }
     	if(bname.equals("SAVE"))
     	{
     		if(tRefDno.getText().equals("") || tRefDname.getText().equals("") || tRefDqua.getText().equals(""))
			{
				String msg = "Please fill all Necessory Data";
			   	JOptionPane.showMessageDialog(null,msg,"Pathology Lab System",
				JOptionPane.INFORMATION_MESSAGE);
					
			}
			else if(!(tRefDname.getText().matches("([a-zA-Z.]+\\s*([a-zA-Z.]+\\s*([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]*)))")))
            {
            	String msg="Invalid  Name";
                JOptionPane.showMessageDialog((Component)null,msg,"Pathology Lab System",
               	JOptionPane.WARNING_MESSAGE);
               	tRefDname.setText("");
               	tRefDname.requestFocus();
          	}	
          	else if(flag==1)// Invalid No
          	{
          		
     		      tRefDno.setText("");
     		      tRefDno.requestFocus();
     		      String msg = "Invalid Number";
     		      JOptionPane.showMessageDialog((Component)null,msg,"Pathology Lab System",
               	   JOptionPane.WARNING_MESSAGE);	
           	}
            else 
            {
      		 try
     		 {
     		 	initialize();
     			int RefDno = Integer.parseInt(tRefDno.getText());
     			pst= con.prepareStatement("Update RefDoc SET RDno=?, RDname=?, RDQua=? where RDno=? ");
     			pst.setInt(1,RefDno);
     			pst.setString(2,tRefDname.getText());
     			pst.setString(3,tRefDqua.getText());
     			pst.setInt(4,RefDno);
     			pst.executeUpdate();
     			
     			tRefDno.setText(""); tRefDname.setText(""); tRefDqua.setText("");
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
     		if(tRefDno.getText().equals(""))
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
     		       	 initialize();
     			     int RefDno = Integer.parseInt(tRefDno.getText());
     			     pst= con.prepareStatement("Delete from RefDoc where RDno=? ");
     			     pst.setInt(1,RefDno);
     			     pst.executeUpdate();
     			
     			     JOptionPane.showMessageDialog((Component)null,"Records Deleted successfully......","Deleted.",JOptionPane.INFORMATION_MESSAGE);
     			     pst.close();
     			     con.close();
     		       }
     			   catch(Exception e)
     	           {
     			     System.out.println ("Exception in Updating.."+e);
     		       }
     	
     	        }
			}//else
     	                                                                        
     }
     }
     /*public static void main (String[] args) 
     {
     	new Ref_DocDetail();
     }*/
    
}