//
import java.awt.*;
import java.awt.print.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;


public class HIV_form extends JDialog implements  ActionListener 
{
	JLabel ltname,lpname,l1,l2;
	JTextField t1,t2;
	
	JLabel pname,pname1,pdate,pdate1,pref,pref1,page,page1,pregno,pregno1; 
	
	JButton b1,b2;
	List list;
	
	
    JLabel RN,NAME,REFBY,AGE,SEX,DATE,l3,l4;
	JLabel RN1,NAME1,REFBY1,AGE1,SEX1,DATE1,Ldrname;
	String regno,date,refby,age,sex;
   
    Font f1 = new Font("Arial Rounded MT Bold",Font.BOLD+Font.ITALIC,20);
	Font f2 = new Font("Arial Rounded MT Bold",Font.BOLD+Font.ITALIC,14);
    Container cc= getContentPane(); 	
	
	Connection con;
	Statement st;
    ResultSet rs;
    PreparedStatement pst;
   
    public HIV_form()
    {
    	setTitle("TEST HIV ANTIBODIES");
		
	    cc.setLayout(null);
		setLocation(100,20);
		setSize(700,650);
		cc.setBackground(Color.white);
		
		setVisible(true);
    	
    	ltname= new JLabel("HIV I & II ANTIBODIES TEST");
    	lpname= new JLabel("PATIENT NAME:");
    	l1= new JLabel("OBSERVATION:");    t1= new JTextField(10);   
    	l2= new JLabel("METHOD:");          t2= new JTextField(10);
    	
    	ltname.setFont(f1);
    	ltname.setBounds(150,10,1000,100);
    	lpname.setFont(f2);
    	lpname.setBounds(50,350,1000,80);
    	
    	l1.setBounds(50,130,200,100);         t1.setBounds(270,170,200,30);
        l1.setFont(f2);
    	l2.setBounds(50,180,200,100);         t2.setBounds(270,230,200,30);
    	l2.setFont(f2);
   
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
          cc.add(ltname); cc.add(lpname);
          cc.add(l1);   cc.add(t1);
          cc.add(l2);   cc.add(t2);
                            
          rs.close();
          st.close();
          con.close();
        }
        catch(Exception e)
		{
			System.out.println("Exception : " + e);
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
    	initialize();
     	if(ae.getActionCommand().equals("ADD"))
     	{
     		String Pname= list.getSelectedItem();
     	    if(t1.getText().equals("")||t2.getText().equals(""))
     	    {
     	    	JOptionPane.showMessageDialog((Component)null,"Please insert proper value..","Sorry",JOptionPane.ERROR_MESSAGE);	
     	    }
     	    else
     	    {
     		try
     		{
     			pst= con.prepareStatement("Insert into Test_HIV values(?,?,?)");
     			pst.setString(1,Pname);
     			pst.setString(2,t1.getText());
     			pst.setString(3,t2.getText());
     			
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
     		this.setBounds(0,0,700,1000); this.setBackground(Color.WHITE);
     		ltname.setText("Jadhav Pathology Lab");
     		ltname.setBounds(100,-110,300,300);
     		
     		RN= new JLabel("REG NO.:");    RN.setBounds(50,50,150,60);      RN.setFont(f2);
     	    NAME=new JLabel("DR.NAME  :");  NAME.setBounds(50,70,150,60);    NAME.setFont(f2);
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
     	    lpname.setText("HIV I & II ANTIBODIES");
     	    lpname.setBounds(100,100,250,100);	  
     	    l3=new JLabel(t1.getText());           l3.setBounds(220,165,150,30);    l3.setFont(f2);
     	    l4=new JLabel(t2.getText());           l4.setBounds(220,215,150,30);    l4.setFont(f2);   
            b1.setBounds(200,600,100,40);
            b1.setLabel("PRINT");
     		
     		b2.setVisible(false);
     		t1.setVisible(false);
     		t2.setVisible(false);
     		list.setVisible(false);
     		
     	    cc.add(RN); cc.add(RN1);
     		cc.add(NAME); cc.add(NAME1);
     		cc.add(REFBY);cc.add(REFBY1);
     		cc.add(AGE); cc.add(AGE1);
     		cc.add(SEX);cc.add(SEX1);
     		cc.add(DATE);cc.add(DATE1);
     		cc.add(l3);  cc.add(l4);
     	}//else
     	}
     	
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
    	new HIV_form();
    }*/
    
}

