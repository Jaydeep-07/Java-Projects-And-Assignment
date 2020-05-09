
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.sql.*;
import javax.swing.table.*;

public class Stock_detFrame extends JDialog implements ActionListener
{
	JLabel lPrno,lPrname,lBatchno,lMfg,lExpdate,lDate,lHeading,lPrate,lStatus;
	JTextField tPrno,tPrname,tBatchno,tMfg,tExpdate,tdate,tPrate;
	JButton bAdd,bUpdate,bDelete,bExit;
      
	Connection con;
    PreparedStatement pst,pst1;
  
	Statement st;
	ResultSet rs;
    
    Object[] data = new Object[7];  // Array of Objects of "Object" Class of size 6
    DefaultTableModel dtm = new DefaultTableModel();
    JTable jt = new JTable(dtm);
	
	public Stock_detFrame() 
    {
  		setTitle("STOCK DETAILS");
  		Container cc= getContentPane();
		cc.setLayout(null);
		setLocation(100,20);
		setSize(700,650);
		
	    Font f = new Font("Arial Rounded MT Bold",Font.BOLD+Font.ITALIC,18);
    	Font f2 = new Font("Vladimir Script",Font.BOLD+Font.ITALIC,40);  
    	
    	lHeading= new JLabel("STOCK DETAILS...");
    	lHeading.setFont(f2);	
		lPrno = new JLabel("PRODUCT NO:");
		lPrno.setFont(f);
		lPrname= new JLabel("PRODUCT NAME:");
		lPrname.setFont(f);
		lBatchno= new JLabel("BATCH NO:");
		lBatchno.setFont(f);
		lMfg= new JLabel("MANUFACTURING:");
		lMfg.setFont(f);
		lExpdate= new JLabel("EXP.DATE:");
		lExpdate.setFont(f);
		lDate= new JLabel("DATE:");
		lDate.setFont(f);
		lPrate= new JLabel("PRODUCT RATE:");
		lPrate.setFont(f);
		
		lStatus= new JLabel("");
		
		tPrno=new JTextField(20);
        tPrname=new JTextField(20);
        tBatchno=new JTextField(20);
        tMfg=new JTextField(20);
        tExpdate=new JTextField(20);
        tdate=new JTextField(20);
        tPrate=new JTextField(20);
        
        bAdd= new JButton("ADD");
        bUpdate= new JButton("UPDATE");
        bDelete= new JButton("DELETE");
        bExit= new JButton("EXIT");
        
        lHeading.setBounds(150,10,1000,100);
        lPrno.setBounds(20,100,200,100);    tPrno.setBounds(200,140,150,30);
        lPrname.setBounds(20,160,200,100);  tPrname.setBounds(200,200,150,30);
        lBatchno.setBounds(20,220,200,100); tBatchno.setBounds(200,260,150,30);
        lMfg.setBounds(20,280,200,100);     tMfg.setBounds(200,320,150,30);
        
        lExpdate.setBounds(355,100,200,100); tExpdate.setBounds(530,140,150,30);
    	lDate.setBounds(355,160,200,100);   tdate.setBounds(530,200,150,30);
    	lPrate.setBounds(355,220,200,100);  tPrate.setBounds(530,260,150,30);
        
       	bAdd.setBounds(50,525,100,40);                    
    	bUpdate.setBounds(200,525,100,40);
    	bDelete.setBounds(350,525,100,40);
        bExit.setBounds( 500,525,100,40);
        
        add(lHeading);     
        add(lPrno);    add(tPrno);
        add(lPrname);  add(tPrname);
        add(lBatchno); add(tBatchno);
        add(lMfg);     add(tMfg);
        add(lExpdate); add(tExpdate);
        add(lDate);    add(tdate);
        add(lPrate);   add(tPrate);
        add(bAdd);     add(bUpdate);   
        add(bDelete);  add(bExit);
        add(lStatus);
        
        bExit.addActionListener(this);
        bAdd.addActionListener(this);
        bUpdate.addActionListener(this);
        bDelete.addActionListener(this); 
        		
    	JLabel Lab_img=new JLabel(new ImageIcon("plainframe.jpg"));
		Lab_img.setSize(700,700);
		add(Lab_img);
                
		setVisible(true);
        //this.cc.add(p);  

         
        String Tcol[] = {"Prid","Prname","Batchname","PrMfg","Pexp","Date","Prate"};  // Array of objects of String(Object type) class
		
		for(int i = 0 ; i <7 ; i++)			
		{
			dtm.addColumn(Tcol[i]);
		}
		try
		{
			initialize();
			rs = st.executeQuery("Select * From Product_info");
			while(rs.next())
			{
				for(int j = 0; j <7; j++)
				{
					String str = rs.getString(j+1);
					data[j] = str; 	
				}
				dtm.addRow(data);
			}
			rs.previous();
			int str= Integer.parseInt(rs.getString(1))+1;
		    tPrno.setText(String.valueOf(str));
			
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
			rs = st.executeQuery("Select * From Product_info");
        }
        catch(Exception e)
        {
            System.out.println("Exception init : " + e);
        }
    }
    
    public void actionPerformed(ActionEvent ae)
    {
    	String bname= ae.getActionCommand();
     	int flag=0;
        String str= tPrno.getText();
     	for(int i=0;i<str.length();i++)
     	{
     	  if(!(Character.isDigit(str.charAt(i))))
     	  {
     	     flag=1;
     	     break;
     	  }
    	}
    	
    	if(bname.equals("EXIT"))
    	{
    		dispose();
    	}
        if(bname.equals("ADD"))
     	{
     		if(tPrno.getText().equals("") || tPrname.getText().equals("") || tBatchno.getText().equals("")||tMfg.getText().equals("")||tPrate.getText().equals("")||tdate.getText().equals("")||tExpdate.getText().equals(""))
			{
				String msg = "Please fill all Necessory Data";
			   	JOptionPane.showMessageDialog(null,msg,"Pathology Lab System",
				JOptionPane.INFORMATION_MESSAGE);
					
			}
			else if(!(tPrname.getText().matches("([a-zA-Z.]+\\s*([a-zA-Z.]+\\s*([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]*)))")))
            {
            	String msg="Invalid  Name";
                JOptionPane.showMessageDialog((Component)null,msg,"Pathology Lab System",
               	JOptionPane.WARNING_MESSAGE);
               	tPrname.setText("");
               	tPrname.requestFocus();
          	}	
          	else if(flag==1)// Invalid No
          	{
          		 tPrno.setText("");
     		     tPrno.requestFocus();
     		     String msg = "Invalid Product Number";
     		     JOptionPane.showMessageDialog((Component)null,msg,"Pathology Lab System",
               	 JOptionPane.WARNING_MESSAGE);
          		
          	}
          	else
          	{
          	
     		try
     		{
     			initialize();
     			pst= con.prepareStatement("Insert into Product_info values(?,?,?,?,?,?,?)");
     			pst.setInt(1,Integer.parseInt(tPrno.getText()));
     			pst.setString(2,tPrname.getText());
     			pst.setString(3,tBatchno.getText());
     			pst.setString(4,tMfg.getText());
     			pst.setString(5,tExpdate.getText());
     			pst.setString(6,tdate.getText());
     			pst.setInt(7,Integer.parseInt(tPrate.getText()));
     			pst.executeUpdate();
     			tPrate.setText("");  tPrno.setText(""); tPrname.setText(""); tBatchno.setText(""); tMfg.setText(""); tdate.setText(""); tExpdate.setText("");
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
     	
     	if(bname.equals("UPDATE"))
     	{
     		 try
     		 {
     		   initialize();
     		   String tname= JOptionPane.showInputDialog(this,"Enter Product no to Update Record:"," ");
     		   int no= Integer.parseInt(tname);
		       tPrno.setText(""+no);
		      
		       pst=con.prepareStatement("select * from Product_info where Prid =?");
		       pst.setInt(1,no);
		        
		       rs=pst.executeQuery();
		       if(rs.next()!=false)
		       {
		          tPrno.setText(rs.getString(1)); 
      	          tPrname.setText(rs.getString(2)); 
      	          tBatchno.setText(rs.getString(3));
      	          tMfg.setText(rs.getString(4));   
      	          tExpdate.setText(rs.getString(5));
      	          tdate.setText(rs.getString(6));
      	          tPrate.setText(rs.getString(7));
      	          bUpdate.setLabel("SAVE");
      	          JOptionPane.showMessageDialog(this," Enter Record To Update:","Update",JOptionPane.WARNING_MESSAGE);
		       }
		       else
     		   {
     		  	 JOptionPane.showMessageDialog((Component)null,"No such record found....","Sorry..",JOptionPane.INFORMATION_MESSAGE);
     		   }
		         
		     }
		     catch(Exception e)
		     {JOptionPane.showMessageDialog(this,"Invalid Record.Try Again!!!");
		     }
		  }
     	if(bname.equals("SAVE"))
     	{
     		if(tPrno.getText().equals("") || tPrname.getText().equals("") || tBatchno.getText().equals("")||tMfg.getText().equals("")||tPrate.getText().equals("")||tdate.getText().equals("")||tExpdate.getText().equals(""))
			{
				String msg = "Please fill all Necessory Data";
			   	JOptionPane.showMessageDialog(null,msg,"Pathology Lab System",
				JOptionPane.INFORMATION_MESSAGE);
					
			}
			else if(!(tPrname.getText().matches("([a-zA-Z.]+\\s*([a-zA-Z.]+\\s*([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]*)))")))
            {
            	String msg="Invalid  Name";
                JOptionPane.showMessageDialog((Component)null,msg,"Pathology Lab System",
               	JOptionPane.WARNING_MESSAGE);
               	tPrname.setText("");
               	tPrname.requestFocus();
          	}	
          	else if(flag==1)// Invalid No
          	{
          	     tPrno.setText("");
     		     tPrno.requestFocus();
     		     String msg = "Invalid Product Number";
     		     JOptionPane.showMessageDialog((Component)null,msg,"Pathology Lab System",
               	 JOptionPane.WARNING_MESSAGE);
          		
          	}
            else 
            {
            
     		try
     		{
     			int Prid =Integer.parseInt(tPrno.getText());
     			pst= con.prepareStatement("Update Product_info SET Prid=?, Prname=?, Batchname=?, Pmfg=?, Pexp=?,Prate=?  where Prid=? ");
     			pst.setInt(1, Prid);
     			pst.setString(2,tPrname.getText());
     			pst.setString(3,tBatchno.getText());
     			pst.setString(4,tMfg.getText());
     			pst.setString(5,tExpdate.getText());
     			pst.setInt(6,Integer.parseInt(tPrate.getText()));
     			pst.setInt(7,Prid);
     			pst.executeUpdate();
     			
     			JOptionPane.showMessageDialog((Component)null,"Records Updated successfully......","Updated.",JOptionPane.INFORMATION_MESSAGE);
     			tPrate.setText("");  tPrno.setText(""); tPrname.setText(""); tBatchno.setText(""); tMfg.setText(""); tdate.setText(""); tExpdate.setText("");
     			
     			pst.close();
     			con.close();
     			
     		}
     		catch(Exception e)
     		{
     		JOptionPane.showMessageDialog(this,"Invalid Record.Try Again!!!");
     		}
            }//else
     	}
     	if(bname.equals("DELETE"))
     	{
		    initialize();
     		if(tPrno.getText().equals(""))
			{
				String msg = "There is no Product to Delete";
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
     			      int Prid = Integer.parseInt(tPrno.getText());
				System.out.println(Prid);
     		          pst= con.prepareStatement("Delete from Product_info where Prid=? ");
     			      pst.setInt(1,Prid);
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
   	 new Stock_detFrame();
   }*/
    
}