//
import java.awt.*;
import java.awt.print.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;


public class Serology2_form extends JDialog implements  ActionListener  
{
    JLabel ltname,ltname1,lpname,l1,l2,l3,l4,l5,l6,l7;
	JTextField t1,t2,t3,t4,t5; 
	
	JButton b1,b2;
	List list;
	JLabel RN,NAME,REFBY,AGE,SEX,DATE,L1,L2,L3,L4;
	JLabel RN1,NAME1,REFBY1,AGE1,SEX1,DATE1,Ldrname;
	String regno,date,refby,age,sex;
	Container cc= getContentPane();
    Font f1 = new Font("Arial Rounded MT Bold",Font.BOLD+Font.ITALIC,20);
	Font f2 = new Font("Arial Rounded MT Bold",Font.BOLD+Font.ITALIC,14);
    
	Connection con;
	Statement st;
    ResultSet rs;
    PreparedStatement pst;
    	
    public Serology2_form()
    {
		setTitle("TEST SEROLOGY RA FACTOR & HBSAG");
	    cc.setLayout(null);
		setLocation(100,20);
		setSize(700,650);
		cc.setBackground(Color.white);
		
    	setVisible(true);
    	
    	ltname= new JLabel("RHEUMATOID FACTOR (RA FACTOR)");
    	ltname1= new JLabel("HBSAG");
    	lpname= new JLabel("PATIENT NAME:");
    	
    	l1= new JLabel("RESULT:");    t1= new JTextField(10);   
    	l2= new JLabel("METHOD:");    t2= new JTextField(10);
    	l3= new JLabel("RESULT:");    t3= new JTextField(10);
    	l4= new JLabel("METHOD:");    t4= new JTextField(10);
    	
    	ltname.setFont(f1);
    	ltname.setBounds(50,10,1000,100);
    	ltname1.setFont(f1);
    	ltname1.setBounds(50,200,1000,100);
    	lpname.setFont(f2);
    	lpname.setBounds(50,350,1000,80);
    	
    	l1.setBounds(50,100,200,100);         t1.setBounds(270,140,200,30);
        l1.setFont(f2);
    	l2.setBounds(50,150,200,100);         t2.setBounds(270,190,200,30);
    	l2.setFont(f2);
    	l3.setBounds(50,240,200,100);         t3.setBounds(270,270,200,30);
    	l3.setFont(f2);
    	l4.setBounds(50,300,200,100);         t4.setBounds(270,330,200,30);
    	l4.setFont(f2);
    
    	  
    	b1= new JButton("ADD");
    	b1.setBounds(200,520,100,40);
    	b2= new JButton("EXIT");
    	b2.setBounds(350,520,100,40);
  
    	    	
    	list= new List();
    	try
        {
         	
          initialize();
          rs = st.executeQuery("Select * From PatientEntry_Mast");
         
          String Pname= new String("");
          while(rs.next())
          { 
          	Pname= rs.getString(3);
         	list.add(Pname);
          }
          
          
          list.setBounds(40,410,200,100);
          cc.add(list);
          cc.add(b1);   cc.add(b2);
          cc.add(ltname); cc.add(ltname1); 
          cc.add(lpname);
          cc.add(l1);   cc.add(t1);
          cc.add(l2);   cc.add(t2);
          cc.add(l3);   cc.add(t3);
          cc.add(l4);   cc.add(t4);
                            
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
    	if(ae.getActionCommand().equals("EXIT"))
    	{
    		dispose();
    	}
    
     	if(ae.getActionCommand().equals("ADD"))
     	{
     		String Pname= list.getSelectedItem();
     		if(t1.getText().equals("")||t2.getText().equals("")||t3.getText().equals("")||t4.getText().equals(""))
     		{
     			JOptionPane.showMessageDialog((Component)null,"Please insert proper value..","Sorry",JOptionPane.ERROR_MESSAGE);	
     		}
     		else
     		{
     		try
     		{
     			initialize();
     			pst= con.prepareStatement("Insert into Test_Ser_RA_HBSAG values(?,?,?,?,?)");
     			pst.setString(1,Pname);
     			pst.setString(2,t1.getText());
     			pst.setString(3,t2.getText());
     			pst.setString(4,t3.getText());
     			pst.setString(5,t4.getText());
     			
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
     			pst.close();
     			con.close();
     		}
     		catch(Exception e)
     		{
     			 JOptionPane.showMessageDialog(this,"Invalid Record.Try Again!!!");
     		}
     		this.setBounds(0,0,700,1000);
     		this.setBackground(Color.white);
     		ltname.setText("Prajyot Pathology Lab");
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
     	    
     	    l1.setBounds(50,200,150,30);   l2.setBounds(50,250,150,30);  l3.setBounds(50,330,200,30);       	
    	    l4.setBounds(50,360,150,30); 
     		b1.setBounds(200,600,100,40);
     		b1.setLabel("PRINT");
     		
     		L1=new JLabel(t1.getText());  L1.setBounds(220,200,150,30); L1.setFont(f2);	     
     	    L2=new JLabel(t2.getText());  L2.setBounds(220,250,150,30); L2.setFont(f2);	     
     	    L3=new JLabel(t3.getText());  L3.setBounds(220,330,150,30); L3.setFont(f2);
     	    L4=new JLabel(t4.getText());  L4.setBounds(220,360,150,30); L4.setFont(f2);	     
     	   
     	    t1.setVisible(false);    t2.setVisible(false);      t3.setVisible(false);
     	    t4.setVisible(false);    list.setVisible(false);    b2.setVisible(false);
     	    
     	    l1= new JLabel("RHEUMATOID FACTOR (RA FACTOR)"); l1.setBounds(50,150,300,70); l1.setFont(f2);
     	    ltname1.setBounds(50,280,200,70);   ltname1.setFont(f2);        
     	    
     	    lpname.setText("SEROLOGY TEST");
     	    lpname.setBounds(100,100,300,100);
     	    
     	    cc.add(L1); cc.add(L2); cc.add(L3); cc.add(L4); cc.add(l1);
     		cc.add(RN); cc.add(RN1);
     		cc.add(NAME); cc.add(NAME1);
     		cc.add(REFBY);cc.add(REFBY1);
     		cc.add(AGE); cc.add(AGE1);
     		cc.add(SEX);cc.add(SEX1);
     		cc.add(DATE);cc.add(DATE1); 
     	}
     	}//else
     	
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
    /*public static void main (String[] args) throws Exception
    {
    	new Serology2_form();
    }*/
    
    
    
}