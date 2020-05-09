//
import java.awt.*;
import java.awt.print.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;


public class Biochem_form extends JDialog implements  ActionListener  
{
    JLabel ltname,lpname,ll1,ll2,ll3,ll4,ll5;
    JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11;
	JTextField t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13; 
	
	JButton b1,b2;
	List list;
	
	JLabel RN,NAME,REFBY,AGE,SEX,DATE,L1,L2,L3,L4,L5,L6,L7,L8,L9,L10,L11,L12;
	JLabel RN1,NAME1,REFBY1,AGE1,SEX1,DATE1,Ldrname;
	String regno,date,refby,age,sex;
	
    Font f1 = new Font("Arial Rounded MT Bold",Font.BOLD+Font.ITALIC,20);
	Font f2 = new Font("Arial Rounded MT Bold",Font.BOLD+Font.ITALIC,14);
    Container cc= getContentPane();
	Connection con;
	Statement st;
    ResultSet rs;
    PreparedStatement pst;
    	
    public Biochem_form()
    {
    	setTitle("BIOCHEMISTRY TEST");
		
		setLocation(100,20);
		setSize(700,650);
		cc.setBackground(Color.white);
		cc.setLayout(null);
	
    	setVisible(true);
    	
    	ltname= new JLabel("BIOCHEMISTRY");  
    	ltname.setFont(f1);
    	ll1= new JLabel("TEST");
    	ll1.setFont(f1);
    	ll2= new JLabel("PLASMA GLUCOSE FASTING");
    	ll2.setFont(f1);
    	ll3= new JLabel("PLASMA GLUCOSE POST-PRANDIAL");
    	ll3.setFont(f1);
    	ll4= new JLabel("RESULTS");
    	ll4.setFont(f1);
    	ll5= new JLabel("METHOD");
    	ll5.setFont(f1);
    	
    	lpname= new JLabel("PATIENT NAME:");
    	
    	l1= new JLabel("RESULTS      :");     t1= new JTextField(10);   t9=new JTextField(10);   
    	l2= new JLabel("URINE SUGAR   :");     t2= new JTextField(10);   
    	l3= new JLabel("URINE ACETONE :");     t3= new JTextField(10);
    	l4= new JLabel("RESULTS      :");     t4= new JTextField(10);   t10=new JTextField(10);
 		l5= new JLabel("URINE SUGAR   :");     t5= new JTextField(10);
   	    l6= new JLabel("URINE ACETONE :");     t6= new JTextField(10);
        l7= new JLabel("SERUM CREATININE:");     t7= new JTextField(10);   t11=new JTextField(10);
        l8= new JLabel("SGPT  :");     t8= new JTextField(10);   t12=new JTextField(10);         
        
    	l1.setFont(f2);   l2.setFont(f2);  l3.setFont(f2);
    	l4.setFont(f2);   l5.setFont(f2);  l6.setFont(f2); 
    	l7.setFont(f2);   l8.setFont(f2);  
    	
    	ltname.setBounds(50,-30,1000,100);
    	ll1.setBounds(50,30,200,60);
    	ll4.setBounds(270,30,200,60);
    	ll5.setBounds(480,30,200,60);
    	
    	ll2.setBounds(50,70,1000,40);
    	ll3.setBounds(50,220,1000,40);
    
    	
    	l1.setBounds(50,100,200,60);         t1.setBounds(270,120,170,25);   t9.setBounds(460,120,170,25);
      	l2.setBounds(50,140,200,60);         t2.setBounds(270,160,170,25);
    	l3.setBounds(50,180,200,60);         t3.setBounds(270,200,170,25);
    	
    	l4.setBounds(50,240,200,60);         t4.setBounds(270,260,170,25);   t10.setBounds(460,260,170,25);
 		l5.setBounds(50,280,200,60);         t5.setBounds(270,300,170,25);
    	l6.setBounds(50,320,200,60);         t6.setBounds(270,340,170,25);
    	l7.setBounds(50,360,200,60);         t7.setBounds(270,380,170,25);   t11.setBounds(460,380,170,25);
    	l8.setBounds(50,400,200,60);         t8.setBounds(270,420,170,25);   t12.setBounds(460,420,170,25);
    	
    	b1= new JButton("ADD");
    	b1.setBounds(300,540,100,40);
    	b2= new JButton("EXIT");
    	b2.setBounds(450,540,100,40);
    	    	
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
          
          lpname.setFont(f2);
    	  lpname.setBounds(50,450,1000,50);
    	
          list.setBounds(40,500,200,100);
          cc.add(list);
          cc.add(b1);   cc.add(b2);
          cc.add(ltname);  cc.add(lpname);
          cc.add(ll1); cc.add(ll2); 
          cc.add(ll3); cc.add(ll4);  cc.add(ll5);
          cc.add(l1);   cc.add(t1);
          cc.add(l2);   cc.add(t2);
          cc.add(l3);   cc.add(t3);
          cc.add(l4);   cc.add(t4);
          cc.add(l5);   cc.add(t5);
          cc.add(l6);   cc.add(t6);
          cc.add(l7);   cc.add(t7);
          cc.add(l8);   cc.add(t8);
          cc.add(t9);   cc.add(t10);
          cc.add(t11);  cc.add(t12);
         
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
    	
     	if(ae.getActionCommand().equals("ADD"))
     	{
     		String Pname= list.getSelectedItem();
     		if(t1.getText().equals("")||t2.getText().equals("")||t3.getText().equals("")||t4.getText().equals("")||t5.getText().equals("")||t6.getText().equals("")||t7.getText().equals("")||t8.getText().equals("")||t9.getText().equals("")||t10.getText().equals("")||t11.getText().equals("")||t12.getText().equals(""))
     		{
     		  JOptionPane.showMessageDialog((Component)null,"Please insert proper value..","Sorry",JOptionPane.ERROR_MESSAGE);	
     		}
     		else
     		{
     		   try
     		   {
     		   	initialize();
     			pst= con.prepareStatement("Insert into Test_Biochemistry values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
     			pst.setString(1,Pname);
     			pst.setString(2,t1.getText());
     			pst.setString(3,t9.getText());
     			pst.setString(4,t2.getText());
     			pst.setString(5,t3.getText());
     			pst.setString(6,t4.getText());
     			pst.setString(7,t10.getText());
     			pst.setString(8,t5.getText());
     			pst.setString(9,t6.getText());
     			pst.setString(10,t7.getText());
     			pst.setString(11,t11.getText());
     			pst.setString(12,t8.getText());
     			pst.setString(13,t12.getText());
     			
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
     		   this.setBounds(0,0,700,1000);  this.setBackground(Color.white);
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
     	    
     	       lpname.setText("BIOCHEMISTRY");
     	       lpname.setBounds(100,100,150,100);
     	    
     	       l1.setBounds(50,200,150,30);   l2.setBounds(50,230,150,30);   l3.setBounds(50,260,150,30);  
     	       l4.setBounds(50,320,150,30);   l5.setBounds(50,350,150,30);   l6.setBounds(50,380,150,30);  
     	       l7.setBounds(50,410,200,30);   l8.setBounds(50,440,150,30);   
     	    
     	       b1.setBounds(200,600,100,40);
     	       b1.setLabel("PRINT");
     	   	    
     	       L1=new JLabel(t1.getText());  L1.setBounds(220,200,150,30); L1.setFont(f2);	     
     	       L2=new JLabel(t2.getText());  L2.setBounds(220,230,150,30); L2.setFont(f2);	     
     	       L3=new JLabel(t3.getText());  L3.setBounds(220,260,150,30); L3.setFont(f2);
     	       L4=new JLabel(t4.getText());  L4.setBounds(220,320,150,30); L4.setFont(f2);	     
     	       L5=new JLabel(t5.getText());  L5.setBounds(220,350,150,30); L5.setFont(f2);	     
     	       L6=new JLabel(t6.getText());  L6.setBounds(220,380,150,30); L6.setFont(f2);	     
     	       L7=new JLabel(t7.getText());  L7.setBounds(220,410,150,30); L7.setFont(f2);	     
     	       L8=new JLabel(t8.getText());  L8.setBounds(220,440,150,30); L8.setFont(f2);	     
     	    
     	       L9=new JLabel(t9.getText());  L9.setBounds(460,200,150,30); L9.setFont(f2);	    
     	       L10=new JLabel(t10.getText()); L10.setBounds(460,320,150,30); L10.setFont(f2);	     	     
     	       L11=new JLabel(t11.getText()); L11.setBounds(460,410,150,30); L11.setFont(f2);	     	     
     	       L12=new JLabel(t12.getText()); L12.setBounds(460,440,150,30); L12.setFont(f2);	     	     
     	     
     	       ll2.setBounds(50,170,1000,50);     ll3.setBounds(50,280,1000,50);
    	       ll4.setBounds(220,150,1000,50); ll1.setBounds(50,150,1000,50);  ll5.setBounds(460,150,1000,50);
     	       ll2.setFont(f2); ll3.setFont(f2); ll4.setFont(f2); ll1.setFont(f2); ll5.setFont(f2); 
     	    	
     	       t1.setVisible(false);    t2.setVisible(false);      t3.setVisible(false);
     	       t4.setVisible(false);	 t5.setVisible(false);		t6.setVisible(false);
     	       t7.setVisible(false);	 t8.setVisible(false);		t9.setVisible(false);
     	       t10.setVisible(false);	 t11.setVisible(false);		t12.setVisible(false);
     	       list.setVisible(false);    b2.setVisible(false);
     	    
     	       cc.add(RN); cc.add(RN1);
     		   cc.add(NAME); cc.add(NAME1);
     		   cc.add(REFBY);cc.add(REFBY1);
     		   cc.add(AGE); cc.add(AGE1);
     		   cc.add(SEX);cc.add(SEX1);
     		   cc.add(DATE);cc.add(DATE1);
     		
     		   cc.add(L1); cc.add(L2); cc.add(L3); cc.add(L4); cc.add(L5); cc.add(L6); cc.add(L7); cc.add(L8); cc.add(L9);
     		   cc.add(L10); cc.add(L11); cc.add(L12);  
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
    	new Biochem_form();
    }*/
    
    
    
}